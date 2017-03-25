package com.neaterbits.query.sql.dsl.api;

import com.neaterbits.query.sql.dsl.api.entity.QueryMetaModel;

abstract class BaseShortSelect<

	// for sums, we return Long for short and int so must differentiate from other aggregate
	// fuctions, this is because sum may wrap over limits of type
	NAMED_SUM_LONG_RET,
	NAMED_COUNT_RET,
	
	// for other types aggregates, we return the same result as the input type, eg.
	// max of short-type will never be > short type
	NAMED_SHORT_RET,
	NAMED_INT_RET,
	NAMED_LONG_RET,
	NAMED_DOUBLE_RET,
	NAMED_BIGDECIMAL_RET,
	NAMED_DATE_RET,
	
	ALIAS_SUM_LONG_RET,
	ALIAS_COUNT_RET,
	ALIAS_SHORT_RET,
	ALIAS_INT_RET,
	ALIAS_LONG_RET,
	ALIAS_DOUBLE_RET,
	ALIAS_BIGDECIMAL_RET,
	ALIAS_DATE_RET
	>

	extends BaseSelect<
		NAMED_SUM_LONG_RET, NAMED_COUNT_RET, NAMED_SHORT_RET, NAMED_INT_RET, NAMED_LONG_RET, NAMED_DOUBLE_RET, NAMED_BIGDECIMAL_RET, NAMED_DATE_RET,
		ALIAS_SUM_LONG_RET, ALIAS_COUNT_RET, ALIAS_SHORT_RET, ALIAS_INT_RET, ALIAS_LONG_RET, ALIAS_DOUBLE_RET, ALIAS_BIGDECIMAL_RET, ALIAS_DATE_RET> {

	
	BaseShortSelect(QueryMetaModel queryMetaModel) {
		super(queryMetaModel);
	}

	@Override
	final EQueryStyle getQueryStyle() {
		return EQueryStyle.SHORT;
	}
}
