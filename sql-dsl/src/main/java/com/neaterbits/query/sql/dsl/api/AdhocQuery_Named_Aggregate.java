package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

final class AdhocQuery_Named_Aggregate<MODEL> extends AdhocQuery_Named_Singular<MODEL>
	implements 	IAdhocNumericNamedResult<MODEL, Object, Object>,
				IAdhocNumericInstanceResult<MODEL, Object>{

	AdhocQuery_Named_Aggregate(Function<?, ?> aggregateGetter, EAggregateFunction aggregateFunction,
			EAggregateType aggregateNumericInputType, EAggregateType aggregateNumericOutputType) {
		super(aggregateGetter, aggregateFunction, aggregateNumericInputType, aggregateNumericOutputType);
	}

	@Override
	public IAdhocFunctions_Initial<
				MODEL,
				Object,
				Object,
				IAdhocLogical_And_Or<MODEL, Object, Object>,
				ISharedCondition_Comparable_Common_Value<MODEL, Object, Integer, IAdhocLogical_And_Or<MODEL, Object, Object>>,
				ISharedCondition_Comparable_Common_Value<MODEL, Object, Long, IAdhocLogical_And_Or<MODEL, Object, Object>>,
				ISharedCondition_Comparable_String_Value<MODEL, Object, IAdhocLogical_And_Or<MODEL, Object, Object>>
			> where() {
		
		return addWhere();
	}
}
