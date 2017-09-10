package com.neaterbits.query.dsl.gen_test;

import org.junit.Test;

import com.neaterbits.query.jpatest.GEN_BaseTestCase;
import com.neaterbits.query.sql.dsl.api.MultiBuilt;
import com.neaterbits.query.test.model.Farm;
import com.neaterbits.query.test.model.mapped.FarmInfo;
import com.neaterbits.query.test.model.mapped.FarmLand;


public class MappedWhereOrGroupByTest extends GEN_BaseTestCase {

    @Test
    public void testMappedSingleNamed() {
		verifyIsCompilable(
				b -> b
					.addImport(FarmInfo.class)
					.addImport(Farm.class),
				"one(FarmInfo.class)" +
				".map(Farm::getFarmId).to(FarmInfo::setFarmId)" +
				".where(Farm::getName).startsWith(\"Farm\")" +
				".or(Farm::getName).startsWith(\"Other\")");

		verifyIsCompilable(
				b -> b
					.addImport(FarmInfo.class)
					.addImport(Farm.class),
				"list(FarmInfo.class)" +
				".map(Farm::getFarmId).to(FarmInfo::setFarmId)" +
				".where(Farm::getName).startsWith(\"Farm\")" +
				".or(Farm::getName).startsWith(\"Other\")" +
				".groupBy(Farm::getFarmId)"
						);
		
		verifyIsNotCompilable(
				b -> b
					.addImport(FarmInfo.class)
					.addImport(Farm.class),
				"one(FarmInfo.class)" + 
				".map(Farm::getFarmId).to(FarmInfo::setFarmId)" +
				".where(Farm::getName).startsWith(\"Farm\")" +
				".or(Farm::getName).startsWith(\"Other\")" +
				".groupBy(Farm::getFarmId)");		
    }

    @Test
    public void testMappedSingleAlias() {
		verifyIsCompilable(
				b -> b.addImport(FarmInfo.class)
					  .add(Farm.class, "f"),
				"one(FarmInfo.class)" +
				".map(f::getFarmId).to(FarmInfo::setFarmId)" +
				".where(f::getName).startsWith(\"Farm\")" +
				".or(f::getName).startsWith(\"Other\")"
				);

		verifyIsCompilable(
				b -> b.addImport(FarmInfo.class)
				  .add(Farm.class, "f"),
				"list(FarmInfo.class)" +
				".map(f::getFarmId).to(FarmInfo::setFarmId)" +
				".where(f::getName).startsWith(\"Farm\")" +						
				".or(f::getName).startsWith(\"Other\")" +
				".groupBy(f::getFarmId)");

		verifyIsNotCompilable(
				b -> b.addImport(FarmInfo.class)
				  .add(Farm.class, "f"),
				"one(FarmInfo.class)" + 
				".map(f::getFarmId).to(FarmInfo::setFarmId)" +
				".where(f::getName).startsWith(\"Farm\")" +						
				".or(f::getName).startsWith(\"Other\")" +
				".groupBy(f::getFarmId)");		
    }

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
    			.build(); 

    	// Store out of order
    	store(farm2, farm4, farm3, farm5, farm1)
    	.checkListUnordered(
    			query,
    			
    			() -> expected( 
					new FarmInfo(null, "main1", null),
					new FarmInfo(null, "main2", null),
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

    	final Farm f = select.alias(Farm.class);
    	
    	MultiBuilt<FarmInfo> query = select.list(FarmInfo.class)
    			.map    ( f::getFarmId   ).to(FarmInfo::setFarmId)
    			.where  ( f::getSubFarmId).isEqualTo("sub2")
    			.or     ( f::getName)	 .isEqualTo("Farm2")
    			.groupBy( f::getFarmId )
    			.build(); 

    	// Store out of order
    	store(farm2, farm4, farm3, farm5, farm1)
    	.checkListUnordered(
    			query,
    			
    			() -> expected( 
					new FarmInfo(null, "main1", null),
					new FarmInfo(null, "main2", null),
					new FarmInfo(null, "main3", null)
    			));
    }
}
