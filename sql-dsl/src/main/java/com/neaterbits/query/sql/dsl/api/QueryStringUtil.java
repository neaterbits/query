package com.neaterbits.query.sql.dsl.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.BiConsumer;

final class QueryStringUtil {

	static <T> void commaSeparated(QueryBuilder sb, Iterable<T> iter, BiConsumer<QueryBuilder, T> c) {
		separated(sb, sb.sb, iter, ", ", c);
	}
	
	static <T> void commaSeparated(StringBuilder sb, Iterable<T> iter, BiConsumer<StringBuilder, T> c) {
		separated(sb, iter, ", ", c);
	}

	static <T> void commaSeparated(StringBuilder sb, int [] indices) {
		
		final Collection<Integer> intList = new ArrayList<>(indices.length);
		
		for (int i : indices) {
			intList.add(i);
		}

		commaSeparated(sb, intList, (StringBuilder s, Integer i) -> s.append(String.valueOf(i)));
	}
	
	static <T, BUILDER> void separated(StringBuilder sb, Iterable<T> iter, String separator, BiConsumer<StringBuilder, T> c) {
		separated(sb, sb, iter, separator, c);
	}

    private static <T, BUILDER> void separated(BUILDER b, StringBuilder sb, Iterable<T> iter, String separator, BiConsumer<BUILDER, T> c) {
		
		boolean first = true;

		// We are returning a mapped type, get each value
		for (T t : iter) {
			// Must return mappings
			if (!first) {
				sb.append(separator);
			} else {
				first = false;
			}

			c.accept(b, t);
		}
	}
}
