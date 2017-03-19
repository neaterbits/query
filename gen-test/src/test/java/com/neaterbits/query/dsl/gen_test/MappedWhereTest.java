package com.neaterbits.query.dsl.gen_test;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import com.neaterbits.query.jpatest.GEN_BaseTestCase;
import com.neaterbits.query.sql.dsl.api.SingleBuilt;
import com.neaterbits.query.test.model.Farm;
import com.neaterbits.query.test.model.mapped.FarmInfo;


public class MappedWhereTest extends GEN_BaseTestCase {


    @Test
    public void testMappedSingleNamed() {
    	final Farm farm1 = new Farm("Hill Valley");
    	final Farm farm2 = new Farm("Table Mountain");
    	final Farm farm3 = new Farm("Snowy Hills");
    	
    	final SingleBuilt<FarmInfo> query = select.one(FarmInfo.class)
    			
    			.map(Farm::getName).to(FarmInfo::setName)
    			
    			.where(Farm::getName).startsWith("Table")
    			.build(); 

    	store(farm1, farm2, farm3)
    	.checkOne(query, new FarmInfo("Table Mountain"));
    }


    @Test
    public void testMappedSingleAlias() {
    	final Farm farm1 = new Farm("Hill Valley");
    	final Farm farm2 = new Farm("Table Mountain");
    	final Farm farm3 = new Farm("Snowy Hills");

    	final Farm f = select.alias(Farm.class);
    	
    	final SingleBuilt<FarmInfo> query = select.one(FarmInfo.class)
    			
    			.map(f::getName).to(FarmInfo::setName)
    			
    			.where(f::getName).startsWith("Table")
    			.build(); 

    	store(farm1, farm2, farm3)
    	.checkOne(query, new FarmInfo("Table Mountain"));
    }


    @Test
    public void testMappedMultiNamed() {
        assertThat(true).isEqualTo(false);
    }


    @Test
    public void testMappedMultiAlias() {
        assertThat(true).isEqualTo(false);
    }
}
