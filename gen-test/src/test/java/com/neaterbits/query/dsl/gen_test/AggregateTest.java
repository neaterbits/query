package com.neaterbits.query.dsl.gen_test;

import java.math.BigDecimal;

import org.junit.Test;
import com.neaterbits.query.jpatest.GEN_BaseTestCase;
import com.neaterbits.query.sql.dsl.api.SingleBuilt;
import com.neaterbits.query.test.model.land.CropLand;
import com.neaterbits.query.test.model.land.Forest;
import com.neaterbits.query.test.model.land.LandPlot;
import com.neaterbits.query.test.model.land.Uncultivated;


public class AggregateTest extends GEN_BaseTestCase {


    @Test
    public void testAggregateSingleNamed() {
    	
    	final LandPlot land1 = new CropLand(new BigDecimal("9.30"));
    	final LandPlot land2 = new Forest(new BigDecimal("40.5"));
    	final LandPlot land3 = new Uncultivated(new BigDecimal("100.5"));

    	// sum for all landplots
    	final SingleBuilt<BigDecimal> sumQuery = select.sum(LandPlot::getHectares).build(); 

    	
    	store(s -> s.add(land1).add(land2))
    	.checkOne(sumQuery, () -> new BigDecimal("49.80"));
    	
    	final SingleBuilt<BigDecimal> minQuery = select.min(LandPlot::getHectares).build(); 

    	store(s -> s.add(land1).add(land2).add(land3))
    	.checkOne(minQuery, () -> new BigDecimal("9.30"));

    	final SingleBuilt<BigDecimal> maxQuery = select.max(LandPlot::getHectares).build(); 

    	store(s -> s.add(land1).add(land2).add(land3))
    	.checkOne(maxQuery, () -> new BigDecimal("100.5"));
    	
    	
    	// TODO: really return Double for avg of BigDecimal? JPA does though
    	final SingleBuilt<Double> avgQuery = select.avg(LandPlot::getHectares).build(); 

    	store(s -> s.add(land1).add(land2).add(land3))
    	.checkOne(avgQuery, () -> 50.1);

    	final SingleBuilt<Long> countQuery = select.count(LandPlot::getHectares).build(); 

    	final Long count = 3L;
    	
    	store(s -> s.add(land1).add(land2).add(land3))
    	.checkOne(countQuery, () -> count);
    }


    @Test
    public void testAggregateSingleAlias() {
    	final LandPlot land1 = new CropLand(new BigDecimal("9.30"));
    	final LandPlot land2 = new Forest(new BigDecimal("40.5"));
    	final LandPlot land3 = new Uncultivated(new BigDecimal("100.5"));

    	
    	final LandPlot lp = select.alias(LandPlot.class);
    	
    	// sum for all landplots
    	final SingleBuilt<BigDecimal> sumQuery = select.sum(lp::getHectares).build(); 

    	store(s -> s.add(land1).add(land2))
    	.checkOne(sumQuery, () -> new BigDecimal("49.80"));
    	
    	final SingleBuilt<BigDecimal> minQuery = select.min(lp::getHectares).build(); 

    	store(s -> s.add(land1).add(land2).add(land3))
    	.checkOne(minQuery, () -> new BigDecimal("9.30"));

    	final SingleBuilt<BigDecimal> maxQuery = select.max(lp::getHectares).build(); 

    	store(s -> s.add(land1).add(land2).add(land3))
    	.checkOne(maxQuery, new BigDecimal("100.5"));
    	
    	
    	// TODO: really return Double for avg of BigDecimal? JPA does though
    	final SingleBuilt<Double> avgQuery = select.avg(lp::getHectares).build(); 

    	store(s -> s.add(land1).add(land2).add(land3))
    	.checkOne(avgQuery, () -> 50.1);

    	final SingleBuilt<Long> countQuery = select.count(lp::getHectares).build(); 

    	final Long count = 3L;
    	
    	store(s -> s.add(land1).add(land2).add(land3))
    	.checkOne(countQuery, () -> count);
    }
}
