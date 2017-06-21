package com.neaterbits.query.sql.dsl.api;

public final class QueryBuilder {

	final StringBuilder sb;

	public QueryBuilder() {
		this.sb = new StringBuilder();
	}
	
	public QueryBuilder append(String s) {
		sb.append(s);
		
		return this;
	}

	public QueryBuilder append(char c) {
		sb.append(c);
		
		return this;
	}
	
	public boolean isEmpty() {
		return sb.length() == 0;
	}

	public String asQueryString() {
		return sb.toString();
	}
	
	@Override
	public String toString() {
		throw new UnsupportedOperationException();
	}
}
