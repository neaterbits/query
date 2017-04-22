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


public class AggregateJoinWhereOrFunctionTest extends GEN_Farm_BaseTestCase {

    @Test
    public void testAggregateSingleNamed() {
    	
    	final Farm farm1 = new Farm("Farm1");
    	final Farm farm2 = new Farm("Other1");
    	final Farm farm3 = new Farm("Other2");
    	
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
    			.where(Farm::getName).startsWith("O")
    			.or().upper(Farm::getName).endsWith("M1")
    			.build();
    	
    	store(farm1, farm2, farm3, land4)
    	.remove(land1, land2, land3)
    	.checkAggregate(query, new BigDecimal("150.3"));
    }
    
    @Test
    public void testAggregateSingleNamed_WhereLandPlot_Inner() {
    	
    	final Farm farm1 = makeFarm("Farm1", "1928-09-02");
    	final Farm farm2 = makeFarm("Farm2", "1974-02-29");
    	final Farm farm3 = makeFarm("Farm3", "2004-03-22");

    	final LandPlot land1 = new CropLand(new BigDecimal("9.30"));
    	final LandPlot land2 = new Forest(new BigDecimal("40.5"));
    	final LandPlot land3 = new Forest(new BigDecimal("100.5"));
    	
    	farm1.setLandPlots(Arrays.asList(land1, land3));
    	land1.setFarm(farm1);
    	land3.setFarm(farm1);

    	farm2.setLandPlots(Arrays.asList(land2));
    	land2.setFarm(farm2);

    	final SingleBuilt<Date> query
	    	= select.max(Farm::getTimeFounded)
				.innerJoin(Farm::getLandPlots)
				.where(LandPlot::getHectares).isLessOrEqualTo(new BigDecimal("39.6"))
				.or().sqrt(LandPlot::getHectares).isGreaterOrEqualTo(7.1)
				.build();
	
		store(farm1, farm2, farm3, land3)
		.remove(land1, land2)
		.checkAggregate(query, date("1928-09-02"));
    }

    @Test
    public void testAggregateSingleNamed_WhereLandPlot_Left() {
    	
    	final Farm farm1 = makeFarm("Farm1", "1928-09-02");
    	final Farm farm2 = makeFarm("Farm2", "1974-02-29");
    	final Farm farm3 = makeFarm("Farm3", "2004-03-22");

    	final LandPlot land1 = new CropLand(new BigDecimal("9.30"));
    	final LandPlot land2 = new Forest(new BigDecimal("40.5"));
    	final LandPlot land3 = new Forest(new BigDecimal("100.5"));
    	
    	farm1.setLandPlots(Arrays.asList(land1, land3));
    	land1.setFarm(farm1);
    	land3.setFarm(farm1);
    	
    	farm2.setLandPlots(Arrays.asList(land2));
    	land2.setFarm(farm2);
    	

    	final SingleBuilt<Date> query
	    	= select.max(Farm::getTimeFounded)
				.innerJoin(Farm::getLandPlots)
				.where(LandPlot::getHectares).isLessOrEqualTo(new BigDecimal("39.6"))
				.or().sqrt(LandPlot::getHectares).isGreaterOrEqualTo(7.1)
				.build();
	
		store(farm1, farm2, farm3, land3)
		.remove(land1, land2)
		.checkAggregate(query, date("1928-09-02"));
    }
    
    @Test
    public void testAggregateSingleNamed_WhereFarm_Inner() {
    	
    	// Aggregate left-join
    	final Farm farm1 = makeFarm("Farm1", "1928-09-02");
    	final Farm farm2 = makeFarm("Farm2", "1974-02-29");
    	final Farm farm3 = makeFarm("Farm3_select", "1991-07-09");
    	final Farm farm4 = makeFarm("Farm4_select", "2004-03-22");
    	final Farm farm5 = makeFarm("Farm5_select", "2013-11-02");

    	final LandPlot land1 = new CropLand(new BigDecimal("9.30"));
    	final LandPlot land2 = new Forest(new BigDecimal("40.5"));
    	final LandPlot land3 = new Forest(new BigDecimal("100.5"));
    	
    	farm1.setLandPlots(Arrays.asList(land1));
    	land1.setFarm(farm1);
    	
    	farm2.setLandPlots(Arrays.asList(land2));
    	land2.setFarm(farm2);

    	farm3.setLandPlots(Arrays.asList(land3));
    	land3.setFarm(farm3);

    	final SingleBuilt<Date> query
	    	= select.max(Farm::getTimeFounded)
				.innerJoin(Farm::getLandPlots)
				.where(Farm::getTimeFounded).isLessThan(date("1983-01-01"))
				.or().upper(Farm::getName).endsWith("SELECT")
				.build();
	
		store(farm1, farm2, farm3, farm4, farm5)
		.remove(land1, land2, land3)
		.checkAggregate(query, date("1991-07-09"));
    }

    @Test
    public void testAggregateSingleNamed_WhereFarm_Left() {
    	
    	
    	// Aggregate left-join
    	final Farm farm1 = makeFarm("Farm1", "1928-09-02");
    	final Farm farm2 = makeFarm("Farm2", "1974-02-29");
    	final Farm farm3 = makeFarm("Farm3", "1991-03-22");
    	final Farm farm4 = makeFarm("Farm4_select", "2004-03-22");
    	final Farm farm5 = makeFarm("Farm5", "2013-11-02");

    	final LandPlot land1 = new CropLand(new BigDecimal("9.30"));
    	final LandPlot land2 = new Forest(new BigDecimal("40.5"));
    	final LandPlot land3 = new Forest(new BigDecimal("100.5"));
    	
    	farm1.setLandPlots(Arrays.asList(land1));
    	land1.setFarm(farm1);
    	
    	farm2.setLandPlots(Arrays.asList(land2));
    	land2.setFarm(farm2);

    	farm3.setLandPlots(Arrays.asList(land3));
    	land3.setFarm(farm3);
    	
    	final SingleBuilt<Date> query
	    	= select.max(Farm::getTimeFounded)
				.leftJoin(Farm::getLandPlots)
				.where(Farm::getTimeFounded).isLessThan(date("1983-01-01"))
				.or().upper(Farm::getName).endsWith("SELECT")
				.build();
	
		store(farm1, farm2, farm3, farm4, farm5)
		.remove(land1, land2, land3)
		.checkAggregate(query, date("2004-03-22"));
    }
    
    @Test
    public void testAggregateSingleAlias() {
    	
    	final Farm farm1 = new Farm("Farm1");
    	final Farm farm2 = new Farm("Other1");
    	final Farm farm3 = new Farm("Other2");
    	
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
    			.where(f::getName).startsWith("O")
    			.or().upper(f::getName).endsWith("M1")
    			.build();
    	
    	store(farm1, farm2, farm3, land4)
    	.remove(land1, land2, land3)
    	.checkAggregate(query, new BigDecimal("150.3"));
    }
    
    @Test
    public void testAggregateSingleAlias_WhereLandPlot_Inner() {
    	
    	final Farm farm1 = makeFarm("Farm1", "1928-09-02");
    	final Farm farm2 = makeFarm("Farm2", "1974-02-29");
    	final Farm farm3 = makeFarm("Farm3", "2004-03-22");

    	final LandPlot land1 = new CropLand(new BigDecimal("9.30"));
    	final LandPlot land2 = new Forest(new BigDecimal("40.5"));
    	final LandPlot land3 = new Forest(new BigDecimal("100.5"));
    	
    	farm1.setLandPlots(Arrays.asList(land1, land3));
    	land1.setFarm(farm1);
    	land3.setFarm(farm1);

    	farm2.setLandPlots(Arrays.asList(land2));
    	land2.setFarm(farm2);

    	final Farm     f = select.alias(Farm.class);
    	final LandPlot l = select.alias(LandPlot.class);
    	
    	final SingleBuilt<Date> query
	    	= select.max(f::getTimeFounded)
				.innerJoin(f::getLandPlots, l)
				.where(l::getHectares).isLessOrEqualTo(new BigDecimal("39.6"))
				.or().sqrt(l::getHectares).isGreaterOrEqualTo(7.1)
				.build();
	
		store(farm1, farm2, farm3, land3)
		.remove(land1, land2)
		.checkAggregate(query, date("1928-09-02"));
    }

    @Test
    public void testAggregateSingleAlias_WhereLandPlot_Left() {
    	
    	final Farm farm1 = makeFarm("Farm1", "1928-09-02");
    	final Farm farm2 = makeFarm("Farm2", "1974-02-29");
    	final Farm farm3 = makeFarm("Farm3", "2004-03-22");

    	final LandPlot land1 = new CropLand(new BigDecimal("9.30"));
    	final LandPlot land2 = new Forest(new BigDecimal("40.5"));
    	final LandPlot land3 = new Forest(new BigDecimal("100.5"));
    	
    	farm1.setLandPlots(Arrays.asList(land1, land3));
    	land1.setFarm(farm1);
    	land3.setFarm(farm1);
    	
    	farm2.setLandPlots(Arrays.asList(land2));
    	land2.setFarm(farm2);
    	
    	final Farm     f = select.alias(Farm.class);
    	final LandPlot l = select.alias(LandPlot.class);

    	final SingleBuilt<Date> query
	    	= select.max(f::getTimeFounded)
				.innerJoin(f::getLandPlots, l)
				.where(l::getHectares).isLessOrEqualTo(new BigDecimal("39.6"))
				.or().sqrt(l::getHectares).isGreaterOrEqualTo(7.1)
				.build();
	
		store(farm1, farm2, farm3, land3)
		.remove(land1, land2)
		.checkAggregate(query, date("1928-09-02"));
    }
    
    @Test
    public void testAggregateSingleAlias_WhereFarm_Inner() {
    	
    	// Aggregate left-join
    	final Farm farm1 = makeFarm("Farm1", "1928-09-02");
    	final Farm farm2 = makeFarm("Farm2", "1974-02-29");
    	final Farm farm3 = makeFarm("Farm3_select", "1991-07-09");
    	final Farm farm4 = makeFarm("Farm4_select", "2004-03-22");
    	final Farm farm5 = makeFarm("Farm5_select", "2013-11-02");

    	final LandPlot land1 = new CropLand(new BigDecimal("9.30"));
    	final LandPlot land2 = new Forest(new BigDecimal("40.5"));
    	final LandPlot land3 = new Forest(new BigDecimal("100.5"));
    	
    	farm1.setLandPlots(Arrays.asList(land1));
    	land1.setFarm(farm1);
    	
    	farm2.setLandPlots(Arrays.asList(land2));
    	land2.setFarm(farm2);

    	farm3.setLandPlots(Arrays.asList(land3));
    	land3.setFarm(farm3);

    	final Farm     f = select.alias(Farm.class);
    	final LandPlot l = select.alias(LandPlot.class);
    	
    	final SingleBuilt<Date> query
	    	= select.max(f::getTimeFounded)
				.innerJoin(f::getLandPlots, l)
				.where(f::getTimeFounded).isLessThan(date("1983-01-01"))
				.or().upper(f::getName).endsWith("SELECT")
				.build();
	
		store(farm1, farm2, farm3, farm4, farm5)
		.remove(land1, land2, land3)
		.checkAggregate(query, date("1991-07-09"));
    }

    @Test
    public void testAggregateSingleAlias_WhereFarm_Left() {
    	
    	
    	// Aggregate left-join
    	final Farm farm1 = makeFarm("Farm1", "1928-09-02");
    	final Farm farm2 = makeFarm("Farm2", "1974-02-29");
    	final Farm farm3 = makeFarm("Farm3", "1991-03-22");
    	final Farm farm4 = makeFarm("Farm4_select", "2004-03-22");
    	final Farm farm5 = makeFarm("Farm5", "2013-11-02");

    	final LandPlot land1 = new CropLand(new BigDecimal("9.30"));
    	final LandPlot land2 = new Forest(new BigDecimal("40.5"));
    	final LandPlot land3 = new Forest(new BigDecimal("100.5"));
    	
    	farm1.setLandPlots(Arrays.asList(land1));
    	land1.setFarm(farm1);
    	
    	farm2.setLandPlots(Arrays.asList(land2));
    	land2.setFarm(farm2);

    	farm3.setLandPlots(Arrays.asList(land3));
    	land3.setFarm(farm3);
    	
    	final Farm     f = select.alias(Farm.class);
    	final LandPlot l = select.alias(LandPlot.class);
    	
    	final SingleBuilt<Date> query
	    	= select.max(f::getTimeFounded)
				.leftJoin(f::getLandPlots, l)
				.where(f::getTimeFounded).isLessThan(date("1983-01-01"))
				.or().upper(f::getName).endsWith("SELECT")
				.build();
	
		store(farm1, farm2, farm3, farm4, farm5)
		.remove(land1, land2, land3)
		.checkAggregate(query, date("2004-03-22"));
    }
}
