package com.neaterbits.query.dsl.gen_test;

import org.junit.Test;

import com.neaterbits.query.jpatest.GEN_BaseTestCase;
import com.neaterbits.query.sql.dsl.api.MultiBuilt;
import com.neaterbits.query.test.model.Farm;


public class EntityWhereOrderByTest extends GEN_BaseTestCase {

    @Test
    public void testEntitySingleNamed() {
    	// Should test that is not compilable
		verifyIsCompilable(
				"one(Farm.class)" + 
    			".where(Farm::getName).startsWith(\"Farm\")");
		
		verifyIsNotCompilable(
				"one(Farm.class)" + 
    			".where(Farm::getName).startsWith(\"Farm\")" +
				".orderBy(Farm::getName)");		
    }


    @Test
    public void testEntitySingleAlias() {
		verifyIsCompilable(
				Farm.class, "f",
				
				"one(Farm.class)" + 
    			".where(f::getName).startsWith(\"Farm\")");
		
		verifyIsNotCompilable(
				Farm.class, "f",
				"one(Farm.class)" + 
    			".where(f::getName).startsWith(\"Farm\")" +
				".orderBy(Farm::getName)");		
    }


    @Test
    public void testEntityMultiNamed() {
    	final Farm farm1 = new Farm("Farm1", "main2", "sub3");
    	final Farm farm2 = new Farm("Farm2", "main1", null);
    	final Farm farm3 = new Farm("Farm3", "main2", "sub2");
    	final Farm farm4 = new Farm("Farm4", "main3", "sub2");
    	final Farm farm5 = new Farm("Farm5", "main2", "");

    	final Farm other1 = new Farm("Other1", "other3", "sub1");
    	final Farm other2 = new Farm("Other2", "other2", "sub2" );
    	final Farm other3 = new Farm("Other3", "other3", "sub2");
    	final Farm other4 = new Farm("Other4", "other1", null);
    	final Farm other5 = new Farm("Other5", "other2", "sub1");
    	
    	MultiBuilt<Farm> query = select.list(Farm.class)
    			.where(Farm::getName).startsWith("Farm")
    			.orderBy(Farm::getName)
    			.build(); 

    	// Store out of order
    	store(other5, farm2, farm4, other2, other4, farm3, other1, farm5, farm1, other3)
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
    	store(other5, farm2, farm4, other2, other4, farm3, other1, farm5, farm1, other3)
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

    	final Farm other1 = new Farm("Other1", "other3", "sub1");
    	final Farm other2 = new Farm("Other2", "other2", "sub2" );
    	final Farm other3 = new Farm("Other3", "other3", "sub2");
    	final Farm other4 = new Farm("Other4", "other1", null);
    	final Farm other5 = new Farm("Other5", "other2", "sub1");
    	
    	final Farm f = select.alias(Farm.class);
    	
    	MultiBuilt<Farm> query = select.list(f)
    			.where(f::getName).startsWith("Farm")
    			.orderBy(f::getName)
    			.build(); 

    	// Store out of order
    	store(other5, farm2, farm4, other2, other4, farm3, other1, farm5, farm1, other3)
    	.checkListOrdered(
    			query,
    			
    			() -> expected(
    					/*
					new Farm(farm1.getId(), "Farm1"),
					new Farm(farm2.getId(), "Farm2"),
					new Farm(farm3.getId(), "Farm3"),
					new Farm(farm4.getId(), "Farm4"),
					new Farm(farm5.getId(), "Farm5")
					*/
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
    	store(other5, farm2, farm4, other2, other4, farm3, other1, farm5, farm1, other3)
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
    	verifyIsNotCompilable(Farm.class, "f",
    			"list(Farm.class)" +
    			".where(f::getName).startsWith(\"Farm\")" +
    			".orderBy(1, 3, 2)"    			
    			);
    }
}
