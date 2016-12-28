package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SQLAdhocAPITest {

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

		assertThat(foos.getClass()).isEqualTo(ArrayList.class);
	}


	@Test
	public void testAdhocListAs() {

		final List<Foo> fooList = new ArrayList<>();

		final Foo foo1 = new Foo(1, 2, new BigDecimal("3.1"));
		final Foo foo2 = new Foo(3, 1, new BigDecimal("4.8"));
		final Foo foo3 = new Foo(2, 7, new BigDecimal("1.8"));

		fooList.add(foo1);
		fooList.add(foo2);
		fooList.add(foo3);

		final LinkedList<Foo> foos = Adhoc.list(fooList)
							.where(Foo::getDecimal).isGreaterThan(new BigDecimal("2.0"))
							.as(LinkedList<Foo>::new);

		assertThat(foos.size()).isEqualTo(2);

		assertThat(foos.contains(foo1)).isTrue();
		assertThat(foos.contains(foo2)).isTrue();

		assertThat(foos.getClass()).isEqualTo(LinkedList.class);
	}

	@Test
	public void testAdhocSet() {

		final List<Foo> fooList = new ArrayList<>();

		final Foo foo1 = new Foo(1, 2, new BigDecimal("3.1"));
		final Foo foo2 = new Foo(3, 1, new BigDecimal("4.8"));
		final Foo foo3 = new Foo(2, 7, new BigDecimal("1.8"));
		final Foo foo4 = new Foo(2, 7, new BigDecimal("1.8"));

		fooList.add(foo1);
		fooList.add(foo2);
		fooList.add(foo3);
		fooList.add(foo4);

		final Set<Foo> foos = Adhoc.set(fooList)
							.where(Foo::getDecimal).isLessThan(new BigDecimal("2.0"))
							.get();

		checkFoos(foo3, foo4, foos, HashSet.class);


		final Comparator<Foo> fooComparator = (f1, f2) -> Integer.compare(f1.getValue(), f2.getValue());
		
		final TreeSet<Foo> foos2 = Adhoc.set(fooList)
				.where(Foo::getDecimal).isLessThan(new BigDecimal("2.0"))
				.as(coll ->  {
					final TreeSet<Foo> set = new TreeSet<Foo>(fooComparator);
					
					set.addAll(coll);
					
					return set;
				});

		checkFoos(foo3, foo4, foos2, TreeSet.class);
	}

	private void checkFoos(Foo foo3, Foo foo4, Set<Foo> foos, Class<?> expectedClass) {
		assertThat(foo3.hashCode()).isEqualTo(foo4.hashCode());
		assertThat(foo3).isEqualTo(foo4);
		
		assertThat(foos.getClass()).isEqualTo(expectedClass);
		
		assertThat(foos.size()).isEqualTo(1);

		assertThat(foos.contains(foo3)).isTrue();
		assertThat(foos.contains(foo4)).isTrue();
	}

	@Test
	public void testAdhocListAnd() {
		final List<Foo> fooList = new ArrayList<>();

		final Foo foo1 = new Foo(1, 2, new BigDecimal("3.1"));
		final Foo foo2 = new Foo(3, 1, new BigDecimal("4.8"));
		final Foo foo3 = new Foo(2, 7, new BigDecimal("1.8"));

		fooList.add(foo1);
		fooList.add(foo2);
		fooList.add(foo3);

		final List<Foo> foos = Adhoc.list(fooList)
							.where(Foo::getDecimal).isGreaterThan  ( new BigDecimal("2.0") )
 							  .and(Foo::getValue)  .isLessOrEqualTo( 1 )
 							.get();

		assertThat(foos.size()).isEqualTo(1);
		assertThat(foos.contains(foo1)).isTrue();
	}

	@Test
	public void testAdhocListOr() {
		final List<Foo> fooList = new ArrayList<>();

		final Foo foo1 = new Foo(1, 2, new BigDecimal("3.1"));
		final Foo foo2 = new Foo(3, 1, new BigDecimal("4.8"));
		final Foo foo3 = new Foo(2, 7, new BigDecimal("1.8"));

		fooList.add(foo1);
		fooList.add(foo2);
		fooList.add(foo3);

		final List<Foo> foos = Adhoc.list(fooList)
							.where(Foo::getDecimal).isGreaterThan(new BigDecimal("2.0"))
								.or(Foo::getValue).isLessOrEqualTo(1)
								.get();

		assertThat(foos.size()).isEqualTo(2);
		assertThat(foos.contains(foo1)).isTrue();
		assertThat(foos.contains(foo2)).isTrue();
	}

	@Test
	public void testAdhocListJoinWithOr() {
		final List<Foo> fooList = new ArrayList<>();

		final Foo foo1 = new Foo(1, 2, new BigDecimal("3.1"));
		final Foo foo2 = new Foo(3, 1, new BigDecimal("4.8"));
		final Foo foo3 = new Foo(2, 7, new BigDecimal("1.8"));

		fooList.add(foo1);
		fooList.add(foo2);
		fooList.add(foo3);
		
		final List<Bar> barList = new ArrayList<>();

		final Bar bar1 = new Bar(foo1.getValue(), "Foo1");
		final Bar bar2 = new Bar(foo2.getValue(), "Foo2");
		
		barList.add(bar1);
		barList.add(bar2);

		final List<Foo> foos = Adhoc.list(fooList)
				
							.innerJoin(barList, j -> j
										.compare(Foo::getValue, Bar::getFooId)
										.where(Bar::getBaz).startsWith("Foo"))

							.where(Foo::getDecimal).isGreaterThan(new BigDecimal("2.0"))
							   .or(Foo::getValue).isLessOrEqualTo(1)
							   .get();

		// Inner-join but not distinct
		assertThat(foos.contains(foo1)).isTrue();
		assertThat(foos.contains(foo2)).isTrue();
		assertThat(foos.size()).isEqualTo(4);
	}

	@Test
	public void testAdhocListJoinWithAnd() {
		final List<Foo> fooList = new ArrayList<>();

		final Foo foo1 = new Foo(1, 2, new BigDecimal("3.1"));
		final Foo foo2 = new Foo(3, 1, new BigDecimal("4.8"));
		final Foo foo3 = new Foo(2, 7, new BigDecimal("1.8"));

		fooList.add(foo1);
		fooList.add(foo2);
		fooList.add(foo3);
		
		final List<Bar> barList = new ArrayList<>();

		final Bar bar1 = new Bar(foo1.getValue(), "Foo1");
		final Bar bar2 = new Bar(foo2.getValue(), "Foo2");
		
		barList.add(bar1);
		barList.add(bar2);
		

		final List<Foo> foos = Adhoc.list(fooList)
				
							.innerJoin(barList, j -> j
										.compare(Foo::getValue, Bar::getFooId)
										.where(Bar::getBaz).startsWith("Foo"))
							
							.where(Foo::getDecimal).isGreaterThan(new BigDecimal("2.0"))
							   .and(Foo::getValue).isLessOrEqualTo(1)
							   .get();

		// Inner-join but not distinct
		assertThat(foos.size()).isEqualTo(1);
		assertThat(foos.contains(foo1)).isTrue();
	}
}

