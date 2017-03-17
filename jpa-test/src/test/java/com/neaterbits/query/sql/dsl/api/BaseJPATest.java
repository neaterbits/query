package com.neaterbits.query.sql.dsl.api;

import java.util.function.Consumer;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.neaterbits.query.sql.dsl.api.entity.QueryMetaModel;

public abstract class BaseJPATest extends BaseSQLAPITest {
	
	
	private static final String derbyPersistenceUnitName = "query-jpa-test-derby";
	private static final String hsqldbPersistenceUnitName = "query-jpa-test-hsqldb";
	
	
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory(derbyPersistenceUnitName);

	private static final QueryMetaModel jpaQueryMetaModel = new JPAQueryMetaModel(emf.getMetamodel());

	private static final JPADataConfig nativeJPADerby = new JPADataConfigNative(derbyPersistenceUnitName);
	protected static final JPADataConfig jpqlJPADerby = new JPADataConfigJPQL(derbyPersistenceUnitName);

	private static final QueryTestDSJPA nativeDSDerby = new QueryTestDSJPA(nativeJPADerby);
	private static final QueryTestDSJPA jpqlDSDerby = new QueryTestDSJPA(jpqlJPADerby);

	private static final JPADataConfig nativeJPAhsqldb = new JPADataConfigNative(hsqldbPersistenceUnitName);
	protected static final JPADataConfig jpqlJPAhsqldb = new JPADataConfigJPQL(hsqldbPersistenceUnitName);

	private static final QueryTestDSJPA nativeDShsqldb = new QueryTestDSJPA(nativeJPAhsqldb);
	private static final QueryTestDSJPA jpqlDShsqldb = new QueryTestDSJPA(jpqlJPAhsqldb);
	
	private static final QueryTestDSInMemory inMemory = new QueryTestDSInMemory(jpaQueryMetaModel);
	
	protected static final IShortPrepared preparedJPQLDerby = IShortPrepared.get(jpqlJPADerby);
	
	protected static final QueryTestDSCheck store(Consumer<QueryTestDSBuilder> b) {
		return new QueryTestDSCombined()
				
				/*
				.add(jpqlDSDerby)
				.add(nativeDSDerby)
				*/
				.add(jpqlDShsqldb)
				.add(nativeDShsqldb)
				
				.add(inMemory)
				
				.store(b);
	}
}
