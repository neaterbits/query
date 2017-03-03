package com.neaterbits.query.sql.dsl.api;

import java.util.HashMap;
import java.util.Map;

final class CountMap<T> {

	private final Map<T, Integer> map;
	
	CountMap(Iterable<T> iter) {

		this.map = new HashMap<>();

		for (T t : iter) {
			if (t == null) {
				throw new IllegalArgumentException("t == null");
			}
			
			Integer existing = map.get(t);
			
			int newVal;
			
			if (existing != null) {
				newVal = existing + 1;
			}
			else {
				newVal = 1;
			}

			map.put(t, newVal);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((map == null) ? 0 : map.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CountMap<?> other = (CountMap<?>) obj;
		if (map == null) {
			if (other.map != null)
				return false;
		} else if (!map.equals(other.map))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return map.toString();
	}
}

