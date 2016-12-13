package com.neaterbits.query.sql.dsl.api;

public interface SelectSourceBuilderAlias<MODEL, RESULT> extends SelectSourceBuilder<MODEL, RESULT> {

    /**
     * Pure aliases search
     * 
     * @param aliases
     */
    
    WhereOrJoinBuilderAlias<MODEL, RESULT> from(Object ... aliases);

	
}
