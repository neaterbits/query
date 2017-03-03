package com.neaterbits.query.sql.dsl.api;

import static com.neaterbits.query.sql.dsl.api.IShortSelect.oneOrNull;
import static com.neaterbits.query.sql.dsl.api.IShortSelect.one;
import static com.neaterbits.query.sql.dsl.api.IShortSelect.list;

import java.util.function.Consumer;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

import com.neaterbits.query.jpatest.model.Company;
import com.neaterbits.query.sql.dsl.api.entity.QueryMetaModel;

public class ShortAPITest extends BaseSQLAPITest {
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("query-jpa-test");

	private static final QueryMetaModel jpaQueryMetaModel = new JPAQueryMetaModel(emf.getMetamodel());

	private static final QueryTestDSStore nativeDS = new QueryTestDSJPANative("query-jpa-test");
	private static final QueryTestDSStore jpql = new QueryTestDSJPQL("query-jpa-test");
	private static final QueryTestDSStore inMemory = new QueryTestDSInMemory(jpaQueryMetaModel);
	
	private static final ShortSelect select = com.neaterbits.query.sql.dsl.api.IShortSelect.get();
	
	private static final QueryDataSource jpqlDS = jpql.getDataSource();
	
	
	private static QueryTestDSCheck store(Consumer<QueryTestDSBuilder> b) {
		
		
		return new QueryTestDSCombined()
				
				//.add(nativeDS)
				.add(jpql)
				
				//.add(inMemory)
				
				.store(b);
	}

	private static final IShortPrepared prepared = IShortPrepared.get(jpqlDS);

	private static final SinglePrepared<Company>
			acmeQuery = prepared
					.one(Company.class)
					.where(Company::getName).startsWith("Acme")
					
					.compile();

	private static final SinglePrepared<Company>
			acmeQuery2 = 
			 one(Company.class)
			.where(Company::getName).startsWith("Acme")
			
			.compile().prepare(jpqlDS);
	
	@Test
    public void testPrepared() {
		
		final Company c =  acmeQuery.execute();
		
		
		assertThat(c).isNull();
		
	}
	
	@Test
    public void testNameBasedMapped() {

		final Company acme = new Company(-1, "Acme");
		final Company foo = new Company(-1, "Foo");

        final SingleCompiled<CompanyResultVO> startsWithAc =
        		oneOrNull(CompanyResultVO.class)

        	.map(Company::getName).to(CompanyResultVO::setName)

        	.where(Company::getName).startsWith("Ac")
        	.  and(Company::getName).endsWith("cme")

        	.compile();
		
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

        final SingleCompiled<Company> startsWithAc =
        		oneOrNull(Company.class)

        	.where(Company::getName).startsWith("Ac")
        	.  and(Company::getName).endsWith("cme")

        	.compile();
		
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

        final MultiCompiled<Company> startsWithAc = list(Company.class).compile();
		
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

        final MultiCompiled<Company> startsWithAc =

        			 select.list(Company.class)
        			.orderBy(Company::getName)
        			.desc()
        			.compile();
		
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
