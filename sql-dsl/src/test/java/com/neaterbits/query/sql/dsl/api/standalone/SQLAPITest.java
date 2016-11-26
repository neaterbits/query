package com.neaterbits.query.sql.dsl.api.standalone;

import java.util.function.Supplier;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

import com.neaterbits.query.sql.dsl.api.Alias;
import com.neaterbits.query.sql.dsl.api.Param;
import com.neaterbits.query.sql.dsl.api.QueryDataSource;
import com.neaterbits.query.sql.dsl.api.SingleQuery;
import com.neaterbits.query.sql.dsl.api.testvo.Company;
import com.neaterbits.query.sql.dsl.api.testvo.Employee;
import com.neaterbits.query.sql.dsl.api.testvo.Person;
import com.neaterbits.query.sql.dsl.api.testvo.Role;

import static com.neaterbits.query.sql.dsl.api.Select.selectOne;
import static com.neaterbits.query.sql.dsl.api.Select.aliasAlias;

public class SQLAPITest {

    @Test
    public void testTableBased() {
    	
    	final Alias<Company> c = aliasAlias(Company.class);
    	
    	final QueryDataSource ds = null;
    	
    	final Param<Integer> param = null;
    	
    	
    	final Company company = null;
    	
    	final Supplier<Integer> f = company::getId;
    	
        final SingleQuery<ResultVO > query =
        		selectOne(ResultVO.class)

        	.map(Company::getId)		.to(ResultVO::setCompanyId)
        	.map(Person::getId)		   	.to(ResultVO::setPersonId)
        	.map(Person::getFirstName)	.to(ResultVO::setFirstName)

        	.from(Company.class, Person.class, Role.class)

        	.where(Company::getName)		.startsWith("Foo")
        	  .and(Company::getId)			.isEqualTo(Employee::getCompanyId)

        	// where-clause
        	//.whereString(Company::getId)
        	.compile();

        	ResultVO result = query.executeOn(ds)
        	 .with(param).setTo(123)
        	  .and(param).setTo(345)
        	  .get();

        	result = query.execute(b -> b
				.with(param).setTo(123)
				.and(param).setTo(456)
				
				.on(ds)
			);

        assertThat(query).isNotNull();
    }

    @Test
    public void testAliasBased() {
        final Alias<Role> role = aliasAlias(Role.class);
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
