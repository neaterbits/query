package com.neaterbits.query.dsl.gen_test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.neaterbits.query.jpatest.GEN_BaseTestCase;
import com.neaterbits.query.test.model.Farm;
import com.neaterbits.query.test.model.land.LandPlot;


public class AggregateJoinWhereOrOrderByTest extends GEN_BaseTestCase {


    @Test
    public void testAggregateSingleNamed() {

    	verifyIsCompilable(
				"sum(LandPlot::getHectares)" +
				".innerJoin(Farm::getLandPlots)" +
				".where(LandPlot::getHectares).isLessThan(new BigDecimal(\"99.9\"))" +
				".or(LandPlot::getHectares).isGreaterThan(new BigDecimal(\"199.9\"))"
				);
		
		verifyIsNotCompilable(
				"sum(LandPlot::getHectares)" +
				".innerJoin(Farm::getLandPlots)" +
				".where(LandPlot::getHectares).isLessThan(new BigDecimal(\"99.9\"))" +
				".or(LandPlot::getHectares).isGreaterThan(new BigDecimal(\"199.9\"))" +
				".orderBy(LandPlot::getHectares)");
    }


    @Test
    public void testAggregateSingleAlias() {
    	verifyIsCompilable(
    			b -> b.add(Farm.class, "f")
    				  .add(LandPlot.class, "l"),
    				  
				"sum(l::getHectares)" +
				".innerJoin(f::getLandPlots, l)" +
				".where(l::getHectares).isLessThan(new BigDecimal(\"99.9\"))" +
				".or(l::getHectares).isGreaterThan(new BigDecimal(\"199.9\"))");
		
		verifyIsNotCompilable(
    			b -> b.add(Farm.class, "f")
				  .add(LandPlot.class, "l"),
				  
				"sum(l::getHectares)" +
				".innerJoin(f::getLandPlots, l)" +
				".where(l::getHectares).isLessThan(new BigDecimal(\"99.9\"))" +
				".or(l::getHectares).isGreaterThan(new BigDecimal(\"199.9\"))" +
				".orderBy(l::getHectares)");
    }
}
