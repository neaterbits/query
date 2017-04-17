package com.neaterbits.query.dsl.gen_test;

import java.math.BigDecimal;

import org.junit.Test;

import com.neaterbits.query.jpatest.GEN_BaseTestCase;
import com.neaterbits.query.sql.dsl.api.SingleBuilt;
import com.neaterbits.query.test.model.land.CropLand;
import com.neaterbits.query.test.model.land.Forest;
import com.neaterbits.query.test.model.land.LandPlot;
import com.neaterbits.query.test.model.land.Uncultivated;


public class AggregateWhereAndFunctionTest extends GEN_BaseTestCase {

    @Test
    public void testAggregateSingleNamed() {

    	final LandPlot land1 = new CropLand(new BigDecimal("9.30"));
    	final LandPlot land2 = new Forest(new BigDecimal("40.5"));
    	final LandPlot land3 = new Uncultivated(new BigDecimal("121.0"));
    	final LandPlot land4 = new Uncultivated(new BigDecimal("144.0"));
    	final LandPlot land5 = new Uncultivated(new BigDecimal("169.0"));
    	    	
    	// sum for all landplots
    	SingleBuilt<BigDecimal> query = select.sum(LandPlot::getHectares)
    			
			.where(LandPlot::getHectares).isGreaterThan(new BigDecimal("130.0"))
			.and().sqrt(LandPlot::getHectares).isGreaterThan(10.0)
			.build(); 

    	store(land1, land2, land3, land4, land5)
    	.checkAggregate(query, new BigDecimal("313.0"));
    	
    	// Test that can have multiple or in a row
    	query = select.sum(LandPlot::getHectares)
    			
    			.where(LandPlot::getHectares).isGreaterThan(new BigDecimal("130.0"))
    			.and().sqrt(LandPlot::getHectares).isGreaterThan(11.0)
    			.and().sqrt(LandPlot::getHectares).isLessThan(13.0)
    			.build(); 

        	store(land1, land2, land3, land4)
        	.checkAggregate(query, new BigDecimal("144.0"));
    }


    @Test
    public void testAggregateSingleAlias() {

    	final LandPlot land1 = new CropLand(new BigDecimal("9.30"));
    	final LandPlot land2 = new Forest(new BigDecimal("40.5"));
    	final LandPlot land3 = new Uncultivated(new BigDecimal("121.0"));
    	final LandPlot land4 = new Uncultivated(new BigDecimal("144.0"));
    	final LandPlot land5 = new Uncultivated(new BigDecimal("169.0"));
    	
    	final LandPlot l = select.alias(LandPlot.class);
    	    	
    	// sum for all landplots
    	SingleBuilt<BigDecimal> query = select.sum(l::getHectares)
    			
			.where(l::getHectares).isGreaterThan(new BigDecimal("130.0"))
			.and().sqrt(l::getHectares).isGreaterThan(10.0)
			.build(); 

    	store(land1, land2, land3, land4, land5)
    	.checkAggregate(query, new BigDecimal("313.0"));
    	
    	// Test that can have multiple or in a row
    	query = select.sum(l::getHectares)
    			
    			.where(l::getHectares).isGreaterThan(new BigDecimal("130.0"))
    			.and().sqrt(l::getHectares).isGreaterThan(11.0)
    			.and().sqrt(l::getHectares).isLessThan(13.0)
    			.build(); 

        	store(land1, land2, land3, land4)
        	.checkAggregate(query, new BigDecimal("144.0"));
    }
}
