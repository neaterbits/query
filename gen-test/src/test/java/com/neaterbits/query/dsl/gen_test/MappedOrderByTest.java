package com.neaterbits.query.dsl.gen_test;

import org.junit.Test;
import com.neaterbits.query.jpatest.GEN_BaseTestCase;
import com.neaterbits.query.sql.dsl.api.MultiBuilt;
import com.neaterbits.query.test.model.Farm;
import com.neaterbits.query.test.model.mapped.FarmInfo;


public class MappedOrderByTest extends GEN_BaseTestCase {


    @Test
    public void testMappedSingleNamed() {
    	// Should test that is not compilable
		verifyIsCompilable(
				"one(FarmInfo.class)" +
				".map(Farm::getName).to(FarmInfo::setName)");
		
		verifyIsNotCompilable(
				"one(Farm.class)" + 
				".map(Farm::getName).to(FarmInfo::setName)" +
				".orderBy(Farm::getName)");		
    }


    @Test
    public void testMappedSingleAlias() {
		verifyIsCompilable(
		    	Farm.class, "f",
				"one(FarmInfo.class)" +
				".map(f::getName).to(FarmInfo::setName)");

		verifyIsNotCompilable(
				Farm.class, "f",
				"one(Farm.class)" + 
				".map(f::getName).to(FarmInfo::setName)" +
				".orderBy(f::getName)");		
    }


    @Test
    public void testMappedMultiNamed() {
    	final Farm farm1 = new Farm("Farm1", "main2", "sub3");
    	final Farm farm2 = new Farm("Farm2", "main1", null);
    	final Farm farm3 = new Farm("Farm3", "main2", "sub2");
    	final Farm farm4 = new Farm("Farm4", "main3", "sub2");
    	final Farm farm5 = new Farm("Farm5", "main2", "");

    	MultiBuilt<FarmInfo> query = select.list(FarmInfo.class)
    			.map(Farm::getName).to(FarmInfo::setName)
    			.where(Farm::getName).startsWith("Farm")
    			.orderBy(Farm::getName)
    			.build(); 

    	// Store out of order
    	store(farm2, farm4, farm3, farm5, farm1)
    	.checkListOrdered(
    			query,
    			
    			() -> expected( 
					new FarmInfo("Farm1"),
					new FarmInfo("Farm2"),
					new FarmInfo("Farm3"),
					new FarmInfo("Farm4"),
					new FarmInfo("Farm5")
    			));

    	
    	query = select.list(FarmInfo.class)
    			.map(Farm::getName)		.to(FarmInfo::setName)
    			.map(Farm::getFarmId)	.to(FarmInfo::setFarmId)
    			.map(Farm::getSubFarmId).to(FarmInfo::setSubFarmId)
    			
    			.where(Farm::getName).startsWith("Farm")
    			
    			.orderBy(Farm::getFarmId).and(Farm::getSubFarmId).and(Farm::getName)
    			.build(); 

    	// Store out of order
    	store(farm2, farm4, farm3, farm5, farm1)
    	.checkListOrdered(
    			query,
    			
    			() -> expected(
					new FarmInfo("Farm2", "main1", null),
					
					// TODO: null sorts last here, after sub2?? seems like it. check entity and named other tests as well
					new FarmInfo("Farm5", "main2", ""),
					new FarmInfo("Farm3", "main2", "sub2"),
					new FarmInfo("Farm1", "main2", "sub3"),
								
					new FarmInfo("Farm4", "main3", "sub2")
    			));
    	
    	query = select.list(FarmInfo.class)
    			.map(Farm::getFarmId)	.to(FarmInfo::setFarmId)
    			.map(Farm::getName)		.to(FarmInfo::setName)
    			.map(Farm::getSubFarmId).to(FarmInfo::setSubFarmId)
    			.where(Farm::getName).startsWith("Farm")
    			.orderBy(1, 3, 2)
    			.build();    	

    	// Store out of order
    	store(farm2, farm4, farm3, farm5, farm1)
    	.checkListOrdered(
    			query,
    			
    			() -> expected( 
					new FarmInfo("Farm2", "main1", null),
					new FarmInfo("Farm5", "main2", ""),
					new FarmInfo("Farm3", "main2", "sub2"),
					new FarmInfo("Farm1", "main2", "sub3"), // TODO null sort order?
					new FarmInfo("Farm4", "main3", "sub2")
    			));
    }


    @Test
    public void testMappedMultiAlias() {
    	final Farm farm1 = new Farm("Farm1", "main2", "sub3");
    	final Farm farm2 = new Farm("Farm2", "main1", null);
    	final Farm farm3 = new Farm("Farm3", "main2", "sub2");
    	final Farm farm4 = new Farm("Farm4", "main3", "sub2");
    	final Farm farm5 = new Farm("Farm5", "main2", "");

    	final Farm f = select.alias(Farm.class);
    	
    	MultiBuilt<FarmInfo> query = select.list(FarmInfo.class)
    			.map(f::getName).to(FarmInfo::setName)
    			.where(f::getName).startsWith("Farm")
    			.orderBy(f::getName)
    			.build(); 

    	// Store out of order
    	store(farm2, farm4, farm3, farm5, farm1)
    	.checkListOrdered(
    			query,
    			
    			() -> expected( 
					new FarmInfo("Farm1"),
					new FarmInfo("Farm2"),
					new FarmInfo("Farm3"),
					new FarmInfo("Farm4"),
					new FarmInfo("Farm5")
    			));
    	
    	
    	query = select.list(FarmInfo.class)
    			.map(f::getName)		.to(FarmInfo::setName)
    			.map(f::getFarmId)		.to(FarmInfo::setFarmId)
    			.map(f::getSubFarmId)	.to(FarmInfo::setSubFarmId)
    			
    			.where(f::getName).startsWith("Farm")
    			
    			.orderBy(f::getFarmId).and(f::getSubFarmId).and(f::getName)
    			.build(); 
    	
    	
    	// Store out of order
    	store(farm2, farm4, farm3, farm5, farm1)
    	.checkListOrdered(
    			query,
    			
    			() -> expected(
					new FarmInfo("Farm2", "main1", null),
					new FarmInfo("Farm5", "main2", ""),
					new FarmInfo("Farm3", "main2", "sub2"),
					new FarmInfo("Farm1", "main2", "sub3"),
					new FarmInfo("Farm4", "main3", "sub2")
    			));
    	
    	query = select.list(FarmInfo.class)
    			.map(f::getFarmId)		.to(FarmInfo::setFarmId)
    			.map(f::getName)		.to(FarmInfo::setName)
    			.map(f::getSubFarmId)	.to(FarmInfo::setSubFarmId)
    			.where(f::getName).startsWith("Farm")
    			.orderBy(1, 3, 2)
    			.build();    	

    	// Store out of order
    	store(farm2, farm4, farm3, farm5, farm1)
    	.checkListOrdered(
    			query,
    			
    			() -> expected( 
					new FarmInfo("Farm2", "main1", null),
					new FarmInfo("Farm5", "main2", ""),
					new FarmInfo("Farm3", "main2", "sub2"),
					new FarmInfo("Farm1", "main2", "sub3"),
					new FarmInfo("Farm4", "main3", "sub2")
    			));
    }
}
