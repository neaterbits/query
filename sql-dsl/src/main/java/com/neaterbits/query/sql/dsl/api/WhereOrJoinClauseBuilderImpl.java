package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;
import java.util.function.Supplier;

final class WhereOrJoinClauseBuilderImpl<MODEL, RESULT>
	extends ClausesImplInitial<MODEL, RESULT>
	implements WhereOrJoinBuilderTable<MODEL, RESULT>,
			   WhereOrJoinBuilderAlias<MODEL, RESULT>,
			   AndOrLogicalClausesTable<MODEL, RESULT>,
			   AndOrLogicalClausesAlias<MODEL, RESULT>,
			   
			   JoinConditionTable<MODEL, RESULT, Object, Object>,
			   JoinConditionAlias<MODEL, RESULT> {

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
	
	private <LEFT, RIGHT> JoinConditionAlias<MODEL, RESULT> getJoinConditionAlias() {
		return (JoinConditionAlias<MODEL, RESULT>)this;
	}
	

	@Override
	public JoinConditionAlias<MODEL, RESULT> innerJoin(Object left, Object right) {

		final CollectedJoinAliases collectedJoin = new CollectedJoinAliases(JoinType.INNER, (IAlias)left, (IAlias)right);
		
		addJoin(collectedJoin);
		
		return getJoinConditionAlias();
	}

	@Override
	public JoinConditionAlias<MODEL, RESULT> leftJoin(Object left, Object right) {

		final CollectedJoinAliases collectedJoin = new CollectedJoinAliases(JoinType.LEFT, (IAlias)left, (IAlias)right);
		
		addJoin(collectedJoin);
		
		return getJoinConditionAlias();
	}

	private <R> JoinConditionAlias<MODEL, RESULT> compareAlias(Supplier<R> left, Supplier<R> right) {
		
		final SupplierGetter leftGetter = new SupplierGetter(left); 
		final SupplierGetter rightGetter = new SupplierGetter(right); 
		
		final CollectedJoin curJoin = getQueryCollector().getJoins().getLast();
		
		final CollectedJoinCondition joinCondition = new CollectedJoinConditionComparisonAliases(leftGetter, rightGetter);
		
		curJoin.addJoinCondition(joinCondition);
		
		return getJoinConditionAlias();
	}

	@Override
	public JoinConditionAlias<MODEL, RESULT> on(CollectionSupplier joinCollection) {
		final SupplierGetter collectionGetter = new SupplierGetter(joinCollection); 
		
		final CollectedJoin curJoin = getQueryCollector().getJoins().getLast();
		
		final CollectedJoinCondition joinCondition = new CollectedJoinConditionOneToManyAlias(collectionGetter);
		
		curJoin.addJoinCondition(joinCondition);
		
		return getJoinConditionAlias();
	}

	@Override
	public JoinConditionAlias<MODEL, RESULT> compare(IntegerSupplier left, IntegerSupplier right) {
		return compareAlias(left, right);
	}
	
	@Override
	public JoinConditionAlias<MODEL, RESULT> compare(LongSupplier left, LongSupplier right) {
		return compareAlias(left, right);
	}

	// ------------------------  WHERE ------------------------
	@Override
	public <T, RR> ConditionClause<MODEL, RESULT, RR, AndOrLogicalClausesTable<MODEL, RESULT>> where(Function<T, RR> getter) {

		return new ConditionClauseImpl<MODEL, RESULT, RR, AndOrLogicalClausesTable<MODEL,RESULT>>(this, makeGetter(getter));
	}

	@Override
	public <T> StringClause<MODEL, RESULT, AndOrLogicalClausesTable<MODEL, RESULT>> where(StringFunction<T> getter) {
		
		return new StringClauseImpl<MODEL, RESULT, AndOrLogicalClausesTable<MODEL,RESULT>>(this, makeGetter(getter));
	}
	
	@Override
	public <R> ConditionClause<MODEL, RESULT, R, AndOrLogicalClausesAlias<MODEL, RESULT>> where(Supplier<R> func) {

		return new ConditionClauseImpl<MODEL, RESULT, R, AndOrLogicalClausesAlias<MODEL,RESULT>>(this, makeGetter(func));
	}

	@Override
	public StringClause<MODEL, RESULT, AndOrLogicalClausesAlias<MODEL, RESULT>> where(StringSupplier supplier) {

		return new StringClauseImpl<MODEL, RESULT, AndOrLogicalClausesAlias<MODEL,RESULT>>(this, makeGetter(supplier));
	}

	// ------------------------  AND ------------------------

	
	@Override
	public <T> ConditionClauseTable<MODEL, RESULT, Integer, AndClausesTable<MODEL, RESULT>> and(IntegerFunction<T> getter) {
		return andClassImpl(getter);
	}

	@Override
	public <T> ConditionClauseTable<MODEL, RESULT, Long, AndClausesTable<MODEL, RESULT>> and(LongFunction<T> getter) {
		return andClassImpl(getter);
	}

	@Override
	public <T> StringClause<MODEL, RESULT, AndClausesTable<MODEL, RESULT>> and(StringFunction<T> getter) {
		
		final AndClausesImpl<MODEL, RESULT> andClauses = new AndClausesImpl<>(this);
		
		return new StringClauseImpl<MODEL, RESULT, AndClausesTable<MODEL,RESULT>>(andClauses, makeGetter(getter));
	}
	
	@Override
	public ConditionClauseAlias<MODEL, RESULT, Integer, AndClausesAlias<MODEL, RESULT>> and(IntegerSupplier getter) {
		return andAliasImpl(getter);
	}

	@Override
	public ConditionClauseAlias<MODEL, RESULT, Long, AndClausesAlias<MODEL, RESULT>> and(LongSupplier getter) {
		return andAliasImpl(getter);
	}

	@Override
	public StringClause<MODEL, RESULT, AndClausesAlias<MODEL, RESULT>> and(StringSupplier getter) {

		final AndClausesImpl<MODEL, RESULT> andClauses = new AndClausesImpl<>(this);
		
		return new StringClauseImpl<MODEL, RESULT, AndClausesAlias<MODEL,RESULT>>(andClauses, makeGetter(getter));
	}
	

	// ------------------------  OR ------------------------

	@Override
	public <T> ConditionClause<MODEL, RESULT, Integer, OrClausesTable<MODEL, RESULT>> or(IntegerFunction<T> getter) {
		return orClassImpl(getter);
	}

	
	@Override
	public <T> ConditionClause<MODEL, RESULT, Long, OrClausesTable<MODEL, RESULT>> or(LongFunction<T> getter) {
		return orClassImpl(getter);
	}


	@Override
	public <T> StringClause<MODEL, RESULT, OrClausesTable<MODEL, RESULT>> or(StringFunction<T> getter) {
		final OrClausesImpl<MODEL, RESULT> orClauses = new OrClausesImpl<>(this);

		return new StringClauseImpl<MODEL, RESULT, OrClausesTable<MODEL,RESULT>>(orClauses, makeGetter(getter));
	}
	
	@Override
	public ConditionClause<MODEL, RESULT, Integer, OrClausesAlias<MODEL, RESULT>> or(IntegerSupplier getter) {
		return orAliasImpl(this, getter);
	}
	
	@Override
	public ConditionClause<MODEL, RESULT, Long, OrClausesAlias<MODEL, RESULT>> or(LongSupplier getter) {
		return orAliasImpl(this, getter);
	}

	@Override
	public StringClause<MODEL, RESULT, OrClausesAlias<MODEL, RESULT>> or(StringSupplier getter) {

		final OrClausesImpl<MODEL, RESULT> orClauses = new OrClausesImpl<>(this);

		return new StringClauseImpl<MODEL, RESULT, OrClausesAlias<MODEL,RESULT>>(orClauses, makeGetter(getter));
	}

	// ------------------------  AND helpers ------------------------

	private <T, RR> ConditionClauseTable<MODEL, RESULT, RR, AndClausesTable<MODEL, RESULT>> andClassImpl(Function<T, RR> getter) {
		
		final AndClausesImpl<MODEL, RESULT> andClauses = new AndClausesImpl<>(this);
		
		return new ConditionClauseImpl<MODEL, RESULT, RR, AndClausesTable<MODEL,RESULT>>(andClauses, makeGetter(getter));
	}

	private <RR> ConditionClauseAlias<MODEL, RESULT, RR, AndClausesAlias<MODEL, RESULT>> andAliasImpl(Supplier<RR> getter) {

		final AndClausesImpl<MODEL, RESULT> andClauses = new AndClausesImpl<>(this);
		
		return new ConditionClauseImpl<MODEL, RESULT, RR, AndClausesAlias<MODEL,RESULT>>(andClauses, makeGetter(getter));
	}

	// ------------------------  OR helpers ------------------------
	final <T, RR> ConditionClause<MODEL, RESULT, RR, OrClausesTable<MODEL, RESULT>> orClassImpl(Function<T, RR> getter) {
		final OrClausesImpl<MODEL, RESULT> orClauses = new OrClausesImpl<>(this);

		return new ConditionClauseImpl<MODEL, RESULT, RR, OrClausesTable<MODEL,RESULT>>(orClauses, makeGetter(getter));
	}

	final <RR> ConditionClause<MODEL, RESULT, RR, OrClausesAlias<MODEL, RESULT>> orAliasImpl(ClausesImplInitial<MODEL, RESULT> last, Supplier<RR> getter) {

		final OrClausesImpl<MODEL, RESULT> orClauses = new OrClausesImpl<>(last);

		return new ConditionClauseImpl<MODEL, RESULT, RR, OrClausesAlias<MODEL,RESULT>>(orClauses, makeGetter(getter));
	}
}

