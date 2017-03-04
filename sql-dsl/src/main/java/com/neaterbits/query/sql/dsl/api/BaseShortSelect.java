package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;

import com.neaterbits.query.sql.dsl.api.entity.QueryMetaModel;

abstract class BaseShortSelect extends BaseSelect<
	IShortResult_Numeric_Named<Long>,
	IShortResult_Numeric_Named<Long>,
	
	IShortResult_Numeric_Named<Short>,
	IShortResult_Numeric_Named<Integer>,
	IShortResult_Numeric_Named<Long>,
	IShortResult_Numeric_Named<BigDecimal>> {

	
	BaseShortSelect(QueryMetaModel queryMetaModel) {
		super(queryMetaModel);
	}

	@Override
	final EQueryStyle getQueryStyle() {
		return EQueryStyle.SHORT;
	}
}
