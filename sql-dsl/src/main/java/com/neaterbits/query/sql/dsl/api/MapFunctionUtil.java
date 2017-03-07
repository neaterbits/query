package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;
import java.util.function.Supplier;

class MapFunctionUtil {

	private static <MODEL, RESULT, R, IF extends ISharedSelectSourceBuilder<MODEL, RESULT>, DECIDED extends IMappingCollector<MODEL, RESULT>>
	
		ResultMapperToImpl<MODEL, RESULT, R, IF> named(CollectedFunctions functions, Function<?, ? extends Comparable<?>> getter, Supplier<DECIDED> supplier) {
		
		final DECIDED impl = supplier.get();
	
		final IMappingCollector<MODEL, RESULT> mappingCollector = impl;
		
		// Named, switch
		
		final Expression expression = new NestedFunctionCallsExpression(functions, getter);
		
		return new ResultMapperToImpl<MODEL, RESULT, R, IF>(expression, mappingCollector);
	}

	private static <MODEL, RESULT, R, IF extends ISharedSelectSourceBuilder<MODEL, RESULT>, DECIDED extends IMappingCollector<MODEL, RESULT>>
	
		ResultMapperToImpl<MODEL, RESULT, R, IF> alias(CollectedFunctions functions, Supplier<? extends Comparable<?>> getter, Supplier<DECIDED> supplier) {
	
		final DECIDED impl = supplier.get();
		
		final Expression expression = new NestedFunctionCallsExpression(functions, getter);
		
		// Named, switch
		return new ResultMapperToImpl<>(expression, impl);
	}
	
	private static <MODEL, RESULT, IF extends ISharedSelectSourceBuilder<MODEL, RESULT>, DECIDED extends IMappingCollector<MODEL, RESULT>>
	
		ISharedCollector_Functions_Callback_Named<MODEL, RESULT, IF> namedCallback(Supplier<DECIDED> supplier) {

		return new ISharedCollector_Functions_Callback_Named<MODEL, RESULT, IF> () {
			
			@Override
			public ISharedFunction_Next<MODEL, RESULT, IF>
						onComparable(CollectedFunctions functions, Function<?, ? extends Comparable<?>> getter) {
		
				return named(functions, getter, supplier);
			}
		
			@Override
			public ISharedFunction_Next<MODEL, RESULT, IF> onString(
					CollectedFunctions functions, StringFunction<?> getter) {
		
				return named(functions, getter, supplier);
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

	static <MODEL, RESULT> ISharedCollector_Functions_Callback_Named<MODEL, RESULT, IShortResult_Mapped_Single_Named<MODEL, RESULT>>
	
		singleNamedCallback(Supplier<Short_Collector_SingleResult_Decided_Named<MODEL, RESULT>> supplier) {
		
		return namedCallback(supplier);
	}
	
	static <MODEL, RESULT> ISharedCollector_Functions_Callback_Alias<MODEL, RESULT, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>
		singleAliasCallback(Supplier<Short_Collector_SingleResult_Decided_Alias<MODEL, RESULT>> supplier) {
		
		return aliasCallback(supplier);
	}
	
	static <MODEL, RESULT> ISharedCollector_Functions_Callback_Named<MODEL, RESULT, IShortResult_Mapped_Multi_Named<MODEL, RESULT>>
		multiNamedCallback(Supplier<Short_Collector_MultiResult_Decided_Named<MODEL, RESULT>> supplier) {
	
		return namedCallback(supplier);
	}

	static <MODEL, RESULT> ISharedCollector_Functions_Callback_Alias<MODEL, RESULT, IShortResult_Mapped_Multi_Alias<MODEL, RESULT>>
		multiAliasCallback(Supplier<Short_Collector_MultiResult_Decided_Alias<MODEL, RESULT>> supplier) {
	
		return aliasCallback(supplier);
	}
}
