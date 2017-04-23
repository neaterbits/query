package com.neaterbits.query.jpatest;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import com.neaterbits.query.sql.dsl.api.BaseJPATest;
import com.neaterbits.query.sql.dsl.api.IShort;

public class GEN_BaseTestCase extends BaseJPATest {

	protected static final IShort select = com.neaterbits.query.sql.dsl.api.IShortSelect.get();

	
    
    protected static final Date date(String s) {
    	final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    	try {
    		return dateFormat.parse(s);
		}
		catch (ParseException ex) {
			throw new RuntimeException("Failed to parse \"" + s + "\"", ex);
		}
    }
}
