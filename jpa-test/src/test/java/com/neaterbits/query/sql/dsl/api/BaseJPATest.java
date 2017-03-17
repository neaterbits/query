package com.neaterbits.query.sql.dsl.api;

import java.util.function.Consumer;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.neaterbits.query.sql.dsl.api.entity.QueryMetaModel;

public abstract class BaseJPATest extends BaseSQLAPITest {
	private static final String derbyPpersistenceUnitName = "query-jpa-test-derby";
	
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory(derbyPpersistenceUnitName);

	private static final QueryMetaModel jpaQueryMetaModel = new JPAQueryMetaModel(emf.getMetamodel());

	private static final JPADataConfig nativeJPA = new JPADataConfigNative(derbyPpersistenceUnitName);
	protected static final JPADataConfig jpqlJPA = new JPADataConfigJPQL(derbyPpersistenceUnitName);
	
	private static final QueryTestDSJPA nativeDS = new QueryTestDSJPA(nativeJPA);
	private static final QueryTestDSJPA jpqlDS = new QueryTestDSJPA(jpqlJPA);
	private static final QueryTestDSInMemory inMemory = new QueryTestDSInMemory(jpaQueryMetaModel);
	
	protected static final IShortPrepared prepared = IShortPrepared.get(jpqlJPA);
	
	protected static final QueryTestDSCheck store(Consumer<QueryTestDSBuilder> b) {
		return new QueryTestDSCombined()
				
				.add(jpqlDS)
				.add(nativeDS)
				.add(inMemory)
				
				.store(b);
	}
}
