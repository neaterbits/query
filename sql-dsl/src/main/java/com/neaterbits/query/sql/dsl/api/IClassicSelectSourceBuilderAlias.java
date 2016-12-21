package com.neaterbits.query.sql.dsl.api;

public interface IClassicSelectSourceBuilderAlias<MODEL, RESULT> extends SelectSourceBuilder<MODEL, RESULT> {

    /**
     * Pure aliases search
     * 
     * @param aliases
     */
    
    IClassicWhereOrJoinBuilderAlias<MODEL, RESULT> from(Object ... aliases);

	
}
