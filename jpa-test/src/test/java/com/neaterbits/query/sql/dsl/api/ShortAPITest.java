package com.neaterbits.query.sql.dsl.api;

import java.util.function.Consumer;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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

	
	
	
}
