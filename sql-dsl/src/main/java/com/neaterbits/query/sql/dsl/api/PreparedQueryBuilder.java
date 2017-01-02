package com.neaterbits.query.sql.dsl.api;

import java.util.List;

abstract class PreparedQueryBuilder {

	abstract void select();
	
	abstract void addEntityResult(FieldReferenceType fieldReferenceType, SourceReference sourceReference);
	
	abstract void addAggregateResult(EAggregateFunction function, FieldReference field);

	abstract void addMappings(List<FieldReference> references);

	abstract void addSelectSources(FieldReferenceType fieldReferenceType, List<SourceReference> references);
	
	abstract void addOneToManyJoin(String entityAliasName, String collectionAttrName, String joinVarName);
	
	abstract void appendJoinStatement(EJoinType joinType);
	
	abstract void resolveFromParams(PreparedQueryConditionsBuilder conditionsBuilder, ParamValueResolver paramValueResolver);
	
	abstract String getQueryAsString();
}

