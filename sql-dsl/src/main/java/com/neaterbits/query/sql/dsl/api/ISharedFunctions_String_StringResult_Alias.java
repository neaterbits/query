package com.neaterbits.query.sql.dsl.api;

public interface ISharedFunctions_String_StringResult_Alias<
			MODEL,
			RESULT,
			RET extends ISharedFunction_After<MODEL, RESULT>,
			
			STRING_CLAUSE extends ISharedFunction_Next<MODEL, RESULT, RET>
	> {

	STRING_CLAUSE lower(ISupplierString getter);

	STRING_CLAUSE upper(ISupplierString getter);

	STRING_CLAUSE trim(ISupplierString getter);

    STRING_CLAUSE substring(ISupplierString getter, int start, int length);
	
    @Deprecated // there is a concat operand for strings
    STRING_CLAUSE concat(ISupplierString getter1, ISupplierString getter2);

    @Deprecated // there is a concat operand for strings
    STRING_CLAUSE concat(ISupplierString getter, String value);

    @Deprecated // there is a concat operand for strings
    STRING_CLAUSE concat(String value, ISupplierString getter);

    @Deprecated // TODO move to separate interface?
    STRING_CLAUSE concat(ISupplierString getter, Param<String> param);

    @Deprecated // TODO move to separate interface?
    STRING_CLAUSE concat(Param<String> param, ISupplierString getter);

}
