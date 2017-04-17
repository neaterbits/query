package com.neaterbits.query.dsl.gen_test;

import org.junit.Test;

import com.neaterbits.query.jpatest.GEN_BaseTestCase;
import com.neaterbits.query.sql.dsl.api.MultiBuilt;
import com.neaterbits.query.sql.dsl.api.SingleBuilt;
import com.neaterbits.query.test.model.Farm;


public class EntityWhereFunctionTest extends GEN_BaseTestCase {


    @Test
    public void testEntitySingleNamed() {
    	verifyIsNotCompilable(Farm.class, "f",
    	    	"one(f)" +
    			".where().lower(Farm::getName).startsWith(\"table\")");

    	verifyIsCompilable(
    	    	"one(Farm.class)" +
    			".where().lower(Farm::getName).startsWith(\"table\")");

    	final Farm farm1 = new Farm("Hill Valley");
    	final Farm farm2 = new Farm("Table Mountain");
    	final Farm farm3 = new Farm("Snowy Hills");
    	
    	final SingleBuilt<Farm> query = select.one(Farm.class)
    			
    			.where().lower(Farm::getName).startsWith("table")
    			.build(); 

    	store(farm1, farm2, farm3)
    	.checkOne(query, () -> new Farm(farm2.getId(), "Table Mountain"));
    }

    @Test
    public void testEntitySingleAlias() {
    	
    	verifyIsNotCompilable(Farm.class, "f",
	    	"one(Farm.class)" +
			".where().lower(f::getName).startsWith(\"table\")");

    	verifyIsCompilable(Farm.class, "f",
    	    	"one(f)" +
    			".where().lower(f::getName).startsWith(\"table\")");
        	
    	
    	final Farm farm1 = new Farm("Hill Valley");
    	final Farm farm2 = new Farm("Table Mountain");
    	final Farm farm3 = new Farm("Snowy Hills");

    	final Farm f = select.alias(Farm.class);
    	
    	final SingleBuilt<Farm> query = select.one(f)
    			.where().lower(f::getName).startsWith("table")
    			.build(); 

    	store(farm1, farm2, farm3)
    	.checkOne(query, () -> new Farm(farm2.getId(), "Table Mountain"));
    }
    

    @Test
    public void testEntityMultiNamed() {
    	
    	verifyIsNotCompilable(Farm.class, "f",
    	    	"list(f)" +
    			".where().lower(Farm::getName).contains(\"hill\")");
    		
    	verifyIsCompilable(
    	    	"list(Farm.class)" +
    			".where().lower(Farm::getName).contains(\"hill\")");

    	final Farm farm1 = new Farm("Hill Valley");
    	final Farm farm2 = new Farm("Table Mountain");
    	final Farm farm3 = new Farm("Snowy Hills");
    	
    	final MultiBuilt<Farm> query = select.list(Farm.class)
    			.where().lower(Farm::getName).contains("hill")
    			.build(); 

    	store(farm1, farm2, farm3)
    	.checkListUnordered(
    			query,
    			
    			() -> expected( 
					new Farm(farm1.getId(), "Hill Valley"),
					new Farm(farm3.getId(), "Snowy Hills")));
    }


    @Test
    public void testEntityMultiAlias() {
    	
    	verifyIsNotCompilable(Farm.class, "f",
	    	"list(Farm.class)" +
			".where().lower(f::getName).contains(\"hill\")");
		
    	verifyIsCompilable(Farm.class, "f",
    	    	"list(f)" +
    			".where().lower(f::getName).contains(\"hill\")");

    	final Farm farm1 = new Farm("Hill Valley");
    	final Farm farm2 = new Farm("Table Mountain");
    	final Farm farm3 = new Farm("Snowy Hills");
    	
    	final Farm f = select.alias(Farm.class);
    	
    	final MultiBuilt<Farm> query = select.list(f)
    			.where().lower(f::getName).contains("hill")
    			.build(); 

    	store(farm1, farm2, farm3)
    	.checkListUnordered(
    			query,
    			() -> expected(
	    			new Farm(farm1.getId(), "Hill Valley"),
	    			new Farm(farm3.getId(), "Snowy Hills")));
	    			
    }
}
