package com.neaterbits.query.sql.dsl.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class ExecuteQueryPOJOsFromListInput implements ExecuteQueryPOJOsInput {

	private final ExecuteQueryPOJOsArrayOfCollectionsInput delegate;
	
	public ExecuteQueryPOJOsFromListInput(Collection<?> instances, Class<?> ... classes) {
		
		if (instances == null) {
			throw new IllegalArgumentException("instances == null");
		}
		
		if (instances.isEmpty()) {
			throw new IllegalArgumentException("no instances");
		}
		
		if (classes.length == 0) {
			throw new IllegalArgumentException("no classes");
		}
		
		this.delegate = convert(instances, classes);
	}
	
	@SuppressWarnings("unchecked")
	private static ExecuteQueryPOJOsArrayOfCollectionsInput convert(Collection<?> instances, Class<?> [] classes) {

		
		@SuppressWarnings("rawtypes")
		final Collection [] collections = new Collection[classes.length];

		for (int i = 0; i < classes.length; ++ i) {
			collections[i] = new ArrayList<>();
		}
		
		// Run though all instances and sort them by type
		for (Object instance : instances) {
			final Class<?> cl = instance.getClass();

			final int idx = idx(classes, cl);

			if (idx < 0) {
				throw new IllegalStateException("instance class " + cl + " is not among expected: " + Arrays.toString(classes));
			}

			collections[idx].add(instance);
		}

		return new ExecuteQueryPOJOsArrayOfCollectionsInput(collections);
	}

	private static <T> boolean has(T [] array, T toFind) {
		return idx(array, toFind) >= 0;
	}
	
	private static <T> int idx(T [] array, T toFind) {
		
		if (toFind == null) {
			throw new IllegalArgumentException("toFind == null");
		}

		for (int i = 0; i < array.length; ++ i) {
			if (array[i].equals(toFind)) {
				return i;
			}
		}
		
		return -1;
	}
	
	
	@Override
	public Collection<?> getPOJOs(int idx) {
		return delegate.getPOJOs(idx);
	}
}
