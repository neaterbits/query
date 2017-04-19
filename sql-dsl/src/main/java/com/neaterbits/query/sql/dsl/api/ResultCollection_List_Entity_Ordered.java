package com.neaterbits.query.sql.dsl.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.function.Function;

public class ResultCollection_List_Entity_Ordered<QUERY> extends ArrayList<Object> implements ResultCollection {

	private static final long serialVersionUID = 1L;

	private final ExecutableQuery<QUERY> q;
	private final QUERY query;
	
	public ResultCollection_List_Entity_Ordered(ExecutableQuery<QUERY> q, QUERY query) {
		
		if  (q == null) {
			throw new IllegalArgumentException("q == null");
		}
		
		if (query == null) {
			throw new IllegalArgumentException("query == null");
		}
		
		if (q.getOrderByFieldCount(query) == 0) {
			throw new IllegalStateException();
		}
		
		this.q = q;
		this.query = query;
	}

	@Override
	public void addResult(Object o) {
		// Just add to array list, sort later
	}

	// note: not thread-safe but we're calling this from within query execution anyhow
	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	public Collection<Object> asCollection() {

		// Depending on how many fields are part of order-by, we have to 
		// sort an array of Comparable values, or just sort one

		final Comparator<Object> comparator;
		
		final int numOrderBy = q.getOrderByFieldCount(query);
		
		switch (numOrderBy) {
		case 0:
			throw new IllegalStateException("should be at least 1 orderBy");
			
		case 1: {
			// optimize the simple-case.
			final Function getter = (Function)q.getEntityOrderByFieldGetter(query, 0);
			
			comparator = (o1, o2) -> {
				
				final Comparable c1 = (Comparable)getter.apply(o1);
				final Comparable c2 = (Comparable)getter.apply(o2);
				
				return c1.compareTo(c2);
			};
			break;
		}
			
		default:
			// Multiple fields, we must compare all of them
			final Function [] getters = new Function[numOrderBy];
			
			for (int i = 0; i < numOrderBy; ++ i) {
				getters[i] = (Function)q.getEntityOrderByFieldGetter(query, i);
			}
			
			
			comparator = (o1, o2) -> {
				
						
				// Must loop over all fields in-order and compare
				for (int i = 0; i < numOrderBy; ++ i) {
					final Function getter = getters[i];
					
					final Comparable c1 = (Comparable)getter.apply(o1);
					final Comparable c2 = (Comparable)getter.apply(o2);
	
					final int val =  c1.compareTo(c2);
					if (val != 0) {
						return val;
					}
				}
					
				return 0;
			};
			break;
		
		}
		
		sort(comparator);
		
		
		return this;
	}
}
