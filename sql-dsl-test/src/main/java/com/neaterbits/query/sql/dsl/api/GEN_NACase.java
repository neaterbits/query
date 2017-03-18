package com.neaterbits.query.sql.dsl.api;


/**
 * Functional test case that is not applicable
 * @author nhl
 *
 */

public class GEN_NACase {

	private final GEN_Functionality functionality;
	
	private final EQueryResultGathering [] gathering;
	private final EQueryResultDimension [] dimensions;
	private final EFieldAccessType [] fieldAccess;
	public 
	
	GEN_NACase(
			GEN_Functionality functionality,
			EQueryResultGathering[] gathering,
			EQueryResultDimension[] dimensions,
			EFieldAccessType[] fieldAccess) {

		if (functionality == null) {
			throw new IllegalArgumentException("functionality == null");
		}
		
		this.functionality = functionality;
		this.gathering = gathering;
		this.dimensions = dimensions;
		this.fieldAccess = fieldAccess;
	}
	
}
