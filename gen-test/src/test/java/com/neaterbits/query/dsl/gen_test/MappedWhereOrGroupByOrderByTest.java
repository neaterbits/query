package com.neaterbits.query.dsl.gen_test;

import org.junit.Test;

import com.neaterbits.query.jpatest.GEN_BaseTestCase;
import com.neaterbits.query.sql.dsl.api.MultiBuilt;
import com.neaterbits.query.test.model.Farm;
import com.neaterbits.query.test.model.mapped.FarmInfo;


public class MappedWhereOrGroupByOrderByTest extends GEN_BaseTestCase {


	// Single-tests for compilation for base group-by already

    @Test
    public void testMappedMultiNamed() {
	   	final Farm farm1 = new Farm("Farm1", "main2", "sub3");
	   	final Farm farm2 = new Farm("Farm2", "main1", null);
	   	final Farm farm3 = new Farm("Farm3", "main2", "sub2");
	   	final Farm farm4 = new Farm("Farm4", "main3", "sub2");
	   	final Farm farm5 = new Farm("Farm5", "main2", "");
	
	   	final Farm other1 = new Farm("Other1", "other4", "sub1");
	   	final Farm other2 = new Farm("Other2", "other2", "sub2" );
	   	final Farm other3 = new Farm("Other3", "other3", "sub2");
	   	final Farm other4 = new Farm("Other4", "other1", null);
	   	final Farm other5 = new Farm("Other5", "other2", "sub1");
	   	
	   	MultiBuilt<FarmInfo> query = select.list(FarmInfo.class)
	   			.map(Farm::getFarmId).to(FarmInfo::setFarmId)
	   			.map(Farm::getSubFarmId).to(FarmInfo::setSubFarmId)
	   			.where(Farm::getSubFarmId).isEqualTo("sub2")
	   			.or(Farm::getName).isEqualTo("Other1")
	   			.groupBy(Farm::getFarmId).and(Farm::getSubFarmId)
	   			.orderBy(Farm::getFarmId).desc().and(Farm::getSubFarmId)
	   			.build(); 
	   	
	   	
	   	store(other5, farm2, farm4, other2, other4, farm3, other1, farm5, farm1, other3)
	   	.checkListOrdered(
	   			query,
	   			
	   			() -> expected( 
						new FarmInfo(null, "other4", "sub1"),
						new FarmInfo(null, "other3", "sub2"),
						new FarmInfo(null, "other2", "sub2"),
						new FarmInfo(null, "main3",  "sub2"),
						new FarmInfo(null, "main2",  "sub2")
	   			));
   }


    @Test
   	public void testMappedMultiAlias() {
    	final Farm farm1 = new Farm("Farm1", "main2", "sub3");
	   	final Farm farm2 = new Farm("Farm2", "main1", null);
	   	final Farm farm3 = new Farm("Farm3", "main2", "sub2");
	   	final Farm farm4 = new Farm("Farm4", "main3", "sub2");
	   	final Farm farm5 = new Farm("Farm5", "main2", "");
	
	   	final Farm other1 = new Farm("Other1", "other4", "sub1");
	   	final Farm other2 = new Farm("Other2", "other2", "sub2" );
	   	final Farm other3 = new Farm("Other3", "other3", "sub2");
	   	final Farm other4 = new Farm("Other4", "other1", null);
	   	final Farm other5 = new Farm("Other5", "other2", "sub1");
	
	   	final Farm f = select.alias(Farm.class);
	   	
	   	MultiBuilt<FarmInfo> query = select.list(FarmInfo.class)
	   			.map(f::getFarmId).to(FarmInfo::setFarmId)
	   			.map(f::getSubFarmId).to(FarmInfo::setSubFarmId)
	   			
	   			.where(f::getSubFarmId).isEqualTo("sub2")
	   			.or(f::getName).isEqualTo("Other1")
	   			
	   			.groupBy(f::getFarmId).and(f::getSubFarmId)
	   			.orderBy(f::getFarmId).desc().and(f::getSubFarmId)
	   			.build(); 
	   	
	   	
	   	store(other5, farm2, farm4, other2, other4, farm3, other1, farm5, farm1, other3)
	   	.checkListOrdered(
	   			query,
	   			
	   			() -> expected( 
						new FarmInfo(null, "other4", "sub1"),
						new FarmInfo(null, "other3", "sub2"),
						new FarmInfo(null, "other2", "sub2"),
						new FarmInfo(null, "main3",  "sub2"),
						new FarmInfo(null, "main2",  "sub2")
	   			));
   }
}
