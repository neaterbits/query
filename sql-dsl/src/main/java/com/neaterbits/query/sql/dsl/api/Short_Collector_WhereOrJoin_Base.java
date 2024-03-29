package com.neaterbits.query.sql.dsl.api;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

abstract class Short_Collector_WhereOrJoin_Base<
		MODEL,
		RESULT,
		
		MAPPED_NAMED_WHERE_OR_JOIN extends ISQLLogical_WhereOrJoin_Named_Base<MODEL, RESULT>,
		MAPPED_ALIAS_WHERE_OR_JOIN extends ISQLLogical_WhereOrJoin_Alias_Base<MODEL, RESULT>,
		
		/*
		MAPPED_SOURCE_NAMED extends ISharedSelectSourceBuilder<MODEL, RESULT>,
		MAPPED_SOURCE_ALIAS extends ISharedSelectSourceBuilder<MODEL, RESULT>,
		*/
		
		NAMED_AND_CLAUSES  extends ISharedLogical_And_Named_All <MODEL, RESULT, NAMED_AND_CLAUSES,  NAMED_NESTED_OR_CLAUSES>,
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
			
			
		AFTER_GROUP_BY
		> 
			
	extends SQL_Collector_WhereOrJoin_Base<
			MODEL,
			RESULT,
			
			NAMED_AND_CLAUSES, NAMED_OR_CLAUSES, NAMED_NESTED_AND_CLAUSES, NAMED_NESTED_OR_CLAUSES, NAMED_JOIN_CONDITION, NAMED_AND_OR, ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>,
			ALIAS_AND_CLAUSES, ALIAS_OR_CLAUSES, ALIAS_NESTED_AND_CLAUSES, ALIAS_NESTED_OR_CLAUSES, ALIAS_JOIN_CONDITION, ALIAS_AND_OR, ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>,
			
			AFTER_GROUP_BY> 

	// Have to move this to leaf classes to avoid name clash
	// implements IShortJoin_Named_Base<MODEL, RESULT, RESULT, MAPPED_NAMED_WHERE_OR_JOIN>

	{
				
		/*
	Short_Collector_WhereOrJoin_Base(Collector_Query<MODEL> queryCollector, ICollectorClause clause) {
		super(queryCollector, clauseCollector);
	}
	*/
	Short_Collector_WhereOrJoin_Base(Collector_Query<MODEL> queryCollector) {
		super(queryCollector);
	}

	Short_Collector_WhereOrJoin_Base(Collector_Conditions_Initial<MODEL, RESULT, AFTER_GROUP_BY> last) {
		super(last);
	}
	
	Short_Collector_WhereOrJoin_Base(Collector_Conditions_Initial<MODEL, RESULT, AFTER_GROUP_BY> last, CollectedQueryResult result) {
		super(last, result);
	}
	
	
	
	/***********************************************************************
	 * Named join
	 **********************************************************************/
	@SuppressWarnings("unchecked")
	private MAPPED_NAMED_WHERE_OR_JOIN addNamedJoin(CollectedJoin_Named collectedJoin) {
		addJoin(collectedJoin);
		
		return (MAPPED_NAMED_WHERE_OR_JOIN)this;
	}

	private <TO> Collector_Joins buildSubJoinsNamed(Consumer<IShortJoin_Sub_Named<MODEL, RESULT, TO, Void>> consumer) {

		final Collector_Joins collector = new Collector_Joins();
		
		final ShortJoin_Sub_Named_Builder<TO> builder = new ShortJoin_Sub_Named_Builder<>(collector);
		
		consumer.accept(builder);

		return collector;
	}

	@SuppressWarnings("unchecked")
	final <FROM, TO, R, RET> RET addInnerJoin(Function<FROM, R> from, Function<TO, R> to) {
		if (from == null) {
			throw new IllegalArgumentException("from == null");
		}
		
		if (to == null) {
			throw new IllegalArgumentException("to == null");
		}

		return (RET)collectJoin(EJoinType.INNER, from, to, null, this::addNamedJoin);
	}
	
	@SuppressWarnings("unchecked")
	final <FROM, TO, R, RET> RET addInnerJoin(Function<FROM, R> from, Function<TO, R> to, Consumer<IShortJoin_Sub_Named<MODEL, RESULT, TO, Void>> consumer) {
		
		if (from == null) {
			throw new IllegalArgumentException("from == null");
		}
		
		if (to == null) {
			throw new IllegalArgumentException("to == null");
		}
		
		if (consumer == null) {
			throw new IllegalArgumentException("consumer == null");
		}
		
		return (RET)collectJoin(EJoinType.INNER, from, to, consumer, this::addNamedJoin);
	}
	
	@SuppressWarnings("unchecked")
	final <FROM, TO, RET> RET addInnerJoin(CollectionFunction<FROM, TO> collection) {
		if (collection == null) {
			throw new IllegalArgumentException("collection == null");
		}
		
		return (RET)collectJoin(EJoinType.INNER, collection, null, this::addNamedJoin);
	}
	
	@SuppressWarnings("unchecked")
	final <FROM, TO, RET> RET addInnerJoin(CollectionFunction<FROM, TO> collection, Consumer<IShortJoin_Sub_Named<MODEL, RESULT, TO, Void>> consumer) {
		if (collection == null) {
			throw new IllegalArgumentException("collection == null");
		}
		
		if (consumer == null) {
			throw new IllegalArgumentException("consumer == null");
		}
		
		return (RET)collectJoin(EJoinType.INNER, collection, consumer, this::addNamedJoin);
	}

	@SuppressWarnings("unchecked")
	final <FROM, TO, R, RET> RET addLeftJoin(Function<FROM, R> from, Function<TO, R> to) {
		if (from == null) {
			throw new IllegalArgumentException("from == null");
		}
		
		if (to == null) {
			throw new IllegalArgumentException("to == null");
		}

		return (RET)collectJoin(EJoinType.LEFT, from, to, null, this::addNamedJoin);
	}
	
	@SuppressWarnings("unchecked")
	final <FROM, TO, R, RET> RET addLeftJoin(Function<FROM, R> from, Function<TO, R> to, Consumer<IShortJoin_Sub_Named<MODEL, RESULT, TO, Void>> consumer) {
		
		if (from == null) {
			throw new IllegalArgumentException("from == null");
		}
		
		if (to == null) {
			throw new IllegalArgumentException("to == null");
		}
		
		if (consumer == null) {
			throw new IllegalArgumentException("consumer == null");
		}
		
		return (RET)collectJoin(EJoinType.LEFT, from, to, consumer, this::addNamedJoin);
	}
	
	@SuppressWarnings("unchecked")
	final <FROM, TO, RET> RET addLeftJoin(CollectionFunction<FROM, TO> collection) {
		if (collection == null) {
			throw new IllegalArgumentException("collection == null");
		}
		
		return (RET)collectJoin(EJoinType.LEFT, collection, null, this::addNamedJoin);
	}
	
	@SuppressWarnings("unchecked")
	final <FROM, TO, RET> RET addLeftJoin(CollectionFunction<FROM, TO> collection, Consumer<IShortJoin_Sub_Named<MODEL, RESULT, TO, Void>> consumer) {
		if (collection == null) {
			throw new IllegalArgumentException("collection == null");
		}
		
		if (consumer == null) {
			throw new IllegalArgumentException("consumer == null");
		}
		
		return (RET)collectJoin(EJoinType.LEFT, collection, consumer, this::addNamedJoin);
	}
		
	private <FROM, TO, R, RET> RET collectJoin(
			EJoinType joinType,
			Function<FROM, R> from,
			Function<TO, R> to,
			
			Consumer<IShortJoin_Sub_Named<MODEL, RESULT, TO, Void>> consumer,
			Function<CollectedJoin_Named, RET> collect) {
		
		final Collector_Joins sub = consumer != null ? buildSubJoinsNamed(consumer) : null;
		
		final CollectedJoin_Named join = new CollectedJoin_Named(joinType, from, to, sub);
		
		return collect.apply(join);
	}
	
	private <FROM, TO, RET> RET collectJoin(
			EJoinType joinType,
			CollectionFunction<FROM, TO> collection,
			Consumer<IShortJoin_Sub_Named<MODEL, RESULT, TO, Void>> consumer,
			Function<CollectedJoin_Named, RET> collect) {
		
		final Collector_Joins sub = consumer != null ? buildSubJoinsNamed(consumer) : null;
		
		final CollectedJoin_Named join = new CollectedJoin_Named(joinType, collection, sub);
		
		return collect.apply(join);
	}
	
	private class ShortJoin_Sub_Named_Builder<TO> implements IShortJoin_Sub_Named<MODEL, RESULT, TO, Void> {

		private final Collector_Joins joins;
		
		ShortJoin_Sub_Named_Builder(Collector_Joins joins) {
			this.joins = joins;
		}

		private Void addNamedJoin(CollectedJoin join) {
			
			if (join == null) {
				throw new IllegalArgumentException("join == null");
			}

			joins.addJoin(join);
			
			return null;
		}
		
		@Override
		public <JOIN_TO, R extends Comparable<R>> Void innerJoin(Function<TO, R> from, Function<JOIN_TO, R> to) {
			return collectJoin(EJoinType.INNER, from, to, null, this::addNamedJoin);
		}

		@Override
		public <JOIN_TO> Void innerJoin(CollectionFunction<TO, JOIN_TO> collection) {
			return collectJoin(EJoinType.INNER, collection, null, this::addNamedJoin);
		}

		@Override
		public <JOIN_TO, R extends Comparable<R>> Void innerJoin(Function<TO, R> from, Function<JOIN_TO, R> to,
				Consumer<IShortJoin_Sub_Named<MODEL, RESULT, JOIN_TO, Void>> consumer) {
			return collectJoin(EJoinType.INNER, from, to, consumer, this::addNamedJoin);
		}

		@Override
		public <JOIN_TO> Void innerJoin(CollectionFunction<TO, JOIN_TO> collection,
				Consumer<IShortJoin_Sub_Named<MODEL, RESULT, JOIN_TO, Void>> consumer) {
			return collectJoin(EJoinType.INNER, collection, consumer, this::addNamedJoin);
		}

		@Override
		public <JOIN_TO, R extends Comparable<R>> Void leftJoin(Function<TO, R> from, Function<JOIN_TO, R> to) {
			return collectJoin(EJoinType.LEFT, from, to, null, this::addNamedJoin);
		}

		@Override
		public <JOIN_TO> Void leftJoin(CollectionFunction<TO, JOIN_TO> collection) {
			return collectJoin(EJoinType.LEFT, collection, null, this::addNamedJoin);
		}

		@Override
		public <JOIN_TO, R extends Comparable<R>> Void leftJoin(Function<TO, R> from, Function<JOIN_TO, R> to,
				Consumer<IShortJoin_Sub_Named<MODEL, RESULT, JOIN_TO, Void>> consumer) {
			return collectJoin(EJoinType.LEFT, from, to, consumer, this::addNamedJoin);
		}

		@Override
		public <JOIN_TO> Void leftJoin(CollectionFunction<TO, JOIN_TO> collection, Consumer<IShortJoin_Sub_Named<MODEL, RESULT, JOIN_TO, Void>> consumer) {
			return collectJoin(EJoinType.LEFT, collection, consumer, this::addNamedJoin);
		}
	}
	
	
	/***********************************************************************
	 * Alias join
	 **********************************************************************/
	@SuppressWarnings("unchecked")
	private MAPPED_ALIAS_WHERE_OR_JOIN addAliasJoin(CollectedJoin_Alias collectedJoin) {
		addJoin(collectedJoin);
		
		return (MAPPED_ALIAS_WHERE_OR_JOIN)this;
	}

	private <TO> Collector_Joins buildSubJoinsAlias(Consumer<IShortJoin_Sub_Alias<MODEL, RESULT, Void>> consumer) {

		final Collector_Joins collector = new Collector_Joins();
		
		final ShortJoin_Sub_Alias_Builder<TO> builder = new ShortJoin_Sub_Alias_Builder<>(collector);
		
		consumer.accept(builder);

		return collector;
	}

	@SuppressWarnings("unchecked")
	final <R, RET> RET addInnerJoin(Supplier<R> from, Supplier<R> to) {
		if (from == null) {
			throw new IllegalArgumentException("from == null");
		}
		
		if (to == null) {
			throw new IllegalArgumentException("to == null");
		}

		return (RET)collectJoin(EJoinType.INNER, from, to, null, this::addAliasJoin);
	}
	
	@SuppressWarnings("unchecked")
	final <R, RET> RET addInnerJoin(Supplier<R> from, Supplier<R> to, Consumer<IShortJoin_Sub_Alias<MODEL, RESULT, Void>> consumer) {
		
		if (from == null) {
			throw new IllegalArgumentException("from == null");
		}
		
		if (to == null) {
			throw new IllegalArgumentException("to == null");
		}
		
		if (consumer == null) {
			throw new IllegalArgumentException("consumer == null");
		}
		
		return (RET)collectJoin(EJoinType.INNER, from, to, consumer, this::addAliasJoin);
	}
	
	@SuppressWarnings("unchecked")
	final <TO, RET> RET addInnerJoin(CollectionSupplier<TO> collection, TO alias) {
		if (collection == null) {
			throw new IllegalArgumentException("collection == null");
		}
		
		return (RET)collectJoin(EJoinType.INNER, collection, alias, null, this::addAliasJoin);
	}
	
	@SuppressWarnings("unchecked")
	final <TO, RET> RET addInnerJoin(CollectionSupplier<TO> collection, TO alias, Consumer<IShortJoin_Sub_Alias<MODEL, RESULT, Void>> consumer) {
		if (collection == null) {
			throw new IllegalArgumentException("collection == null");
		}
		
		if (consumer == null) {
			throw new IllegalArgumentException("consumer == null");
		}
		
		return (RET)collectJoin(EJoinType.INNER, collection, alias, consumer, this::addAliasJoin);
	}

	@SuppressWarnings("unchecked")
	final <R, RET> RET addLeftJoin(Supplier<R> from, Supplier<R> to) {
		if (from == null) {
			throw new IllegalArgumentException("from == null");
		}
		
		if (to == null) {
			throw new IllegalArgumentException("to == null");
		}

		return (RET)collectJoin(EJoinType.LEFT, from, to, null, this::addAliasJoin);
	}
	
	@SuppressWarnings("unchecked")
	final <R, RET> RET addLeftJoin(Supplier<R> from, Supplier<R> to, Consumer<IShortJoin_Sub_Alias<MODEL, RESULT, Void>> consumer) {
		
		if (from == null) {
			throw new IllegalArgumentException("from == null");
		}
		
		if (to == null) {
			throw new IllegalArgumentException("to == null");
		}
		
		if (consumer == null) {
			throw new IllegalArgumentException("consumer == null");
		}
		
		return (RET)collectJoin(EJoinType.LEFT, from, to, consumer, this::addAliasJoin);
	}
	
	@SuppressWarnings("unchecked")
	final <TO, RET> RET addLeftJoin(CollectionSupplier<TO> collection, TO alias) {
		if (collection == null) {
			throw new IllegalArgumentException("collection == null");
		}
		
		return (RET)collectJoin(EJoinType.LEFT, collection, alias ,null, this::addAliasJoin);
	}
	
	@SuppressWarnings("unchecked")
	final <TO, RET> RET addLeftJoin(CollectionSupplier<TO> collection, TO alias, Consumer<IShortJoin_Sub_Alias<MODEL, RESULT, Void>> consumer) {
		if (collection == null) {
			throw new IllegalArgumentException("collection == null");
		}
		
		if (consumer == null) {
			throw new IllegalArgumentException("consumer == null");
		}
		
		return (RET)collectJoin(EJoinType.LEFT, collection, alias, consumer, this::addAliasJoin);
	}
		
	private <TO, R, RET> RET collectJoin(
			EJoinType joinType,
			Supplier<R> from,
			Supplier<R> to,
			
			Consumer<IShortJoin_Sub_Alias<MODEL, RESULT, Void>> consumer,
			Function<CollectedJoin_Alias, RET> collect) {
		
		final Collector_Joins sub = consumer != null ? buildSubJoinsAlias(consumer) : null;
		
		final CollectedJoin_Alias join = new CollectedJoin_Alias(joinType, from, to, sub);
		
		return collect.apply(join);
	}
	
	private <TO, RET> RET collectJoin(
			EJoinType joinType,
			CollectionSupplier<TO> collection,
			TO alias,
			Consumer<IShortJoin_Sub_Alias<MODEL, RESULT, Void>> consumer,
			Function<CollectedJoin_Alias, RET> collect) {
		
		final Collector_Joins sub = consumer != null ? buildSubJoinsAlias(consumer) : null;

		final CollectedJoin_Alias join = new CollectedJoin_Alias(joinType, collection, sub, (IAlias)alias);

		return collect.apply(join);
	}
	
	private class ShortJoin_Sub_Alias_Builder<TO> implements IShortJoin_Sub_Alias<MODEL, RESULT, Void> {

		private final Collector_Joins joins;
		
		ShortJoin_Sub_Alias_Builder(Collector_Joins joins) {
			this.joins = joins;
		}

		private Void addAliasJoin(CollectedJoin join) {
			
			if (join == null) {
				throw new IllegalArgumentException("join == null");
			}

			joins.addJoin(join);
			
			return null;
		}
		
		@Override
		public <R extends Comparable<R>> Void innerJoin(Supplier<R> from, Supplier<R> to) {
			return collectJoin(EJoinType.INNER, from, to, null, this::addAliasJoin);
		}

		@Override
		public <JOIN_TO> Void innerJoin(CollectionSupplier<JOIN_TO> collection, JOIN_TO alias) {
			return collectJoin(EJoinType.INNER, collection, alias, null, this::addAliasJoin);
		}

		@Override
		public <R extends Comparable<R>> Void innerJoin(Supplier<R> from, Supplier<R> to, Consumer<IShortJoin_Sub_Alias<MODEL, RESULT, Void>> consumer) {
			return collectJoin(EJoinType.INNER, from, to, consumer, this::addAliasJoin);
		}

		@Override
		public <JOIN_TO> Void innerJoin(CollectionSupplier<JOIN_TO> collection, JOIN_TO alias, Consumer<IShortJoin_Sub_Alias<MODEL, RESULT, Void>> consumer) {
			return collectJoin(EJoinType.INNER, collection, alias, consumer, this::addAliasJoin);
		}

		@Override
		public <R extends Comparable<R>> Void leftJoin(Supplier<R> from, Supplier<R> to) {
			return collectJoin(EJoinType.LEFT, from, to, null, this::addAliasJoin);
		}

		@Override
		public <JOIN_TO> Void leftJoin(CollectionSupplier<JOIN_TO> collection, JOIN_TO alias) {
			return collectJoin(EJoinType.LEFT, collection, alias, null, this::addAliasJoin);
		}

		@Override
		public <R extends Comparable<R>> Void leftJoin(Supplier<R> from, Supplier<R> to,
				Consumer<IShortJoin_Sub_Alias<MODEL, RESULT, Void>> consumer) {
			return collectJoin(EJoinType.LEFT, from, to, consumer, this::addAliasJoin);
		}

		@Override
		public <JOIN_TO> Void leftJoin(CollectionSupplier<JOIN_TO> collection, JOIN_TO alias, Consumer<IShortJoin_Sub_Alias<MODEL, RESULT, Void>> consumer) {
			return collectJoin(EJoinType.LEFT, collection, alias, consumer, this::addAliasJoin);
		}
	}

	
	/**************************************************************************
	 * And/Or
	 *************************************************************************/
	
	

	@Override
	@SuppressWarnings({"unchecked", "rawtypes"})
	final Collector_Or_Named<
			MODEL,
			RESULT,
			NAMED_NESTED_OR_CLAUSES,
			NAMED_NESTED_AND_CLAUSES,
			NAMED_NESTED_OR_CLAUSES,
			ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>
		>
	
		createNamedNestedOrCollector(
			Collector_And_Named<
				MODEL,
				RESULT,
				NAMED_AND_CLAUSES,
				NAMED_NESTED_AND_CLAUSES,
				NAMED_NESTED_OR_CLAUSES,
				ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>> andClauses) {
		
		return (Collector_Or_Named)new SQL_Collector_Or_NonProcessResult_Named<>(andClauses, null);
	}

	@Override
	@SuppressWarnings({"unchecked", "rawtypes"})
	final Collector_And_Named<
			MODEL,
			RESULT,
			NAMED_NESTED_AND_CLAUSES,
			NAMED_NESTED_AND_CLAUSES,
			NAMED_NESTED_OR_CLAUSES,
			ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>
		
		>
	
		createNamedNestedAndCollector(
					
				Collector_Or_Named<
						MODEL,
						RESULT,
						NAMED_OR_CLAUSES,
						NAMED_NESTED_AND_CLAUSES,
						NAMED_NESTED_OR_CLAUSES, ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>> orClauses) {
		
		return (Collector_And_Named)new SQL_Collector_And_NonProcessResult_Named<>(orClauses);
	}

	@Override
	@SuppressWarnings({"unchecked", "rawtypes"})
	final Collector_And_Alias<
			MODEL,
			RESULT,
			ALIAS_NESTED_AND_CLAUSES,
			ALIAS_NESTED_AND_CLAUSES,
			ALIAS_NESTED_OR_CLAUSES,
			ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>
		>
	
		createAliasNestedAndCollector(
					Collector_Or_Alias<
							MODEL,
							RESULT,
							ALIAS_OR_CLAUSES,
							ALIAS_NESTED_AND_CLAUSES,
							ALIAS_NESTED_OR_CLAUSES,
							ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>> orClauses) {
		
		return (Collector_And_Alias)new SQL_Collector_And_NonProcessResult_Alias<>(orClauses);
	}

	@Override
	@SuppressWarnings({"unchecked", "rawtypes"})
	final Collector_Or_Alias<
			MODEL,
			RESULT,
			ALIAS_NESTED_OR_CLAUSES,
			ALIAS_NESTED_AND_CLAUSES,
			ALIAS_NESTED_OR_CLAUSES, 
			ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>
		>
		createAliasNestedOrCollector(
					Collector_And_Alias<
						MODEL,
						RESULT,
						ALIAS_AND_CLAUSES, 
						ALIAS_NESTED_AND_CLAUSES, 
						ALIAS_NESTED_OR_CLAUSES,
						ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>> andClauses) {
		
		return (Collector_Or_Alias)new SQL_Collector_Or_NonProcessResult_Alias<>(andClauses);
	}
}
