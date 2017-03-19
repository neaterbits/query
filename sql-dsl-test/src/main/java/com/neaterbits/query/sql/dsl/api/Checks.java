package com.neaterbits.query.sql.dsl.api;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

final class Checks {

	static <T> void checkSelectOneOrNull(DataConfig ds, T expected, SingleBuilt<T> query, Function<ISharedPreparedQueryOps<T>, T> execute) {
		
		ISharedPreparedQueryOps<T> ops = query.prepare(ds);

		final T result = execute.apply(ops);
    			
        assertThat(query).isNotNull();
    	
    	if (expected == null) {
    		assertThat(result).isNull();
    	}
    	else {
    		// TODO hmm.. Longs become same for some reason
    		if (!(expected instanceof Long)) {
    			assertThat(result).isNotSameAs(expected);
    		}
    		
    		if (result instanceof BigDecimal && expected instanceof BigDecimal) {
    			// handle scale differences (ie. 000's at the end)
    			final BigDecimal resultDecimal = (BigDecimal)result;
    			final BigDecimal expectedDecimal = (BigDecimal)expected;
    			
    			assertThat(resultDecimal.compareTo(expectedDecimal)).isEqualTo(0);
    		}
    		else {
    			assertThat(result).isEqualTo(expected);
    		}
    	}
	}


	static final <T> void checkSelectListUnordered(DataConfig ds, MultiBuilt<T> query, Function<ISharedPreparedQueryOps<List<T>>, List<T>> execute, @SuppressWarnings("unchecked") T ... expected) {

		final List<T> ret = checkSelectListCommon(ds, query, execute, expected);
		
		// Check that contains the same number of items as the expected
		
		final CountMap<T> resultMap = new CountMap<>(ret);
		
		final CountMap<T> expectedMap = new CountMap<>(Arrays.asList(expected));
		
		assertThat(resultMap).isEqualTo(expectedMap);
	}

	static <T> void checkSelectListOrdered(DataConfig ds, MultiBuilt<T> query, Function<ISharedPreparedQueryOps<List<T>>, List<T>> execute, @SuppressWarnings("unchecked") T ... expected) {

		final List<T> ret = checkSelectListCommon(ds, query, execute, expected);
		
		// Check that contains the same number of items as the expected
		// and in order
		assertThat(ret.size()).isEqualTo(expected.length);
		
		for (int i = 0; i < expected.length; ++ i) {
			assertThat(ret.get(i)).isEqualTo(expected[i]);
		}
	}
	
	static <T> List<T> checkSelectListCommon(DataConfig ds, MultiBuilt<T> query, Function<ISharedPreparedQueryOps<List<T>>, List<T>> execute, @SuppressWarnings("unchecked") T ... expected) {
		
		
		ISharedPreparedQueryOps<List<T>> ops = query.prepare(ds);

		final List<T> result = execute.apply(ops);

		for (int i = 0; i < result.size(); ++ i) {
			System.out.println("-- result " + i + ": " + result.get(i));
		}

        assertThat(query).isNotNull();

        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(expected.length);
		
        return result;
	}
	
}