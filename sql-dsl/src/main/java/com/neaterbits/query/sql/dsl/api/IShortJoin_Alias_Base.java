package com.neaterbits.query.sql.dsl.api;

import java.util.function.Consumer;
import java.util.function.Supplier;

/*
 * Contrary to named variant, we're not able to do from-type checking here
 * at Java compile time since we're using suppliers
 * 
 */

public interface IShortJoin_Alias_Base<MODEL, RESULT, RET_TYPE> {

	<R extends Comparable<R>> RET_TYPE innerJoin(Supplier<R> from, Supplier<R> to);
	
	
	<JOIN_TO> RET_TYPE innerJoin(CollectionSupplier<JOIN_TO> collection, JOIN_TO alias);

	/* Nested joins */
	<R extends Comparable<R>> RET_TYPE innerJoin(
				Supplier<R> from,
				Supplier<R> to,
				Consumer<IShortJoin_Sub_Alias<MODEL, RESULT, Void>> consumer);

	<JOIN_TO> RET_TYPE innerJoin(
					CollectionSupplier<JOIN_TO> collection,
					JOIN_TO alias,
					Consumer<IShortJoin_Sub_Alias<MODEL, RESULT, Void>> consumer);

	<R extends Comparable<R>> RET_TYPE leftJoin(Supplier<R> from, Supplier<R> to);

	<JOIN_TO> RET_TYPE leftJoin(CollectionSupplier<JOIN_TO> collection, JOIN_TO alias);

	<R extends Comparable<R>> RET_TYPE leftJoin(
					Supplier<R> from,
					Supplier<R> to,
					Consumer<IShortJoin_Sub_Alias<MODEL, RESULT, Void>> consumer);

	<JOIN_TO> RET_TYPE leftJoin(
					CollectionSupplier<JOIN_TO> collection,
					JOIN_TO alias,
					Consumer<IShortJoin_Sub_Alias<MODEL, RESULT, Void>> consumer);
	
}
