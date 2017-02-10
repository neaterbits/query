package com.neaterbits.query.sql.dsl.api;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

abstract class Classic_Collector_WhereOrJoin_Named_Base<

			MODEL,
			RESULT,
			JOIN_CONDITION extends IClassicJoin_Condition_Named_Base<MODEL, RESULT, Object, Object, JOIN_CONDITION>,
			
			AND_CLAUSES extends ISharedLogical_And_Named_All<MODEL, RESULT, AND_CLAUSES, IClassicLogical_Or_NonProcessResult_Named<MODEL, RESULT>>,
			OR_CLAUSES  extends ISharedLogical_Or_Named_All<MODEL, RESULT, OR_CLAUSES,  IClassicLogical_And_NonProcessResult_Named<MODEL, RESULT>>,

			AND_OR extends ISharedLogical_Base<MODEL, RESULT> /* extends ISharedLogical_And_Or_Named_All<
							MODEL,
							RESULT,
							AND_CLAUSES,
							OR_CLAUSES,
							IClassicLogical_And_NonProcessResult_Named<MODEL, RESULT>,
							IClassicLogical_Or_NonProcessResult_Named<MODEL, RESULT>> */>

	extends Classic_Collector_Where_Or_Join<MODEL, RESULT>
	implements 
	
				IClassicLogical_WhereOrJoin_Named_Base<MODEL, RESULT>,
				IClassicJoin_Condition_Named_Base<MODEL, RESULT, Object, Object, JOIN_CONDITION>,
				ISharedLogical_And_Or_Named_All<
								MODEL,
								RESULT,
								AND_CLAUSES,
								OR_CLAUSES,
								IClassicLogical_And_NonProcessResult_Named<MODEL, RESULT>,
								IClassicLogical_Or_NonProcessResult_Named<MODEL, RESULT>> {

    abstract Classic_Collector_Or_Named<MODEL, RESULT, OR_CLAUSES> createOrCollector();
    abstract Classic_Collector_And_Named<MODEL, RESULT, AND_CLAUSES> createAndCollector();
    
    /*

    abstract Classic_Collector_And_Named<MODEL, RESULT, IClassicLogical_And_NonProcessResult_Named<MODEL, RESULT>>
    				createNestedAnd(Classic_Collector_Or_Named<MODEL, RESULT, OR_CLAUSES> orClauses); 
    
    
    abstract Classic_Collector_Or_Named<MODEL, RESULT, IClassicLogical_Or_NonProcessResult_Named<MODEL, RESULT>>
    				createNestedOr(Classic_Collector_And_Named<MODEL, RESULT, AND_CLAUSES> andClauses); 
		
	*/
					
	Classic_Collector_WhereOrJoin_Named_Base(BaseQueryEntity<MODEL> last) {
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
	private JOIN_CONDITION getJoinCondition() {
		return (JOIN_CONDITION)this;
	}
	
	
	final <LEFT, RIGHT> JOIN_CONDITION innerJoinUtil(Class<LEFT> leftType, Class<RIGHT> rightType) {

		final CollectedJoin_Named collectedJoin = new CollectedJoin_Named(EJoinType.INNER, leftType, rightType);
		
		addJoin(collectedJoin);
		
		return getJoinCondition();
	}

	final <LEFT, RIGHT> JOIN_CONDITION leftJoinUtil(Class<LEFT> leftType, Class<RIGHT> rightType) {

		final CollectedJoin_Named collectedJoin = new CollectedJoin_Named(EJoinType.LEFT, leftType, rightType);
		
		addJoin(collectedJoin);
		
		return getJoinCondition();
	}

	
	@Override
	public final JOIN_CONDITION on(CollectionFunction<Object, Object> joinCollection) {
		final FunctionGetter collectionGetter = new FunctionGetter(joinCollection); 

		final CollectedJoin curJoin = getQueryCollector().getJoins().getLast();

		final CollectedJoinCondition joinCondition = new CollectedJoinCondition_OneToMany_Named(collectionGetter);
		
		curJoin.addJoinCondition(joinCondition);
		
		return getJoinCondition();
	}

	@Override
	public final JOIN_CONDITION compare(IFunctionInteger<Object> left, IFunctionInteger<Object> right) {
		
		final FunctionGetter leftGetter = new FunctionGetter(left); 
		final FunctionGetter rightGetter = new FunctionGetter(right); 
		
		final CollectedJoin curJoin = getQueryCollector().getJoins().getLast();
		
		final CollectedJoinCondition joinCondition = new CollectedJoinCondition_Comparison_Named(leftGetter, rightGetter);
		
		curJoin.addJoinCondition(joinCondition);
		
		return getJoinCondition();
	}
	


	// ------------------------  WHERE ------------------------
	// implemented in subclass @Override
	public final <T> ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, Integer, AND_OR> where(IFunctionInteger<T> getter) {

		return new Collector_Condition_Comparative<MODEL, RESULT, Integer, AND_OR>(this, makeGetter(getter));
	}
	
	//implemented in subclass @Override
	public final <T> ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, Long, AND_OR> where(IFunctionLong<T> getter) {

		return new Collector_Condition_Comparative<MODEL, RESULT, Long, AND_OR>(this, makeGetter(getter));
	}

	// implemented in subclass @Override
	public final <T, E extends Enum<E>> ISharedCondition_Equality_All<MODEL, RESULT, E, AND_OR> where(IFunctionEnum<T, E> getter) {

		return new Collector_Condition_Comparative<MODEL, RESULT, E, AND_OR>(this, makeGetter(getter));
	}

	// implemented in subclass @Override
	public final <T> ISharedCondition_Comparable_String_All_Compilable<MODEL, RESULT, AND_OR> where(StringFunction<T> getter) {
		
		return new Collector_Condition_String<MODEL, RESULT, AND_OR>(this, makeGetter(getter));
	}
	
	// implemented in subclass @Override
	public final ISharedFunctions_Named_Initial<
			MODEL, RESULT,
			AND_OR,
			
			ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, Integer, AND_OR>,
			ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, Long, AND_OR>,
			ISharedCondition_Comparable_String_All_Compilable<MODEL, RESULT, AND_OR>> 
	
			where() {
		
		@SuppressWarnings({"unchecked", "rawtypes"})
		final ISharedCollector_Functions_Callback_Named<MODEL, RESULT, AND_OR> cb
				= new ISharedCollector_Functions_Callback_Named<MODEL, RESULT, AND_OR>() {
		
			@Override
			public ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Comparable<?>, AND_OR>
				onComparable(CollectedFunctions functions, Function getter) {
				
				return andClassImplComparable(functions, (Function)getter);
			}
		
			@Override
			public ISharedCondition_Comparable_String_Base<MODEL, RESULT, AND_OR>
				onString(CollectedFunctions functions, StringFunction getter) {
				
				return new Collector_Condition_String<MODEL, RESULT, AND_OR> (Classic_Collector_WhereOrJoin_Named_Base.this, functions, makeGetter(getter));
			}
		};

		return new Collector_SharedFunctions_Named<>(cb);
	}

	// ------------------------  AND ------------------------


	@Override
	public final <T> ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, AND_CLAUSES> and(IFunctionInteger<T> getter) {
		return andClassImplComparative(getter);
	}

	@Override
	public final <T> ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, AND_CLAUSES> and(IFunctionLong<T> getter) {
		return andClassImplComparative(getter);
	}

	@Override
	public final <T> ISharedCondition_Comparable_String_All<MODEL, RESULT, AND_CLAUSES> and(StringFunction<T> getter) {
		return andClassImplString(null, getter);
	}
	
	@Override
	public final ISharedFunctions_Named_Initial<
		MODEL,
		RESULT,
		AND_CLAUSES,
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, AND_CLAUSES>,
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, AND_CLAUSES>,
		ISharedCondition_Comparable_String_All<MODEL, RESULT, AND_CLAUSES>
	>
		and() {

		@SuppressWarnings({"unchecked", "rawtypes"})
		final ISharedCollector_Functions_Callback_Named<MODEL, RESULT, AND_CLAUSES> cb
				= new ISharedCollector_Functions_Callback_Named<MODEL, RESULT, AND_CLAUSES>() {
		
			@Override
			public ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Comparable<?>, AND_CLAUSES>
				onComparable(CollectedFunctions functions, Function getter) {
				
				return andClassImplComparable(functions, (Function)getter);
			}
		
			@Override
			public ISharedCondition_Comparable_String_Base<MODEL, RESULT, AND_CLAUSES>
				onString(CollectedFunctions functions, StringFunction getter) {
				
				return andClassImplString(functions, getter);
			}
		};

		return new Collector_SharedFunctions_Named<>(cb);
	}

	// ------------------------  OR ------------------------


	@Override
	public final <T> ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, OR_CLAUSES> or(IFunctionInteger<T> getter) {
		return orClassImplComparable(getter);
	}

	
	@Override
	public final <T> ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, OR_CLAUSES> or(IFunctionLong<T> getter) {
		return orClassImplComparable(getter);
	}


	@Override
	public final <T> ISharedCondition_Comparable_String_All<MODEL, RESULT, OR_CLAUSES> or(StringFunction<T> getter) {
		return orClassImplString(null, getter);
	}

	@Override
	public final ISharedFunctions_Named_Initial<
		MODEL,
		RESULT,
		OR_CLAUSES,
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, OR_CLAUSES>,
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, OR_CLAUSES>,
		ISharedCondition_Comparable_String_All<MODEL, RESULT, OR_CLAUSES>
	>
		or() {

		@SuppressWarnings({"unchecked", "rawtypes"})
		final ISharedCollector_Functions_Callback_Named<MODEL, RESULT, OR_CLAUSES> cb
				= new ISharedCollector_Functions_Callback_Named<MODEL, RESULT, OR_CLAUSES>() {
		
			@Override
			public ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Comparable<?>, OR_CLAUSES>
				onComparable(CollectedFunctions functions, Function getter) {
				
				return orClassImplComparable(functions, (Function)getter);
			}
		
			@Override
			public ISharedCondition_Comparable_String_Base<MODEL, RESULT, OR_CLAUSES> 
				onString(CollectedFunctions functions, StringFunction getter) {
				return orClassImplString(functions, getter);
			}
		};

		return new Collector_SharedFunctions_Named<>(cb);
	}
	// ------------------------  AND helpers ------------------------

	private <T, RR extends Comparable<RR>>
			
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, RR, AND_CLAUSES> andClassImplComparative(Function<T, RR> getter) {
		
		return andClassImplComparable(null, getter);
	}

	private <T, RR extends Comparable<RR>>
		
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, RR, AND_CLAUSES> andClassImplComparable(CollectedFunctions functions, Function<T, RR> getter) {
	
		final Classic_Collector_And_Named<MODEL, RESULT, AND_CLAUSES> andClauses = createAndCollector(); // new Classic_Collector_And_Named<>(this);
	
		return new Collector_Condition_Comparative<MODEL, RESULT, RR, AND_CLAUSES>(andClauses, functions, makeGetter(getter));
	}
		
		
	private ISharedCondition_Comparable_String_All<MODEL, RESULT, AND_CLAUSES> andClassImplString(CollectedFunctions functions, StringFunction<?> getter) {
		
		final Classic_Collector_And_Named<MODEL, RESULT, AND_CLAUSES> andClauses = createAndCollector(); // new Classic_Collector_And_Named<>(this);
		
		return new Collector_Condition_String<MODEL, RESULT, AND_CLAUSES>(andClauses, functions, makeGetter(getter));
	}

	// ------------------------  OR helpers ------------------------
	private <T, RR extends Comparable<RR>>
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, RR, OR_CLAUSES> orClassImplComparable(Function<T, RR> getter) {
		
		return orClassImplComparable(null, getter);
	}

	private <T, RR extends Comparable<RR>>
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, RR, OR_CLAUSES> orClassImplComparable(CollectedFunctions functions, Function<T, RR> getter) {
	
		final Classic_Collector_Or_Named<MODEL, RESULT, OR_CLAUSES> orClauses = createOrCollector(); // new Classic_Collector_Or_Named<>(this);

		return new Collector_Condition_Comparative<MODEL, RESULT, RR, OR_CLAUSES>(orClauses, functions, makeGetter(getter));
	}
	private ISharedCondition_Comparable_String_All<MODEL, RESULT, OR_CLAUSES>
				orClassImplString(CollectedFunctions functions, StringFunction<?> getter) {

		final Classic_Collector_Or_Named<MODEL, RESULT, OR_CLAUSES> andClauses = createOrCollector(); // new Classic_Collector_Or_Named<>(this);

		return new Collector_Condition_String<MODEL, RESULT, OR_CLAUSES>(andClauses, functions, makeGetter(getter));
	}
	

	private <RR extends Comparable<RR>>
		ISharedCondition_Equality_All<MODEL, RESULT, RR, OR_CLAUSES> orAliasImpl(Classic_Collector_WhereOrJoin_Named_Base<MODEL, RESULT, ?, ?, ?, ?> last, Supplier<RR> getter) {

		final Classic_Collector_Or_Named<MODEL, RESULT, OR_CLAUSES> orClauses =  createOrCollector(); // new Classic_Collector_Or_Named<>(last);

		return new Collector_Condition_Comparative<MODEL, RESULT, RR, OR_CLAUSES>(orClauses, makeGetter(getter));
	}
		

	// ------------------------  OR helpers ------------------------
	private final <T extends ISharedLogical_And<MODEL, RESULT>>
		Classic_Collector_Or_Named<MODEL, RESULT, OR_CLAUSES> addNestedAndImpl(Consumer<T> orBuilder) {
	
		final Classic_Collector_Or_Named<MODEL, RESULT, OR_CLAUSES> orClauses = createOrCollector(); // new Classic_Collector_Or_Named<>(this);
		
		// Add to new OR
		orClauses.addNestedAndImpl(orBuilder, new Classic_Collector_And_NonProcessResult_Named<MODEL, RESULT>(orClauses));
		
		return orClauses;
	}
	
	private final <T extends ISharedLogical_Or<MODEL, RESULT>>
		Classic_Collector_And_Named<MODEL, RESULT, AND_CLAUSES> addNestedOrImpl(Consumer<T> orBuilder) {

		final Classic_Collector_And_Named<MODEL, RESULT, AND_CLAUSES> andClauses = createAndCollector(); // new Classic_Collector_And_Named<>(this);

		// Add to new AND clause
		andClauses.addNestedOrImpl(orBuilder, new Classic_Collector_Or_NonProcessResult_Named<MODEL, RESULT>(andClauses));
		
		return andClauses;
	}

	@SuppressWarnings("unchecked")
	@Override
	public final AND_CLAUSES andNest(ISharedNestedOrConsumerNamed<MODEL, RESULT, IClassicLogical_Or_NonProcessResult_Named<MODEL, RESULT>> orBuilder) {
		return (AND_CLAUSES)addNestedOrImpl(orBuilder);
	}

	@SuppressWarnings("unchecked")
	@Override
	public final OR_CLAUSES orNest(ISharedNestedAndConsumerNamed<MODEL, RESULT, IClassicLogical_And_NonProcessResult_Named<MODEL, RESULT>> andBuilder) {
		return (OR_CLAUSES)addNestedAndImpl(andBuilder);
	}
}

