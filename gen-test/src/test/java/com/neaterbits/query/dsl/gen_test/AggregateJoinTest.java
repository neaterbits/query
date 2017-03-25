package com.neaterbits.query.dsl.gen_test;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.Test;
import com.neaterbits.query.jpatest.GEN_BaseTestCase;
import com.neaterbits.query.sql.dsl.api.SingleBuilt;
import com.neaterbits.query.test.model.Farm;
import com.neaterbits.query.test.model.land.CropLand;
import com.neaterbits.query.test.model.land.Forest;
import com.neaterbits.query.test.model.land.LandPlot;
import com.neaterbits.query.test.model.land.Uncultivated;


public class AggregateJoinTest extends GEN_BaseTestCase {


    @Test
    public void testAggregateSingleNamed() {
    	
    	final Farm farm1 = new Farm("Farm1");
    	final Farm farm2 = new Farm("Farm2");
    	
    	final LandPlot land1 = new CropLand(new BigDecimal("9.30"));
    	final LandPlot land2 = new Forest(new BigDecimal("40.5"));
    	final LandPlot land3 = new Uncultivated(new BigDecimal("100.5"));
    	final LandPlot land4 = new Uncultivated(new BigDecimal("345.43"));

    	farm1.setLandPlots(Arrays.asList(land1, land2));
    	
    	land1.setFarm(farm1);
    	land2.setFarm(farm1);
    	
    	farm2.setLandPlots(Arrays.asList(land3));
    	land3.setFarm(farm2);
    	
    	// only landplots that belong to farms, so land4 should not be included in sum
    	// since doing innerjoin from farm to landplot
    	final SingleBuilt<BigDecimal> query
    		= select.sum(LandPlot::getHectares)
    			.innerJoin(Farm::getLandPlots)   
    			.build();
    	
    	store(farm1, farm2, land4)
    	/*.dump(Farm.class)
    	.dump(LandPlot.class)
    	*/
    	.dump("select * from farm")
    	.dump("select * from land_plot")
    	
    	// remove to avoid delete constraints when deleting Farm (not cascade)
    	.remove(land1, land2, land3)
    	.checkAggregate(query, new BigDecimal("150.30"));
    	
    	
    	//fortsett, innerjoin fra farm til landplot med aggregate på farm? for eksempel min og max TimeFounded eller lignende
    	assertThat(true).isEqualTo(false);
    }


    @Test
    public void testAggregateSingleAlias() {
    	final Farm farm1 = new Farm("Farm1");
    	final Farm farm2 = new Farm("Farm2");
    	
    	final LandPlot land1 = new CropLand(new BigDecimal("9.30"));
    	final LandPlot land2 = new Forest(new BigDecimal("40.5"));
    	final LandPlot land3 = new Uncultivated(new BigDecimal("100.5"));
    	final LandPlot land4 = new Uncultivated(new BigDecimal("345.43"));

    	farm1.setLandPlots(Arrays.asList(land1, land2));
    	
    	land1.setFarm(farm1);
    	land2.setFarm(farm1);
    	
    	farm2.setLandPlots(Arrays.asList(land3));
    	land3.setFarm(farm2);
    	
    	final LandPlot l = select.alias(LandPlot.class);
    	final Farm     f = select.alias(Farm.class);
    	
    	// only landplots that belong to farms, so land4 should not be included in sum
    	// since doing innerjoin from farm to landplot
    	final SingleBuilt<BigDecimal> query
    		= select.sum(l::getHectares)
    			.innerJoin(f::getLandPlots, l)   
    			.build();
    	
    	store(farm1, farm2, land4)
    	
    	// remove to avoid delete constraints when deleting Farm (not cascade)
    	.remove(land1, land2, land3)
    	.checkAggregate(query, new BigDecimal("150.30"));
    	
        assertThat(true).isEqualTo(false);
    }
}
