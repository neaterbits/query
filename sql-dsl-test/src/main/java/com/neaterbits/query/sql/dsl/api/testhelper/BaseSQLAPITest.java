package com.neaterbits.query.sql.dsl.api.testhelper;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

import com.neaterbits.query.sql.dsl.api.MultiQuery;
import com.neaterbits.query.sql.dsl.api.PreparedQueryOps;
import com.neaterbits.query.sql.dsl.api.QueryDataSource;
import com.neaterbits.query.sql.dsl.api.SingleQuery;

public abstract class BaseSQLAPITest {

	protected final <T> void checkSelectOneOrNull(QueryDataSource ds, T expected, SingleQuery<T> query, Function<PreparedQueryOps<T>, T> execute) {
		
		PreparedQueryOps<T> ops = query.prepare(ds);

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


	protected final <T> void checkSelectListUnordered(QueryDataSource ds, MultiQuery<T> query, Function<PreparedQueryOps<List<T>>, List<T>> execute, @SuppressWarnings("unchecked") T ... expected) {

		final List<T> ret = checkSelectListCommon(ds, query, execute, expected);
		
		// Check that contains the same number of items as the expected
		
		final CountMap<T> resultMap = new CountMap<>(ret);
		
		final CountMap<T> expectedMap = new CountMap<>(Arrays.asList(expected));
		
		assertThat(resultMap).isEqualTo(expectedMap);
	}
	
	private <T> List<T> checkSelectListCommon(QueryDataSource ds, MultiQuery<T> query, Function<PreparedQueryOps<List<T>>, List<T>> execute, @SuppressWarnings("unchecked") T ... expected) {
		PreparedQueryOps<List<T>> ops = query.prepare(ds);

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
