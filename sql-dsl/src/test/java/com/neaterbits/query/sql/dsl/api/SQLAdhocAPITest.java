package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SQLAdhocAPITest {

	private static class Foo {
		
		
		public Foo(int value, int value2, BigDecimal decimal) {
			this.value = value;
			this.value2 = value2;
			this.decimal = decimal;
		}

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
	public void testPerformance() {

		for (int i = 4; i > 0; -- i) {
			int numElements = 1;
			
			for (int j = 0; j < i; ++ j) {
				numElements *= 10;
			}
			
			System.out.println("i " + i + " num " + numElements);

			testPerformance(numElements, 10000);
		}
	}
	
	

	private void testPerformance(int listLength, int iterations) {
		
		final long millis = System.currentTimeMillis();
		
		final List<Foo> testData = getTestData(listLength);
		
		Object r = null;
		for (int iteration = 0; iteration < iterations; ++ iteration) {
			Object result1 = Adhoc.max(Foo::getValue).from(testData).get();
			
			if (r != null && !r.equals(result1)) {
				throw new IllegalArgumentException("Unknown result"); 
			}
			r = result1;
		}

		System.out.println("Select based on length " + listLength + ", iterations " + iterations + ": " + (System.currentTimeMillis() - millis));

		Object result2 = null;

		for (int iteration = 0; iteration < iterations; ++ iteration) {
			result2 = testData.stream()
					.max((f1, f2) -> Integer.compare(f1.getValue(), f2.getValue()))
					.get()
					.getValue();
		}

		System.out.println("Stream based on length " + listLength
						+ ", iterations " + iterations + ": "
						+ (System.currentTimeMillis() - millis));

		if (!r.equals(result2)) {
			throw new IllegalStateException("result mismatch: " + r + "/" + result2 + "(" + r.getClass().getName() + "/" + result2.getClass().getName());
		}

		System.out.println(" ");
	}
	
	
	private static List<Foo> getTestData(int length) {
		final Random r = new Random(System.currentTimeMillis());
		
		
		final List<Foo> ret = new ArrayList<>(length);
		
		for (int i = 0; i < length; ++ i) {
			
			final int val1 = r.nextInt(100);
			final int val2 = r.nextInt(100);
			final int val3 = r.nextInt(100);
			final int val4 = r.nextInt(100);
			
			final Foo foo = new Foo(val1, val2, new BigDecimal(String.valueOf(val3) + "." + String.valueOf(val4)));
			
			ret.add(foo);
		}
		

		return ret;
	}
}
