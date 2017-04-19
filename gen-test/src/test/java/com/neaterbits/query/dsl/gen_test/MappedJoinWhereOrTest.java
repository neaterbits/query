package com.neaterbits.query.dsl.gen_test;


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


public class MappedJoinWhereOrTest extends GEN_BaseTestCase {


    @Test
    public void testMappedSingleNamed() {
    	verifyIsNotCompilable(
    			a -> a.add(Farm.class, "f").add(LandPlot.class, "l"),
    			
    			"one(FarmLand.class)" +
    	    	".map(Farm::getName)	 .to(FarmLand::setFarmName)" +
    	    	".map(LandPlot::getHectares) .to(FarmLand::setHectares)" +
    			".innerJoin(Farm::getLandPlots)" +
    			".where(LandPlot::getHectares).isGreaterThan(new BigDecimal(\"100.5\"))" +
    			".or(l::getHectares).isLessThan(new BigDecimal(\"200.5\")");

    	verifyIsCompilable(
    			a -> a.add(Farm.class, "f").add(LandPlot.class, "l"),
    			
    			"one(FarmLand.class)" +
    	    	".map(Farm::getName)	 .to(FarmLand::setFarmName)" +
    	    	".map(LandPlot::getHectares) .to(FarmLand::setHectares)" +
    			".innerJoin(Farm::getLandPlots)" +
    			".where(LandPlot::getHectares).isGreaterThan(new BigDecimal(\"100.5\"))" +
    			".or(LandPlot::getHectares).isLessThan(new BigDecimal(\"200.5\"))");
    	
    	final Farm farm1 = new Farm("Farm1");
    	final Farm farm2 = new Farm("Farm2");
    	
    	final LandPlot land1 = new CropLand(new BigDecimal("9.30"));
    	final LandPlot land2 = new Forest(new BigDecimal("40.5"));
    	final LandPlot land3 = new Uncultivated(new BigDecimal("100.5"));
    	final LandPlot land4 = new Uncultivated(new BigDecimal("345.43"));

    	// Only one landplot attached to farms
    	farm1.setLandPlots(Arrays.asList(land2));
    	land2.setFarm(farm1);
    	
    	farm2.setLandPlots(Arrays.asList(land3, land4));
    	land3.setFarm(farm2);
    	land4.setFarm(farm2);
    	
    	// only landplots that belong to farms, so land4 should not be included in sum
    	// since doing innerjoin from farm to landplot
    	SingleBuilt<FarmLand> query = select.one(FarmLand.class)
    			.map(Farm::getName)			.to(FarmLand::setFarmName)
    			.map(LandPlot::getHectares) .to(FarmLand::setHectares)
    			.innerJoin(Farm::getLandPlots)
    			.where(LandPlot::getHectares).isGreaterThan(new BigDecimal("400.01"))
    			.or(LandPlot::getHectares).isLessThan(new BigDecimal("50.01"))
    			.build();
    	
    	store(farm1, farm2, land1)
    	// remove to avoid delete constraints when deleting Farm (not cascade)
    	.remove(land2, land3, land4)
    	.checkOne(query, () -> new FarmLand("Farm1", new BigDecimal("40.5")));
    	
    	// check match on where as wel
    	query = select.one(FarmLand.class)
    			.map(Farm::getName)			.to(FarmLand::setFarmName)
    			.map(LandPlot::getHectares) .to(FarmLand::setHectares)
    			.innerJoin(Farm::getLandPlots)
    			.where(LandPlot::getHectares).isGreaterThan(new BigDecimal("300.01"))
    			.or(LandPlot::getHectares).isLessThan(new BigDecimal("40.01"))
    			.build();
    	store(farm1, farm2, land1)
		// remove to avoid delete constraints when deleting Farm (not cascade)
    	.remove(land2, land3, land4)
    	.checkOne(query, () -> new FarmLand("Farm2", new BigDecimal("345.43")));
    }

    @Test
    public void testMappedSingleNamed_Farm_Left() {
    	final Farm farm1 = new Farm("Farm1");
    	final Farm farm2 = new Farm("Farm2");
    	
    	final LandPlot land1 = new CropLand(new BigDecimal("9.30"));
    	final LandPlot land2 = new Forest(new BigDecimal("40.5"));
    	final LandPlot land3 = new Uncultivated(new BigDecimal("100.5"));
    	final LandPlot land4 = new Uncultivated(new BigDecimal("345.43"));
    	
    	farm1.setLandPlots(Arrays.asList(land1, land2));
    	land1.setFarm(farm1);
    	land2.setFarm(farm2);
    	
    	farm2.setLandPlots(Arrays.asList(land3));
    	land3.setFarm(farm2);

    	// only landplots that belong to farms, so land4 should not be included in sum
    	// since doing innerjoin from farm to landplot
    	
    	// Match or
    	SingleBuilt<FarmLand> query = select.one(FarmLand.class)
    			.map(Farm::getName)			.to(FarmLand::setFarmName)
    			.map(LandPlot::getHectares) .to(FarmLand::setHectares)
    			.leftJoin(Farm::getLandPlots)
    			.where(LandPlot::getHectares).isLessThan(new BigDecimal("8.40"))
    			.or(LandPlot::getHectares).isGreaterOrEqualTo(new BigDecimal("100.5"))
    			.build();
    	
    	store(farm1, farm2, land4)
    	.remove(land1, land2, land3)
    	.checkOne(query, () -> new FarmLand("Farm2", new BigDecimal("100.5")));
    	
    	// match where
    	query = select.one(FarmLand.class)
    			.map(Farm::getName)			.to(FarmLand::setFarmName)
    			.map(LandPlot::getHectares) .to(FarmLand::setHectares)
    			.leftJoin(Farm::getLandPlots)
    			.where(LandPlot::getHectares).isLessThan(new BigDecimal("40"))
    			.or(LandPlot::getHectares).isGreaterOrEqualTo(new BigDecimal("100.6"))
    			.build();
    	
    	store(farm1, farm2, land4)
    	.remove(land1, land2, land3)
    	.checkOne(query, () -> new FarmLand("Farm1", new BigDecimal("9.30")));    	
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
    			".or(LandPlot::getHectares).isLessThan(new BigDecimal(\"200.5\"))");

    	verifyIsCompilable(
    			a -> a.add(Farm.class, "f").add(LandPlot.class, "l"),
    			
    			"one(FarmLand.class)" +
    	    	".map(f::getName)	 .to(FarmLand::setFarmName)" +
    	    	".map(l::getHectares) .to(FarmLand::setHectares)" +
    			".innerJoin(f::getLandPlots, l)" +
    			".where(l::getHectares).isGreaterThan(new BigDecimal(\"100.5\"))" +
    			".or(l::getHectares).isLessThan(new BigDecimal(\"200.5\"))");
    	
    	
    	final Farm farm1 = new Farm("Farm1");
    	final Farm farm2 = new Farm("Farm2");
    	
    	final LandPlot land1 = new CropLand(new BigDecimal("9.30"));
    	final LandPlot land2 = new Forest(new BigDecimal("40.5"));
    	final LandPlot land3 = new Uncultivated(new BigDecimal("100.5"));
    	final LandPlot land4 = new Uncultivated(new BigDecimal("345.43"));

    	// Only one landplot attached to farms
    	farm1.setLandPlots(Arrays.asList(land2));
    	land2.setFarm(farm1);
    	
    	farm2.setLandPlots(Arrays.asList(land3, land4));
    	land3.setFarm(farm2);
    	land4.setFarm(farm2);

    	final Farm 	   f = select.alias(Farm.class);
    	final LandPlot l = select.alias(LandPlot.class);

    	// only landplots that belong to farms, so land4 should not be included in sum
    	// since doing innerjoin from farm to landplot
    	SingleBuilt<FarmLand> query = select.one(FarmLand.class)
    			.map(f::getName)			.to(FarmLand::setFarmName)
    			.map(l::getHectares) .to(FarmLand::setHectares)
    			.innerJoin(f::getLandPlots, l)
    			.where(l::getHectares).isGreaterThan(new BigDecimal("400.01"))
    			.or(l::getHectares).isLessThan(new BigDecimal("50.01"))
    			.build();
    	
    	store(farm1, farm2, land1)
    	// remove to avoid delete constraints when deleting Farm (not cascade)
    	.remove(land2, land3, land4)
    	.checkOne(query, () -> new FarmLand("Farm1", new BigDecimal("40.5")));
    	
    	// check match on where as wel
    	query = select.one(FarmLand.class)
    			.map(f::getName)	 .to(FarmLand::setFarmName)
    			.map(l::getHectares) .to(FarmLand::setHectares)
    			.innerJoin(f::getLandPlots, l)
    			.where(l::getHectares).isGreaterThan(new BigDecimal("300.01"))
    			.or(l::getHectares).isLessThan(new BigDecimal("40.01"))
    			.build();
    	store(farm1, farm2, land1)
		// remove to avoid delete constraints when deleting Farm (not cascade)
    	.remove(land2, land3, land4)
    	.checkOne(query, () -> new FarmLand("Farm2", new BigDecimal("345.43")));
    }

    @Test
    public void testMappedSingleAlias_Farm_Left() {
    	final Farm farm1 = new Farm("Farm1");
    	final Farm farm2 = new Farm("Farm2");
    	
    	final LandPlot land1 = new CropLand(new BigDecimal("9.30"));
    	final LandPlot land2 = new Forest(new BigDecimal("40.5"));
    	final LandPlot land3 = new Uncultivated(new BigDecimal("100.5"));
    	final LandPlot land4 = new Uncultivated(new BigDecimal("345.43"));
    	
    	farm1.setLandPlots(Arrays.asList(land1, land2));
    	land1.setFarm(farm1);
    	land2.setFarm(farm2);
    	
    	farm2.setLandPlots(Arrays.asList(land3));
    	land3.setFarm(farm2);

    	final Farm 	   f = select.alias(Farm.class);
    	final LandPlot l = select.alias(LandPlot.class);
    	
    	// only landplots that belong to farms, so land4 should not be included in sum
    	// since doing innerjoin from farm to landplot
    	// Match or
    	SingleBuilt<FarmLand> query = select.one(FarmLand.class)
    			.map(f::getName)			.to(FarmLand::setFarmName)
    			.map(l::getHectares) .to(FarmLand::setHectares)
    			.leftJoin(f::getLandPlots, l)
    			.where(l::getHectares).isLessThan(new BigDecimal("8.40"))
    			.or(l::getHectares).isGreaterOrEqualTo(new BigDecimal("100.5"))
    			.build();
    	
    	store(farm1, farm2, land4)
    	.remove(land1, land2, land3)
    	.checkOne(query, () -> new FarmLand("Farm2", new BigDecimal("100.5")));
    	
    	// match where
    	query = select.one(FarmLand.class)
    			.map(f::getName)	 .to(FarmLand::setFarmName)
    			.map(l::getHectares) .to(FarmLand::setHectares)
    			.leftJoin(f::getLandPlots, l)
    			.where(l::getHectares).isLessThan(new BigDecimal("40"))
    			.or(l::getHectares).isGreaterOrEqualTo(new BigDecimal("100.6"))
    			.build();
    	
    	store(farm1, farm2, land4)
    	.remove(land1, land2, land3)
    	.checkOne(query, () -> new FarmLand("Farm1", new BigDecimal("9.30")));    	
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
    			".or(l::getHectares).isLessThan(new BigDecimal(\"200.5\"))");

    	verifyIsCompilable(
    			a -> a.add(Farm.class, "f").add(LandPlot.class, "l"),
    			
    			"list(FarmLand.class)" +
    	    	".map(Farm::getName)	 .to(FarmLand::setFarmName)" +
    	    	".map(LandPlot::getHectares) .to(FarmLand::setHectares)" +
    			".innerJoin(Farm::getLandPlots)" +
    			".where(LandPlot::getHectares).isGreaterThan(new BigDecimal(\"100.5\"))" +
    			".or(LandPlot::getHectares).isGreaterThan(new BigDecimal(\"100.5\"))");

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
    	
    	final MultiBuilt<FarmLand> query
    		= select.list(FarmLand.class)
				.map(Farm::getName)			.to(FarmLand::setFarmName)
				.map(LandPlot::getHectares) .to(FarmLand::setHectares)
    			.innerJoin(Farm::getLandPlots)
    			.where(LandPlot::getHectares).isGreaterThan(new BigDecimal("40.5"))
    			.or(LandPlot::getHectares).isLessOrEqualTo(new BigDecimal("9.30"))
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
    public void testMappedMultiNamed_Farm_Left() {
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
    	final MultiBuilt<FarmLand> query
    		= select.list(FarmLand.class)
				.map(Farm::getName)			.to(FarmLand::setFarmName)
				.map(LandPlot::getHectares) .to(FarmLand::setHectares)
    			.leftJoin(Farm::getLandPlots)   
    			.where(LandPlot::getHectares).isGreaterThan(new BigDecimal("40.5"))
    			.or(LandPlot::getHectares).isLessOrEqualTo(new BigDecimal("9.30"))
    			.build();
    	
    	store(farm1, farm2, farm3, land4)
    	.remove(land1, land2, land3)
    	.checkListUnordered(query, () -> Arrays.asList(
				new FarmLand("Farm1", new BigDecimal("9.30")),
				new FarmLand("Farm2", new BigDecimal("100.5"))
				//,new FarmLand("Farm3", null))
    			));
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
