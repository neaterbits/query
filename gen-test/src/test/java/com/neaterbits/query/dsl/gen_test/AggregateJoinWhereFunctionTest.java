package com.neaterbits.query.dsl.gen_test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

import org.junit.Test;

import com.neaterbits.query.sql.dsl.api.SingleBuilt;
import com.neaterbits.query.test.model.Farm;
import com.neaterbits.query.test.model.land.CropLand;
import com.neaterbits.query.test.model.land.Forest;
import com.neaterbits.query.test.model.land.LandPlot;
import com.neaterbits.query.test.model.land.Uncultivated;


public class AggregateJoinWhereFunctionTest extends GEN_Farm_BaseTestCase {


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
    	
    	final SingleBuilt<BigDecimal> query
    		= select.sum(LandPlot::getHectares)
    			.innerJoin(Farm::getLandPlots)
    			.where().upper(Farm::getName).isEqualTo("FARM1")
    			.build();
    	
    	store(farm1, farm2, land4)
    	.remove(land1, land2, land3)
    	.checkAggregate(query, new BigDecimal("49.80"));
    }
    
    @Test
    public void testAggregateSingleNamed_WhereLandPlot_Inner() {
    	
    	final Farm farm1 = makeFarm("Farm1", "1928-09-02");
    	final Farm farm2 = makeFarm("Farm2", "1974-02-29");
    	final Farm farm3 = makeFarm("Farm3", "2004-03-22");

    	final LandPlot land1 = new CropLand(new BigDecimal("9.30"));
    	final LandPlot land2 = new Forest(new BigDecimal("40.5"));
    	
    	farm1.setLandPlots(Arrays.asList(land1));
    	land1.setFarm(farm1);
    	
    	farm2.setLandPlots(Arrays.asList(land2));
    	land2.setFarm(farm2);
    	

    	final SingleBuilt<Date> query
	    	= select.max(Farm::getTimeFounded)
				.innerJoin(Farm::getLandPlots)
				.where().sqrt(LandPlot::getHectares).isLessOrEqualTo(6.3)
				.build();
	
		store(farm1, farm2, farm3)
		// remove to avoid delete constraints when deleting Farm (not cascade)
		.remove(land1, land2)
		.checkAggregate(query, date("1928-09-02"));
    }

    @Test
    public void testAggregateSingleNamed_WhereLandPlot_Left() {
    	
    	// Aggregate left-join
    	final Farm farm1 = makeFarm("Farm1", "1928-09-02");
    	final Farm farm2 = makeFarm("Farm2", "1974-02-29");
    	final Farm farm3 = makeFarm("Farm3", "2004-03-22");

    	final LandPlot land1 = new CropLand(new BigDecimal("9.30"));
    	final LandPlot land2 = new Forest(new BigDecimal("40.5"));
    	
    	farm1.setLandPlots(Arrays.asList(land1));
    	land1.setFarm(farm1);
    	
    	farm2.setLandPlots(Arrays.asList(land2));
    	land2.setFarm(farm2);
    	

    	final SingleBuilt<Date> query
	    	= select.max(Farm::getTimeFounded)
				.leftJoin(Farm::getLandPlots)
				.where().sqrt(LandPlot::getHectares).isGreaterThan(1.2)
				.build();
	
		store(farm1, farm2, farm3)
		/*.dump(Farm.class)
		.dump(LandPlot.class)
		*/
		
		// remove to avoid delete constraints when deleting Farm (not cascade)
		.remove(land1, land2)
		.checkAggregate(query, date("1974-02-29"));
    }
    
    @Test
    public void testAggregateSingleNamed_WhereFarm_Inner() {
    	
    	// Aggregate left-join
    	final Farm farm1 = makeFarm("Farm1", "1928-09-02");
    	final Farm farm2 = makeFarm("Farm2", "1974-02-29");
    	final Farm farm3 = makeFarm("Farm2", "2004-03-22");

    	final LandPlot land1 = new CropLand(new BigDecimal("9.30"));
    	final LandPlot land2 = new Forest(new BigDecimal("40.5"));
    	
    	farm1.setLandPlots(Arrays.asList(land1));
    	land1.setFarm(farm1);
    	
    	farm2.setLandPlots(Arrays.asList(land2));
    	land2.setFarm(farm2);
    	

    	final SingleBuilt<Date> query
	    	= select.max(Farm::getTimeFounded)
				.innerJoin(Farm::getLandPlots)
				.where().upper(Farm::getName).endsWith("M2")
				.build();
	
		store(farm1, farm2, farm3)
		.remove(land1, land2)
		.checkAggregate(query, date("1974-02-29"));
    }

    @Test
    public void testAggregateSingleNamed_WhereFarm_Left() {
    	
    	// Aggregate left-join
    	final Farm farm1 = makeFarm("Farm1", "1928-09-02");
    	final Farm farm2 = makeFarm("Farm2", "1974-02-29");
    	final Farm farm3 = makeFarm("Farm2", "2004-03-22");

    	final LandPlot land1 = new CropLand(new BigDecimal("9.30"));
    	final LandPlot land2 = new Forest(new BigDecimal("40.5"));
    	
    	farm1.setLandPlots(Arrays.asList(land1));
    	land1.setFarm(farm1);
    	
    	farm2.setLandPlots(Arrays.asList(land2));
    	land2.setFarm(farm2);
    	

    	final SingleBuilt<Date> query
	    	= select.max(Farm::getTimeFounded)
				.leftJoin(Farm::getLandPlots)
				.where().upper(Farm::getName).endsWith("M2")
				.build();
	
		store(farm1, farm2, farm3)
		.remove(land1, land2)
		.checkAggregate(query, date("2004-03-22"));
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
    	
    	final Farm     f = select.alias(Farm.class);
    	final LandPlot l = select.alias(LandPlot.class);
    	
    	final SingleBuilt<BigDecimal> query
    		= select.sum(l::getHectares)
    			.innerJoin(f::getLandPlots, l)
    			.where().upper(f::getName).isEqualTo("FARM1")
    			.build();
    	
    	store(farm1, farm2, land4)
    	.remove(land1, land2, land3)
    	.checkAggregate(query, new BigDecimal("49.80"));
    }
    
    @Test
    public void testAggregateSingleAlias_WhereLandPlot_Inner() {
    	
    	final Farm farm1 = makeFarm("Farm1", "1928-09-02");
    	final Farm farm2 = makeFarm("Farm2", "1974-02-29");
    	final Farm farm3 = makeFarm("Farm3", "2004-03-22");

    	final LandPlot land1 = new CropLand(new BigDecimal("9.30"));
    	final LandPlot land2 = new Forest(new BigDecimal("40.5"));
    	
    	farm1.setLandPlots(Arrays.asList(land1));
    	land1.setFarm(farm1);
    	
    	farm2.setLandPlots(Arrays.asList(land2));
    	land2.setFarm(farm2);
    	
    	final Farm     f = select.alias(Farm.class);
    	final LandPlot l = select.alias(LandPlot.class);

    	final SingleBuilt<Date> query
	    	= select.max(f::getTimeFounded)
				.innerJoin(f::getLandPlots, l)
				.where().sqrt(l::getHectares).isLessOrEqualTo(6.3)
				.build();
	
		store(farm1, farm2, farm3)
		// remove to avoid delete constraints when deleting Farm (not cascade)
		.remove(land1, land2)
		.checkAggregate(query, date("1928-09-02"));
    }

    @Test
    public void testAggregateSingleAlias_WhereLandPlot_Left() {
    	
    	// Aggregate left-join
    	final Farm farm1 = makeFarm("Farm1", "1928-09-02");
    	final Farm farm2 = makeFarm("Farm2", "1974-02-29");
    	final Farm farm3 = makeFarm("Farm3", "2004-03-22");

    	final LandPlot land1 = new CropLand(new BigDecimal("9.30"));
    	final LandPlot land2 = new Forest(new BigDecimal("40.5"));
    	
    	farm1.setLandPlots(Arrays.asList(land1));
    	land1.setFarm(farm1);
    	
    	farm2.setLandPlots(Arrays.asList(land2));
    	land2.setFarm(farm2);
    	
    	final Farm     f = select.alias(Farm.class);
    	final LandPlot l = select.alias(LandPlot.class);

    	final SingleBuilt<Date> query
	    	= select.max(f::getTimeFounded)
				.leftJoin(f::getLandPlots, l)
				.where().sqrt(l::getHectares).isGreaterThan(1.2)
				.build();
	
		store(farm1, farm2, farm3)
		/*.dump(Farm.class)
		.dump(LandPlot.class)
		*/
		
		// remove to avoid delete constraints when deleting Farm (not cascade)
		.remove(land1, land2)
		.checkAggregate(query, date("1974-02-29"));
    }
    
    @Test
    public void testAggregateSingleAlias_WhereFarm_Inner() {
    	
    	// Aggregate left-join
    	final Farm farm1 = makeFarm("Farm1", "1928-09-02");
    	final Farm farm2 = makeFarm("Farm2", "1974-02-29");
    	final Farm farm3 = makeFarm("Farm2", "2004-03-22");

    	final LandPlot land1 = new CropLand(new BigDecimal("9.30"));
    	final LandPlot land2 = new Forest(new BigDecimal("40.5"));
    	
    	farm1.setLandPlots(Arrays.asList(land1));
    	land1.setFarm(farm1);
    	
    	farm2.setLandPlots(Arrays.asList(land2));
    	land2.setFarm(farm2);
    	
    	final Farm     f = select.alias(Farm.class);
    	final LandPlot l = select.alias(LandPlot.class);
    	
    	final SingleBuilt<Date> query
	    	= select.max(f::getTimeFounded)
				.innerJoin(f::getLandPlots, l)
				.where().upper(f::getName).endsWith("M2")
				.build();
	
		store(farm1, farm2, farm3)
		.remove(land1, land2)
		.checkAggregate(query, date("1974-02-29"));
    }

    @Test
    public void testAggregateSingleAlias_WhereFarm_Left() {
    	
    	// Aggregate left-join
    	final Farm farm1 = makeFarm("Farm1", "1928-09-02");
    	final Farm farm2 = makeFarm("Farm2", "1974-02-29");
    	final Farm farm3 = makeFarm("Farm2", "2004-03-22");

    	final LandPlot land1 = new CropLand(new BigDecimal("9.30"));
    	final LandPlot land2 = new Forest(new BigDecimal("40.5"));
    	
    	farm1.setLandPlots(Arrays.asList(land1));
    	land1.setFarm(farm1);
    	
    	farm2.setLandPlots(Arrays.asList(land2));
    	land2.setFarm(farm2);
    	
    	final Farm     f = select.alias(Farm.class);
    	final LandPlot l = select.alias(LandPlot.class);

    	final SingleBuilt<Date> query
	    	= select.max(f::getTimeFounded)
				.leftJoin(f::getLandPlots, l)
				.where().upper(f::getName).endsWith("M2")
				.build();
	
		store(farm1, farm2, farm3)
		.remove(land1, land2)
		.checkAggregate(query, date("2004-03-22"));
    }
}
