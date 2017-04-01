package com.neaterbits.query.dsl.gen_test;

import org.junit.Test;
import com.neaterbits.query.jpatest.GEN_BaseTestCase;
import com.neaterbits.query.sql.dsl.api.MultiBuilt;
import com.neaterbits.query.test.model.Farm;


public class EntityOrderByDescTest extends GEN_BaseTestCase {


    @Test
    public void testEntitySingleNamed() {
		verifyIsCompilable(
				"one(Farm.class)");
		
		verifyIsNotCompilable(
				"one(Farm.class)" + 
				".orderBy(Farm::getName)" +
				".desc()");		
    }


    @Test
    public void testEntitySingleAlias() {
		verifyIsCompilable(
				Farm.class, "f",
				"one(f)");
		
		verifyIsNotCompilable(
				Farm.class, "f",
				"one(f)" + 
				".orderBy(f::getName)" +
				".desc()");		
    }


    @Test
    public void testEntityMultiNamed() {
    	final Farm farm1 = new Farm("Farm1", "main2", "sub3");
    	final Farm farm2 = new Farm("Farm2", "main1", null);
    	final Farm farm3 = new Farm("Farm3", "main2", "sub2");
    	final Farm farm4 = new Farm("Farm4", "main3", "sub2");
    	final Farm farm5 = new Farm("Farm5", "main2", "");
    	final Farm farm6 = new Farm("Farm6", "main2", "sub2");

    	final Farm f = select.alias(Farm.class);
    	
    	MultiBuilt<Farm> query = select.list(f)
    			.where(f::getName).startsWith("Farm")
    			.orderBy(f::getName)
    			.desc()
    			.build(); 

    	// Store out of order
    	store(farm2, farm4, farm6, farm3, farm5, farm1)
    	.checkListOrdered(
    			query,
    			
    			() -> expected(
					new Farm(farm6.getId(), "Farm6", "main2", "sub2"),
					new Farm(farm5.getId(), "Farm5", "main2", ""),
					new Farm(farm4.getId(), "Farm4", "main3", "sub2"),
					new Farm(farm3.getId(), "Farm3", "main2", "sub2"),
					new Farm(farm2.getId(), "Farm2", "main1", null),
					new Farm(farm1.getId(), "Farm1", "main2", "sub3")
    			));
    	
    	query = select.list(f)
    			.where(f::getName).startsWith("Farm")
    			.orderBy(f::getFarmId).desc().and(f::getSubFarmId).and(f::getName).desc()
    			.build(); 

    	// Store out of order
    	store(farm2, farm4, farm6, farm3, farm5, farm1)
    	.checkListOrdered(
    			query,
    			
    			() -> expected(
    					new Farm(farm4.getId(), "Farm4", "main3", "sub2"),
    					new Farm(farm5.getId(), "Farm5", "main2", ""),
    					new Farm(farm6.getId(), "Farm6", "main2", "sub2"),
    					new Farm(farm3.getId(), "Farm3", "main2", "sub2"),
    					new Farm(farm1.getId(), "Farm1", "main2", "sub3"),
    					
    					new Farm(farm2.getId(), "Farm2", "main1", null)    
				));
    	
    	// Entities cannot be ordered by numeric index, since difficult to make sense of (order of attributes in class? very brittle)
    	verifyIsNotCompilable("list(Farm.class)" +
    			".where(Farm::getName).startsWith(\"Farm\")" +
    			".orderBy(1, 3, 2)" +
    			".desc()");
    }


    @Test
    public void testEntityMultiAlias() {
    	final Farm farm1 = new Farm("Farm1", "main2", "sub3");
    	final Farm farm2 = new Farm("Farm2", "main1", null);
    	final Farm farm3 = new Farm("Farm3", "main2", "sub2");
    	final Farm farm4 = new Farm("Farm4", "main3", "sub2");
    	final Farm farm5 = new Farm("Farm5", "main2", "");
    	final Farm farm6 = new Farm("Farm6", "main2", "sub2");

    	MultiBuilt<Farm> query = select.list(Farm.class)
    			.where(Farm::getName).startsWith("Farm")
    			.orderBy(Farm::getName)
    			.desc()
    			.build(); 

    	// Store out of order
    	store(farm2, farm4, farm6, farm3, farm5, farm1)
    	.checkListOrdered(
    			query,
    			
    			() -> expected(
					new Farm(farm6.getId(), "Farm6", "main2", "sub2"),
					new Farm(farm5.getId(), "Farm5", "main2", ""),
					new Farm(farm4.getId(), "Farm4", "main3", "sub2"),
					new Farm(farm3.getId(), "Farm3", "main2", "sub2"),
					new Farm(farm2.getId(), "Farm2", "main1", null),
					new Farm(farm1.getId(), "Farm1", "main2", "sub3")
    			));
    	
    	query = select.list(Farm.class)
    			
    			.where(Farm::getName).startsWith("Farm")
    			
    			.orderBy(Farm::getFarmId).desc().and(Farm::getSubFarmId).and(Farm::getName).desc()
    			.build(); 

    	// Store out of order
    	store(farm2, farm4, farm6, farm3, farm5, farm1)
    	.checkListOrdered(
    			query,
    			
    			() -> expected(
    					new Farm(farm4.getId(), "Farm4", "main3", "sub2"),
    					new Farm(farm5.getId(), "Farm5", "main2", ""),
    					new Farm(farm6.getId(), "Farm6", "main2", "sub2"),
    					new Farm(farm3.getId(), "Farm3", "main2", "sub2"),
    					new Farm(farm1.getId(), "Farm1", "main2", "sub3"),
    					
    					new Farm(farm2.getId(), "Farm2", "main1", null)    
				));
    	
    	// Entities cannot be ordered by numeric index, since difficult to make sense of (order of attributes in class? very brittle)
    	verifyIsNotCompilable(
    			Farm.class, "f",
    			"list(f)" +
    			".where(f::getName).startsWith(\"Farm\")" +
    			".orderBy(1, 3, 2)" +
    			".desc()");
    }
}
