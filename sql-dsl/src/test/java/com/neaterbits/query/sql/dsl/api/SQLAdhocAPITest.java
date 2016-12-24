package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SQLAdhocAPITest {

	private static class Bar {
		private int baz;

		public int getBaz() {
			return baz;
		}

		public void setBaz(int value) {
			this.baz = value;
		}
	}
	
	@Test
	public void testAdhoc() {
		
		final List<Foo> fooList = new ArrayList<>();
		
		fooList.add(new Foo(1, 2, new BigDecimal("3.1")));
		fooList.add(new Foo(3, 1, new BigDecimal("4.8")));

		final int sum = Adhoc.sum(Foo::getValue)
			.from(fooList)
			.where(Foo::getDecimal).isEqualTo(BigDecimal.ONE)
			.get();
		
		assertThat(sum).isEqualTo(0);
		
		final int sumOfAll = Adhoc.sum(Foo::getValue).from(fooList).get();
		assertThat(sumOfAll).isEqualTo(4);
		
		final BigDecimal sumOfDecimal = Adhoc.sum(Foo::getDecimal).from(fooList).get();
		assertThat(sumOfDecimal.compareTo(new BigDecimal("7.9"))).isEqualTo(0);
	}
}
