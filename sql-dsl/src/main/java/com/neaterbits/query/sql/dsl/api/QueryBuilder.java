package com.neaterbits.query.sql.dsl.api;

final class QueryBuilder {

	final StringBuilder sb;

	QueryBuilder() {
		this.sb = new StringBuilder();
	}
	
	QueryBuilder append(String s) {
		sb.append(s);
		
		return this;
	}

	QueryBuilder append(char c) {
		sb.append(c);
		
		return this;
	}
	
	boolean isEmpty() {
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
