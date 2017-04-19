package com.neaterbits.query.dsl.gen_test;

import java.math.BigDecimal;

import org.junit.Test;

import com.neaterbits.query.jpatest.GEN_BaseTestCase;
import com.neaterbits.query.sql.dsl.api.SingleBuilt;
import com.neaterbits.query.test.model.land.CropLand;
import com.neaterbits.query.test.model.land.Forest;
import com.neaterbits.query.test.model.land.LandPlot;
import com.neaterbits.query.test.model.land.Uncultivated;


public class AggregateWhereOrTest extends GEN_BaseTestCase {


    @Test
    public void testAggregateSingleNamed() {
    	final LandPlot land1 = new CropLand(new BigDecimal("9.30"));
    	final LandPlot land2 = new Forest(new BigDecimal("40.5"));
    	final LandPlot land3 = new Uncultivated(new BigDecimal("100.5"));
    	final LandPlot land4 = new Uncultivated(new BigDecimal("213.5"));

    	verifyIsNotCompilable(LandPlot.class, "l",
    			"sum(LandPlot::getHectares)" +
    			".where(LandPlot::getHectares).isLessThan(new BigDecimal(\"99.5\"))" +
    		    ".or(l::getHectares).isGreaterThan(new BigDecimal(\"30.5\"))");

    	verifyIsCompilable(LandPlot.class, "l",
    			"sum(LandPlot::getHectares)" +
    			".where(LandPlot::getHectares).isLessThan(new BigDecimal(\"99.5\"))" +
    		    ".or(LandPlot::getHectares).isGreaterThan(new BigDecimal(\"30.5\"))");
    	    	
    	final SingleBuilt<BigDecimal> sumQuery
    							= select.sum(LandPlot::getHectares)
    									.where(LandPlot::getHectares).isGreaterThan(new BigDecimal("101.5"))
    									.or(LandPlot::getHectares).isLessThan(new BigDecimal("40.0"))
    									.build(); 
    	
    	store(land1, land2, land3, land4)
    	.checkAggregate(sumQuery, new BigDecimal("222.8"));
    	
    	final SingleBuilt<BigDecimal> minQuery
    							= select.min(LandPlot::getHectares)
    									.where(LandPlot::getHectares).isGreaterThan(new BigDecimal("101.5"))
    									.or(LandPlot::getHectares).isLessThan(new BigDecimal("40.0"))
    									.build(); 

    	store(land1, land2, land3, land4)
    	.checkAggregate(minQuery, new BigDecimal("9.30"));

    	final SingleBuilt<BigDecimal> maxQuery
    				= select.max(LandPlot::getHectares)
							.where(LandPlot::getHectares).isGreaterThan(new BigDecimal("101.5"))
							.or(LandPlot::getHectares).isLessThan(new BigDecimal("40.0"))
    					    .build(); 

    	store(land1, land2, land3, land4)
    	.checkAggregate(maxQuery, new BigDecimal("213.5"));
    	
    	
    	// TODO: really return Double for avg of BigDecimal? JPA does though
    	final SingleBuilt<Double> avgQuery = select.avg(LandPlot::getHectares)
				.where(LandPlot::getHectares).isGreaterThan(new BigDecimal("101.5"))
				.or(LandPlot::getHectares).isLessThan(new BigDecimal("40.0"))
    			.build(); 

    	store(land1, land2, land3, land4)
    	.checkAggregate(avgQuery, 111.4);

    	final SingleBuilt<Long> countQuery
    				= select.count(LandPlot::getHectares)
							.where(LandPlot::getHectares).isGreaterThan(new BigDecimal("101.5"))
							.or(LandPlot::getHectares).isLessThan(new BigDecimal("40.0"))
    						.build(); 

    	store(land1, land2, land3, land4)
    	.checkAggregate(countQuery, 2L);
    }


    @Test
    public void testAggregateSingleAlias() {
    	final LandPlot land1 = new CropLand(new BigDecimal("9.30"));
    	final LandPlot land2 = new Forest(new BigDecimal("40.5"));
    	final LandPlot land3 = new Uncultivated(new BigDecimal("100.5"));
    	final LandPlot land4 = new Uncultivated(new BigDecimal("213.5"));

    	final LandPlot l = select.alias(LandPlot.class);
    	
    	verifyIsNotCompilable(LandPlot.class, "l",
    					"sum(l::getHectares)" + 
    					".where(l::getHectares).isLessThan(new BigDecimal(\"99.5\"))" +
    					".or(LandPlot::getHectares).isGreaterThan(new BigDecimal(\"30.5\"))");
    	
    	verifyIsCompilable(LandPlot.class, "l",
				"sum(l::getHectares)" + 
				".where(l::getHectares).isLessThan(new BigDecimal(\"99.5\"))" +
				".or(l::getHectares).isGreaterThan(new BigDecimal(\"30.5\"))");    	

    	final SingleBuilt<BigDecimal> sumQuery
    							= select.sum(l::getHectares)
    									.where(l::getHectares).isGreaterThan(new BigDecimal("101.5"))
    									.or(l::getHectares).isLessThan(new BigDecimal("40.0"))
    									.build(); 
    	
    	store(land1, land2, land3, land4)
    	.checkAggregate(sumQuery, new BigDecimal("222.8"));
    	
    	final SingleBuilt<BigDecimal> minQuery
    							= select.min(l::getHectares)
    									.where(l::getHectares).isGreaterThan(new BigDecimal("101.5"))
    									.or(l::getHectares).isLessThan(new BigDecimal("40.0"))
    									.build(); 

    	store(land1, land2, land3, land4)
    	.checkAggregate(minQuery, new BigDecimal("9.30"));

    	final SingleBuilt<BigDecimal> maxQuery
    				= select.max(l::getHectares)
							.where(l::getHectares).isGreaterThan(new BigDecimal("101.5"))
							.or(l::getHectares).isLessThan(new BigDecimal("40.0"))
    					    .build(); 

    	store(land1, land2, land3, land4)
    	.checkAggregate(maxQuery, new BigDecimal("213.5"));
    	
    	
    	// TODO: really return Double for avg of BigDecimal? JPA does though
    	final SingleBuilt<Double> avgQuery = select.avg(l::getHectares)
				.where(l::getHectares).isGreaterThan(new BigDecimal("101.5"))
				.or(l::getHectares).isLessThan(new BigDecimal("40.0"))
    			.build(); 

    	store(land1, land2, land3, land4)
    	.checkAggregate(avgQuery, 111.4);

    	final SingleBuilt<Long> countQuery
    				= select.count(l::getHectares)
							.where(l::getHectares).isGreaterThan(new BigDecimal("101.5"))
							.or(l::getHectares).isLessThan(new BigDecimal("40.0"))
    						.build(); 

    	store(land1, land2, land3, land4)
    	.checkAggregate(countQuery, 2L);
    	
    }
}
