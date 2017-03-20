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
    	
    	final SingleBuilt<Farm> query = select.one(Farm.class)
    			
    			.where(Farm::getName).contains("Hill")
    			.  and(Farm::getName).contains("Valley")
    			.build(); 

    	store(farm1, farm2, farm3)
    	.checkOne(query, () -> new Farm(farm1.getId(), "Hill Valley"));
    }


    @Test
    public void testEntitySingleAlias() {
    	final Farm farm1 = new Farm("Hill Valley");
    	final Farm farm2 = new Farm("Table Mountain");
    	final Farm farm3 = new Farm("Snowy Hills");

    	final Farm f = select.alias(Farm.class);
    	
    	final SingleBuilt<Farm> query = select.one(Farm.class)
    			.where(f::getName).contains("Hill")
    			.  and(f::getName).contains("Snowy")
    			.build(); 

    	store(farm1, farm2, farm3)
    	.checkOne(query, () -> new Farm(farm3.getId(), "Snowy Hills"));
    }


    @Test
    public void testEntityMultiNamed() {
    	final Farm farm1 = new Farm("Hill Valley");
    	final Farm farm2 = new Farm("Table Mountain");
    	final Farm farm3 = new Farm("Snowy Hills");
    	
    	final MultiBuilt<Farm> query = select.list(Farm.class)
    			.where(Farm::getName).contains("l")
    			.  and(Farm::getName).contains("Hi")
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
    	final Farm farm1 = new Farm("Hill Valley");
    	final Farm farm2 = new Farm("Table Mountain");
    	final Farm farm3 = new Farm("Snowy Hills");
    	
    	final Farm f = select.alias(Farm.class);
    	
    	final MultiBuilt<Farm> query = select.list(Farm.class)
    			.where(f::getName).contains("l")
    			.  and(f::getName).contains("Hi")
    			.build(); 

    	store(farm1, farm2, farm3)
    	.checkListUnordered(
    			query,
    			() -> expected(
	    			new Farm(farm1.getId(), "Hill Valley"),
	    			new Farm(farm3.getId(), "Snowy Hills")));
    }
}
