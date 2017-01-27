package com.neaterbits.query.sql.dsl.api;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

final class Classic_Collector_Where_Or_Join<MODEL, RESULT>
	extends Collector_Conditions_Initial<MODEL, RESULT>
	implements IClassicWhereOrJoinBuilderNamed<MODEL, RESULT>,
			   IClassicWhereOrJoinBuilderAlias<MODEL, RESULT>,
			   IClassicAndOrLogicalClausesNamed<MODEL, RESULT>,
			   IClassicAndOrLogicalClausesAlias<MODEL, RESULT>,
			   
			   IClassicJoinConditionNamed<MODEL, RESULT, Object, Object>,
			   IClassicJoinConditionAlias<MODEL, RESULT> {

	Classic_Collector_Where_Or_Join(BaseQueryEntity<MODEL> last) {
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
	
	// -- Table -- 
	
	@SuppressWarnings("unchecked")
	private <LEFT, RIGHT> IClassicJoinConditionNamed<MODEL, RESULT, LEFT, RIGHT> getJoinConditionTable() {
		return (IClassicJoinConditionNamed<MODEL, RESULT, LEFT, RIGHT>)this;
	}
	
	@Override
	public <LEFT, RIGHT> IClassicJoinConditionNamed<MODEL, RESULT, LEFT, RIGHT> innerJoin(Class<LEFT> leftType, Class<RIGHT> rightType) {

		final CollectedJoin_Named collectedJoin = new CollectedJoin_Named(EJoinType.INNER, leftType, rightType);
		
		addJoin(collectedJoin);
		
		return getJoinConditionTable();
	}

	@Override
	public <LEFT, RIGHT> IClassicJoinConditionNamed<MODEL, RESULT, LEFT, RIGHT> leftJoin(Class<LEFT> leftType, Class<RIGHT> rightType) {

		final CollectedJoin_Named collectedJoin = new CollectedJoin_Named(EJoinType.LEFT, leftType, rightType);
		
		addJoin(collectedJoin);
		
		return getJoinConditionTable();
	}

	
	@Override
	public IClassicJoinConditionNamed<MODEL, RESULT, Object, Object> on(CollectionFunction<Object, Object> joinCollection) {
		final FunctionGetter collectionGetter = new FunctionGetter(joinCollection); 

		final CollectedJoin curJoin = getQueryCollector().getJoins().getLast();

		final CollectedJoinCondition joinCondition = new CollectedJoinCondition_OneToMany_Named(collectionGetter);
		
		curJoin.addJoinCondition(joinCondition);
		
		return getJoinConditionTable();
	}

	@Override
	public IClassicJoinConditionNamed<MODEL, RESULT, Object, Object> compare(IFunctionInteger<Object> left, IFunctionInteger<Object> right) {
		
		final FunctionGetter leftGetter = new FunctionGetter(left); 
		final FunctionGetter rightGetter = new FunctionGetter(right); 
		
		final CollectedJoin curJoin = getQueryCollector().getJoins().getLast();
		
		final CollectedJoinCondition joinCondition = new CollectedJoinCondition_Comparison_Named(leftGetter, rightGetter);
		
		curJoin.addJoinCondition(joinCondition);
		
		return getJoinConditionTable();
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
	public <T> ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, Integer, IClassicAndOrLogicalClausesNamed<MODEL, RESULT>> where(IFunctionInteger<T> getter) {

		return new Collector_Condition_Comparative<MODEL, RESULT, Integer, IClassicAndOrLogicalClausesNamed<MODEL,RESULT>>(this, makeGetter(getter));
	}
	
	@Override
	public <T> ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, Long, IClassicAndOrLogicalClausesNamed<MODEL, RESULT>> where(IFunctionLong<T> getter) {

		return new Collector_Condition_Comparative<MODEL, RESULT, Long, IClassicAndOrLogicalClausesNamed<MODEL,RESULT>>(this, makeGetter(getter));
	}

	@Override
	public <T, E extends Enum<E>> ISharedCondition_Equality_All<MODEL, RESULT, E, IClassicAndOrLogicalClausesNamed<MODEL, RESULT>> where(IFunctionEnum<T, E> getter) {

		return new Collector_Condition_Equality<MODEL, RESULT, E, IClassicAndOrLogicalClausesNamed<MODEL,RESULT>>(this, makeGetter(getter));
	}

	@Override
	public <T> ISharedCondition_Comparable_String_All_Compilable<MODEL, RESULT, IClassicAndOrLogicalClausesNamed<MODEL, RESULT>> where(StringFunction<T> getter) {
		
		return new Collector_Condition_String<MODEL, RESULT, IClassicAndOrLogicalClausesNamed<MODEL,RESULT>>(this, makeGetter(getter));
	}
	
	@Override
	public ISharedCondition_Equality_All<MODEL, RESULT, Integer, IClassicAndOrLogicalClausesAlias<MODEL, RESULT>> where(ISupplierInteger func) {

		return new Collector_Condition_Equality<MODEL, RESULT, Integer, IClassicAndOrLogicalClausesAlias<MODEL,RESULT>>(this, makeGetter(func));
	}	

	@Override
	public ISharedCondition_Comparable_String_All<MODEL, RESULT, IClassicAndOrLogicalClausesAlias<MODEL, RESULT>> where(ISupplierString supplier) {

		return new Collector_Condition_String<MODEL, RESULT, IClassicAndOrLogicalClausesAlias<MODEL,RESULT>>(this, makeGetter(supplier));
	}
	
	
	@Override
	public ISharedFunctions_Named_Initial<
			MODEL, RESULT,
			IClassicAndOrLogicalClausesNamed<MODEL, RESULT>,
			
			ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, ? extends Comparable<?>, IClassicAndOrLogicalClausesNamed<MODEL, RESULT>>,
					ISharedCondition_Comparable_String_All_Compilable<MODEL, RESULT, IClassicAndOrLogicalClausesNamed<MODEL, RESULT>>> 
	
			where() {
		
		@SuppressWarnings({"unchecked", "rawtypes"})
		final Collector_Functions_Callback<MODEL, RESULT, IClassicAndOrLogicalClausesNamed<MODEL, RESULT>> cb
				= new Collector_Functions_Callback<MODEL, RESULT, IClassicAndOrLogicalClausesNamed<MODEL, RESULT>>() {
		
			@Override
			public ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Comparable<?>, IClassicAndOrLogicalClausesNamed<MODEL, RESULT>>
				onComparable(CollectedFunctions functions, Function getter) {
				
				return andClassImplComparable(functions, (Function)getter);
			}
		
			@Override
			public ISharedCondition_Comparable_String_Base<MODEL, RESULT, IClassicAndOrLogicalClausesNamed<MODEL, RESULT>>
				onString(CollectedFunctions functions, StringFunction getter) {
				
				return new Collector_Condition_String<MODEL, RESULT, IClassicAndOrLogicalClausesNamed<MODEL, RESULT>> (Classic_Collector_Where_Or_Join.this, functions, makeGetter(getter));
			}
		};

		return new Collector_SharedFunctions<>(cb);
	}

	// ------------------------  AND ------------------------


	@Override
	public <T> ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, IClassicAndClausesNamed<MODEL, RESULT>> and(IFunctionInteger<T> getter) {
		return andClassImplComparative(getter);
	}

	@Override
	public <T> ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, IClassicAndClausesNamed<MODEL, RESULT>> and(IFunctionLong<T> getter) {
		return andClassImplComparative(getter);
	}

	@Override
	public <T> ISharedCondition_Comparable_String_All<MODEL, RESULT, IClassicAndClausesNamed<MODEL, RESULT>> and(StringFunction<T> getter) {
		return andClassImplString(null, getter);
	}
	
	@Override
	public ISharedCondition_Equality_Alias<MODEL, RESULT, Integer, IClassicAndClausesAlias<MODEL, RESULT>> and(ISupplierInteger getter) {
		return andAliasImpl(getter);
	}

	@Override
	public ISharedCondition_Equality_Alias<MODEL, RESULT, Long, IClassicAndClausesAlias<MODEL, RESULT>> and(ISupplierLong getter) {
		return andAliasImpl(getter);
	}

	@Override
	public ISharedCondition_Comparable_String_All<MODEL, RESULT, IClassicAndClausesAlias<MODEL, RESULT>> and(ISupplierString getter) {

		final Classic_Collector_And<MODEL, RESULT> andClauses = new Classic_Collector_And<>(this);
		
		return new Collector_Condition_String<MODEL, RESULT, IClassicAndClausesAlias<MODEL,RESULT>>(andClauses, makeGetter(getter));
	}
	
	
	@Override
	public ISharedFunctions_Named_Initial<
		MODEL,
		RESULT,
		IClassicAndClausesNamed<MODEL, RESULT>,
		ISharedCondition_Comparable_Common_Base<MODEL, RESULT, ?, IClassicAndClausesNamed<MODEL, RESULT>>,
		ISharedCondition_Comparable_String_All<MODEL, RESULT, IClassicAndClausesNamed<MODEL, RESULT>>
	>
		and() {

		@SuppressWarnings({"unchecked", "rawtypes"})
		final Collector_Functions_Callback<MODEL, RESULT, IClassicAndClausesNamed<MODEL, RESULT>> cb
				= new Collector_Functions_Callback<MODEL, RESULT, IClassicAndClausesNamed<MODEL, RESULT>>() {
		
			@Override
			public ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Comparable<?>, IClassicAndClausesNamed<MODEL, RESULT>>
				onComparable(CollectedFunctions functions, Function getter) {
				
				return andClassImplComparable(functions, (Function)getter);
			}
		
			@Override
			public ISharedCondition_Comparable_String_Base<MODEL, RESULT, IClassicAndClausesNamed<MODEL, RESULT>>
				onString(CollectedFunctions functions, StringFunction getter) {
				
				return andClassImplString(functions, getter);
			}
		};

		return new Collector_SharedFunctions<>(cb);
	}

	// ------------------------  OR ------------------------


	@Override
	public <T> ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, IClassicOrClausesNamed<MODEL, RESULT>> or(IFunctionInteger<T> getter) {
		return orClassImplComparable(getter);
	}

	
	@Override
	public <T> ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, IClassicOrClausesNamed<MODEL, RESULT>> or(IFunctionLong<T> getter) {
		return orClassImplComparable(getter);
	}


	@Override
	public <T> ISharedCondition_Comparable_String_All<MODEL, RESULT, IClassicOrClausesNamed<MODEL, RESULT>> or(StringFunction<T> getter) {
		
		return orClassImplString(null, getter);
	}
	
	@Override
	public ISharedCondition_Equality_All<MODEL, RESULT, Integer, IClassicOrClausesAlias<MODEL, RESULT>> or(ISupplierInteger getter) {
		return orAliasImpl(this, getter);
	}
	
	@Override
	public ISharedCondition_Equality_All<MODEL, RESULT, Long, IClassicOrClausesAlias<MODEL, RESULT>> or(ISupplierLong getter) {
		return orAliasImpl(this, getter);
	}

	@Override
	public ISharedCondition_Comparable_String_All<MODEL, RESULT, IClassicOrClausesAlias<MODEL, RESULT>> or(ISupplierString getter) {

		final Classic_Collector_Or<MODEL, RESULT> orClauses = new Classic_Collector_Or<>(this);

		return new Collector_Condition_String<MODEL, RESULT, IClassicOrClausesAlias<MODEL,RESULT>>(orClauses, makeGetter(getter));
	}

	@Override
	public ISharedFunctions_Named_Initial<
		MODEL,
		RESULT,
		IClassicOrClausesNamed<MODEL, RESULT>,
		ISharedCondition_Comparable_Common_Base<MODEL, RESULT, ?, IClassicOrClausesNamed<MODEL, RESULT>>,
		ISharedCondition_Comparable_String_All<MODEL, RESULT, IClassicOrClausesNamed<MODEL, RESULT>>
	>
		or() {

		@SuppressWarnings({"unchecked", "rawtypes"})
		final Collector_Functions_Callback<MODEL, RESULT, IClassicOrClausesNamed<MODEL, RESULT>> cb
				= new Collector_Functions_Callback<MODEL, RESULT, IClassicOrClausesNamed<MODEL, RESULT>>() {
		
			@Override
			public ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Comparable<?>, IClassicOrClausesNamed<MODEL, RESULT>>
				onComparable(CollectedFunctions functions, Function getter) {
				
				return orClassImplComparable(functions, (Function)getter);
			}
		
			@Override
			public ISharedCondition_Comparable_String_Base<MODEL, RESULT, IClassicOrClausesNamed<MODEL, RESULT>> 
				onString(CollectedFunctions functions, StringFunction getter) {
				return orClassImplString(functions, getter);
			}
		};

		return new Collector_SharedFunctions<>(cb);
	}
	// ------------------------  AND helpers ------------------------

	private <T,
			RR extends Comparable<RR>,
			AND_CLAUSES 	  extends ISharedLogical_And_Named_All<MODEL, RESULT, AND_CLAUSES, 	  NESTED_OR_CLAUSES>,
			NESTED_OR_CLAUSES extends ISharedLogical_Or_Named_All <MODEL, RESULT, NESTED_OR_CLAUSES, AND_CLAUSES>>
			
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, RR, AND_CLAUSES> andClassImplComparative(Function<T, RR> getter) {
		
		return andClassImplComparable(null, getter);
	}

	private <T,
			RR extends Comparable<RR>,
			AND_CLAUSES 	  extends ISharedLogical_And_Named_All<MODEL, RESULT, AND_CLAUSES, 		NESTED_OR_CLAUSES>,
			NESTED_OR_CLAUSES extends ISharedLogical_Or_Named_All <MODEL, RESULT, NESTED_OR_CLAUSES, 	AND_CLAUSES>>
		
	ISharedCondition_Comparable_Common_All<MODEL, RESULT, RR, AND_CLAUSES> andClassImplComparable(CollectedFunctions functions, Function<T, RR> getter) {
	
		final Classic_Collector_And<MODEL, RESULT> andClauses = new Classic_Collector_And<>(this);
	
		return new Collector_Condition_Comparative<MODEL, RESULT, RR, AND_CLAUSES>(andClauses, functions, makeGetter(getter));
	}
		
		
	private <
			AND_CLAUSES 	  extends ISharedLogical_And_Named_All<MODEL, RESULT, AND_CLAUSES, 		NESTED_OR_CLAUSES>,
			NESTED_OR_CLAUSES extends ISharedLogical_Or_Named_All <MODEL, RESULT, NESTED_OR_CLAUSES, 	AND_CLAUSES>>

		ISharedCondition_Comparable_String_All<MODEL, RESULT, AND_CLAUSES> andClassImplString(CollectedFunctions functions, StringFunction<?> getter) {
		
		final Classic_Collector_And<MODEL, RESULT> andClauses = new Classic_Collector_And<>(this);
		
		return new Collector_Condition_String<MODEL, RESULT, AND_CLAUSES>(andClauses, functions, makeGetter(getter));
	}


	private <RR,
			AND_CLAUSES 	  extends ISharedLogical_And_Alias_Base<MODEL, RESULT, AND_CLAUSES, NESTED_OR_CLAUSES>,
			NESTED_OR_CLAUSES extends ISharedLogical_Or_Alias<MODEL, RESULT>>
	
		ISharedCondition_Equality_Alias<MODEL, RESULT, RR, AND_CLAUSES> andAliasImpl(Supplier<RR> getter) {

		final Classic_Collector_And<MODEL, RESULT> andClauses = new Classic_Collector_And<>(this);
		
		return new Collector_Condition_Equality<MODEL, RESULT, RR, AND_CLAUSES>(andClauses, makeGetter(getter));
	}

	// ------------------------  OR helpers ------------------------
	private <T,
			RR extends Comparable<RR>,
			OR_CLAUSES 		   extends ISharedLogical_Or_Named_All <MODEL, RESULT, OR_CLAUSES, 	    NESTED_AND_CLAUSES>,
			NESTED_AND_CLAUSES extends ISharedLogical_And_Named_All<MODEL, RESULT, NESTED_AND_CLAUSES, OR_CLAUSES>>
	
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, RR, OR_CLAUSES> orClassImplComparable(Function<T, RR> getter) {
		
		return orClassImplComparable(null, getter);
	}

	private <T,
		RR extends Comparable<RR>,
		OR_CLAUSES 		   extends ISharedLogical_Or_Named_All <MODEL, RESULT, OR_CLAUSES, 	    NESTED_AND_CLAUSES>,
		NESTED_AND_CLAUSES extends ISharedLogical_And_Named_All<MODEL, RESULT, NESTED_AND_CLAUSES, OR_CLAUSES>>

	ISharedCondition_Comparable_Common_All<MODEL, RESULT, RR, OR_CLAUSES> orClassImplComparable(CollectedFunctions functions, Function<T, RR> getter) {
	
		final Classic_Collector_Or<MODEL, RESULT> orClauses = new Classic_Collector_Or<>(this);

		return new Collector_Condition_Comparative<MODEL, RESULT, RR, OR_CLAUSES>(orClauses, functions, makeGetter(getter));
	}
	private <
		OR_CLAUSES 		   extends ISharedLogical_Or_Named_All <MODEL, RESULT, OR_CLAUSES, 	    NESTED_AND_CLAUSES>,
		NESTED_AND_CLAUSES extends ISharedLogical_And_Named_All<MODEL, RESULT, NESTED_AND_CLAUSES, OR_CLAUSES>>

	ISharedCondition_Comparable_String_All<MODEL, RESULT, OR_CLAUSES> orClassImplString(CollectedFunctions functions, StringFunction<?> getter) {

		final Classic_Collector_Or<MODEL, RESULT> andClauses = new Classic_Collector_Or<>(this);

		return new Collector_Condition_String<MODEL, RESULT, OR_CLAUSES>(andClauses, functions, makeGetter(getter));
	}
	

	private <RR extends Comparable<RR>,
			OR_CLAUSES extends ISharedLogical_Or_Alias_Base<MODEL, RESULT, OR_CLAUSES, NESTED_AND_CLAUSES>,
			NESTED_AND_CLAUSES extends ISharedLogical_And_Alias_Base<MODEL, RESULT, NESTED_AND_CLAUSES, OR_CLAUSES>>
		ISharedCondition_Equality_All<MODEL, RESULT, RR, OR_CLAUSES> orAliasImpl(Collector_Conditions_Initial<MODEL, RESULT> last, Supplier<RR> getter) {

		final Classic_Collector_Or<MODEL, RESULT> orClauses = new Classic_Collector_Or<>(last);

		return new Collector_Condition_Comparative<MODEL, RESULT, RR, OR_CLAUSES>(orClauses, makeGetter(getter));
	}
		

	// ------------------------  OR helpers ------------------------
	private final <T extends ISharedLogical_And<MODEL, RESULT>, IMPL extends Collector_Conditions<MODEL, RESULT>>
		Classic_Collector_Or<MODEL, RESULT> addNestedAndImpl(Consumer<T> orBuilder) {
	
		final Classic_Collector_Or<MODEL, RESULT> orClauses = new Classic_Collector_Or<>(this);
		
		// Add to new OR
		orClauses.addNestedAndImpl(orBuilder, new Classic_Collector_And<MODEL, RESULT>(orClauses));
		
		return orClauses;
	}
	
	private final <T extends ISharedLogical_Or<MODEL, RESULT>, IMPL extends Collector_Conditions<MODEL, RESULT>>
		Classic_Collector_And<MODEL, RESULT> addNestedOrImpl(Consumer<T> orBuilder) {

		final Classic_Collector_And<MODEL, RESULT> andClauses = new Classic_Collector_And<>(this);

		// Add to new AND clause
		andClauses.addNestedOrImpl(orBuilder, new Classic_Collector_Or<MODEL, RESULT>(andClauses));
		
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

	@Override
	public IClassicAndClausesNamed<MODEL, RESULT> andNest(ISharedNestedOrConsumerNamed<MODEL, RESULT, IClassicOrClausesNamed<MODEL, RESULT>> orBuilder) {
		return addNestedOrImpl(orBuilder);
	}

	@Override
	public IClassicOrClausesNamed<MODEL, RESULT> orNest(ISharedNestedAndConsumerNamed<MODEL, RESULT, IClassicAndClausesNamed<MODEL, RESULT>> andBuilder) {
		return addNestedAndImpl(andBuilder);
	}
}

