package com.neaterbits.query.sql.dsl.api;

import static com.neaterbits.query.sql.dsl.api.IShortSelect.oneOrNull;
import static com.neaterbits.query.sql.dsl.api.IShortSelect.one;
import static com.neaterbits.query.sql.dsl.api.IShortSelect.list;

import java.math.BigDecimal;
import java.util.function.Consumer;
import java.util.function.Function;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

import com.neaterbits.query.jpatest.model.Company;
import com.neaterbits.query.sql.dsl.api.entity.QueryMetaModel;

public class ShortAPITest extends BaseSQLAPITest {
	
	private static final String persistenceUnitName = "query-jpa-test";
	
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnitName);

	private static final QueryMetaModel jpaQueryMetaModel = new JPAQueryMetaModel(emf.getMetamodel());

	private static final JPADataConfig nativeJPA = new JPADataConfigNative(persistenceUnitName);
	private static final JPADataConfig jpqlJPA = new JPADataConfigJPQL(persistenceUnitName);
	
	private static final QueryTestDSJPA nativeDS = new QueryTestDSJPA(nativeJPA);
	private static final QueryTestDSJPA jpqlDS = new QueryTestDSJPA(jpqlJPA);
	private static final QueryTestDSInMemory inMemory = new QueryTestDSInMemory(jpaQueryMetaModel);
	
	private static final ShortSelect select = com.neaterbits.query.sql.dsl.api.IShortSelect.get();
	
	//private static final QueryDataSource jpqlDS = jpql.getDataSource();
	
	
	private static QueryTestDSCheck store(Consumer<QueryTestDSBuilder> b) {
		
		
		return new QueryTestDSCombined()
				
				//.add(nativeDS)
				.add(jpqlDS)
				
				//.add(inMemory)
				
				.store(b);
	}

	private static final IShortPrepared prepared = IShortPrepared.get(jpqlJPA);
	/*
	private static final SinglePrepared<Company>
			acmeQuery2 = 
			 one(Company.class)
			.where(Company::getName).startsWith("Acme")
			
			.compile().prepare(jpqlJPA);
	*/
	

	@Test
    public void testLambdaIdentities() {
		
		final Function<Company, String> lambda1 = Company::getName;
		final Function<Company, String> lambda2 = Company::getName;
		
		System.out.format("Lambda 1: %08x\n", System.identityHashCode(lambda1));
		System.out.format("Lambda 2: %08x\n", System.identityHashCode(lambda2));
		
		System.out.println("equals: " + lambda1.equals(lambda2));
		System.out.println("hashCode: " + lambda1.hashCode() + "/" + lambda2.hashCode());
	}
	
	/*
	@Test
    public void testPrepared() {
		
		final Company c =  acmeQuery.execute();
		
		
		assertThat(c).isNull();
		
	}
	*/

	static class CompanyResultsVO {
		private String name;
		private BigDecimal avgStockPrice;
		private BigDecimal sumStockPrice;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public BigDecimal getAvgStockPrice() {
			return avgStockPrice;
		}

		public void setAvgStockPrice(BigDecimal avgStockPrice) {
			this.avgStockPrice = avgStockPrice;
		}

		public BigDecimal getSumStockPrice() {
			return sumStockPrice;
		}

		public void setSumStockPrice(BigDecimal sumStockPrice) {
			this.sumStockPrice = sumStockPrice;
		}
	}
	
	@Test
    public void testMapSum() {
		final SinglePrepared<CompanyResultsVO> acmeQuery = prepared
				.one(CompanyResultsVO.class)

				.map().lower(Company::getName).to(CompanyResultsVO::setName)
				.map().sum(Company::getStockPrice).to(CompanyResultsVO::setSumStockPrice)

				// TODO: sqrt().avg()
				
				.map().avg(Company::getStockPrice).to(CompanyResultsVO::setAvgStockPrice)
				
				.where(Company::getName).startsWith("Acme")
				
				.build();
		
		
	}
	
	@Test
    public void testPrepared() {
		final SinglePrepared<Company> acmeQuery = prepared
				.one(Company.class)
				.where(Company::getName).startsWith("Acme")
				
				.build();
		
		
	}

	@Test
    public void testNonPreparedAggregate() {

		final SinglePrepared<BigDecimal> prepared = select.sum(Company::getStockPrice).build().prepare(jpqlJPA);

		final BigDecimal value = prepared.execute();

		assertThat(value).isNotNull();
	}
	
	
	@Test
    public void testPreparedAggregate() {
		final BigDecimal ret = prepared.sum(Company::getStockPrice).build().execute();
	}
	
	@Test
    public void testNameBasedMapped() {

		final Company acme = new Company(-1, "Acme");
		final Company foo = new Company(-1, "Foo");

        final SingleBuilt<CompanyResultVO> startsWithAc =
        		oneOrNull(CompanyResultVO.class)

        	.map(Company::getName).to(CompanyResultVO::setName)

        	.where(Company::getName).startsWith("Ac")
        	.  and(Company::getName).endsWith("cme")

        	.build();
		
		store(s  -> s.add(acme)).
		check(ds -> {
	        checkSelectOneOrNull(
	        		ds,
	        		new CompanyResultVO(acme.getName()),
	        		startsWithAc,
	        		q -> q.execute());
		});

		// Search for foo as well, should return no matches
		store(s  -> s.add(foo)).
		check(ds -> {
	        checkSelectOneOrNull(
	        		ds,
	        		null,
	        		startsWithAc,
	        		q -> q.execute());
		});
	}
	
	@Test
    public void testSingleNameBasedEntity() {
		
		final Company acme = new Company(-1, "Acme");
		final Company foo = new Company(-1, "Foo");

        final SingleBuilt<Company> startsWithAc =
        		oneOrNull(Company.class)

        	.where(Company::getName).startsWith("Ac")
        	.  and(Company::getName).endsWith("cme")

        	.build();
		
		store(s  -> s.add(acme)).
		check(ds -> {
	        checkSelectOneOrNull(
	        		ds,
	        		acme,
	        		startsWithAc,
	        		q -> q.execute());
		});

		// Search for foo as well, should return no matches
		store(s  -> s.add(foo)).
		check(ds -> {
	        checkSelectOneOrNull(
	        		ds,
	        		null,
	        		startsWithAc,
	        		q -> q.execute());
		});
	}
	
	@Test
    public void testListAllEntities() {
		
		final Company acme = new Company(-1, "Acme");
		final Company foo = new Company(-1, "Foo");

        final MultiBuilt<Company> startsWithAc = list(Company.class).build();
		
		store(s  -> s.add(acme)).
		check(ds -> {
	        checkSelectListUnordered(
	        		ds,
	        		startsWithAc,
	        		q -> q.execute(),
	        		acme,
	        		foo);
		});

		// Search for foo as well, should return no matches
		store(s  -> s.add(foo)).
		check(ds -> {
	        checkSelectListUnordered(
	        		ds,
	        		startsWithAc,
	        		q -> q.execute(),
	        		foo);
		});
	}

	@Test
    public void testListAllOrderByEntities() {
		
		final Company acme = new Company(-1, "Acme");
		final Company foo = new Company(-1, "Foo");

        final MultiBuilt<Company> startsWithAc =

        			 select.list(Company.class)
        			.orderBy(Company::getName)
        			.desc()
        			.build();
		
		store(s  -> s.add(acme)).
		check(ds -> {
	        checkSelectListUnordered(
	        		ds,
	        		startsWithAc,
	        		q -> q.execute(),
	        		acme,
	        		foo);
		});

		// Search for foo as well, should return no matches
		store(s  -> s.add(foo)).
		check(ds -> {
	        checkSelectListUnordered(
	        		ds,
	        		startsWithAc,
	        		q -> q.execute(),
	        		foo);
		});
	}
	
}
