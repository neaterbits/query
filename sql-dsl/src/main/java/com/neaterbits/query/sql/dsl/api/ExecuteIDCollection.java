package com.neaterbits.query.sql.dsl.api;

public interface ExecuteIDCollection<T, COLL, ID> {
	
	/**
	 * Get all elements of a particular ID
	 * 
	 * @param coll collection
	 * @param id ID, eg "name" for Aliases
	 * 
	 * @return number of elements of ID
	 */
	
	int getSize(COLL coll, ID id);
	
	int getFirst(ID id);
	
	int getNext(ID id, int last);
	
	T getAt(int idx);
}
