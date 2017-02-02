package com.neaterbits.query.sql.dsl.api;

import java.util.function.Consumer;
import java.util.function.Supplier;

final class Classic_Collector_Or_Alias<MODEL, RESULT>
		extends Classic_Collector_Or<MODEL, RESULT, Classic_Collector_And_Alias<MODEL, RESULT>, Classic_Collector_Or_Alias<MODEL, RESULT>>


		implements IClassicOrClausesAlias<MODEL, RESULT> {

	Classic_Collector_Or_Alias(BaseQueryEntity<MODEL> qe) {
		super(qe);
	}

	Classic_Collector_Or_Alias(Classic_Collector_Where_Or_Join_Alias<MODEL, RESULT> last) {
		super(last);
	}

	private <RR extends Comparable<RR>> ISharedCondition_Comparable_Common_All<MODEL, RESULT, RR, IClassicOrClausesAlias<MODEL, RESULT>> orAliasImplComparable(Supplier<RR> getter) {
		
		return orAliasImplComparable(null, getter);
	}

	private <RR extends Comparable<RR>> ISharedCondition_Comparable_Common_All<MODEL, RESULT, RR, IClassicOrClausesAlias<MODEL, RESULT>> orAliasImplComparable(CollectedFunctions functions, Supplier<RR> getter) {
		
		
		return new Collector_Condition_Comparative<MODEL, RESULT, RR, IClassicOrClausesAlias<MODEL, RESULT>>(this, functions, makeGetter(getter));
	}
	
	private ISharedCondition_Comparable_String_All<MODEL, RESULT, IClassicOrClausesAlias<MODEL, RESULT>>
		orAliasImplString(CollectedFunctions functions, ISupplierString getter) {

		return new Collector_Condition_String<MODEL, RESULT, IClassicOrClausesAlias<MODEL, RESULT>>(this, functions, makeGetter(getter));
	}

	@Override
	public ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, IClassicOrClausesAlias<MODEL, RESULT>> or(ISupplierInteger getter) {
		return orAliasImplComparable(getter);
	}

	@Override
	public ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, IClassicOrClausesAlias<MODEL, RESULT>> or(ISupplierLong getter) {
		return orAliasImplComparable(getter);
	}

	@Override
	public ISharedCondition_Comparable_String_All<MODEL, RESULT, IClassicOrClausesAlias<MODEL, RESULT>> or(ISupplierString getter) {
		
		return orAliasImplString(null, getter);
	}

	private final <T extends ISharedLogical_And<MODEL, RESULT>>
		
		void addNestedAndImpl(Consumer<T> orBuilder) {
		
		super.addNestedAndImpl(orBuilder, new Classic_Collector_And_Alias<MODEL, RESULT>(this));
	}

	@Override
	public IClassicOrClausesAlias<MODEL, RESULT> orNest(
			ISharedNestedAndConsumerAlias<MODEL, RESULT, IClassicAndClausesAlias<MODEL, RESULT>> orBuilder) {

		addNestedAndImpl(orBuilder);

		return this;
	}

	@Override
	public ISharedFunctions_Alias_Initial<
			MODEL,
			RESULT,
			IClassicOrClausesAlias<MODEL, RESULT>,
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, IClassicOrClausesAlias<MODEL, RESULT>>,
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, IClassicOrClausesAlias<MODEL, RESULT>>,
			ISharedCondition_Comparable_String_All<MODEL, RESULT, IClassicOrClausesAlias<MODEL, RESULT>>> or() {

		@SuppressWarnings({ "unchecked", "rawtypes" })
		final ISharedCollector_Functions_Callback_Alias<MODEL, RESULT, IClassicOrClausesAlias<MODEL, RESULT>> cb = new ISharedCollector_Functions_Callback_Alias<MODEL, RESULT, IClassicOrClausesAlias<MODEL, RESULT>>() {

			@Override
			public ISharedCondition_Comparable_Common_All<MODEL, RESULT, Comparable<?>, IClassicOrClausesAlias<MODEL, RESULT>> onComparable(CollectedFunctions functions, Supplier getter) {

				return orAliasImplComparable(functions, (Supplier)getter);
			}

			@Override
			public ISharedCondition_Comparable_String_Base<MODEL, RESULT, IClassicOrClausesAlias<MODEL, RESULT>> onString(CollectedFunctions functions, ISupplierString getter) {
				return orAliasImplString(functions, getter);
			}
		};

		return new Collector_SharedFunctions_Alias<>(cb);
	}

}
