package com.neaterbits.query.sql.dsl.api;

final class CompiledResultFields {

	// Should always have indices
	private int [] indicesStartingAtOne;
	private FunctionGetter [] optionalGetters; // in case we specified rows by getter
	
	
	CompiledResultFields(int [] indicesStartingAtOne, FunctionGetter [] optionalGetters) {
		
		if (indicesStartingAtOne == null) {
			throw new IllegalArgumentException("indicesStartingAtOne == null");
		}
		
		if (indicesStartingAtOne.length == 0) {
			throw new IllegalArgumentException("no indices");
		}
		
		if (optionalGetters != null && optionalGetters.length != indicesStartingAtOne.length) {
			throw new IllegalArgumentException("mismatch between optionalGetters and indices");
		}
		
		this.indicesStartingAtOne = indicesStartingAtOne;
		this.optionalGetters = optionalGetters;
	}


	int[] getIndicesStartingAtOne() {
		return indicesStartingAtOne;
	}


	void setIndicesStartingAtOne(int[] indicesStartingAtOne) {
		this.indicesStartingAtOne = indicesStartingAtOne;
	}

	FunctionGetter[] getOptionalGetters() {
		return optionalGetters;
	}
}
