package com.neaterbits.query.sql.dsl.api;

import java.util.HashMap;
import java.util.Map;

import com.neaterbits.util.IdentityKey;

/**
 * Helper class that assigns name to parameters
 * and returns the same name for known parameters
 * 
 * @author nhl
 *
 */

final class ParamNameAssigner {

	private final Map<IdentityKey<Param<?>>, String> names;

	private int curParam;

	ParamNameAssigner() {
		this.names = new HashMap<>();
		this.curParam = 0;
	}

	String getName(Param<?> param) {

		if (param == null) {
			throw new IllegalArgumentException("param == null");
		}

		final IdentityKey<Param<?>> key = new IdentityKey<>(param);

		String name = names.get(key);

		if (name == null) {
			name = "param" + curParam;
			++ curParam;

			names.put(key, name);
		}

		return name;
	}
}

