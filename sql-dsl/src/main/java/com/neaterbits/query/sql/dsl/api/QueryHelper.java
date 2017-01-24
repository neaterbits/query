package com.neaterbits.query.sql.dsl.api;

final class QueryHelper {

	static <QUERY> boolean hasConditionParams(ExecutableQuery<QUERY> q, QUERY query) {

		final boolean hasParams;
		
		if (q.hasConditions(query)) {
			// Loop through all conditions to figure whether has params
			
			final int maxDepth = q.getConditionsMaxDepth(query);
			
			final int [] conditionIndices = new int [maxDepth];
			
			hasParams = hasConditionParams(q, query, 0, conditionIndices);
		}
		else {
			// No conditions so cannot have params
			hasParams = false;
		}

		return hasParams;
	}

	private static <QUERY> boolean hasConditionParams(ExecutableQuery<QUERY> q, QUERY query, int level, int [] conditionIndices) {
		
		boolean hasParams = false;

		final int conditionCount = q.getConditionsCount(query, level, conditionIndices);
		
		for (int conditionIdx = 0; conditionIdx < conditionCount; ++ conditionIdx) {

			boolean thisConditionHasParams;
			
			conditionIndices[level] = conditionIdx;
			
			if (q.isSubCondition(query, level, conditionIndices)) {
				thisConditionHasParams = hasConditionParams(q, query, level + 1, conditionIndices);
			}
			else {
				
				final ConditionValue conditionValue = q.getConditionValue(query, level, conditionIndices);

				thisConditionHasParams = conditionValue.getType() == EConditionValue.PARAM;
			}
			
			if (thisConditionHasParams) {
				hasParams = true;
				break;
			}
		}

		return hasParams;
	}
}
