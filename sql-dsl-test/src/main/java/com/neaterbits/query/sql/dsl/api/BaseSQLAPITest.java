package com.neaterbits.query.sql.dsl.api;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

import com.neaterbits.query.sql.dsl.api.MultiBuilt;
import com.neaterbits.query.sql.dsl.api.ISharedPreparedQueryOps;
import com.neaterbits.query.sql.dsl.api.SingleBuilt;

public abstract class BaseSQLAPITest {

	protected final <T> void checkSelectOneOrNull(DataConfig ds, T expected, SingleBuilt<T> query, Function<ISharedPreparedQueryOps<T>, T> execute) {
		
		ISharedPreparedQueryOps<T> ops = query.prepare(ds);

		final T result = execute.apply(ops);
    			
        assertThat(query).isNotNull();
    	
    	if (expected == null) {
    		assertThat(result).isNull();
    	}
    	else {
    		assertThat(result).isNotSameAs(expected);
    		assertThat(result).isEqualTo(expected);
    	}
	}


	protected final <T> void checkSelectListUnordered(DataConfig ds, MultiBuilt<T> query, Function<ISharedPreparedQueryOps<List<T>>, List<T>> execute, @SuppressWarnings("unchecked") T ... expected) {

		final List<T> ret = checkSelectListCommon(ds, query, execute, expected);
		
		// Check that contains the same number of items as the expected
		
		final CountMap<T> resultMap = new CountMap<>(ret);
		
		final CountMap<T> expectedMap = new CountMap<>(Arrays.asList(expected));
		
		assertThat(resultMap).isEqualTo(expectedMap);
	}

	protected final <T> void checkSelectListOrdered(DataConfig ds, MultiBuilt<T> query, Function<ISharedPreparedQueryOps<List<T>>, List<T>> execute, @SuppressWarnings("unchecked") T ... expected) {

		final List<T> ret = checkSelectListCommon(ds, query, execute, expected);
		
		// Check that contains the same number of items as the expected
		// and in order
		assertThat(ret.size()).isEqualTo(expected.length);
		
		for (int i = 0; i < expected.length; ++ i) {
			assertThat(ret.get(i)).isEqualTo(expected[i]);
		}
	}
	
	private <T> List<T> checkSelectListCommon(DataConfig ds, MultiBuilt<T> query, Function<ISharedPreparedQueryOps<List<T>>, List<T>> execute, @SuppressWarnings("unchecked") T ... expected) {
		
		
		ISharedPreparedQueryOps<List<T>> ops = query.prepare(ds);

		final List<T> result = execute.apply(ops);

		for (int i = 0; i < result.size(); ++ i) {
			System.out.println("result " + i + ": " + result.get(i));
		}

        assertThat(query).isNotNull();

        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(expected.length);
		
        return result;
	}
}
