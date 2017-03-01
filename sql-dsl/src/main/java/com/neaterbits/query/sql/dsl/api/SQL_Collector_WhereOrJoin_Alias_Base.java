package com.neaterbits.query.sql.dsl.api;

import java.util.function.Supplier;

abstract class SQL_Collector_WhereOrJoin_Alias_Base<

				MODEL,
				RESULT,
				JOIN_CONDITION extends ISQLJoin_Condition_Alias_Base<MODEL, RESULT, JOIN_CONDITION>,
				
				AND_CLAUSES extends ISharedLogical_And_Alias_Base<MODEL, RESULT, AND_CLAUSES, ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>>,
				OR_CLAUSES  extends ISharedLogical_Or_Alias_Base <MODEL, RESULT, OR_CLAUSES,  ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>>,

				AND_OR extends ISharedLogical_And_Or_Alias<
							MODEL,
							RESULT,
							AND_CLAUSES,
							OR_CLAUSES,
							ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>,
							ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>>>

	extends Collector_And_Or_Alias<
			MODEL,
			RESULT,
			AND_CLAUSES,
			OR_CLAUSES,
			// nested
			ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>,
			ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>,
			
			ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>>


	implements 
		   ISQLLogical_WhereOrJoin_Alias_Base<MODEL, RESULT>,
		   ISQLJoin_Alias<MODEL, RESULT, JOIN_CONDITION> {
			   
    
    /*

    abstract Classic_Collector_And_Alias<MODEL, RESULT, IClassicLogical_And_NonProcessResult_Alias<MODEL, RESULT>>
    				createNestedAnd(Classic_Collector_Or_Alias<MODEL, RESULT, OR_CLAUSES> orClauses); 
    
    
    abstract Classic_Collector_Or_Alias<MODEL, RESULT, IClassicLogical_Or_NonProcessResult_Alias<MODEL, RESULT>>
    				createNestedOr(Classic_Collector_And_Alias<MODEL, RESULT, AND_CLAUSES> andClauses);
	*/ 
			   
	SQL_Collector_WhereOrJoin_Alias_Base(Collector_Base<MODEL> last) {
		super(last, EConditionsClause.WHERE);
	}

// ------------------------  JOIN ------------------------


	private Collector_Joins addJoin(CollectedJoin collectedJoin) {

		Collector_Joins joinCollector = getQueryCollector().getJoins();
		
		if (joinCollector == null) {
			joinCollector = new Collector_Joins();
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
				
				return new Collector_Condition_String<MODEL, RESULT, AND_OR> (SQL_Collector_WhereOrJoin_Alias_Base.this, functions, makeGetter(getter));
			}
		};
	
		return new Collector_SharedFunctions_Alias<>(cb);
	}

	@Override
	final Collector_Or_Alias<
			MODEL,
			RESULT,
			ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>,
			ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>,
			ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>,
			
			ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>>
	
		createNestedOrCollector(
			Collector_And_Alias<
				MODEL,
				RESULT,
				AND_CLAUSES,
				ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>,
				ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>,
					
				ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>> andClauses) {
		
		return new SQL_Collector_Or_NonProcessResult_Alias<>(andClauses);
	}

	@Override
	final Collector_And_Alias<
			MODEL,
			RESULT,
			ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>,
			ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>,
			ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>,
			ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>>
	
		createNestedAndCollector(
			Collector_Or_Alias<
				MODEL,
				RESULT,
				OR_CLAUSES,
				ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>,
				ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>,
				ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>> orClauses) {

		return new SQL_Collector_And_NonProcessResult_Alias<>(orClauses);
	}
}

