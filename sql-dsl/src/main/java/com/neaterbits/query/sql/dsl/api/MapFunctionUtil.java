package com.neaterbits.query.sql.dsl.api;

import java.util.function.Supplier;

@Deprecated
class MapFunctionUtil {

	private static <MODEL, RESULT, R, IF extends ISharedSelectSourceBuilder<MODEL, RESULT>, DECIDED extends IMappingCollector<MODEL, RESULT>>
	
		// ResultMapperToImpl<MODEL, RESULT, R, IF>
	
		IMappingCollector<MODEL, RESULT> named(Expression expression, Supplier<DECIDED> supplier) {
		
		final DECIDED impl = supplier.get();
	
		final IMappingCollector<MODEL, RESULT> mappingCollector = impl;
		
		// Named, switch
		
		return mappingCollector;
		
	}

	private static <MODEL, RESULT, R, IF extends ISharedSelectSourceBuilder<MODEL, RESULT>, DECIDED extends IMappingCollector<MODEL, RESULT>>
	
		ResultMapperToImpl<MODEL, RESULT, R, IF> alias(CollectedFunctions functions, Supplier<? extends Comparable<?>> getter, Supplier<DECIDED> supplier) {
	
		final DECIDED impl = supplier.get();
		
		final Expression expression = new NestedFunctionCallsExpression(functions, getter);
		
		// Named, switch
		return new ResultMapperToImpl<>(expression, impl);
	}
	
	private static <MODEL, RESULT, IF extends ISharedSelectSourceBuilder<MODEL, RESULT>, DECIDED extends IMappingCollector<MODEL, RESULT>>
	
		ISharedCollector_Functions_Callback<MODEL, RESULT, IF> namedCallback(Supplier<DECIDED> supplier) {

		return new ISharedCollector_Functions_Callback<MODEL, RESULT, IF> () {
			
			@Override
			public ISharedFunction_Next<MODEL, RESULT, IF>
						onComparable(Expression expression) {
		
				final IMappingCollector<MODEL, RESULT> impl = named(expression, supplier);
				
				return new ResultMapper_ExpressionList_Numeric_Named<>(expression, impl);

			}
		
			@Override
			public ISharedFunction_Next<MODEL, RESULT, IF> onString(Expression expression) {
		
				final IMappingCollector<MODEL, RESULT> impl =  named(expression, supplier);
				
				return new ResultMapper_ExpressionList_String_Named<>(expression, impl);
			}
		};
	}

	private static <MODEL, RESULT, IF extends ISharedSelectSourceBuilder<MODEL, RESULT>, DECIDED extends IMappingCollector<MODEL, RESULT>>
	
		ISharedCollector_Functions_Callback_Alias<MODEL, RESULT, IF> aliasCallback(Supplier<DECIDED> supplier) {

		return new ISharedCollector_Functions_Callback_Alias<MODEL, RESULT, IF>() {

			@Override
			public ISharedFunction_Next<MODEL, RESULT, IF>
					onComparable(CollectedFunctions functions, Supplier<? extends Comparable<?>> getter) {
				
				return alias(functions, getter, supplier);
			}
			
			@Override
			public ISharedFunction_Next<MODEL, RESULT, IF> onString(
					CollectedFunctions functions, ISupplierString getter) {
			
				return alias(functions, getter, supplier);
			}
		};
	}

	static <MODEL, RESULT> ISharedCollector_Functions_Callback<MODEL, RESULT, IShortResult_Mapped_Single_Named<MODEL, RESULT>>
	
		singleNamedCallback(Supplier<Short_Collector_SingleResult_Decided_Named<MODEL, RESULT>> supplier) {
		
		return namedCallback(supplier);
	}
	
	static <MODEL, RESULT> ISharedCollector_Functions_Callback_Alias<MODEL, RESULT, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>
		singleAliasCallback(Supplier<Short_Collector_SingleResult_Decided_Alias<MODEL, RESULT>> supplier) {
		
		return aliasCallback(supplier);
	}
	
	static <MODEL, RESULT> ISharedCollector_Functions_Callback<MODEL, RESULT, IShortResult_Mapped_Multi_Named<MODEL, RESULT>>
		multiNamedCallback(Supplier<Short_Collector_MultiResult_Decided_Named<MODEL, RESULT>> supplier) {
	
		return namedCallback(supplier);
	}

	static <MODEL, RESULT> ISharedCollector_Functions_Callback_Alias<MODEL, RESULT, IShortResult_Mapped_Multi_Alias<MODEL, RESULT>>
		multiAliasCallback(Supplier<Short_Collector_MultiResult_Decided_Alias<MODEL, RESULT>> supplier) {
	
		return aliasCallback(supplier);
	}
}
