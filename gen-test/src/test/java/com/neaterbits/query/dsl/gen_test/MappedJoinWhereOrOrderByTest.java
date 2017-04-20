package com.neaterbits.query.dsl.gen_test;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.Test;

import com.neaterbits.query.jpatest.GEN_BaseTestCase;
import com.neaterbits.query.sql.dsl.api.MultiBuilt;
import com.neaterbits.query.sql.dsl.api.SingleBuilt;
import com.neaterbits.query.test.model.Farm;
import com.neaterbits.query.test.model.land.CropLand;
import com.neaterbits.query.test.model.land.Forest;
import com.neaterbits.query.test.model.land.LandPlot;
import com.neaterbits.query.test.model.land.Uncultivated;
import com.neaterbits.query.test.model.mapped.FarmLand;


public class MappedJoinWhereOrOrderByTest extends GEN_Farm_BaseTestCase {


    @Test
    public void testMappedSingleNamed() {
    	verifyIsNotCompilable(
    			a -> a.add(Farm.class, "f").add(LandPlot.class, "l"),
    			
    			"one(FarmLand.class)" +
    	    	".map(Farm::getName)	 .to(FarmLand::setFarmName)" +
    	    	".map(LandPlot::getHectares) .to(FarmLand::setHectares)" +
    			".innerJoin(Farm::getLandPlots)" +
    			".where(LandPlot::getHectares).isGreaterThan(new BigDecimal(\"100.5\"))" +
    			".or(LandPlot::getHectares).isLessThan(new BigDecimal(\"200.5\")" +
    			".orderBy(LandPlot::getHectares)");

    	verifyIsCompilable(
    			a -> a.add(Farm.class, "f").add(LandPlot.class, "l"),
    			
    			"one(FarmLand.class)" +
    	    	".map(Farm::getName)	 .to(FarmLand::setFarmName)" +
    	    	".map(LandPlot::getHectares) .to(FarmLand::setHectares)" +
    			".innerJoin(Farm::getLandPlots)" +
    			".where(LandPlot::getHectares).isGreaterThan(new BigDecimal(\"100.5\"))" +
    			".or(LandPlot::getHectares).isLessThan(new BigDecimal(\"200.5\"))");
    	
    }

    @Test
    public void testMappedSingleAlias() {
    	verifyIsNotCompilable(
    			a -> a.add(Farm.class, "f").add(LandPlot.class, "l"),
    			
    			"one(FarmLand.class)" +
    	    	".map(f::getName)	 .to(FarmLand::setFarmName)" +
    	    	".map(l::getHectares) .to(FarmLand::setHectares)" +
    			".innerJoin(f::getLandPlots, l)" +
    			".where(l::getHectares).isGreaterThan(new BigDecimal(\"100.5\"))" +
    			".or(l::getHectares).isLessThan(new BigDecimal(\"200.5\"))" +
    			".orderBy(l::getHectares)");

    	verifyIsCompilable(
    			a -> a.add(Farm.class, "f").add(LandPlot.class, "l"),
    			
    			"one(FarmLand.class)" +
    	    	".map(f::getName)	 .to(FarmLand::setFarmName)" +
    	    	".map(l::getHectares) .to(FarmLand::setHectares)" +
    			".innerJoin(f::getLandPlots, l)" +
    			".where(l::getHectares).isGreaterThan(new BigDecimal(\"100.5\"))" +
    			".or(l::getHectares).isLessThan(new BigDecimal(\"200.5\"))");
    	
    }

    
    @Test
    public void testMappedMultiNamed() {
    	verifyIsNotCompilable(
    			a -> a.add(Farm.class, "f").add(LandPlot.class, "l"),
    			
    			"list(FarmLand.class)" +
    	    	".map(Farm::getName)	 .to(FarmLand::setFarmName)" +
    	    	".map(LandPlot::getHectares) .to(FarmLand::setHectares)" +
    			".innerJoin(Farm::getLandPlots)" +
    			".where(LandPlot::getHectares).isGreaterThan(new BigDecimal(\"100.5\"))" +
    			".or(LandPlot::getHectares).isLessThan(new BigDecimal(\"200.5\"))" +
    			".orderBy(l::getHectares)");

    	verifyIsCompilable(
    			a -> a.add(Farm.class, "f").add(LandPlot.class, "l"),
    			
    			"list(FarmLand.class)" +
    	    	".map(Farm::getName)	 .to(FarmLand::setFarmName)" +
    	    	".map(LandPlot::getHectares) .to(FarmLand::setHectares)" +
    			".innerJoin(Farm::getLandPlots)" +
    			".where(LandPlot::getHectares).isGreaterThan(new BigDecimal(\"100.5\"))" +
    			".or(LandPlot::getHectares).isGreaterThan(new BigDecimal(\"100.5\"))" +
    			".orderBy(LandPlot::getHectares)");

    	final Farm farm1 = new Farm("Farm1");
    	final Farm farm2 = new Farm("Farm2");
    	final Farm farm3 = new Farm("Farm3");
    	
    	final LandPlot land1 = new CropLand(new BigDecimal("9.30"));
    	final LandPlot land2 = new Uncultivated(new BigDecimal("345.43"));
    	final LandPlot land3 = new Forest(new BigDecimal("40.5"));
    	final LandPlot land4 = new Uncultivated(new BigDecimal("100.5"));
    	final LandPlot land5 = new Uncultivated(new BigDecimal("140.2"));
    	final LandPlot land6 = new Uncultivated(new BigDecimal("302.4"));

    	farm1.setLandPlots(Arrays.asList(land1, land2, land3));
    	land1.setFarm(farm1);
    	land2.setFarm(farm1);
    	land3.setFarm(farm1);
    	
    	farm2.setLandPlots(Arrays.asList(land4, land5));
    	land4.setFarm(farm2);
    	land5.setFarm(farm2);
    	
    	final MultiBuilt<FarmLand> query
    		= select.list(FarmLand.class)
				.map(Farm::getName)			.to(FarmLand::setFarmName)
				.map(LandPlot::getHectares) .to(FarmLand::setHectares)
    			.innerJoin(Farm::getLandPlots)
    			.where(LandPlot::getHectares).isGreaterThan(new BigDecimal("100.0"))
    			.or(LandPlot::getHectares).isLessOrEqualTo(new BigDecimal("9.30"))
    			.orderBy(LandPlot::getHectares)
    			.build();
    	
    	store(farm1, farm2, farm3, land6)
    	// remove to avoid delete constraints when deleting Farm (not cascade)
    	.remove(land1, land2, land3, land4, land5)
    	.checkListOrdered(query, () -> 
    			Arrays.asList(
    				new FarmLand("Farm1", new BigDecimal("9.30")),
    				new FarmLand("Farm2", new BigDecimal("100.5")),
    				new FarmLand("Farm2", new BigDecimal("140.2")),
    				new FarmLand("Farm1", new BigDecimal("345.43"))
				));
    }

    @Test
    public void testMappedMultiNamed_LandPlot_Left() {
    	final Farm farm1 = new Farm("Farm1");
    	final Farm farm2 = new Farm("Farm2");
    	final Farm farm3 = new Farm("Farm3");
    	
    	final LandPlot land1 = new CropLand(new BigDecimal("9.30"));
    	final LandPlot land2 = new Uncultivated(new BigDecimal("345.43"));
    	final LandPlot land3 = new Forest(new BigDecimal("40.5"));
    	final LandPlot land4 = new Uncultivated(new BigDecimal("100.5"));
    	final LandPlot land5 = new Uncultivated(new BigDecimal("140.2"));
    	final LandPlot land6 = new Uncultivated(new BigDecimal("302.4"));

    	farm1.setLandPlots(Arrays.asList(land1, land2, land3));
    	land1.setFarm(farm1);
    	land2.setFarm(farm1);
    	land3.setFarm(farm1);
    	
    	farm2.setLandPlots(Arrays.asList(land4, land5));
    	land4.setFarm(farm2);
    	land5.setFarm(farm2);
    	
    	// only landplots that belong to farms, so land4 should not be included in sum
    	// since doing innerjoin from farm to landplot
    	final MultiBuilt<FarmLand> query
    		= select.list(FarmLand.class)
				.map(Farm::getName)			.to(FarmLand::setFarmName)
				.map(LandPlot::getHectares) .to(FarmLand::setHectares)
    			.leftJoin(Farm::getLandPlots)   
    			.where(LandPlot::getHectares).isGreaterThan(new BigDecimal("100.0"))
    			.or(LandPlot::getHectares).isLessOrEqualTo(new BigDecimal("9.30"))
    			.orderBy(LandPlot::getHectares)
    			.build();
    	
    	store(farm1, farm2, farm3, land6)
    	.remove(land1, land2, land3, land4, land5)
    	.checkListUnordered(query, () -> Arrays.asList(
				new FarmLand("Farm1", new BigDecimal("9.30")),
				new FarmLand("Farm2", new BigDecimal("100.5")),
				new FarmLand("Farm2", new BigDecimal("140.2")),
				new FarmLand("Farm1", new BigDecimal("345.43"))
				//,new FarmLand("Farm3", null)) matches not, as long as where doesn't match would require where-clause on farm
    			));
    }
    
    
    // Join with where-clause on farm, to see that both orders AND adds farms with no landplots
    @Test
    public void testMappedMultiNamed_Farm_Left() {

    	final Farm farm1 = makeFarm("Farm1", "1993-03-12");
    	final Farm farm2 = makeFarm("Farm1", "1873-04-23");
    	final Farm farm3 = makeFarm("Farm2", "1974-12-11");
    	final Farm farm4 = makeFarm("Farm3", "1843-07-25");
    	final Farm farm5 = makeFarm("Farm4", "1733-05-09");

    	final LandPlot land1 = new CropLand(new BigDecimal("9.30"));
    	final LandPlot land2 = new Uncultivated(new BigDecimal("345.43"));
    	final LandPlot land3 = new Forest(new BigDecimal("40.5"));
    	final LandPlot land4 = new Uncultivated(new BigDecimal("100.5"));
    	final LandPlot land5 = new Uncultivated(new BigDecimal("140.2"));
    	final LandPlot land6 = new Uncultivated(new BigDecimal("302.4"));

    	farm1.setLandPlots(Arrays.asList(land1, land2));
    	land1.setFarm(farm1);
    	land2.setFarm(farm1);

    	farm2.setLandPlots(Arrays.asList(land3, land4));
    	land3.setFarm(farm2);
    	land4.setFarm(farm2);

    	farm3.setLandPlots(Arrays.asList(land5));
    	land5.setFarm(farm3);
    	
    	farm4.setLandPlots(Arrays.asList(land6));
    	land6.setFarm(farm4);

    	// NOTE! this should include farm5 in result, even if does not have  
    	MultiBuilt<FarmLand> query
    		= select.list(FarmLand.class)
				.map(Farm::getName)			.to(FarmLand::setFarmName)
				.map(Farm::getTimeFounded)  .to(FarmLand::setTimeFarmFounded)
				.map(LandPlot::getHectares) .to(FarmLand::setHectares)
    			.leftJoin(Farm::getLandPlots)   
    			.where(Farm::getTimeFounded).isGreaterThan(date("1900-01-01"))
    			.or(Farm::getTimeFounded).isLessThan(date("1800-01-01"))
    			.orderBy(Farm::getTimeFounded)
    			.build();
    	
    	store(farm1, farm2, farm3, farm4, farm5)
    	.remove(land1, land2, land3, land4, land5, land6)
    	.checkListUnordered(query, () -> Arrays.asList(
				new FarmLand("Farm1", new BigDecimal("9.30")),
				new FarmLand("Farm2", new BigDecimal("100.5")),
				new FarmLand("Farm2", new BigDecimal("140.2")),
				new FarmLand("Farm1", new BigDecimal("345.43"))
				//,new FarmLand("Farm3", null)) matches not, as long as where doesn't match would require where-clause on farm
    			));
    	// TODO: test with joins with where-clauses on farm
    }

    @Test
    public void testMappedMultiAlias() {
    	verifyIsNotCompilable(
    			a -> a.add(Farm.class, "f").add(LandPlot.class, "l"),
    			
    			"list(FarmLand.class)" +
    	    	".map(f::getName)	 .to(FarmLand::setFarmName)" +
    	    	".map(l::getHectares) .to(FarmLand::setHectares)" +
    			".innerJoin(f::getLandPlots, l)" +
    			".where(l::getHectares).isGreaterThan(new BigDecimal(\"100.5\"))" +
    			".or(LandPlot::getHectares).isLessThan(new BigDecimal(\"200.5\"))");

    	verifyIsCompilable(
    			a -> a.add(Farm.class, "f").add(LandPlot.class, "l"),
    			
    			"list(FarmLand.class)" +
    	    	".map(f::getName)	 .to(FarmLand::setFarmName)" +
    	    	".map(l::getHectares) .to(FarmLand::setHectares)" +
    			".innerJoin(f::getLandPlots, l)" +
    			".where(l::getHectares).isGreaterThan(new BigDecimal(\"100.5\"))" +
    			".or(l::getHectares).isLessThan(new BigDecimal(\"200.5\"))");

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

    	final Farm 	   f = select.alias(Farm.class);
    	final LandPlot l = select.alias(LandPlot.class);
    	
    	final MultiBuilt<FarmLand> query
    		= select.list(FarmLand.class)
				.map(f::getName)	.to(FarmLand::setFarmName)
				.map(l::getHectares).to(FarmLand::setHectares)
    			.innerJoin(f::getLandPlots, l)
    			.where(l::getHectares).isGreaterThan(new BigDecimal("40.5"))
    			.or(l::getHectares).isLessOrEqualTo(new BigDecimal("9.30"))
    			.build();
    	
    	store(farm1, farm2, farm3, land4)
    	// remove to avoid delete constraints when deleting Farm (not cascade)
    	.remove(land1, land2, land3)
    	.checkListUnordered(query, () -> 
    			Arrays.asList(
    				new FarmLand("Farm1", new BigDecimal("9.30")),
    				new FarmLand("Farm2", new BigDecimal("100.5"))));
    }

    @Test
    public void testMappedMultiAlias_Farm_Left() {
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
    	
    	final Farm 	   f = select.alias(Farm.class);
    	final LandPlot l = select.alias(LandPlot.class);
    	
    	// only landplots that belong to farms, so land4 should not be included in sum
    	// since doing innerjoin from farm to landplot
    	final MultiBuilt<FarmLand> query
    		= select.list(FarmLand.class)
				.map(f::getName)	.to(FarmLand::setFarmName)
				.map(l::getHectares).to(FarmLand::setHectares)
    			.leftJoin(f::getLandPlots, l)   
    			.where(l::getHectares).isGreaterThan(new BigDecimal("40.5"))
    			.or(l::getHectares).isLessOrEqualTo(new BigDecimal("9.30"))
    			.build();
    	
    	store(farm1, farm2, farm3, land4)
    	.remove(land1, land2, land3)
    	.checkListUnordered(query, () -> Arrays.asList(
				new FarmLand("Farm1", new BigDecimal("9.30")),
				new FarmLand("Farm2", new BigDecimal("100.5"))
				//,new FarmLand("Farm3", null))
    			));
    }

}
