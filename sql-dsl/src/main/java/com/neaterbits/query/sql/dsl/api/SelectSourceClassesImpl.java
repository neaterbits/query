package com.neaterbits.query.sql.dsl.api;

final class SelectSourceClassesImpl extends SelectSourceImpl {

	private final Class<?> [] classes;

	public SelectSourceClassesImpl(Class<?>[] classes) {
		
		if (classes == null) {
			throw new IllegalArgumentException("classes == null");
		}
		
		this.classes = classes;
	}

	Class<?>[] getClasses() {
		return classes;
	}
}
