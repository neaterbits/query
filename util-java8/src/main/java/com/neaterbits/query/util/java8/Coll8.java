package com.neaterbits.query.util.java8;

import java.util.Collection;
import java.util.function.Predicate;

public class Coll8 {

	public static <T> boolean has(Collection<T> coll, Predicate<T> predicate) {

		if (coll == null) {
			return false;
		}

		for (T elem : coll) {
			if (predicate.test(elem)) {
				return true;
			}
		}

		return false;
	}
	
}
