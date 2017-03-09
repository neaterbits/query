package com.neaterbits.query.sql.dsl.api;

import java.util.List;

import com.neaterbits.query.util.java8.Coll8;

final class CompiledSelectSource_Named extends CompiledSelectSource {

	CompiledSelectSource_Named(CollectedSelectSource_Named selectSource, Class<?> type, String name, int idx) {
		super(selectSource, type, name, idx);
	}

	CompiledSelectSource_Named(Class<?> type, String name, int idx) {
		super(type, name, idx);
	}

	static String getNameNoCheck(Class<?> cl) {
		final String name = cl.getSimpleName().toLowerCase();
		
		return name;
	}
	
	static String getName(Class<?> cl, List<CompiledSelectSource_Named> compiledList) {
		final String name = getNameNoCheck(cl);

		if (Coll8.has(compiledList, e -> e.getName().equals(name))) {
			throw new IllegalStateException("Two entity classes with same lowercase name \"" + name + "\"");
		}
		
		return name;
	}
}
