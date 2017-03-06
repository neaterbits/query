package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;
import java.util.function.Supplier;

abstract class SQL_Collector_WhereOrJoin_Base<
	MODEL,
	RESULT,
	
	NAMED_AND_CLAUSES extends ISharedLogical_And_Named_All<MODEL, RESULT, NAMED_AND_CLAUSES, NAMED_NESTED_OR_CLAUSES>,
	NAMED_OR_CLAUSES  extends ISharedLogical_Or_Named_All <MODEL, RESULT, NAMED_OR_CLAUSES,  NAMED_NESTED_AND_CLAUSES>,

	NAMED_NESTED_AND_CLAUSES extends ISharedLogical_And_Named_All<MODEL, RESULT, NAMED_NESTED_AND_CLAUSES, NAMED_NESTED_OR_CLAUSES>,
	NAMED_NESTED_OR_CLAUSES  extends ISharedLogical_Or_Named_All<MODEL, RESULT, NAMED_NESTED_OR_CLAUSES,  NAMED_NESTED_AND_CLAUSES>,			

	NAMED_JOIN_CONDITION extends ISQLJoin_Condition_Named_Base<MODEL, RESULT, Object, Object, NAMED_JOIN_CONDITION>,

	NAMED_AND_OR extends ISharedLogical_And_Or_Named_All<
		MODEL,
		RESULT,
		NAMED_AND_CLAUSES,
		NAMED_OR_CLAUSES,
		NAMED_NESTED_AND_CLAUSES,
		NAMED_NESTED_OR_CLAUSES>,
		
	NAMED_AFTER_GROUP_BY,
		
	
	ALIAS_AND_CLAUSES extends ISharedLogical_And_Alias_Base<MODEL, RESULT, ALIAS_AND_CLAUSES, ALIAS_NESTED_OR_CLAUSES>,
	ALIAS_OR_CLAUSES  extends ISharedLogical_Or_Alias_Base <MODEL, RESULT, ALIAS_OR_CLAUSES,  ALIAS_NESTED_AND_CLAUSES>,

	ALIAS_NESTED_AND_CLAUSES extends ISharedLogical_And_Alias_Base<MODEL, RESULT, ALIAS_NESTED_AND_CLAUSES, ALIAS_NESTED_OR_CLAUSES>,
	ALIAS_NESTED_OR_CLAUSES  extends ISharedLogical_Or_Alias_Base <MODEL, RESULT, ALIAS_NESTED_OR_CLAUSES,  ALIAS_NESTED_AND_CLAUSES>,			

	ALIAS_JOIN_CONDITION extends ISQLJoin_Condition_Alias_Base<MODEL, RESULT, ALIAS_JOIN_CONDITION>,

	ALIAS_AND_OR extends ISharedLogical_And_Or_Alias<
		MODEL,
		RESULT,
		ALIAS_AND_CLAUSES,
		ALIAS_OR_CLAUSES,
		ALIAS_NESTED_AND_CLAUSES,
		ALIAS_NESTED_OR_CLAUSES>,
		
	ALIAS_AFTER_GROUP_BY,
	
	AFTER_GROUP_BY>
		
		
		
	extends Collector_And_Or_Named_And_Alias_Base<
			MODEL,
			RESULT,

			NAMED_AND_CLAUSES,
			NAMED_OR_CLAUSES,
			NAMED_NESTED_AND_CLAUSES,
			NAMED_NESTED_OR_CLAUSES,
			NAMED_AFTER_GROUP_BY,

			ALIAS_AND_CLAUSES,
			ALIAS_OR_CLAUSES,
			ALIAS_NESTED_AND_CLAUSES,
			ALIAS_NESTED_OR_CLAUSES,
			ALIAS_AFTER_GROUP_BY,
			
			AFTER_GROUP_BY>
	
	implements
				ISQLLogical_WhereOrJoin_Named_Base<MODEL, RESULT>,
				ISQLJoin_Named_Base<MODEL, RESULT>,
				ISharedJoin_Condition_Named<MODEL, RESULT, Object, Object, NAMED_JOIN_CONDITION>,
	
			   	ISQLLogical_WhereOrJoin_Alias_Base<MODEL, RESULT>,
			   	ISQLJoin_Alias<MODEL, RESULT, ALIAS_JOIN_CONDITION>
		
		{
		
			
	SQL_Collector_WhereOrJoin_Base(Collector_Base<MODEL> last, EConditionsClause conditionsClause) {
		super(last, conditionsClause);
	}


	SQL_Collector_WhereOrJoin_Base(Collector_Query<MODEL> queryCollector, Collector_Clause collector) {
		super(queryCollector, collector);
	}
	
			
	/*********************************************************************************************
	 * Named
	 ********************************************************************************************/

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
	
	// -- Table -- 
	

	@SuppressWarnings("unchecked")
	private NAMED_JOIN_CONDITION getJoinCondition() {
		return (NAMED_JOIN_CONDITION)this;
	}
	
	
	final <LEFT, RIGHT> NAMED_JOIN_CONDITION innerJoinUtil(Class<LEFT> leftType, Class<RIGHT> rightType) {

		final CollectedJoin_Named collectedJoin = new CollectedJoin_Named(EJoinType.INNER, leftType, rightType);
		
		addJoin(collectedJoin);
		
		return getJoinCondition();
	}

	final <LEFT, RIGHT> NAMED_JOIN_CONDITION leftJoinUtil(Class<LEFT> leftType, Class<RIGHT> rightType) {

		final CollectedJoin_Named collectedJoin = new CollectedJoin_Named(EJoinType.LEFT, leftType, rightType);
		
		addJoin(collectedJoin);
		
		return getJoinCondition();
	}

	
	@Override
	public final NAMED_JOIN_CONDITION on(CollectionFunction<Object, Object> joinCollection) {
		final FunctionGetter collectionGetter = new FunctionGetter(joinCollection); 

		final CollectedJoin curJoin = getQueryCollector().getJoins().getLast();

		final CollectedJoinCondition joinCondition = new CollectedJoinCondition_OneToMany_Named(collectionGetter);
		
		curJoin.addJoinCondition(joinCondition);
		
		return getJoinCondition();
	}

	@Override
	public final NAMED_JOIN_CONDITION compare(IFunctionInteger<Object> left, IFunctionInteger<Object> right) {
		
		final FunctionGetter leftGetter = new FunctionGetter(left); 
		final FunctionGetter rightGetter = new FunctionGetter(right); 
		
		final CollectedJoin curJoin = getQueryCollector().getJoins().getLast();
		
		final CollectedJoinCondition joinCondition = new CollectedJoinCondition_Comparison_Named(leftGetter, rightGetter);
		
		curJoin.addJoinCondition(joinCondition);
		
		return getJoinCondition();
	}
	

	final ISharedFunctions_Transform_Initial_Named<
		MODEL,
		RESULT,
		NAMED_AND_OR,
	
		ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, Integer, NAMED_AND_OR>,
		ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, Long, NAMED_AND_OR>,
		ISharedCondition_Comparable_String_All_Compilable<MODEL, RESULT, NAMED_AND_OR>> 

			whereNamed() {

		@SuppressWarnings({"unchecked", "rawtypes"})
		final ISharedCollector_Functions_Callback_Named<MODEL, RESULT, NAMED_AND_OR> cb
				= new ISharedCollector_Functions_Callback_Named<MODEL, RESULT, NAMED_AND_OR>() {
		
			@Override
			public ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Comparable<?>, NAMED_AND_OR>
				onComparable(CollectedFunctions functions, Function getter) {
				
				return andNamedClassImplComparable(functions, (Function)getter);
			}
		
			@Override
			public ISharedCondition_Comparable_String_Base<MODEL, RESULT, NAMED_AND_OR>
				onString(CollectedFunctions functions, StringFunction getter) {
				
				return new Collector_Condition_String<MODEL, RESULT, NAMED_AND_OR> (SQL_Collector_WhereOrJoin_Base.this, functions, makeGetter(getter));
			}
		};
		
		return new Collector_SharedFunctions_Named<>(cb);
	}
	

	// ------------------------  WHERE ------------------------

	// Allow swithcing to some other instance after initial where, when we know whether named or alias
	Collector_Conditions_GroupBy<MODEL, RESULT, ?> getAfterWhereNamed() {
		
		return this;
	}
		
	// implemented in subclass @Override
	public final <T> ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, Integer, NAMED_AND_OR> where(IFunctionInteger<T> getter) {

		return new Collector_Condition_Comparative<MODEL, RESULT, Integer, NAMED_AND_OR>(getAfterWhereNamed(), makeGetter(getter));
	}
	
	//implemented in subclass @Override
	public final <T> ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, Long, NAMED_AND_OR> where(IFunctionLong<T> getter) {

		return new Collector_Condition_Comparative<MODEL, RESULT, Long, NAMED_AND_OR>(getAfterWhereNamed(), makeGetter(getter));
	}

	// implemented in subclass @Override
	public final <T, E extends Enum<E>> ISharedCondition_Equality_All<MODEL, RESULT, E, NAMED_AND_OR> where(IFunctionEnum<T, E> getter) {

		return new Collector_Condition_Comparative<MODEL, RESULT, E, NAMED_AND_OR>(getAfterWhereNamed(), makeGetter(getter));
	}

	// implemented in subclass @Override
	public final <T> ISharedCondition_Comparable_String_All_Compilable<MODEL, RESULT, NAMED_AND_OR> where(StringFunction<T> getter) {
		
		return new Collector_Condition_String<MODEL, RESULT, NAMED_AND_OR>(getAfterWhereNamed(), makeGetter(getter));
	}
	
			

	/*********************************************************************************************
	 * Alias
	 ********************************************************************************************/
			
		
	// ------------------------  JOIN ------------------------




	// -- Alias  --
	
	@SuppressWarnings("unchecked")
	private <LEFT, RIGHT> ALIAS_JOIN_CONDITION getJoinConditionAlias() {
		return (ALIAS_JOIN_CONDITION)this;
	}
	
	
	@Override
	public final ALIAS_JOIN_CONDITION innerJoin(Object left, Object right) {
	
		final CollectedJoin_Alias collectedJoin = new CollectedJoin_Alias(EJoinType.INNER, (IAlias)left, (IAlias)right);
		
		addJoin(collectedJoin);
		
		return getJoinConditionAlias();
	}
	
	@Override
	public final ALIAS_JOIN_CONDITION leftJoin(Object left, Object right) {
	
		final CollectedJoin_Alias collectedJoin = new CollectedJoin_Alias(EJoinType.LEFT, (IAlias)left, (IAlias)right);
		
		addJoin(collectedJoin);
		
		return getJoinConditionAlias();
	}
	
	private <R> ALIAS_JOIN_CONDITION compareAlias(Supplier<R> left, Supplier<R> right) {
		
		final SupplierGetter leftGetter = new SupplierGetter(left); 
		final SupplierGetter rightGetter = new SupplierGetter(right); 
		
		final CollectedJoin curJoin = getQueryCollector().getJoins().getLast();
		
		final CollectedJoinCondition joinCondition = new CollectedJoinCondition_Comparison_Alias(leftGetter, rightGetter);
		
		curJoin.addJoinCondition(joinCondition);
		
		return getJoinConditionAlias();
	}
	
	// JoinCondition, markes as implemented in subclass
	public final ALIAS_JOIN_CONDITION on(ISupplierCollection joinCollection) {
		final SupplierGetter collectionGetter = new SupplierGetter(joinCollection); 
		
		final CollectedJoin curJoin = getQueryCollector().getJoins().getLast();
		
		final CollectedJoinCondition joinCondition = new CollectedJoinCondition_OneToMany_Alias(collectionGetter);
		
		curJoin.addJoinCondition(joinCondition);
		
		return getJoinConditionAlias();
	}
	
	public final ALIAS_JOIN_CONDITION compare(ISupplierInteger left, ISupplierInteger right) {
		return compareAlias(left, right);
	}

	// JoinCondition, markes as implemented in subclass
	public final ALIAS_JOIN_CONDITION compare(ISupplierLong left, ISupplierLong right) {
		return compareAlias(left, right);
	}

	// JoinCondition, marks this as implemented in subclass by implementing matching interface there
	final ISharedFunctions_Transform_Initial_Alias<
			MODEL, RESULT,
			ALIAS_AND_OR,
			
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, ALIAS_AND_OR>,
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, ALIAS_AND_OR>,
			ISharedCondition_Comparable_String_All<MODEL, RESULT, ALIAS_AND_OR>
	> 
	
			whereAlias() {
		
		@SuppressWarnings({"unchecked", "rawtypes"})
		final ISharedCollector_Functions_Callback_Alias<MODEL, RESULT, ALIAS_AND_OR> cb
				= new ISharedCollector_Functions_Callback_Alias<MODEL, RESULT, ALIAS_AND_OR>() {
		
			@Override
			public ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Comparable<?>, ALIAS_AND_OR>
				onComparable(CollectedFunctions functions, Supplier<? extends Comparable<?>> getter) {
				
				return andAliasImplComparable(functions, (Supplier)getter);
			}
		
			@Override
			public ISharedCondition_Comparable_String_Base<MODEL, RESULT, ALIAS_AND_OR>
				onString(CollectedFunctions functions, ISupplierString getter) {
				
				return new Collector_Condition_String<MODEL, RESULT, ALIAS_AND_OR> (SQL_Collector_WhereOrJoin_Base.this, functions, makeGetter(getter));
			}
		};
	
		return new Collector_SharedFunctions_Alias<>(cb);
	}
	// ------------------------  WHERE ------------------------

	// Allow swithcing to some other instance after initial where, when we know whether named or alias
	Collector_Conditions_GroupBy<MODEL, RESULT, ?> getAfterWhereAlias() {
		
		return this;
	}
			
	//implemented in subclass @Override
	public final ISharedCondition_Comparable_Common_All<
				MODEL,
				RESULT,
				Integer,
				ALIAS_AND_OR> where(ISupplierInteger func) {
	
		return new Collector_Condition_Comparative<MODEL, RESULT, Integer, ALIAS_AND_OR>(getAfterWhereAlias(), makeGetter(func));
	}	
	
	// implemented in subclass @Override
	public final ISharedCondition_Comparable_String_All<
				MODEL,
				RESULT,
				ALIAS_AND_OR> where(ISupplierString supplier) {
	
		return new Collector_Condition_String<MODEL, RESULT, ALIAS_AND_OR>(getAfterWhereAlias(), makeGetter(supplier));
	}

}
