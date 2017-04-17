package com.neaterbits.query.dsl.gen_test;

import org.junit.Test;

import com.neaterbits.query.jpatest.GEN_BaseTestCase;
import com.neaterbits.query.test.model.Farm;
import com.neaterbits.query.test.model.land.LandPlot;


public class AggregateJoinOrderByTest extends GEN_BaseTestCase {


    @Test
    public void testAggregateSingleNamed() {

    	verifyIsCompilable(
				"sum(LandPlot::getHectares)" +
				".innerJoin(Farm::getLandPlots)");
		
		verifyIsNotCompilable(
				"sum(LandPlot::getHectares)" +
				".innerJoin(Farm::getLandPlots)" +
				".orderBy(LandPlot::getHectares)");
    }


    @Test
    public void testAggregateSingleAlias() {
    	verifyIsCompilable(
    			b -> b.add(Farm.class, "f")
    				  .add(LandPlot.class, "l"),
    				  
				"sum(l::getHectares)" +
				".innerJoin(f::getLandPlots, l)");
		
		verifyIsNotCompilable(
    			b -> b.add(Farm.class, "f")
				  .add(LandPlot.class, "l"),
				  
				"sum(l::getHectares)" +
				".innerJoin(f::getLandPlots, l)" +
				".orderBy(l::getHectares)");
    }
}
