package com.neaterbits.query.sql.dsl.api;

import java.util.function.Consumer;
import java.util.function.Function;

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

	implements IShortJoin_Named_Base<MODEL, RESULT, RESULT, MAPPED_NAMED_WHERE_OR_JOIN>

	{
				
	Short_Collector_WhereOrJoin_Base(Collector_Query<MODEL> queryCollector, Collector_Clause clauseCollector) {
		super(queryCollector, clauseCollector);
	}
				
	@Override
	public final <JOIN_TO, R extends Comparable<R>> MAPPED_NAMED_WHERE_OR_JOIN innerJoin(Function<RESULT, R> from, Function<JOIN_TO, R> to) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public final <JOIN_TO> MAPPED_NAMED_WHERE_OR_JOIN innerJoin(CollectionFunction<RESULT, JOIN_TO> collection) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public final <JOIN_TO, R extends Comparable<R>> MAPPED_NAMED_WHERE_OR_JOIN innerJoin(
			Function<RESULT, R> from, Function<JOIN_TO, R> to,
			Consumer<IShortJoin_Sub_Named<MODEL, RESULT, JOIN_TO, Void>> consumer) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public final <JOIN_TO> MAPPED_NAMED_WHERE_OR_JOIN innerJoin(
			CollectionFunction<RESULT, JOIN_TO> collection,
			Consumer<IShortJoin_Sub_Named<MODEL, RESULT, JOIN_TO, Void>> consumer) {
		
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public final <JOIN_TO, R extends Comparable<R>> MAPPED_NAMED_WHERE_OR_JOIN leftJoin(Function<RESULT, R> from, Function<JOIN_TO, R> to) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public final <JOIN_TO> MAPPED_NAMED_WHERE_OR_JOIN leftJoin(CollectionFunction<RESULT, JOIN_TO> collection) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public final <JOIN_TO, R extends Comparable<R>> MAPPED_NAMED_WHERE_OR_JOIN leftJoin(
			Function<RESULT, R> from, Function<JOIN_TO, R> to,
			Consumer<IShortJoin_Sub_Named<MODEL, RESULT, JOIN_TO, Void>> consumer) {
		
		throw new UnsupportedOperationException("TODO");
	}
	
	@Override
	public final <JOIN_TO> MAPPED_NAMED_WHERE_OR_JOIN leftJoin(
			CollectionFunction<RESULT, JOIN_TO> collection,
			Consumer<IShortJoin_Sub_Named<MODEL, RESULT, JOIN_TO, Void>> consumer) {
		throw new UnsupportedOperationException("TODO");
	}

}
