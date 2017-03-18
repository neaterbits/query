package com.neaterbits.query.sql.dsl.api;


// WHether applicable for one single test

interface GEN_Test_Applicability {

	boolean isApplicable(
			EQueryResultGathering gathering,
			EQueryResultDimension dimension,
			EFieldAccessType fieldAccessType); 
}
