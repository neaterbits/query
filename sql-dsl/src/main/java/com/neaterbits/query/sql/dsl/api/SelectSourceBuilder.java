package com.neaterbits.query.sql.dsl.api;


public interface SelectSourceBuilder<RESULT> {

	/**
	 * Pure table search
	 * @param tables
	 */
	
	WhereClauseBuilder<Void, ?> from(Class<?> ... tables);

    /**
     * Pure aliases search
     * @param aliases
     */
    
    WhereClauseBuilder<Void, ?> from(Alias<?> ... aliases);
    
    /* TODO: Mix of tables and aliases ? */
    
}
