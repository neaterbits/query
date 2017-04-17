package com.neaterbits.query.dsl.gen_test;

import org.junit.Test;

import com.neaterbits.query.jpatest.GEN_BaseTestCase;
import com.neaterbits.query.test.model.Farm;


public class EntityWhereGroupByTest extends GEN_BaseTestCase {


    @Test
    public void testEntitySingleNamed() {
    	
    	verifyIsCompilable(
    		  "one(Farm.class)"
    		+ ".where(Farm::getName).startsWith(\"Farm\")");
    	
    	verifyIsNotCompilable(
      		   "one(Farm.class)"
      		+ ".where(Farm::getName).startsWith(\"Farm\")"
      	    + ".groupBy(Farm::getName)");
    }


    @Test
    public void testEntitySingleAlias() {
    	verifyIsCompilable(
	    		Farm.class, "f",
	      		  "one(f)"
	      		+ ".where(f::getName).startsWith(\"Farm\")");
      	
      	verifyIsNotCompilable(
	    		Farm.class, "f",
        		   "one(f)"
        		+ ".where(f::getName).startsWith(\"Farm\")"
        	    + ".groupBy(f::getName)");
    }


    @Test
    public void testEntityMultiNamed() {
    	verifyIsCompilable(
      		  "list(Farm.class)"
      		+ ".where(Farm::getName).startsWith(\"Farm\")");
      	
      	verifyIsNotCompilable(
    		   "list(Farm.class)"
    		+ ".where(Farm::getName).startsWith(\"Farm\")"
    	    + ".groupBy(Farm::getName)");
    }


    @Test
    public void testEntityMultiAlias() {
    	verifyIsCompilable(
	    		Farm.class, "f",
	      		  "list(f)"
	      		+ ".where(f::getName).startsWith(\"Farm\")");
      	
      	verifyIsNotCompilable(
	    		Farm.class, "f",
        		   "list(f)"
        		+ ".where(f::getName).startsWith(\"Farm\")"
        	    + ".groupBy(f::getName)");
    }
}
