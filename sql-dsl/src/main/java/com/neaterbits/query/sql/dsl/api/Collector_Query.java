package com.neaterbits.query.sql.dsl.api;

/**
 * Abstract-class passed along the way of query collection
 * for collecting the various bits and parts as walks though builders,
 * idea is this is the only instance that has to be passed along.
 * 
 * @author nhl
 *
 */

abstract class Collector_Query<MODEL> {
	
	private final EQueryStyle queryStyle;
	private final ModelCompiler<MODEL> modelCompiler;

	abstract CollectedQueryResult getResult();
	
	abstract void setResult(CollectedQueryResult result);
	
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
	
	
	Collector_Query(EQueryStyle queryStyle, ModelCompiler<MODEL> modelCompiler) {
		
		if (queryStyle == null) {
			throw new IllegalArgumentException("queryStyle == null");
		}
		
		if (modelCompiler == null) {
			throw new IllegalArgumentException("modelCompiler == null");
		}
		

		this.queryStyle = queryStyle;
		this.modelCompiler = modelCompiler;
	}

	final EQueryStyle getQueryStyle() {
		return queryStyle;
	}

	final ModelCompiler<MODEL> getModelCompiler() {
		return modelCompiler;
	}
}
