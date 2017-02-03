package com.neaterbits.query.sql.dsl.api;

public interface IClassic_From_Named_Base<MODEL, RESULT, WHERE_OR_JOIN extends IClassicLogical_WhereOrJoin_Named_Base<MODEL, RESULT>>
		extends ISharedSelectSourceBuilder<MODEL, RESULT> {

	/**
	 * Pure table search
	 * @param tables
	 */
	
	WHERE_OR_JOIN from(Class<?> ... tables);
	
	
	
	
}
