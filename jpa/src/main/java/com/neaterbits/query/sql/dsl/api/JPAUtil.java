package com.neaterbits.query.sql.dsl.api;

import java.util.function.Consumer;

final class JPAUtil {

	static <T> void commaSeparated(StringBuilder sb, Iterable<T> iter, Consumer<T> c) {
		separated(sb, iter, ", ", c);
	}

	static <T> void separated(StringBuilder sb, Iterable<T> iter, String separator, Consumer<T> c) {
		boolean first = true;

		// We are returning a mapped type, get each value
		for (T t : iter) {
			// Must return mappings
			if (!first) {
				sb.append(separator);
			} else {
				first = false;
			}

			c.accept(t);
		}
	}
}
