package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SQLAdhocAPITest {

	private static class Foo {
		private int value;
		private int value2;
		private BigDecimal decimal;

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}

		public int getValue2() {
			return value2;
		}

		public void setValue2(int value2) {
			this.value2 = value2;
		}

		public BigDecimal getDecimal() {
			return decimal;
		}

		public void setDecimal(BigDecimal decimal) {
			this.decimal = decimal;
		}
	}

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
		
		final List<Integer> intList = new ArrayList<>();
		final List<Foo> fooList = new ArrayList<>();

		final int sum = Adhoc.sum(Foo::getValue)
			.from(fooList)
			.where(Foo::getDecimal).isEqualTo(BigDecimal.ONE)
			.get();
		
		assertThat(sum).isEqualTo(0);
		
		final int sumOfAll = Adhoc.sum(Foo::getValue).from(fooList).get();

		assertThat(sumOfAll).isEqualTo(0);
		
	}
	
}
