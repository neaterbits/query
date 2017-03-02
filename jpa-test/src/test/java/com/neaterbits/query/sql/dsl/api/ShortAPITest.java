package com.neaterbits.query.sql.dsl.api;

import static com.neaterbits.query.sql.dsl.api.IShortSelect.oneOrNull;

import java.util.function.Consumer;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

import com.neaterbits.query.jpatest.model.Company;
import com.neaterbits.query.sql.dsl.api.entity.QueryMetaModel;
import com.neaterbits.query.sql.dsl.api.helper.jpa.QueryTestDSJPANative;
import com.neaterbits.query.sql.dsl.api.helper.jpa.QueryTestDSJPQL;
import com.neaterbits.query.sql.dsl.api.testhelper.BaseSQLAPITest;
import com.neaterbits.query.sql.dsl.api.testhelper.QueryTestDSBuilder;
import com.neaterbits.query.sql.dsl.api.testhelper.QueryTestDSCheck;
import com.neaterbits.query.sql.dsl.api.testhelper.QueryTestDSCombined;
import com.neaterbits.query.sql.dsl.api.testhelper.QueryTestDSInMemory;
import com.neaterbits.query.sql.dsl.api.testhelper.QueryTestDSStore;

public class ShortAPITest extends BaseSQLAPITest {
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("query-jpa-test");

	private static final QueryMetaModel jpaQueryMetaModel = new JPAQueryMetaModel(emf.getMetamodel());

	private static final QueryTestDSStore nativeDS = new QueryTestDSJPANative("query-jpa-test");
	private static final QueryTestDSStore jpql = new QueryTestDSJPQL("query-jpa-test");
	private static final QueryTestDSStore inMemory = new QueryTestDSInMemory(jpaQueryMetaModel);
	
	private static QueryTestDSCheck store(Consumer<QueryTestDSBuilder> b) {
		
		
		return new QueryTestDSCombined()
				
				//.add(nativeDS)
				.add(jpql)
				
				//.add(inMemory)
				
				.store(b);
	}

	
	@Test
    public void testNameBasedMapped() {

		final Company acme = new Company(-1, "Acme");
		final Company foo = new Company(-1, "Foo");

        final SingleQuery<CompanyResultVO> startsWithAc =
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

        final SingleQuery<Company> startsWithAc =
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
	
}
