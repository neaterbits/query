package com.neaterbits.query.dsl.gen_test;

import org.junit.Test;

import com.neaterbits.query.jpatest.GEN_BaseTestCase;
import com.neaterbits.query.test.model.Farm;
import com.neaterbits.query.test.model.land.LandPlot;


public class EntityJoinWhereGroupByTest extends GEN_BaseTestCase {


    @Test
    public void testEntitySingleNamed() {
    	
    	verifyIsCompilable(
    		  "one(Farm.class)"
    		+ ".innerJoin(Farm::getLandPlots)"
    		+ ".where(Farm::getName).startsWith(\"Farm\")");
    	
    	verifyIsNotCompilable(
      		   "one(Farm.class)"
    		+ ".innerJoin(Farm::getLandPlots)"
      		+ ".where(Farm::getName).startsWith(\"Farm\")"
      	    + ".groupBy(Farm::getName)");
    }


    @Test
    public void testEntitySingleAlias() {
    	verifyIsCompilable(
	    		b -> b.add(Farm.class, "f").add(LandPlot.class, "l"),
	      		  "one(f)"
  	    		+ ".innerJoin(f::getLandPlots, l)"
	      		+ ".where(f::getName).startsWith(\"Farm\")");
      	
      	verifyIsNotCompilable(
	    		b -> b.add(Farm.class, "f").add(LandPlot.class, "l"),
        		   "one(f)"
	    		+ ".innerJoin(f::getLandPlots, l)"
        		+ ".where(f::getName).startsWith(\"Farm\")"
        	    + ".groupBy(f::getName)");
    }


    @Test
    public void testEntityMultiNamed() {
    	verifyIsCompilable(
      		  "list(Farm.class)"
    		+ ".innerJoin(Farm::getLandPlots)"
      		+ ".where(Farm::getName).startsWith(\"Farm\")");
      	
      	verifyIsNotCompilable(
    		   "list(Farm.class)"
    		+ ".innerJoin(Farm::getLandPlots)"
    		+ ".where(Farm::getName).startsWith(\"Farm\")"
    	    + ".groupBy(Farm::getName)");
    }


    @Test
    public void testEntityMultiAlias() {
    	verifyIsCompilable(
	    		b -> b.add(Farm.class, "f").add(LandPlot.class, "l"),
	      		  "list(f)"
  	    		+ ".innerJoin(f::getLandPlots, l)"
	      		+ ".where(f::getName).startsWith(\"Farm\")");
      	
      	verifyIsNotCompilable(
	    		b -> b.add(Farm.class, "f").add(LandPlot.class, "l"),
        		   "list(f)"
  	    		+ ".innerJoin(f::getLandPlots, l)"
        		+ ".where(f::getName).startsWith(\"Farm\")"
        	    + ".groupBy(f::getName)");
    }
}
