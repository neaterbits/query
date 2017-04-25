package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import com.neaterbits.query.sql.dsl.api.entity.AttributeType;
import com.neaterbits.query.sql.dsl.api.entity.IEntity;
import com.neaterbits.query.sql.dsl.api.entity.IEntityAttribute;
import com.neaterbits.query.sql.dsl.api.entity.IEntityFields;
import com.neaterbits.query.util.java8.Coll8;

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

	private final FunctionVisitor<Object, Object> resultConversionVisitor = new FunctionVisitor<Object, Object>() {
		
		@Override
		public Object onStringUpper(Function_String_Upper function, Object param) {
			return param;
		}
		
		@Override
		public Object onStringTrim(Function_String_Trim function, Object param) {
			return param;
		}
		
		@Override
		public Object onStringSubstring(Function_String_Substring function, Object param) {
			return param;
		}
		
		@Override
		public Object onStringLower(Function_String_Lower function, Object param) {
			return param;
		}
		
		@Override
		public Object onStringLength(Function_String_Length function, Object param) {
			
			// Native returns Long on some databases eg HSQLDB
			final Object ret;
			if (param instanceof Long) {
				final Long l = (Long)param;
				
				if (l.longValue() > Integer.MAX_VALUE) {
					throw new IllegalStateException("Casting return of length() from long > Integer.MAX_VALUE: " + l);
				}
				
				ret = l.intValue();
			}
			else {
				// Derby returns Integer
				ret = param;
			}
			
			return ret;
		}
		
		@Override
		public Object onStringConcat(Function_String_Concat function, Object param) {
			return param;
		}
		
		@Override
		public Object onArithmeticSqrt(Function_Arithmetic_Sqrt function, Object param) {
			return param;
		}
		
		@Override
		public Object onArithmeticMod(Function_Arithmetic_Mod function, Object param) {
			return param;
		}
		
		@Override
		public Object onArithmeticAbs(Function_Arithmetic_Abs function, Object param) {
			return param;
		}
		
		@Override
		public Object onAggregate(Function_Aggregate function, Object param) {
			
			final Object ret;
			
			switch (function.getFunction()) {
			case AVG:
				ret = convertAvgAggregateResult(Double.class, param);
				break;
				
			case COUNT:
				ret = convertCountAggregateResult(Long.class, param);
				break;
				
			default:
				ret = param;
				break;
			}
			
			return ret;
		}
	};
	
	@Override
	Object convertFunctionResultBeforeMapping(FunctionBase function, Object queryResult) {
		
		// Some functions have other return value types than JPQL when calling native SQL, so must convert
		return function.visit(resultConversionVisitor, queryResult);
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

	static void forEachInheritanceColumn(IEntity entity, EntityEachAttribute each) {
		int idx = 0;
		
		idx = forEachEntityColumn(entity, idx, each);
		
		if (entity.isBaseType()) {
			for (IEntityFields sub : entity.getSubEntitiesOrdered()) {
				idx = forEachEntityColumn(sub, idx, each);
			}
		}
	}

	
	static int forEachEntityColumn(IEntityFields entity, int idx, EntityEachAttribute each) {
		
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
			
			each.each(entity, attr, columns[0], idx);
			
			/*
			
			for (String column : attr.getColumns()) {
				each.each(attr, column, idx);
				
				++ idx;
			}
			*/
			
			++ idx;
		}
		
		return idx;
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
	
	private static Object instantiate(IEntityFields entity) {
		final Object instance;

		try {
			instance = entity.getJavaType().newInstance();
		} catch (InstantiationException | IllegalAccessException ex) {
			throw new IllegalStateException("Failed to instantiate result entity of type " + entity.getJavaType(), ex);
		}

		return instance;
	}
	
	private Object mapOneEntity(IEntity entity, Object [] row) {

		final Object instance;
		
		if (entity.isBaseType()) {
			// We were selecting a base type and should do polymorphic result creation from selected columns
			
			switch (entity.getSubClassing()) {
			case SINGLE_TABLE:
				// We selected attributes from all rows in order
				
				// Map back inherited, must figure type to instantiate
				
				final String type = (String)row[row.length - 1];
				
				final IEntityFields sub = Coll8.find(entity.getSubEntitiesOrdered(), e -> e.getName().equals(type));
				
				if (sub == null) {
					throw new IllegalStateException("No sub for inherited typed " + type);
				}

				instance = instantiate(sub);

				forEachInheritanceColumn(entity, (e, attr, column, idx) -> {

					if (e.getJavaType().isAssignableFrom(sub.getJavaType())) {
						attr.set(instance, row[idx]);
					}
				});
				break;


			default:
				throw new UnsupportedOperationException("Unknown subclassing type " + entity.getSubClassing());
			}
			
		}
		else {
			// Not a basetype, straightforward
			instance = instantiate(entity);
			
			// TODO: Should cache setters methods or lambdas in query indexed on rows so no need for reflection lookup for each instance

			forEachEntityColumn(entity, 0, (e, attr, column, idx) -> {
				attr.set(instance, row[idx]);
				
			});
		}
		
		
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
		return new QueryDialect_ANSI_SQL(getEntityModelUtil());
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
			throw new IllegalStateException("Neither Integer nor Long: " + input.getClass());
		}

		return ret;
	}
}

