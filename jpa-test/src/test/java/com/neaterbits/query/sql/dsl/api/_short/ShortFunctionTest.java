package com.neaterbits.query.sql.dsl.api._short;

import java.math.BigDecimal;

import org.junit.Test;

import com.neaterbits.query.jpatest.model.Company;
import com.neaterbits.query.sql.dsl.api.BaseJPATest;
import com.neaterbits.query.sql.dsl.api.IShort;
import com.neaterbits.query.sql.dsl.api.MultiBuilt;

public class ShortFunctionTest extends BaseJPATest {
	protected static final IShort select = com.neaterbits.query.sql.dsl.api.IShortSelect.get();

	
	@Test // Test that can combine mod and length functions
	public void testModAndLengthNamed() {
		final Company acme1 = new Company(1, "Acme", new BigDecimal("49"));
		final Company acme2 = new Company(2, "Acme Inc.", new BigDecimal("121"));

		final MultiBuilt<NameLength> acmeQuery = select
				.list(NameLength.class)
				
				.map(Company::getName).to(NameLength::setName)
				//.map().modOf(b -> b.lower(Company::getName) ).to(NameLength::setLength)
				.map().modOf(b -> b.length(Company::getName).plus((short)1), 3).to(NameLength::setLength)

						/*
				!! fortsett her
				
				!! sjekke at ikke kan ha annen type i sub, f.eks dette bør ikke kompilere 
				modOf(b -> b.lower(Company::getName)).to(NameLength::setLength)
				
				
				!! sjekke at lower() returnerer nye funjsoner
				!! sjekke at abs(). returnerer alle funsjoner som returnere integer, også length
				*/
				.build();
		
		store(acme1, acme2)
		.checkListUnordered(acmeQuery, new NameLength("Acme", 4), new NameLength("Acme Inc.", 9));
	}

	// Test that can call nestedfunction as first entry after map()
	// this is a problematic case since we have not yet decided whether named or aliased
	@Test 
	public void testVerifyNestedInFirstMapQueryWheneNamedOrAliasNotYetDecided() {

		verifyIsCompilable(
				b -> b.addImport(NameLength.class).addImport(Company.class),
				
				"list(NameLength.class)" +
				".map().modOf(b -> b.length(Company::getName), 3).to(NameLength::setLength)");

		verifyIsCompilable(
				b -> b.addImport(NameLength.class).addImport(Company.class),
				
				"list(NameLength.class)" +
				".map().sqrt().length(Company::getName).to(NameLength::setLengthSqrt)");
	}

	@Test // Test that can combine no-param, integer and length functions directly
	public void testCanCombineNoParamArithmeticAndStringLength_Named() {
		final Company acme1 = new Company(1, "Acme", new BigDecimal("49"));
		final Company acme2 = new Company(2, "Acme Inc.", new BigDecimal("121"));

		final MultiBuilt<NameLength> acmeQuery = select
				.list(NameLength.class)
				
				.map(Company::getName).to(NameLength::setName)
				//.map().modOf(b -> b.lower(Company::getName) ).to(NameLength::setLength)
				.map().sqrt().length(Company::getName).to(NameLength::setLengthSqrt)
				.build();

		
		store(acme1, acme2)
		.checkListUnordered(acmeQuery, new NameLength("Acme", 2.0), new NameLength("Acme Inc.", 3.0));
	}
	
	@Test // Test that can combine no-param, integer and length functions directly
	public void testCanCombineNoParamArithmeticAndStringLength_Alias() {
		final Company acme1 = new Company(1, "Acme", new BigDecimal("49"));
		final Company acme2 = new Company(2, "Acme Inc.", new BigDecimal("121"));

		final Company c = select.alias(Company.class);
		
		final MultiBuilt<NameLength> acmeQuery = select
				.list(NameLength.class)
				
				.map(c::getName).to(NameLength::setName)
				//.map().modOf(b -> b.lower(Company::getName) ).to(NameLength::setLength)
				.map().sqrt().length(c::getName).to(NameLength::setLengthSqrt)
				.build();

		
		store(acme1, acme2)
		.checkListUnordered(acmeQuery, new NameLength("Acme", 2.0), new NameLength("Acme Inc.", 3.0));
	}

	@Test // Test that cannot combine no-param, integer and other String functions directly
	public void testCannotCombineNoParamArithmeticAndStringLength() {
		
	}

	@Test // Test that can combine mod and length functions
	public void testVerifyNoStringTypeInIntegerSub() {
		
		verifyIsCompilable(
				b -> b.addImport(NameLength.class).addImport(Company.class),
				
				"list(NameLength.class)" +
				".map(Company::getName).to(NameLength::setName)" +
				".map().modOf(b -> b.length(Company::getName), 3).to(NameLength::setLength)");
		
		verifyIsNotCompilable(
				"list(NameLength.class)" +
				".map(Company::getName).to(NameLength::setName)" +
				".map().modOf(b -> b.lower(Company::getName), 3).to(NameLength::setLength)");
	}
}
