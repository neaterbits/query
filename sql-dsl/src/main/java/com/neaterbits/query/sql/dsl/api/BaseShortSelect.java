package com.neaterbits.query.sql.dsl.api;

import com.neaterbits.query.sql.dsl.api.entity.QueryMetaModel;

abstract class BaseShortSelect<

	// for sums, we return Long for short and int so must differentiate from other aggregate
	// fuctions, this is because sum may wrap over limits of type
	SUM_LONG_RET,
	COUNT_RET,
	
	// for other types aggregates, we return the same result as the input type, eg.
	// max of short-type will never be > short type
	SHORT_RET,
	INT_RET,
	LONG_RET,
	BIGDECIMAL_RET>

	extends BaseSelect<SUM_LONG_RET, COUNT_RET, SHORT_RET, INT_RET, LONG_RET, BIGDECIMAL_RET> {

	
	BaseShortSelect(QueryMetaModel queryMetaModel) {
		super(queryMetaModel);
	}

	@Override
	final EQueryStyle getQueryStyle() {
		return EQueryStyle.SHORT;
	}
}
