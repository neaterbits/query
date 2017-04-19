package com.neaterbits.query.dsl.gen_test;

import org.junit.Test;

import com.neaterbits.query.jpatest.GEN_BaseTestCase;
import com.neaterbits.query.test.model.Farm;
import com.neaterbits.query.test.model.land.LandPlot;


public class AggregateJoinWhereOrderByTest extends GEN_BaseTestCase {


    @Test
    public void testAggregateSingleNamed() {

    	verifyIsCompilable(
				"sum(LandPlot::getHectares)" +
				".innerJoin(Farm::getLandPlots)" +
				".where(LandPlot::getHectares).isGreaterThan(new BigDecimal(\"99.9\"))");
		
		verifyIsNotCompilable(
				"sum(LandPlot::getHectares)" +
				".innerJoin(Farm::getLandPlots)" +
				".where(LandPlot::getHectares).isGreaterThan(new BigDecimal(\"99.9\"))" +
				".orderBy(LandPlot::getHectares)");
    }


    @Test
    public void testAggregateSingleAlias() {
    	verifyIsCompilable(
    			b -> b.add(Farm.class, "f")
    				  .add(LandPlot.class, "l"),
    				  
				"sum(l::getHectares)" +
				".innerJoin(f::getLandPlots, l)" +
				".where(l::getHectares).isGreaterThan(new BigDecimal(\"99.9\"))");
		
		verifyIsNotCompilable(
    			b -> b.add(Farm.class, "f")
				  .add(LandPlot.class, "l"),
				  
				"sum(l::getHectares)" +
				".innerJoin(f::getLandPlots, l)" +
				".where(l::getHectares).isGreaterThan(new BigDecimal(\"99.9\"))" +
				".orderBy(l::getHectares)");
    }
}
