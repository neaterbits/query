package com.neaterbits.query.sql.dsl.api._short;

import java.math.BigDecimal;

import org.junit.Test;

import com.neaterbits.query.jpatest.model.Company;
import com.neaterbits.query.sql.dsl.api.BaseJPATest;
import com.neaterbits.query.sql.dsl.api.IShort;
import com.neaterbits.query.sql.dsl.api.MultiBuilt;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests that check for undecided map/alias all the way through
 * @author nhl
 *
 */

public class ShortUndecidedTest extends BaseJPATest {
	protected static final IShort select = com.neaterbits.query.sql.dsl.api.IShortSelect.get();

	@Test
	public void testWhere() {
		final Company acme1 = new Company(1, "Acme", new BigDecimal("49"));
		final Company acme2 = new Company(2, "Acme Inc.", new BigDecimal("121"));
		
		/*
		select.list(NameLength.class)
			.map().absOfInteger(b -> b.length(Company::getName)).toString();
			*/

		final MultiBuilt<NameLength> acmeQuery = select
				.list(NameLength.class)
				
				.map().sqrtOfInteger(b -> b.length(Company::getName)).to(NameLength::setLengthSqrt)
				.where().absOfInteger(b -> b.length(Company::getName)).isEqualTo(4)

				.build();
		
		store(acme1, acme2)
		.checkListUnordered(acmeQuery, 
				new NameLength("Acme", 2),  		// (4 + 1) % 3 => 2
				new NameLength("Acme Inc.", 1)); 	// (9 + 1) % 3 => 1
	}
	
	@Test
	public void testAvgSumAndOthersThatReturnCorrectType() {
		// Check that returns Long for <= long type and BigInteger for BigInteger etc
		assertThat(true).isEqualTo(false);
	}
}
