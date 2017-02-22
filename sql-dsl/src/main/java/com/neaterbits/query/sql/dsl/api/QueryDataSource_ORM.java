package com.neaterbits.query.sql.dsl.api;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;

import com.neaterbits.query.sql.dsl.api.entity.EntityModel;
import com.neaterbits.query.sql.dsl.api.entity.EntityModelUtil;
import com.neaterbits.query.sql.dsl.api.entity.Relation;

abstract class QueryDataSource_ORM<ORM_QUERY, MANAGED, EMBEDDED, IDENTIFIABLE, ATTRIBUTE, COLL extends Collection<ATTRIBUTE>> extends QueryDataSource_GenBase {

	private final EntityModel<MANAGED, EMBEDDED, IDENTIFIABLE, ATTRIBUTE, COLL> entityModel;
	private final EntityModelUtil<MANAGED, EMBEDDED, IDENTIFIABLE, ATTRIBUTE, COLL> entityModelUtil;
	
	abstract String getColumnNameForGetter(TypeMapSource source, CompiledGetter getter);
	
	abstract <QUERY> PreparedQueryComparisonRHS convertCondition(EClauseOperator operator, ConditionValue value, ConditionStringBuilder sb);
	
	abstract PreparedQueryConditionsBuilder createConditionsBuilder(PreparedQueryBuilderORM queryBuilderORM, EConditionsClause conditionsClause, boolean atRoot);
	
	abstract <QUERY> PreparedQuery_DB<QUERY, ORM_QUERY> makeCompletePreparedQuery(ExecutableQuery<QUERY> q, QUERY query, QueryParametersDistinct distinctParams, PreparedQueryBuilder sb);
	
	abstract <QUERY> PreparedQuery_DB<QUERY, ORM_QUERY> makeHalfwayPreparedQuery(ExecutableQuery<QUERY> queryAccess, QUERY query, QueryParametersDistinct distinctParams, PreparedQueryBuilder base, PreparedQueryConditionsBuilder conditions);

	abstract ConditionStringBuilder makeConditionStringBuilder(QueryParametersDistinct distinctParams);
	
	
	/**
	 * Whether supports join fields natively, ie. ON table1.somefield = table2.somefiled.
	 * This is not true for JPQL and we have to simulate this by implicit join in WHERE and then add conditions to the WHERE clause 
	 *  
	 * @return
	 */
	abstract boolean supportsNonRelationJoins();
	
	QueryDataSource_ORM(EntityModelUtil<MANAGED, EMBEDDED, IDENTIFIABLE, ATTRIBUTE, COLL> modelUtil) {

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
	final <QUERY> PreparedQuery_DS<QueryDataSource_DB> prepare(PreparedQueryBuilder sb, ExecutableQuery<QUERY> q, QUERY query) {
		
		sb.select();
		
		convertResult(q, query, sb);

		// Figure out the from select source
		final int [] fromSelectSources = getFromSelectSources(q, query);

		validateFromIndices(fromSelectSources, q, query);
		
		// Add all froms
		prepareFroms(sb, q, query, fromSelectSources);

		List<JoinConditionId> addJoinToWhere = new ArrayList<>();
		
		if (q.hasJoins(query)) {
			prepareJoins(sb, q, query, addJoinToWhere);
		}
		
		// Prepare conditions if present
		final PreparedQuery_DB<QUERY, ORM_QUERY> ret;
		
		if (q.hasConditions(query)) {
			
		    final QueryParametersDistinct distinctParams = q.getDistinctParams(query);
			
			//final CompileConditionParam param = new CompileConditionParam(paramNameAssigner, em);

			final PreparedQueryConditionsBuilder conditionsBuilder = createConditionsBuilder((PreparedQueryBuilderORM)sb, EConditionsClause.WHERE, true);

			prepareConditions(q, query, conditionsBuilder, addJoinToWhere, distinctParams);

			if (conditionsBuilder.hasUnresolved()) {
				ret = makeHalfwayPreparedQuery(q, query, distinctParams, sb, conditionsBuilder);
			}
			else {
				sb.resolveFromParams(conditionsBuilder, null);

				ret = makeCompleteQueryWithResultProcessing(q, query, distinctParams, sb);
			}
		}
		else {
			ret = makeCompleteQueryWithResultProcessing(q, query, null, sb);
		}

		return ret;
	}

	private static <QUERY> int [] getArray(QUERY query, int num, BiFunction<QUERY, Integer, Integer> getIndex) {
		
		final int [] ret = new int[num];

		for (int i = 0; i < num; ++ i) {
			ret[i] = getIndex.apply(query, i) + 1; // add one since API returns index start at 0
		}

		return ret;
	}

	private <QUERY> List<FieldReference> getFieldReferences(ExecutableQuery<QUERY> q, QUERY query, int num, BiFunction<QUERY, Integer, Integer> getIndex) {
		
		final List<FieldReference> ret = new ArrayList<>(num);
		
		for (int i = 0; i < num; ++ i) {
			final int idx = getIndex.apply(query, i);
			
			final CompiledFieldReference compiledFieldRef = q.getMappingField(query, idx);
			
			ret.add(prepareFieldReference(q, query, compiledFieldRef));
		}

		return ret;
	}
	
	final <QUERY> void addResultProcessing(ExecutableQuery<QUERY> q, QUERY query, PreparedQueryBuilder sb, QueryParametersDistinct distinctParams) {
		// Check whether we should do result-processing and add that
		
		final int numGroupBy = q.getGroupByFieldCount(query);
		
		if (numGroupBy > 0) {
			sb.appendGroupBy(
					getFieldReferences(q, query, numGroupBy, q::getGroupByFieldIndex)
					//getArray(query, numGroupBy, q::getGroupByFieldIndex)
					);
		}
		

		if (q.hasHaving(query)) {
			
			// Create conditions 
			final ExecutableQueryConditions<QUERY> qh = q.getExecutableQueryHaving();
			
	    	final int maxDepth = qh.getConditionsMaxDepth(query);
	    	
	    	final int [] conditionIndices = new int[maxDepth];

			final PreparedQueryConditionsBuilder conditionsBuilder = createConditionsBuilder((PreparedQueryBuilderORM)sb, EConditionsClause.HAVING, true);

			if (distinctParams == null) {
				throw new IllegalStateException("distinctParams == null");
			}

			final ConditionStringBuilder conditionSB = makeConditionStringBuilder(distinctParams);
			final ConditionsType conditionsType = qh.getConditionsType(query, 0, conditionIndices);
			
	    	prepareConditions(q, qh, query, conditionsType, conditionsBuilder, 0, conditionIndices, conditionSB);
		}
		

		final int numOrderBy = q.getOrderByFieldCount(query);
		
		if (numOrderBy > 0) {
			
			final List<FieldReference> fieldReferences = getFieldReferences(q, query, numOrderBy, q::getOrderByFieldIndex);
			final List<OrderByReference> orderByReferences = new ArrayList<>(numOrderBy);
			
			for (int i = 0; i < numOrderBy; ++ i) {
				
				final ESortOrder sortOrder = q.getOrderBySortOrder(query, i);
				
				orderByReferences.add(new OrderByReference(fieldReferences.get(i), sortOrder));
			}
			
			sb.appendOrderBy(orderByReferences);
		}
	}
	
	
	private <QUERY> PreparedQuery_DB<QUERY, ORM_QUERY> makeCompleteQueryWithResultProcessing(ExecutableQuery<QUERY> q, QUERY query, QueryParametersDistinct distinctParams, PreparedQueryBuilder sb) {

		addResultProcessing(q, query, sb, distinctParams);

		return makeCompletePreparedQuery(q, query, distinctParams, sb);
	}
	
	private <QUERY> int [] getFromSelectSources(ExecutableQuery<QUERY> q, QUERY query) {

		
		// For JPQL, we cannot remove from where any compare-inner joins
		// but when supports inner-joins we can remove thos as well.
		// for one-to-many joins, we always remove
		

		final int [] fromSelectSources;
		
		final int numSources = q.getAllSourceCount(query);
		
		
		if (q.hasJoins(query)) {
			
			// We might have to remove sources in the "from" set if they are duplicated within joins
			// since joins will take precedence over implicit outer-join semantics
			// NOTE: This is supported here to be generic in backen but may be restricted in API so that one cannot mix joins
			
			final int numJoins = q.getJoinCount(query);
			
			final LinkedHashSet<Integer> fromSources = new LinkedHashSet<>(numSources);

			// Default, add all sources
			for (int sourceIdx = 0; sourceIdx < numSources; ++ sourceIdx) {
				fromSources.add(sourceIdx);
			}

			
			// Loop over all joins to see if we should remove any of them from the FROM clause
			// ie. when already part of an ANSI style join
			for (int joinIdx = 0; joinIdx < numJoins; ++ joinIdx) {
				final EJoinConditionType joinConditionType = q.getJoinConditionType(query, joinIdx);
				
				final boolean removeFromFROMifPresent;
				
				switch (joinConditionType) {
				case COMPARISON:
					if (supportsNonRelationJoins()) {
						// We can remove this from FROM clause since we can do inner join
						removeFromFROMifPresent = true;
					}
					else {
						// Cannot remove since we need to emulate join through FROM and WHERE
						// TODO: Also emulate LEFT JOIN on comparison?
						removeFromFROMifPresent = false;
					}
					break;
					
				case ONE_TO_MANY:
					// Always remove from FROM if present
					removeFromFROMifPresent = true;
					break;
					
				default:
					throw new UnsupportedOperationException("Unknown join condition type " + joinConditionType);
				}
				
				if (removeFromFROMifPresent) {
					// Remove from FROM clause
					final int rightSourceIdx = q.getJoinRightSourceIdx(query, joinIdx);

					fromSources.remove(rightSourceIdx);
				}
			}
			

			fromSelectSources = new int [fromSources.size()];
			
			int dstIdx = 0;
			for (int fromSourceIdx : fromSources) {
				fromSelectSources[dstIdx ++] = fromSourceIdx;
			}
		}
		else {
			
			fromSelectSources = new int [numSources];
			
			int dstIdx = 0;
			
			for (int sourceIdx = 0; sourceIdx < numSources; ++ sourceIdx) {
				fromSelectSources[dstIdx ++] = sourceIdx;
			}
		}
		
		return fromSelectSources;
	}
	
	private static <QUERY> void validateFromIndices(int [] fromSelectSources, ExecutableQuery<QUERY> q, QUERY query) {
		
		if (fromSelectSources.length > q.getAllSourceCount(query)) {
			throw new IllegalArgumentException("Should never be more from sources than all sources");
		}

		final Set<Integer> unique = new HashSet<>(fromSelectSources.length);
		
		for (int fromSelectSource : fromSelectSources) {
			if (unique.contains(fromSelectSource)) {
				throw new IllegalStateException("From select source " + fromSelectSource + " contained twice or more");
			}
			
			unique.add(fromSelectSource);
			
			if (!hasSelectSource(q, query, fromSelectSource)) {
				throw new IllegalArgumentException("from select source " + fromSelectSource + " not among select sources");
			}
		}
	}
	
	private static <QUERY> boolean hasSelectSource(ExecutableQuery<QUERY> q, QUERY query, int sourceIdx) {

		return sourceIdx >= 0 && sourceIdx < q.getAllSourceCount(query);
		
	}
	
	
	private <QUERY> SourceReference getSourceReference(ExecutableQuery<QUERY> q, QUERY query, int idx) {
		final SourceReference ref = new SourceReference(q.getSourceJavaType(query, idx), q.getSourceName(query, idx));

		return ref;
	}
	
	
	private <QUERY> void prepareFroms(PreparedQueryBuilder sb, ExecutableQuery<QUERY> q, QUERY query, int [] fromSelectSources) {

		final FieldReferenceType fieldReferenceType = q.getQueryFieldReferenceType(query);

		final int numFroms = fromSelectSources.length;
		
		final List<SourceReference> refs = new ArrayList<>(numFroms);
		
		for (int fromIdx = 0; fromIdx < numFroms; ++ fromIdx) {

			final int sourceIdx = fromSelectSources[fromIdx];
			
			final SourceReference ref = getSourceReference(q, query, sourceIdx);
			
			refs.add(ref);
		}

		sb.addFromSelectSources(fieldReferenceType, refs);
	}
	
	private <QUERY> FieldReference prepareFieldReference(ExecutableQuery<QUERY> q, QUERY query, CompiledFieldReference field) {

		final TypeMapSource source = field.getSource();

		final CompiledGetter getter = field.getGetter();

		final String columnName = getColumnNameForGetter(source, getter);

		final FieldReferenceType fieldReferenceType = q.getQueryFieldReferenceType(query);
		
		final FieldReference ret;
		
		switch (fieldReferenceType) {
		case ALIAS:
			ret = new FieldReferenceAlias(source.getName(), columnName);
			break;
			
		case ENTITY:
			ret = new FieldReferenceEntity(source.getType(), source.getName(), columnName);
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
			final EJoinConditionType conditionType = q.getJoinConditionType(query, joinIdx);

			switch (conditionType) {

			case COMPARISON:
				
				if (joinType != EJoinType.INNER) {
					throw new IllegalStateException("Can only do inner joins when performing field comparison");
				}

				
				

				// Joining two fields. TODO Which version of JPA are we?

				// Add as where-queries for later on
				
				if (supportsNonRelationJoins()) {
					// Supports comparison joins
					
					final List<JoinFieldComparison> fieldComparisons = new ArrayList<>(numJoinConditions);
					
					
					for (int conditionIdx = 0; conditionIdx < numJoinConditions; ++ conditionIdx) {

						final CompiledFieldReference lhs = q.getJoinConditionComparisonLhs(query, joinIdx, conditionIdx);
						final CompiledFieldReference rhs = q.getJoinConditionComparisonRhs(query, joinIdx, conditionIdx);
						
						final JoinFieldComparison comparison = new JoinFieldComparison(
								prepareFieldReference(q, query, lhs),
								prepareFieldReference(q, query, rhs));
						
						fieldComparisons.add(comparison);
					}

					sb.appendJoinStatement(joinType);

					final int comparisonLeftSourceIdx  = q.getJoinLeftSourceIdx(query, joinIdx);
					final int comparisonRightSourceIdx = q.getJoinRightSourceIdx(query, joinIdx);

					// Add to list and add join
					sb.addComparisonJoin(
							fieldComparisons,
							getSourceReference(q, query, comparisonLeftSourceIdx),
							getSourceReference(q, query, comparisonRightSourceIdx)
							);
				}
				else {
					// Just add comparisons to return list
					for (int conditionIdx = 0; conditionIdx < numJoinConditions; ++ conditionIdx) {
						final JoinConditionId comparison = new JoinConditionId(joinIdx, conditionIdx);

						addJoinToWhere.add(comparison);
					}
				}
				break;

			case ONE_TO_MANY:
				if (numJoinConditions != 1) {
					throw new IllegalStateException("Should be exacly one condition");
				}
				addOneToManyJoin(sb, joinType, joinIdx, q, query);
				break;

			default:
				throw new UnsupportedOperationException("Unknown join condition type " + conditionType);
			}
		}
		
		return joinParamIdx;
	}

	private <QUERY> void addOneToManyJoin(PreparedQueryBuilder sb, EJoinType joinType, int joinIdx, ExecutableQuery<QUERY> q, QUERY query) {
		
		final int conditionIdx = 0;
		
		sb.appendJoinStatement(joinType);

		final Class<?> oneToManyLeftType  = q.getJoinConditionLeftJavaType(query, joinIdx, conditionIdx);
		final Class<?> oneToManyRightType = q.getJoinConditionRightJavaType(query, joinIdx, conditionIdx);
		
		final Method collectionGetterMethod = q.getJoinConditionOneToManyCollectionGetter(query, joinIdx, conditionIdx);
		
		final FieldReferenceType fieldReferenceType = q.getQueryFieldReferenceType(query);
		
		final int oneToManyLeftSourceIdx  = q.getJoinConditionLeftSourceIdx(query, joinIdx, conditionIdx);
		final int oneToManyRightSourceIdx = q.getJoinConditionRightSourceIdx(query, joinIdx, conditionIdx);
		
		// Must figure out which relation attribute this is ?
		final Relation relation = entityModelUtil.findOneToManyRelation(oneToManyLeftType, oneToManyRightType, collectionGetterMethod);

		if (relation == null) {
			throw new IllegalStateException("Failed to find relation for " + collectionGetterMethod + " from " + oneToManyLeftType + " to " + oneToManyRightType);
		}

		sb.addOneToManyJoin(
				relation,
				fieldReferenceType,
				getSourceReference(q, query, oneToManyLeftSourceIdx),
				getSourceReference(q, query, oneToManyRightSourceIdx));
		
	}
	
	private <QUERY> ConditionsType prepareConditions(ExecutableQuery<QUERY> q, QUERY query, PreparedQueryConditionsBuilder conditionsBuilder, List<JoinConditionId> joinComparisonConditions, QueryParametersDistinct distinctParams) {

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
		

		final PreparedQueryConditionsBuilder sub = nestConditions
				? conditionsBuilder.addNestedForJoin(os)
				: conditionsBuilder;

			
		final ConditionStringBuilder conditionSB = makeConditionStringBuilder(distinctParams);
				
	    if (q.isRootConditionOnly(query)) {
	    	prepareRootConditions(q, query, original, sub, conditionSB);
	    }
	    else {
	    	final int maxDepth = q.getExecutableQueryConditions().getConditionsMaxDepth(query);
	    	
	    	final int [] conditionIndices = new int[maxDepth];
	    	
	    	prepareConditions(q, q.getExecutableQueryConditions(), query, original, sub, 0, conditionIndices, conditionSB);
	    }

		return os;
	}

	
	
	private <QUERY> List<PreparedQueryConditionComparison> prepareRootConditions(ExecutableQuery<QUERY> q, QUERY query, ConditionsType original, PreparedQueryConditionsBuilder sub, ConditionStringBuilder conditionSB) {

		final int numConditions = q.getRootConditionCount(query);
		
		final List<PreparedQueryConditionComparison> ret = new ArrayList<>(numConditions);
		
		for (int conditionIdx = 0; conditionIdx < numConditions; ++ conditionIdx) {

			final CompiledFieldReference lhs = q.getRootConditionLhs(query, conditionIdx);
			final ConditionValue value = q.getRootConditionValue(query, conditionIdx);
			final EClauseOperator operator = q.getRootConditionOperator(query, conditionIdx);

			final FieldReference left = prepareFieldReference(q, query, lhs);
			final List<FunctionBase> lhsFunctions = q.getRootConditionFunctions(query, conditionIdx);
					

			// Operator and value
			final PreparedQueryComparisonRHS preparedCondition = convertCondition(operator, value, conditionSB);

			final PreparedQueryConditionComparison prepared = new PreparedQueryConditionComparison(lhsFunctions, left, preparedCondition);

			sub.addComparisonCondition(original, prepared);
			
			// Clear for next iteration
			conditionSB.clear();
		}

		return ret;
	}

	private <QUERY> List<PreparedQueryConditionComparison> prepareConditions(ExecutableQuery<QUERY> q, ExecutableQueryConditions<QUERY> qc, QUERY query, ConditionsType original, PreparedQueryConditionsBuilder sub, int level, int [] conditionIndices, ConditionStringBuilder conditionSB) {

		final int numConditions = qc.getConditionsCount(query, level, conditionIndices);
		
		final List<PreparedQueryConditionComparison> ret = new ArrayList<>(numConditions);
		
		for (int conditionIdx = 0; conditionIdx < numConditions; ++ conditionIdx) {

			conditionIndices[level] = conditionIdx;
			
			if (qc.isSubCondition(query, level, conditionIndices)) {
				// Condition a sub condition? must add nested

				final int nextLevel = level + 1;
				
				final ConditionsType conditionsType = qc.getConditionsType(query, nextLevel, conditionIndices);
				
				if (conditionsType == null) {
					throw new IllegalArgumentException("conditionsType == null");
				}
				
				final PreparedQueryConditionsBuilder subsub = sub.addNestedForRegularSub(conditionsType);
				
				// Recursively prepare
				prepareConditions(q, qc, query, conditionsType, subsub, nextLevel, conditionIndices, conditionSB);
				
			}
			else {
				// Just create comparison condition
				final CompiledFieldReference lhs = qc.getConditionLhs(query, level, conditionIndices);
				final ConditionValue value = qc.getConditionValue(query, level, conditionIndices);
				final EClauseOperator operator = qc.getOperator(query, level, conditionIndices);
				
				final FieldReference left = prepareFieldReference(q, query, lhs);
				
				final List<FunctionBase> lhsFunctions = qc.getConditionFunctions(query, level, conditionIndices);
				

				// Operator and value
				final PreparedQueryComparisonRHS preparedCondition = convertCondition(operator, value, conditionSB);

				final PreparedQueryConditionComparison prepared = new PreparedQueryConditionComparison(lhsFunctions, left, preparedCondition);

				sub.addComparisonCondition(original, prepared);
				
				// Clear for next iteration
				conditionSB.clear();
			}
		}

		return ret;
	}
	
	private <QUERY> void convertResult(ExecutableQuery<QUERY> q, QUERY query, PreparedQueryBuilder sb) {
		
		final EQueryResultGathering resultGathering = q.getGathering(query);
		
		switch (resultGathering) {
		case ENTITY:
			// Result may be an alias
			final FieldReferenceType fieldReferenceType = q.getQueryFieldReferenceType(query);
			
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

