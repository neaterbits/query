package com.neaterbits.query.sql.dsl.api;

public interface IClassic_From_Named<MODEL, RESULT> extends ISharedSelectSourceBuilder<MODEL, RESULT> {

	/**
	 * Pure table search
	 * @param tables
	 */
	
	IClassicLogical_WhereOrJoin_Named<MODEL, RESULT> from(Class<?> ... tables);
	
	
}
