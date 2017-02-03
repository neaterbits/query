package com.neaterbits.query.sql.dsl.api;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

final class Classic_Collector_Where_Or_Join_Named<MODEL, RESULT>
	extends Classic_Collector_Where_Or_Join<MODEL, RESULT, Classic_Collector_And_Named<MODEL, RESULT>, Classic_Collector_Or_Named<MODEL, RESULT>>
	implements 
	
				IClassicLogical_WhereOrJoin_Named<MODEL, RESULT>,
				IClassicLogical_And_Or_Named<MODEL, RESULT>,
				IClassicJoinConditionNamed<MODEL, RESULT, Object, Object> {

	Classic_Collector_Where_Or_Join_Named(BaseQueryEntity<MODEL> last) {
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
	


	// ------------------------  WHERE ------------------------
	@Override
	public <T> ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, Integer, IClassicLogical_And_Or_Named<MODEL, RESULT>> where(IFunctionInteger<T> getter) {

		return new Collector_Condition_Comparative<MODEL, RESULT, Integer, IClassicLogical_And_Or_Named<MODEL,RESULT>>(this, makeGetter(getter));
	}
	
	@Override
	public <T> ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, Long, IClassicLogical_And_Or_Named<MODEL, RESULT>> where(IFunctionLong<T> getter) {

		return new Collector_Condition_Comparative<MODEL, RESULT, Long, IClassicLogical_And_Or_Named<MODEL,RESULT>>(this, makeGetter(getter));
	}

	@Override
	public <T, E extends Enum<E>> ISharedCondition_Equality_All<MODEL, RESULT, E, IClassicLogical_And_Or_Named<MODEL, RESULT>> where(IFunctionEnum<T, E> getter) {

		return new Collector_Condition_Comparative<MODEL, RESULT, E, IClassicLogical_And_Or_Named<MODEL,RESULT>>(this, makeGetter(getter));
	}

	@Override
	public <T> ISharedCondition_Comparable_String_All_Compilable<MODEL, RESULT, IClassicLogical_And_Or_Named<MODEL, RESULT>> where(StringFunction<T> getter) {
		
		return new Collector_Condition_String<MODEL, RESULT, IClassicLogical_And_Or_Named<MODEL,RESULT>>(this, makeGetter(getter));
	}
	
	
	@Override
	public ISharedFunctions_Named_Initial<
			MODEL, RESULT,
			IClassicLogical_And_Or_Named<MODEL, RESULT>,
			
			ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, Integer, IClassicLogical_And_Or_Named<MODEL, RESULT>>,
			ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, Long, IClassicLogical_And_Or_Named<MODEL, RESULT>>,
			ISharedCondition_Comparable_String_All_Compilable<MODEL, RESULT, IClassicLogical_And_Or_Named<MODEL, RESULT>>> 
	
			where() {
		
		@SuppressWarnings({"unchecked", "rawtypes"})
		final ISharedCollector_Functions_Callback_Named<MODEL, RESULT, IClassicLogical_And_Or_Named<MODEL, RESULT>> cb
				= new ISharedCollector_Functions_Callback_Named<MODEL, RESULT, IClassicLogical_And_Or_Named<MODEL, RESULT>>() {
		
			@Override
			public ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Comparable<?>, IClassicLogical_And_Or_Named<MODEL, RESULT>>
				onComparable(CollectedFunctions functions, Function getter) {
				
				return andClassImplComparable(functions, (Function)getter);
			}
		
			@Override
			public ISharedCondition_Comparable_String_Base<MODEL, RESULT, IClassicLogical_And_Or_Named<MODEL, RESULT>>
				onString(CollectedFunctions functions, StringFunction getter) {
				
				return new Collector_Condition_String<MODEL, RESULT, IClassicLogical_And_Or_Named<MODEL, RESULT>> (Classic_Collector_Where_Or_Join_Named.this, functions, makeGetter(getter));
			}
		};

		return new Collector_SharedFunctions_Named<>(cb);
	}

	// ------------------------  AND ------------------------


	@Override
	public <T> ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, IClassicLogical_And_Named<MODEL, RESULT>> and(IFunctionInteger<T> getter) {
		return andClassImplComparative(getter);
	}

	@Override
	public <T> ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, IClassicLogical_And_Named<MODEL, RESULT>> and(IFunctionLong<T> getter) {
		return andClassImplComparative(getter);
	}

	@Override
	public <T> ISharedCondition_Comparable_String_All<MODEL, RESULT, IClassicLogical_And_Named<MODEL, RESULT>> and(StringFunction<T> getter) {
		return andClassImplString(null, getter);
	}
	
	@Override
	public ISharedFunctions_Named_Initial<
		MODEL,
		RESULT,
		IClassicLogical_And_Named<MODEL, RESULT>,
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, IClassicLogical_And_Named<MODEL, RESULT>>,
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, IClassicLogical_And_Named<MODEL, RESULT>>,
		ISharedCondition_Comparable_String_All<MODEL, RESULT, IClassicLogical_And_Named<MODEL, RESULT>>
	>
		and() {

		@SuppressWarnings({"unchecked", "rawtypes"})
		final ISharedCollector_Functions_Callback_Named<MODEL, RESULT, IClassicLogical_And_Named<MODEL, RESULT>> cb
				= new ISharedCollector_Functions_Callback_Named<MODEL, RESULT, IClassicLogical_And_Named<MODEL, RESULT>>() {
		
			@Override
			public ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Comparable<?>, IClassicLogical_And_Named<MODEL, RESULT>>
				onComparable(CollectedFunctions functions, Function getter) {
				
				return andClassImplComparable(functions, (Function)getter);
			}
		
			@Override
			public ISharedCondition_Comparable_String_Base<MODEL, RESULT, IClassicLogical_And_Named<MODEL, RESULT>>
				onString(CollectedFunctions functions, StringFunction getter) {
				
				return andClassImplString(functions, getter);
			}
		};

		return new Collector_SharedFunctions_Named<>(cb);
	}

	// ------------------------  OR ------------------------


	@Override
	public <T> ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, IClassicLogical_Or_Named<MODEL, RESULT>> or(IFunctionInteger<T> getter) {
		return orClassImplComparable(getter);
	}

	
	@Override
	public <T> ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, IClassicLogical_Or_Named<MODEL, RESULT>> or(IFunctionLong<T> getter) {
		return orClassImplComparable(getter);
	}


	@Override
	public <T> ISharedCondition_Comparable_String_All<MODEL, RESULT, IClassicLogical_Or_Named<MODEL, RESULT>> or(StringFunction<T> getter) {
		
		return orClassImplString(null, getter);
	}

	@Override
	public ISharedFunctions_Named_Initial<
		MODEL,
		RESULT,
		IClassicLogical_Or_Named<MODEL, RESULT>,
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, IClassicLogical_Or_Named<MODEL, RESULT>>,
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, IClassicLogical_Or_Named<MODEL, RESULT>>,
		ISharedCondition_Comparable_String_All<MODEL, RESULT, IClassicLogical_Or_Named<MODEL, RESULT>>
	>
		or() {

		@SuppressWarnings({"unchecked", "rawtypes"})
		final ISharedCollector_Functions_Callback_Named<MODEL, RESULT, IClassicLogical_Or_Named<MODEL, RESULT>> cb
				= new ISharedCollector_Functions_Callback_Named<MODEL, RESULT, IClassicLogical_Or_Named<MODEL, RESULT>>() {
		
			@Override
			public ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Comparable<?>, IClassicLogical_Or_Named<MODEL, RESULT>>
				onComparable(CollectedFunctions functions, Function getter) {
				
				return orClassImplComparable(functions, (Function)getter);
			}
		
			@Override
			public ISharedCondition_Comparable_String_Base<MODEL, RESULT, IClassicLogical_Or_Named<MODEL, RESULT>> 
				onString(CollectedFunctions functions, StringFunction getter) {
				return orClassImplString(functions, getter);
			}
		};

		return new Collector_SharedFunctions_Named<>(cb);
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
	
		final Classic_Collector_And_Named<MODEL, RESULT> andClauses = new Classic_Collector_And_Named<>(this);
	
		return new Collector_Condition_Comparative<MODEL, RESULT, RR, AND_CLAUSES>(andClauses, functions, makeGetter(getter));
	}
		
		
	private <
			AND_CLAUSES 	  extends ISharedLogical_And_Named_All<MODEL, RESULT, AND_CLAUSES, 		NESTED_OR_CLAUSES>,
			NESTED_OR_CLAUSES extends ISharedLogical_Or_Named_All <MODEL, RESULT, NESTED_OR_CLAUSES, 	AND_CLAUSES>>

		ISharedCondition_Comparable_String_All<MODEL, RESULT, AND_CLAUSES> andClassImplString(CollectedFunctions functions, StringFunction<?> getter) {
		
		final Classic_Collector_And_Named<MODEL, RESULT> andClauses = new Classic_Collector_And_Named<>(this);
		
		return new Collector_Condition_String<MODEL, RESULT, AND_CLAUSES>(andClauses, functions, makeGetter(getter));
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
	
		final Classic_Collector_Or_Named<MODEL, RESULT> orClauses = new Classic_Collector_Or_Named<>(this);

		return new Collector_Condition_Comparative<MODEL, RESULT, RR, OR_CLAUSES>(orClauses, functions, makeGetter(getter));
	}
	private <
		OR_CLAUSES 		   extends ISharedLogical_Or_Named_All <MODEL, RESULT, OR_CLAUSES, 	    NESTED_AND_CLAUSES>,
		NESTED_AND_CLAUSES extends ISharedLogical_And_Named_All<MODEL, RESULT, NESTED_AND_CLAUSES, OR_CLAUSES>>

	ISharedCondition_Comparable_String_All<MODEL, RESULT, OR_CLAUSES> orClassImplString(CollectedFunctions functions, StringFunction<?> getter) {

		final Classic_Collector_Or_Named<MODEL, RESULT> andClauses = new Classic_Collector_Or_Named<>(this);

		return new Collector_Condition_String<MODEL, RESULT, OR_CLAUSES>(andClauses, functions, makeGetter(getter));
	}
	

	private <RR extends Comparable<RR>,
			OR_CLAUSES extends ISharedLogical_Or_Alias_Base<MODEL, RESULT, OR_CLAUSES, NESTED_AND_CLAUSES>,
			NESTED_AND_CLAUSES extends ISharedLogical_And_Alias_Base<MODEL, RESULT, NESTED_AND_CLAUSES, OR_CLAUSES>>
		ISharedCondition_Equality_All<MODEL, RESULT, RR, OR_CLAUSES> orAliasImpl(Classic_Collector_Where_Or_Join_Named<MODEL, RESULT> last, Supplier<RR> getter) {

		final Classic_Collector_Or_Named<MODEL, RESULT> orClauses = new Classic_Collector_Or_Named<>(last);

		return new Collector_Condition_Comparative<MODEL, RESULT, RR, OR_CLAUSES>(orClauses, makeGetter(getter));
	}
		

	// ------------------------  OR helpers ------------------------
	private final <T extends ISharedLogical_And<MODEL, RESULT>>
		Classic_Collector_Or_Named<MODEL, RESULT> addNestedAndImpl(Consumer<T> orBuilder) {
	
		final Classic_Collector_Or_Named<MODEL, RESULT> orClauses = new Classic_Collector_Or_Named<>(this);
		
		// Add to new OR
		orClauses.addNestedAndImpl(orBuilder, new Classic_Collector_And_Named<MODEL, RESULT>(orClauses));
		
		return orClauses;
	}
	
	private final <T extends ISharedLogical_Or<MODEL, RESULT>>
		Classic_Collector_And_Named<MODEL, RESULT> addNestedOrImpl(Consumer<T> orBuilder) {

		final Classic_Collector_And_Named<MODEL, RESULT> andClauses = new Classic_Collector_And_Named<>(this);

		// Add to new AND clause
		andClauses.addNestedOrImpl(orBuilder, new Classic_Collector_Or_Named<MODEL, RESULT>(andClauses));
		
		return andClauses;
	}

	@Override
	public IClassicLogical_And_Named<MODEL, RESULT> andNest(ISharedNestedOrConsumerNamed<MODEL, RESULT, IClassicLogical_Or_Named<MODEL, RESULT>> orBuilder) {
		return addNestedOrImpl(orBuilder);
	}

	@Override
	public IClassicLogical_Or_Named<MODEL, RESULT> orNest(ISharedNestedAndConsumerNamed<MODEL, RESULT, IClassicLogical_And_Named<MODEL, RESULT>> andBuilder) {
		return addNestedAndImpl(andBuilder);
	}
}

