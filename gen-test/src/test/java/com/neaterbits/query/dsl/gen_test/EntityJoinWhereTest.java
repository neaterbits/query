package com.neaterbits.query.dsl.gen_test;

import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.Test;

import com.neaterbits.query.sql.dsl.api.MultiBuilt;
import com.neaterbits.query.sql.dsl.api.SingleBuilt;
import com.neaterbits.query.test.model.Farm;
import com.neaterbits.query.test.model.land.CropLand;
import com.neaterbits.query.test.model.land.Forest;
import com.neaterbits.query.test.model.land.LandPlot;
import com.neaterbits.query.test.model.land.Uncultivated;


public class EntityJoinWhereTest extends GEN_Farm_BaseTestCase {


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
    public void testEntitySingleNamed_WhereLandPlot_Inner() {
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
    public void testEntitySingleNamed_WhereLandPlot_Left() {
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
    public void testEntitySingleNamed_WhereFarm_Inner() {
    	final Farm farm1 = makeFarm("Farm1", "1863-08-12");
    	final Farm farm2 = makeFarm("Farm2", "1951-12-14");
    	final Farm farm3 = makeFarm("Farm3", "1975-01-28");
    	
    	final LandPlot land1 = new CropLand(new BigDecimal("9.30"));
    	final LandPlot land2 = new Forest(new BigDecimal("40.5"));
    	final LandPlot land3 = new Uncultivated(new BigDecimal("100.5"));
    	final LandPlot land4 = new Uncultivated(new BigDecimal("345.43"));

    	farm1.setLandPlots(Arrays.asList(land1, land2));
    	land1.setFarm(farm1);
    	land2.setFarm(farm1);
    	farm2.setLandPlots(Arrays.asList(land3));
    	land3.setFarm(farm2);
    	
    	final SingleBuilt<Farm> query
    		= select.one(Farm.class)
    			.innerJoin(Farm::getLandPlots)
    			.where(Farm::getTimeFounded).isGreaterOrEqualTo(date("1900-01-01"))
    			.build();
    	
    	store(farm1, farm2, farm3, land4)
    	// remove to avoid delete constraints when deleting Farm (not cascade)
    	.remove(land1, land2, land3)
    	.checkOne(query, () -> new Farm(farm2.getId(), "Farm2"));
    }
    
    @Test
    public void testEntitySingleNamed_WhereFarm_Left() {
    	final Farm farm1 = makeFarm("Farm1", "1863-08-12");
    	final Farm farm2 = makeFarm("Farm2", "1951-12-14");
    	final Farm farm3 = makeFarm("Farm3", "1975-01-28");
    	
    	final LandPlot land1 = new CropLand(new BigDecimal("9.30"));
    	final LandPlot land2 = new Forest(new BigDecimal("40.5"));
    	final LandPlot land3 = new Uncultivated(new BigDecimal("100.5"));
    	final LandPlot land4 = new Uncultivated(new BigDecimal("345.43"));

    	farm1.setLandPlots(Arrays.asList(land1, land2));
    	land1.setFarm(farm1);
    	land2.setFarm(farm1);
    	farm2.setLandPlots(Arrays.asList(land3));
    	land3.setFarm(farm2);
    	
    	final SingleBuilt<Farm> query
    		= select.one(Farm.class)
    			.leftJoin(Farm::getLandPlots)
    			.where(Farm::getTimeFounded).isGreaterOrEqualTo(date("1952-01-01"))
    			.build();
    	
    	store(farm1, farm2, farm3, land4)
    	// remove to avoid delete constraints when deleting Farm (not cascade)
    	.remove(land1, land2, land3)
    	.checkOne(query, () -> new Farm(farm3.getId(), "Farm3"));
    }

    @Test
    public void testEntitySingleAlias() {
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

    	final Farm     f = select.alias(Farm.class);
    	final LandPlot l = select.alias(LandPlot.class);
    	
    	// only landplots that belong to farms, so land4 should not be included in sum
    	// since doing innerjoin from farm to landplot
    	final SingleBuilt<LandPlot> query
    		= select.one(l)
    			.innerJoin(f::getLandPlots, l)
    			.where(l::getHectares).isLessThan(new BigDecimal("40.5"))
    			.build();
    	
    	store(farm1, farm2, land4)
    	// remove to avoid delete constraints when deleting Farm (not cascade)
    	.remove(land1, land2, land3)
    	.checkOne(query, () -> new CropLand(land1.getId(), new BigDecimal("9.30")));
    }

    @Test
    public void testEntitySingleAlias_WhereLandPlot_Inner() {
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

    	final Farm     f = select.alias(Farm.class);
    	final LandPlot l = select.alias(LandPlot.class);
    	
    	// only landplots that belong to farms, so land4 should not be included in sum
    	// since doing innerjoin from farm to landplot
    	final SingleBuilt<Farm> query
    		= select.one(f)
    			.innerJoin(f::getLandPlots, l)
    			.where(l::getHectares).isLessThan(new BigDecimal("40.5"))
    			.build();
    	
    	store(farm1, farm2, land4)
    	// remove to avoid delete constraints when deleting Farm (not cascade)
    	.remove(land1, land2, land3)
    	.checkOne(query, () -> new Farm(farm1.getId(), "Farm1"));
    }
    
    @Test
    public void testEntitySingleAlias_WhereLandPlot_Left() {
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

    	final Farm     f = select.alias(Farm.class);
    	final LandPlot l = select.alias(LandPlot.class);
    	
    	// only landplots that belong to farms, so land4 should not be included in sum
    	// since doing innerjoin from farm to landplot
    	final SingleBuilt<Farm> query
    		= select.one(f)
    			.leftJoin(f::getLandPlots, l)
    			.where(l::getHectares).isGreaterOrEqualTo(new BigDecimal("100.5"))
    			.build();
    	
    	store(farm1, farm2, land4)
    	.remove(land1, land2, land3)
    	.checkOne(query, () -> new Farm(farm2.getId(), "Farm2"));
    }

    @Test
    public void testEntitySingleAlias_WhereFarm_Inner() {
    	final Farm farm1 = makeFarm("Farm1", "1863-08-12");
    	final Farm farm2 = makeFarm("Farm2", "1951-12-14");
    	final Farm farm3 = makeFarm("Farm3", "1975-01-28");
    	
    	final LandPlot land1 = new CropLand(new BigDecimal("9.30"));
    	final LandPlot land2 = new Forest(new BigDecimal("40.5"));
    	final LandPlot land3 = new Uncultivated(new BigDecimal("100.5"));
    	final LandPlot land4 = new Uncultivated(new BigDecimal("345.43"));

    	farm1.setLandPlots(Arrays.asList(land1, land2));
    	land1.setFarm(farm1);
    	land2.setFarm(farm1);
    	farm2.setLandPlots(Arrays.asList(land3));
    	land3.setFarm(farm2);
    	
    	final Farm     f = select.alias(Farm.class);
    	final LandPlot l = select.alias(LandPlot.class);

    	final SingleBuilt<Farm> query
    		= select.one(f)
    			.innerJoin(f::getLandPlots, l)
    			.where(f::getTimeFounded).isGreaterOrEqualTo(date("1900-01-01"))
    			.build();
    	
    	store(farm1, farm2, farm3, land4)
    	// remove to avoid delete constraints when deleting Farm (not cascade)
    	.remove(land1, land2, land3)
    	.checkOne(query, () -> new Farm(farm2.getId(), "Farm2"));
    }
    
    @Test
    public void testEntitySingleAlias_WhereFarm_Left() {
    	final Farm farm1 = makeFarm("Farm1", "1863-08-12");
    	final Farm farm2 = makeFarm("Farm2", "1951-12-14");
    	final Farm farm3 = makeFarm("Farm3", "1975-01-28");
    	
    	final LandPlot land1 = new CropLand(new BigDecimal("9.30"));
    	final LandPlot land2 = new Forest(new BigDecimal("40.5"));
    	final LandPlot land3 = new Uncultivated(new BigDecimal("100.5"));
    	final LandPlot land4 = new Uncultivated(new BigDecimal("345.43"));

    	farm1.setLandPlots(Arrays.asList(land1, land2));
    	land1.setFarm(farm1);
    	land2.setFarm(farm1);
    	farm2.setLandPlots(Arrays.asList(land3));
    	land3.setFarm(farm2);
    	
    	final Farm     f = select.alias(Farm.class);
    	final LandPlot l = select.alias(LandPlot.class);
    	
    	final SingleBuilt<Farm> query
    		= select.one(f)
    			.leftJoin(f::getLandPlots, l)
    			.where(f::getTimeFounded).isGreaterOrEqualTo(date("1952-01-01"))
    			.build();
    	
    	store(farm1, farm2, farm3, land4)
    	// remove to avoid delete constraints when deleting Farm (not cascade)
    	.remove(land1, land2, land3)
    	.checkOne(query, () -> new Farm(farm3.getId(), "Farm3"));
    }

    
    @Test
    public void testEntityMultiNamed() {
    	final Farm farm1 = new Farm("Farm1");
    	final Farm farm2 = new Farm("Farm2");
    	final Farm farm3 = new Farm("Farm3");
    	
    	final LandPlot land1 = new CropLand(new BigDecimal("9.30"));
    	final LandPlot land2 = new Forest(new BigDecimal("40.5"));
    	final LandPlot land3 = new Uncultivated(new BigDecimal("100.5"));
    	final LandPlot land4 = new Uncultivated(new BigDecimal("345.43"));

    	farm1.setLandPlots(Arrays.asList(land1, land2));
    	land1.setFarm(farm1);
    	land2.setFarm(farm1);
    	
    	farm2.setLandPlots(Arrays.asList(land3));
    	land3.setFarm(farm2);
    	
    	final MultiBuilt<LandPlot> query
    		= select.list(LandPlot.class)
    			.innerJoin(Farm::getLandPlots)
    			.where(LandPlot::getHectares).isGreaterOrEqualTo(new BigDecimal("9.3001"))
    			.build();
    	
    	store(farm1, farm2, farm3, land4)
    	// remove to avoid delete constraints when deleting Farm (not cascade)
    	.remove(land1, land2, land3)
    	.checkListUnordered(query, () -> 
    			Arrays.asList(
    				new Forest		(land2.getId(), new BigDecimal("40.5")),
    				new Uncultivated(land3.getId(), new BigDecimal("100.5"))));
    }

    @Test
    public void testEntityMultiNamed_WhereLandPlot_Inner() {
    	final Farm farm1 = new Farm("Farm1");
    	final Farm farm2 = new Farm("Farm2");
    	final Farm farm3 = new Farm("Farm3");
    	
    	final LandPlot land1 = new CropLand(new BigDecimal("9.30"));
    	final LandPlot land2 = new Forest(new BigDecimal("40.5"));
    	final LandPlot land3 = new Uncultivated(new BigDecimal("100.5"));
    	final LandPlot land4 = new Uncultivated(new BigDecimal("345.43"));

    	farm1.setLandPlots(Arrays.asList(land1, land2));
    	land1.setFarm(farm1);
    	land2.setFarm(farm1);
    	
    	farm2.setLandPlots(Arrays.asList(land3));
    	land3.setFarm(farm2);
    	
    	final MultiBuilt<Farm> query
    		= select.list(Farm.class)
    			.innerJoin(Farm::getLandPlots)
    			.where(LandPlot::getHectares).isGreaterThan(new BigDecimal("40.004"))
    			.build();
    	
    	store(farm1, farm2, farm3, land4)
    	// remove to avoid delete constraints when deleting Farm (not cascade)
    	.remove(land1, land2, land3)
    	.checkListUnordered(query, () -> Arrays.asList(
				//new Farm(farm1.getId(), "Farm1"),
				new Farm(farm1.getId(), "Farm1"),
				new Farm(farm2.getId(), "Farm2")));
    }

    @Test
    public void testEntityMultiNamed_WhereLandPlot_Left() {
    	final Farm farm1 = new Farm("Farm1");
    	final Farm farm2 = new Farm("Farm2");
    	final Farm farm3 = new Farm("Farm3");
    	
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
    	final MultiBuilt<Farm> query
    		= select.list(Farm.class)
    			.leftJoin(Farm::getLandPlots)
    			.where(LandPlot::getHectares).isGreaterThan(new BigDecimal("40.04"))
    			.build();
    	
    	store(farm1, farm2, farm3, land4)
    	.remove(land1, land2, land3)
    	.checkListUnordered(query, () -> Arrays.asList(
				//new Farm(farm1.getId(), "Farm1"),
				new Farm(farm1.getId(), "Farm1"),
				new Farm(farm2.getId(), "Farm2")
				//, new Farm(farm3.getId(), "Farm3")
				));
    }
    
    @Test
    public void testEntityMultiNamed_WhereFarm_Inner() {
    	final Farm farm1 = makeFarm("Farm1", "1863-08-12");
    	final Farm farm2 = makeFarm("Farm2", "1951-12-14");
    	final Farm farm3 = makeFarm("Farm3", "1975-01-28");
    	final Farm farm4 = makeFarm("Farm4", "1984-02-12");
    	
    	final LandPlot land1 = new CropLand(new BigDecimal("9.30"));
    	final LandPlot land2 = new Forest(new BigDecimal("40.5"));
    	final LandPlot land3 = new Uncultivated(new BigDecimal("100.5"));
    	final LandPlot land4 = new Uncultivated(new BigDecimal("345.43"));
    	final LandPlot land5 = new Uncultivated(new BigDecimal("434.13"));

    	farm1.setLandPlots(Arrays.asList(land1, land2));
    	land1.setFarm(farm1);
    	land2.setFarm(farm1);
    	farm2.setLandPlots(Arrays.asList(land3));
    	land3.setFarm(farm2);
    	
    	farm3.setLandPlots(Arrays.asList(land4));
    	land4.setFarm(farm3);
    	
    	final MultiBuilt<Farm> query
    		= select.list(Farm.class)
    			.innerJoin(Farm::getLandPlots)
    			.where(Farm::getTimeFounded).isGreaterOrEqualTo(date("1900-01-01"))
    			.build();
    	
    	store(farm1, farm2, farm3, farm4, land5)
    	// remove to avoid delete constraints when deleting Farm (not cascade)
    	.remove(land1, land2, land3, land4)
    	.checkListUnordered(query, () -> Arrays.asList(
    			new Farm(farm2.getId(), "Farm2"),
    			new Farm(farm3.getId(), "Farm3")
    			));
    }
    
    @Test
    public void testEntityMultiNamed_WhereFarm_Left() {
    	final Farm farm1 = makeFarm("Farm1", "1863-08-12");
    	final Farm farm2 = makeFarm("Farm2", "1951-12-14");
    	final Farm farm3 = makeFarm("Farm3", "1975-01-28");
    	final Farm farm4 = makeFarm("Farm4", "1984-02-12");
    	
    	final LandPlot land1 = new CropLand(new BigDecimal("9.30"));
    	final LandPlot land2 = new Forest(new BigDecimal("40.5"));
    	final LandPlot land3 = new Uncultivated(new BigDecimal("100.5"));
    	final LandPlot land4 = new Uncultivated(new BigDecimal("345.43"));
    	final LandPlot land5 = new Uncultivated(new BigDecimal("434.13"));

    	farm1.setLandPlots(Arrays.asList(land1, land2));
    	land1.setFarm(farm1);
    	land2.setFarm(farm1);
    	farm2.setLandPlots(Arrays.asList(land3));
    	land3.setFarm(farm2);
    	
    	farm3.setLandPlots(Arrays.asList(land4));
    	land4.setFarm(farm3);
    	
    	final MultiBuilt<Farm> query
    		= select.list(Farm.class)
    			.leftJoin(Farm::getLandPlots)
    			.where(Farm::getTimeFounded).isGreaterOrEqualTo(date("1900-01-01"))
    			.build();
    	
    	store(farm1, farm2, farm3, farm4, land5)
    	// remove to avoid delete constraints when deleting Farm (not cascade)
    	.remove(land1, land2, land3, land4)
    	.checkListUnordered(query, () -> Arrays.asList(
    			new Farm(farm2.getId(), "Farm2"),
    			new Farm(farm3.getId(), "Farm3"),
    			new Farm(farm4.getId(), "Farm4")
    			));
    }

    @Test
    public void testEntityMultiAlias() {
    	final Farm farm1 = new Farm("Farm1");
    	final Farm farm2 = new Farm("Farm2");
    	final Farm farm3 = new Farm("Farm3");
    	
    	final LandPlot land1 = new CropLand(new BigDecimal("9.30"));
    	final LandPlot land2 = new Forest(new BigDecimal("40.5"));
    	final LandPlot land3 = new Uncultivated(new BigDecimal("100.5"));
    	final LandPlot land4 = new Uncultivated(new BigDecimal("345.43"));

    	farm1.setLandPlots(Arrays.asList(land1, land2));
    	land1.setFarm(farm1);
    	land2.setFarm(farm1);
    	
    	farm2.setLandPlots(Arrays.asList(land3));
    	land3.setFarm(farm2);
    	
    	final Farm     f = select.alias(Farm.class);
    	final LandPlot l = select.alias(LandPlot.class);
    	
    	final MultiBuilt<LandPlot> query
    		= select.list(l)
    			.innerJoin(f::getLandPlots, l)
    			.where(l::getHectares).isGreaterOrEqualTo(new BigDecimal("9.3001"))
    			.build();
    	
    	store(farm1, farm2, farm3, land4)
    	// remove to avoid delete constraints when deleting Farm (not cascade)
    	.remove(land1, land2, land3)
    	.checkListUnordered(query, () -> 
    			Arrays.asList(
    				new Forest		(land2.getId(), new BigDecimal("40.5")),
    				new Uncultivated(land3.getId(), new BigDecimal("100.5"))));
    }

    @Test
    public void testEntityMultiAlias_WhereLandPlot_Inner() {
    	final Farm farm1 = new Farm("Farm1");
    	final Farm farm2 = new Farm("Farm2");
    	final Farm farm3 = new Farm("Farm3");
    	
    	final LandPlot land1 = new CropLand(new BigDecimal("9.30"));
    	final LandPlot land2 = new Forest(new BigDecimal("40.5"));
    	final LandPlot land3 = new Uncultivated(new BigDecimal("100.5"));
    	final LandPlot land4 = new Uncultivated(new BigDecimal("345.43"));

    	farm1.setLandPlots(Arrays.asList(land1, land2));
    	land1.setFarm(farm1);
    	land2.setFarm(farm1);
    	
    	farm2.setLandPlots(Arrays.asList(land3));
    	land3.setFarm(farm2);
    	
    	final Farm     f = select.alias(Farm.class);
    	final LandPlot l = select.alias(LandPlot.class);
    	
    	final MultiBuilt<Farm> query
    		= select.list(f)
    			.innerJoin(f::getLandPlots, l)
    			.where(l::getHectares).isGreaterThan(new BigDecimal("40.004"))
    			.build();
    	
    	store(farm1, farm2, farm3, land4)
    	// remove to avoid delete constraints when deleting Farm (not cascade)
    	.remove(land1, land2, land3)
    	.checkListUnordered(query, () -> Arrays.asList(
				//new Farm(farm1.getId(), "Farm1"),
				new Farm(farm1.getId(), "Farm1"),
				new Farm(farm2.getId(), "Farm2")));
    }

    @Test
    public void testEntityMultiAlias_WhereLandPlot_Left() {
    	final Farm farm1 = new Farm("Farm1");
    	final Farm farm2 = new Farm("Farm2");
    	final Farm farm3 = new Farm("Farm3");
    	
    	final LandPlot land1 = new CropLand(new BigDecimal("9.30"));
    	final LandPlot land2 = new Forest(new BigDecimal("40.5"));
    	final LandPlot land3 = new Uncultivated(new BigDecimal("100.5"));
    	final LandPlot land4 = new Uncultivated(new BigDecimal("345.43"));

    	farm1.setLandPlots(Arrays.asList(land1, land2));
    	land1.setFarm(farm1);
    	land2.setFarm(farm1);
    	
    	farm2.setLandPlots(Arrays.asList(land3));
    	land3.setFarm(farm2);
    	
    	final Farm     f = select.alias(Farm.class);
    	final LandPlot l = select.alias(LandPlot.class);
    	
    	// only landplots that belong to farms, so land4 should not be included in sum
    	// since doing innerjoin from farm to landplot
    	final MultiBuilt<Farm> query
    		= select.list(f)
    			.leftJoin(f::getLandPlots, l)
    			.where(l::getHectares).isGreaterThan(new BigDecimal("40.004"))
    			.build();
    	
    	store(farm1, farm2, farm3, land4)
    	.remove(land1, land2, land3)
    	.checkListUnordered(query, () -> Arrays.asList(
				//new Farm(farm1.getId(), "Farm1"),
				new Farm(farm1.getId(), "Farm1"),
				new Farm(farm2.getId(), "Farm2")
				//, new Farm(farm3.getId(), "Farm3")
				));
    }

    @Test
    public void testEntityMultiAlias_WhereFarm_Inner() {
    	final Farm farm1 = makeFarm("Farm1", "1863-08-12");
    	final Farm farm2 = makeFarm("Farm2", "1951-12-14");
    	final Farm farm3 = makeFarm("Farm3", "1975-01-28");
    	final Farm farm4 = makeFarm("Farm4", "1984-02-12");
    	
    	final LandPlot land1 = new CropLand(new BigDecimal("9.30"));
    	final LandPlot land2 = new Forest(new BigDecimal("40.5"));
    	final LandPlot land3 = new Uncultivated(new BigDecimal("100.5"));
    	final LandPlot land4 = new Uncultivated(new BigDecimal("345.43"));
    	final LandPlot land5 = new Uncultivated(new BigDecimal("434.13"));

    	farm1.setLandPlots(Arrays.asList(land1, land2));
    	land1.setFarm(farm1);
    	land2.setFarm(farm1);
    	farm2.setLandPlots(Arrays.asList(land3));
    	land3.setFarm(farm2);
    	
    	farm3.setLandPlots(Arrays.asList(land4));
    	land4.setFarm(farm3);

    	final Farm     f = select.alias(Farm.class);
    	final LandPlot l = select.alias(LandPlot.class);
    	
    	final MultiBuilt<Farm> query
    		= select.list(f)
    			.innerJoin(f::getLandPlots, l)
    			.where(f::getTimeFounded).isGreaterOrEqualTo(date("1900-01-01"))
    			.build();
    	
    	store(farm1, farm2, farm3, farm4, land5)
    	// remove to avoid delete constraints when deleting Farm (not cascade)
    	.remove(land1, land2, land3, land4)
    	.checkListUnordered(query, () -> Arrays.asList(
    			new Farm(farm2.getId(), "Farm2"),
    			new Farm(farm3.getId(), "Farm3")
    			));
    }
    
    @Test
    public void testEntityMultiAlias_WhereFarm_Left() {
    	final Farm farm1 = makeFarm("Farm1", "1863-08-12");
    	final Farm farm2 = makeFarm("Farm2", "1951-12-14");
    	final Farm farm3 = makeFarm("Farm3", "1975-01-28");
    	final Farm farm4 = makeFarm("Farm4", "1984-02-12");
    	
    	final LandPlot land1 = new CropLand(new BigDecimal("9.30"));
    	final LandPlot land2 = new Forest(new BigDecimal("40.5"));
    	final LandPlot land3 = new Uncultivated(new BigDecimal("100.5"));
    	final LandPlot land4 = new Uncultivated(new BigDecimal("345.43"));
    	final LandPlot land5 = new Uncultivated(new BigDecimal("434.13"));

    	farm1.setLandPlots(Arrays.asList(land1, land2));
    	land1.setFarm(farm1);
    	land2.setFarm(farm1);
    	farm2.setLandPlots(Arrays.asList(land3));
    	land3.setFarm(farm2);
    	
    	farm3.setLandPlots(Arrays.asList(land4));
    	land4.setFarm(farm3);
    	
    	final Farm     f = select.alias(Farm.class);
    	final LandPlot l = select.alias(LandPlot.class);
    	
    	final MultiBuilt<Farm> query
    		= select.list(f)
    			.leftJoin(f::getLandPlots, l)
    			.where(f::getTimeFounded).isGreaterOrEqualTo(date("1900-01-01"))
    			.build();
    	
    	store(farm1, farm2, farm3, farm4, land5)
    	// remove to avoid delete constraints when deleting Farm (not cascade)
    	.remove(land1, land2, land3, land4)
    	.checkListUnordered(query, () -> Arrays.asList(
    			new Farm(farm2.getId(), "Farm2"),
    			new Farm(farm3.getId(), "Farm3"),
    			new Farm(farm4.getId(), "Farm4")
    			));
    }
}
