package com.neaterbits.query.sql.dsl.api;

final class Classic_Collector_WhereOrJoin_MultiMapped_Named<MODEL, RESULT>
	extends SQL_Collector_WhereOrJoin_MultiMapped_Named<
		MODEL,
		RESULT,
		IClassicJoin_Condition_MultiMapped_Alias<MODEL, RESULT>
		> 
	implements IClassicJoin_MultiMapped_Named<MODEL, RESULT>

{

	Classic_Collector_WhereOrJoin_MultiMapped_Named(Collector_Conditions_Initial<MODEL, RESULT, ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>> last) {
		super(last);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <LEFT, RIGHT> ISQLJoin_Condition_MultiMapped_Named<MODEL, RESULT, LEFT, RIGHT>
	
			innerJoin(Class<LEFT> leftType, Class<RIGHT> rightType) {

		return (ISQLJoin_Condition_MultiMapped_Named<MODEL, RESULT, LEFT, RIGHT>)super.innerJoinUtil(leftType, rightType);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <LEFT, RIGHT> ISQLJoin_Condition_MultiMapped_Named<MODEL, RESULT, LEFT, RIGHT>
		leftJoin(Class<LEFT> leftType, Class<RIGHT> rightType) {
		
		
		return (ISQLJoin_Condition_MultiMapped_Named<MODEL, RESULT, LEFT, RIGHT>)super.leftJoinUtil(leftType, rightType);
	}
}
