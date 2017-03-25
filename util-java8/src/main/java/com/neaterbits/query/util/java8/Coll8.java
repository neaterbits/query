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

	public static <T> T find(Collection<T> coll, Predicate<T> predicate) {

		if (coll == null) {
			return null;
		}

		for (T elem : coll) {
			if (predicate.test(elem)) {
				return elem;
			}
		}

		return null;
	}
	
	public static <T, O> List<O> remap(Collection<T> list, Function<T, O> map) {
		final List<O> ret = new ArrayList<>(list.size());
		
		for (T t : list) {
			ret.add(map.apply(t));
		}

		return ret;
	}

	public static <T> List<T> fromIterable(List<T> dst, Iterable<T> iterable) {
		for (T item : iterable) {
			dst.add(item);
		}
		
		return dst;
	}
	
	
	public static <T> int idxOf(List<T> list, Predicate<T> predicate) {
		for (int i = 0; i < list.size(); ++ i) {
			if (predicate.test(list.get(i))) {
				return i;
			}
		}
		
		return -1;
	}
}
