package com.neaterbits.query.sql.dsl.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

	static <QUERY> List<Param<?>> getConditionParamRefs(ExecutableQuery<QUERY> q, QUERY query, boolean unique) {

		final List<Param<?>> ret;

		if (q.hasConditions(query)) {
			final List<Param<?>> list = new ArrayList<>();
			
			getConditionParamRefs(q, query, 0, makeConditionIndices(q, query), list, unique);
			
			ret = Collections.unmodifiableList(list);
		}
		else {
			ret = Collections.emptyList();
		}

		return ret;
	}

	private static <QUERY> int [] makeConditionIndices(ExecutableQuery<QUERY> q, QUERY query) {
		final int maxDepth = q.getConditionsMaxDepth(query);
		final int [] conditionIndices = new int [maxDepth];

		return conditionIndices;
	}

	private static <QUERY> void getConditionParamRefs(ExecutableQuery<QUERY> q, QUERY query, int level, int [] conditionIndices, List<Param<?>> list, boolean unique) {

		final int conditionCount = q.getConditionsCount(query, level, conditionIndices);

		for (int conditionIdx = 0; conditionIdx < conditionCount; ++ conditionIdx) {

			conditionIndices[level] = conditionIdx;

			if (q.isSubCondition(query, level, conditionIndices)) {
				getConditionParamRefs(q, query, level + 1, conditionIndices, list, unique);
			}
			else {
				final ConditionValue conditionValue = q.getConditionValue(query, level, conditionIndices);

				if (conditionValue.getType() == EConditionValue.PARAM) {
					final ConditionValue_Param conditionValue_Param = (ConditionValue_Param)conditionValue;
					
					final Param<?> param = conditionValue_Param.getParam();
					
					if (param == null) {
						throw new IllegalStateException("param == null");
					}

					if (unique) {
						if (!list.contains(param)) {
							list.add(param);
						}
					}
					else {
						// just add
						list.add(param);
					}
				}
			}
		}
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
