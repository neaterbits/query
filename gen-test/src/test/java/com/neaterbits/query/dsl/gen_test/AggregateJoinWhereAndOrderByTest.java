package com.neaterbits.query.dsl.gen_test;


import org.junit.Test;

import com.neaterbits.query.jpatest.GEN_BaseTestCase;
import com.neaterbits.query.test.model.Farm;
import com.neaterbits.query.test.model.land.LandPlot;


public class AggregateJoinWhereAndOrderByTest extends GEN_BaseTestCase {


    @Test
    public void testAggregateSingleNamed() {

    	verifyIsCompilable(
    			b -> b
    				.addImport(LandPlot.class)
    				.addImport(Farm.class),
				"sum(LandPlot::getHectares)" +
				".innerJoin(Farm::getLandPlots)" +
				".where(LandPlot::getHectares).isGreaterThan(new BigDecimal(\"99.9\"))" +
				".and(LandPlot::getHectares).isLessThan(new BigDecimal(\"199.9\"))"
				);
		
		verifyIsNotCompilable(
    			b -> b
					.addImport(LandPlot.class)
					.addImport(Farm.class),
				"sum(LandPlot::getHectares)" +
				".innerJoin(Farm::getLandPlots)" +
				".where(LandPlot::getHectares).isGreaterThan(new BigDecimal(\"99.9\"))" +
				".and(LandPlot::getHectares).isLessThan(new BigDecimal(\"199.9\"))" +
				".orderBy(LandPlot::getHectares)");
    }


    @Test
    public void testAggregateSingleAlias() {
    	verifyIsCompilable(
    			b -> b.add(Farm.class, "f")
    				  .add(LandPlot.class, "l"),
    				  
				"sum(l::getHectares)" +
				".innerJoin(f::getLandPlots, l)" +
				".where(l::getHectares).isGreaterThan(new BigDecimal(\"99.9\"))" +
				".and(l::getHectares).isLessThan(new BigDecimal(\"199.9\"))");
		
		verifyIsNotCompilable(
    			b -> b.add(Farm.class, "f")
				  .add(LandPlot.class, "l"),
				  
				"sum(l::getHectares)" +
				".innerJoin(f::getLandPlots, l)" +
				".where(l::getHectares).isGreaterThan(new BigDecimal(\"99.9\"))" +
				".and(l::getHectares).isLessThan(new BigDecimal(\"199.9\"))" +
				".orderBy(l::getHectares)");
    }
}
