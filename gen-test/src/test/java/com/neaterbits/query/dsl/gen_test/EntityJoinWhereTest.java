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


public class EntityJoinWhereTest extends GEN_BaseTestCase {


    @Test
    public void testEntitySingleNamed() {
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
    	final SingleBuilt<LandPlot> query
    		= select.one(LandPlot.class)
    			.innerJoin(Farm::getLandPlots)
    			.where(LandPlot::getHectares).isLessThan(new BigDecimal("40.5"))
    			.build();
    	
    	store(farm1, farm2, land4)
    	// remove to avoid delete constraints when deleting Farm (not cascade)
    	.remove(land1, land2, land3)
    	.checkOne(query, () -> new CropLand(land1.getId(), new BigDecimal("9.30")));
    }

    @Test
    public void testEntitySingleNamed_Farm_Inner() {
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
    	final SingleBuilt<Farm> query
    		= select.one(Farm.class)
    			.innerJoin(Farm::getLandPlots)
    			.where(LandPlot::getHectares).isLessThan(new BigDecimal("40.5"))
    			.build();
    	
    	store(farm1, farm2, land4)
    	// remove to avoid delete constraints when deleting Farm (not cascade)
    	.remove(land1, land2, land3)
    	.checkOne(query, () -> new Farm(farm1.getId(), "Farm1"));
    }
    
    @Test
    public void testEntitySingleNamed_Farm_Left() {
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
    	final SingleBuilt<Farm> query
    		= select.one(Farm.class)
    			.leftJoin(Farm::getLandPlots)
    			.where(LandPlot::getHectares).isGreaterOrEqualTo(new BigDecimal("100.5"))
    			.build();
    	
    	store(farm1, farm2, land4)
    	.remove(land1, land2, land3)
    	.checkOne(query, () -> new Farm(farm2.getId(), "Farm2"));
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
}
