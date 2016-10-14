package com.neaterbits.gui.sql.dsl.api.standalone;

import com.neaterbits.gui.sql.dsl.api.SelectCondition;

public interface SelectSourceBuilder<RESULT> {

	/**
	 * Pure table search
	 * @param tables
	 */
	
	SelectCondition<Void, ?> from(Class<?> ... tables);

    /**
     * Pure aliases search
     * @param aliases
     */
    
    void from(Alias<?> ... aliases);
    
    /* TODO: Mix of tables and aliases ? */
    
}
