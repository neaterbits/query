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


public class EntityJoinTest extends GEN_BaseTestCase {


    @Test
    public void testEntitySingleNamed() {
    	final Farm farm1 = new Farm("Farm1");
    	final Farm farm2 = new Farm("Farm2");
    	
    	final LandPlot land1 = new CropLand(new BigDecimal("9.30"));
    	final LandPlot land2 = new Forest(new BigDecimal("40.5"));
    	final LandPlot land3 = new Uncultivated(new BigDecimal("100.5"));
    	final LandPlot land4 = new Uncultivated(new BigDecimal("345.43"));

    	// Only one landplot attached to farms
    	farm1.setLandPlots(Arrays.asList(land1, land2));
    	land2.setFarm(farm1);
    	
    	// only landplots that belong to farms, so land4 should not be included in sum
    	// since doing innerjoin from farm to landplot
    	final SingleBuilt<LandPlot> query
    		= select.one(LandPlot.class)
    			.innerJoin(Farm::getLandPlots)   
    			.build();
    	
    	store(farm1, farm2, land1, land3, land4)
    	/*.dump(Farm.class)
    	.dump(LandPlot.class)
    	*/
    	.dump("select * from farm")
    	.dump("select * from land_plot")
    	
    	// remove to avoid delete constraints when deleting Farm (not cascade)
    	.remove(land2)
    	.checkOne(query, () -> new Forest(land2.getId(), new BigDecimal("40.5")));
    }

    @Test
    public void testEntitySingleNamed_Farm_Inner() {
    	final Farm farm1 = new Farm("Farm1");
    	final Farm farm2 = new Farm("Farm2");
    	
    	final LandPlot land1 = new CropLand(new BigDecimal("9.30"));
    	final LandPlot land2 = new Forest(new BigDecimal("40.5"));
    	final LandPlot land3 = new Uncultivated(new BigDecimal("100.5"));
    	final LandPlot land4 = new Uncultivated(new BigDecimal("345.43"));

    	// Only one landplot attached to farms
    	farm1.setLandPlots(Arrays.asList(land1));
    	land1.setFarm(farm1);
    	
    	// only landplots that belong to farms, so land4 should not be included in sum
    	// since doing innerjoin from farm to landplot
    	final SingleBuilt<Farm> query
    		= select.one(Farm.class)
    			.innerJoin(Farm::getLandPlots)
    			.build();
    	
    	store(farm1, farm2, land2, land3, land4)
    	// remove to avoid delete constraints when deleting Farm (not cascade)
    	.remove(land1)
    	.checkOne(query, () -> new Farm(farm1.getId(), "Farm1"));
    }
    
    @Test
    public void testEntitySingleNamed_Farm_Left() {
    	final Farm farm1 = new Farm("Farm1");
    	
    	final LandPlot land1 = new CropLand(new BigDecimal("9.30"));
    	final LandPlot land2 = new Forest(new BigDecimal("40.5"));

    	// only landplots that belong to farms, so land4 should not be included in sum
    	// since doing innerjoin from farm to landplot
    	final SingleBuilt<Farm> query
    		= select.one(Farm.class)
    			.leftJoin(Farm::getLandPlots)   
    			.build();
    	
    	store(farm1, land1, land2)
    	.checkOne(query, () -> new Farm(farm1.getId(), "Farm1"));
    }
    

    @Test
    public void testEntitySingleAlias() {
    	
    	verifyIsNotCompilable(
    			a -> a.add(Farm.class, "f").add(LandPlot.class, "l"),
    			
    			"one(LandPlot.class)" +
    			".innerJoin(f::getLandPlots, l)");

    	verifyIsCompilable(
    			a -> a.add(Farm.class, "f").add(LandPlot.class, "l"),
    			
    			"one(f)" +
    			".innerJoin(f::getLandPlots, l)");
    	
    	final Farm farm1 = new Farm("Farm1");
    	final Farm farm2 = new Farm("Farm2");
    	
    	final LandPlot land1 = new CropLand(new BigDecimal("9.30"));
    	final LandPlot land2 = new Forest(new BigDecimal("40.5"));
    	final LandPlot land3 = new Uncultivated(new BigDecimal("100.5"));
    	final LandPlot land4 = new Uncultivated(new BigDecimal("345.43"));

    	// Only one landplot attached to farms
    	farm1.setLandPlots(Arrays.asList(land1, land2));
    	land2.setFarm(farm1);

    	final Farm     f = select.alias(Farm.class);
    	final LandPlot l = select.alias(LandPlot.class);
    	
    	// only landplots that belong to farms, so land4 should not be included in sum
    	// since doing innerjoin from farm to landplot
    	final SingleBuilt<LandPlot> query
    		= select.one(l)
    			.innerJoin(f::getLandPlots, l)   
    			.build();
    	
    	store(farm1, farm2, land1, land3, land4)
    	/*.dump(Farm.class)
    	.dump(LandPlot.class)
    	*/
    	.dump("select * from farm")
    	.dump("select * from land_plot")
    	
    	// remove to avoid delete constraints when deleting Farm (not cascade)
    	.remove(land2)
    	.checkOne(query, () -> new Forest(land2.getId(), new BigDecimal("40.5")));
    }


    @Test
    public void testEntitySingleAlias_Farm_Inner() {
    	final Farm farm1 = new Farm("Farm1");
    	final Farm farm2 = new Farm("Farm2");
    	
    	final LandPlot land1 = new CropLand(new BigDecimal("9.30"));
    	final LandPlot land2 = new Forest(new BigDecimal("40.5"));
    	final LandPlot land3 = new Uncultivated(new BigDecimal("100.5"));
    	final LandPlot land4 = new Uncultivated(new BigDecimal("345.43"));

    	// Only one landplot attached to farms
    	farm1.setLandPlots(Arrays.asList(land1));
    	land1.setFarm(farm1);
    	
    	final Farm     f = select.alias(Farm.class);
    	final LandPlot l = select.alias(LandPlot.class);
    	
    	// only landplots that belong to farms, so land4 should not be included in sum
    	// since doing innerjoin from farm to landplot
    	final SingleBuilt<Farm> query
    		= select.one(f)
    			.innerJoin(f::getLandPlots, l)
    			.build();
    	
    	store(farm1, farm2, land2, land3, land4)
    	// remove to avoid delete constraints when deleting Farm (not cascade)
    	.remove(land1)
    	.checkOne(query, () -> new Farm(farm1.getId(), "Farm1"));
    }
    
    @Test
    public void testEntitySingleAlias_Farm_Left() {
    	final Farm farm1 = new Farm("Farm1");
    	
    	final LandPlot land1 = new CropLand(new BigDecimal("9.30"));
    	final LandPlot land2 = new Forest(new BigDecimal("40.5"));

    	final Farm     f = select.alias(Farm.class);
    	final LandPlot l = select.alias(LandPlot.class);
    	
    	// only landplots that belong to farms, so land4 should not be included in sum
    	// since doing innerjoin from farm to landplot
    	final SingleBuilt<Farm> query
    		= select.one(f)
    			.leftJoin(f::getLandPlots, l)   
    			.build();
    	
    	store(farm1, land1, land2)
    	.checkOne(query, () -> new Farm(farm1.getId(), "Farm1"));
    }

    @Test
    public void testEntityMultiNamed() {
        assertThat(true).isEqualTo(false);
    }


    @Test
    public void testEntityMultiAlias() {
    	verifyIsNotCompilable(
    			a -> a.add(Farm.class, "f").add(LandPlot.class, "l"),
    			
    			"select.list(LandPlot.class)" +
    			".innerJoin(f::getLandPlots, l)" +
    			".build())");

    	verifyIsCompilable(
    			a -> a.add(Farm.class, "f").add(LandPlot.class, "l"),
    			
    			"select.list(f)" +
    			".innerJoin(f::getLandPlots, l)" +
    			".build())");
        assertThat(true).isEqualTo(false);
    }
}
