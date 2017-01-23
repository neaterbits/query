package com.neaterbits.query.sql.dsl.api;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

final class ClassicWhereOrJoinClauseBuilder<MODEL, RESULT>
	extends CollectedClauses_Initial<MODEL, RESULT>
	implements IClassicWhereOrJoinBuilderNamed<MODEL, RESULT>,
			   IClassicWhereOrJoinBuilderAlias<MODEL, RESULT>,
			   IClassicAndOrLogicalClausesNamed<MODEL, RESULT>,
			   IClassicAndOrLogicalClausesAlias<MODEL, RESULT>,
			   
			   IClassicJoinConditionNamed<MODEL, RESULT, Object, Object>,
			   IClassicJoinConditionAlias<MODEL, RESULT> {

	ClassicWhereOrJoinClauseBuilder(BaseQueryEntity<MODEL> last) {
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
	public <T> ISharedClauseComparableCommonAll_Compilable<MODEL, RESULT, Integer, IClassicAndOrLogicalClausesNamed<MODEL, RESULT>> where(IFunctionInteger<T> getter) {

		return new CollectedClause_Comparative<MODEL, RESULT, Integer, IClassicAndOrLogicalClausesNamed<MODEL,RESULT>>(this, makeGetter(getter));
	}
	

	@Override
	public <T, E extends Enum<E>> ISharedClauseConditionAll<MODEL, RESULT, E, IClassicAndOrLogicalClausesNamed<MODEL, RESULT>> where(IFunctionEnum<T, E> getter) {

		return new CollectedClause_Condition<MODEL, RESULT, E, IClassicAndOrLogicalClausesNamed<MODEL,RESULT>>(this, makeGetter(getter));
	}

	@Override
	public <T> ISharedClauseComparableStringAll_Compilable<MODEL, RESULT, IClassicAndOrLogicalClausesNamed<MODEL, RESULT>> where(StringFunction<T> getter) {
		
		return new CollectedClause_String<MODEL, RESULT, IClassicAndOrLogicalClausesNamed<MODEL,RESULT>>(this, makeGetter(getter));
	}
	
	@Override
	public ISharedClauseConditionAll<MODEL, RESULT, Integer, IClassicAndOrLogicalClausesAlias<MODEL, RESULT>> where(ISupplierInteger func) {

		return new CollectedClause_Condition<MODEL, RESULT, Integer, IClassicAndOrLogicalClausesAlias<MODEL,RESULT>>(this, makeGetter(func));
	}	

	@Override
	public ISharedClauseComparableStringAll<MODEL, RESULT, IClassicAndOrLogicalClausesAlias<MODEL, RESULT>> where(ISupplierString supplier) {

		return new CollectedClause_String<MODEL, RESULT, IClassicAndOrLogicalClausesAlias<MODEL,RESULT>>(this, makeGetter(supplier));
	}

	// ------------------------  AND ------------------------

	
	@Override
	public <T> ISharedClauseComparableCommonAll<MODEL, RESULT, Integer, IClassicAndClausesNamed<MODEL, RESULT>> and(IFunctionInteger<T> getter) {
		return andClassImpl(getter);
	}

	@Override
	public <T> ISharedClauseComparableCommonAll<MODEL, RESULT, Long, IClassicAndClausesNamed<MODEL, RESULT>> and(IFunctionLong<T> getter) {
		return andClassImpl(getter);
	}

	@Override
	public <T> ISharedClauseComparableStringAll<MODEL, RESULT, IClassicAndClausesNamed<MODEL, RESULT>> and(StringFunction<T> getter) {
		
		final ClassicCollectedAndClauses<MODEL, RESULT> andClauses = new ClassicCollectedAndClauses<>(this);
		
		return new CollectedClause_String<MODEL, RESULT, IClassicAndClausesNamed<MODEL,RESULT>>(andClauses, makeGetter(getter));
	}
	
	@Override
	public ISharedConditionClauseAlias<MODEL, RESULT, Integer, IClassicAndClausesAlias<MODEL, RESULT>> and(ISupplierInteger getter) {
		return andAliasImpl(getter);
	}

	@Override
	public ISharedConditionClauseAlias<MODEL, RESULT, Long, IClassicAndClausesAlias<MODEL, RESULT>> and(ISupplierLong getter) {
		return andAliasImpl(getter);
	}

	@Override
	public ISharedClauseComparableStringAll<MODEL, RESULT, IClassicAndClausesAlias<MODEL, RESULT>> and(ISupplierString getter) {

		final ClassicCollectedAndClauses<MODEL, RESULT> andClauses = new ClassicCollectedAndClauses<>(this);
		
		return new CollectedClause_String<MODEL, RESULT, IClassicAndClausesAlias<MODEL,RESULT>>(andClauses, makeGetter(getter));
	}
	

	// ------------------------  OR ------------------------

	@Override
	public <T> ISharedClauseComparableCommonAll<MODEL, RESULT, Integer, IClassicOrClausesNamed<MODEL, RESULT>> or(IFunctionInteger<T> getter) {
		return orClassImpl(getter);
	}

	
	@Override
	public <T> ISharedClauseComparableCommonAll<MODEL, RESULT, Long, IClassicOrClausesNamed<MODEL, RESULT>> or(IFunctionLong<T> getter) {
		return orClassImpl(getter);
	}


	@Override
	public <T> ISharedClauseComparableStringAll<MODEL, RESULT, IClassicOrClausesNamed<MODEL, RESULT>> or(StringFunction<T> getter) {
		final ClassicCollectedOrClauses<MODEL, RESULT> orClauses = new ClassicCollectedOrClauses<>(this);

		return new CollectedClause_String<MODEL, RESULT, IClassicOrClausesNamed<MODEL,RESULT>>(orClauses, makeGetter(getter));
	}
	
	@Override
	public ISharedClauseConditionAll<MODEL, RESULT, Integer, IClassicOrClausesAlias<MODEL, RESULT>> or(ISupplierInteger getter) {
		return orAliasImpl(this, getter);
	}
	
	@Override
	public ISharedClauseConditionAll<MODEL, RESULT, Long, IClassicOrClausesAlias<MODEL, RESULT>> or(ISupplierLong getter) {
		return orAliasImpl(this, getter);
	}

	@Override
	public ISharedClauseComparableStringAll<MODEL, RESULT, IClassicOrClausesAlias<MODEL, RESULT>> or(ISupplierString getter) {

		final ClassicCollectedOrClauses<MODEL, RESULT> orClauses = new ClassicCollectedOrClauses<>(this);

		return new CollectedClause_String<MODEL, RESULT, IClassicOrClausesAlias<MODEL,RESULT>>(orClauses, makeGetter(getter));
	}

	// ------------------------  AND helpers ------------------------

	private <T,
			RR extends Comparable<RR>,
			AND_CLAUSES extends ISharedAndClausesNamedAll<MODEL, RESULT, AND_CLAUSES, NESTED_OR_CLAUSES>,
			NESTED_OR_CLAUSES extends ISharedOrClausesNamedAll<MODEL, RESULT, NESTED_OR_CLAUSES, AND_CLAUSES>>
			
		ISharedClauseComparableCommonAll<MODEL, RESULT, RR, AND_CLAUSES> andClassImpl(Function<T, RR> getter) {
		
		final ClassicCollectedAndClauses<MODEL, RESULT> andClauses = new ClassicCollectedAndClauses<>(this);
		
		return new CollectedClause_Comparative<MODEL, RESULT, RR, AND_CLAUSES>(andClauses, makeGetter(getter));
	}

	private <RR,
			AND_CLAUSES extends ISharedAndClausesAliasBase<MODEL, RESULT, AND_CLAUSES, NESTED_OR_CLAUSES>,
			NESTED_OR_CLAUSES extends ISharedOrClausesAlias<MODEL, RESULT>>
	
		ISharedConditionClauseAlias<MODEL, RESULT, RR, AND_CLAUSES> andAliasImpl(Supplier<RR> getter) {

		final ClassicCollectedAndClauses<MODEL, RESULT> andClauses = new ClassicCollectedAndClauses<>(this);
		
		return new CollectedClause_Condition<MODEL, RESULT, RR, AND_CLAUSES>(andClauses, makeGetter(getter));
	}

	// ------------------------  OR helpers ------------------------
	private <T,
			RR extends Comparable<RR>,
			OR_CLAUSES extends ISharedOrClausesNamedAll<MODEL, RESULT, OR_CLAUSES, NESTED_AND_CLAUSES>,
			NESTED_AND_CLAUSES extends ISharedAndClausesNamedAll<MODEL, RESULT, NESTED_AND_CLAUSES, OR_CLAUSES>>
	
		ISharedClauseComparableCommonAll<MODEL, RESULT, RR, OR_CLAUSES> orClassImpl(Function<T, RR> getter) {
		
		final ClassicCollectedOrClauses<MODEL, RESULT> orClauses = new ClassicCollectedOrClauses<>(this);

		return new CollectedClause_Comparative<MODEL, RESULT, RR, OR_CLAUSES>(orClauses, makeGetter(getter));
	}

	private <RR extends Comparable<RR>,
			OR_CLAUSES extends ISharedOrClausesAliasBase<MODEL, RESULT, OR_CLAUSES, NESTED_AND_CLAUSES>,
			NESTED_AND_CLAUSES extends ISharedAndClausesAliasBase<MODEL, RESULT, NESTED_AND_CLAUSES, OR_CLAUSES>>
		ISharedClauseConditionAll<MODEL, RESULT, RR, OR_CLAUSES> orAliasImpl(CollectedClauses_Initial<MODEL, RESULT> last, Supplier<RR> getter) {

		final ClassicCollectedOrClauses<MODEL, RESULT> orClauses = new ClassicCollectedOrClauses<>(last);

		return new CollectedClause_Comparative<MODEL, RESULT, RR, OR_CLAUSES>(orClauses, makeGetter(getter));
	}
		
		

	// ------------------------  OR helpers ------------------------
	private final <T extends ISharedAndClauses<MODEL, RESULT>, IMPL extends CollectedClauses<MODEL, RESULT>>
		ClassicCollectedOrClauses<MODEL, RESULT> addNestedAndImpl(Consumer<T> orBuilder) {
	
		final ClassicCollectedOrClauses<MODEL, RESULT> orClauses = new ClassicCollectedOrClauses<>(this);
		
		// Add to new OR
		orClauses.addNestedAndImpl(orBuilder, new ClassicCollectedAndClauses<MODEL, RESULT>(orClauses));
		
		return orClauses;
	}
	
	private final <T extends ISharedOrClauses<MODEL, RESULT>, IMPL extends CollectedClauses<MODEL, RESULT>>
		ClassicCollectedAndClauses<MODEL, RESULT> addNestedOrImpl(Consumer<T> orBuilder) {

		final ClassicCollectedAndClauses<MODEL, RESULT> andClauses = new ClassicCollectedAndClauses<>(this);

		// Add to new AND clause
		andClauses.addNestedOrImpl(orBuilder, new ClassicCollectedOrClauses<MODEL, RESULT>(andClauses));
		
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

