package com.neaterbits.query.sql.dsl.api;

public interface IClassicSelectSourceBuilderNamed<MODEL, RESULT> extends SelectSourceBuilder<MODEL, RESULT> {

	/**
	 * Pure table search
	 * @param tables
	 */
	
	IClassicWhereOrJoinBuilderNamed<MODEL, RESULT> from(Class<?> ... tables);
	
	
}
