package com.neaterbits.query.sql.dsl.api;

public class SQL_Collector_WhereOrJoin_MultiEntity_Named<MODEL, RESULT>

		extends SQL_Collector_WhereOrJoin_Named_Base<MODEL, RESULT,

				ISQLJoin_Condition_MultiEntity_Named<MODEL, RESULT, Object, Object>,

				ISQLLogical_And_MultiEntity_Named<MODEL, RESULT>,
				ISQLLogical_Or_MultiEntity_Named<MODEL, RESULT>,
				ISQLLogical_AndOr_MultiEntity_Named<MODEL, RESULT>>

		implements ISQLLogical_WhereOrJoin_MultiEntity_Named<MODEL, RESULT> {

	SQL_Collector_WhereOrJoin_MultiEntity_Named(Collector_Base<MODEL> last) {
		super(last);
	}

	@Override
	Collector_Or_Named<MODEL, RESULT, ISQLLogical_Or_MultiEntity_Named<MODEL, RESULT>, ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>, ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>, ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>> createOrCollector() {
		return new SQL_Collector_Or_MultiEntity_Named<>(this);
	}

	@Override
	Collector_And_Named<MODEL, RESULT, ISQLLogical_And_MultiEntity_Named<MODEL, RESULT>, ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>, ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>, ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>> createAndCollector() {
		return new SQL_Collector_And_MultiEntity_Named<>(this);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <LEFT, RIGHT> ISQLJoin_Condition_MultiEntity_Named<MODEL, RESULT, LEFT, RIGHT>

			innerJoin(Class<LEFT> leftType, Class<RIGHT> rightType) {

		return (ISQLJoin_Condition_MultiEntity_Named<MODEL, RESULT, LEFT, RIGHT>) super.innerJoinUtil(leftType,
				rightType);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <LEFT, RIGHT> ISQLJoin_Condition_MultiEntity_Named<MODEL, RESULT, LEFT, RIGHT> leftJoin(
			Class<LEFT> leftType, Class<RIGHT> rightType) {

		return (ISQLJoin_Condition_MultiEntity_Named<MODEL, RESULT, LEFT, RIGHT>) super.leftJoinUtil(leftType,
				rightType);
	}

}
