package com.neaterbits.query.dsl.gen_test;

import org.junit.Test;
import com.neaterbits.query.jpatest.GEN_BaseTestCase;
import com.neaterbits.query.test.model.land.LandPlot;


public class AggregateWhereOrderByTest extends GEN_BaseTestCase {


    @Test
    public void testAggregateSingleNamed() {
    	// Should test that is not compilable
		verifyIsCompilable(
				"sum(LandPlot::getHectares)" + 
    			".where(LandPlot::getHectares).isEqualTo(new BigDecimal(\"1.2\"))");
		
		verifyIsNotCompilable(
				"sum(LandPlot::getHectares)" + 
				".where(LandPlot::getHectares).isEqualTo(new BigDecimal(\"1.2\"))" +
				".orderBy(LandPlot::getHectares)");		
    }


    @Test
    public void testAggregateSingleAlias() {
    	
		verifyIsCompilable(
				"l", LandPlot.class,
				"sum(l::getHectares)" + 
				".where(l::getHectares).isEqualTo(new BigDecimal(\"1.2\"))");
		
		verifyIsNotCompilable(
				"l", LandPlot.class,
				"sum(l::getHectares)" + 
				".where(l::getHectares).isEqualTo(new BigDecimal(\"1.2\"))" +
				".orderBy(l::getHectares)");		
    }
}
