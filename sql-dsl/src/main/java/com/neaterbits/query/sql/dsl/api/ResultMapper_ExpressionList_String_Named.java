package com.neaterbits.query.sql.dsl.api;

@Deprecated // Necessary with String-specific?
final class ResultMapper_ExpressionList_String_Named<
		MODEL,
		RESULT,
		AFTER extends ISharedFunction_After<MODEL, RESULT>
/*,
		NEXT extends ISharedFunction_Next<MODEL, RESULT, AFTER> */>

	extends ResultMapper_ExpressionList_Base<
		MODEL, RESULT,
		String,
		
		AFTER,
		
		AFTER,
		ISharedFunction_After<MODEL, RESULT>,
		ISharedFunction_After<MODEL, RESULT>,

		
		ISharedFunction_Next<MODEL, RESULT, AFTER>, // Comparable
		ISharedMap_OpsAndTo_String_Named<MODEL, RESULT, AFTER>,
		ISharedFunction_Next<MODEL, RESULT, AFTER>, ISharedFunction_Next<MODEL, RESULT, AFTER>,
		ISharedFunction_Next<MODEL, RESULT, AFTER>, // Comparable
		ISharedMap_OpsAndTo_String_Named<MODEL, RESULT, AFTER>,
		
		ISharedFunction_Next<MODEL, RESULT, AFTER>,
		ISharedFunction_Next<MODEL, RESULT, AFTER>,
		ISharedFunction_Next<MODEL, RESULT, AFTER>,
		
		ISharedFunction_Next<MODEL, RESULT, AFTER>,
		ISharedFunction_Next<MODEL, RESULT, AFTER>,
		ISharedFunction_Next<MODEL, RESULT, AFTER>,
		ISharedFunction_Next<MODEL, RESULT, AFTER>,
		ISharedFunction_Next<MODEL, RESULT, AFTER>,
		ISharedFunction_Next<MODEL, RESULT, AFTER>,
		ISharedFunction_Next<MODEL, RESULT, AFTER>,
		ISharedFunction_Next<MODEL, RESULT, AFTER>,
		ISharedMap_OpsAndTo_String_Named<MODEL, RESULT, AFTER>,
		ISharedFunction_Next<MODEL, RESULT, AFTER>,
		ISharedFunction_Next<MODEL, RESULT, AFTER>,
		ISharedFunction_Next<MODEL, RESULT, AFTER>,
		ISharedFunction_Next<MODEL, RESULT, AFTER>,
		ISharedFunction_Next<MODEL, RESULT, AFTER>,

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


	implements ISharedMap_OpsAndTo_String_Named<MODEL, RESULT, AFTER> {

	private final IMappingCollector<MODEL, RESULT> impl;
	
	ResultMapper_ExpressionList_String_Named(Expression expression, IMappingCollector<MODEL, RESULT> impl) {
		super(expression, EFieldAccessType.NAMED);
		
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
