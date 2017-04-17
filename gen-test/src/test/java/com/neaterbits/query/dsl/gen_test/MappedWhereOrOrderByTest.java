package com.neaterbits.query.dsl.gen_test;

import org.junit.Test;

import com.neaterbits.query.jpatest.GEN_BaseTestCase;
import com.neaterbits.query.sql.dsl.api.MultiBuilt;
import com.neaterbits.query.test.model.Farm;
import com.neaterbits.query.test.model.mapped.FarmInfo;


public class MappedWhereOrOrderByTest extends GEN_BaseTestCase {


    @Test
    public void testMappedSingleNamed() {
		verifyIsCompilable(
				"one(FarmInfo.class)" +
				".map(Farm::getName).to(FarmInfo::setName)" +
    			".where(Farm::getName).startsWith(\"Farm\")" +
				".or(Farm::getSubFarmId).endsWith(\"2\")"
				);
		
		verifyIsCompilable(
				"list(FarmInfo.class)" +
				".map(Farm::getName).to(FarmInfo::setName)" +
    			".where(Farm::getName).startsWith(\"Farm\")" +
				".and(Farm::getSubFarmId).endsWith(\"2\")" +
				".orderBy(Farm::getName)");
		
		verifyIsNotCompilable(
				"one(Farm.class)" + 
				".map(Farm::getName).to(FarmInfo::setName)" +
    			".where(Farm::getName).startsWith(\"Farm\")" +
				".or(Farm::getSubFarmId).endsWith(\"2\")" +
				".orderBy(Farm::getName)");		
    }

    @Test
    public void testMappedSingleAlias() {
		verifyIsCompilable(
		    	Farm.class, "f",
				"one(FarmInfo.class)" +
				".map(f::getName).to(FarmInfo::setName)" +
				".where(f::getName).startsWith(\"Farm\")" +
				".or(f::getSubFarmId).endsWith(\"2\")"
				);

		verifyIsCompilable(
		    	Farm.class, "f",
				"list(FarmInfo.class)" +
				".map(f::getName).to(FarmInfo::setName)" +
				".where(f::getName).startsWith(\"Farm\")" +
				".or(f::getSubFarmId).endsWith(\"2\")" +
				".orderBy(f::getName)");

		verifyIsNotCompilable(
				Farm.class, "f",
				"one(Farm.class)" + 
				".map(f::getName).to(FarmInfo::setName)" +
    			".where(f::getName).startsWith(\"Farm\")" +
				".or(f::getSubFarmId).endsWith(\"2\")" +
				".orderBy(f::getName)");		
    }

    @Test
    public void testMappedMultiNamed() {
    	final Farm farm1 = new Farm("Farm1", "main2", "sub3");
    	final Farm farm2 = new Farm("Farm2", "main1", null);
    	final Farm farm3 = new Farm("Farm3", "main2", "sub2");
    	final Farm farm4 = new Farm("Farm4", "main3", "sub2");
    	final Farm farm5 = new Farm("Farm5", "main2", "");

    	final Farm other1 = new Farm("Other1", "other3", "sub1");
    	final Farm other2 = new Farm("Other2", "other2", "sub2" );
    	final Farm other3 = new Farm("Other3", "other3", "sub2");
    	final Farm other4 = new Farm("Other4", "other1", null);
    	final Farm other5 = new Farm("Other5", "other2", "sub1");
    	
    	MultiBuilt<FarmInfo> query = select.list(FarmInfo.class)
    			.map(Farm::getName).to(FarmInfo::setName)
    			.where(Farm::getName).startsWith("Farm")
    			  .or(Farm::getFarmId).endsWith("2")
    			.orderBy(Farm::getName)
    			.build(); 

    	// Store out of order
    	store(other5, farm2, farm4, other2, other4, farm3, other1, farm5, farm1, other3)
    	.checkListOrdered(
    			query,
    			
    			() -> expected( 
					new FarmInfo("Farm1"),
					new FarmInfo("Farm2"),
					new FarmInfo("Farm3"),
					new FarmInfo("Farm4"),
					new FarmInfo("Farm5"),

					new FarmInfo("Other2"),
					new FarmInfo("Other5")
    			));

    	
    	query = select.list(FarmInfo.class)
    			.map(Farm::getName)		.to(FarmInfo::setName)
    			.map(Farm::getFarmId)	.to(FarmInfo::setFarmId)
    			.map(Farm::getSubFarmId).to(FarmInfo::setSubFarmId)
    			
    			.where(Farm::getFarmId).isEqualTo("main2")
    			   .or(Farm::getName).endsWith("3")
    			
    			.orderBy(Farm::getFarmId).and(Farm::getSubFarmId).and(Farm::getName)
    			.build(); 

    	// Store out of order
    	store(other5, farm2, farm4, other2, other4, farm3, other1, farm5, farm1, other3)
    	.checkListOrdered(
    			query,
    			
    			() -> expected(
					// TODO: null sorts last here, after sub2?? seems like it. check entity and named other tests as well
					new FarmInfo("Farm5", "main2", ""),
					new FarmInfo("Farm3", "main2", "sub2"),
					new FarmInfo("Farm1", "main2", "sub3"),
					new FarmInfo("Other3", "other3", "sub2")
    			));
    	
    	query = select.list(FarmInfo.class)
    			.map(Farm::getFarmId)	.to(FarmInfo::setFarmId)
    			.map(Farm::getName)		.to(FarmInfo::setName)
    			.map(Farm::getSubFarmId).to(FarmInfo::setSubFarmId)
    			.where(Farm::getFarmId).isEqualTo("main2")
    				.or(Farm::getName).endsWith("3")
    			.orderBy(1, 3, 2)
    			.build();    	

    	// Store out of order
    	store(other5, farm2, farm4, other2, other4, farm3, other1, farm5, farm1, other3)
    	.checkListOrdered(
    			query,
    			
    			() -> expected( 
					new FarmInfo("Farm5", "main2", ""),
					new FarmInfo("Farm3", "main2", "sub2"),
					new FarmInfo("Farm1", "main2", "sub3"),
					new FarmInfo("Other3", "other3", "sub2") // TODO null sort order?
    			));
    }


    @Test
    public void testMappedMultiAlias() {
    	final Farm farm1 = new Farm("Farm1", "main2", "sub3");
    	final Farm farm2 = new Farm("Farm2", "main1", null);
    	final Farm farm3 = new Farm("Farm3", "main2", "sub2");
    	final Farm farm4 = new Farm("Farm4", "main3", "sub2");
    	final Farm farm5 = new Farm("Farm5", "main2", "");

    	final Farm other1 = new Farm("Other1", "other3", "sub1");
    	final Farm other2 = new Farm("Other2", "other2", "sub2" );
    	final Farm other3 = new Farm("Other3", "other3", "sub2");
    	final Farm other4 = new Farm("Other4", "other1", null);
    	final Farm other5 = new Farm("Other5", "other2", "sub1");

    	final Farm f = select.alias(Farm.class);
    	
    	MultiBuilt<FarmInfo> query = select.list(FarmInfo.class)
    			.map(f::getName).to(FarmInfo::setName)
    			.where(f::getName).startsWith("Farm")
    			  .or(f::getFarmId).endsWith("2")
    			.orderBy(f::getName)
    			.build(); 

    	// Store out of order
    	store(other5, farm2, farm4, other2, other4, farm3, other1, farm5, farm1, other3)
    	.checkListOrdered(
    			query,
    			
    			() -> expected( 
					new FarmInfo("Farm1"),
					new FarmInfo("Farm2"),
					new FarmInfo("Farm3"),
					new FarmInfo("Farm4"),
					new FarmInfo("Farm5"),

					new FarmInfo("Other2"),
					new FarmInfo("Other5")
    			));

    	
    	query = select.list(FarmInfo.class)
    			.map(f::getName)		.to(FarmInfo::setName)
    			.map(f::getFarmId)	.to(FarmInfo::setFarmId)
    			.map(f::getSubFarmId).to(FarmInfo::setSubFarmId)
    			
    			.where(f::getFarmId).isEqualTo("main2")
    			   .or(f::getName).endsWith("3")
    			
    			.orderBy(f::getFarmId).and(f::getSubFarmId).and(f::getName)
    			.build(); 

    	// Store out of order
    	store(other5, farm2, farm4, other2, other4, farm3, other1, farm5, farm1, other3)
    	.checkListOrdered(
    			query,
    			
    			() -> expected(
					// TODO: null sorts last here, after sub2?? seems like it. check entity and named other tests as well
					new FarmInfo("Farm5", "main2", ""),
					new FarmInfo("Farm3", "main2", "sub2"),
					new FarmInfo("Farm1", "main2", "sub3"),
					new FarmInfo("Other3", "other3", "sub2")
    			));
    	
    	query = select.list(FarmInfo.class)
    			.map(f::getFarmId)	 .to(FarmInfo::setFarmId)
    			.map(f::getName)	 .to(FarmInfo::setName)
    			.map(f::getSubFarmId).to(FarmInfo::setSubFarmId)
    			.where(f::getFarmId).isEqualTo("main2")
    				.or(f::getName).endsWith("3")
    			.orderBy(1, 3, 2)
    			.build();    	

    	// Store out of order
    	store(other5, farm2, farm4, other2, other4, farm3, other1, farm5, farm1, other3)
    	.checkListOrdered(
    			query,
    			
    			() -> expected( 
					new FarmInfo("Farm5", "main2", ""),
					new FarmInfo("Farm3", "main2", "sub2"),
					new FarmInfo("Farm1", "main2", "sub3"),
					new FarmInfo("Other3", "other3", "sub2") // TODO null sort order?
    			));
    }
}
