package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

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

	extends Collector_And_Or_Named<
				MODEL,
				RESULT,
				AND_CLAUSES,
				OR_CLAUSES,
				IClassicLogical_And_NonProcessResult_Named<MODEL, RESULT>,
				IClassicLogical_Or_NonProcessResult_Named<MODEL, RESULT>,
				
				
				ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>>
	implements 
	
				IClassicLogical_WhereOrJoin_Named_Base<MODEL, RESULT>,
				IClassicJoin_Condition_Named_Base<MODEL, RESULT, Object, Object, JOIN_CONDITION> {
    
					

	Classic_Collector_WhereOrJoin_Named_Base(Collector_Base<MODEL> last) {
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

	// nested-instantiation
			
	@Override
	Collector_Or_Named<MODEL, RESULT, IClassicLogical_Or_NonProcessResult_Named<MODEL, RESULT>, IClassicLogical_And_NonProcessResult_Named<MODEL, RESULT>, IClassicLogical_Or_NonProcessResult_Named<MODEL, RESULT>, ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>> createNestedOrCollector(
		Collector_And_Named<MODEL, RESULT, AND_CLAUSES, IClassicLogical_And_NonProcessResult_Named<MODEL, RESULT>, IClassicLogical_Or_NonProcessResult_Named<MODEL, RESULT>, ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>> andClauses) {

		return new Classic_Collector_Or_NonProcessResult_Named<>(andClauses, andClauses.getConditionsClause());
	}

	@Override
	Collector_And_Named<MODEL, RESULT, IClassicLogical_And_NonProcessResult_Named<MODEL, RESULT>, IClassicLogical_And_NonProcessResult_Named<MODEL, RESULT>, IClassicLogical_Or_NonProcessResult_Named<MODEL, RESULT>, ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>>


		createNestedAndCollector(
			Collector_Or_Named<MODEL, RESULT, OR_CLAUSES, IClassicLogical_And_NonProcessResult_Named<MODEL, RESULT>, IClassicLogical_Or_NonProcessResult_Named<MODEL, RESULT>, ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>> orClauses) {
		return new Classic_Collector_And_NonProcessResult_Named<>(orClauses, orClauses.getConditionsClause());
	}
}

