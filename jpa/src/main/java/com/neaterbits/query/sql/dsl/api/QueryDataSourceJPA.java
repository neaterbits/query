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
	
	private JPAPreparedQuery prepareQuery(CompiledQuery query) {

		final StringBuilder sb = new StringBuilder();

		prepare(sb, query);

		final String jpql = sb.toString();
	
		System.out.println("## jpql:\n" + jpql);
		
		final javax.persistence.Query jpaQuery = em.createQuery(jpql);

		return new JPAPreparedQuery(jpaQuery);
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
		
		// Prepare clauses if present
		
	}
	
	
	private void prepareMappings(StringBuilder sb, CompiledMappings mappings) {
		
		commaSeparated(sb, mappings.getMappings(), (CompiledMapping mapping) -> {

			final CompiledFieldReference field = mapping.getField();

			prepareFieldReference(sb, field);
		});
	}

	private void prepareSelectSources(StringBuilder sb, CompiledSelectSources<CompiledSelectSource> sources) {

		commaSeparated(sb, sources.getSources(), (CompiledSelectSource source) -> {

			final String entityName = entityName(source.getType());

			sb.append(entityName).append(' ').append(source.getName());
		});
	}
	
	private void prepareFieldReference(StringBuilder sb, CompiledFieldReference field) {

		final CompiledSelectSource source = field.getSource();

		final CompiledGetter getter = field.getGetter();

		final String columnName = getColumnNameForGetter(source, getter);

		sb.append(source.getName()).append('.').append(columnName);
	}
	
	private static final ConditionToOperatorVisitor conditionToOperatorVisitor = new ConditionToOperatorVisitor();
	
	private void prepareConditions(CompiledConditions conditions, CompileConditionParam param) {

		final StringBuilder sb = param.sb;
		
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
			if (first) {
				sb.append("WHERE");
			}
			else {
				sb.append(opString);
			}

			sb.append(' ');

			prepareFieldReference(sb, condition.getLhs());

			// Operator and value
			condition.getOriginal().visit(conditionToOperatorVisitor, param);
			
		}
	}
	
	private static class CompileConditionParam {
		private final StringBuilder sb;
		private final ParamNameAssigner paramNameAssigner;

		CompileConditionParam(StringBuilder sb, ParamNameAssigner paramNameAssigner) {
			
			if (sb == null) {
				throw new IllegalArgumentException("sb == null");
			}
			
			if (paramNameAssigner == null) {
				throw new IllegalArgumentException("paramNameAssigner == null");
			}
			
			this.sb = sb;
			this.paramNameAssigner = paramNameAssigner;
		}
	}
	
	
	
	private static final class ConditionToOperatorVisitor implements ConditionVisitor<CompileConditionParam, Void> {

		@Override
		public Void onEqualTo(ConditionEqualToImpl condition, CompileConditionParam param) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Void onNotEqualTo(ConditionNotEqualToImpl condition,
				CompileConditionParam param) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Void onIn(ConditionInImpl condition, CompileConditionParam param) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Void onGreaterThan(ConditionGreaterThanImpl condition,
				CompileConditionParam param) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Void onGreaterThanOrEqual(
				ConditionGreaterThanOrEqualImpl condition,
				CompileConditionParam param) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Void onLessThan(ConditionLessThanImpl condition,
				CompileConditionParam param) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Void onLessThanOrEqual(ConditionLessThanOrEqualImpl condition,
				CompileConditionParam param) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Void onStartsWith(ConditionStringStartsWith condition,
				CompileConditionParam param) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Void onEndsWith(ConditionStringEndsWith condition,
				CompileConditionParam param) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Void onContains(ConditionStringContains condition, CompileConditionParam param) {
			
			param.sb.append("LIKE '%");
			
			return null;
		}

		@Override
		public Void onMatches(ConditionStringMatches condition, CompileConditionParam param) {
			throw new IllegalArgumentException("matches not supported");
		}
	}
	
	private void appendStringValue(ConditionStringImpl condition, CompileConditionParam param) {
		
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

	private String getColumnNameForGetter(CompiledSelectSource source, CompiledGetter getter) {

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

