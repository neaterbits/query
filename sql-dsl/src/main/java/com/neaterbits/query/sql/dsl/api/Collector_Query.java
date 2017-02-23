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
	private final ModelCompiler<MODEL> modelCompiler;

	abstract CollectedQueryResult getResult();
	
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
	
	
	Collector_Query(ModelCompiler<MODEL> modelCompiler) {
		if (modelCompiler == null) {
			throw new IllegalArgumentException("modelCompiler == null");
		}

		this.modelCompiler = modelCompiler;
	}


	final ModelCompiler<MODEL> getModelCompiler() {
		return modelCompiler;
	}
}
