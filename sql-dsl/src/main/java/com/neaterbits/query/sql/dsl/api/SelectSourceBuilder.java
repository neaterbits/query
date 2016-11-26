package com.neaterbits.query.sql.dsl.api;


public interface SelectSourceBuilder<MODEL, RESULT> {

	/**
	 * Pure table search
	 * @param tables
	 */
	
	WhereClauseBuilder<MODEL, RESULT> from(Class<?> ... tables);

    /**
     * Pure aliases search
     * @param aliases
     */
    
    WhereClauseBuilder<MODEL, RESULT> from(Alias<?> ... aliases);
    
    /* TODO: Mix of tables and aliases ? */
    
}
