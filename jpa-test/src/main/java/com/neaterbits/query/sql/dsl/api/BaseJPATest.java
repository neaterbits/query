package com.neaterbits.query.sql.dsl.api;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.neaterbits.query.sql.dsl.api.BaseSQLAPITest;
import com.neaterbits.query.sql.dsl.api.IShortPrepared;
import com.neaterbits.query.sql.dsl.api.JPADataConfig;
import com.neaterbits.query.sql.dsl.api.JPADataConfigJPQL;
import com.neaterbits.query.sql.dsl.api.JPADataConfigNative;
import com.neaterbits.query.sql.dsl.api.JPAQueryMetaModel;
import com.neaterbits.query.sql.dsl.api.QueryTestDSBuilder;
import com.neaterbits.query.sql.dsl.api.QueryTestDSCheck;
import com.neaterbits.query.sql.dsl.api.QueryTestDSCombined;
import com.neaterbits.query.sql.dsl.api.QueryTestDSInMemory;
import com.neaterbits.query.sql.dsl.api.QueryTestDSJPA;
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

	protected static <T> List<T> expected(@SuppressWarnings("unchecked") T ... elements) {
    	return Arrays.asList(elements);
    }

	
	protected static final QueryTestDSCheck store(Object ...objects) {
		return store(s -> {
			for (Object o : objects) {
				s.add(o);
			}
		});
	}

	
	protected static final QueryTestDSCheck store(Consumer<QueryTestDSBuilder> b) {
		return new QueryTestDSCombined()
				
				.add(jpqlDSDerby)
				.add(nativeDSDerby)
				.add(jpqlDShsqldb)
				.add(nativeDShsqldb)
				
				// TODO: test support for in-memory later
				//.add(inMemory)
				
				.store(b);
	}
}
