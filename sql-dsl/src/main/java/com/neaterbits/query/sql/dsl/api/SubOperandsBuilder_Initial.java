package com.neaterbits.query.sql.dsl.api;

abstract class SubOperandsBuilder_Initial<

		MODEL,
		RESULT,

		R extends Comparable<R>,
		
		AFTER extends ISharedFunction_After<MODEL,RESULT>,
		
		NAMED_RET extends ISharedFunction_After<MODEL, RESULT>,
		ALIAS_RET extends ISharedFunction_After<MODEL, RESULT>,
		
		NUMERIC_OPERAND_NEXT   extends ISharedFunction_Next<MODEL, RESULT, AFTER>,
		STRING_OPERAND_NEXT    extends ISharedFunction_Next<MODEL, RESULT, AFTER>
	>

	extends SubOperandsBuilder<
			MODEL,
			RESULT,
			
			R,
			
			AFTER,
			
			NAMED_RET,
			ALIAS_RET,

			NUMERIC_OPERAND_NEXT,
			STRING_OPERAND_NEXT,
			
			
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
			ISharedSubOperand_String_Ops_Named<MODEL, RESULT, NAMED_RET>,
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
			ISharedSubOperand_String_Ops_Alias<MODEL, RESULT, ALIAS_RET>,
			ISharedSubOperand_Numeric_Ops_Alias<MODEL, RESULT, R, ALIAS_RET>,
			ISharedSubOperand_Numeric_Ops_Alias<MODEL, RESULT, R, ALIAS_RET>,
			ISharedSubOperand_NumericSQLTimeType_Ops_Alias<MODEL, RESULT, R, ALIAS_RET>,
			ISharedSubOperand_NumericSQLTimeType_Ops_Alias<MODEL, RESULT, R, ALIAS_RET>,
			ISharedSubOperand_NumericSQLTimeType_Ops_Alias<MODEL, RESULT, R, ALIAS_RET>
			>

 {

 }
