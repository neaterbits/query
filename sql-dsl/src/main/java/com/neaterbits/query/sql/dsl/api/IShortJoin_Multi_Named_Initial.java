package com.neaterbits.query.sql.dsl.api;

import java.util.function.Consumer;
import java.util.function.Function;

public interface IShortJoin_Multi_Named_Initial<MODEL, RESULT> {
	
	<JOIN_FROM, JOIN_TO, R extends Comparable<R>>
	IShortLogical_WhereOrJoin_MultiEntity_Named<MODEL, RESULT, JOIN_FROM> innerJoin(Function<JOIN_FROM, R> from, Function<JOIN_TO, R> to);

	<JOIN_FROM, JOIN_TO>
		IShortLogical_WhereOrJoin_MultiEntity_Named<MODEL, RESULT, JOIN_FROM> innerJoin(CollectionFunction<JOIN_FROM, JOIN_TO> collection);
	
	/* Nested joins */
	<JOIN_FROM, JOIN_TO, R extends Comparable<R>>
	
		IShortLogical_WhereOrJoin_MultiEntity_Named<MODEL, RESULT, JOIN_FROM> innerJoin(
				Function<JOIN_FROM, R> from,
				Function<JOIN_TO, R> to,
				Consumer<IShortJoin_Sub_Named<MODEL, RESULT, JOIN_TO, Void>> consumer);
	
	<JOIN_FROM, JOIN_TO>
		IShortLogical_WhereOrJoin_MultiEntity_Named<MODEL, RESULT, JOIN_FROM> innerJoin(
					CollectionFunction<JOIN_FROM, JOIN_TO> collection,
					Consumer<IShortJoin_Sub_Named<MODEL, RESULT, JOIN_TO, Void>> consumer);
	
	<JOIN_FROM, JOIN_TO, R extends Comparable<R>> 
		IShortLogical_WhereOrJoin_MultiEntity_Named<MODEL, RESULT, JOIN_FROM>  leftJoin(Function<JOIN_FROM, R> from, Function<JOIN_TO, R> to);
		
		
	<JOIN_FROM, JOIN_TO> IShortLogical_WhereOrJoin_MultiEntity_Named<MODEL, RESULT, JOIN_FROM> leftJoin(CollectionFunction<JOIN_FROM, JOIN_TO> collection);
	
	<JOIN_FROM, JOIN_TO, R extends Comparable<R>> 
		IShortLogical_WhereOrJoin_MultiEntity_Named<MODEL, RESULT, JOIN_FROM> leftJoin(
					Function<JOIN_FROM, R> from,
					Function<JOIN_TO, R> to,
					Consumer<IShortJoin_Sub_Named<MODEL, RESULT, JOIN_TO, Void>> consumer);
	
	<JOIN_FROM, JOIN_TO>
		IShortLogical_WhereOrJoin_MultiEntity_Named<MODEL, RESULT, JOIN_FROM> leftJoin(
					CollectionFunction<JOIN_FROM, JOIN_TO> collection,
					Consumer<IShortJoin_Sub_Named<MODEL, RESULT, JOIN_TO, Void>> consumer);

}
