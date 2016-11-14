package com.neaterbits.query.sql.dsl.api.standalone;

import org.junit.Test;

import com.neaterbits.query.sql.dsl.api.Alias;
import com.neaterbits.query.sql.dsl.api.testvo.Company;
import com.neaterbits.query.sql.dsl.api.testvo.Employee;
import com.neaterbits.query.sql.dsl.api.testvo.Person;
import com.neaterbits.query.sql.dsl.api.testvo.Role;

import static com.neaterbits.query.sql.dsl.api.Select.selectOne;
import static com.neaterbits.query.sql.dsl.api.Select.alias;

public class SQLAPITest {

    @Test
    public void testTableBased() {

        selectOne(ResultVO.class)

        	.map(Company::getId)		.to(ResultVO::setCompanyId)
        	.map(Person::getId)		   	.to(ResultVO::setPersonId)
        	.map(Person::getFirstName)	.to(ResultVO::setFirstName)

        	.from(Company.class, Person.class, Role.class)

        	.where(Company::getName)		.startsWith("Foo")
        	  .and(Company::getId)			.isEqualTo(Employee::getCompanyId)
        	  .and(Employee::getPersonId)	.isEqualTo(Person::getId);

        	// where-clause
        	//.whereString(Company::getId)

        	;

    }

    @Test
    public void testAliasBased() {
        final Alias<Role> role = alias(Role.class);
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
