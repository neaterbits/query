package com.neaterbits.query.dsl.gen_test;

import org.junit.Test;
import com.neaterbits.query.jpatest.GEN_BaseTestCase;
import com.neaterbits.query.test.model.land.LandPlot;


public class AggregateWhereOrOrderByTest extends GEN_BaseTestCase {

    @Test
    public void testAggregateSingleNamed() {
    	
    	// Should test that is not compilable
		verifyIsCompilable(
				"sum(LandPlot::getHectares)" + 
    			".where(LandPlot::getHectares).isLessThan(new BigDecimal(\"1.2\"))" +
    			".   or(LandPlot::getHectares).isGreaterThan(new BigDecimal(\"1.3\"))");
		
		verifyIsNotCompilable(
				"sum(LandPlot::getHectares)" + 
				".where(LandPlot::getHectares).isLessThan(new BigDecimal(\"1.2\"))" +
				".   or(LandPlot::getHectares).isGreaterThan(new BigDecimal(\"1.3\"))" +
				".orderBy(LandPlot::getHectares)");		
    }


    @Test
    public void testAggregateSingleAlias() {
    	
		verifyIsCompilable(
				LandPlot.class, "l",
				"sum(l::getHectares)" + 
				".where(l::getHectares).isLessThan(new BigDecimal(\"1.2\"))" +
				".   or(l::getHectares).isGreaterThan(new BigDecimal(\"1.3\"))");
		
		verifyIsNotCompilable(
				LandPlot.class, "l",
				"sum(l::getHectares)" + 
				".where(l::getHectares).isLessThan(new BigDecimal(\"1.2\"))" +
				".   or(l::getHectares).isGreaterThan(new BigDecimal(\"1.3\"))" +
				".orderBy(l::getHectares)");		
    }
}
