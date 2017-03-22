package com.neaterbits.query.sql.dsl.api;

abstract class CompiledResultFields {

	// Should always have indices
	private int [] indicesStartingAtOne;
	private Getter [] optionalGetters; // in case we specified rows by getter
	
	
	CompiledResultFields(int [] indicesStartingAtOne, Getter [] optionalGetters) {
		
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


	final int[] getIndicesStartingAtOne() {
		return indicesStartingAtOne;
	}


	final void setIndicesStartingAtOne(int[] indicesStartingAtOne) {
		this.indicesStartingAtOne = indicesStartingAtOne;
	}

	final Getter[] getOptionalGetters() {
		return optionalGetters;
	}
}
