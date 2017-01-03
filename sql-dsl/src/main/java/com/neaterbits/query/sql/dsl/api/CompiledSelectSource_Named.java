package com.neaterbits.query.sql.dsl.api;

final class CompiledSelectSource_Named extends CompiledSelectSource {

	CompiledSelectSource_Named(CollectedSelectSource_Named selectSource, Class<?> type, String name, int idx) {
		super(selectSource, type, name, idx);
	}
}
