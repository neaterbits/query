package com.neaterbits.query.sql.dsl.api;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class SQLAdhocAPITest {

	private static class Foo {
		private int value;

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
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
		
		Adhoc.sum(Foo::getValue)
			.from(fooList)
			.where(Bar::getBaz).isEqualTo(1);
		
	}
	
}
