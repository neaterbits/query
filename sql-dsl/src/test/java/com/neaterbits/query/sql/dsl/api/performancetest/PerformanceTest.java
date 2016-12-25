package com.neaterbits.query.sql.dsl.api.performancetest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.junit.Test;

import static com.neaterbits.query.sql.dsl.api.Adhoc.max;
import static com.neaterbits.query.sql.dsl.api.Adhoc.maxInstance;
import static com.neaterbits.query.sql.dsl.api.Adhoc.sum;
import static com.neaterbits.query.sql.dsl.api.Adhoc.list;

import com.neaterbits.query.sql.dsl.api.Foo;

public class PerformanceTest {
	
	@Test
	public void testPerformance() {

		System.out.println("Max test");
		checkPerformance(
				testData -> max(Foo::getValue).from(testData).get(),
				
				testData -> testData.stream()
				.max((f1, f2) -> Integer.compare(f1.getValue(), f2.getValue()))
				.get()
				.getValue()
		);
		

		System.out.println("Max instance test");
		checkPerformance(
				testData -> maxInstance(Foo::getValue).from(testData).get(),
				testData -> testData.stream()
				.max((f1, f2) -> Integer.compare(f1.getValue(), f2.getValue()))
				.get()
		);
		
		// TODO: Max test with instance
		
		System.out.println("Sum test");

		checkPerformance(
				testData -> sum(Foo::getValue).from(testData).get(),
				
				testData -> testData.stream()
				.mapToInt(Foo::getValue)
				.reduce((int1, int2) -> int1 + int2)
				.getAsInt()
		);

		System.out.println("Sum test with filter ");
	
		checkPerformance(
				testData -> sum(Foo::getValue)
					.from(testData)
					.where(Foo::getValue).isLesserThan(50)
					.get(),

				testData -> testData.stream()
					.mapToInt(Foo::getValue)
					.filter(i -> i < 50)
					.reduce((int1, int2) -> int1 + int2)
					.getAsInt()
		);

		System.out.println("Collection test ");
		
		checkPerformance(
				testData ->list(testData).where(Foo::getValue).isLesserThan(50).get(),
				
				testData -> testData.stream().filter(f -> f.getValue() < 50).collect(Collectors.toList())
				
		);
	}
	
	private <T> void checkPerformance(
			Function<List<Foo>, T> selectBased,
			Function<List<Foo>, T> streamBased) {

		//final int num = 10000;
		final int innerLoopIterations = 10 * 10 * 1000 * 1000;
		//final int innerLoopIterations = 10000;
		
		
		for (int i = 4; i > 0; -- i) {
			int numElements = 1;
			
			for (int j = 0; j < i; ++ j) {
				numElements *= 10;
			}
			
			System.out.println("i " + i + " num " + numElements);

			final int iterations = innerLoopIterations / numElements;
			
			checkPerformance(numElements, iterations, selectBased, streamBased);
		}
	}
	
	

	private <T> void checkPerformance(int listLength, int iterations,
				Function<List<Foo>, T> selectBased,
				Function<List<Foo>, T> streamBased) {
		
		final long millis = System.currentTimeMillis();
		
		final List<Foo> testData = getTestData(listLength);
		
		Object r = null;
		for (int iteration = 0; iteration < iterations; ++ iteration) {
			Object result1 =
					selectBased.apply(testData);
					// Adhoc.max(Foo::getValue).from(testData).get();
			
			if (r != null && !r.equals(result1)) {
				throw new IllegalArgumentException("Unknown result"); 
			}
			r = result1;
		}
		
		System.out.println("Select based on length " + listLength + ", iterations " + iterations + ": " + (System.currentTimeMillis() - millis));

		Object result2 = null;

		for (int iteration = 0; iteration < iterations; ++ iteration) {
			
			result2 = streamBased.apply(testData);
					
					/*
					testData.stream()
					.max((f1, f2) -> Integer.compare(f1.getValue(), f2.getValue()))
					.get()
					.getValue();
					*/
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
