package com.neaterbits.query.dsl.gen_test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.neaterbits.query.jpatest.GEN_BaseTestCase;
import com.neaterbits.query.test.model.Farm;
import com.neaterbits.query.test.model.land.LandPlot;


public class AggregateJoinWhereGroupByTest extends GEN_BaseTestCase {


    @Test
    public void testAggregateSingleNamed() {

    	verifyIsCompilable(
				"sum(LandPlot::getHectares)" +
				".innerJoin(Farm::getLandPlots)");
		
		verifyIsNotCompilable(
				"sum(LandPlot::getHectares)" +
				".innerJoin(Farm::getLandPlots)" +
				".groupBy(LandPlot::getHectares)");
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
				".groupBy(l::getHectares)");
    }
}
