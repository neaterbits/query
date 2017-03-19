package com.neaterbits.query.sql.dsl.api;

import java.util.function.Consumer;
import java.util.function.Function;

abstract class Short_Collector_WhereOrJoin_TypedJoin_Base<
	MODEL,
	RESULT,
	
	JOIN_FROM,
	
	MAPPED_NAMED_WHERE_OR_JOIN extends ISQLLogical_WhereOrJoin_Named_Base<MODEL, RESULT>,
	MAPPED_ALIAS_WHERE_OR_JOIN extends ISQLLogical_WhereOrJoin_Alias_Base<MODEL, RESULT>,
	
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
		
	AFTER_GROUP_BY>
 
		
		extends Short_Collector_WhereOrJoin_Base<
			MODEL, RESULT,
			
			MAPPED_NAMED_WHERE_OR_JOIN,
			MAPPED_ALIAS_WHERE_OR_JOIN,
			
			NAMED_AND_CLAUSES,
			NAMED_OR_CLAUSES,
			NAMED_NESTED_AND_CLAUSES,
			NAMED_NESTED_OR_CLAUSES,
			
			NAMED_JOIN_CONDITION,
			NAMED_AND_OR,
			
			ALIAS_AND_CLAUSES,
			ALIAS_OR_CLAUSES,
			ALIAS_NESTED_AND_CLAUSES,
			ALIAS_NESTED_OR_CLAUSES,
			
			ALIAS_JOIN_CONDITION,
			ALIAS_AND_OR,
			
			AFTER_GROUP_BY>
		
		
		implements IShortJoin_Named_Base<MODEL, RESULT, JOIN_FROM, MAPPED_NAMED_WHERE_OR_JOIN>
		
		{
				
				

	Short_Collector_WhereOrJoin_TypedJoin_Base(Collector_Query<MODEL> queryCollector, Collector_Clause clauseCollector) {
		super(queryCollector, clauseCollector);
	}

	@Override
	public final <JOIN_TO, R extends Comparable<R>> MAPPED_NAMED_WHERE_OR_JOIN innerJoin(Function<JOIN_FROM, R> from, Function<JOIN_TO, R> to) {
		return collectJoin(EJoinType.INNER, from, to, null, this::addNamedJoin);
	}

	@Override
	public final <JOIN_TO> MAPPED_NAMED_WHERE_OR_JOIN innerJoin(CollectionFunction<JOIN_FROM, JOIN_TO> collection) {
		return collectJoin(EJoinType.INNER, collection, null, this::addNamedJoin);
	}

	@Override
	public final <JOIN_TO, R extends Comparable<R>> MAPPED_NAMED_WHERE_OR_JOIN innerJoin(
			Function<JOIN_FROM, R> from, Function<JOIN_TO, R> to,
			Consumer<IShortJoin_Sub_Named<MODEL, RESULT, JOIN_TO, Void>> consumer) {

		return collectJoin(EJoinType.INNER, from, to, consumer, this::addNamedJoin);
	}

	@Override
	public final <JOIN_TO> MAPPED_NAMED_WHERE_OR_JOIN innerJoin(
			CollectionFunction<JOIN_FROM, JOIN_TO> collection,
			Consumer<IShortJoin_Sub_Named<MODEL, RESULT, JOIN_TO, Void>> consumer) {
		
		return collectJoin(EJoinType.INNER, collection, consumer, this::addNamedJoin);
	}

	@Override
	public final <JOIN_TO, R extends Comparable<R>> MAPPED_NAMED_WHERE_OR_JOIN leftJoin(Function<JOIN_FROM, R> from, Function<JOIN_TO, R> to) {
		return collectJoin(EJoinType.LEFT, from, to, null, this::addNamedJoin);
	}

	@Override
	public final <JOIN_TO> MAPPED_NAMED_WHERE_OR_JOIN leftJoin(CollectionFunction<JOIN_FROM, JOIN_TO> collection) {
		return collectJoin(EJoinType.LEFT, collection, null, this::addNamedJoin);
	}

	@Override
	public final <JOIN_TO, R extends Comparable<R>> MAPPED_NAMED_WHERE_OR_JOIN leftJoin(
			Function<JOIN_FROM, R> from, Function<JOIN_TO, R> to,
			Consumer<IShortJoin_Sub_Named<MODEL, RESULT, JOIN_TO, Void>> consumer) {
		
		return collectJoin(EJoinType.LEFT, from, to, consumer, this::addNamedJoin);
	}
	
	@Override
	public final <JOIN_TO> MAPPED_NAMED_WHERE_OR_JOIN leftJoin(
			CollectionFunction<JOIN_FROM, JOIN_TO> collection,
			Consumer<IShortJoin_Sub_Named<MODEL, RESULT, JOIN_TO, Void>> consumer) {
		
		return collectJoin(EJoinType.LEFT, collection, consumer, this::addNamedJoin);
	}
}
