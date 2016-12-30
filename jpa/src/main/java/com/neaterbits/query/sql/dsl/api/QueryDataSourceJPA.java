package com.neaterbits.query.sql.dsl.api;

import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.ManagedType;
import javax.persistence.metamodel.Metamodel;

import com.neaterbits.query.sql.dsl.api.PreparedQueryBuilder.FieldReference;
import com.neaterbits.query.sql.dsl.api.PreparedQueryBuilder.SourceReference;
import com.neaterbits.query.sql.dsl.api.entity.Relation;

/**
 * Query data source implementation for JPA
 * @author nhl
 *
 */

public final class QueryDataSourceJPA extends QueryDataSourceGenBase {
	
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
	
	
	@Override
	PreparedQueryBuilder createBuilder() {
		return new JPAPreparedQueryBuilder();
	}


	@Override
	<QUERY> DSPreparedQuery prepare(PreparedQueryBuilder sb, ExecutableQuery<QUERY> q, QUERY query) {
		
		sb.select();;
		
		queryResultConverter.convert(q, query, sb);

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

			final PreparedQueryConditionsBuilderJPA conditionsBuilderJPA = new PreparedQueryConditionsBuilderJPA();
			
			prepareConditions(q, query, conditionsBuilderJPA, addJoinToWhere);

			if (param.hasUnresolved()) {
				ret = new JPAHalfwayPreparedQuery<QUERY>(q, query, paramNameAssigner, sb.toString(), conditionsBuilderJPA, em);
			}
			else {

				sb.addAllConditions(conditionsBuilderJPA, null);

				ret = makeComplete(q, query, paramNameAssigner, sb);
			}
		}
		else {
			ret = makeComplete(q, query, null, sb);
		}

		return ret;
	}
	
	private <QUERY> JPACompletePreparedQuery<QUERY> makeComplete(ExecutableQuery<QUERY> q, QUERY query, ParamNameAssigner paramNameAssigner, PreparedQueryBuilder sb) {
		final String jpql = sb.toString();
		
		System.out.println("## jpql:\n" + jpql);
		
		final javax.persistence.Query jpaQuery = em.createQuery(jpql);

		return new JPACompletePreparedQuery<QUERY>(q, query, paramNameAssigner, jpaQuery);
	}

	private <QUERY> void prepareSelectSources(PreparedQueryBuilder sb, ExecutableQuery<QUERY> q, QUERY query) {

		final int numSources = q.getSourceCount(query);
		
		final List<SourceReference> refs = new ArrayList<>(numSources);
		
		for (int i = 0; i < numSources; ++ i) {
			
			final SourceReference ref = new SourceReference(q.getSourceJavaType(query, i), q.getSourceName(query, i));
			
			refs.add(ref);
			
		}

		sb.addSelectSources(refs);
	}
	
	static FieldReference prepareFieldReference(CompiledFieldReference field, EntityManager em) {

		final TypeMapSource source = field.getSource();

		final CompiledGetter getter = field.getGetter();

		final String columnName = getColumnNameForGetter(source, getter, em);

		return new FieldReference(source.getName(), columnName);
	}

	private <QUERY> void prepareJoins(PreparedQueryBuilder sb, ExecutableQuery<QUERY> q, QUERY query, List<JoinConditionId> addJoinToWhere) {
		
		int joinParamIdx = 0;
		
		final int numJoins = q.getJoinCount(query);
		
		for (int join = 0; join < numJoins; ++ join) {
			joinParamIdx = addRelationFromMetaModel(sb, join, q, query, joinParamIdx, addJoinToWhere);
		}
	}
	
	private <QUERY> int addRelationFromMetaModel(
			PreparedQueryBuilder sb,
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
			sb.appendJoinStatement(q.getJoinType(query, joinIdx));
			
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
					
					sb.appendJoinStatement(joinType);

					
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

					final String joinVarName = "join" + joinParamIdx++;
					
					sb.addOneToManyJoin(entityAliasName, collectionAttrName, joinVarName);

					// TODO: Join comparison on rhs?
					break;

				default:
					throw new UnsupportedOperationException("Unknown join condition type " + conditionType);
				}
			}
		}
		
		return joinParamIdx;
	}
	
	private <QUERY> ConditionsType prepareConditions(ExecutableQuery<QUERY> q, QUERY query, PreparedQueryConditionsBuilder conditionsBuilder, List<JoinConditionId> joinComparisonConditions) {

		final ConditionsType original = q.getRootConditionsType(query);
		
		ConditionsType os = ConditionsType.SINGLE;
		
		final boolean nestConditions =
				
				       ConditionsType.OR.equals(original)
					&& q.hasConditions(query)
					&& !joinComparisonConditions.isEmpty();
		
		
		// Must add comparisons from joins
		if (!joinComparisonConditions.isEmpty()) {
			
			for (JoinConditionId comparison : joinComparisonConditions) {
				
				if (os == null) {
					throw new IllegalStateException("os == null");
				}

				final CompiledFieldReference lhs = q.getJoinConditionComparisonLhs(query, comparison.joinIdx, comparison.conditionIdx);
				final CompiledFieldReference rhs = q.getJoinConditionComparisonRhs(query, comparison.joinIdx, comparison.conditionIdx);
				
				// Output join condition as regular join
				final FieldReference left = prepareFieldReference(lhs, em);

				final FieldReference right = prepareFieldReference(rhs, em);

				conditionsBuilder.addCondition(os, left, EClauseOperator.IS_EQUAL, right);

				os = ConditionsType.AND;
			}
		}

		if (!q.isRootConditionOnly(query)) {
			throw new UnsupportedOperationException("TODO: supports root conditions only for now");
		}
		
		final List<PreparedQueryCondition> rootConditions = prepareRootConditions(q, query, original);
		
		if (!rootConditions.isEmpty()) {
			if (nestConditions) {
				conditionsBuilder.addNested(os, rootConditions);
			}
			else {
				conditionsBuilder.addConditions(os, rootConditions);
			}
		}

		return os;
	}
	
	private <QUERY> List<PreparedQueryCondition> prepareRootConditions(ExecutableQuery<QUERY> q, QUERY query, ConditionsType os) {

		final int numConditions = q.getRootConditionCount(query);
		final StringBuilder conditionSB = new StringBuilder();
		
		final List<PreparedQueryCondition> ret = new ArrayList<>(numConditions);
		
		for (int conditionIdx = 0; conditionIdx < numConditions; ++ conditionIdx) {

			final CompiledFieldReference lhs = q.getRootConditionLhs(query, conditionIdx);
			final ConditionValueImpl value = q.getRootConditionValue(query, conditionIdx);
			
			final FieldReference left = prepareFieldReference(lhs, em);
			

			// Operator and value
			final PreparedQueryConditionRHS preparedCondition = conditionToOperator.convert(q, query, conditionIdx, value, conditionSB);

			ret.add(new PreparedQueryCondition(left, preparedCondition));
		}

		return ret;
	}

	private static final JPAConditionToOperator conditionToOperator = new JPAConditionToOperator();

	


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

	private static class JoinConditionId {
		private final int joinIdx;
		private final int conditionIdx;
		
		JoinConditionId(int joinIdx, int conditionIdx) {
			this.joinIdx = joinIdx;
			this.conditionIdx = conditionIdx;
		}
	}
}

