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

public final class QueryDataSourceJPA extends QueryDataSourceBase {

	private final EntityManager em;
	private final JPACompiledQueryResultVisitor queryResultVisitor;

	public QueryDataSourceJPA(EntityManager entityManager) {

		if (entityManager == null) {
			throw new IllegalArgumentException("entityManager == null");
		}

		this.em = entityManager;
		this.queryResultVisitor = new JPACompiledQueryResultVisitor(em);
	}
	
	private JPABasePreparedQuery prepareQuery(CompiledQuery query) {

		final StringBuilder sb = new StringBuilder();

		return prepare(sb, query);
	}
	

	private String entityName(Class<?> entityType) {
		return entityType.getSimpleName();
	}

	
	private JPABasePreparedQuery prepare(StringBuilder sb, CompiledQuery query) {
		
		sb.append("SELECT ");
		
		query.getResult().visit(queryResultVisitor, sb);

		sb.append("\n").append("FROM ");
		
		// Add all select sources
		prepareSelectSources(sb, (CompiledSelectSources<CompiledSelectSource>)query.getSelectSources());

		if (query.getJoins() != null) {
			prepareJoins(sb, query.getJoins());
		}
		
		// Prepare conditions if present
		final JPABasePreparedQuery ret;
		
		if (query.getConditions() != null) {
			
			final ParamNameAssigner paramNameAssigner = new ParamNameAssigner();
			final CompileConditionParam param = new CompileConditionParam(paramNameAssigner, em);
			
			final JPAOp op = prepareConditions(query.getConditions(), param);
			
			if (param.hasUnresolved()) {
				ret = new JPAHalfwayPreparedQuery(query, paramNameAssigner, sb.toString(), op, param.getConditions(), em);
			}
			else {
				param.addAllConditions(sb, null);

				ret = makeComplete(query, paramNameAssigner, sb);
			}
		}
		else {
			ret = makeComplete(query, null, sb);
		}
		
		return ret;
	}
	
	private JPACompletePreparedQuery makeComplete(CompiledQuery query, ParamNameAssigner paramNameAssigner, StringBuilder sb) {
		final String jpql = sb.toString();
		
		System.out.println("## jpql:\n" + jpql);
		
		final javax.persistence.Query jpaQuery = em.createQuery(jpql);

		return new JPACompletePreparedQuery(query, paramNameAssigner, jpaQuery);
	}

	private void prepareSelectSources(StringBuilder sb, CompiledSelectSources<CompiledSelectSource> sources) {

		JPAUtil.commaSeparated(sb, sources.getSources(), (CompiledSelectSource source) -> {

			final String entityName = entityName(source.getType());

			sb.append(entityName).append(' ').append(source.getName());
		});
	}
	
	static void prepareFieldReference(Consumer<String> c, CompiledFieldReference field, EntityManager em) {

		final TypeMapSource source = field.getSource();

		final CompiledGetter getter = field.getGetter();

		final String columnName = getColumnNameForGetter(source, getter, em);

		c.accept(source.getName());
		c.accept(".");
		c.accept(columnName);
	}
	
	private void prepareJoins(StringBuilder sb, CompiledJoins joins) {
		for (CompiledJoin join : joins.getJoins()) {
			switch (join.getJoinType()) {
			
			
			case LEFT:
				sb.append(" LEFT JOIN ");
				break;
				
			case INNER:
				sb.append(" INNER JOIN ");
				break;
				
			default:
				throw new UnsupportedOperationException("Unknown join condition ");
			}

			for (CompiledJoinCondition joinCondition : join.getConditions()) {
				
			}
			
		}
	}
	
	
	private static final JPAConditionToOperatorVisitor conditionToOperatorVisitor = new JPAConditionToOperatorVisitor();
	
	private JPAOp prepareConditions(CompiledConditions conditions, CompileConditionParam param) {

		boolean first = true;
		
		final String opString;
		final JPAOp op;
		
		if (conditions instanceof CompiledConditionsAnd) {
			opString = "AND";
			op = JPAOp.AND;
		}
		else if (conditions instanceof CompiledConditionsOr) {
			opString = "OR";
			op = JPAOp.OR;
		}
		else if (conditions instanceof CompiledConditionsSingle) {
			opString = null;
			op = null;
		}
		else {
			throw new IllegalStateException("unknown conditions " + conditions.getClass());
		}

		for (CompiledCondition condition : conditions.getConditions()) {

			final String os;

			if (first) {
				os = "WHERE";
				first = false;
			}
			else {
				os = opString;
			}
			
			param.append(" ").append(os).append(" ");

			prepareFieldReference(param::append, condition.getLhs(), em);
			
			param.append(" ");

			param.setValue(condition.getValue());
			
			// Operator and value
			condition.getOriginal().visit(conditionToOperatorVisitor, param);
		}

		return op;
	}
	
	


	private static String getColumnNameForGetter(TypeMapSource source, CompiledGetter getter, EntityManager em) {

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

