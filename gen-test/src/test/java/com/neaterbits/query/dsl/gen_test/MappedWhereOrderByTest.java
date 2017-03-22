package com.neaterbits.query.dsl.gen_test;

import org.junit.Test;
import com.neaterbits.query.jpatest.GEN_BaseTestCase;
import com.neaterbits.query.sql.dsl.api.MultiBuilt;
import com.neaterbits.query.test.model.Farm;
import com.neaterbits.query.test.model.mapped.FarmInfo;


public class MappedWhereOrderByTest extends GEN_BaseTestCase {


    @Test
    public void testMappedSingleNamed() {
    	// Should test that is not compilable
		verifyIsCompilable(
				"one(FarmInfo.class)" +
				".map(Farm::getName).to(FarmInfo::setName)" +
    			".where(Farm::getName).startsWith(\"Farm\")");
		
		verifyIsNotCompilable(
				"one(Farm.class)" + 
				".map(Farm::getName).to(FarmInfo::setName)" +
    			".where(Farm::getName).startsWith(\"Farm\")" +
				".orderBy(Farm::getName)");		
    }


    @Test
    public void testMappedSingleAlias() {
		verifyIsCompilable(
		    	"f", Farm.class,
				"one(FarmInfo.class)" +
				".map(f::getName).to(FarmInfo::setName)" +
				".where(f::getName).startsWith(\"Farm\")");

		verifyIsNotCompilable(
				"f", Farm.class,
				"one(Farm.class)" + 
				".map(f::getName).to(FarmInfo::setName)" +
    			".where(f::getName).startsWith(\"Farm\")" +
				".orderBy(f::getName)");		
    }


    @Test
    public void testMappedMultiNamed() {
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
    	
    	final MultiBuilt<FarmInfo> query = select.list(FarmInfo.class)
    			.map(Farm::getName).to(FarmInfo::setName)
    			.where(Farm::getName).startsWith("Farm")
    			.orderBy(Farm::getName)
    			.build(); 

    	// Store out of order
    	store(other5, farm2, farm4, other2, other4, farm3, other1, farm5, farm1, other3)
    	.checkListUnordered(
    			query,
    			
    			() -> expected( 
					new FarmInfo("Farm1"),
					new FarmInfo("Farm2"),
					new FarmInfo("Farm3"),
					new FarmInfo("Farm4"),
					new FarmInfo("Farm5")
    			));
    }


    @Test
    public void testMappedMultiAlias() {
    	final Farm farm1 = new Farm("Farm1", "main2", null);
    	final Farm farm2 = new Farm("Farm2", "main1", null);
    	final Farm farm3 = new Farm("Farm3", "main2", "sub2");
    	final Farm farm4 = new Farm("Farm4", "main3", "sub2");
    	final Farm farm5 = new Farm("Farm5", "main3", "sub1");

    	final Farm other1 = new Farm("Other1", "other3", "sub1");
    	final Farm other2 = new Farm("Other2", "other2", "sub2" );
    	final Farm other3 = new Farm("Other3", "other3", "sub2");
    	final Farm other4 = new Farm("Other4", "other1", null);
    	final Farm other5 = new Farm("Other5", "other2", "sub1");
    	
    	final Farm f = select.alias(Farm.class);
    	
    	MultiBuilt<FarmInfo> query = select.list(FarmInfo.class)
    			.map(f::getName).to(FarmInfo::setName)
    			.where(f::getName).startsWith("Farm")
    			.orderBy(f::getName)
    			.build(); 

    	// Store out of order
    	store(other5, farm2, farm4, other2, other4, farm3, other1, farm5, farm1, other3)
    	.checkListUnordered(
    			query,
    			
    			() -> expected( 
					new FarmInfo("Farm1"),
					new FarmInfo("Farm2"),
					new FarmInfo("Farm3"),
					new FarmInfo("Farm4"),
					new FarmInfo("Farm5")
    			));
    	
    	
    	query = select.list(FarmInfo.class)
    			.map(f::getName).to(FarmInfo::setName)
    			.map(f::getFarmId).to(FarmInfo::setFarmId)
    			.map(f::getSubFarmId).to(FarmInfo::setSubFarmId)
    			.where(f::getName).startsWith("Farm")
    			.orderBy(f::getFarmId).and(f::getSubFarmId).and(f::getName)
    			.build(); 
    	
    	
    	// Store out of order
    	store(other5, farm2, farm4, other2, other4, farm3, other1, farm5, farm1, other3)
    	.checkListUnordered(
    			query,
    			
    			() -> expected(
					new FarmInfo("Farm2", "main1", null),
					new FarmInfo("Farm1", "main2", null),
					new FarmInfo("Farm3", "main2", "sub2"),
					new FarmInfo("Farm5", "main3", "sub1"),
					new FarmInfo("Farm4", "main3", "sub2")
/*
					new FarmInfo("Farm2", "main1"),
					new FarmInfo("Farm1", "main2"),
					new FarmInfo("Farm3", "main2"),
					new FarmInfo("Farm5", "main3"),
					new FarmInfo("Farm3", "main3")
					*/
    			));
    	
    	query = select.list(FarmInfo.class)
    			.map(f::getFarmId).to(FarmInfo::setFarmId)
    			.map(f::getName).to(FarmInfo::setName)
    			.map(f::getSubFarmId).to(FarmInfo::setSubFarmId)
    			.where(f::getName).startsWith("Farm")
    			.orderBy(1, 3, 2)
    			.build();    	

    	// Store out of order
    	store(other5, farm2, farm4, other2, other4, farm3, other1, farm5, farm1, other3)
    	.checkListUnordered(
    			query,
    			
    			() -> expected( 
					new FarmInfo("Farm2", "main1", null),
					new FarmInfo("Farm1", "main2", null),
					new FarmInfo("Farm3", "main2", "sub2"),
					new FarmInfo("Farm5", "main3", "sub1"),
					new FarmInfo("Farm4", "main3", "sub2")
    			));
    }
}
