package com.neaterbits.query.dsl.gen_test;

import org.junit.Test;

import com.neaterbits.query.jpatest.GEN_BaseTestCase;
import com.neaterbits.query.sql.dsl.api.MultiBuilt;
import com.neaterbits.query.test.model.Farm;
import com.neaterbits.query.test.model.mapped.FarmInfo;


public class MappedWhereAndGroupByHavingTest extends GEN_BaseTestCase {


	// Single tests already done in group-by
    @Test
    public void testMappedMultiNamed() {
		final Farm farm1 = new Farm("Farm1", "main2", "sub3");
    	final Farm farm2 = new Farm("Farm2", "main1", null);
    	final Farm farm3 = new Farm("Farm3", "main2", "sub2");
    	final Farm farm4 = new Farm("Farm4", "main3", "sub2");
    	final Farm farm5 = new Farm("Farm5", "main2", "");
    	final Farm farm6 = new Farm("Other1", "other1", "sub2");

    	MultiBuilt<FarmInfo> query = select.list(FarmInfo.class)
    			.map    ( Farm::getFarmId   ).to(FarmInfo::setFarmId)
    			.where  ( Farm::getSubFarmId).isEqualTo("sub2")
    			.and    ( Farm::getName)	 .startsWith("Farm")
    			.groupBy( Farm::getFarmId )
    			.having ( Farm::getFarmId ).endsWith("3")
    			.build(); 

    	// Store out of order
    	store(farm2, farm4, farm6, farm3, farm5, farm1)
    	.checkListUnordered(
    			query,
    			
    			() -> expected( 
					new FarmInfo(null, "main3", null)
    			));
    }


    @Test
    public void testMappedMultiAlias() {
		final Farm farm1 = new Farm("Farm1", "main2", "sub3");
    	final Farm farm2 = new Farm("Farm2", "main1", null);
    	final Farm farm3 = new Farm("Farm3", "main2", "sub2");
    	final Farm farm4 = new Farm("Farm4", "main3", "sub2");
    	final Farm farm5 = new Farm("Farm5", "main2", "");
    	final Farm farm6 = new Farm("Other1", "other1", "sub2");

    	final Farm f = select.alias(Farm.class);
    	
    	MultiBuilt<FarmInfo> query = select.list(FarmInfo.class)
    			.map    ( f::getFarmId   ).to(FarmInfo::setFarmId)
    			.where  ( f::getSubFarmId).isEqualTo("sub2")
    			.and    ( f::getName)	  .startsWith("Farm")
    			.groupBy( f::getFarmId )
    			.having ( f::getFarmId ).endsWith("3")
    			.build(); 

    	// Store out of order
    	store(farm2, farm4, farm6, farm3, farm5, farm1)
    	.checkListUnordered(
    			query,
    			
    			() -> expected( 
					new FarmInfo(null, "main3", null)
    			));
    }
}
