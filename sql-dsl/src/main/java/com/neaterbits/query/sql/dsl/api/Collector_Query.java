package com.neaterbits.query.sql.dsl.api;

import java.util.List;

import com.neaterbits.query.sql.dsl.api.entity.QueryMetaModel;

/**
 * Abstract-class passed along the way of query collection
 * for collecting the various bits and parts as walks though builders,
 * idea is this is the only instance that has to be passed along.
 * 
 * @author nhl
 *
 */

abstract class Collector_Query<MODEL> {

	// Initial query instance
	private final BaseQuery baseQuery;
	
	// Also knows how to compile query
	private final ModelCompiler<MODEL> modelCompiler;

	// Should always know result at beginning of query
	private final CollectedQueryResult result;

	//abstract void setResult(CollectedQueryResult result);
	
	abstract MappingCollector getMappings();
	
	abstract void setMappings(MappingCollector mappings);
	
	abstract Collector_Joins getJoins();
	
	abstract void setJoins(Collector_Joins joins);

	abstract void setSources(CollectedSelectSource sources);
	
	abstract CollectedSelectSource getSources();

	abstract void setClauses(Collector_Clause clauses);

	abstract Collector_Clause getClauses();

	abstract void setGroupBy(Collector_GroupBy<MODEL, ?> groupBy);
	
	abstract void setOrderBy(Collector_OrderBy<MODEL, ?> orderBy);
	
	abstract void setOrderBy(int [] resultColumns);
	
	abstract Collected_GroupBy getGroupBy();
	
	abstract Collected_OrderBy getOrderBy();
	
	Collector_Query(Collector_Query<MODEL> toCopy) {
		this.baseQuery = toCopy.baseQuery;
		this.modelCompiler = toCopy.modelCompiler;
		this.result = toCopy.result;
	}

	Collector_Query(Collector_Query<MODEL> toCopy, CollectedQueryResult result) {
		this.baseQuery = toCopy.baseQuery;
		this.modelCompiler = toCopy.modelCompiler;
		
		if (toCopy.result != null && result != null) {
			throw new IllegalArgumentException("Overriding result from copy, should only pass when existing result");
		}

		if (toCopy.result == null && result == null) {
			throw new IllegalArgumentException("Result neither from copy nor parameter");
		}
		
		// TODO: should perhaps always pass result in this constructor?
		this.result = result != null ? result : toCopy.result;
	}
	
	Collector_Query(BaseQuery baseQuery, ModelCompiler<MODEL> modelCompiler, CollectedQueryResult result) {
		
		if (baseQuery == null) {
			throw new IllegalArgumentException("baseQuery == null");
		}
		
		if (modelCompiler == null) {
			throw new IllegalArgumentException("modelCompiler == null");
		}

		this.baseQuery = baseQuery;
		this.modelCompiler = modelCompiler;
		this.result = result;
	}

	final EQueryStyle getQueryStyle() {
		return baseQuery.getQueryStyle();
	}
	
	final ModelCompiler<MODEL> getModelCompiler() {
		return modelCompiler;
	}
	
	final List<IAlias> getAllAliases() {
		return baseQuery.getAliases();
	}

	final QueryMetaModel getQueryMetaModel() {
		return baseQuery.getQueryMetaModel();
	}

	final Class<?> getResultType() {
		return result.getType();
	}
	
	final CollectedQueryResult getResult() {
		return result;
	}
	
}
