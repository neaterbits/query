package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

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
		return new PreparedQueryBuilderORM<>(getEntityModelUtil(), getDialect());
	}
	
	@Override
	final <QUERY> PreparedQuery_DB<QUERY> makeCompletePreparedQuery(ExecutableQuery<QUERY> q, QUERY query, QueryParametersDistinct distinctParams, PreparedQueryBuilder sb) {
		final String ansiSQL = sb.getQueryAsString();
		
		System.out.println("## native:\n" + ansiSQL);
		
		final QueryRunner_JPA queryRunner = createQueryRunner(ansiSQL);

		return new PreparedQuery_DB_Complete<QUERY>(this, queryRunner, q, query, distinctParams);
	}
	
	
	@Override
	final <QUERY> PreparedQuery_DB<QUERY> makeHalfwayPreparedQuery(ExecutableQuery<QUERY> queryAccess, QUERY query,
			QueryParametersDistinct distinctParams, PreparedQueryBuilder base, PreparedQueryConditionsBuilder conditions) {
		
		return new PreparedQuery_DB_Halfway<QUERY>(this, queryAccess, query, distinctParams, base, (PreparedQueryConditionsBuilderORM)conditions);
	}
	
	/*
	mapMultipleEntities) {
		todo map a list of entities to avoid looking up model information every time
	}
	*/
	
	
	@Override
	QueryRunner_JPA createQueryRunner(String queryString) {
		
		final javax.persistence.Query jpaQuery = em.createNativeQuery(queryString);
		
		return new QueryRunner_JPA_Native(jpaQuery);
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

	@Override
	Object convertAvgAggregateResult(Class<?> aggregateResultType, Object input) {
		// TODO hack to work around native returning BigDecimal
		
		if (!aggregateResultType.equals(Double.class)) {
			throw new IllegalStateException("Expected Double");
		}

		final Object ret;

		if (input instanceof Double) {
			ret = input;
		}
		else if (input instanceof BigDecimal) {
			
			final BigDecimal decimal = (BigDecimal)input;
			
			ret = decimal.doubleValue();
		}
		else {
			throw new IllegalStateException("Neither Double nor BigDecimal");
		}
		
		
		return ret;
	}
	
	@Override
	Object convertCountAggregateResult(Class<?> aggregateResultType, Object input) {
		// TODO hack to work around native returning Integer
		
		if (!aggregateResultType.equals(Long.class)) {
			throw new IllegalStateException("Expected Long");
		}

		final Object ret;

		if (input instanceof Integer) {
			ret = new Long((Integer)input);
		}
		else if (input instanceof Long) {
			ret = input;
		}
		else {
			throw new IllegalStateException("Neither Integer nor Long");
		}

		return ret;
	}

	@Override
	Object convertUnknownAggregateResult(Class<?> aggregateResultType, Object input) {
		if (!aggregateResultType.equals(Date.class)) {
			throw new IllegalStateException("Expected Date");
		}

		final Object ret;

		if (input instanceof java.sql.Timestamp) {
			ret = new Date(((java.sql.Timestamp)input).getTime());
		}
		/*
		else if (input instanceof Long) {
			ret = input;
		}
		*/
		else {
			throw new IllegalStateException("Neither Integer nor Long");
		}

		return ret;
	}
}

