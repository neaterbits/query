package com.neaterbits.query.dsl.gen_test;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.junit.Test;
import com.neaterbits.query.jpatest.GEN_BaseTestCase;
import com.neaterbits.query.sql.dsl.api.SingleBuilt;
import com.neaterbits.query.test.model.Farm;
import com.neaterbits.query.test.model.land.CropLand;
import com.neaterbits.query.test.model.land.Forest;
import com.neaterbits.query.test.model.land.LandPlot;
import com.neaterbits.query.test.model.land.Uncultivated;


public class AggregateJoinTest extends GEN_BaseTestCase {


    @Test
    public void testAggregateSingleNamed() {
    	
    	final Farm farm1 = new Farm("Farm1");
    	final Farm farm2 = new Farm("Farm2");
    	
    	final LandPlot land1 = new CropLand(new BigDecimal("9.30"));
    	final LandPlot land2 = new Forest(new BigDecimal("40.5"));
    	final LandPlot land3 = new Uncultivated(new BigDecimal("100.5"));
    	final LandPlot land4 = new Uncultivated(new BigDecimal("345.43"));

    	farm1.setLandPlots(Arrays.asList(land1, land2));
    	
    	land1.setFarm(farm1);
    	land2.setFarm(farm1);
    	
    	farm2.setLandPlots(Arrays.asList(land3));
    	land3.setFarm(farm2);
    	
    	// only landplots that belong to farms, so land4 should not be included in sum
    	// since doing innerjoin from farm to landplot
    	final SingleBuilt<BigDecimal> query
    		= select.sum(LandPlot::getHectares)
    			.innerJoin(Farm::getLandPlots)   
    			.build();
    	
    	store(farm1, farm2, land4)
    	/*.dump(Farm.class)
    	.dump(LandPlot.class)
    	*/
    	.dump("select * from farm")
    	.dump("select * from land_plot")
    	
    	// remove to avoid delete constraints when deleting Farm (not cascade)
    	.remove(land1, land2, land3)
    	.checkAggregate(query, new BigDecimal("150.30"));
    	
    }
    
    @Test
    public void testAggregateSingleNamed_Left() {
    	
    	// Aggregate left-join
    	final Farm farm1 = makeFarm("Farm1", "1928-09-02");
    	final Farm farm2 = makeFarm("Farm2", "1974-02-29");
    	final Farm farm3 = makeFarm("Farm3", "1839-03-22");

    	final LandPlot land1 = new CropLand(new BigDecimal("9.30"));
    	final LandPlot land2 = new Forest(new BigDecimal("40.5"));
    	
    	farm1.setLandPlots(Arrays.asList(land1));
    	land1.setFarm(farm1);
    	
    	farm2.setLandPlots(Arrays.asList(land2));
    	land2.setFarm(farm2);
    	

    	final SingleBuilt<Date> query
	    	= select.max(Farm::getTimeFounded)
				.innerJoin(Farm::getLandPlots)   
				.build();
	
		store(farm1, farm2)
		/*.dump(Farm.class)
		.dump(LandPlot.class)
		*/
		
		// remove to avoid delete constraints when deleting Farm (not cascade)
		.remove(land1, land2)
		.checkAggregate(query, new Date());
		
    }
    
    private static Farm makeFarm(String name, String timeFounded) {

    	final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    	final Farm farm = new Farm(name);

    	try {
    		farm.setTimeFounded(dateFormat.parse(timeFounded));
    	}
    	catch (ParseException ex) {
    		throw new RuntimeException("Failed to parse \"" + timeFounded + "\"", ex);
    	}

    	return farm;
    }


    @Test
    public void testAggregateSingleAlias() {
    	final Farm farm1 = new Farm("Farm1");
    	final Farm farm2 = new Farm("Farm2");
    	
    	final LandPlot land1 = new CropLand(new BigDecimal("9.30"));
    	final LandPlot land2 = new Forest(new BigDecimal("40.5"));
    	final LandPlot land3 = new Uncultivated(new BigDecimal("100.5"));
    	final LandPlot land4 = new Uncultivated(new BigDecimal("345.43"));

    	farm1.setLandPlots(Arrays.asList(land1, land2));
    	
    	land1.setFarm(farm1);
    	land2.setFarm(farm1);
    	
    	farm2.setLandPlots(Arrays.asList(land3));
    	land3.setFarm(farm2);
    	
    	final LandPlot l = select.alias(LandPlot.class);
    	final Farm     f = select.alias(Farm.class);
    	
    	// only landplots that belong to farms, so land4 should not be included in sum
    	// since doing innerjoin from farm to landplot
    	final SingleBuilt<BigDecimal> query
    		= select.sum(l::getHectares)
    			.innerJoin(f::getLandPlots, l)
    			.build();
    	
    	store(farm1, farm2, land4)
    	
    	// remove to avoid delete constraints when deleting Farm (not cascade)
    	.remove(land1, land2, land3)
    	.checkAggregate(query, new BigDecimal("150.30"));
    	
        assertThat(true).isEqualTo(false);
    }
}
