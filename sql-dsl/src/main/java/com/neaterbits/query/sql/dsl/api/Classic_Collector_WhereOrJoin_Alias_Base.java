package com.neaterbits.query.sql.dsl.api;

import java.util.function.Consumer;
import java.util.function.Supplier;

abstract class Classic_Collector_WhereOrJoin_Alias_Base<

				MODEL,
				RESULT,
				JOIN_CONDITION extends IClassicJoin_Condition_Alias_Base<MODEL, RESULT, JOIN_CONDITION>,
				
				AND_CLAUSES extends ISharedLogical_And_Alias_Base<MODEL, RESULT, AND_CLAUSES, IClassicLogical_Or_NonProcessResult_Alias<MODEL, RESULT>>,
				OR_CLAUSES  extends ISharedLogical_Or_Alias_Base <MODEL, RESULT, OR_CLAUSES,  IClassicLogical_And_NonProcessResult_Alias<MODEL, RESULT>>,

				AND_OR extends ISharedLogical_And_Or_Alias<
							MODEL,
							RESULT,
							AND_CLAUSES,
							OR_CLAUSES,
							IClassicLogical_And_NonProcessResult_Alias<MODEL, RESULT>,
							IClassicLogical_Or_NonProcessResult_Alias<MODEL, RESULT>>>

	extends Classic_Collector_Where_Or_Join<MODEL, RESULT, ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>>


	implements 
		   IClassicLogical_WhereOrJoin_Alias_Base<MODEL, RESULT>,
		   IClassicJoin_Alias<MODEL, RESULT, JOIN_CONDITION>,
		   ISharedLogical_And_Or_Alias<MODEL, RESULT, AND_CLAUSES, OR_CLAUSES, IClassicLogical_And_NonProcessResult_Alias<MODEL, RESULT>, IClassicLogical_Or_NonProcessResult_Alias<MODEL, RESULT>> {

			   
    abstract Classic_Collector_Or_Alias<MODEL, RESULT, OR_CLAUSES> createOrCollector();
    abstract Classic_Collector_And_Alias<MODEL, RESULT, AND_CLAUSES> createAndCollector();
    
    /*

    abstract Classic_Collector_And_Alias<MODEL, RESULT, IClassicLogical_And_NonProcessResult_Alias<MODEL, RESULT>>
    				createNestedAnd(Classic_Collector_Or_Alias<MODEL, RESULT, OR_CLAUSES> orClauses); 
    
    
    abstract Classic_Collector_Or_Alias<MODEL, RESULT, IClassicLogical_Or_NonProcessResult_Alias<MODEL, RESULT>>
    				createNestedOr(Classic_Collector_And_Alias<MODEL, RESULT, AND_CLAUSES> andClauses);
	*/ 
			   
	Classic_Collector_WhereOrJoin_Alias_Base(BaseQueryEntity<MODEL> last) {
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
	
	@SuppressWarnings("unchecked")
	private <LEFT, RIGHT> JOIN_CONDITION getJoinConditionAlias() {
		return (JOIN_CONDITION)this;
	}
	
	
	@Override
	public final JOIN_CONDITION innerJoin(Object left, Object right) {
	
		final CollectedJoin_Alias collectedJoin = new CollectedJoin_Alias(EJoinType.INNER, (IAlias)left, (IAlias)right);
		
		addJoin(collectedJoin);
		
		return getJoinConditionAlias();
	}
	
	@Override
	public final JOIN_CONDITION leftJoin(Object left, Object right) {
	
		final CollectedJoin_Alias collectedJoin = new CollectedJoin_Alias(EJoinType.LEFT, (IAlias)left, (IAlias)right);
		
		addJoin(collectedJoin);
		
		return getJoinConditionAlias();
	}
	
	private <R> JOIN_CONDITION compareAlias(Supplier<R> left, Supplier<R> right) {
		
		final SupplierGetter leftGetter = new SupplierGetter(left); 
		final SupplierGetter rightGetter = new SupplierGetter(right); 
		
		final CollectedJoin curJoin = getQueryCollector().getJoins().getLast();
		
		final CollectedJoinCondition joinCondition = new CollectedJoinCondition_Comparison_Alias(leftGetter, rightGetter);
		
		curJoin.addJoinCondition(joinCondition);
		
		return getJoinConditionAlias();
	}
	
	// JoinCondition, markes as implemented in subclass
	public final JOIN_CONDITION on(ISupplierCollection joinCollection) {
		final SupplierGetter collectionGetter = new SupplierGetter(joinCollection); 
		
		final CollectedJoin curJoin = getQueryCollector().getJoins().getLast();
		
		final CollectedJoinCondition joinCondition = new CollectedJoinCondition_OneToMany_Alias(collectionGetter);
		
		curJoin.addJoinCondition(joinCondition);
		
		return getJoinConditionAlias();
	}
	
	public final JOIN_CONDITION compare(ISupplierInteger left, ISupplierInteger right) {
		return compareAlias(left, right);
	}

	// JoinCondition, markes as implemented in subclass
	public final JOIN_CONDITION compare(ISupplierLong left, ISupplierLong right) {
		return compareAlias(left, right);
	}

	// ------------------------  WHERE ------------------------
	
	//implemented in subclass @Override
	public final ISharedCondition_Comparable_Common_All<
				MODEL,
				RESULT,
				Integer,
				AND_OR> where(ISupplierInteger func) {
	
		return new Collector_Condition_Comparative<MODEL, RESULT, Integer, AND_OR>(this, makeGetter(func));
	}	
	
	// implemented in subclass @Override
	public final ISharedCondition_Comparable_String_All<
				MODEL,
				RESULT,
				AND_OR> where(ISupplierString supplier) {
	
		return new Collector_Condition_String<MODEL, RESULT, AND_OR>(this, makeGetter(supplier));
	}


	// JoinCondition, marks this as implemented in subclass by implementing matching interface there
	public final ISharedFunctions_Alias_Initial<
			MODEL, RESULT,
			AND_OR,
			
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, AND_OR>,
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, AND_OR>,
			ISharedCondition_Comparable_String_All<MODEL, RESULT, AND_OR>
	> 
	
			where() {
		
		@SuppressWarnings({"unchecked", "rawtypes"})
		final ISharedCollector_Functions_Callback_Alias<MODEL, RESULT, AND_OR> cb
				= new ISharedCollector_Functions_Callback_Alias<MODEL, RESULT, AND_OR>() {
		
			@Override
			public ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Comparable<?>, AND_OR>
				onComparable(CollectedFunctions functions, Supplier<? extends Comparable<?>> getter) {
				
				return andAliasImplComparable(functions, (Supplier)getter);
			}
		
			@Override
			public ISharedCondition_Comparable_String_Base<MODEL, RESULT, AND_OR>
				onString(CollectedFunctions functions, ISupplierString getter) {
				
				return new Collector_Condition_String<MODEL, RESULT, AND_OR> (Classic_Collector_WhereOrJoin_Alias_Base.this, functions, makeGetter(getter));
			}
		};
	
		return new Collector_SharedFunctions_Alias<>(cb);
	}

	// ------------------------  AND ------------------------
	
	
	@Override
	public final ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, AND_CLAUSES> and(ISupplierInteger getter) {
		return andAliasImplComparable(getter);
	}
	
	@Override
	public final ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, AND_CLAUSES> and(ISupplierLong getter) {
		return andAliasImplComparable(getter);
	}
	
	@Override
	public final ISharedCondition_Comparable_String_All<MODEL, RESULT, AND_CLAUSES> and(ISupplierString getter) {
	
		final Classic_Collector_And_Alias<MODEL, RESULT, AND_CLAUSES> andClauses = createAndCollector();// new Classic_Collector_And_Alias<>(this);
		
		return new Collector_Condition_String<MODEL, RESULT, AND_CLAUSES>(andClauses, makeGetter(getter));
	}

	
	@Override
	public final ISharedFunctions_Alias_Initial<
		MODEL,
		RESULT,
		AND_CLAUSES,
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, AND_CLAUSES>,
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, AND_CLAUSES>,
		ISharedCondition_Comparable_String_All<MODEL, RESULT, AND_CLAUSES>
	>
		and() {
	
		@SuppressWarnings({"unchecked", "rawtypes"})
		final ISharedCollector_Functions_Callback_Alias<MODEL, RESULT, AND_CLAUSES> cb
				= new ISharedCollector_Functions_Callback_Alias<MODEL, RESULT, AND_CLAUSES>() {
		
			@Override
			public ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Comparable<?>, AND_CLAUSES>
				onComparable(CollectedFunctions functions, Supplier<? extends Comparable<?>> getter) {
				
				return andAliasImplComparable(functions, (Supplier)getter);
			}
		
			@Override
			public ISharedCondition_Comparable_String_All<MODEL, RESULT, AND_CLAUSES>
				onString(CollectedFunctions functions, ISupplierString getter) {
				
				return andAliasImplString(functions, getter);
			}
		};
	
		return new Collector_SharedFunctions_Alias<>(cb);
	}

	// ------------------------  OR ------------------------


	@Override
	public final ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, OR_CLAUSES> or(ISupplierInteger getter) {
		return orAliasImplComparable(getter);
	}

	@Override
	public final ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, OR_CLAUSES> or(ISupplierLong getter) {
		return orAliasImplComparable(getter);
	}
	
	@Override
	public final ISharedCondition_Comparable_String_All<MODEL, RESULT, OR_CLAUSES> or(ISupplierString getter) {
	
		return orAliasImplString(null, getter);
	}
	
	@Override
	public final ISharedFunctions_Alias_Initial<
		MODEL,
		RESULT,
		OR_CLAUSES,
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, OR_CLAUSES>,
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, OR_CLAUSES>,
		ISharedCondition_Comparable_String_All<MODEL, RESULT, OR_CLAUSES>
	>
		or() {
	
		@SuppressWarnings({"unchecked", "rawtypes"})
		final ISharedCollector_Functions_Callback_Alias<MODEL, RESULT, OR_CLAUSES> cb
				= new ISharedCollector_Functions_Callback_Alias<MODEL, RESULT, OR_CLAUSES>() {
		
			@Override
			public ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Comparable<?>, OR_CLAUSES>
				onComparable(CollectedFunctions functions, Supplier<? extends Comparable<?>> getter) {
				
				return orAliasImplComparable(functions, (Supplier)getter);
			}

			@Override
			public ISharedCondition_Comparable_String_Base<MODEL, RESULT, OR_CLAUSES> 
				onString(CollectedFunctions functions, ISupplierString getter) {

				return orAliasImplString(functions, getter);
			}
		};

		return new Collector_SharedFunctions_Alias<>(cb);
	}
	// ------------------------  AND helpers ------------------------
	
	private <RR extends Comparable<RR>>
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, RR, AND_CLAUSES> andAliasImplComparable(Supplier<RR> getter) {
		
		return andAliasImplComparable(null, getter);
	}
	
	private <RR extends Comparable<RR>>
		
	ISharedCondition_Comparable_Common_All<MODEL, RESULT, RR, AND_CLAUSES> andAliasImplComparable(CollectedFunctions functions, Supplier<RR> getter) {
	
		final Classic_Collector_And_Alias<MODEL, RESULT, AND_CLAUSES> andClauses = createAndCollector();// new Classic_Collector_And_Alias<MODEL, RESULT>(this);
	
		return new Collector_Condition_Comparative<MODEL, RESULT, RR, AND_CLAUSES>(andClauses, functions, makeGetter(getter));
	}
		
		
	private 
	
		ISharedCondition_Comparable_String_All<MODEL, RESULT, AND_CLAUSES>
			andAliasImplString(CollectedFunctions functions, ISupplierString getter) {
		
		final Classic_Collector_And_Alias<MODEL, RESULT, AND_CLAUSES> andClauses = createAndCollector(); // new Classic_Collector_And_Alias<>(this);
		
		return new Collector_Condition_String<MODEL, RESULT, AND_CLAUSES>(andClauses, functions, makeGetter(getter));
	}
	
	// ------------------------  OR helpers ------------------------
	private <RR extends Comparable<RR>>
	
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, RR, OR_CLAUSES> orAliasImplComparable(Supplier<RR> getter) {
		
		return orAliasImplComparable(null, getter);
	}
	
	private <T, RR extends Comparable<RR>>
	
	ISharedCondition_Comparable_Common_All<MODEL, RESULT, RR, OR_CLAUSES> orAliasImplComparable(CollectedFunctions functions, Supplier<RR> getter) {
	
		final Classic_Collector_Or_Alias<MODEL, RESULT, OR_CLAUSES> orClauses = createOrCollector(); // new Classic_Collector_Or_Alias<>(this);
	
		return new Collector_Condition_Comparative<MODEL, RESULT, RR, OR_CLAUSES>(orClauses, functions, makeGetter(getter));
	}
	
	private <
		M_OR_CLAUSES 		   extends ISharedLogical_Or_Alias_Base <MODEL, RESULT, M_OR_CLAUSES, 	    NESTED_AND_CLAUSES>,
		NESTED_AND_CLAUSES extends ISharedLogical_And_Alias_Base<MODEL, RESULT, NESTED_AND_CLAUSES, M_OR_CLAUSES>>
	
	ISharedCondition_Comparable_String_All<MODEL, RESULT, OR_CLAUSES> orAliasImplString(CollectedFunctions functions, ISupplierString getter) {
	
		final Classic_Collector_Or_Alias<MODEL, RESULT, OR_CLAUSES> orClauses = createOrCollector(); // new Classic_Collector_Or_Alias<>(this);
	
		return new Collector_Condition_String<MODEL, RESULT, OR_CLAUSES>(orClauses, functions, makeGetter(getter));
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
		Classic_Collector_Or_Alias<MODEL, RESULT, OR_CLAUSES>
		
			addNestedAndImpl(Consumer<T> orBuilder) {
	
		final Classic_Collector_Or_Alias<
				MODEL,
				RESULT,
				OR_CLAUSES> orClauses
					
				
				= createOrCollector(); // new Classic_Collector_Or_Alias<>(this);
		
		// Add to new OR
		orClauses.addNestedAndImpl(orBuilder, new Classic_Collector_And_NonProcessResult_Alias<>(orClauses));
		
		
		return orClauses;
	}

	private final <T extends ISharedLogical_Or<MODEL, RESULT>>
		
		Classic_Collector_And_Alias<MODEL, RESULT, AND_CLAUSES>
			addNestedOrImpl(Consumer<T> orBuilder) {
	
		final Classic_Collector_And_Alias<MODEL, RESULT, AND_CLAUSES> andClauses = createAndCollector(); // new Classic_Collector_And_Alias<>(this);
	
		// Add to new AND clause
		andClauses.addNestedOrImpl(orBuilder, new Classic_Collector_Or_NonProcessResult_Alias<>(andClauses));
		
		return andClauses;
	}
	
	
	@Override
	@SuppressWarnings("unchecked")
	public final AND_CLAUSES andNest(ISharedNestedOrConsumerAlias<MODEL, RESULT, IClassicLogical_Or_NonProcessResult_Alias<MODEL, RESULT>> orBuilder) {
		return (AND_CLAUSES)addNestedOrImpl(orBuilder);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public final OR_CLAUSES orNest(ISharedNestedAndConsumerAlias<MODEL, RESULT, IClassicLogical_And_NonProcessResult_Alias<MODEL, RESULT>> andBuilder) {
		return (OR_CLAUSES)addNestedAndImpl(andBuilder);
	}
}

