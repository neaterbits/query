package com.neaterbits.query.sql.dsl.api;

abstract class CompiledResultFields {

	// Should always have indices
	private int [] indicesStartingAtOne; // in case we specified rows by index
	private CompiledFieldReference [] getters; // compiled field references 
	
	
	CompiledResultFields(int [] indicesStartingAtOne, CompiledFieldReference [] getters) {
		
		if (getters == null) {
			throw new IllegalArgumentException("getters == null");
		}

		if (indicesStartingAtOne != null && getters.length != indicesStartingAtOne.length) {
			throw new IllegalArgumentException("mismatch between optionalGetters and indices");
		}
		
		for (int i = 0; i < getters.length; ++ i) {
			if (getters[i] == null) {
				throw new IllegalStateException("null getter at " + i);
			}
		}

		this.indicesStartingAtOne = indicesStartingAtOne;
		this.getters = getters;
	}

	final int[] getIndicesStartingAtOne() {
		return indicesStartingAtOne;
	}

	final void setIndicesStartingAtOne(int[] indicesStartingAtOne) {
		this.indicesStartingAtOne = indicesStartingAtOne;
	}

	final CompiledFieldReference[] getFieldReferences() {
		return getters;
	}
}
