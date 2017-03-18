package com.neaterbits.query.dsl.gen_test;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.Test;
import com.neaterbits.query.jpatest.GEN_BaseTestCase;
import com.neaterbits.query.sql.dsl.api.SingleBuilt;
import com.neaterbits.query.test.model.land.CropLand;
import com.neaterbits.query.test.model.land.Forest;
import com.neaterbits.query.test.model.land.LandPlot;


public class AggregateTest extends GEN_BaseTestCase {


    @Test
    public void testEntitySingleNamed() {
    	
    	final LandPlot land1 = new CropLand(new BigDecimal("9.30"));
    	final LandPlot land2 = new Forest(new BigDecimal("40.5"));
    	
    	// sum for all landplots
    	final SingleBuilt<BigDecimal> sumQuery = select.sum(LandPlot::getHectares).build(); 

    	store(s -> s.add(land1).add(land2))
    	.check(ds ->
    		checkSelectOneOrNull(
    				ds,
    				new BigDecimal(49.80),
    				sumQuery, 
    				q -> q.execute()));
    	
    }


    @Test
    public void testEntitySingleAlias() {
        assertThat(true).isEqualTo(false);
    }


    @Test
    public void testEntityMultiNamed() {
        assertThat(true).isEqualTo(false);
    }


    @Test
    public void testEntityMultiAlias() {
        assertThat(true).isEqualTo(false);
    }


    @Test
    public void testMappedSingleNamed() {
        assertThat(true).isEqualTo(false);
    }


    @Test
    public void testMappedSingleAlias() {
        assertThat(true).isEqualTo(false);
    }


    @Test
    public void testMappedMultiNamed() {
        assertThat(true).isEqualTo(false);
    }


    @Test
    public void testMappedMultiAlias() {
        assertThat(true).isEqualTo(false);
    }


    @Test
    public void testAggregateSingleNamed() {
        assertThat(true).isEqualTo(false);
    }


    @Test
    public void testAggregateSingleAlias() {
        assertThat(true).isEqualTo(false);
    }


    @Test
    public void testAggregateMultiNamed() {
        assertThat(true).isEqualTo(false);
    }


    @Test
    public void testAggregateMultiAlias() {
        assertThat(true).isEqualTo(false);
    }
}
