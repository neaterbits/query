package com.neaterbits.query.dsl.gen_test;

import org.junit.Test;
import com.neaterbits.query.jpatest.GEN_BaseTestCase;
import com.neaterbits.query.sql.dsl.api.MultiBuilt;
import com.neaterbits.query.test.model.Farm;
import com.neaterbits.query.test.model.mapped.FarmInfo;

import static org.assertj.core.api.Assertions.assertThat;

public class MappedWhereGroupByHavingTest extends GEN_BaseTestCase {


	// Single non-compilability already tested in where-groupby 
	
    @Test
    public void testMappedMultiNamed() {
    	
    	assertThat(true).isEqualTo(false); // TODO: add test in a way that separates where and having logic
    	
    	final Farm farm1 = new Farm("Farm1", "main2", "sub3");
    	final Farm farm2 = new Farm("Farm2", "main1", null);
    	final Farm farm3 = new Farm("Farm3", "main2", "sub2");
    	final Farm farm4 = new Farm("Farm4", "main3", "sub2");
    	final Farm farm5 = new Farm("Farm5", "main2", "");

    	final Farm other1 = new Farm("Other1", "other3", "sub1");
    	final Farm other2 = new Farm("Other2", "other2", "sub2" );
    	final Farm other3 = new Farm("Other3", "other3", "sub2");
    	final Farm other4 = new Farm("Other4", "other1", null);
    	final Farm other5 = new Farm("Other5", "other2", "sub2"); // SAME ID AS Other2

    	MultiBuilt<FarmInfo> query = select.list(FarmInfo.class)
    			.map(Farm::getFarmId)   .to(FarmInfo::setFarmId)
    			.map(Farm::getSubFarmId).to(FarmInfo::setSubFarmId)
    			.groupBy(Farm::getFarmId).and(Farm::getSubFarmId)
    			.having (Farm::getFarmId).endsWith("2")
    			.build(); 
    	
    	
    	store(other5, farm2, farm4, other2, other4, farm3, other1, farm5, farm1, other3)
    	.checkListUnordered(
    			query,
    			
    			() -> expected( 
					
					new FarmInfo(null, "main2",  ""),
					new FarmInfo(null, "main2",  "sub2"),
					new FarmInfo(null, "main2",  "sub3"),

					new FarmInfo(null, "other2", "sub2")
					// new FarmInfo(null, "other2", "sub1"), Same grouping as Other2 to test grouping
    			));

    	// Test grouping by only one critera as well
    	query = select.list(FarmInfo.class)
    			.map(Farm::getFarmId).to(FarmInfo::setFarmId)
    			.groupBy(Farm::getFarmId)
    			.having(Farm::getFarmId).endsWith("2")
    			.build();     	

    	store(other5, farm2, farm4, other2, other4, farm3, other1, farm5, farm1, other3)
    	.checkListUnordered(
    			query,
    			() -> expected( 
				
					new FarmInfo(null, "main2", null),
					new FarmInfo(null, "other2", null)
		));
    }


    @Test
    public void testMappedMultiAlias() {

    	assertThat(true).isEqualTo(false); // TODO: add test in a way that separates where and having logic
    	
    	final Farm farm1 = new Farm("Farm1", "main2", "sub3");
    	final Farm farm2 = new Farm("Farm2", "main1", null);
    	final Farm farm3 = new Farm("Farm3", "main2", "sub2");
    	final Farm farm4 = new Farm("Farm4", "main3", "sub2");
    	final Farm farm5 = new Farm("Farm5", "main2", "");

    	final Farm other1 = new Farm("Other1", "other3", "sub1");
    	final Farm other2 = new Farm("Other2", "other2", "sub2" );
    	final Farm other3 = new Farm("Other3", "other3", "sub2");
    	final Farm other4 = new Farm("Other4", "other1", null);
    	final Farm other5 = new Farm("Other5", "other2", "sub2"); // SAME ID AS Other2

    	final Farm f = select.alias(Farm.class);
    	
    	MultiBuilt<FarmInfo> query = select.list(FarmInfo.class)
    			.map(f::getFarmId).to(FarmInfo::setFarmId)
    			.map(f::getSubFarmId).to(FarmInfo::setSubFarmId)
    			.groupBy(f::getFarmId).and(f::getSubFarmId)
    			.having (f::getFarmId).endsWith("2")
    			.build(); 
    	
    	
    	store(other5, farm2, farm4, other2, other4, farm3, other1, farm5, farm1, other3)
    	.checkListUnordered(
    			query,
    			
    			() -> expected( 
					
					new FarmInfo(null, "main2",  ""),
					new FarmInfo(null, "main2",  "sub2"),
					new FarmInfo(null, "main2",  "sub3"),
					new FarmInfo(null, "other2", "sub2")
					// new FarmInfo(null, "other2", "sub1"), Same grouping as Other2 to test grouping
    			));

    	// Test grouping by only one critera as well
    	query = select.list(FarmInfo.class)
    			.map(f::getFarmId).to(FarmInfo::setFarmId)
    			.groupBy(f::getFarmId)
    			.having(f::getFarmId).endsWith("2")
    			.build();     	

    	store(other5, farm2, farm4, other2, other4, farm3, other1, farm5, farm1, other3)
    	.checkListUnordered(
    			query,
    			() -> expected( 
				
					new FarmInfo(null, "main2", null),
					new FarmInfo(null, "other2", null)
		));
    }
}
