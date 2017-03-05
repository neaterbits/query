package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;
import java.util.function.Supplier;

class MapFunctionUtil {

	private static <MODEL, RESULT, R> ResultMapperToImpl<MODEL, RESULT, R, IShortResult_Mapped_Single_Named<MODEL, RESULT>>

		singleNamed(CollectedFunctions functions, Function<?, ? extends Comparable<?>> getter, Supplier<Short_Collector_SingleResult_Decided_Named<MODEL, RESULT>> supplier) {
		
		final Short_Collector_SingleResult_Decided_Named<MODEL, RESULT> impl = supplier.get();
	
		final IMappingCollector<MODEL, RESULT> mappingCollector = impl;
		
		// Named, switch
		return new ResultMapperToImpl<>(getter, mappingCollector, functions);
	}

	private static <MODEL, RESULT, R> ResultMapperToImpl<MODEL, RESULT, R, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>

		singleAlias(CollectedFunctions functions, Supplier<? extends Comparable<?>> getter, Supplier<Short_Collector_SingleResult_Decided_Alias<MODEL, RESULT>> supplier) {
	
		final Short_Collector_SingleResult_Decided_Alias<MODEL, RESULT> impl = supplier.get();
		
		// Named, switch
		return new ResultMapperToImpl<>(getter, impl, functions);
	}
	
	static <MODEL, RESULT> ISharedCollector_Functions_Callback_Named<MODEL, RESULT, IShortResult_Mapped_Single_Named<MODEL, RESULT>>
	
			singleNamedCallback(Supplier<Short_Collector_SingleResult_Decided_Named<MODEL, RESULT>> supplier) {

		return new ISharedCollector_Functions_Callback_Named<MODEL, RESULT, IShortResult_Mapped_Single_Named<MODEL, RESULT>> () {
			
			@Override
			public ISharedFunction_Next<MODEL, RESULT, IShortResult_Mapped_Single_Named<MODEL, RESULT>>
						onComparable(CollectedFunctions functions, Function<?, ? extends Comparable<?>> getter) {
		
				return singleNamed(functions, getter, supplier);
			}
		
			@Override
			public ISharedFunction_Next<MODEL, RESULT, IShortResult_Mapped_Single_Named<MODEL, RESULT>> onString(
					CollectedFunctions functions, StringFunction<?> getter) {
		
				return singleNamed(functions, getter, supplier);
			}
		};
	}

	static <MODEL, RESULT> ISharedCollector_Functions_Callback_Alias<MODEL, RESULT, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>
	
			singleAliasCallback(Supplier<Short_Collector_SingleResult_Decided_Alias<MODEL, RESULT>> supplier) {

		return new ISharedCollector_Functions_Callback_Alias<MODEL, RESULT, IShortResult_Mapped_Single_Alias<MODEL,RESULT>>() {

			@Override
			public ISharedFunction_Next<MODEL, RESULT, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>
					onComparable(CollectedFunctions functions, Supplier<? extends Comparable<?>> getter) {
				
				return singleAlias(functions, getter, supplier);
			}
			
			@Override
			public ISharedFunction_Next<MODEL, RESULT, IShortResult_Mapped_Single_Alias<MODEL, RESULT>> onString(
					CollectedFunctions functions, ISupplierString getter) {
			
				return singleAlias(functions, getter, supplier);
			}
		};
	}
}
