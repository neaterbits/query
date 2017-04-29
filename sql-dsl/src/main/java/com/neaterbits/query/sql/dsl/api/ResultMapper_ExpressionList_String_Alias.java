package com.neaterbits.query.sql.dsl.api;

@Deprecated // Necessary with String-specific?
final class ResultMapper_ExpressionList_String_Alias<
		MODEL,
		RESULT,
		AFTER extends ISharedFunction_After<MODEL, RESULT>
		/*,
		NEXT extends ISharedFunction_Next<MODEL, RESULT, AFTER> */>
		
	extends ResultMapper_ExpressionList_Base<
			MODEL, RESULT,
			String,
			
			AFTER,
			
			ISharedFunction_After<MODEL, RESULT>,
			AFTER,
			
			
			ISharedFunction_Next<MODEL, RESULT, AFTER>, // Comparable
			ISharedResultMap_OpsAndTo_String_Alias<MODEL, RESULT, AFTER>,
			
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
			ISharedResultMap_OpsAndTo_String_Alias<MODEL, RESULT, AFTER>,
			ISharedFunction_Next<MODEL, RESULT, AFTER>,
			ISharedFunction_Next<MODEL, RESULT, AFTER>,
			ISharedFunction_Next<MODEL, RESULT, AFTER>,
			ISharedFunction_Next<MODEL, RESULT, AFTER>,
			ISharedFunction_Next<MODEL, RESULT, AFTER>
	> 
	implements ISharedResultMap_OpsAndTo_String_Alias<MODEL, RESULT, AFTER> {

	private final IMappingCollector<MODEL, RESULT> impl;

	ResultMapper_ExpressionList_String_Alias(Expression expression, IMappingCollector<MODEL, RESULT> impl) {
		super(expression, EFieldAccessType.ALIAS);
		
		if (impl == null) {
			throw new IllegalArgumentException("impl == null");
		}
		
		this.impl = impl;

	}

	@Override
	IMappingCollector<MODEL, RESULT> getMappingCollector(EFieldAccessType fieldAccessType) {
		if (fieldAccessType != EFieldAccessType.ALIAS) {
			throw new IllegalStateException("Expected alias");
		}
		
		return impl;
	}
}
