package com.neaterbits.query.sql.dsl.api.entity;

import java.lang.reflect.Member;

public class EntityAttribute {

	private final Member field;
	private final String name;
	private final String column;
	private final Class<?> type;

	public EntityAttribute(Member field, String name, String column, Class<?> type) {
		this.field = field;
		this.name = name;
		this.column = column;
		this.type = type;
	}
}
