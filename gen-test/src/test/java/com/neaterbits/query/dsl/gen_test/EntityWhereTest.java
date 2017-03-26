package com.neaterbits.query.dsl.gen_test;

import org.junit.Test;
import com.neaterbits.query.jpatest.GEN_BaseTestCase;
import com.neaterbits.query.sql.dsl.api.MultiBuilt;
import com.neaterbits.query.sql.dsl.api.SingleBuilt;
import com.neaterbits.query.test.model.Farm;


public class EntityWhereTest extends GEN_BaseTestCase {

    @Test
    public void testEntitySingleNamed() {
    	final Farm farm1 = new Farm("Hill Valley");
    	final Farm farm2 = new Farm("Table Mountain");
    	final Farm farm3 = new Farm("Snowy Hills");
    	
    	final SingleBuilt<Farm> query = select.one(Farm.class)
    			
    			.where(Farm::getName).startsWith("Table")
    			.build(); 

    	store(farm1, farm2, farm3)
    	.checkOne(query, () -> new Farm(farm2.getId(), "Table Mountain"));
    }

    @Test
    public void testEntitySingleAlias() {
    	
    	verifyIsNotCompilable(Farm.class, "f",
	    	"one(Farm.class)" +
			".where(f::getName).startsWith(\"Table\")");

    	verifyIsCompilable(Farm.class, "f",
    	    	"one(f)" +
    			".where(f::getName).startsWith(\"Table\")");
        	
    	
    	final Farm farm1 = new Farm("Hill Valley");
    	final Farm farm2 = new Farm("Table Mountain");
    	final Farm farm3 = new Farm("Snowy Hills");

    	final Farm f = select.alias(Farm.class);
    	
    	final SingleBuilt<Farm> query = select.one(f)
    			.where(f::getName).startsWith("Table")
    			.build(); 

    	store(farm1, farm2, farm3)
    	.checkOne(query, () -> new Farm(farm2.getId(), "Table Mountain"));
    }
    

    @Test
    public void testEntityMultiNamed() {
    	
    	final Farm farm1 = new Farm("Hill Valley");
    	final Farm farm2 = new Farm("Table Mountain");
    	final Farm farm3 = new Farm("Snowy Hills");
    	
    	final MultiBuilt<Farm> query = select.list(Farm.class)
    			.where(Farm::getName).contains("Hill")
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
			".where(f::getName).contains(\"Hill\")");
		
    	verifyIsCompilable(Farm.class, "f",
    	    	"list(f)" +
    			".where(f::getName).contains(\"Hill\")");

    	final Farm farm1 = new Farm("Hill Valley");
    	final Farm farm2 = new Farm("Table Mountain");
    	final Farm farm3 = new Farm("Snowy Hills");
    	
    	final Farm f = select.alias(Farm.class);
    	
    	final MultiBuilt<Farm> query = select.list(f)
    			.where(f::getName).contains("Hill")
    			.build(); 

    	store(farm1, farm2, farm3)
    	.checkListUnordered(
    			query,
    			() -> expected(
	    			new Farm(farm1.getId(), "Hill Valley"),
	    			new Farm(farm3.getId(), "Snowy Hills")));
    }
}
