package com.neaterbits.query.sql.dsl.api._short;

import java.math.BigDecimal;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

import com.neaterbits.query.jpatest.model.Company;
import com.neaterbits.query.sql.dsl.api.BaseJPATest;
import com.neaterbits.query.sql.dsl.api.CompanyAggregatesVO;
import com.neaterbits.query.sql.dsl.api.CompanyResultVO;
import com.neaterbits.query.sql.dsl.api.CompanySqrtAggregatesVO;
import com.neaterbits.query.sql.dsl.api.IShort;
import com.neaterbits.query.sql.dsl.api.MultiBuilt;
import com.neaterbits.query.sql.dsl.api.SingleBuilt;

public class ShortFunctionTest extends BaseJPATest {
	protected static final IShort select = com.neaterbits.query.sql.dsl.api.IShortSelect.get();

	
	@Test // Test that can combine mod and length functions
	public void testModAndLengthNamed() {
		final Company acme1 = new Company(1, "Acme", new BigDecimal("49"));
		final Company acme2 = new Company(2, "Acme Inc.", new BigDecimal("121"));
		
		/*
		select.list(NameLength.class)
			.map().absOfInteger(b -> b.length(Company::getName)).toString();
			*/

		final MultiBuilt<NameLength> acmeQuery = select
				.list(NameLength.class)
				
				.map(Company::getName).to(NameLength::setName)
				//.map().modOf(b -> b.lower(Company::getName) ).to(NameLength::setLength)
				.map().modOf(b -> b.length(Company::getName).plus((short)1), 3).to(NameLength::setLength)

				.build();
		
		store(acme1, acme2)
		.checkListUnordered(acmeQuery, 
				new NameLength("Acme", 2),  		// (4 + 1) % 3 => 2
				new NameLength("Acme Inc.", 1)); 	// (9 + 1) % 3 => 1
	}
	
	

	@Test // Test that can combine mod and length functions
	public void testModAndLengthAlias() {
		final Company acme1 = new Company(1, "Acme", new BigDecimal("49"));
		final Company acme2 = new Company(2, "Acme Inc.", new BigDecimal("121"));

		final Company c = select.alias(Company.class);

		final MultiBuilt<NameLength> acmeQuery = select
				.list(NameLength.class)

				.map(c::getName).to(NameLength::setName)
				//.map().modOf(b -> b.lower(Company::getName) ).to(NameLength::setLength)

				//fortsett her, fikse slik at kun returnerer
				.map().modOf(b -> b.length(c::getName).plus((short)1), 3).to(NameLength::setLength)
				. where()
					.absOfDouble(b -> b.sqrt(c::getStockPrice)).isEqualTo(11.0)

				.build();

		store(acme1, acme2)
		.checkListUnordered(acmeQuery, 
				new NameLength("Acme Inc.", 1)  		// (9 + 1) % 3 => 1
				);
	}
	

	@Test // Should not allow .absOfBigDecimal for sqrt() since sqrt() is Double type 
	public void testShouldNotCompileCondition() {

		verifyIsNotCompilable(
				b -> b.addImport(NameLength.class).addImport(Company.class),
						
				"list(NameLength.class)" +
				
				".map(Company::getName).to(NameLength::setName)" +
				".where().absOfBigDecimal(b -> b.sqrt(Company::getStockPrice)).isEqualTo(new BigDecimal(\"1.2\"))");

		verifyIsNotCompilable(
				b -> b.addImport(NameLength.class).addImport(Company.class)
					 .add(Company.class, "c"),
						
				"list(NameLength.class)" +
				
				".map(c::getName).to(NameLength::setName)" +
				".where().absOfBigDecimal(b -> b.sqrt(c::getStockPrice)).isEqualTo(new BigDecimal(\"1.2\"))");

	}
	@Test // Seems to compile
	public void testDoesNotCompileAndAfterMap() {
		verifyIsNotCompilable(
				b -> b.addImport(NameLength.class).addImport(Company.class),
				
				"list(NameLength.class)" +
				
				".map(Company::getName).to(NameLength::setName)" + 
				".and(Company::getName).isEqualTo(\"Acme\")");		
		
		verifyIsNotCompilable(
				b -> b.addImport(NameLength.class).addImport(Company.class)
					 .add(Company.class, "c"),
				
				"list(NameLength.class)" +
				
				".map(c::getName).to(NameLength::setName)" + 
				".and(c::getName).isEqualTo(\"Acme\")");		
	}
	
	@Test
	public void testDoesNotCompileOrAfterMap() {
		verifyIsNotCompilable(
				b -> b.addImport(NameLength.class).addImport(Company.class),
				
				"list(NameLength.class)" +
				
				".map(Company::getName).to(NameLength::setName)" + 
				".or(Company::getName).isEqualTo(\"Acme\")");		
		
		verifyIsNotCompilable(
				b -> b.addImport(NameLength.class).addImport(Company.class)
					 .add(Company.class, "c"),
				
				"list(NameLength.class)" +
				
				".map(c::getName).to(NameLength::setName)" + 
				".or(c::getName).isEqualTo(\"Acme\")");		
	}

	// Test that can call nested function as first entry after map()
	// this is a problematic case since we have not yet decided whether named or aliased
	@Test 
	public void testVerifyNestedInFirstMapQueryWhenNamedOrAliasNotYetDecided() {

		verifyIsCompilable(
				b -> b.addImport(NameLength.class).addImport(Company.class),
				
				"list(NameLength.class)" +
				".map().modOf(b -> b.length(Company::getName), 3).to(NameLength::setLength)");


		verifyIsCompilable(
				b -> b.addImport(NameLength.class).addImport(Company.class),
				
				"list(NameLength.class)" +
				".map().sqrt().length(Company::getName).to(NameLength::setLengthSqrt)");

		final Company acme1 = new Company(1, "Acme", new BigDecimal("49"));
		final Company acme2 = new Company(2, "Acme Inc.", new BigDecimal("121"));
		
		MultiBuilt<NameLength> query;


		/*
		query = select.list(NameLength.class)
				.map().modOf(b -> b.length(Company::getName), 3).to(NameLength::setLength)
				.build();
				*/
		
		query = select.list(NameLength.class)
			.map().sqrtOfInteger(s -> s.length(Company::getName)).to(NameLength::setLengthSqrt)
			
			.where().sqrtOfInteger(s -> s.length(Company::getName)).isEqualTo(1.2)
			.groupBy(1)
			.having().avg(Company::getStockPrice).isEqualTo(132.2)
			.orderBy(1)
			.build();
		
		store(acme1, acme2)
		.checkListUnordered(query, new NameLength(null, 2.0), new NameLength(null, 3.0));

		assertThat(true).isEqualTo(false); // TODO - must pass Undecided versions all along to group-by etc since may use sub-functions all the way
		
		query = select.list(NameLength.class)
				.map().sqrt().length(Company::getName).to(NameLength::setLengthSqrt)
				.map(Company::getName).to(NameLength::setName)
				.build();

			store(acme1, acme2)
			.checkListUnordered(query, new NameLength("Acme", 2.0), new NameLength("Acme Inc.", 3.0));
	}

	@Test 
	public void testVerifyThatDetectsMixOfNamedAndAliasAtQueryCompileTime() {
		// Must make sure that throws exception for queries where we cannot detect type differences
		// at Java compile time, eg two .xyzOf() queries at the same level
		// eg. whether named or alias
		assertThat(true).isEqualTo(false);
	}

	@Test 
	public void testVerifyMultipleNestedInFirstMapQueryWhenNamedOrAliasNotYetDecided() {
		
		assertThat(true).isEqualTo(false); // TODO - various version of undecided
		
		/*
		final Company acme1 = new Company(1, "Acme", new BigDecimal("49"));
		final Company acme2 = new Company(2, "Acme Inc.", new BigDecimal("121"));
		
		
		final MultiBuilt<NameLength> query = select.list(NameLength.class)
			.map().modOf(b1 -> b1.absOfInteger(b2 -> b2.length(Company::getName)), 3).to(NameLength::setLength)
			.build();
		*/
	
		
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

	@Test
	public void testThatLowerReturnsOtherString_Named() {
		final Company acme1 = new Company(1, " Acme", new BigDecimal("49"));
		final Company acme2 = new Company(2, " Acme Inc.", new BigDecimal("121"));

		final MultiBuilt<NameLength> acmeQuery = select
				.list(NameLength.class)
				.map().lower().trim(Company::getName).to(NameLength::setName)
				.build();

		
		store(acme1, acme2)
		.checkListUnordered(acmeQuery, new NameLength("acme"), new NameLength("acme inc."));
	}
	
	@Test
	public void testThatLowerReturnsOtherString_Alias() {
		final Company acme1 = new Company(1, " Acme", new BigDecimal("49"));
		final Company acme2 = new Company(2, " Acme Inc.", new BigDecimal("121"));

		final Company c = select.alias(Company.class);
		
		final MultiBuilt<NameLength> acmeQuery = select
				.list(NameLength.class)
				.map().lower().trim(c::getName).to(NameLength::setName)
				.build();

		
		store(acme1, acme2)
		.checkListUnordered(acmeQuery, new NameLength("acme"), new NameLength("acme inc."));
	}

	@Test
	public void testThatLengthReturnsOtherString_Named() {
		final Company acme1 = new Company(1, " Acme", new BigDecimal("49"));
		final Company acme2 = new Company(2, " Acme Inc.", new BigDecimal("121"));

		final MultiBuilt<NameLength> acmeQuery = select
				.list(NameLength.class)
				.map(Company::getName).to(NameLength::setName)
				.map().length().trim(Company::getName).to(NameLength::setLength)
				.build();

		
		store(acme1, acme2)
		.checkListUnordered(acmeQuery, new NameLength(" Acme", 4), new NameLength(" Acme Inc.", 9));
	}

	@Test
	public void testThatLengthReturnsOtherString_Alias() {
		final Company acme1 = new Company(1, " Acme", new BigDecimal("49"));
		final Company acme2 = new Company(2, " Acme Inc.", new BigDecimal("121"));

		final Company c= select.alias(Company.class);
		
		final MultiBuilt<NameLength> acmeQuery = select
				.list(NameLength.class)
				.map(c::getName).to(NameLength::setName)
				.map().length().trim(c::getName).to(NameLength::setLength)
				.build();

		
		store(acme1, acme2)
		.checkListUnordered(acmeQuery, new NameLength(" Acme", 4), new NameLength(" Acme Inc.", 9));
	}

	@Test
	public void testThatCanDoAbsOfLength_Named() {
		final Company acme1 = new Company(1, "Acme", new BigDecimal("49"));
		final Company acme2 = new Company(2, "Acme Inc.", new BigDecimal("121"));

		final MultiBuilt<NameLength> acmeQuery = select.list(NameLength.class)
				.map(Company::getName).to(NameLength::setName)
				.map().abs().length(Company::getName).to(NameLength::setLength)
				.build();

		
		store(acme1, acme2)
		.checkListUnordered(acmeQuery, new NameLength("Acme", 4), new NameLength("Acme Inc.", 9));
	}
	
	@Test
	public void testThatCanDoAbsOfLength_Alias() {
		final Company acme1 = new Company(1, "Acme", new BigDecimal("49"));
		final Company acme2 = new Company(2, "Acme Inc.", new BigDecimal("121"));

		final MultiBuilt<NameLength> acmeQuery = select.list(NameLength.class)
				.map(Company::getName).to(NameLength::setName)
				.map().abs().length(Company::getName).to(NameLength::setLength)
				.build();

		
		store(acme1, acme2)
		.checkListUnordered(acmeQuery, new NameLength("Acme", 4), new NameLength("Acme Inc.", 9));
	}
	
	@Test
	public void testThatCanDoAbsOfLengthTrim_Named() {
		final Company acme1 = new Company(1, " Acme", new BigDecimal("49"));
		final Company acme2 = new Company(2, " Acme Inc.", new BigDecimal("121"));

		final MultiBuilt<NameLength> acmeQuery = select.list(NameLength.class)
				.map(Company::getName).to(NameLength::setName)
				.map().abs().length(Company::getName).to(NameLength::setLength)
				.map().abs().length().trim(Company::getName).to(NameLength::setLength)
				.build();

		
		store(acme1, acme2)
		.checkListUnordered(acmeQuery, new NameLength(" Acme", 4), new NameLength(" Acme Inc.", 9));
	}

	@Test
	public void testThatCanDoAbsOfLengthTrim_Alias() {
		final Company acme1 = new Company(1, " Acme", new BigDecimal("49"));
		final Company acme2 = new Company(2, " Acme Inc.", new BigDecimal("121"));

		final Company c = select.alias(Company.class);
		
		final MultiBuilt<NameLength> acmeQuery = select.list(NameLength.class)
				.map(c::getName).to(NameLength::setName)
				.map().abs().length().trim(c::getName).to(NameLength::setLength)
				.build();

		
		store(acme1, acme2)
		.checkListUnordered(acmeQuery, new NameLength(" Acme", 4), new NameLength(" Acme Inc.", 9));
	}

	@Test
	public void testThatStringMethodDoesNotReturnLengthFunction_Named() {
		verifyIsCompilable(
				b -> b.addImport(NameLength.class).addImport(Company.class),
				"list(NameLength.class)" +
				".map().lower().trim(Company::getName).to(NameLength::setName)"				
				);

		verifyIsNotCompilable(
				b -> b.addImport(NameLength.class).addImport(Company.class),
				"list(NameLength.class)" +
				".map().lower().length(Company::getName).to(NameLength::setLength)"				
				);


		verifyIsNotCompilable(
				b -> b.addImport(NameLength.class).addImport(Company.class),
				"list(NameLength.class)" +
				".map().lower(Company::getName).to(NameLength::setName)" +				
				".map().lower().length(Company::getName).to(NameLength::setLength)" 				
				);

		verifyIsNotCompilable(
				b -> b.addImport(NameLength.class).addImport(Company.class),
				"list(NameLength.class)" +
				".map().lower(Company::getName).to(NameLength::setName)" +				
				".map().lower().length().trim(Company::getName).to(NameLength::setLength)" 				
				);
	}

	@Test
	public void testThatStringMethodDoesNotReturnLengthFunction_Alias() {

		verifyIsCompilable(
				b -> b.addImport(NameLength.class).addImport(Company.class)
					.add(Company.class, "c"),
				"list(NameLength.class)" +
				".map().lower().trim(c::getName).to(NameLength::setName)"				
				);

		verifyIsNotCompilable(
				b -> b.addImport(NameLength.class).addImport(Company.class)
					.add(Company.class, "c"),
				"list(NameLength.class)" +
				".map().lower().length(c::getName).to(NameLength::setLength)"				
				);

		verifyIsNotCompilable(
				b -> b.addImport(NameLength.class).addImport(Company.class)
					.add(Company.class, "c"),
				"list(NameLength.class)" +
				".map().lower(c::getName).to(NameLength::setName)" + 				
				".map().lower().length(c::getName).to(NameLength::setLength)"
				);

		verifyIsNotCompilable(
				b -> b.addImport(NameLength.class).addImport(Company.class)
					.add(Company.class, "c"),
				"list(NameLength.class)" +
				".map().lower(c::getName).to(NameLength::setName)" + 				
				".map().lower().length().trim(c::getName).to(NameLength::setLength)"
				);
	}

	@Test
	public void testThatLengthFunctionDoesNotReturnLengthFunction_Named() {
		
		verifyIsCompilable(
				b -> b.addImport(NameLength.class).addImport(Company.class),
				"list(NameLength.class)" +
				".map().length().trim(Company::getName).to(NameLength::setLength)"				
				);

		verifyIsNotCompilable(
				b -> b.addImport(NameLength.class).addImport(Company.class),
				"list(NameLength.class)" +
				".map().length().length(Company::getName).to(NameLength::setLength)"				
				);
		
		verifyIsNotCompilable(
				b -> b.addImport(NameLength.class).addImport(Company.class),
				"list(NameLength.class)" +
				".map().length().length().lower(Company::getName).to(NameLength::setLength)"				
				);

		verifyIsNotCompilable(
				b -> b.addImport(NameLength.class).addImport(Company.class),
				"list(NameLength.class)" +
				".map().lower(Company::getName).to(NameLength::setName)" +				
				".map().length().length(Company::getName).to(NameLength::setLength)"				
				);
		
		verifyIsNotCompilable(
				b -> b.addImport(NameLength.class).addImport(Company.class),
				"list(NameLength.class)" +
				".map().lower(Company::getName).to(NameLength::setName)" +				
				".map().length().length().lower(Company::getName).to(NameLength::setLength)"				
				);

	}


	@Test
	public void testThatLengthFunctionDoesNotReturnLengthFunction_Alias() {
		verifyIsCompilable(
				b -> b.addImport(NameLength.class).addImport(Company.class)
				.add(Company.class, "c"),
				"list(NameLength.class)" +
				".map().length().trim(c::getName).to(NameLength::setLength)"				
				);

		verifyIsNotCompilable(
				b -> b.addImport(NameLength.class).addImport(Company.class)
				.add(Company.class, "c"),
				"list(NameLength.class)" +
				".map().length().length(c::getName).to(NameLength::setLength)"				
				);
		
		verifyIsNotCompilable(
				b -> b.addImport(NameLength.class).addImport(Company.class)
				.add(Company.class, "c"),
				"list(NameLength.class)" +
				".map().length().length().lower(c::getName).to(NameLength::setLength)"				
				);

		verifyIsNotCompilable(
				b -> b.addImport(NameLength.class).addImport(Company.class)
				.add(Company.class, "c"),
				"list(NameLength.class)" +
				".map().lower(c::getName).to(NameLength::setName)" +				
				".map().length().length(c::getName).to(NameLength::setLength)"				
				);
		
		verifyIsNotCompilable(
				b -> b.addImport(NameLength.class).addImport(Company.class)
				.add(Company.class, "c"),
				"list(NameLength.class)" +
				".map().lower(c::getName).to(NameLength::setName)" +				
				".map().length().length().lower(c::getName).to(NameLength::setLength)"				
				);

	}
	
	// Test that cannot combine no-param, integer and other String functions directly
	// eg. combining sqrt() and lower() 
	@Test 
	public void testCannotCombineNoParamArithmeticAndStringLength() {

		verifyIsCompilable(
				b -> b.addImport(NameLength.class).addImport(Company.class),
				"list(NameLength.class)" +
				".map().lower(Company::getName).to(NameLength::setName)" +				
				".map().abs().length(Company::getName).to(NameLength::setLength)"				
				);

		verifyIsNotCompilable(
				b -> b.addImport(NameLength.class).addImport(Company.class)
				.add(Company.class, "c"),
				"list(NameLength.class)" +
				".map().lower(Company::getName).to(NameLength::setName)" +				
				".map().abs().lower(Company::getName).to(NameLength::setLength)"				
				);

		verifyIsCompilable(
				b -> b.addImport(NameLength.class).addImport(Company.class)
				.add(Company.class, "c"),
				"list(NameLength.class)" +
				".map().lower(c::getName).to(NameLength::setName)" +				
				".map().abs().length(c::getName).to(NameLength::setLength)"				
				);
		
		verifyIsNotCompilable(
				b -> b.addImport(NameLength.class).addImport(Company.class)
				.add(Company.class, "c"),
				"list(NameLength.class)" +
				".map().lower(c::getName).to(NameLength::setName)" +				
				".map().abs().lower(c::getName).to(NameLength::setLength)"				
				);
	}

	@Test // Test that can combine mod and length functions
	public void testVerifyNoStringTypeInIntegerSub() {

		/*
		select.list(NameLength.class)
			.map().absOfInteger(b ->b.abs(Company::getYearFounded)).
		*/
		
		
		verifyIsCompilable(
				b -> b.addImport(NameLength.class).addImport(Company.class),
				
				"list(NameLength.class)" +
				".map().modOf(b -> b.length(Company::getName), 3).to(NameLength::setLength)");

		verifyIsNotCompilable(
				"list(NameLength.class)" +
				".map().modOf(b -> b.lower(Company::getName), 3).to(NameLength::setLength)");

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

	@Test
    public void testMapSumAndAvgList() {
		
		final Company acme1 = new Company(1, "Acme1", new BigDecimal("153.2"));
		final Company acme2 = new Company(2, "Acme2", new BigDecimal("96.7"));
		final Company foo = new Company(3, "Foo", new BigDecimal("35.6"));

		final SingleBuilt<CompanyAggregatesVO> acmeQuery = select
				.one(CompanyAggregatesVO.class)

				//.map(Company::getName) .to (CompanyResultsVO::setName)
				.map().sum(Company::getStockPrice).to (CompanyAggregatesVO::setSumStockPrice)


				.map().avg(Company::getStockPrice).to(CompanyAggregatesVO::setAvgStockPrice)

				.where(Company::getName).startsWith("Acme")
				.build();
		
		store(s  -> s.add(acme1)
					 .add(acme2)
					 .add(foo)).
		checkOneValue(acmeQuery, new CompanyAggregatesVO(null, 124.95, new BigDecimal("249.9")));
	}

	@Test
    public void testSqrtOfAvgList() {
		
		final Company acme1 = new Company(1, "Acme1", new BigDecimal("45"));
		final Company acme2 = new Company(2, "Acme2", new BigDecimal("53"));
		final Company foo = new Company(3, "Foo", new BigDecimal("35.6"));

		final SingleBuilt<CompanySqrtAggregatesVO> acmeQuery = select
				.one(CompanySqrtAggregatesVO.class)

				.map().sqrt().avg(Company::getStockPrice).to(CompanySqrtAggregatesVO::setSqrtAvgStockPrice)

				.where(Company::getName).startsWith("Acme")
				.build();
		
		store(s  -> s.add(acme1)
					 .add(acme2)
					 .add(foo)).
		checkOneValue(acmeQuery, new CompanySqrtAggregatesVO(7.0, null));
	}
	
	@Test
    public void testArithmeticModNamed() {
		
		final Company acme1 = new Company(1, "Acme1", new BigDecimal("49"));
		final Company acme2 = new Company(2, "Acme2", new BigDecimal("121"));

		acme2.setYearFounded(1967);
		
		MultiBuilt<CompanyResultVO> acmeQuery = select
				.list(CompanyResultVO.class)
				
				.map(Company::getName).to(CompanyResultVO::setName)
				.map().mod(Company::getYearFounded, 100).to(CompanyResultVO::setYearFounded)

				.build();
		
		store(acme1, acme2)
		.checkListUnordered(acmeQuery, new CompanyResultVO("Acme1"), new CompanyResultVO("Acme2", 67));
	}
	
	@Test
    public void testArithmeticModOfNamed() {
		
		final Company acme1 = new Company(1, "Acme1", new BigDecimal("49"));
		final Company acme2 = new Company(2, "Acme2", new BigDecimal("121"));

		acme2.setYearFounded(1967);
		
		MultiBuilt<CompanyResultVO> acmeQuery = select
				.list(CompanyResultVO.class)
				
				.map(Company::getName).to(CompanyResultVO::setName)
				.map().modOf(b -> b.abs(Company::getYearFounded), 100).to(CompanyResultVO::setYearFounded)

				.build();
		
		store(acme1, acme2)
		.checkListUnordered(acmeQuery, new CompanyResultVO("Acme1"), new CompanyResultVO("Acme2", 67));
	}

	@Test
    public void testLengthNamed() {
		final Company acme1 = new Company(1, "Acme", new BigDecimal("49"));
		final Company acme2 = new Company(2, "Acme Inc.", new BigDecimal("121"));

		MultiBuilt<NameLength> acmeQuery = select
				.list(NameLength.class)
				
				.map(Company::getName).to(NameLength::setName)
				.map().length(Company::getName).to(NameLength::setLength)

				.build();
		
		store(acme1, acme2)
		.checkListUnordered(acmeQuery, new NameLength("Acme", 4), new NameLength("Acme Inc.", 9));
	}


	@Test
    public void testLengthAlias() {
		final Company acme1 = new Company(1, "Acme", new BigDecimal("49"));
		final Company acme2 = new Company(2, "Acme Inc.", new BigDecimal("121"));

		final Company c = select.alias(Company.class);
		
		MultiBuilt<NameLength> acmeQuery = select
				.list(NameLength.class)
				
				.map(c::getName).to(NameLength::setName)
				.map().length(c::getName).to(NameLength::setLength)

				.build();
		
		store(acme1, acme2)
		.checkListUnordered(acmeQuery, new NameLength("Acme", 4), new NameLength("Acme Inc.", 9));
	}
}
