package com.neaterbits.query.sql.dsl.api;

final class SelectSourceClassesImpl extends SelectSourceImpl {

	private final Class<?> [] classes;

	SelectSourceClassesImpl(Class<?>[] classes) {
		super(classes);

		if (classes == null) {
			throw new IllegalArgumentException("classes == null");
		}
		
		if (classes.length == 0) {
			throw new IllegalArgumentException("no classes");
		}
		
		this.classes = classes;
	}

	Class<?>[] getClasses() {
		return classes;
	}

	@Override
	final CompiledGetter compileGetter(CollectedMapping mapping, CompiledGetterSetterCache cache) throws CompileException {

		final CollectedMappingNamed tableMapping = (CollectedMappingNamed)mapping;
		
		final CompiledGetter ret = cache.findGetterFromTypesArray(getTypes(), tableMapping.getFunctionGetter());
		
		if (ret == null) {
			throw new CompileException("No getter found: " + tableMapping.getFunctionGetter());
		}
		
		return ret;
	}
}
