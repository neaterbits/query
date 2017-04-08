package com.neaterbits.query.dsl.gen_test;

import org.junit.Test;
import com.neaterbits.query.jpatest.GEN_BaseTestCase;
import com.neaterbits.query.sql.dsl.api.MultiBuilt;
import com.neaterbits.query.test.model.Farm;


public class EntityWhereAndOrderByTest extends GEN_BaseTestCase {


    @Test
    public void testEntitySingleNamed() {
    	
    	verifyIsCompilable(
    		"one(Farm.class)" +
			".where(Farm::getName).contains(\"Hill\")" +
    		".and(Farm::getName).contains(\"Valley\")");

    	verifyIsCompilable(
        		"list(Farm.class)" +
    			".where(Farm::getName).contains(\"Hill\")" +
        		".and(Farm::getName).contains(\"Valley\")" +
    			".orderBy(Farm::getName)");

    	verifyIsNotCompilable(
        		"one(Farm.class)" +
    			".where(Farm::getName).contains(\"Hill\")" +
        		".and(Farm::getName).contains(\"Valley\")" +
    			".orderBy(Farm::getNamed)");
    }


    @Test
    public void testEntitySingleAlias() {
    	verifyIsCompilable(
    			Farm.class, "f",
        		"one(f)" +
    			".where(f::getName).contains(\"Hill\")" +
        		".and(f::getName).contains(\"Valley\")");

    	verifyIsCompilable(
    			Farm.class, "f",
        		"list(f)" +
    			".where(f::getName).contains(\"Hill\")" +
        		".and(f::getName).contains(\"Valley\")" +
    			".orderBy(f::getName)");

    	verifyIsNotCompilable(
    			Farm.class, "f",
        		"one(f)" +
    			".where(f::getName).contains(\"Hill\")" +
        		".and(f::getName).contains(\"Valley\")" +
    			".orderBy(f::getNamed)");
    }


    @Test
    public void testEntityMultiNamed() {

    	final Farm farm1 = new Farm("Lower Mountain Hills");
    	final Farm farm2 = new Farm("Hillary Farms");
    	final Farm farm3 = new Farm("Hill Valley");
    	final Farm farm4 = new Farm("Table Mountain");
    	final Farm farm5 = new Farm("Snowy Hills");
    	
    	MultiBuilt<Farm> query = select.list(Farm.class)
    			.where(Farm::getName).contains("a")
    			.  and(Farm::getName).contains("Hi")
    			.orderBy(Farm::getName)
    			.build(); 

    	store(farm1, farm2, farm3, farm4, farm5)
    	.checkListUnordered(
    			query,
    			
    			() -> expected(
					new Farm(farm3.getId(), "Hill Valley"),
					new Farm(farm2.getId(), "Hillary Farms"),
    				new Farm(farm1.getId(), "Lower Mountain Hills")));

    	// Check whether drops initial-clause as well
    	query = select.list(Farm.class)
    			.where(Farm::getName).contains("Mountain")
    			.  and(Farm::getName).contains("b")
    			.  orderBy(Farm::getName)
    			.build(); 

    	store(farm1, farm2, farm3, farm4, farm5)
    	.checkListUnordered(
    			query,
    			
    			() -> expected( 
					new Farm(farm4.getId(), "Table Mountain")));
    }

    @Test
    public void testEntityMultiAlias() {
    	final Farm farm1 = new Farm("Lower Mountain Hills");
    	final Farm farm2 = new Farm("Hillary Farms");
    	final Farm farm3 = new Farm("Hill Valley");
    	final Farm farm4 = new Farm("Table Mountain");
    	final Farm farm5 = new Farm("Snowy Hills");
    	
    	final Farm f = select.alias(Farm.class);
    	
    	MultiBuilt<Farm> query = select.list(f)
    			.where(f::getName).contains("a")
    			.  and(f::getName).contains("Hi")
    			.orderBy(f::getName)
    			.build(); 

    	store(farm1, farm2, farm3, farm4, farm5)
    	.checkListUnordered(
    			query,
    			
    			() -> expected(
					new Farm(farm3.getId(), "Hill Valley"),
					new Farm(farm2.getId(), "Hillary Farms"),
    				new Farm(farm1.getId(), "Lower Mountain Hills")));

    	// Check whether drops initial-clause as well
    	query = select.list(f)
    			.where(f::getName).contains("Mountain")
    			.  and(f::getName).contains("b")
    			.  orderBy(f::getName)
    			.build(); 

    	store(farm1, farm2, farm3, farm4, farm5)
    	.checkListUnordered(
    			query,
    			
    			() -> expected( 
					new Farm(farm4.getId(), "Table Mountain")));
    }
}
