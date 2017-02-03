package com.neaterbits.query.sql.dsl.api;

public interface IClassic_From_Alias<MODEL, RESULT> extends ISharedSelectSourceBuilder<MODEL, RESULT> {

    /**
     * Pure aliases search
     * 
     * @param aliases
     */
    
    IClassicLogical_WhereOrJoin_Alias<MODEL, RESULT> from(Object ... aliases);

	
}
