package com.neaterbits.query.sql.dsl.api;

abstract class SubOperandsBuilder_Initial<

		MODEL,
		RESULT,

		R extends Comparable<R>,
		
		AFTER extends ISharedFunction_After<MODEL,RESULT>,
		
		NAMED_RET extends ISharedFunction_After<MODEL, RESULT>,
		ALIAS_RET extends ISharedFunction_After<MODEL, RESULT>
	>

	extends SubOperandsBuilder<
			MODEL,
			RESULT,
			
			R,
			
			AFTER,
			
			NAMED_RET,
			ALIAS_RET,
			
			ISharedSubOperandsBuilder_Numeric_Next_Named<MODEL,RESULT,R,AFTER>,
			ISharedFunction_Next<MODEL, RESULT, AFTER>,
			
			ISharedSubOperand_Numeric_Ops_Named<MODEL, RESULT, R, NAMED_RET>,
			ISharedSubOperand_Numeric_Ops_Named<MODEL, RESULT, R, NAMED_RET>,
			
			ISharedSubOperand_Numeric_Ops_Named<MODEL, RESULT, R, NAMED_RET>, 
			ISharedSubOperand_Numeric_Ops_Named<MODEL, RESULT, R, NAMED_RET>, 
			ISharedSubOperand_Numeric_Ops_Named<MODEL, RESULT, R, NAMED_RET>,
			ISharedSubOperand_Numeric_Ops_Named<MODEL, RESULT, R, NAMED_RET>,
			ISharedSubOperand_Numeric_Ops_Named<MODEL, RESULT, R, NAMED_RET>,
			ISharedSubOperand_Numeric_Ops_Named<MODEL, RESULT, R, NAMED_RET>,
			ISharedSubOperand_Numeric_Ops_Named<MODEL, RESULT, R, NAMED_RET>,
			ISharedSubOperand_Numeric_Ops_Named<MODEL, RESULT, R, NAMED_RET>,
			ISharedSubOperand_Numeric_Ops_Named<MODEL, RESULT, R, NAMED_RET>,
			ISharedSubOperand_Numeric_Ops_Named<MODEL, RESULT, R, NAMED_RET>,
			ISharedSubOperand_Numeric_Ops_Named<MODEL, RESULT, R, NAMED_RET>,
			ISharedSubOperand_NumericSQLTimeType_Ops_Named<MODEL, RESULT, R, NAMED_RET>,
			ISharedSubOperand_NumericSQLTimeType_Ops_Named<MODEL, RESULT, R, NAMED_RET>,
			ISharedSubOperand_NumericSQLTimeType_Ops_Named<MODEL, RESULT, R, NAMED_RET>,

			ISharedSubOperand_Numeric_Ops_Alias<MODEL, RESULT, R, ALIAS_RET>,
			ISharedSubOperand_Numeric_Ops_Alias<MODEL, RESULT, R, ALIAS_RET>,

			ISharedSubOperand_Numeric_Ops_Alias<MODEL, RESULT, R, ALIAS_RET>, 
			ISharedSubOperand_Numeric_Ops_Alias<MODEL, RESULT, R, ALIAS_RET>,
			ISharedSubOperand_Numeric_Ops_Alias<MODEL, RESULT, R, ALIAS_RET>,
			ISharedSubOperand_Numeric_Ops_Alias<MODEL, RESULT, R, ALIAS_RET>,
			ISharedSubOperand_Numeric_Ops_Alias<MODEL, RESULT, R, ALIAS_RET>,
			ISharedSubOperand_Numeric_Ops_Alias<MODEL, RESULT, R, ALIAS_RET>,
			ISharedSubOperand_Numeric_Ops_Alias<MODEL, RESULT, R, ALIAS_RET>,
			ISharedSubOperand_Numeric_Ops_Alias<MODEL, RESULT, R, ALIAS_RET>,
			ISharedSubOperand_Numeric_Ops_Alias<MODEL, RESULT, R, ALIAS_RET>,
			ISharedSubOperand_Numeric_Ops_Alias<MODEL, RESULT, R, ALIAS_RET>,
			ISharedSubOperand_Numeric_Ops_Alias<MODEL, RESULT, R, ALIAS_RET>,
			ISharedSubOperand_NumericSQLTimeType_Ops_Alias<MODEL, RESULT, R, ALIAS_RET>,
			ISharedSubOperand_NumericSQLTimeType_Ops_Alias<MODEL, RESULT, R, ALIAS_RET>,
			ISharedSubOperand_NumericSQLTimeType_Ops_Alias<MODEL, RESULT, R, ALIAS_RET>
			>

		implements ISharedSubOperandsBuilder_Numeric_Next_Named<MODEL, RESULT, R, AFTER>
 {

 }
