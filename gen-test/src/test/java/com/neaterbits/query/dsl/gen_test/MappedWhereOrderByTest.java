package com.neaterbits.query.dsl.gen_test;

import static org.assertj.core.api.Assertions.assertThat;
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
    	
    	final Farm f = select.alias(Farm.class);
    	
    	final MultiBuilt<FarmInfo> query = select.list(FarmInfo.class)
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
    }
}
