package com.neaterbits.query.dsl.gen_test;

import org.junit.Test;

import com.neaterbits.query.jpatest.GEN_BaseTestCase;
import com.neaterbits.query.sql.dsl.api.MultiBuilt;
import com.neaterbits.query.test.model.Farm;


public class EntityOrderByTest extends GEN_BaseTestCase {


    @Test
    public void testEntitySingleNamed() {
    	// Should test that is not compilable
		verifyIsCompilable(
				b -> b.addImport(Farm.class),
				"one(Farm.class)");
		
		verifyIsNotCompilable(
				b -> b.addImport(Farm.class),
				"one(Farm.class)" + 
				".orderBy(Farm::getName)");		
    }


    @Test
    public void testEntitySingleAlias() {
		verifyIsCompilable(
				b -> b.add(Farm.class, "f"),
				"one(f)");
		
		verifyIsNotCompilable(
				b -> b.add(Farm.class, "f"),
				"one(f)" + 
				".orderBy(f::getName)");		
    }


    @Test
    public void testEntityMultiNamed() {
    	final Farm farm1 = new Farm("Farm1", "main2", "sub3");
    	final Farm farm2 = new Farm("Farm2", "main1", null);
    	final Farm farm3 = new Farm("Farm3", "main2", "sub2");
    	final Farm farm4 = new Farm("Farm4", "main3", "sub2");
    	final Farm farm5 = new Farm("Farm5", "main2", "");

    	MultiBuilt<Farm> query = select.list(Farm.class)
    			.where(Farm::getName).startsWith("Farm")
    			.orderBy(Farm::getName)
    			.build(); 

    	// Store out of order
    	store(farm2, farm4, farm3, farm5, farm1)
    	.checkListOrdered(
    			query,
    			
    			() -> expected(
					new Farm(farm1.getId(), "Farm1", "main2", "sub3"),
					new Farm(farm2.getId(), "Farm2", "main1", null),
					new Farm(farm3.getId(), "Farm3", "main2", "sub2"),
					new Farm(farm4.getId(), "Farm4", "main3", "sub2"),
					new Farm(farm5.getId(), "Farm5", "main2", "")
    			));
    	
    	query = select.list(Farm.class)
    			
    			.where(Farm::getName).startsWith("Farm")
    			
    			.orderBy(Farm::getFarmId).and(Farm::getSubFarmId).and(Farm::getName)
    			.build(); 

    	// Store out of order
    	store(farm2, farm4, farm3, farm5, farm1)
    	.checkListOrdered(
    			query,
    			
    			() -> expected(
    					new Farm(farm2.getId(), "Farm2", "main1", null),
    					new Farm(farm5.getId(), "Farm5", "main2", ""),
    					new Farm(farm3.getId(), "Farm3", "main2", "sub2"),
    					new Farm(farm1.getId(), "Farm1", "main2", "sub3"),
    					new Farm(farm4.getId(), "Farm4", "main3", "sub2")
    			));
    	
    	// Entities cannot be ordered by numeric index, since difficult to make sense of (order of attributes in class? very brittle)
    	verifyIsNotCompilable("list(FarmInfo.class)" +
    			".where(Farm::getName).startsWith(\"Farm\")" +
    			".orderBy(1, 3, 2)");
    }


    @Test
    public void testEntityMultiAlias() {
    	final Farm farm1 = new Farm("Farm1", "main2", "sub3");
    	final Farm farm2 = new Farm("Farm2", "main1", null);
    	final Farm farm3 = new Farm("Farm3", "main2", "sub2");
    	final Farm farm4 = new Farm("Farm4", "main3", "sub2");
    	final Farm farm5 = new Farm("Farm5", "main2", "");

    	final Farm f = select.alias(Farm.class);

    	MultiBuilt<Farm> query = select.list(f)
    			.where(f::getName).startsWith("Farm")
    			.orderBy(f::getName)
    			.build(); 

    	// Store out of order
    	store(farm2, farm4, farm3, farm5, farm1)
    	.checkListOrdered(
    			query,
    			
    			() -> expected(
					new Farm(farm1.getId(), "Farm1", "main2", "sub3"),
					new Farm(farm2.getId(), "Farm2", "main1", null),
					new Farm(farm3.getId(), "Farm3", "main2", "sub2"),
					new Farm(farm4.getId(), "Farm4", "main3", "sub2"),
					new Farm(farm5.getId(), "Farm5", "main2", "")
    			));
    	
    	query = select.list(f)
    			
    			.where(f::getName).startsWith("Farm")
    			
    			.orderBy(f::getFarmId).and(f::getSubFarmId).and(f::getName)
    			.build(); 

    	// Store out of order
    	store(farm2, farm4, farm3, farm5, farm1)
    	.checkListOrdered(
    			query,
    			
    			() -> expected(
    					new Farm(farm2.getId(), "Farm2", "main1", null),
    					new Farm(farm5.getId(), "Farm5", "main2", ""),
    					new Farm(farm3.getId(), "Farm3", "main2", "sub2"),
    					new Farm(farm1.getId(), "Farm1", "main2", "sub3"),
    					new Farm(farm4.getId(), "Farm4", "main3", "sub2")
    			));
    	
    	// Entities cannot be ordered by numeric index, since difficult to make sense of (order of attributes in class? very brittle)
    	verifyIsNotCompilable(
    			Farm.class, "f",
    			"list(f)" +
    			".where(f::getName).startsWith(\"Farm\")" +
    			".orderBy(1, 3, 2)");
    }
}
