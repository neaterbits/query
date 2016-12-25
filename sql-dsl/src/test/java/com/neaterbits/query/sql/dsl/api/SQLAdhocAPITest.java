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
	public void testAdhocAggregate() {
		
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

	@Test
	public void testAdhocList() {

		final List<Foo> fooList = new ArrayList<>();

		final Foo foo1 = new Foo(1, 2, new BigDecimal("3.1"));
		final Foo foo2 = new Foo(3, 1, new BigDecimal("4.8"));
		final Foo foo3 = new Foo(2, 7, new BigDecimal("1.8"));

		fooList.add(foo1);
		fooList.add(foo2);
		fooList.add(foo3);

		final List<Foo> foos = Adhoc.list(fooList)
							.where(Foo::getDecimal).isGreaterThan(new BigDecimal("2.0"))
							.get();

		assertThat(foos.size()).isEqualTo(2);
		
		assertThat(foos.contains(foo1)).isTrue();
		assertThat(foos.contains(foo2)).isTrue();
	}
}
