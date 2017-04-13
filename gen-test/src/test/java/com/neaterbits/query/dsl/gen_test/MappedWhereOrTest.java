package com.neaterbits.query.dsl.gen_test;

import org.junit.Test;
import com.neaterbits.query.jpatest.GEN_BaseTestCase;
import com.neaterbits.query.sql.dsl.api.MultiBuilt;
import com.neaterbits.query.sql.dsl.api.SingleBuilt;
import com.neaterbits.query.test.model.Farm;
import com.neaterbits.query.test.model.mapped.FarmInfo;


public class MappedWhereOrTest extends GEN_BaseTestCase {

    @Test
    public void testMappedSingleNamed() {

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

    	SingleBuilt<FarmInfo> query = select.one(FarmInfo.class)
    			.map(Farm::getName).to(FarmInfo::setName)
    			.where(Farm::getName).startsWith("Farm4")
    			   .or(Farm::getFarmId).endsWith("9")
    			.build(); 

    	// Store out of order
    	store(other5, farm2, farm4, other2, other4, farm3, other1, farm5, farm1, other3)
    	.checkOne(query, new FarmInfo("Farm4"));
    }


    @Test
    public void testMappedSingleAlias() {

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

    	SingleBuilt<FarmInfo> query = select.one(FarmInfo.class)
    			.map(f::getName).to(FarmInfo::setName)
    			.where(f::getName).startsWith("Farm4")
    			   .or(f::getFarmId).endsWith("9")
    			.build(); 

    	// Store out of order
    	store(other5, farm2, farm4, other2, other4, farm3, other1, farm5, farm1, other3)
    	.checkOne(query, new FarmInfo("Farm4"));
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
    			.where(Farm::getName).endsWith("Farm4")
    			   .or(Farm::getFarmId).endsWith("2")
    			.build(); 

    	// Store out of order
    	store(other5, farm2, farm4, other2, other4, farm3, other1, farm5, farm1, other3)
    	.checkListUnordered(
    			query,
    			
    			() -> expected( 
					new FarmInfo("Farm1"),
					new FarmInfo("Farm4"),
					new FarmInfo("Farm3"),
					new FarmInfo("Farm5"),
					new FarmInfo("Other2"),
					new FarmInfo("Other5")
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
    			.where(f::getName).endsWith("Farm4")
    			   .or(f::getFarmId).endsWith("2")
    			.build(); 

    	// Store out of order
    	store(other5, farm2, farm4, other2, other4, farm3, other1, farm5, farm1, other3)
    	.checkListUnordered(
    			query,
    			
    			() -> expected( 
					new FarmInfo("Farm1"),
					new FarmInfo("Farm4"),
					new FarmInfo("Farm3"),
					new FarmInfo("Farm5"),
					new FarmInfo("Other2"),
					new FarmInfo("Other5")
    			));
    }
}
