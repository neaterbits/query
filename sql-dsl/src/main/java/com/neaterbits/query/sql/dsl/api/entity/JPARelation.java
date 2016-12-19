package com.neaterbits.query.sql.dsl.api.entity;

final class JPARelation {

	private final RelationField from;
	private final RelationField to;

	JPARelation(RelationField from, RelationField to) {

		if (from == null) {
			throw new IllegalArgumentException("from == null");
		}

		if (to == null) {
			throw new IllegalArgumentException("to == null");
		}

		this.from = from;
		this.to = to;
	}

	RelationField getFrom() {
		return from;
	}

	RelationField getTo() {
		return to;
	}
}
