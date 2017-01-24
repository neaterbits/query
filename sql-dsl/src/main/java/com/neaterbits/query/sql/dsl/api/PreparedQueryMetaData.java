package com.neaterbits.query.sql.dsl.api;

/**
 * Some cached metadata about a prepared query
 * @author nhl
 *
 */

final class PreparedQueryMetaData {

	private final boolean hasParams;

	public PreparedQueryMetaData(boolean hasParams) {
		super();
		this.hasParams = hasParams;
	}

	boolean hasParams() {
		return hasParams;
	}
}
