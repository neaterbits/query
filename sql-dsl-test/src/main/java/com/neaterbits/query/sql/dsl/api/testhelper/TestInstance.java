package com.neaterbits.query.sql.dsl.api.testhelper;

final class TestInstance {
	private final Object instance;
	private final Object pk;
	
	TestInstance(Object instance, Object pk) {
		
		if (instance == null) {
			throw new IllegalArgumentException("instance == null");
		}
		
		if (pk == null) {
			throw new IllegalArgumentException("pk == null");
		}

		this.instance = instance;
		this.pk = pk;
	}

	Object getInstance() {
		return instance;
	}

	Object getPK() {
		return pk;
	}
}
