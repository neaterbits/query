package com.neaterbits.query.sql.dsl.api;

import java.util.function.Consumer;
import java.util.function.Supplier;

final class Classic_Collector_And_Alias<MODEL, RESULT> extends Classic_Collector_And<MODEL, RESULT, Classic_Collector_And_Alias<MODEL, RESULT>, Classic_Collector_Or_Alias<MODEL, RESULT>> 
		implements IClassicAndClausesAlias<MODEL, RESULT>{

	
	public Classic_Collector_And_Alias(BaseQueryEntity<MODEL> qe) {
		super(qe);
	}

	public Classic_Collector_And_Alias(Classic_Collector_Where_Or_Join_Alias<MODEL, RESULT> last) {
		super(last);
	}

	final <T extends ISharedLogical_Or<MODEL, RESULT>> void addNestedOrImpl(Consumer<T> orBuilder) {
		super.addNestedOrImpl(orBuilder, new Classic_Collector_Or_Alias<MODEL, RESULT>(this));
	}
	
	private <RR extends Comparable<RR>,
			AND_CLAUSES extends ISharedLogical_And_Alias_Base<MODEL, RESULT, AND_CLAUSES, NESTED_OR_CLAUSES>,
			NESTED_OR_CLAUSES extends ISharedLogical_Or_Alias<MODEL, RESULT>>
		
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, RR, AND_CLAUSES> andAliasImplComparable(Supplier<RR> getter) {
		
		return andAliasImplComparable(null, getter);
	}

	private <RR extends Comparable<RR>,
		AND_CLAUSES extends ISharedLogical_And_Alias_Base<MODEL, RESULT, AND_CLAUSES, NESTED_OR_CLAUSES>,
		NESTED_OR_CLAUSES extends ISharedLogical_Or_Alias<MODEL, RESULT>>
		
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, RR, AND_CLAUSES> andAliasImplComparable(CollectedFunctions functions, Supplier<RR> getter) {
	
			return new Collector_Condition_Comparative<MODEL, RESULT, RR, AND_CLAUSES>(this, functions, makeGetter(getter));
	}

	private ISharedCondition_Comparable_String_All<MODEL, RESULT, IClassicAndClausesAlias<MODEL, RESULT>>
		andAliasImplString(CollectedFunctions functions, ISupplierString getter) {
		
		return new Collector_Condition_String<MODEL, RESULT, IClassicAndClausesAlias<MODEL,RESULT>>(this, functions, makeGetter(getter));
	}
		
	@Override
	public ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, IClassicAndClausesAlias<MODEL, RESULT>> and(ISupplierInteger getter) {
		return andAliasImplComparable(getter);
	}

	@Override
	public ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, IClassicAndClausesAlias<MODEL, RESULT>> and(ISupplierLong getter) {
		return andAliasImplComparable(getter);
	}

	@Override
	public ISharedCondition_Comparable_String_All<MODEL, RESULT, IClassicAndClausesAlias<MODEL, RESULT>> and(ISupplierString getter) {
		return andAliasImplString(null, getter);
	}


	@Override
	public IClassicAndClausesAlias<MODEL, RESULT> andNest(ISharedNestedOrConsumerAlias<MODEL, RESULT, IClassicOrClausesAlias<MODEL, RESULT>> orBuilder) {
	
		addNestedOrImpl(orBuilder);
	
		return this;
	}
	
	@Override
	public ISharedFunctions_Alias_Initial<
				MODEL,
				RESULT,
				IClassicAndClausesAlias<MODEL, RESULT>,
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, IClassicAndClausesAlias<MODEL, RESULT>>,
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, IClassicAndClausesAlias<MODEL, RESULT>>,
				ISharedCondition_Comparable_String_All<MODEL, RESULT, IClassicAndClausesAlias<MODEL, RESULT>>> and() {

		@SuppressWarnings({"unchecked", "rawtypes"})
		final ISharedCollector_Functions_Callback_Alias<MODEL, RESULT, IClassicAndClausesAlias<MODEL, RESULT>> cb
				= new ISharedCollector_Functions_Callback_Alias<MODEL, RESULT, IClassicAndClausesAlias<MODEL, RESULT>>() {

			@Override
			public ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Comparable<?>, IClassicAndClausesAlias<MODEL, RESULT>>
				onComparable(CollectedFunctions functions, Supplier getter) {
				
				return andAliasImplComparable(functions, (Supplier)getter);
			}

			@Override
			public ISharedCondition_Comparable_String_Base<MODEL, RESULT, IClassicAndClausesAlias<MODEL, RESULT>>
				onString(CollectedFunctions functions, ISupplierString getter) {
				
				return andAliasImplString(functions, getter);
			}
		};

		return new Collector_SharedFunctions_Alias<>(cb);
	}
}
