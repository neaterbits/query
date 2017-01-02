package com.neaterbits.query.sql.dsl.api;

import java.util.List;

import com.neaterbits.query.sql.dsl.api.entity.Relation;

abstract class PreparedQueryBuilder {

	abstract void select();
	
	abstract void addEntityResult(FieldReferenceType fieldReferenceType, SourceReference sourceReference);
	
	abstract void addAggregateResult(EAggregateFunction function, FieldReference field);

	abstract void addMappings(List<FieldReference> references);

	abstract void addFromSelectSources(FieldReferenceType fieldReferenceType, List<SourceReference> references);
	
	abstract void addOneToManyJoin(Relation relation, FieldReferenceType fieldReferenceType, SourceReference from, SourceReference to);
	
	abstract void addComparisonJoin(List<JoinFieldComparison> fieldComparisons, SourceReference from, SourceReference to);
	
	// abstract void addOneToManyJoin(Relation relation, String entityAliasName, String collectionAttrName, String joinVarName);
	
	abstract void appendJoinStatement(EJoinType joinType);
	
	abstract void resolveFromParams(PreparedQueryConditionsBuilder conditionsBuilder, ParamValueResolver paramValueResolver);
	
	abstract String getQueryAsString();
}

