package com.neaterbits.query.sql.dsl.api;

// TODO: split in named and alias?
public interface ISharedComparison_SQLTimeType_All<MODEL, RESULT, T, L extends ISharedLogical_Base<MODEL, RESULT>>
		extends ISharedComparison_SQLTimeType_Value<MODEL, RESULT, T, L>,
				ISharedComparison_SQLTimeType_Param<MODEL, RESULT, L>{

}
