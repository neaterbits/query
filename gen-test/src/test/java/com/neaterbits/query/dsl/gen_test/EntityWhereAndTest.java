package com.neaterbits.query.dsl.gen_test;

import org.junit.Test;

import com.neaterbits.query.jpatest.GEN_BaseTestCase;
import com.neaterbits.query.sql.dsl.api.MultiBuilt;
import com.neaterbits.query.sql.dsl.api.SingleBuilt;
import com.neaterbits.query.test.model.Farm;


public class EntityWhereAndTest extends GEN_BaseTestCase {


    @Test
    public void testEntitySingleNamed() {
    	final Farm farm1 = new Farm("Hill Valley");
    	final Farm farm2 = new Farm("Table Mountain");
    	final Farm farm3 = new Farm("Snowy Hills");
    	
    	SingleBuilt<Farm> query = select.one(Farm.class)
    			
    			.where(Farm::getName).contains("Hill")
    			.  and(Farm::getName).contains("Valley")
    			.build(); 

    	store(farm1, farm2, farm3)
    	.checkOne(query, () -> new Farm(farm1.getId(), "Hill Valley"));
    
    	// Check opposite order as well in case drops initial where
    	query = select.one(Farm.class)
    			
    			.where(Farm::getName).contains("Valley")
    			.  and(Farm::getName).contains("Hill")
    			.build(); 

    	store(farm1, farm2, farm3)
    	.checkOne(query, () -> new Farm(farm1.getId(), "Hill Valley"));    	
    }


    @Test
    public void testEntitySingleAlias() {
    	
    	verifyIsNotCompilable(Farm.class, "f", 
    			"one(Farm.class)" +
    			".where(f::getName).contains(\"Hill\")" +
    			".  and(f::getName).contains(\"Snowy\")");
    	
    	
    	verifyIsCompilable(Farm.class, "f", 
    			"one(f)" +
    			".where(f::getName).contains(\"Hill\")" +
    			".  and(f::getName).contains(\"Snowy\")");
    	
    	final Farm farm1 = new Farm("Hill Valley");
    	final Farm farm2 = new Farm("Table Mountain");
    	final Farm farm3 = new Farm("Snowy Hills");

    	final Farm f = select.alias(Farm.class);
    	
    	// Check opposite order as well in case drops initial where
    	SingleBuilt<Farm> query = select.one(f)
    			.where(f::getName).contains("Hill")
    			.  and(f::getName).contains("Snowy")
    			.build(); 

    	store(farm1, farm2, farm3)
    	.checkOne(query, () -> new Farm(farm3.getId(), "Snowy Hills"));

    	query = select.one(f)
    			.where(f::getName).contains("Snowy")
    			.  and(f::getName).contains("Hill")
    			.build(); 

    	store(farm1, farm2, farm3)
    	.checkOne(query, () -> new Farm(farm3.getId(), "Snowy Hills"));
    }


    @Test
    public void testEntityMultiNamed() {
	    	
    	final Farm farm1 = new Farm("Hill Valley");
    	final Farm farm2 = new Farm("Table Mountain");
    	final Farm farm3 = new Farm("Snowy Hills");
    	
    	MultiBuilt<Farm> query = select.list(Farm.class)
    			.where(Farm::getName).contains("l")
    			.  and(Farm::getName).contains("Hi")
    			.build(); 

    	store(farm1, farm2, farm3)
    	.checkListUnordered(
    			query,
    			
    			() -> expected( 
					new Farm(farm1.getId(), "Hill Valley"),
					new Farm(farm3.getId(), "Snowy Hills")));
    	
    	
    	// Check whether drops initial-clause as well
    	query = select.list(Farm.class)
    			.where(Farm::getName).contains("Mountain")
    			.  and(Farm::getName).contains("l")
    			.build(); 

    	store(farm1, farm2, farm3)
    	.checkListUnordered(
    			query,
    			
    			() -> expected( 
					new Farm(farm2.getId(), "Table Mountain")));
    }
    


    @Test
    public void testEntityMultiAlias() {
    	final Farm farm1 = new Farm("Hill Valley");
    	final Farm farm2 = new Farm("Table Mountain");
    	final Farm farm3 = new Farm("Snowy Hills");
    	
    	final Farm f = select.alias(Farm.class);

    	verifyIsNotCompilable(Farm.class, "f",
    	    	"list(Farm.class)" +
    			".where(f::getName).contains(\"Mountain\")" +
    			".  and(f::getName).contains(\"l\")");
        	
    	verifyIsCompilable(Farm.class, "f",
	    	"list(f)" +
			".where(f::getName).contains(\"Mountain\")" +
			".  and(f::getName).contains(\"l\")");
    	
    	MultiBuilt<Farm> query = select.list(f)
    			.where(f::getName).contains("l")
    			.  and(f::getName).contains("Hi")
    			.build(); 

    	store(farm1, farm2, farm3)
    	.checkListUnordered(
    			query,
    			() -> expected(
	    			new Farm(farm1.getId(), "Hill Valley"),
	    			new Farm(farm3.getId(), "Snowy Hills")));

    	// Check whether drops initial-clause as well
    	query = select.list(f)
    			.where(f::getName).contains("Mountain")
    			.  and(f::getName).contains("l")
    			.build(); 

    	store(farm1, farm2, farm3)
    	.checkListUnordered(
    			query,
    			() -> expected(
	    			new Farm(farm2.getId(), "Table Mountain")));    	
    }
}
