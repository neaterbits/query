package com.neaterbits.query.sql.dsl.api;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.neaterbits.query.sql.dsl.api.entity.EntityModel;
import com.neaterbits.query.sql.dsl.api.entity.EntityModelUtil;
import com.neaterbits.query.sql.dsl.api.entity.Relation;

abstract class QueryDataSourceORM<ORM_QUERY, MANAGED, EMBEDDED, IDENTIFIABLE, ATTRIBUTE, COLL extends Collection<ATTRIBUTE>> extends QueryDataSourceGenBase {

	private final EntityModel<MANAGED, EMBEDDED, IDENTIFIABLE, ATTRIBUTE, COLL> entityModel;
	private final EntityModelUtil<MANAGED, EMBEDDED, IDENTIFIABLE, ATTRIBUTE, COLL> entityModelUtil;
	
	abstract String getColumnNameForGetter(TypeMapSource source, CompiledGetter getter);
	
	abstract <QUERY> PreparedQueryComparisonRHS convertConditions(ExecutableQuery<QUERY> q, QUERY query, int conditionIdx, ConditionValueImpl value, StringBuilder sb);
	
	abstract PreparedQueryConditionsBuilder createConditionsBuilder(PreparedQueryBuilderORM queryBuilderORM, boolean atRoot);
	
	abstract <QUERY> DSPreparedQueryDB<QUERY, ORM_QUERY> makeCompletePreparedQuery(ExecutableQuery<QUERY> q, QUERY query, ParamNameAssigner paramNameAssigner, PreparedQueryBuilder sb);
	
	abstract <QUERY> DSPreparedQueryDB<QUERY, ORM_QUERY> makeHalfwayPreparedQuery(ExecutableQuery<QUERY> queryAccess, QUERY query, ParamNameAssigner paramNameAssigner, String base, PreparedQueryConditionsBuilder conditions);
	
	
	QueryDataSourceORM(EntityModelUtil<MANAGED, EMBEDDED, IDENTIFIABLE, ATTRIBUTE, COLL> modelUtil) {

		if (modelUtil == null) {
			throw new IllegalArgumentException("modelUtil == null");
		}
		
		this.entityModelUtil = modelUtil;
		this.entityModel = modelUtil.getModel();
	}
	
	final EntityModelUtil<MANAGED, EMBEDDED, IDENTIFIABLE, ATTRIBUTE, COLL> getEntityModelUtil() {
		return entityModelUtil;
	}

	@Override
	final <QUERY> DSPreparedQuery<QueryDataSourceDB> prepare(PreparedQueryBuilder sb, ExecutableQuery<QUERY> q, QUERY query) {
		
		sb.select();
		
		convertResult(q, query, sb);

		// Add all select sources
		prepareSelectSources(sb, q, query);

		List<JoinConditionId> addJoinToWhere = new ArrayList<>();
		
		if (q.hasJoins(query)) {
			prepareJoins(sb, q, query, addJoinToWhere);
		}
		
		// Prepare conditions if present
		final DSPreparedQueryDB<QUERY, ORM_QUERY> ret;
		
		if (q.hasConditions(query)) {
			
			final ParamNameAssigner paramNameAssigner = new ParamNameAssigner();
			//final CompileConditionParam param = new CompileConditionParam(paramNameAssigner, em);

			final PreparedQueryConditionsBuilder conditionsBuilder = createConditionsBuilder((PreparedQueryBuilderORM)sb, true);

			prepareConditions(q, query, conditionsBuilder, addJoinToWhere);

			if (conditionsBuilder.hasUnresolved()) {
				ret = makeHalfwayPreparedQuery(q, query, paramNameAssigner, sb.toString(), conditionsBuilder);
			}
			else {
				sb.resolveFromParams(conditionsBuilder, null);

				ret = makeCompletePreparedQuery(q, query, paramNameAssigner, sb);
			}
		}
		else {
			ret = makeCompletePreparedQuery(q, query, null, sb);
		}

		return ret;
	}
	
	private <QUERY> SourceReference getSourceReference(ExecutableQuery<QUERY> q, QUERY query, int idx) {
		final SourceReference ref = new SourceReference(q.getSourceJavaType(query, idx), q.getSourceName(query, idx));

		return ref;
	}
	
	
	private <QUERY> void prepareSelectSources(PreparedQueryBuilder sb, ExecutableQuery<QUERY> q, QUERY query) {

		final FieldReferenceType fieldReferenceType = q.getQueryFieldRefereneType(query);

		final int numSources = q.getSourceCount(query);
		
		final List<SourceReference> refs = new ArrayList<>(numSources);
		
		for (int i = 0; i < numSources; ++ i) {
			
			final SourceReference ref = getSourceReference(q, query, i);
			
			refs.add(ref);
		}

		sb.addSelectSources(fieldReferenceType, refs);
	}
	
	private <QUERY> FieldReference prepareFieldReference(ExecutableQuery<QUERY> q, QUERY query, CompiledFieldReference field) {

		final TypeMapSource source = field.getSource();

		final CompiledGetter getter = field.getGetter();

		final String columnName = getColumnNameForGetter(source, getter);

		final FieldReferenceType fieldReferenceType = q.getQueryFieldRefereneType(query);
		
		final FieldReference ret;
		
		switch (fieldReferenceType) {
		case ALIAS:
			ret = new FieldReferenceAlias(source.getName(), columnName);
			break;
			
		case ENTITY:
			ret = new FieldReferenceEntity(source.getType(), columnName);
			break;
			
		default:
			throw new IllegalStateException("field reference type: " + fieldReferenceType);
		}

		return ret;
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

		final MANAGED leftEntityType  =  entityModel.getManaged(leftType);
		final MANAGED rightEntityType =  entityModel.getManaged(rightType);

		if (leftEntityType.equals(rightEntityType)) {
			throw new IllegalStateException("Left entity equals right entity: "  + leftEntityType + " / " + rightEntityType);
		}

		final EJoinType joinType = q.getJoinType(query, joinIdx);

		// Look at conditions to see if there is a join on any of the conditions
		if (!q.joinHasConditions(query, joinIdx)) {

			// No conditions in join so must look for the one defined join between the types
			
			sb.appendJoinStatement(joinType);
			
			// Must find exactly one relation from this one to other
			final List<Relation> leftToRight =  entityModelUtil.findRelations(leftType, rightType);
			final List<Relation> rightToLeft =  entityModelUtil.findRelations(leftType, rightType);
			
			if (leftToRight.size() != 1) {
				throw new IllegalStateException("Expected exactly one relation from " + leftType + " to " + rightType + ": " + leftToRight.size());
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
						throw new IllegalStateException("Failed to find relation for " + collectionGetterMethod + " from " + oneToManyLeftType + " to " + oneToManyRightType);
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

		// Default to SINGLE
		ConditionsType os = ConditionsType.SINGLE;
		
		final boolean nestConditions =
				
				       ConditionsType.OR.equals(original)
					&& q.hasConditions(query)
					&& !joinComparisonConditions.isEmpty();
		
		
		// Must add comparisons from joins
		if (!joinComparisonConditions.isEmpty()) {

			// Comparison joins are always ANDed
			os = ConditionsType.AND;

			for (JoinConditionId comparison : joinComparisonConditions) {
				

				if (os == null) {
					throw new IllegalStateException("os == null");
				}

				final CompiledFieldReference lhs = q.getJoinConditionComparisonLhs(query, comparison.joinIdx, comparison.conditionIdx);
				final CompiledFieldReference rhs = q.getJoinConditionComparisonRhs(query, comparison.joinIdx, comparison.conditionIdx);
				
				// Output join condition as regular join
				final FieldReference left  = prepareFieldReference(q, query, lhs);
				final FieldReference right = prepareFieldReference(q, query, rhs);

				conditionsBuilder.addJoinCondition(os, left, EClauseOperator.IS_EQUAL, right);
			}
		}
		
		if (!q.isRootConditionOnly(query)) {
			throw new UnsupportedOperationException("TODO: supports root conditions only for now");
		}

		final PreparedQueryConditionsBuilder sub = nestConditions
				? conditionsBuilder.addNested(os)
				: conditionsBuilder;

		prepareRootConditions(q, query, original, sub);

		return os;
	}
	
	private <QUERY> List<PreparedQueryConditionComparison> prepareRootConditions(ExecutableQuery<QUERY> q, QUERY query, ConditionsType original, PreparedQueryConditionsBuilder sub) {

		final int numConditions = q.getRootConditionCount(query);
		final StringBuilder conditionSB = new StringBuilder();
		
		final List<PreparedQueryConditionComparison> ret = new ArrayList<>(numConditions);
		
		for (int conditionIdx = 0; conditionIdx < numConditions; ++ conditionIdx) {

			final CompiledFieldReference lhs = q.getRootConditionLhs(query, conditionIdx);
			final ConditionValueImpl value = q.getRootConditionValue(query, conditionIdx);
			
			final FieldReference left = prepareFieldReference(q, query, lhs);
			

			// Operator and value
			final PreparedQueryComparisonRHS preparedCondition = convertConditions(q, query, conditionIdx, value, conditionSB);

			final PreparedQueryConditionComparison prepared = new PreparedQueryConditionComparison(left, preparedCondition);

			sub.addComparisonCondition(original, prepared);
			
			// Clear for next iteration
			conditionSB.setLength(0);
		}

		return ret;
	}

	private <QUERY> void convertResult(ExecutableQuery<QUERY> q, QUERY query, PreparedQueryBuilder sb) {
		
		final EQueryResultGathering resultGathering = q.getGathering(query);
		
		switch (resultGathering) {
		case ENTITY:
			// Result may be an alias
			final FieldReferenceType fieldReferenceType = q.getQueryFieldRefereneType(query);
			
			// Find the resulting source
			
			final int entityResultSourceIdx = q.getEntityResultSourceIdx(query);
			final SourceReference sourceReference = getSourceReference(q, query, entityResultSourceIdx);

			sb.addEntityResult(fieldReferenceType, sourceReference);
			break;

		case MAPPED:
			prepareMappings(sb, q, query);
			break;

		case AGGREGATE:
			final EAggregateFunction function = q.getAggregateResultFunction(query);
			final CompiledFieldReference resultField = q.getAggregateResultField(query);
			final FieldReference ref = prepareFieldReference(q, query, resultField);

			sb.addAggregateResult(function, ref);
			break;
			
		default:
			throw new UnsupportedOperationException("Unknown result gathering " + resultGathering);
		}
	}
	
	private <QUERY> void prepareMappings(PreparedQueryBuilder sb, ExecutableQuery<QUERY> q, QUERY query) {
		
		final int numMappings = q.getMappingCount(query);
		
		final List<FieldReference> refs = new ArrayList<>(numMappings);
		
		for (int i = 0; i < numMappings; ++ i) {
			final CompiledFieldReference field = q.getMappingField(query, i);

			final FieldReference ref = prepareFieldReference(q, query, field);
			
			refs.add(ref);
		}
		
		sb.addMappings(refs);
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

