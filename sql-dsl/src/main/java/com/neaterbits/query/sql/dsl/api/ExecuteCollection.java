package com.neaterbits.query.sql.dsl.api;

interface ExecuteCollection<ID, COLL> {

	/**
	 * 
	 * Total numbers of elements in collection
	 * 
	 * @param coll
	 * 
	 * @return
	 */
	
	int getSize(COLL coll);
	
	
	/**
	 * Get 
	 * @param id
	 * @return
	 */

	
	int getFirst();
	
	int getNext(int last);

	<T> T getAt(int idx);
	
}
