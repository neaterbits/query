package com.neaterbits.query.sql.dsl.api;

import java.util.function.Consumer;
import java.util.function.Supplier;

final class Short_Collector_Multi_Entity_Alias<MODEL, RESULT>
	extends Short_Collector_Multi_Entity_Any<MODEL, RESULT, ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>>

	implements IShortResult_Entity_Multi_Alias<MODEL, RESULT>

	{

	Short_Collector_Multi_Entity_Alias(BaseQuery select, CollectedQueryResult_Entity_Multi result, Collector_Query<MODEL> queryCollector) {
		super(select, result, queryCollector);
	}

	@Override
	Collector_GroupBy<MODEL, RESULT> createGroupByCollector(Collector_Base<MODEL> last, int[] groupByColumns,
		Collector_Conditions_GroupBy<MODEL, RESULT, ?> collectorConditions) {
	
		return new Collector_GroupBy_Alias<>(last, groupByColumns, collectorConditions);
	}

	@Override
	public <R extends Comparable<R>> IShortResult_Entity_Multi_Alias<MODEL, RESULT> innerJoin(Supplier<R> from, Supplier<R> to) {
		return addInnerJoin(from, to);
	}

	@Override
	public <JOIN_TO> IShortResult_Entity_Multi_Alias<MODEL, RESULT> innerJoin(CollectionSupplier<JOIN_TO> collection, JOIN_TO alias) {
		return addInnerJoin(collection, alias);
	}

	@Override
	public <R extends Comparable<R>> IShortResult_Entity_Multi_Alias<MODEL, RESULT> innerJoin(Supplier<R> from, Supplier<R> to, Consumer<IShortJoin_Sub_Alias<MODEL, RESULT, Void>> consumer) {
		return addInnerJoin(from, to, consumer);
	}

	@Override
	public <JOIN_TO> IShortResult_Entity_Multi_Alias<MODEL, RESULT> innerJoin(CollectionSupplier<JOIN_TO> collection, JOIN_TO alias, Consumer<IShortJoin_Sub_Alias<MODEL, RESULT, Void>> consumer) {
		return addInnerJoin(collection, alias, consumer);
	}

	@Override
	public <R extends Comparable<R>> IShortResult_Entity_Multi_Alias<MODEL, RESULT> leftJoin(Supplier<R> from, Supplier<R> to) {
		return addLeftJoin(from, to);
	}

	@Override
	public <JOIN_TO> IShortResult_Entity_Multi_Alias<MODEL, RESULT> leftJoin(CollectionSupplier<JOIN_TO> collection, JOIN_TO alias) {
		return addLeftJoin(collection, alias);
	}

	@Override
	public <R extends Comparable<R>> IShortResult_Entity_Multi_Alias<MODEL, RESULT> leftJoin(Supplier<R> from, Supplier<R> to, Consumer<IShortJoin_Sub_Alias<MODEL, RESULT, Void>> consumer) {
		return addLeftJoin(from, to, consumer);
	}

	@Override
	public <JOIN_TO> IShortResult_Entity_Multi_Alias<MODEL, RESULT> leftJoin(CollectionSupplier<JOIN_TO> collection, JOIN_TO alias, Consumer<IShortJoin_Sub_Alias<MODEL, RESULT, Void>> consumer) {
		return addLeftJoin(collection, alias, consumer);
	}
}
