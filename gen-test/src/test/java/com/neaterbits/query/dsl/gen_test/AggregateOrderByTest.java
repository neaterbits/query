package com.neaterbits.query.dsl.gen_test;

import org.junit.Test;

import com.neaterbits.query.jpatest.GEN_BaseTestCase;
import com.neaterbits.query.test.model.land.LandPlot;

// No OrderByTests for Aggregates but verify that does not compile

public class AggregateOrderByTest extends GEN_BaseTestCase {

	@Test
    public void testAggregateSingleNamed() {
    	// Should test that is not compilable
		verifyIsCompilable(
				"sum(LandPlot::getHectares)");
		
		verifyIsNotCompilable(
				"sum(LandPlot::getHectares)" + 
				".orderBy(LandPlot::getHectares)");		
    }


    @Test
    public void testAggregateSingleAlias() {
		verifyIsCompilable(
				LandPlot.class, "l",
				
				"sum(l::getHectares)");
		
		verifyIsNotCompilable(
				LandPlot.class, "l",
				"sum(l::getHectares)" + 
				".orderBy(l::getHectares)");		
    }
}
