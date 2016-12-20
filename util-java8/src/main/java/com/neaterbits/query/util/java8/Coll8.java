package com.neaterbits.query.util.java8;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
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
	
	public static <T, O> List<O> remap(Collection<T> list, Function<T, O> map) {
		final List<O> ret = new ArrayList<>(list.size());
		
		for (T t : list) {
			ret.add(map.apply(t));
		}

		return ret;
	}

}
