package com.neaterbits.query.sql.dsl.api;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.neaterbits.query.sql.dsl.api.entity.AttributeType;
import com.neaterbits.query.sql.dsl.api.entity.IEntity;
import com.neaterbits.query.sql.dsl.api.entity.IEntityAttribute;

/**
 * JPA base data source, ie. JPA annotated entities but skips JPA and generates native ANSI SQL instead
 *
 */

public final class QueryDataSourceJPANative extends QueryDataSourceJPA {

	public QueryDataSourceJPANative(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	PreparedQueryBuilder createBuilder() {
		return new PreparedQueryBuilderANSISQL<>(getEntityModelUtil(), em);
	}
	
	@Override
	final <QUERY> PreparedQuery_DB<QUERY, javax.persistence.Query> makeCompletePreparedQuery(ExecutableQuery<QUERY> q, QUERY query, QueryParametersDistinct distinctParams, PreparedQueryBuilder sb) {
		final String ansiSQL = sb.getQueryAsString();
		
		System.out.println("## native:\n" + ansiSQL);
		
		final javax.persistence.Query jpaQuery = createJPAQuery(ansiSQL);

		return new PreparedQuery_JPA_Complete_Native<QUERY>(this, q, query, distinctParams, jpaQuery);
	}
	
	
	@Override
	final <QUERY> PreparedQuery_DB<QUERY, Query> makeHalfwayPreparedQuery(ExecutableQuery<QUERY> queryAccess, QUERY query,
			QueryParametersDistinct distinctParams, PreparedQueryBuilder base, PreparedQueryConditionsBuilder conditions) {
		
		return new PreparedQuery_JPA_Halfway_Native<QUERY>(this, queryAccess, query, distinctParams, base, (PreparedQueryConditionsBuilderORM)conditions);
	}
	
	/*
	mapMultipleEntities) {
		todo map a list of entities to avoid looking up model information every time
	}
	*/
	
	
	@Override
	Query createJPAQuery(String queryString) {
		return em.createNativeQuery(queryString);
	}

	static void forEachResultColumn(IEntity entity, EntityEachAttribute each) {
		int idx = 0;
		
		// Get all attributes in order
		for (IEntityAttribute attr : entity.getAttributes()) {
			
			if (attr.getType() == AttributeType.RELATION) {
				// Skip all relational attributes
				continue;
			}
			
			if (attr.getType() != AttributeType.SCALAR) {
				throw new IllegalStateException("Support non-scalar attribute " + attr.getType() + " for "+ attr.getName() + " of  " + entity.getName());
			}

			final String [] columns = attr.getColumns();
			
			if (columns.length != 1) {
				throw new IllegalArgumentException("columns.length != 1");
			}
			
			each.each(attr, columns[0], idx);
			
			/*
			
			for (String column : attr.getColumns()) {
				each.each(attr, column, idx);
				
				++ idx;
			}
			*/
			
			++ idx;
		}
	}
	
	
	@Override
	<QUERY> Object mapSingleEntity(ExecutableQuery<QUERY> q, QUERY query, Object input) {
		
		// Must map back an array of attrs in order

		final Object [] rows = (Object[])input;
		
		// Map to entity
		final IEntity entity = getResultEntity(q, query);

		return mapOneEntity(entity, rows);
	}
	
	
	@Override
	<QUERY> List<Object> mapMultipleEntitities(ExecutableQuery<QUERY> q, QUERY query, List<?> input) {

		// Map to entity
		final IEntity entity = getResultEntity(q, query);
		
		final List<Object> ret = new ArrayList<>(input.size());
		
		for (Object o : input) {
			final Object [] row = (Object[])o;
			
			final Object mapped = mapOneEntity(entity, row);
			
			ret.add(mapped);
		}
		
		return ret;
	}
	
	private Object mapOneEntity(IEntity entity, Object [] row) {
		final Object instance;
		try {
			instance = entity.getJavaType().newInstance();
		} catch (InstantiationException | IllegalAccessException ex) {
			throw new IllegalStateException("Failed to instantiate result entity of type " + entity.getJavaType(), ex);
		}
		
		
		// TODO: Should cache setters methods or lambdas in query indexed on rows so no need for reflection lookup for each instance

		forEachResultColumn(entity, (attr, column , idx) -> {
			attr.set(instance, row[idx]);
			
		});
		
		return instance;
	}

	private <QUERY> IEntity getResultEntity(ExecutableQuery<QUERY> q, QUERY query) {
		final Class<?> resultType = q.getResultJavaType(query);

		final IEntity entity = getEntityModelUtil().getEntityInfo(resultType);
		
		if (entity == null) {
			throw new IllegalStateException("No entity for type " + resultType);
		}
		
		return entity;
	}

	@Override
	protected QueryDialect_SQL getDialect() {
		return new QueryDialect_ANSI_SQL<>(getEntityModelUtil());
	}
}

