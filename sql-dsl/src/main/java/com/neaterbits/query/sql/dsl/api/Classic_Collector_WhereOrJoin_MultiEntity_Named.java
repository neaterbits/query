package com.neaterbits.query.sql.dsl.api;

public class Classic_Collector_WhereOrJoin_MultiEntity_Named<MODEL, RESULT>

		extends Classic_Collector_WhereOrJoin_Named_Base<MODEL, RESULT,

				IClassicJoin_Condition_MultiEntity_Named<MODEL, RESULT, Object, Object>,

				IClassicLogical_And_MultiEntity_Named<MODEL, RESULT>,
				IClassicLogical_Or_MultiEntity_Named<MODEL, RESULT>,
				IClassicLogical_AndOr_MultiEntity_Named<MODEL, RESULT>>

		implements IClassicLogical_WhereOrJoin_MultiEntity_Named<MODEL, RESULT> {

	Classic_Collector_WhereOrJoin_MultiEntity_Named(BaseQueryEntity<MODEL> last) {
		super(last);
	}

	@Override
	Classic_Collector_Or_Named<MODEL, RESULT, IClassicLogical_Or_MultiEntity_Named<MODEL, RESULT>> createOrCollector() {
		return new Classic_Collector_Or_MultiEntity_Named<>(this);
	}

	@Override
	Classic_Collector_And_Named<MODEL, RESULT, IClassicLogical_And_MultiEntity_Named<MODEL, RESULT>> createAndCollector() {
		return new Classic_Collector_And_MultiEntity_Named<>(this);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <LEFT, RIGHT> IClassicJoin_Condition_MultiEntity_Named<MODEL, RESULT, LEFT, RIGHT>

			innerJoin(Class<LEFT> leftType, Class<RIGHT> rightType) {

		return (IClassicJoin_Condition_MultiEntity_Named<MODEL, RESULT, LEFT, RIGHT>) super.innerJoinUtil(leftType,
				rightType);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <LEFT, RIGHT> IClassicJoin_Condition_MultiEntity_Named<MODEL, RESULT, LEFT, RIGHT> leftJoin(
			Class<LEFT> leftType, Class<RIGHT> rightType) {

		return (IClassicJoin_Condition_MultiEntity_Named<MODEL, RESULT, LEFT, RIGHT>) super.leftJoinUtil(leftType,
				rightType);
	}

}
