package com.neaterbits.query.sql.dsl.api;

public interface GEN_TestCase {

	boolean isTestApplicable(EQueryResultGathering gathering, EQueryResultDimension dimension, EFieldAccessType accessType);

	String [] getFunctionalitySourceCodeNames();
}
