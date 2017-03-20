package com.neaterbits.query.dsl.gen_test;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import com.neaterbits.query.jpatest.GEN_BaseTestCase;
import com.neaterbits.query.sql.dsl.api.MultiBuilt;
import com.neaterbits.query.sql.dsl.api.SingleBuilt;
import com.neaterbits.query.test.model.Farm;


public class EntityWhereOrTest extends GEN_BaseTestCase {


    @Test
    public void testEntitySingleNamed() {
    	final Farm farm1 = new Farm("Hill Valley");
    	final Farm farm2 = new Farm("Table Mountain");
    	final Farm farm3 = new Farm("Snowy Hills");
    	
    	SingleBuilt<Farm> query = select.one(Farm.class)
    			
    			.where(Farm::getName).contains("Hill V")
    			.   or(Farm::getName).contains("l Valley")
    			.build(); 

    	store(farm1, farm2, farm3)
    	.checkOne(query, () -> new Farm(farm1.getId(), "Hill Valley"));
    	
    	// In case drops initial clause
		query = select.one(Farm.class)
    			
    			.where(Farm::getName).contains("Hill V")
    			.   or(Farm::getName).contains("nonexistent")
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
    	
    	SingleBuilt<Farm> query = select.one(Farm.class)
    			.where(f::getName).contains("Hills")
    			.   or(f::getName).contains("Snowy")
    			.build(); 

    	
    	store(farm1, farm2, farm3)
    	.checkOne(query, () -> new Farm(farm3.getId(), "Snowy Hills"));
    	
    	
    	// In case drops initial clause
    	query = select.one(Farm.class)
    			.where(f::getName).contains("Hills")
    			.   or(f::getName).contains("nonexistent")
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
    			.where(Farm::getName).contains("Valley")
    			.   or(Farm::getName).contains("Snowy")
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
    			.where(f::getName).contains("Valley")
    			.   or(f::getName).contains("Snowy")
    			.build(); 

    	store(farm1, farm2, farm3)
    	.checkListUnordered(
    			query,
    			() -> expected(
	    			new Farm(farm1.getId(), "Hill Valley"),
	    			new Farm(farm3.getId(), "Snowy Hills")));
    }
}
