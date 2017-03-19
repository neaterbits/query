package com.neaterbits.query.sql.dsl.api;

final class Short_Collector_SingleResult_Entity_JoinDecided_Named<MODEL, RESULT>
	extends Short_Collector_SingleResult_Decided_Named<MODEL, RESULT, IShortLogical_WhereOrJoin_SingleResult_Entity_Named<MODEL, RESULT, JOIN_FROM>> 
	
	implements IShortLogical_WhereOrJoin_SingleResult_Entity_Named<MODEL, RESULT, Integer>
	
	{

	Short_Collector_SingleResult_Entity_JoinDecided_Named(BaseQuery select, CollectedQueryResult_Entity_Single result, Collector_Query<MODEL> queryCollector) {
		super(select, result, queryCollector);
	}
}
