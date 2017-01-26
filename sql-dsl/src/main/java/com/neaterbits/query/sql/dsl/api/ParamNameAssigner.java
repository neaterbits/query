package com.neaterbits.query.sql.dsl.api;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import com.neaterbits.util.IdentityKey;

/**
 * Helper class that assigns name to parameters
 * and returns the same name for known parameters
 * 
 * @author nhl
 *
 */

@Deprecated
final class ParamNameAssigner {

	private final Map<IdentityKey<Param<?>>, String> names;

	private int curParam;

	private ParamNameAssigner() {
		this.names = new HashMap<>();
		this.curParam = 0;
	}

	@Deprecated
	String getOrAllocateName(Param<?> param) {

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

	@Deprecated
	String getExistingName(Param<?> param) {

		if (param == null) {
			throw new IllegalArgumentException("param == null");
		}

		final IdentityKey<Param<?>> key = new IdentityKey<>(param);

		return names.get(key);
	}
	
	@Deprecated
	void forEach(BiConsumer<Param<?>, String> consumer) {
		for (Map.Entry<IdentityKey<Param<?>>, String> entry : names.entrySet()) {
			consumer.accept(entry.getKey().get(), entry.getValue());
		}
	}
}

