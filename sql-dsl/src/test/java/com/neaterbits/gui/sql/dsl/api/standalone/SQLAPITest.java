package com.neaterbits.gui.sql.dsl.api.standalone;

import org.junit.Test;

import com.neaterbits.gui.sql.dsl.api.standalone.Alias;
import com.neaterbits.gui.sql.dsl.api.standalone.SelectAPI;
import com.neaterbits.gui.sql.dsl.api.testvo.Company;
import com.neaterbits.gui.sql.dsl.api.testvo.Employee;
import com.neaterbits.gui.sql.dsl.api.testvo.Person;
import com.neaterbits.gui.sql.dsl.api.testvo.Role;

public class SQLAPITest {

    @Test
    public void testTableBased() {

        final SelectAPI selectAPI = null;

        selectAPI.selectSingle(ResultVO.class)

        	.map(Company::getId)		.to(ResultVO::setCompanyId)
        	.map(Person::getId)		   	.to(ResultVO::setPersonId)
        	.map(Person::getFirstName)	.to(ResultVO::setFirstName)

        	.from(Company.class, Person.class, Role.class)

        	.where(Company::getName)		.	startsWith("Foo")
        	     .and(Company::getId)			.isEqualTo(Employee::getCompanyId)
        	     .and(Employee::getPersonId)	.isEqualTo(Person::getId);

        	// where-clause
        	//.whereString(Company::getId)

        	;

        final FuncTest funcTest = null;

        funcTest.foo(Company::getName);
    }

    @Test
    public void testAliasBased() {
        final SelectAPI selectAPI = null;
        
        final Alias<Role> role = selectAPI.alias(Role.class);
    	
    }

    private static class Foo {
    	private String test;

		public String getTest() {
			return test;
		}

		public void setTest(String test) {
			this.test = test;
		}
    }
}
