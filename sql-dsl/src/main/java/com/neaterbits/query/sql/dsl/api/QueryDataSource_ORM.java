package com.neaterbits.query.sql.dsl.api;

import java.util.Collection;
import java.util.List;

import com.neaterbits.query.sql.dsl.api.PreparedQueryBuilderORM.JoinConditionId;
import com.neaterbits.query.sql.dsl.api.entity.EntityModel;
import com.neaterbits.query.sql.dsl.api.entity.EntityModelUtil;

abstract class QueryDataSource_ORM<MANAGED, EMBEDDED, IDENTIFIABLE, ATTRIBUTE, COLL extends Collection<ATTRIBUTE>> extends QueryDataSource_GenBase {

	private final EntityModel<MANAGED, EMBEDDED, IDENTIFIABLE, ATTRIBUTE, COLL> entityModel;
	private final EntityModelUtil<MANAGED, EMBEDDED, IDENTIFIABLE, ATTRIBUTE, COLL> entityModelUtil;
	
	
	abstract <QUERY> PreparedQuery_DB<QUERY> makeCompletePreparedQuery(ExecutableQuery<QUERY> q, QUERY query, QueryParametersDistinct distinctParams, PreparedQueryBuilder sb);
	
	abstract <QUERY> PreparedQuery_DB<QUERY> makeHalfwayPreparedQuery(ExecutableQuery<QUERY> queryAccess, QUERY query, QueryParametersDistinct distinctParams, PreparedQueryBuilder base, PreparedQueryConditionsBuilder conditions);

	
	QueryDataSource_ORM(EntityModelUtil<MANAGED, EMBEDDED, IDENTIFIABLE, ATTRIBUTE, COLL> modelUtil) {

		if (modelUtil == null) {
			throw new IllegalArgumentException("modelUtil == null");
		}
		
		this.entityModelUtil = modelUtil;
		this.entityModel = modelUtil.getModel();
	}
	
	final EntityModelUtil<MANAGED, EMBEDDED, IDENTIFIABLE, ATTRIBUTE, COLL> getEntityModelUtil() {
		return entityModelUtil;
	}

	@Override
	final <QUERY> PreparedQuery_DS<QueryDataSource_DB> prepare(PreparedQueryBuilder builder, QueryDialect_SQL dialect, ExecutableQuery<QUERY> q, QUERY query) {
	
		final PreparedQueryBuilderORM<MANAGED, EMBEDDED, IDENTIFIABLE, ATTRIBUTE, COLL> sb = (PreparedQueryBuilderORM)builder;

		final List<JoinConditionId> addJoinToWhere = sb.prepareInitial(q, query);
		
		// Prepare conditions if present
		final PreparedQuery_DB<QUERY> ret;

		final QueryParametersDistinct distinctParams = q.getDistinctParams(query);
		
		if (q.hasConditions(query)) {
			
			//final CompileConditionParam param = new CompileConditionParam(paramNameAssigner, em);

			final PreparedQueryConditionsBuilder conditionsBuilder = sb.createConditionsBuilder(dialect, EConditionsClause.WHERE, true, q.getQueryFieldAccessType(query));

			sb.prepareConditions(q, query, conditionsBuilder, addJoinToWhere, distinctParams);

			if (conditionsBuilder.hasUnresolved()) {
				ret = makeHalfwayPreparedQuery(q, query, distinctParams, sb, conditionsBuilder);
			}
			else {
				sb.resolveFromParams(conditionsBuilder, null);

				ret = makeCompleteQueryWithResultProcessing(q, query, distinctParams, sb);
			}
		}
		else {
			ret = makeCompleteQueryWithResultProcessing(q, query, distinctParams, sb);
		}

		return ret;
	}
	
	private <QUERY> PreparedQuery_DB<QUERY> makeCompleteQueryWithResultProcessing(ExecutableQuery<QUERY> q, QUERY query, QueryParametersDistinct distinctParams, PreparedQueryBuilderORM<MANAGED, EMBEDDED, IDENTIFIABLE, ATTRIBUTE, COLL> sb) {

		sb.addResultProcessing(q, query, distinctParams);

		return makeCompletePreparedQuery(q, query, distinctParams, sb);
	}
}

