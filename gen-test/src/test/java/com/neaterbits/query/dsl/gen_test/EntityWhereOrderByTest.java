package com.neaterbits.query.dsl.gen_test;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import com.neaterbits.query.jpatest.GEN_BaseTestCase;
import com.neaterbits.query.sql.dsl.api.MultiBuilt;
import com.neaterbits.query.test.model.Farm;


public class EntityWhereOrderByTest extends GEN_BaseTestCase {


    @Test
    public void testEntitySingleNamed() {
    	// Should test that is not compilable
        assertThat(true).isEqualTo(false);
    }


    @Test
    public void testEntitySingleAlias() {
    	// Should test that is not compilable
        assertThat(true).isEqualTo(false);
    }


    @Test
    public void testEntityMultiNamed() {
    	final Farm farm1 = new Farm("Farm1");
    	final Farm farm2 = new Farm("Farm2");
    	final Farm farm3 = new Farm("Farm3");
    	final Farm farm4 = new Farm("Farm4");
    	final Farm farm5 = new Farm("Farm5");

    	final Farm other1 = new Farm("Other1");
    	final Farm other2 = new Farm("Other2");
    	final Farm other3 = new Farm("Other3");
    	final Farm other4 = new Farm("Other4");
    	final Farm other5 = new Farm("Other5");
    	
    	final MultiBuilt<Farm> query = select.list(Farm.class)
    			.where(Farm::getName).startsWith("Farm")
    			.orderBy(Farm::getName)
    			.build(); 

    	// Store out of order
    	store(other5, farm2, farm4, other2, other4, farm3, other1, farm5, farm1, other3)
    	.checkListUnordered(
    			query,
    			
    			() -> expected( 
					new Farm(farm1.getId(), "Farm1"),
					new Farm(farm2.getId(), "Farm2"),
					new Farm(farm3.getId(), "Farm3"),
					new Farm(farm4.getId(), "Farm4"),
					new Farm(farm5.getId(), "Farm5")
    			));
    }


    @Test
    public void testEntityMultiAlias() {
        assertThat(true).isEqualTo(false);
    }
}
