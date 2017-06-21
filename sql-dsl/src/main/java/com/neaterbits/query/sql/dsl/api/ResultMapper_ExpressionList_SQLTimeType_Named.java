package com.neaterbits.query.sql.dsl.api;


public class ResultMapper_ExpressionList_SQLTimeType_Named< 
	MODEL,
	RESULT,
	R, // extends Comparable<R>,
	
	RET extends ISharedFunction_After<MODEL, RESULT>>
	
	extends ResultMapper_ExpressionList_Base<
		MODEL,
		RESULT,
		R,
		
		RET,
		
		RET,
		ISharedFunction_After<MODEL, RESULT>, // Alias
		ISharedFunction_After<MODEL, RESULT>, // Undecided
		
		ISharedFunction_Next<MODEL, RESULT, RET>, // Comparable
		ISharedFunction_Next<MODEL, RESULT, RET>, // String
		ISharedFunction_Next<MODEL, RESULT, RET>, // Comparable
		ISharedFunction_Next<MODEL, RESULT, RET>, // String
		ISharedFunction_Next<MODEL, RESULT, RET>, // Comparable
		ISharedFunction_Next<MODEL, RESULT, RET>, // String
		
		ISharedFunction_Next<MODEL, RESULT, RET>,
		ISharedFunction_Next<MODEL, RESULT, RET>,
		ISharedFunction_Next<MODEL, RESULT, RET>,
		
		ISharedFunction_Next<MODEL, RESULT, RET>,
		ISharedFunction_Next<MODEL, RESULT, RET>,
		ISharedFunction_Next<MODEL, RESULT, RET>,
		ISharedFunction_Next<MODEL, RESULT, RET>,
		ISharedFunction_Next<MODEL, RESULT, RET>,
		ISharedFunction_Next<MODEL, RESULT, RET>,
		ISharedFunction_Next<MODEL, RESULT, RET>,
		ISharedFunction_Next<MODEL, RESULT, RET>,
		ISharedFunction_Next<MODEL, RESULT, RET>,
		ISharedFunction_Next<MODEL, RESULT, RET>,
		ISharedFunction_Next<MODEL, RESULT, RET>,
		ISharedMap_OpsAndTo_SQLTimeType_Named<MODEL, RESULT, java.sql.Date, RET>,
		ISharedMap_OpsAndTo_SQLTimeType_Named<MODEL, RESULT, java.sql.Time, RET>,
		ISharedMap_OpsAndTo_SQLTimeType_Named<MODEL, RESULT, java.sql.Timestamp, RET>,
		
	
		ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
		
		ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
		
		ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
		
		ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>
		
	>
	
	implements ISharedMap_OpsAndTo_SQLTimeType_Named<MODEL, RESULT, R, RET>

{
	private final IMappingCollector<MODEL, RESULT> impl;
	
	// TODO go over constructor calls and use static utility methods? 
	ResultMapper_ExpressionList_SQLTimeType_Named(Expression expression, IMappingCollector<MODEL, RESULT> impl) {
		super(expression /*, impl */, EFieldAccessType.NAMED);
		
		if (impl == null) {
			throw new IllegalArgumentException("impl == null");
		}
		
		this.impl = impl;
	}
	
	
	@Override
	IMappingCollector<MODEL, RESULT> getMappingCollector(EFieldAccessType fieldAccessType) {
	if (fieldAccessType != EFieldAccessType.NAMED) {
		throw new IllegalStateException("Expected named");
	}
	
	return impl;
}
}
