package com.neaterbits.query.dsl.gen_test;

import org.junit.Test;
import com.neaterbits.query.jpatest.GEN_BaseTestCase;
import com.neaterbits.query.sql.dsl.api.MultiBuilt;
import com.neaterbits.query.test.model.Farm;
import com.neaterbits.query.test.model.mapped.FarmInfo;


public class MappedGroupByTest extends GEN_BaseTestCase {

    @Test
    public void testMappedSingleNamed() {
		verifyIsCompilable(
				"one(FarmInfo.class)" +
				".map(Farm::getName).to(FarmInfo::setName)");

		verifyIsCompilable(
				"list(FarmInfo.class)" +
				".map(Farm::getName).to(FarmInfo::setName)" +
				".groupBy(Farm::getName)"
						);
		
		verifyIsNotCompilable(
				"one(FarmInfo.class)" + 
				".map(Farm::getName).to(FarmInfo::setName)" +
				".groupBy(Farm::getName)");		
    }

    @Test
    public void testMappedSingleAlias() {
		verifyIsCompilable(
		    	Farm.class, "f",
				"one(FarmInfo.class)" +
				".map(f::getName).to(FarmInfo::setName)");

		verifyIsCompilable(
		    	Farm.class, "f",
				"one(FarmInfo.class)" +
				".map(f::getName).to(FarmInfo::setName)" + 
				".groupBy(f::getName)");

		verifyIsNotCompilable(
				Farm.class, "f",
				"one(FarmInfo.class)" + 
				".map(f::getName).to(FarmInfo::setName)" +
				".groupBy(f::getName)");		
    }


    @Test
    public void testMappedMultiNamed() {
		final Farm farm1 = new Farm("Farm1", "main2", "sub3");
    	final Farm farm2 = new Farm("Farm2", "main1", null);
    	final Farm farm3 = new Farm("Farm3", "main2", "sub2");
    	final Farm farm4 = new Farm("Farm4", "main3", "sub2");
    	final Farm farm5 = new Farm("Farm5", "main2", "");

    	MultiBuilt<FarmInfo> query = select.list(FarmInfo.class)
    			.map(Farm::getFarmId).to(FarmInfo::setFarmId)
    			.groupBy(Farm::getFarmId)
    			.build(); 

    	// Store out of order
    	store(farm2, farm4, farm3, farm5, farm1)
    	.checkListUnordered(
    			query,
    			
    			() -> expected( 
					new FarmInfo(null, "main1", null),
					new FarmInfo(null, "main2", null),
					new FarmInfo(null, "main3", null)
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
    			.map(f::getFarmId).to(FarmInfo::setFarmId)
    			.groupBy(f::getFarmId)
    			.build(); 

    	// Store out of order
    	store(farm2, farm4, farm3, farm5, farm1)
    	.checkListUnordered(
    			query,
    			
    			() -> expected( 
					new FarmInfo(null, "main1", null),
					new FarmInfo(null, "main2", null),
					new FarmInfo(null, "main3", null)
    			));
    }
}
