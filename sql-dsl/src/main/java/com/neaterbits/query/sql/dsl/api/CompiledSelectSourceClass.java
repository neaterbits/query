package com.neaterbits.query.sql.dsl.api;

final class CompiledSelectSourceClass extends CompiledSelectSource {

	CompiledSelectSourceClass(SelectSourceClassesImpl selectSource, Class<?> type, String name) {
		super(selectSource, type, name);
	}
}
