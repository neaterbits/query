package com.neaterbits.query.dsl.gen_test;

import org.junit.Test;

import com.neaterbits.query.jpatest.GEN_BaseTestCase;
import com.neaterbits.query.sql.dsl.api.MultiBuilt;
import com.neaterbits.query.sql.dsl.api.SingleBuilt;
import com.neaterbits.query.test.model.Farm;


public class EntityWhereOrFunctionTest extends GEN_BaseTestCase {


    @Test
    public void testEntitySingleNamed() {
    	final Farm farm1 = new Farm("Hill Valley");
    	final Farm farm2 = new Farm("Table Mountain");
    	final Farm farm3 = new Farm("Snowy Hills");
    	
    	SingleBuilt<Farm> query = select.one(Farm.class)
    			
    			.where(Farm::getName).contains("Hill V")
    			.   or().lower(Farm::getName).contains("l valley")
    			.build(); 

    	store(farm1, farm2, farm3)
    	.checkOne(query, () -> new Farm(farm1.getId(), "Hill Valley"));
    	
    	// In case drops initial clause
		query = select.one(Farm.class)
    			
    			.where(Farm::getName).contains("Hill V")
    			.   or().lower(Farm::getName).contains("nonexistent")
    			.build(); 

    	store(farm1, farm2, farm3)
    	.checkOne(query, () -> new Farm(farm1.getId(), "Hill Valley"));

    	// Other way around
		query = select.one(Farm.class)
    			
    			.where(Farm::getName).contains("nonexistent")
    			.   or().lower(Farm::getName).contains("l valley")
    			.build(); 

    	store(farm1, farm2, farm3)
    	.checkOne(query, () -> new Farm(farm1.getId(), "Hill Valley"));
    	
    	// Multiple, more of a compile test
    	query = select.one(Farm.class)
    			.where(Farm::getName).contains("Hill V")
    			.   or().lower(Farm::getName).contains("l valley")
    			.   or().upper(Farm::getName).contains("L VALLEY")
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
    	
    	SingleBuilt<Farm> query = select.one(f)
    			.where(f::getName).contains("Hills")
    			.   or().lower(f::getName).contains("Snowy")
    			.build(); 

    	
    	store(farm1, farm2, farm3)
    	.checkOne(query, () -> new Farm(farm3.getId(), "Snowy Hills"));
    	
    	
    	// In case drops initial clause
    	query = select.one(f)
    			.where(f::getName).contains("Hills")
    			.   or().lower(f::getName).contains("nonexistent")
    			.build(); 

    	
    	store(farm1, farm2, farm3)
    	.checkOne(query, () -> new Farm(farm3.getId(), "Snowy Hills"));

    	// Other way around
		query = select.one(f)
    			.where(f::getName).contains("nonexistent")
    			.   or().lower(f::getName).contains("l valley")
    			.build(); 

    	store(farm1, farm2, farm3)
    	.checkOne(query, () -> new Farm(farm1.getId(), "Hill Valley"));

    	// Multiple, more of a compile test
    	query = select.one(f)
    			.where(f::getName).contains("Hill V")
    			.   or().lower(f::getName).contains("l valley")
    			.   or().upper(f::getName).contains("L VALLEY")
    			.build(); 

    	store(farm1, farm2, farm3)
    	.checkOne(query, () -> new Farm(farm1.getId(), "Hill Valley"));
    }


    @Test
    public void testEntityMultiNamed() {
    	final Farm farm1 = new Farm("Hill Valley");
    	final Farm farm2 = new Farm("Table Mountain");
    	final Farm farm3 = new Farm("Snowy Hills");
    	
    	MultiBuilt<Farm> query = select.list(Farm.class)
    			.where(Farm::getName).contains("Valley")
    			.   or().lower(Farm::getName).contains("snowy")
    			.build(); 

    	store(farm1, farm2, farm3)
    	.checkListUnordered(
    			query,
    			
    			() -> expected( 
					new Farm(farm1.getId(), "Hill Valley"),
					new Farm(farm3.getId(), "Snowy Hills")));
    	
    	// same test without lower() to assure difference
    	query = select.list(Farm.class)
    			.where(Farm::getName).contains("Valley")
    			.   or(Farm::getName).contains("snowy")
    			.build(); 

    	store(farm1, farm2, farm3)
    	.checkListUnordered(
    			query,
    			
    			() -> expected( 
					new Farm(farm1.getId(), "Hill Valley")));
    	
    	query = select.list(Farm.class)
    			.where(Farm::getName).contains("Valley")
    			.   or().lower(Farm::getName).contains("snowy")
    			.   or().upper(Farm::getName).contains("SNOWY")
    			.build(); 

    	// Multiple or's, more of a compile test
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
    	
    	MultiBuilt<Farm> query = select.list(f)
    			.where(f::getName).contains("Valley")
    			.   or().lower(f::getName).contains("snowy")
    			.build(); 

    	store(farm1, farm2, farm3)
    	.checkListUnordered(
    			query,
    			
    			() -> expected( 
					new Farm(farm1.getId(), "Hill Valley"),
					new Farm(farm3.getId(), "Snowy Hills")));
    	
    	// same test without lower() to assure difference
    	query = select.list(f)
    			.where(f::getName).contains("Valley")
    			.   or(f::getName).contains("snowy")
    			.build(); 

    	store(farm1, farm2, farm3)
    	.checkListUnordered(
    			query,
    			
    			() -> expected( 
					new Farm(farm1.getId(), "Hill Valley")));    	

    	query = select.list(f)
    			.where(f::getName).contains("Valley")
    			.   or().lower(f::getName).contains("snowy")
    			.   or().upper(f::getName).contains("SNOWY")
    			.build(); 

    	// Multiple or's, more of a compile test
    	store(farm1, farm2, farm3)
    	.checkListUnordered(
    			query,
    			
    			() -> expected( 
					new Farm(farm1.getId(), "Hill Valley"),
					new Farm(farm3.getId(), "Snowy Hills")));
    }
}
