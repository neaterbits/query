package com.neaterbits.query.dsl.gen_test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.neaterbits.query.jpatest.GEN_BaseTestCase;
import com.neaterbits.query.sql.dsl.api.MultiBuilt;
import com.neaterbits.query.sql.dsl.api.SingleBuilt;
import com.neaterbits.query.test.model.Farm;
import com.neaterbits.query.test.model.mapped.FarmInfo;


public class MappedWhereFunctionTest extends GEN_BaseTestCase {


    @Test
    public void testMappedSingleNamed() {
    	final Farm farm1 = new Farm("Hill Valley");
    	final Farm farm2 = new Farm("Table Mountain");
    	final Farm farm3 = new Farm("Snowy Hills");
    	
    	final SingleBuilt<FarmInfo> query = select.one(FarmInfo.class)
    			
    			.map(Farm::getName).to(FarmInfo::setName)
    			
    			.where().lower(Farm::getName).startsWith("table")
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
    			
    			.where().lower(f::getName).startsWith("table")
    			.build(); 

    	store(farm1, farm2, farm3)
    	.checkOne(query, new FarmInfo("Table Mountain"));
    }


    @Test
    public void testMappedMultiNamed() {
    	final Farm farm1 = new Farm("Hill Valley");
    	final Farm farm2 = new Farm("Table Mountain");
    	final Farm farm3 = new Farm("Snowy Hills");
    	
    	final MultiBuilt<FarmInfo> query = select.list(FarmInfo.class)
    			
    			.map(Farm::getName).to(FarmInfo::setName)
    			
    			.where().lower(Farm::getName).contains("hill")
    			.build(); 

    	store(farm1, farm2, farm3)
    	.checkListUnordered(
    			query,
    			new FarmInfo("Hill Valley"),
    			new FarmInfo("Snowy Hills"));
    }


    @Test
    public void testMappedMultiAlias() {
    	final Farm farm1 = new Farm("Hill Valley");
    	final Farm farm2 = new Farm("Table Mountain");
    	final Farm farm3 = new Farm("Snowy Hills");

    	final Farm f = select.alias(Farm.class);
    	
    	final MultiBuilt<FarmInfo> query = select.list(FarmInfo.class)
    			
    			.map(f::getName).to(FarmInfo::setName)
    			
    			.where().lower(f::getName).contains("hill")
    			.build(); 

    	store(farm1, farm2, farm3)
    	.checkListUnordered(
    			query,
    			new FarmInfo("Hill Valley"),
    			new FarmInfo("Snowy Hills"));
    }
}
