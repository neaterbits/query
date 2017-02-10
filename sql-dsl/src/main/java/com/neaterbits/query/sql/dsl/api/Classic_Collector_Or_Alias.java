package com.neaterbits.query.sql.dsl.api;

import java.util.function.Consumer;
import java.util.function.Supplier;

abstract class Classic_Collector_Or_Alias<
				MODEL,
				RESULT,
				OR_CLAUSES extends ISharedLogical_Or_Alias_Base<MODEL, RESULT, OR_CLAUSES, IClassicLogical_And_NonProcessResult_Alias<MODEL, RESULT>>>

		extends Classic_Collector_Or<MODEL, RESULT, ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>>

		implements ISharedLogical_Or_Alias_Base<MODEL, RESULT, OR_CLAUSES, IClassicLogical_And_NonProcessResult_Alias<MODEL, RESULT>> {

	Classic_Collector_Or_Alias(BaseQueryEntity<MODEL> qe) {
		super(qe);
	}

	Classic_Collector_Or_Alias(Classic_Collector_WhereOrJoin_Alias_Base<MODEL, RESULT, ?, ?, ?, ?> last) {
		super(last);
	}

	private <RR extends Comparable<RR>> ISharedCondition_Comparable_Common_All<MODEL, RESULT, RR, OR_CLAUSES> orAliasImplComparable(Supplier<RR> getter) {
		
		return orAliasImplComparable(null, getter);
	}

	private <RR extends Comparable<RR>> ISharedCondition_Comparable_Common_All<MODEL, RESULT, RR, OR_CLAUSES> orAliasImplComparable(CollectedFunctions functions, Supplier<RR> getter) {
		
		
		return new Collector_Condition_Comparative<MODEL, RESULT, RR, OR_CLAUSES>(this, functions, makeGetter(getter));
	}
	
	private ISharedCondition_Comparable_String_All<MODEL, RESULT, OR_CLAUSES>
		orAliasImplString(CollectedFunctions functions, ISupplierString getter) {

		return new Collector_Condition_String<MODEL, RESULT, OR_CLAUSES>(this, functions, makeGetter(getter));
	}

	@Override
	public ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, OR_CLAUSES> or(ISupplierInteger getter) {
		return orAliasImplComparable(getter);
	}

	@Override
	public ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, OR_CLAUSES> or(ISupplierLong getter) {
		return orAliasImplComparable(getter);
	}

	@Override
	public ISharedCondition_Comparable_String_All<MODEL, RESULT, OR_CLAUSES> or(ISupplierString getter) {
		
		return orAliasImplString(null, getter);
	}

	private final <T extends ISharedLogical_And<MODEL, RESULT>>
		
		void addNestedAndImpl(Consumer<T> orBuilder) {
		
		super.addNestedAndImpl(orBuilder, new Classic_Collector_And_NonProcessResult_Alias<>(this));
	}

	@SuppressWarnings("unchecked")
	@Override
	public OR_CLAUSES orNest(
			ISharedNestedAndConsumerAlias<MODEL, RESULT, IClassicLogical_And_NonProcessResult_Alias<MODEL, RESULT>> orBuilder) {

		addNestedAndImpl(orBuilder);

		return (OR_CLAUSES)this;
	}

	@Override
	public ISharedFunctions_Alias_Initial<
			MODEL,
			RESULT,
			OR_CLAUSES,
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, OR_CLAUSES>,
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, OR_CLAUSES>,
			ISharedCondition_Comparable_String_All<MODEL, RESULT, OR_CLAUSES>> or() {

		@SuppressWarnings({ "unchecked", "rawtypes" })
		final ISharedCollector_Functions_Callback_Alias<MODEL, RESULT, OR_CLAUSES> cb = new ISharedCollector_Functions_Callback_Alias<MODEL, RESULT, OR_CLAUSES>() {

			@Override
			public ISharedCondition_Comparable_Common_All<MODEL, RESULT, Comparable<?>, OR_CLAUSES> onComparable(CollectedFunctions functions, Supplier getter) {

				return orAliasImplComparable(functions, (Supplier)getter);
			}

			@Override
			public ISharedCondition_Comparable_String_Base<MODEL, RESULT, OR_CLAUSES> onString(CollectedFunctions functions, ISupplierString getter) {
				return orAliasImplString(functions, getter);
			}
		};

		return new Collector_SharedFunctions_Alias<>(cb);
	}

}
