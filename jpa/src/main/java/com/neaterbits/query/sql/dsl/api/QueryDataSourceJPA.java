package com.neaterbits.query.sql.dsl.api;

import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.ManagedType;
import javax.persistence.metamodel.Metamodel;

import com.neaterbits.query.sql.dsl.api.entity.Relation;

/**
 * Query data source implementation for JPA
 * @author nhl
 *
 */

public final class QueryDataSourceJPA extends QueryDataSourceBase {
	

	private final EntityManager em;
	private final JPAQueryResultConverter queryResultConverter;
	private final JPAEntityModel entityModel;
	private final JPAEntityModelUtil entityModelUtil;

	public QueryDataSourceJPA(EntityManager entityManager) {

		if (entityManager == null) {
			throw new IllegalArgumentException("entityManager == null");
		}

		this.em = entityManager;
		this.queryResultConverter = new JPAQueryResultConverter(em);
		this.entityModel = new JPAEntityModel(em.getMetamodel());
		this.entityModelUtil = new JPAEntityModelUtil(entityModel);
	}
	
	private <QUERY> JPABasePreparedQuery<QUERY> prepareQuery(ExecutableQuery<QUERY> q, QUERY query) {

		final StringBuilder sb = new StringBuilder();

		return prepare(sb, q, query);
	}
	

	private String entityName(Class<?> entityType) {
		return entityType.getSimpleName();
	}

	
	private <QUERY> JPABasePreparedQuery<QUERY> prepare(StringBuilder sb, ExecutableQuery<QUERY> q, QUERY query) {
		
		sb.append("SELECT ");
		
		queryResultConverter.convert(q, query, sb);

		sb.append("\n").append("FROM ");
		
		// Add all select sources
		prepareSelectSources(sb, q, query);

		List<JoinConditionId> addJoinToWhere = new ArrayList<>();
		
		if (q.hasJoins(query)) {
			prepareJoins(sb, q, query, addJoinToWhere);
		}
		
		// Prepare conditions if present
		final JPABasePreparedQuery<QUERY> ret;
		
		if (q.hasConditions(query)) {
			
			final ParamNameAssigner paramNameAssigner = new ParamNameAssigner();
			final CompileConditionParam param = new CompileConditionParam(paramNameAssigner, em);

			final JPAOp op = prepareConditions(q, query, param, addJoinToWhere);

			if (param.hasUnresolved()) {
				ret = new JPAHalfwayPreparedQuery<QUERY>(q, query, paramNameAssigner, sb.toString(), op, param.getConditions(), em);
			}
			else {
				param.addAllConditions(sb, null);

				ret = makeComplete(q, query, paramNameAssigner, sb);
			}
		}
		else {
			ret = makeComplete(q, query, null, sb);
		}

		return ret;
	}
	
	private <QUERY> JPACompletePreparedQuery<QUERY> makeComplete(ExecutableQuery<QUERY> q, QUERY query, ParamNameAssigner paramNameAssigner, StringBuilder sb) {
		final String jpql = sb.toString();
		
		System.out.println("## jpql:\n" + jpql);
		
		final javax.persistence.Query jpaQuery = em.createQuery(jpql);

		return new JPACompletePreparedQuery<QUERY>(q, query, paramNameAssigner, jpaQuery);
	}

	private <QUERY> void prepareSelectSources(StringBuilder sb, ExecutableQuery<QUERY> q, QUERY query) {

		final int numSources = q.getSourceCount(query);
		
		for (int i = 0; i < numSources; ++ i) {
			if (i > 0) {
				sb.append(", ");
			}

			
			final String entityName = entityName(q.getSourceJavaType(query, i));

			sb.append(entityName).append(' ').append(q.getSourceName(query, i));
		}
		
		/*
		JPAUtil.commaSeparated(sb, sources.getSources(), (CompiledSelectSource source) -> {

			final String entityName = entityName(source.getType());

			sb.append(entityName).append(' ').append(source.getName());
		});
		*/
	}
	
	static void prepareFieldReference(Consumer<String> c, CompiledFieldReference field, EntityManager em) {

		final TypeMapSource source = field.getSource();

		final CompiledGetter getter = field.getGetter();

		final String columnName = getColumnNameForGetter(source, getter, em);

		c.accept(source.getName());
		c.accept(".");
		c.accept(columnName);
	}
	
	private <QUERY> void prepareJoins(StringBuilder sb, ExecutableQuery<QUERY> q, QUERY query, List<JoinConditionId> addJoinToWhere) {
		
		int joinParamIdx = 0;
		
		final int numJoins = q.getJoinCount(query);
		
		for (int join = 0; join < numJoins; ++ join) {
			joinParamIdx = addRelationFromMetaModel(sb, join, q, query, joinParamIdx, addJoinToWhere);
		}
	}
	
	private void appendJoinStatement(StringBuilder sb, EJoinType joinType) {
		switch (joinType) {
		
		case LEFT:
			sb.append(" LEFT JOIN ");
			break;
			
		case INNER:
			sb.append(" INNER JOIN ");
			break;
			
		default:
			throw new UnsupportedOperationException("Unknown join type " + joinType);
		}
	}

	private <QUERY> int addRelationFromMetaModel(
			StringBuilder sb,
			int joinIdx,
			ExecutableQuery<QUERY> q,
			QUERY query,
			int joinParamIdx,
			List<JoinConditionId> addJoinToWhere) {

		// First must find the types for this join
		final Class<?> leftType  = q.getJoinLeftJavaType(query, joinIdx);
		final Class<?> rightType = q.getJoinRightJavaType(query, joinIdx);

		final ManagedType<?> leftEntityType  =  entityModel.getManaged(leftType);
		final ManagedType<?> rightEntityType =  entityModel.getManaged(rightType);

		if (leftEntityType.equals(rightEntityType)) {
			throw new IllegalStateException("Left entity equals right entity: "  + leftEntityType + " / " + rightEntityType);
		}

		// Look at conditions to see if there is a join on any of the conditions
		if (q.joinHasConditions(query, joinIdx)) {
			appendJoinStatement(sb, q.getJoinType(query, joinIdx));
			
			// Must find exactly one relation from this one to other
			final List<Relation> leftToRight =  entityModelUtil.findRelations(leftType, rightType);
			final List<Relation> rightToLeft =  entityModelUtil.findRelations(leftType, rightType);
			
			if (leftToRight.size() != 1) {
				throw new IllegalStateException("Expected exactly one relation from " + leftType + " to " + rightType);
			}
			
			// final Relation leftRelation = leftToRight.get(0);
			
			if (rightToLeft.size() != 1) {
				throw new IllegalStateException("Expected exactly one relation from " + rightType + " to " + leftType);
			}
			
			// Got one relation
			//final Relation rightRelation = rightToLeft.get(0);
		}
		else {
			// Must find metamodel entry that matches the foreign key patterns specified
			
			final int numJoinConditions = q.getJoinConditionCount(query, joinIdx);
			
			final EJoinType joinType = q.getJoinType(query, joinIdx);
			
			for (int conditionIdx = 0; conditionIdx < numJoinConditions; ++ conditionIdx) {
				// Find something that matches this condition in correct order
				
				final EJoinConditionType conditionType = q.getJoinConditionType(query, joinIdx, conditionIdx);
				switch (conditionType) {

				case COMPARISON:
					
					if (joinType != EJoinType.INNER) {
						throw new IllegalStateException("Can only do inner joins when performing field comparison");
					}

					final JoinConditionId comparison = new JoinConditionId(joinIdx, conditionIdx);

					// Joining two fields. TODO Which version of JPA are we?

					// Add as where-queries for later on
					
					addJoinToWhere.add(comparison);
					break;

				case ONE_TO_MANY:
					
					appendJoinStatement(sb, joinType);

					
					final Class<?> oneToManyLeftType  = q.getJoinConditionLeftJavaType(query, joinIdx, conditionIdx);
					final Class<?> oneToManyRightType = q.getJoinConditionRightJavaType(query, joinIdx, conditionIdx);
					
					final Method collectionGetterMethod = q.getJoinConditionOneToManyCollectionGetter(query, joinIdx, conditionIdx);
					
					
					// Must figure out which relation attribute this is ?
					final Relation relation = entityModelUtil.findOneToManyRelation(oneToManyLeftType, oneToManyRightType, collectionGetterMethod);
					
					if (relation == null) {
						throw new IllegalStateException("Failed to find relation for " + collectionGetterMethod);
					}
					
					final String entityAliasName = q.getJoinConditionLeftName(query, joinIdx, conditionIdx);
					final String collectionAttrName = relation.getFrom().getAttribute().getName();
					
					// Output JPA join on collection
					// from name in from-clause
					sb.append(" ")
					  .append(entityAliasName).append(".").append(collectionAttrName)
					  .append(" ")
					  .append(" join").append(joinParamIdx ++);

					// TODO: Join comparison on rhs?
					break;

				default:
					throw new UnsupportedOperationException("Unknown join condition type " + conditionType);
				}
			}
		}
		
		return joinParamIdx;
	}
	
	// Must find whether there are multiple or just one mathing join between two tables.
	// should usually be just one
	
	private static final JPAConditionToOperator conditionToOperator = new JPAConditionToOperator();

	private <QUERY> JPAOp getJPAOp(ExecutableQuery<QUERY> q, QUERY query) {

		final JPAOp op;
		final ConditionsType type = q.getRootConditionsType(query);

		switch (type) {
		case AND:
			op = JPAOp.AND;
			break;

		case OR:
			op = JPAOp.OR;
			break;

		case SINGLE:
			op = null;
			break;

		default:
			throw new IllegalStateException("unknown conditions type " + type);
		}

		return op;
	}
	
	private <QUERY> JPAOp prepareConditions(ExecutableQuery<QUERY> q, QUERY query, CompileConditionParam param, List<JoinConditionId> joinComparisonConditions) {

		final JPAOp op = getJPAOp(q, query);
		
		String os = "WHERE";
		
		final boolean nestConditions =
				
				       JPAOp.OR.equals(op)
					&& q.hasConditions(query)
					&& !joinComparisonConditions.isEmpty();
		
		if (!joinComparisonConditions.isEmpty()) {
			
			for (JoinConditionId comparison : joinComparisonConditions) {
				
				if (os == null) {
					throw new IllegalStateException("os == null");
				}

				param.append(" ").append(os).append(" ");

				final CompiledFieldReference lhs = q.getJoinConditionComparisonLhs(query, comparison.joinIdx, comparison.conditionIdx);
				final CompiledFieldReference rhs = q.getJoinConditionComparisonRhs(query, comparison.joinIdx, comparison.conditionIdx);
				
				// Output join condition as regular join
				prepareFieldReference(param::append, lhs, em);

				param.append(" = ");

				prepareFieldReference(param::append, rhs, em);

				os = "AND";
			}

			if (nestConditions) {
				os = "AND (";
			}
		}

		if (!q.isRootConditionOnly(query)) {
			throw new UnsupportedOperationException("TODO: supports root conditions only for now");
		}
		
		final int numConditions = q.getRootConditionCount(query);
		
		
		for (int conditionIdx = 0; conditionIdx < numConditions; ++ conditionIdx) {

			if (os == null) {
				throw new IllegalStateException("os == null");
			}
			
			param.append(" ").append(os).append(" ");

			final CompiledFieldReference lhs = q.getRootConditionLhs(query, conditionIdx);
			final ConditionValueImpl value = q.getRootConditionValue(query, conditionIdx);
			
			prepareFieldReference(param::append, lhs, em);
			
			param.append(" ");

			param.setValue(value);
			
			// Operator and value
			conditionToOperator.convert(q, query, conditionIdx, param);
			
			os = op != null ? op.getOpString() : null;
		}

		if (nestConditions) {
			param.append(" )");
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
	<QUERY> DSPreparedQuery prepareSingleQuery(ExecutableQuery<QUERY> q, QUERY query) {

		if (query == null) {
			throw new IllegalArgumentException("query == null");
		}

		return prepareQuery(q, query);
	}

	@Override
	<QUERY> DSPreparedQuery prepareMultiQuery(ExecutableQuery<QUERY> q, QUERY query) {

		if (query == null) {
			throw new IllegalArgumentException("query == null");
		}

		return prepareQuery(q, query);
	}
	
	
	private static class JoinConditionId {
		private final int joinIdx;
		private final int conditionIdx;
		
		JoinConditionId(int joinIdx, int conditionIdx) {
			this.joinIdx = joinIdx;
			this.conditionIdx = conditionIdx;
		}
	}
}

