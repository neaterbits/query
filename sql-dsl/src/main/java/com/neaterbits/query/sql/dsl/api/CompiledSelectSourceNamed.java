package com.neaterbits.query.sql.dsl.api;

final class CompiledSelectSourceNamed extends CompiledSelectSource {

	CompiledSelectSourceNamed(SelectSourceNamedImpl selectSource, Class<?> type, String name, int idx) {
		super(selectSource, type, name, idx);
	}
}
