package com.neaterbits.query.sql.dsl.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

final class ResultCollection_List_GroupBy_OrderBy<QUERY> extends ArrayList<Object> implements ResultCollection {

	private static final long serialVersionUID = 1L;

	private final ExecutableQuery<QUERY> q;
	private final QUERY query;
	

	private final int numGroupBy;
	private final int numOrderBy;
	
	private final int numMappings;
	
	public ResultCollection_List_GroupBy_OrderBy(ExecutableQuery<QUERY> q, QUERY query) {
		
		if  (q == null) {
			throw new IllegalArgumentException("q == null");
		}
		
		if (query == null) {
			throw new IllegalArgumentException("query == null");
		}
		
		this.numGroupBy = q.getGroupByFieldCount(query);
		this.numOrderBy = q.getOrderByFieldCount(query);
				
		
		if (numGroupBy == 0 && numOrderBy == 0 ) {
			throw new IllegalStateException("neither group by nor order by set");
		}
		
		if (q.getGathering(query) != EQueryResultGathering.MAPPED) {
			throw new IllegalArgumentException("only for mapped queries");
		}
		
		this.numMappings = q.getMappingCount(query);
		if (numMappings < 1) {
			throw new IllegalArgumentException("no mappings");
		}
		
		this.q = q;
		this.query = query;
	}
	
	@Override
	public void addResult(Object o) {
		
		final ExecuteQueryScratch scratch = (ExecuteQueryScratch)o;
		
		final Object toAdd;
		
		// scratch-area is reset for each iteration, so we have to copy result
		if (numMappings == 1) {
			// Must find the one value
			final int sourceIdx = q.getMappingSourceIdx(query, 0);

			final Object instance = scratch.get(sourceIdx);
			
			final Object value = q.executeMappingGetter(query, 0, instance);
			
			toAdd = value;
		}
		else {
			
			// fortsett her, må mappe mappingIdx til noe og hente ut felt-verdien
			// must add multiple values, so we can sort them at the end and map them into new mapped entities
			final Comparable<?> [] values = new Comparable[numMappings];
			
			
			for (int mappingIdx = 0; mappingIdx < numMappings; ++ mappingIdx) {
				
				final int sourceIdx = q.getMappingSourceIdx(query, mappingIdx);
				
				// Must look up from scratch-buffer on source idx
				final Object instance = scratch.get(sourceIdx);
				
				try {
					final Object value = q.executeMappingGetter(query, mappingIdx, instance);
				
					values[mappingIdx] = (Comparable<?>)value;
				}
				catch (RuntimeException ex) {
					throw new IllegalStateException("Expection while mapping scratch  " + mappingIdx + " of class " + instance.getClass().getSimpleName(), ex);
				}
			}
			
			toAdd = values;
		}
		
		super.add(toAdd);
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	private int compareWithNull(Comparable c1, Comparable c2, ESortOrder sortOrder) {

		int val;
		
		if (c1 == null) {
			if (c2 == null) {
				val = 0;
			}
			else {
				// null sorts before values
				val = -1;
			}
		}
		else {
			if (c2 == null) {
				// null sorts before values
				val = 1;
			}
			else {
				val = c1.compareTo(c2);
			}
		}
		
		if (val != 0 && sortOrder != ESortOrder.ASCENDING) {
			val = - val;
		}
		
		return val;
		
	}
	
	@Override
	public Collection<Object> asCollection() {
		// Must sort the results before returning
		
		final Collection<Object> ret;
		
		
		/**
		 * We do a trick here in order to perform almost the same algorithm for
		 * group-by as for order-by.
		 * 
		 * The former does not say anything about the order, so we might just as well
		 * just sort all entries with consolidated group-by finding equal entries,
		 * and orderBy sorting them in order.
		 * 
		 * After having done a complete sort of the collection according to the consolidated criteria,
		 * we can then, in the case of group-by, remove any duplicates if
		 *  - multiple entries have the same values for all group-by columns and
		 *    - either all columns are specified in group-by
		 *    - (TODO) or the columns not in group-by are also unique 
		 *
		 * For the special-case of only one mapping, we will just sort according to order, then remove all non-distinct entries.
		 */
		

		if (numGroupBy > 0 && numGroupBy != numMappings) {
			throw new UnsupportedOperationException("TODO - only supports all entries to be part og group by");
		}

		if (numMappings == 1) {
			ret = orderSingleMapping(this);
		}
		else {
			ret = orderMultipleMappings(this);
		}
		
		return ret;
	}
	
	@SuppressWarnings("rawtypes")
	private List<Object> orderSingleMapping(Collection<Object> input) {
		// The fields are stored directly in the array, just sort them in whatever order.
		
		final ESortOrder order;
		
		final List<Object> ret;
		
		if (numOrderBy > 0) {
			if (numOrderBy != 1) {
				throw new IllegalStateException("Expected at most one orderBy");
			}
			
			order = q.getOrderBySortOrder(query, 0);
		}
		else {
			order = ESortOrder.ASCENDING;
		}
			
		// Sort.
		sort((o1, o2) -> {
			
			final Comparable c1 = (Comparable)o1;
			final Comparable c2 = (Comparable)o2;

			return compareWithNull(c1, c2, order);
		}); 
		
		
		// We now have a complete list, if group-by, then return only distinct entries
		ret = new ArrayList<>(this.size());

		if (numGroupBy > 0) {
		
			int elementNo = 0;
			Comparable last = null;
			
			for (Object o : this) {

				if (elementNo > 0) {
					final Comparable next = checkSameValueAsLast(last, o);
					
					if (next != last) { // new entry, update last
						last = next;
					}
					else {
						continue;
					}
				}
				else {
					last = (Comparable)o;
				}
				
				// different value, add to array
				ret.add(ExecuteQueryUtil.mapSingleFromFieldValue(q, query, o));
				++ elementNo;
			}
		}
		else {
			// No groupBy, just return sorted value
			for (Object o : this) {
				ret.add(ExecuteQueryUtil.mapSingleFromFieldValue(q, query, o));
			}
		}

		return ret;
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	private static Comparable checkSameValueAsLast(Comparable last, Object o) {
		Comparable c;
	    
		if (last == null) {
			if (o == null) {
				// both null, skip to next
				c = null;
			}
			else {
				// next one is non-null, 
				c = (Comparable<?>)o;
			}
		}
		else {
			if (o == null) {
				// add this one as last
				c = null;
			}
			else {
				c = (Comparable<?>)o;
				
				if (c.compareTo(last) == 0) {
					// same value skip
					c = last;
				}
			}
		}

		return c;
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	private List<Object> orderMultipleMappings(Collection<Object> input) {
		// more than one mapping, we have to sort
		final ESortOrder[] sortOrders = new ESortOrder[numMappings];
		
		// default to ascending
		Arrays.fill(sortOrders, ESortOrder.ASCENDING);
		
		// Override with order-by values
		for (int i = 0; i < numOrderBy; ++ i) {
			
			// Override with sort-order from order-by
			sortOrders[q.getOrderByFieldIndex(query, i)] = q.getOrderBySortOrder(query, i);
		}
		
		final int [] sortIndices;
		
		if (numGroupBy > 0) {
			// Now loop through and sort in order of group-by criteria

			// cache sort indices
			sortIndices = new int[numGroupBy];
			
			for (int i = 0; i < numGroupBy; ++ i) {
				sortIndices[i] = q.getGroupByFieldIndex(query, i);
			}
			
			// also taken into account order-by indices, but those do not change the grouping order,
			// they only specified the sort-order for within each group, as specified earlier
			
			// NOTE! If we change the assertion that group-by == numMappings, then we'd have to do something clever here
			// eg. add all from order by that are not part of group-by, we would also
			// have to take this into account for group-by case.
			
		}
		else {
			// not group-by so order-by also specifies which columns take higher presedence in sorting

			
			if (numOrderBy > 0) {
				// First add all indices form orderby
				sortIndices = new int[numOrderBy];
				
				
				int dstIdx = 0;
				for (int i = 0; i < numOrderBy; ++ i) {
					sortIndices[dstIdx ++] = q.getOrderByFieldIndex(query, i);
				}
				
				// Now add the rest of the indices in result that were not part of order-by clause
				
				/*
				for (int i = 0; i < numMappings; ++ i) {
					
					boolean alreadyAdded = false;
					for (int j = 0; j < numOrderBy; ++ j) {
						
						if (sortIndices[i] == j) {
							alreadyAdded = true;
							break;
						}
					}
					
					if (!alreadyAdded) {
						// this index was not part of order-by and 
					}
					
				}
				*/
			}
			else {
				throw new UnsupportedOperationException("Should have been eithe rgroup by or order by");
			}
			
		}
		
		// cache
		final int numSortIndices = sortIndices.length;
			
		sort((o1, o2) -> {
			final Comparable<Object> [] a1 = (Comparable<Object>[])o1;
			final Comparable<Object> [] a2 = (Comparable<Object>[])o2;

			for (int i = 0; i < numSortIndices; ++ i) {
				
				final int sortIdx = sortIndices[i];
			
				
				final int val = compareWithNull(a1[sortIdx], a2[sortIdx], sortOrders[i]);
				
				if (val != 0) {
					return val;
				}
			}
			
			return 0;
		});

		// OK, done with grouping and sorting.
		// If we had group-by, we have to consolidate equal entries

		final ArrayList<Object> ret = new ArrayList<>(input.size());

		if (numGroupBy > 0) {
			
			// Skip like entries
			int elementNo = 0;
			Comparable [] last = null;

			for (Object o : input) {
				
				final Comparable [] a = (Comparable[])o;
				
				if (elementNo > 0) {
				
					final boolean alike = checkSameValueAsLast(sortIndices, last, a);
	
					if (alike) {
						// exactly same value, and we're ordered so can just skip this linec
						continue;
					}
					else {
						last = a;
					}
				}
				else {
					last = a;
				}
				
				// different value, add to array
				ret.add(ExecuteQueryUtil.mapMultipleFromArray(q, query, numMappings, a));
				
				++ elementNo;
			}
			
		}
		else {
			// Only sorting, just map all
			for (Object o : this) {
				ret.add(ExecuteQueryUtil.mapMultipleFromArray(q, query, numMappings, (Comparable[])o));
			}
		}
		
		return ret;
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	private static boolean checkSameValueAsLast(int [] sortIndices, Comparable [] last, Comparable [] a) {

		boolean alike = true;
			
		// see if is the same instance, by looping over all entries in group-by set
		for (int i = 0; i < sortIndices.length; ++ i) {
			final int sortIdx = sortIndices[i];
			
			final Comparable cLast = last[sortIdx];
			final Comparable cCur = a[sortIdx];

			if (cLast != null) {
				if (cCur != null) {
					alike = cLast.compareTo(cCur) == 0;
				}
				else {
					alike = false;
				}
			}
			else {
				alike = cCur == null;
			}
		}
		
		// new value, update last
		if (!alike) {
			last = a;
		}

		return alike;
	}

}
