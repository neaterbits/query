package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;
import java.util.function.Supplier;

final class WhereOrJoinClauseBuilderImpl<MODEL, RESULT>
	extends ClausesImplInitial<MODEL, RESULT>
	implements WhereOrJoinBuilderTable<MODEL, RESULT>,
			   IClassicWhereOrJoinBuilderAlias<MODEL, RESULT>,
			   IClassicAndOrLogicalClausesTable<MODEL, RESULT>,
			   IClassicAndOrLogicalClausesAlias<MODEL, RESULT>,
			   
			   JoinConditionTable<MODEL, RESULT, Object, Object>,
			   IClassicJoinConditionAlias<MODEL, RESULT> {

	WhereOrJoinClauseBuilderImpl(BaseQueryEntity<MODEL> last) {
		super(last, new ClauseCollectorImpl());
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
	private <LEFT, RIGHT> JoinConditionTable<MODEL, RESULT, LEFT, RIGHT> getJoinConditionTable() {
		return (JoinConditionTable<MODEL, RESULT, LEFT, RIGHT>)this;
	}
	
	@Override
	public <LEFT, RIGHT> JoinConditionTable<MODEL, RESULT, LEFT, RIGHT> innerJoin(Class<LEFT> leftType, Class<RIGHT> rightType) {

		final CollectedJoinClasses collectedJoin = new CollectedJoinClasses(JoinType.INNER, leftType, rightType);
		
		addJoin(collectedJoin);
		
		return getJoinConditionTable();
	}

	@Override
	public <LEFT, RIGHT> JoinConditionTable<MODEL, RESULT, LEFT, RIGHT> leftJoin(Class<LEFT> leftType, Class<RIGHT> rightType) {

		final CollectedJoinClasses collectedJoin = new CollectedJoinClasses(JoinType.LEFT, leftType, rightType);
		
		addJoin(collectedJoin);
		
		return getJoinConditionTable();
	}

	
	@Override
	public JoinConditionTable<MODEL, RESULT, Object, Object> on(CollectionFunction<Object, Object> joinCollection) {
		final FunctionGetter collectionGetter = new FunctionGetter(joinCollection); 

		final CollectedJoin curJoin = getQueryCollector().getJoins().getLast();

		final CollectedJoinCondition joinCondition = new CollectedJoinConditionOneToManyClass(collectionGetter);
		
		curJoin.addJoinCondition(joinCondition);
		
		return getJoinConditionTable();
	}

	@Override
	public JoinConditionTable<MODEL, RESULT, Object, Object> compare(IntegerFunction<Object> left, IntegerFunction<Object> right) {
		
		final FunctionGetter leftGetter = new FunctionGetter(left); 
		final FunctionGetter rightGetter = new FunctionGetter(right); 
		
		final CollectedJoin curJoin = getQueryCollector().getJoins().getLast();
		
		final CollectedJoinCondition joinCondition = new CollectedJoinConditionComparisonClasses(leftGetter, rightGetter);
		
		curJoin.addJoinCondition(joinCondition);
		
		return getJoinConditionTable();
	}
	
	// -- Alias  --
	
	private <LEFT, RIGHT> IClassicJoinConditionAlias<MODEL, RESULT> getJoinConditionAlias() {
		return (IClassicJoinConditionAlias<MODEL, RESULT>)this;
	}
	

	@Override
	public IClassicJoinConditionAlias<MODEL, RESULT> innerJoin(Object left, Object right) {

		final CollectedJoinAliases collectedJoin = new CollectedJoinAliases(JoinType.INNER, (IAlias)left, (IAlias)right);
		
		addJoin(collectedJoin);
		
		return getJoinConditionAlias();
	}

	@Override
	public IClassicJoinConditionAlias<MODEL, RESULT> leftJoin(Object left, Object right) {

		final CollectedJoinAliases collectedJoin = new CollectedJoinAliases(JoinType.LEFT, (IAlias)left, (IAlias)right);
		
		addJoin(collectedJoin);
		
		return getJoinConditionAlias();
	}

	private <R> IClassicJoinConditionAlias<MODEL, RESULT> compareAlias(Supplier<R> left, Supplier<R> right) {
		
		final SupplierGetter leftGetter = new SupplierGetter(left); 
		final SupplierGetter rightGetter = new SupplierGetter(right); 
		
		final CollectedJoin curJoin = getQueryCollector().getJoins().getLast();
		
		final CollectedJoinCondition joinCondition = new CollectedJoinConditionComparisonAliases(leftGetter, rightGetter);
		
		curJoin.addJoinCondition(joinCondition);
		
		return getJoinConditionAlias();
	}

	@Override
	public IClassicJoinConditionAlias<MODEL, RESULT> on(CollectionSupplier joinCollection) {
		final SupplierGetter collectionGetter = new SupplierGetter(joinCollection); 
		
		final CollectedJoin curJoin = getQueryCollector().getJoins().getLast();
		
		final CollectedJoinCondition joinCondition = new CollectedJoinConditionOneToManyAlias(collectionGetter);
		
		curJoin.addJoinCondition(joinCondition);
		
		return getJoinConditionAlias();
	}

	@Override
	public IClassicJoinConditionAlias<MODEL, RESULT> compare(IntegerSupplier left, IntegerSupplier right) {
		return compareAlias(left, right);
	}
	
	@Override
	public IClassicJoinConditionAlias<MODEL, RESULT> compare(LongSupplier left, LongSupplier right) {
		return compareAlias(left, right);
	}

	// ------------------------  WHERE ------------------------
	@Override
	public <T, RR> ISharedConditionClause<MODEL, RESULT, RR, IClassicAndOrLogicalClausesTable<MODEL, RESULT>> where(Function<T, RR> getter) {

		return new ConditionClauseImpl<MODEL, RESULT, RR, IClassicAndOrLogicalClausesTable<MODEL,RESULT>>(this, makeGetter(getter));
	}

	@Override
	public <T> ISharedStringClause<MODEL, RESULT, IClassicAndOrLogicalClausesTable<MODEL, RESULT>> where(StringFunction<T> getter) {
		
		return new StringClauseImpl<MODEL, RESULT, IClassicAndOrLogicalClausesTable<MODEL,RESULT>>(this, makeGetter(getter));
	}
	
	@Override
	public <R> ISharedConditionClause<MODEL, RESULT, R, IClassicAndOrLogicalClausesAlias<MODEL, RESULT>> where(Supplier<R> func) {

		return new ConditionClauseImpl<MODEL, RESULT, R, IClassicAndOrLogicalClausesAlias<MODEL,RESULT>>(this, makeGetter(func));
	}

	@Override
	public ISharedStringClause<MODEL, RESULT, IClassicAndOrLogicalClausesAlias<MODEL, RESULT>> where(StringSupplier supplier) {

		return new StringClauseImpl<MODEL, RESULT, IClassicAndOrLogicalClausesAlias<MODEL,RESULT>>(this, makeGetter(supplier));
	}

	// ------------------------  AND ------------------------

	
	@Override
	public <T> ISharedConditionClauseTable<MODEL, RESULT, Integer, IClassicAndClausesTable<MODEL, RESULT>> and(IntegerFunction<T> getter) {
		return andClassImpl(getter);
	}

	@Override
	public <T> ISharedConditionClauseTable<MODEL, RESULT, Long, IClassicAndClausesTable<MODEL, RESULT>> and(LongFunction<T> getter) {
		return andClassImpl(getter);
	}

	@Override
	public <T> ISharedStringClause<MODEL, RESULT, IClassicAndClausesTable<MODEL, RESULT>> and(StringFunction<T> getter) {
		
		final AndClausesImpl<MODEL, RESULT> andClauses = new AndClausesImpl<>(this);
		
		return new StringClauseImpl<MODEL, RESULT, IClassicAndClausesTable<MODEL,RESULT>>(andClauses, makeGetter(getter));
	}
	
	@Override
	public ISharedConditionClauseAlias<MODEL, RESULT, Integer, IClassicAndClausesAlias<MODEL, RESULT>> and(IntegerSupplier getter) {
		return andAliasImpl(getter);
	}

	@Override
	public ISharedConditionClauseAlias<MODEL, RESULT, Long, IClassicAndClausesAlias<MODEL, RESULT>> and(LongSupplier getter) {
		return andAliasImpl(getter);
	}

	@Override
	public ISharedStringClause<MODEL, RESULT, IClassicAndClausesAlias<MODEL, RESULT>> and(StringSupplier getter) {

		final AndClausesImpl<MODEL, RESULT> andClauses = new AndClausesImpl<>(this);
		
		return new StringClauseImpl<MODEL, RESULT, IClassicAndClausesAlias<MODEL,RESULT>>(andClauses, makeGetter(getter));
	}
	

	// ------------------------  OR ------------------------

	@Override
	public <T> ISharedConditionClause<MODEL, RESULT, Integer, IClassicOrClausesTable<MODEL, RESULT>> or(IntegerFunction<T> getter) {
		return orClassImpl(getter);
	}

	
	@Override
	public <T> ISharedConditionClause<MODEL, RESULT, Long, IClassicOrClausesTable<MODEL, RESULT>> or(LongFunction<T> getter) {
		return orClassImpl(getter);
	}


	@Override
	public <T> ISharedStringClause<MODEL, RESULT, IClassicOrClausesTable<MODEL, RESULT>> or(StringFunction<T> getter) {
		final OrClausesImpl<MODEL, RESULT> orClauses = new OrClausesImpl<>(this);

		return new StringClauseImpl<MODEL, RESULT, IClassicOrClausesTable<MODEL,RESULT>>(orClauses, makeGetter(getter));
	}
	
	@Override
	public ISharedConditionClause<MODEL, RESULT, Integer, IClassicOrClausesAlias<MODEL, RESULT>> or(IntegerSupplier getter) {
		return orAliasImpl(this, getter);
	}
	
	@Override
	public ISharedConditionClause<MODEL, RESULT, Long, IClassicOrClausesAlias<MODEL, RESULT>> or(LongSupplier getter) {
		return orAliasImpl(this, getter);
	}

	@Override
	public ISharedStringClause<MODEL, RESULT, IClassicOrClausesAlias<MODEL, RESULT>> or(StringSupplier getter) {

		final OrClausesImpl<MODEL, RESULT> orClauses = new OrClausesImpl<>(this);

		return new StringClauseImpl<MODEL, RESULT, IClassicOrClausesAlias<MODEL,RESULT>>(orClauses, makeGetter(getter));
	}

	// ------------------------  AND helpers ------------------------

	private <T, RR, AND_CLAUSES extends ISharedAndClausesTable<MODEL, RESULT, AND_CLAUSES>>
		ISharedConditionClauseTable<MODEL, RESULT, RR, AND_CLAUSES> andClassImpl(Function<T, RR> getter) {
		
		final AndClausesImpl<MODEL, RESULT> andClauses = new AndClausesImpl<>(this);
		
		return new ConditionClauseImpl<MODEL, RESULT, RR, AND_CLAUSES>(andClauses, makeGetter(getter));
	}

	private <RR, AND_CLAUSES extends ISharedAndClausesAlias<MODEL, RESULT, AND_CLAUSES>>
		ISharedConditionClauseAlias<MODEL, RESULT, RR, AND_CLAUSES> andAliasImpl(Supplier<RR> getter) {

		final AndClausesImpl<MODEL, RESULT> andClauses = new AndClausesImpl<>(this);
		
		return new ConditionClauseImpl<MODEL, RESULT, RR, AND_CLAUSES>(andClauses, makeGetter(getter));
	}

	// ------------------------  OR helpers ------------------------
	private <T, RR, OR_CLAUSES extends ISharedOrClausesTable<MODEL, RESULT, OR_CLAUSES>>
		ISharedConditionClause<MODEL, RESULT, RR, OR_CLAUSES> orClassImpl(Function<T, RR> getter) {
		
		final OrClausesImpl<MODEL, RESULT> orClauses = new OrClausesImpl<>(this);

		return new ConditionClauseImpl<MODEL, RESULT, RR, OR_CLAUSES>(orClauses, makeGetter(getter));
	}

	private <RR, OR_CLAUSES extends ISharedOrClausesAlias<MODEL, RESULT, OR_CLAUSES>>
		ISharedConditionClause<MODEL, RESULT, RR, OR_CLAUSES> orAliasImpl(ClausesImplInitial<MODEL, RESULT> last, Supplier<RR> getter) {

		final OrClausesImpl<MODEL, RESULT> orClauses = new OrClausesImpl<>(last);

		return new ConditionClauseImpl<MODEL, RESULT, RR, OR_CLAUSES>(orClauses, makeGetter(getter));
	}
}

