package com.neaterbits.query.dsl.gen_test;

import com.neaterbits.query.jpatest.GEN_BaseTestCase;
import com.neaterbits.query.test.model.Farm;

abstract class GEN_Farm_BaseTestCase extends GEN_BaseTestCase {
    protected static Farm makeFarm(String name, String timeFounded) {

    	
    	final Farm farm = new Farm(name);

		farm.setTimeFounded(date(timeFounded));

    	return farm;
    }
}
