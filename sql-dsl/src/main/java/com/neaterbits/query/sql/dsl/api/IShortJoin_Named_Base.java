package com.neaterbits.query.sql.dsl.api;

import java.util.function.Consumer;
import java.util.function.Function;

public interface IShortJoin_Named_Base<MODEL, RESULT, JOIN_FROM, RET_TYPE> {
	// Join is formed similar to a sub-select, since we can only type-safely on one type at a time
	// while not utilizing reflection

	<JOIN_TO, R extends Comparable<R>> RET_TYPE innerJoin(Function<JOIN_FROM, R> from, Function<JOIN_TO, R> to); 
	<JOIN_TO> RET_TYPE innerJoin(CollectionFunction<JOIN_FROM, JOIN_TO> collection);

	/* Nested joins */
	<JOIN_TO, R extends Comparable<R>> RET_TYPE innerJoin(
				Function<JOIN_FROM, R> from,
				Function<JOIN_TO, R> to,
				Consumer<IShortJoin_Sub_Named<MODEL, RESULT, JOIN_TO, Void>> consumer);

	<JOIN_TO> RET_TYPE innerJoin(
					CollectionFunction<JOIN_FROM, JOIN_TO> collection,
					Consumer<IShortJoin_Sub_Named<MODEL, RESULT, JOIN_TO, Void>> consumer);

	<JOIN_TO, R extends Comparable<R>> RET_TYPE leftJoin(Function<JOIN_FROM, R> from, Function<JOIN_TO, R> to);
	<JOIN_TO> RET_TYPE leftJoin(CollectionFunction<JOIN_FROM, JOIN_TO> collection);

	<JOIN_TO, R extends Comparable<R>> RET_TYPE leftJoin(
					Function<JOIN_FROM, R> from,
					Function<JOIN_TO, R> to,
					Consumer<IShortJoin_Sub_Named<MODEL, RESULT, JOIN_TO, Void>> consumer);

	<JOIN_TO> RET_TYPE leftJoin(
					CollectionFunction<JOIN_FROM, JOIN_TO> collection,
					Consumer<IShortJoin_Sub_Named<MODEL, RESULT, JOIN_TO, Void>> consumer);
}
