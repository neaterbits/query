package com.neaterbits.query.sql.dsl.api;

import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

/**
 * Query data source implementation for JPA
 * @author nhl
 *
 */

final class QueryDataSourceJPA extends QueryDataSourceBase {

	private final EntityManager em;

	QueryDataSourceJPA(EntityManager entityManager) {

		if (entityManager == null) {
			throw new IllegalArgumentException("entityManager == null");
		}

		this.em = entityManager;
	}
	
	private JPACompletePreparedQuery prepareQuery(CompiledQuery query) {

		final StringBuilder sb = new StringBuilder();

		prepare(sb, query);

		final String jpql = sb.toString();
	
		System.out.println("## jpql:\n" + jpql);
		
		final javax.persistence.Query jpaQuery = em.createQuery(jpql);

		return new JPACompletePreparedQuery(query.isSingleResult(), jpaQuery);
	}
	
	// TODO: Assure unique
	private static String resultVarName(Class<?> resultType) {
		return "_result";
	}

	private String entityName(Class<?> entityType) {
		return entityType.getSimpleName();
	}

	private void prepare(StringBuilder sb, CompiledQuery query) {
		
		final Class<?> resultType = query.getResultType();

		sb.append("SELECT ");

		final CompiledMappings mappings = query.getMappings();
		if (mappings != null) {
			prepareMappings(sb, mappings);
		}
		else {
			sb.append(resultType.getSimpleName()).append(" ").append(resultVarName(resultType));
		}

		sb.append("\n").append("FROM ");
		
		// Add all select sources
		prepareSelectSources(sb, (CompiledSelectSources<CompiledSelectSource>)query.getSelectSources());
		
		// Prepare conditions if present
		
		
		if (query.getConditions() != null) {
			
			final ParamNameAssigner paramNameAssigner = new ParamNameAssigner();
			final CompileConditionParam param = new CompileConditionParam(paramNameAssigner, em);
			
			prepareConditions(query.getConditions(), param);
		}
	}
	
	
	private void prepareMappings(StringBuilder sb, CompiledMappings mappings) {
		
		commaSeparated(sb, mappings.getMappings(), (CompiledMapping mapping) -> {

			final CompiledFieldReference field = mapping.getField();

			prepareFieldReference(sb::append, field, em);
		});
	}

	private void prepareSelectSources(StringBuilder sb, CompiledSelectSources<CompiledSelectSource> sources) {

		commaSeparated(sb, sources.getSources(), (CompiledSelectSource source) -> {

			final String entityName = entityName(source.getType());

			sb.append(entityName).append(' ').append(source.getName());
		});
	}
	
	private static void prepareFieldReference(Consumer<String> c, CompiledFieldReference field, EntityManager em) {

		final CompiledSelectSource source = field.getSource();

		final CompiledGetter getter = field.getGetter();

		final String columnName = getColumnNameForGetter(source, getter, em);

		c.accept(source.getName());
		c.accept(".");
		c.accept(columnName);
	}

	private static final ConditionToOperatorVisitor conditionToOperatorVisitor = new ConditionToOperatorVisitor();
	
	private void prepareConditions(CompiledConditions conditions, CompileConditionParam param) {

		boolean first = true;
		
		final String opString;
		
		if (conditions instanceof CompiledConditionsAnd) {
			opString = "AND";
		}
		else if (conditions instanceof CompiledConditionsOr) {
			opString = "OR";
		}
		else if (conditions instanceof CompiledConditionsSingle) {
			opString = null;
		}
		else {
			throw new IllegalStateException("unknown conditions " + conditions.getClass());
		}

		for (CompiledCondition condition : conditions.getConditions()) {

			final String os;

			if (first) {
				os = "WHERE";
			}
			else {
				os = opString;
			}
			
			param.append(os);

			prepareFieldReference(param::append, condition.getLhs(), em);

			param.setValue(condition.getValue());
			
			// Operator and value
			condition.getOriginal().visit(conditionToOperatorVisitor, param);
		}
	}
	
	
	private static final class ConditionToOperatorVisitor implements ConditionVisitor<CompileConditionParam, Void> {

		@Override
		public Void onEqualTo(ConditionEqualToImpl condition, CompileConditionParam param) {
			appendOpAndValue("=", param.getValue(), param);

			return null;
		}

		@Override
		public Void onNotEqualTo(ConditionNotEqualToImpl condition, CompileConditionParam param) {
			
			appendOpAndValue("!=", param.getValue(), param);
			
			return null;
		}

		@Override
		public Void onIn(ConditionInImpl condition, CompileConditionParam param) {

			param.append("(");
			
			condition.getValue().visit(conditionValueVisitor, param);

			param.append(")");

			return null;
		}

		@Override
		public Void onGreaterThan(ConditionGreaterThanImpl condition, CompileConditionParam param) {

			appendOpAndValue(">", param.getValue(), param);

			return null;
		}

		@Override
		public Void onGreaterThanOrEqual(ConditionGreaterThanOrEqualImpl condition, CompileConditionParam param) {

			appendOpAndValue(">=", param.getValue(), param);
			
			return null;
		}

		@Override
		public Void onLessThan(ConditionLessThanImpl condition, CompileConditionParam param) {

			appendOpAndValue("<", param.getValue(), param);

			return null;
		}

		@Override
		public Void onLessThanOrEqual(ConditionLessThanOrEqualImpl condition, CompileConditionParam param) {

			appendOpAndValue("<=", param.getValue(), param);

			return null;
		}

		@Override
		public Void onStartsWith(ConditionStringStartsWith condition, CompileConditionParam param) {

			appendLike(false, true, param.getValue(), param);

			return null;
		}

		@Override
		public Void onEndsWith(ConditionStringEndsWith condition, CompileConditionParam param) {

			appendLike(true, false, param.getValue(), param);

			return null;
		}

		@Override
		public Void onContains(ConditionStringContains condition, CompileConditionParam param) {

			appendLike(true, true, param.getValue(), param);

			return null;
		}

		@Override
		public Void onMatches(ConditionStringMatches condition, CompileConditionParam param) {
			throw new IllegalArgumentException("matches not supported");
		}
	}


	private static void appendLike(boolean wildcardBefore, boolean wildcardAfter, ConditionValueImpl value, CompileConditionParam param) {

		param.append("LIKE ");

		if (value instanceof ConditionValueLiteralStringImpl) {

			value.visit(conditionValueVisitor, param);

			param.completeResolvedCondition();
		}
		else if (value instanceof ConditionValueParamImpl) {
			param.unresolvedCondition(prefix -> new JPAConditionLikeWithParamUnresolved(prefix, wildcardBefore, wildcardAfter));
		}
		else {
			throw new UnsupportedOperationException("Neither string nor param value for LIKE query: " + value.getClass().getName());
		}
	}


	private static void appendOpAndValue(String op, ConditionValueImpl value, CompileConditionParam param) {
		param.append(op).append(" ");
		
		value.visit(conditionValueVisitor, param);
		
		param.completeResolvedCondition();
	}

	private static final ConditionValueVisitor<CompileConditionParam, Void> conditionValueVisitor
						= new ConditionValueVisitor<CompileConditionParam, Void>() {

		@Override
		public Void onLiteralAny(ConditionValueLiteralAnyImpl<?> value, CompileConditionParam param) {

			appendLiteral(value.getLiteral(), param);
			
			return null;
		}

		@Override
		public Void onLiteralString(ConditionValueLiteralStringImpl value, CompileConditionParam param) {

			appendStringLiteral(value.getLiteral(), param);

			return null;
		}

		@Override
		public Void onArray(ConditionValueArrayImpl value, CompileConditionParam param) {

			final Object [] values = value.getValues();
			
			for (int i = 0;  i < values.length; ++ i) {
				if (i > 0) {
					param.append(", ");
				}
			
				appendLiteral(values[i], param);
			}

			return null;
		}
		
		@Override
		public Void onParam(ConditionValueParamImpl value, CompileConditionParam param) {
			
			param.appendParam(value.getParam());
			
			return null;
		}

		@Override
		public Void onGetter(ConditionValueGetterImpl value, CompileConditionParam param) {
			throw new UnsupportedOperationException("Getter should have been compiled");
		}

		@Override
		public Void onFieldReference(ConditionValueFieldRerefenceImpl value, CompileConditionParam param) {
			
			prepareFieldReference(param::append, value.getFieldReference(), param.getEntityManager());
			
			return null;
		}
	};
	
	private static void appendStringLiteral(String literal, CompileConditionParam param) {
		param.append("'").append(literal).append("'");
	}
	
	private static void appendLiteral(Object literal, CompileConditionParam param) {
		
		if (literal instanceof String) {
			appendStringLiteral((String)literal, param);
		}
		else if (literal instanceof Integer) {
			param.append(String.valueOf((Integer)literal));
		}
		else {
			throw new UnsupportedOperationException("Unknown literal of type " + literal.getClass().getName());
		}
	}
	
	
	private static <T> void commaSeparated(StringBuilder sb, Iterable<T> iter, Consumer<T> c) {
		separated(sb, iter, ", ", c);
	}
	
	private static <T> void separated(StringBuilder sb, Iterable<T> iter, String separator, Consumer<T> c) {
		boolean first = true;

		// We are returning a mapped type, get each value
		for (T t : iter) {
			// Must return mappings
			if (!first) {
				sb.append(separator);
			}
			else {
				first = false;
			}

			c.accept(t);
		}
	}

	private static String getColumnNameForGetter(CompiledSelectSource source, CompiledGetter getter, EntityManager em) {

		// Look up in entity manager
		
		final Metamodel metaModel = em.getEntityManagerFactory().getMetamodel();
				final EntityType<?> entityType = metaModel.entity(source.getType());

		if (entityType == null) {
			throw new IllegalStateException("No entity type for " + source.getType());
		}

		final Attribute<?, ?> attr = findAttr(entityType, getter.getGetterMethod());
		
		if (attr == null) {
			throw new IllegalArgumentException("No attribute for getter " + getter);
		}

		return attr.getName();
	}
	
	private static Attribute<?, ?> findAttr(EntityType<?> entityType, Method getterMethod) {
		Attribute<?, ?> found = null;
		
		// Find attribute
		for (Attribute<?, ?> attr : entityType.getAttributes()) {
			final Member m  = attr.getJavaMember();

			if (m instanceof Method) {
				final Method method = (Method)m;
				
				if (method.equals(getterMethod)) {
					found = attr;
				}
			}
			else {
				throw new UnsupportedOperationException("Does not support field members for now");
			}
		}

		return found;
	}
	

	@Override
	DSPreparedQuery prepareSingleQuery(CompiledQuery query) {

		if (query == null) {
			throw new IllegalArgumentException("query == null");
		}

		return prepareQuery(query);
	}

	@Override
	DSPreparedQuery prepareMultiQuery(CompiledQuery query) {

		if (query == null) {
			throw new IllegalArgumentException("query == null");
		}

		return prepareQuery(query);
	}
}

