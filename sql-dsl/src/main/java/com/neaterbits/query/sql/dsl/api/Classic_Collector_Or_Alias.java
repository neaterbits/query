package com.neaterbits.query.sql.dsl.api;

import java.util.function.Consumer;
import java.util.function.Supplier;

final class Classic_Collector_Or_Alias<MODEL, RESULT>
		extends Classic_Collector_Or<MODEL, RESULT, Classic_Collector_And_Alias<MODEL, RESULT>, Classic_Collector_Or_Alias<MODEL, RESULT>>


		implements IClassicLogical_Or_Alias<MODEL, RESULT> {

	Classic_Collector_Or_Alias(BaseQueryEntity<MODEL> qe) {
		super(qe);
	}

	Classic_Collector_Or_Alias(Classic_Collector_WhereOrJoin_Alias_Base<MODEL, RESULT, ?> last) {
		super(last);
	}

	private <RR extends Comparable<RR>> ISharedCondition_Comparable_Common_All<MODEL, RESULT, RR, IClassicLogical_Or_Alias<MODEL, RESULT>> orAliasImplComparable(Supplier<RR> getter) {
		
		return orAliasImplComparable(null, getter);
	}

	private <RR extends Comparable<RR>> ISharedCondition_Comparable_Common_All<MODEL, RESULT, RR, IClassicLogical_Or_Alias<MODEL, RESULT>> orAliasImplComparable(CollectedFunctions functions, Supplier<RR> getter) {
		
		
		return new Collector_Condition_Comparative<MODEL, RESULT, RR, IClassicLogical_Or_Alias<MODEL, RESULT>>(this, functions, makeGetter(getter));
	}
	
	private ISharedCondition_Comparable_String_All<MODEL, RESULT, IClassicLogical_Or_Alias<MODEL, RESULT>>
		orAliasImplString(CollectedFunctions functions, ISupplierString getter) {

		return new Collector_Condition_String<MODEL, RESULT, IClassicLogical_Or_Alias<MODEL, RESULT>>(this, functions, makeGetter(getter));
	}

	@Override
	public ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, IClassicLogical_Or_Alias<MODEL, RESULT>> or(ISupplierInteger getter) {
		return orAliasImplComparable(getter);
	}

	@Override
	public ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, IClassicLogical_Or_Alias<MODEL, RESULT>> or(ISupplierLong getter) {
		return orAliasImplComparable(getter);
	}

	@Override
	public ISharedCondition_Comparable_String_All<MODEL, RESULT, IClassicLogical_Or_Alias<MODEL, RESULT>> or(ISupplierString getter) {
		
		return orAliasImplString(null, getter);
	}

	private final <T extends ISharedLogical_And<MODEL, RESULT>>
		
		void addNestedAndImpl(Consumer<T> orBuilder) {
		
		super.addNestedAndImpl(orBuilder, new Classic_Collector_And_Alias<MODEL, RESULT>(this));
	}

	@Override
	public IClassicLogical_Or_Alias<MODEL, RESULT> orNest(
			ISharedNestedAndConsumerAlias<MODEL, RESULT, IClassicLogical_And_Alias<MODEL, RESULT>> orBuilder) {

		addNestedAndImpl(orBuilder);

		return this;
	}

	@Override
	public ISharedFunctions_Alias_Initial<
			MODEL,
			RESULT,
			IClassicLogical_Or_Alias<MODEL, RESULT>,
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, IClassicLogical_Or_Alias<MODEL, RESULT>>,
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, IClassicLogical_Or_Alias<MODEL, RESULT>>,
			ISharedCondition_Comparable_String_All<MODEL, RESULT, IClassicLogical_Or_Alias<MODEL, RESULT>>> or() {

		@SuppressWarnings({ "unchecked", "rawtypes" })
		final ISharedCollector_Functions_Callback_Alias<MODEL, RESULT, IClassicLogical_Or_Alias<MODEL, RESULT>> cb = new ISharedCollector_Functions_Callback_Alias<MODEL, RESULT, IClassicLogical_Or_Alias<MODEL, RESULT>>() {

			@Override
			public ISharedCondition_Comparable_Common_All<MODEL, RESULT, Comparable<?>, IClassicLogical_Or_Alias<MODEL, RESULT>> onComparable(CollectedFunctions functions, Supplier getter) {

				return orAliasImplComparable(functions, (Supplier)getter);
			}

			@Override
			public ISharedCondition_Comparable_String_Base<MODEL, RESULT, IClassicLogical_Or_Alias<MODEL, RESULT>> onString(CollectedFunctions functions, ISupplierString getter) {
				return orAliasImplString(functions, getter);
			}
		};

		return new Collector_SharedFunctions_Alias<>(cb);
	}

}
