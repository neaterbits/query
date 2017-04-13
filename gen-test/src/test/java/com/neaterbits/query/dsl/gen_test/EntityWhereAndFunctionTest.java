package com.neaterbits.query.dsl.gen_test;

import org.junit.Test;
import com.neaterbits.query.jpatest.GEN_BaseTestCase;
import com.neaterbits.query.sql.dsl.api.MultiBuilt;
import com.neaterbits.query.sql.dsl.api.SingleBuilt;
import com.neaterbits.query.test.model.Farm;


public class EntityWhereAndFunctionTest extends GEN_BaseTestCase {

    @Test
    public void testEntitySingleNamed() {
    	final Farm farm1 = new Farm("Hill Valley");
    	final Farm farm2 = new Farm("Table Mountain");
    	final Farm farm3 = new Farm("Snowy Hills");
    	
    	SingleBuilt<Farm> query = select.one(Farm.class)
    			
    			.where(Farm::getName).contains("Hill V")
    			.   and().lower(Farm::getName).contains("l valley")
    			.build(); 

    	store(farm1, farm2, farm3)
    	.checkOne(query, () -> new Farm(farm1.getId(), "Hill Valley"));
    	
    	// In case drops initial clause
		query = select.oneOrNull(Farm.class)
    			
    			.where(Farm::getName).contains("Hill V")
    			.   and().lower(Farm::getName).contains("nonexistent")
    			.build(); 

    	store(farm1, farm2, farm3)
    	.checkOne(query, () -> null);
    	
    	// Other way around
		query = select.oneOrNull(Farm.class)
    			
    			.where(Farm::getName).contains("nonexistent")
    			.   and().lower(Farm::getName).contains("l valley")
    			.build(); 

    	store(farm1, farm2, farm3)
    	.checkOne(query, () -> null);
    	
    	// Multiple functions
		query = select.one(Farm.class)
		.where(Farm::getName).contains("Hill V")
		.   and().lower(Farm::getName).contains("l valley")
		.   and().upper(Farm::getName).contains("L VALLEY")
		.build(); 
    }


    @Test
    public void testEntitySingleAlias() {
    	final Farm farm1 = new Farm("Hill Valley");
    	final Farm farm2 = new Farm("Table Mountain");
    	final Farm farm3 = new Farm("Snowy Hills");
    	
    	final Farm f = select.alias(Farm.class);
    	
    	SingleBuilt<Farm> query = select.one(f)
    			
    			.where(f::getName).contains("Hill V")
    			.   and().lower(f::getName).contains("l valley")
    			.build(); 

    	store(farm1, farm2, farm3)
    	.checkOne(query, () -> new Farm(farm1.getId(), "Hill Valley"));
    	
    	// In case drops initial clause
		query = select.oneOrNull(f)
    			
    			.where(f::getName).contains("Hill V")
    			.   and().lower(f::getName).contains("nonexistent")
    			.build(); 

    	store(farm1, farm2, farm3)
    	.checkOne(query, () -> null);
    	
    	// Other way around
		query = select.oneOrNull(f)
    			
    			.where(f::getName).contains("nonexistent")
    			.   and().lower(f::getName).contains("l valley")
    			.build(); 

    	store(farm1, farm2, farm3)
    	.checkOne(query, () -> null);

    	// Multiple functions
		query = select.one(f)
		.where(f::getName).contains("Hill V")
		.   and().lower(f::getName).contains("l valley")
		.   and().upper(f::getName).contains("L VALLEY")
		.build(); 
    }


    @Test
    public void testEntityMultiNamed() {

    	final Farm farm1 = new Farm("Hill Valley");
    	final Farm farm2 = new Farm("Table Mountain");
    	final Farm farm3 = new Farm("Snowy Hills");
    	
    	MultiBuilt<Farm> query = select.list(Farm.class)
    			.where(Farm::getName).contains("y")
    			.   and().lower(Farm::getName).contains("hill")
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
    			.   and(Farm::getName).contains("hill")
    			.build(); 

    	store(farm1, farm2, farm3)
    	.checkListUnordered(
    			query,
    			
    			() -> none());    	

    	// Multiple functions
    	query = select.list(Farm.class)
    			.where(Farm::getName).contains("y")
    			.   and().lower(Farm::getName).contains("hill")
    			.   and().upper(Farm::getName).contains("HILL")
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
    	
    	MultiBuilt<Farm> query = select.list(f)
    			.where(f::getName).contains("y")
    			.   and().lower(f::getName).contains("hill")
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
    			.   and(f::getName).contains("hill")
    			.build(); 

    	store(farm1, farm2, farm3)
    	.checkListUnordered(
    			query,
    			
    			() -> none());    	

    	// Multiple functions
    	query = select.list(f)
    			.where(f::getName).contains("y")
    			.   and().lower(f::getName).contains("hill")
    			.   and().upper(f::getName).contains("HILL")
    			.build(); 

    	store(farm1, farm2, farm3)
    	.checkListUnordered(
    			query,
    			
    			() -> expected( 
					new Farm(farm1.getId(), "Hill Valley"),
					new Farm(farm3.getId(), "Snowy Hills")));
    }
}
