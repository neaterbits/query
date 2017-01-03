package com.neaterbits.query.sql.dsl.api;

public interface IClassicSelectSourceBuilderAlias<MODEL, RESULT> extends ISharedSelectSourceBuilder<MODEL, RESULT> {

    /**
     * Pure aliases search
     * 
     * @param aliases
     */
    
    IClassicWhereOrJoinBuilderAlias<MODEL, RESULT> from(Object ... aliases);

	
}
