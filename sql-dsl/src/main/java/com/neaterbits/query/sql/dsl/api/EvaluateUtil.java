package com.neaterbits.query.sql.dsl.api;

class EvaluateUtil {

	@SuppressWarnings("unchecked")
	static boolean evaluateComparables(Object lhs, Object rhs) {
		final boolean ret;
		
		if (lhs == null) {
			ret = rhs == null;
		}
		else if (rhs == null) {
			ret = true;
		}
		else {
			ret = ((Comparable<Object>)lhs).compareTo((Comparable<Object>)rhs) == 0;
		}
		
		return ret;
	}
}
