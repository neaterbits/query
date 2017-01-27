package com.neaterbits.query.sql.dsl.api;

import java.util.function.Consumer;
import java.util.function.Supplier;

final class Classic_Collector_Where_Or_Join_Alias<MODEL, RESULT>
	extends Classic_Collector_Where_Or_Join<MODEL, RESULT, Classic_Collector_And_Alias<MODEL, RESULT>, Classic_Collector_Or_Alias<MODEL, RESULT>>
	implements 
		   IClassicWhereOrJoinBuilderAlias<MODEL, RESULT>,
		   IClassicAndOrLogicalClausesAlias<MODEL, RESULT>,
		   
		   IClassicJoinConditionAlias<MODEL, RESULT> {

	Classic_Collector_Where_Or_Join_Alias(BaseQueryEntity<MODEL> last) {
		super(last);
	}

// ------------------------  JOIN ------------------------


	private JoinCollector addJoin(CollectedJoin collectedJoin) {

		JoinCollector joinCollector = getQueryCollector().getJoins();
		
		if (joinCollector == null) {
			joinCollector = new JoinCollector();
			getQueryCollector().setJoins(joinCollector);
		}
	
		joinCollector.addJoin(collectedJoin);
		
		return joinCollector;
	}


	// -- Alias  --
	
	private <LEFT, RIGHT> IClassicJoinConditionAlias<MODEL, RESULT> getJoinConditionAlias() {
		return (IClassicJoinConditionAlias<MODEL, RESULT>)this;
	}
	
	
	@Override
	public IClassicJoinConditionAlias<MODEL, RESULT> innerJoin(Object left, Object right) {
	
		final CollectedJoin_Alias collectedJoin = new CollectedJoin_Alias(EJoinType.INNER, (IAlias)left, (IAlias)right);
		
		addJoin(collectedJoin);
		
		return getJoinConditionAlias();
	}
	
	@Override
	public IClassicJoinConditionAlias<MODEL, RESULT> leftJoin(Object left, Object right) {
	
		final CollectedJoin_Alias collectedJoin = new CollectedJoin_Alias(EJoinType.LEFT, (IAlias)left, (IAlias)right);
		
		addJoin(collectedJoin);
		
		return getJoinConditionAlias();
	}
	
	private <R> IClassicJoinConditionAlias<MODEL, RESULT> compareAlias(Supplier<R> left, Supplier<R> right) {
		
		final SupplierGetter leftGetter = new SupplierGetter(left); 
		final SupplierGetter rightGetter = new SupplierGetter(right); 
		
		final CollectedJoin curJoin = getQueryCollector().getJoins().getLast();
		
		final CollectedJoinCondition joinCondition = new CollectedJoinCondition_Comparison_Alias(leftGetter, rightGetter);
		
		curJoin.addJoinCondition(joinCondition);
		
		return getJoinConditionAlias();
	}
	
	@Override
	public IClassicJoinConditionAlias<MODEL, RESULT> on(ISupplierCollection joinCollection) {
		final SupplierGetter collectionGetter = new SupplierGetter(joinCollection); 
		
		final CollectedJoin curJoin = getQueryCollector().getJoins().getLast();
		
		final CollectedJoinCondition joinCondition = new CollectedJoinCondition_OneToMany_Alias(collectionGetter);
		
		curJoin.addJoinCondition(joinCondition);
		
		return getJoinConditionAlias();
	}
	
	@Override
	public IClassicJoinConditionAlias<MODEL, RESULT> compare(ISupplierInteger left, ISupplierInteger right) {
		return compareAlias(left, right);
	}
	
	@Override
	public IClassicJoinConditionAlias<MODEL, RESULT> compare(ISupplierLong left, ISupplierLong right) {
		return compareAlias(left, right);
	}

	// ------------------------  WHERE ------------------------
	
	@Override
	public ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, IClassicAndOrLogicalClausesAlias<MODEL, RESULT>> where(ISupplierInteger func) {
	
		return new Collector_Condition_Comparative<MODEL, RESULT, Integer, IClassicAndOrLogicalClausesAlias<MODEL,RESULT>>(this, makeGetter(func));
	}	
	
	@Override
	public ISharedCondition_Comparable_String_All<MODEL, RESULT, IClassicAndOrLogicalClausesAlias<MODEL, RESULT>> where(ISupplierString supplier) {
	
		return new Collector_Condition_String<MODEL, RESULT, IClassicAndOrLogicalClausesAlias<MODEL,RESULT>>(this, makeGetter(supplier));
	}


	@Override
	public ISharedFunctions_Alias_Initial<
			MODEL, RESULT,
			IClassicAndOrLogicalClausesAlias<MODEL, RESULT>,
			
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, ? extends Comparable<?>, IClassicAndOrLogicalClausesAlias<MODEL, RESULT>>,
			ISharedCondition_Comparable_String_All<MODEL, RESULT, IClassicAndOrLogicalClausesAlias<MODEL, RESULT>>
	> 
	
			where() {
		
		@SuppressWarnings({"unchecked", "rawtypes"})
		final ISharedCollector_Functions_Callback_Alias<MODEL, RESULT, IClassicAndOrLogicalClausesAlias<MODEL, RESULT>> cb
				= new ISharedCollector_Functions_Callback_Alias<MODEL, RESULT, IClassicAndOrLogicalClausesAlias<MODEL, RESULT>>() {
		
			@Override
			public ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Comparable<?>, IClassicAndOrLogicalClausesAlias<MODEL, RESULT>>
				onComparable(CollectedFunctions functions, Supplier<? extends Comparable<?>> getter) {
				
				return andAliasImplComparable(functions, (Supplier)getter);
			}
		
			@Override
			public ISharedCondition_Comparable_String_Base<MODEL, RESULT, IClassicAndOrLogicalClausesAlias<MODEL, RESULT>>
				onString(CollectedFunctions functions, ISupplierString getter) {
				
				return new Collector_Condition_String<MODEL, RESULT, IClassicAndOrLogicalClausesAlias<MODEL, RESULT>> (Classic_Collector_Where_Or_Join_Alias.this, functions, makeGetter(getter));
			}
		};
	
		return new Collector_SharedFunctions_Alias<>(cb);
	}

	// ------------------------  AND ------------------------
	
	
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
	
		final Classic_Collector_And_Alias<MODEL, RESULT> andClauses = new Classic_Collector_And_Alias<>(this);
		
		return new Collector_Condition_String<MODEL, RESULT, IClassicAndClausesAlias<MODEL,RESULT>>(andClauses, makeGetter(getter));
	}


	@Override
	public ISharedFunctions_Alias_Initial<
		MODEL,
		RESULT,
		IClassicAndClausesAlias<MODEL, RESULT>,
		ISharedCondition_Comparable_Common_Base<MODEL, RESULT, ?, IClassicAndClausesAlias<MODEL, RESULT>>,
		ISharedCondition_Comparable_String_All<MODEL, RESULT, IClassicAndClausesAlias<MODEL, RESULT>>
	>
		and() {
	
		@SuppressWarnings({"unchecked", "rawtypes"})
		final ISharedCollector_Functions_Callback_Alias<MODEL, RESULT, IClassicAndClausesAlias<MODEL, RESULT>> cb
				= new ISharedCollector_Functions_Callback_Alias<MODEL, RESULT, IClassicAndClausesAlias<MODEL, RESULT>>() {
		
			@Override
			public ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Comparable<?>, IClassicAndClausesAlias<MODEL, RESULT>>
				onComparable(CollectedFunctions functions, Supplier<? extends Comparable<?>> getter) {
				
				return andAliasImplComparable(functions, (Supplier)getter);
			}
		
			@Override
			public ISharedCondition_Comparable_String_All<MODEL, RESULT, IClassicAndClausesAlias<MODEL, RESULT>>
				onString(CollectedFunctions functions, ISupplierString getter) {
				
				return andAliasImplString(functions, getter);
			}
		};
	
		return new Collector_SharedFunctions_Alias<>(cb);
	}

	// ------------------------  OR ------------------------


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
	
	@Override
	public ISharedFunctions_Alias_Initial<
		MODEL,
		RESULT,
		IClassicOrClausesAlias<MODEL, RESULT>,
		ISharedCondition_Comparable_Common_Base<MODEL, RESULT, ?, IClassicOrClausesAlias<MODEL, RESULT>>,
		ISharedCondition_Comparable_String_All<MODEL, RESULT, IClassicOrClausesAlias<MODEL, RESULT>>
	>
		or() {
	
		@SuppressWarnings({"unchecked", "rawtypes"})
		final ISharedCollector_Functions_Callback_Alias<MODEL, RESULT, IClassicOrClausesAlias<MODEL, RESULT>> cb
				= new ISharedCollector_Functions_Callback_Alias<MODEL, RESULT, IClassicOrClausesAlias<MODEL, RESULT>>() {
		
			@Override
			public ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Comparable<?>, IClassicOrClausesAlias<MODEL, RESULT>>
				onComparable(CollectedFunctions functions, Supplier<? extends Comparable<?>> getter) {
				
				return orAliasImplComparable(functions, (Supplier)getter);
			}
		
			@Override
			public ISharedCondition_Comparable_String_Base<MODEL, RESULT, IClassicOrClausesAlias<MODEL, RESULT>> 
				onString(CollectedFunctions functions, ISupplierString getter) {
				return orAliasImplString(functions, getter);
			}
		};
	
		return new Collector_SharedFunctions_Alias<>(cb);
	}
	// ------------------------  AND helpers ------------------------
	
	private <
			RR extends Comparable<RR>,
			AND_CLAUSES 	  extends ISharedLogical_And_Alias_Base<MODEL, RESULT, AND_CLAUSES, 	  NESTED_OR_CLAUSES>,
			NESTED_OR_CLAUSES extends ISharedLogical_Or_Alias_Base <MODEL, RESULT, NESTED_OR_CLAUSES, AND_CLAUSES>>
			
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, RR, AND_CLAUSES> andAliasImplComparable(Supplier<RR> getter) {
		
		return andAliasImplComparable(null, getter);
	}
	
	private <
			RR extends Comparable<RR>,
			AND_CLAUSES 	  extends ISharedLogical_And_Alias_Base<MODEL, RESULT, AND_CLAUSES, 		NESTED_OR_CLAUSES>,
			NESTED_OR_CLAUSES extends ISharedLogical_Or_Alias_Base <MODEL, RESULT, NESTED_OR_CLAUSES, 	AND_CLAUSES>>
		
	ISharedCondition_Comparable_Common_All<MODEL, RESULT, RR, AND_CLAUSES> andAliasImplComparable(CollectedFunctions functions, Supplier<RR> getter) {
	
		final Classic_Collector_And_Alias<MODEL, RESULT> andClauses = new Classic_Collector_And_Alias<MODEL, RESULT>(this);
	
		return new Collector_Condition_Comparative<MODEL, RESULT, RR, AND_CLAUSES>(andClauses, functions, makeGetter(getter));
	}
		
		
	private <AND_CLAUSES extends IClassicAndClausesAlias<MODEL, RESULT>>
	
		ISharedCondition_Comparable_String_All<MODEL, RESULT, AND_CLAUSES>
			andAliasImplString(CollectedFunctions functions, ISupplierString getter) {
		
		final Classic_Collector_And_Alias<MODEL, RESULT> andClauses = new Classic_Collector_And_Alias<>(this);
		
		return new Collector_Condition_String<MODEL, RESULT, AND_CLAUSES>(andClauses, functions, makeGetter(getter));
	}
	
	// ------------------------  OR helpers ------------------------
	private <RR extends Comparable<RR>,
			OR_CLAUSES 		   extends ISharedLogical_Or_Alias_Base <MODEL, RESULT, OR_CLAUSES, 	    NESTED_AND_CLAUSES>,
			NESTED_AND_CLAUSES extends ISharedLogical_And_Alias_Base<MODEL, RESULT, NESTED_AND_CLAUSES, OR_CLAUSES>>
	
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, RR, OR_CLAUSES> orAliasImplComparable(Supplier<RR> getter) {
		
		return orAliasImplComparable(null, getter);
	}
	
	private <T,
		RR extends Comparable<RR>,
		OR_CLAUSES 		   extends ISharedLogical_Or_Alias_Base<MODEL, RESULT, OR_CLAUSES, NESTED_AND_CLAUSES>,
		NESTED_AND_CLAUSES extends ISharedLogical_And_Alias_Base<MODEL, RESULT, NESTED_AND_CLAUSES, OR_CLAUSES>>
	
	ISharedCondition_Comparable_Common_All<MODEL, RESULT, RR, OR_CLAUSES> orAliasImplComparable(CollectedFunctions functions, Supplier<RR> getter) {
	
		final Classic_Collector_Or_Alias<MODEL, RESULT> orClauses = new Classic_Collector_Or_Alias<>(this);
	
		return new Collector_Condition_Comparative<MODEL, RESULT, RR, OR_CLAUSES>(orClauses, functions, makeGetter(getter));
	}
	
	private <
		OR_CLAUSES 		   extends ISharedLogical_Or_Alias_Base <MODEL, RESULT, OR_CLAUSES, 	    NESTED_AND_CLAUSES>,
		NESTED_AND_CLAUSES extends ISharedLogical_And_Alias_Base<MODEL, RESULT, NESTED_AND_CLAUSES, OR_CLAUSES>>
	
	ISharedCondition_Comparable_String_All<MODEL, RESULT, OR_CLAUSES> orAliasImplString(CollectedFunctions functions, ISupplierString getter) {
	
		final Classic_Collector_Or_Alias<MODEL, RESULT> andClauses = new Classic_Collector_Or_Alias<>(this);
	
		return new Collector_Condition_String<MODEL, RESULT, OR_CLAUSES>(andClauses, functions, makeGetter(getter));
	}
	
	
	/*
	private <RR extends Comparable<RR>,
			OR_CLAUSES extends ISharedLogical_Or_Alias_Base<MODEL, RESULT, OR_CLAUSES, NESTED_AND_CLAUSES>,
			NESTED_AND_CLAUSES extends ISharedLogical_And_Alias_Base<MODEL, RESULT, NESTED_AND_CLAUSES, OR_CLAUSES>>

		ISharedCondition_Equality_All<MODEL, RESULT, RR, OR_CLAUSES> orAliasImplComparable(Collector_Conditions_Initial<MODEL, RESULT> last, Supplier<RR> getter) {
	
		final Classic_Collector_Or_Named<MODEL, RESULT> orClauses = new Classic_Collector_Or_Named<>(last);
	
		return new Collector_Condition_Comparative<MODEL, RESULT, RR, OR_CLAUSES>(orClauses, makeGetter(getter));
	}
	*/
	

	// ------------------------  OR helpers ------------------------
	private final <T extends ISharedLogical_And<MODEL, RESULT>>
		Classic_Collector_Or_Alias<MODEL, RESULT> addNestedAndImpl(Consumer<T> orBuilder) {
	
		final Classic_Collector_Or_Alias<MODEL, RESULT> orClauses = new Classic_Collector_Or_Alias<>(this);
		
		// Add to new OR
		orClauses.addNestedAndImpl(orBuilder, new Classic_Collector_And_Alias<MODEL, RESULT>(orClauses));
		
		return orClauses;
	}

	private final <T extends ISharedLogical_Or<MODEL, RESULT>>
		Classic_Collector_And_Alias<MODEL, RESULT> addNestedOrImpl(Consumer<T> orBuilder) {
	
		final Classic_Collector_And_Alias<MODEL, RESULT> andClauses = new Classic_Collector_And_Alias<>(this);
	
		// Add to new AND clause
		andClauses.addNestedOrImpl(orBuilder, new Classic_Collector_Or_Alias<MODEL, RESULT>(andClauses));
		
		return andClauses;
	}
	
	
	@Override
	public IClassicAndClausesAlias<MODEL, RESULT> andNest(ISharedNestedOrConsumerAlias<MODEL, RESULT, IClassicOrClausesAlias<MODEL, RESULT>> orBuilder) {
		return addNestedOrImpl(orBuilder);
	}
	
	@Override
	public IClassicOrClausesAlias<MODEL, RESULT> orNest(ISharedNestedAndConsumerAlias<MODEL, RESULT, IClassicAndClausesAlias<MODEL, RESULT>> andBuilder) {
		return addNestedAndImpl(andBuilder);
	}
}

