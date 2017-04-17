package com.neaterbits.query.dsl.gen_test;

import org.junit.Test;

import com.neaterbits.query.jpatest.GEN_BaseTestCase;
import com.neaterbits.query.test.model.land.LandPlot;


public class AggregateWhereGroupByTest extends GEN_BaseTestCase {


    @Test
    public void testAggregateSingleNamed() {
    	// Should test that is not compilable
		verifyIsCompilable(
				"sum(LandPlot::getHectares)" + 
    			".where(LandPlot::getHectares).isEqualTo(new BigDecimal(\"1.2\"))");
		
		verifyIsNotCompilable(
				"sum(LandPlot::getHectares)" + 
				".where(LandPlot::getHectares).isEqualTo(new BigDecimal(\"1.2\"))" +
				".groupBy(LandPlot::getHectares)");		
    }


    @Test
    public void testAggregateSingleAlias() {
    	
		verifyIsCompilable(
				LandPlot.class, "l",
				"sum(l::getHectares)" + 
				".where(l::getHectares).isEqualTo(new BigDecimal(\"1.2\"))");
		
		verifyIsNotCompilable(
				LandPlot.class, "l",
				"sum(l::getHectares)" + 
				".where(l::getHectares).isEqualTo(new BigDecimal(\"1.2\"))" +
				".groupBy(l::getHectares)");		
    }
}
