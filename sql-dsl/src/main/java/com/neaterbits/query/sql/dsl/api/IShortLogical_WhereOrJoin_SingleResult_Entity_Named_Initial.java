package com.neaterbits.query.sql.dsl.api;

import java.util.function.Consumer;
import java.util.function.Function;

public interface IShortLogical_WhereOrJoin_SingleResult_Entity_Named_Initial<MODEL, RESULT>
	extends ISQLLogical_WhereOrJoin_SingleResult_Named_And_Function<MODEL, RESULT>

/*,
	
	 IShortJoin_Entitity_Named_Initial<MODEL, RESULT, RESULT, IShortLogical_WhereOrJoin_SingleResult_Entity_Named<MODEL, RESULT, RESULT>> 
	*/
	{

	//<T> IShortLogical_WhereOrJoin_SingleResult_Entity_Named<MODEL, RESULT, T> joinRoot(Class<T> type);
	
	<JOIN_FROM, JOIN_TO, R extends Comparable<R>>
		IShortLogical_WhereOrJoin_SingleResult_Entity_Named<MODEL, RESULT, JOIN_FROM> innerJoin(Function<JOIN_FROM, R> from, Function<JOIN_TO, R> to);
		
	<JOIN_FROM, JOIN_TO>
		IShortLogical_WhereOrJoin_SingleResult_Entity_Named<MODEL, RESULT, JOIN_FROM> innerJoin(CollectionFunction<JOIN_FROM, JOIN_TO> collection);

	/* Nested joins */
	<JOIN_FROM, JOIN_TO, R extends Comparable<R>>
	
		IShortLogical_WhereOrJoin_SingleResult_Entity_Named<MODEL, RESULT, JOIN_FROM> innerJoin(
				Function<JOIN_FROM, R> from,
				Function<JOIN_TO, R> to,
				Consumer<IShortJoin_Sub_Named<MODEL, RESULT, JOIN_TO, Void>> consumer);

	<JOIN_FROM, JOIN_TO>
		IShortLogical_WhereOrJoin_SingleResult_Entity_Named<MODEL, RESULT, JOIN_FROM> innerJoin(
					CollectionFunction<JOIN_FROM, JOIN_TO> collection,
					Consumer<IShortJoin_Sub_Named<MODEL, RESULT, JOIN_TO, Void>> consumer);

	<JOIN_FROM, JOIN_TO, R extends Comparable<R>> 
		IShortLogical_WhereOrJoin_SingleResult_Entity_Named<MODEL, RESULT, JOIN_FROM>  leftJoin(Function<JOIN_FROM, R> from, Function<JOIN_TO, R> to);
		
		
	<JOIN_FROM, JOIN_TO> IShortLogical_WhereOrJoin_SingleResult_Entity_Named<MODEL, RESULT, JOIN_FROM> leftJoin(CollectionFunction<JOIN_FROM, JOIN_TO> collection);

	<JOIN_FROM, JOIN_TO, R extends Comparable<R>> 
		IShortLogical_WhereOrJoin_SingleResult_Entity_Named<MODEL, RESULT, JOIN_FROM> leftJoin(
					Function<JOIN_FROM, R> from,
					Function<JOIN_TO, R> to,
					Consumer<IShortJoin_Sub_Named<MODEL, RESULT, JOIN_TO, Void>> consumer);

	<JOIN_FROM, JOIN_TO>
		IShortLogical_WhereOrJoin_SingleResult_Entity_Named<MODEL, RESULT, JOIN_FROM> leftJoin(
					CollectionFunction<JOIN_FROM, JOIN_TO> collection,
					Consumer<IShortJoin_Sub_Named<MODEL, RESULT, JOIN_TO, Void>> consumer);
	
	
}
