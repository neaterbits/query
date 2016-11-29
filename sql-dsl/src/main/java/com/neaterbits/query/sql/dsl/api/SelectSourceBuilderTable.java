package com.neaterbits.query.sql.dsl.api;

public interface SelectSourceBuilderTable<MODEL, RESULT> extends SelectSourceBuilder<MODEL, RESULT> {

	/**
	 * Pure table search
	 * @param tables
	 */
	
	WhereClauseBuilderTable<MODEL, RESULT> from(Class<?> ... tables);
	
	
}
