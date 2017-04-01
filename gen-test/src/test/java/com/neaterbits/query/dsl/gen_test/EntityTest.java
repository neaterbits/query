package com.neaterbits.query.dsl.gen_test;


import org.junit.Test;
import com.neaterbits.query.jpatest.GEN_BaseTestCase;
import com.neaterbits.query.sql.dsl.api.MultiBuilt;
import com.neaterbits.query.sql.dsl.api.SingleBuilt;
import com.neaterbits.query.test.model.Farm;
import java.util.Arrays;


public class EntityTest extends GEN_BaseTestCase {

	

    @Test
    public void testEntitySingleNamed() {
    	final Farm farm1 = new Farm("Hill Valley");
    	
    	final SingleBuilt<Farm> query = select.one(Farm.class).build(); 

    	store(s -> s.add(farm1))
    	.checkOne(query, () -> new Farm(farm1.getId(), "Hill Valley"));
    }


    /* Not applicable, since really same as named
    @Test
    public void testEntitySingleAlias() {
        assertThat(true).isEqualTo(false);
    }
    */


    @Test
    public void testEntityMultiNamed() {
    	final Farm farm1 = new Farm("Hill Valley");
    	final Farm farm2 = new Farm("Table Mountain");
    	final Farm farm3 = new Farm("Snowy Hills");
    	
    	final MultiBuilt<Farm> query = select.list(Farm.class).build(); 

    	store(farm1, farm2, farm3)
    	.checkListUnordered(query, () -> Arrays.asList(
    				new Farm(farm1.getId(), "Hill Valley"),
    				new Farm(farm2.getId(), "Table Mountain"),
    				new Farm(farm3.getId(), "Snowy Hills"))
    	);
    }


    /* Not applicable, since really same as named
    @Test
    public void testEntityMultiAlias() {
        assertThat(true).isEqualTo(false);
    }
    */
    
}
