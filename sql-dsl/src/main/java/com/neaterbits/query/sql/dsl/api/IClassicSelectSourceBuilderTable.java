package com.neaterbits.query.sql.dsl.api;

public interface IClassicSelectSourceBuilderTable<MODEL, RESULT> extends SelectSourceBuilder<MODEL, RESULT> {

	/**
	 * Pure table search
	 * @param tables
	 */
	
	WhereOrJoinBuilderTable<MODEL, RESULT> from(Class<?> ... tables);
	
	
}
