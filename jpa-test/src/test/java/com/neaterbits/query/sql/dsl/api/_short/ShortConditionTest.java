package com.neaterbits.query.sql.dsl.api._short;

import java.math.BigDecimal;

import org.junit.Test;

import java.util.Arrays;

import com.neaterbits.query.jpatest.model.Company;
import com.neaterbits.query.sql.dsl.api.BaseJPATest;
import com.neaterbits.query.sql.dsl.api.IShort;
import com.neaterbits.query.sql.dsl.api.MultiBuilt;

public class ShortConditionTest extends BaseJPATest {
	
	protected static final IShort select = com.neaterbits.query.sql.dsl.api.IShortSelect.get();
	
    @Test
    public void testAggregateSingleNamed() {
		final Company acme1 = new Company(1, "Acme", new BigDecimal("49"));
		final Company acme2 = new Company(2, "Acme Inc.", new BigDecimal("121"));

    	    	
    	// sum for all landplots
    	final MultiBuilt<Company> query
			= select.list(Company.class)
				.where(Company::getStockPrice).plus(new BigDecimal("1")).isEqualTo(new BigDecimal("122"))
				.build();
    	
    	store(acme1, acme2)
		.checkListUnordered(query, () -> Arrays.asList(new Company(acme2.getId(), "Acme Inc.", new BigDecimal("121"))));
    }

    @Test
    public void testAggregateSingleAlias() {
		final Company acme1 = new Company(1, "Acme", new BigDecimal("49"));
		final Company acme2 = new Company(2, "Acme Inc.", new BigDecimal("121"));

		final Company c = select.alias(Company.class);
    	    	
    	// sum for all landplots
    	final MultiBuilt<Company> query
			= select.list(c)
				.where(c::getStockPrice).plus(new BigDecimal("1")).isEqualTo(new BigDecimal("122"))
				.build();
    	
    	store(acme1, acme2)
		.checkListUnordered(query, () -> Arrays.asList(new Company(acme2.getId(), "Acme Inc.", new BigDecimal("121"))));
    }
    
    @Test
    public void testAggregateSingleNamed2() {
		final Company acme1 = new Company(1, "Acme", 1953);
		final Company acme2 = new Company(2, "Acme Inc.", 1979);
    	    	
    	// sum for all landplots
    	final MultiBuilt<Company> query
			= select.list(Company.class)
				.where(Company::getYearFounded).plus((short)2).isEqualTo(1981)
				.build();
    	
    	store(acme1, acme2)
		.checkListUnordered(query, () -> Arrays.asList(new Company(acme2.getId(), "Acme Inc.", 1979)));
    	
    }

    @Test
    public void testAggregateSingleAlias2() {
		final Company acme1 = new Company(1, "Acme", 1953);
		final Company acme2 = new Company(2, "Acme Inc.", 1979);

		final Company c = select.alias(Company.class);
    	    	
    	// sum for all landplots
    	final MultiBuilt<Company> query
			= select.list(c)
				.where(c::getYearFounded).plus((short)2).isEqualTo(1981)
				.build();
    	
    	store(acme1, acme2)
		.checkListUnordered(query, () -> Arrays.asList(new Company(acme2.getId(), "Acme Inc.", 1979)));
    	
    }

    @Test
    public void testIsNullNamed() {
		final Company acme1 = new Company(1, "Acme", new BigDecimal("49"));
		final Company acme2 = new Company(2, "Acme Inc.", (BigDecimal)null);

    	    	
    	// sum for all landplots
    	final MultiBuilt<Company> query
			= select.list(Company.class)
				.where(Company::getStockPrice).isNull()
				.build();
    	
    	store(acme1, acme2)
		.checkListUnordered(query, () -> Arrays.asList(new Company(acme2.getId(), "Acme Inc.")));
    }

    @Test
    public void testIsNullAlias() {
		final Company acme1 = new Company(1, "Acme", new BigDecimal("49"));
		final Company acme2 = new Company(2, "Acme Inc.", (BigDecimal)null);

		final Company c = select.alias(Company.class);
    	    	
    	// sum for all landplots
    	final MultiBuilt<Company> query
			= select.list(c)
				.where(c::getStockPrice).isNull()
				.build();
    	
    	store(acme1, acme2)
		.checkListUnordered(query, () -> Arrays.asList(new Company(acme2.getId(), "Acme Inc.")));
    }

    @Test
    public void testIsNotNullNamed() {
		final Company acme1 = new Company(1, "Acme", new BigDecimal("49"));
		final Company acme2 = new Company(2, "Acme Inc.", (BigDecimal)null);

    	    	
    	// sum for all landplots
    	final MultiBuilt<Company> query
			= select.list(Company.class)
				.where(Company::getStockPrice).isNotNull()
				.build();
    	
    	store(acme1, acme2)
		.checkListUnordered(query, () -> Arrays.asList(new Company(acme1.getId(), "Acme", new BigDecimal("49"))));
    }

    @Test
    public void testIsNotNullAlias() {
		final Company acme1 = new Company(1, "Acme", new BigDecimal("49"));
		final Company acme2 = new Company(2, "Acme Inc.", (BigDecimal)null);

		final Company c = select.alias(Company.class);
    	    	
    	// sum for all landplots
    	final MultiBuilt<Company> query
			= select.list(c)
				.where(c::getStockPrice).isNotNull()
				.build();
    	
    	store(acme1, acme2)
		.checkListUnordered(query, () -> Arrays.asList(new Company(acme1.getId(), "Acme", new BigDecimal("49"))));
    }

}
