package com.neaterbits.query.sql.dsl.api.helper;

class TestInstance {
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

	public Object getInstance() {
		return instance;
	}

	public Object getPK() {
		return pk;
	}
}
