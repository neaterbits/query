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
import com.neaterbits.query.test.model.mapped.FarmLand;


public class MappedJoinTest extends GEN_BaseTestCase {


    @Test
    public void testMappedSingleNamed() {
    	final Farm farm1 = new Farm("Farm1");
    	final Farm farm2 = new Farm("Farm2");
    	
    	final LandPlot land1 = new CropLand(new BigDecimal("9.30"));
    	final LandPlot land2 = new Forest(new BigDecimal("40.5"));
    	final LandPlot land3 = new Uncultivated(new BigDecimal("100.5"));
    	final LandPlot land4 = new Uncultivated(new BigDecimal("345.43"));

    	// Only one landplot attached to farms
    	farm1.setLandPlots(Arrays.asList(land2));
    	land2.setFarm(farm1);
    	
    	// only landplots that belong to farms, so land4 should not be included in sum
    	// since doing innerjoin from farm to landplot
    	final SingleBuilt<FarmLand> query
    		= select.one(FarmLand.class)
    			.map(Farm::getName)			.to(FarmLand::setFarmName)
    			.map(LandPlot::getHectares) .to(FarmLand::setHectares)
    			.innerJoin(Farm::getLandPlots)
    			.build();
    	
    	store(farm1, farm2, land1, land3, land4)
    	// remove to avoid delete constraints when deleting Farm (not cascade)
    	.remove(land2)
    	.checkOne(query, () -> new FarmLand("Farm1", new BigDecimal("40.5")));
    }


    @Test
    public void testMappedSingleAlias() {
        assertThat(true).isEqualTo(false);
        /*
    	final Farm farm1 = new Farm("Farm1");
    	final Farm farm2 = new Farm("Farm2");
    	
    	final LandPlot land1 = new CropLand(new BigDecimal("9.30"));
    	final LandPlot land2 = new Forest(new BigDecimal("40.5"));
    	final LandPlot land3 = new Uncultivated(new BigDecimal("100.5"));
    	final LandPlot land4 = new Uncultivated(new BigDecimal("345.43"));

    	// Only one landplot attached to farms
    	farm1.setLandPlots(Arrays.asList(land2));
    	land2.setFarm(farm1);
    	
    	final Farm f = select.alias(Farm.class);
    	final LandPlot l = select.alias(LandPlot.class);
    	
    	
    	// only landplots that belong to farms, so land4 should not be included in sum
    	// since doing innerjoin from farm to landplot
    	final SingleBuilt<FarmLand> query
    		= select.one(f)
    			.map(f::getName)			.to(FarmLand::setFarmName)
    			.map(l::getHectares) .to(FarmLand::setHectares)
    			.innerJoin(Farm::getLandPlots)
    			.build();
    	
    	store(farm1, farm2, land1, land3, land4)
    	// remove to avoid delete constraints when deleting Farm (not cascade)
    	.remove(land2)
    	.checkOne(query, () -> new FarmLand("Farm1", new BigDecimal("40.5")));
    	*/
    }


    @Test
    public void testMappedMultiNamed() {
        assertThat(true).isEqualTo(false);
    }


    @Test
    public void testMappedMultiAlias() {
        assertThat(true).isEqualTo(false);
    }
}
