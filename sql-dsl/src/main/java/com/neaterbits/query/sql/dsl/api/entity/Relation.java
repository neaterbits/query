package com.neaterbits.query.sql.dsl.api.entity;

public class Relation {
	private final RelationField from;
	private final RelationField to;

	Relation(RelationType type, RelationField from, RelationField to) {

		if (type == null) {
			throw new IllegalArgumentException("type == null");
		}

		if (from == null) {
			throw new IllegalArgumentException("from == null");
		}

		if (to == null) {
			throw new IllegalArgumentException("to == null");
		}

		this.from = from;
		this.to = to;
	}

	public RelationField getFrom() {
		return from;
	}

	public RelationField getTo() {
		return to;
	}
}
