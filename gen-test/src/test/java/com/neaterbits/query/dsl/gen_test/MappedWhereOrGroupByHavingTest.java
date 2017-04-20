package com.neaterbits.query.dsl.gen_test;

import org.junit.Test;

import com.neaterbits.query.jpatest.GEN_BaseTestCase;
import com.neaterbits.query.sql.dsl.api.MultiBuilt;
import com.neaterbits.query.test.model.Farm;
import com.neaterbits.query.test.model.mapped.FarmInfo;


public class MappedWhereOrGroupByHavingTest extends GEN_BaseTestCase {

	// Single-tests done for .groupBy() already
	

    @Test
    public void testMappedMultiNamed() {
		final Farm farm1 = new Farm("Farm1", "main2", "sub3");
    	final Farm farm2 = new Farm("Farm2", "main1", null);
    	final Farm farm3 = new Farm("Farm3", "main2", "sub2");
    	final Farm farm4 = new Farm("Farm4", "main3", "sub2");
    	final Farm farm5 = new Farm("Farm5", "main2", "");

    	MultiBuilt<FarmInfo> query = select.list(FarmInfo.class)
    			.map    ( Farm::getFarmId   ).to(FarmInfo::setFarmId)
    			.where  ( Farm::getSubFarmId).isEqualTo("sub2")
    			.or     ( Farm::getName)	 .isEqualTo("Farm2")
    			.groupBy( Farm::getFarmId )
    			.having ( Farm::getFarmId).endsWith("2")
    			.build(); 

    	// Store out of order
    	store(farm2, farm4, farm3, farm5, farm1)
    	.checkListUnordered(
    			query,
    			
    			() -> expected( 
					new FarmInfo(null, "main2", null)
    			));
    }


    @Test
    public void testMappedMultiAlias() {
		final Farm farm1 = new Farm("Farm1", "main2", "sub3");
    	final Farm farm2 = new Farm("Farm2", "main1", null);
    	final Farm farm3 = new Farm("Farm3", "main2", "sub2");
    	final Farm farm4 = new Farm("Farm4", "main3", "sub2");
    	final Farm farm5 = new Farm("Farm5", "main2", "");

    	final Farm f = select.alias(Farm.class);
    	
    	MultiBuilt<FarmInfo> query = select.list(FarmInfo.class)
    			.map    ( f::getFarmId   ).to(FarmInfo::setFarmId)
    			.where  ( f::getSubFarmId).isEqualTo("sub2")
    			.or     ( f::getName)	 .isEqualTo("Farm2")
    			.groupBy( f::getFarmId )
    			.having( f::getFarmId ).endsWith("2")
    			.build(); 

    	// Store out of order
    	store(farm2, farm4, farm3, farm5, farm1)
    	.checkListUnordered(
    			query,
    			
    			() -> expected( 
					new FarmInfo(null, "main2", null)
    			));
    }
}
