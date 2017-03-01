package com.neaterbits.query.sql.dsl.api;

public interface IClassic_From_Alias_Base<
			MODEL,
			RESULT,
			WHERE_OR_JOIN extends ISQLLogical_WhereOrJoin_Alias_Base<MODEL, RESULT>

		> extends ISharedSelectSourceBuilder<MODEL, RESULT> {

    /**
     * Pure aliases search
     * 
     * @param aliases
     */
    
    WHERE_OR_JOIN from(Object ... aliases);

	
}
