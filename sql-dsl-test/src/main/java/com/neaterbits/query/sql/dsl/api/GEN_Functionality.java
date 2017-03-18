package com.neaterbits.query.sql.dsl.api;

import java.util.ArrayList;
import java.util.List;

import com.neaterbits.util.Stack;
import com.neaterbits.util.StringUtils;

public enum GEN_Functionality {

	AGGREGATE(true, true,
			
		      null,
		      (result, dimension, fieldAccess) -> result == EQueryResultGathering.AGGREGATE && dimension == EQueryResultDimension.SINGLE),

	ENTITY(true, true,
			
		  null,
		  (result, dimension, fieldAccess) -> result == EQueryResultGathering.ENTITY),

 	MAPPED(true, false,

 		   null,
		   (result, dimension, fieldAccess) -> result == EQueryResultGathering.ENTITY),

	JOIN(false, true, AGGREGATE, ENTITY, MAPPED), // Choose one when traversing
	
	WHERE(false, true, JOIN, ENTITY, AGGREGATE, MAPPED),
	
	WHERE_FUNCTION(false, true, JOIN, ENTITY, AGGREGATE, MAPPED), // not all combinations needer here??
	
	AND(false, true, WHERE), 
	OR(false, true, WHERE),
	
	AND_FUNCTION(false, true, WHERE),
	OR_FUNCTION(false, true, WHERE),
	
	AND_NEST(false, false, WHERE, AND),
	OR_NEST(false, false, WHERE, OR, AND_NEST),

	GROUP_BY(
			false,
			true,

			// Must be mapped query and have multi-dimension
			stack -> stack.contains(GEN_Functionality.MAPPED),
			(result, dimension, fieldAccess) -> dimension == EQueryResultDimension.MULTI && result == EQueryResultGathering.MAPPED,
			
			MAPPED, WHERE, WHERE_FUNCTION, AND, AND_FUNCTION, OR, OR_FUNCTION),
	HAVING(false, true, GROUP_BY),

	// hmm.. only follow join if the former are specified. Perhaps should just pass criteria into this tree?
	ORDER_BY(false, true, ENTITY, MAPPED, JOIN, WHERE, AND, OR, GROUP_BY, HAVING)  


	;
	
	private static final List<GEN_Functionality> startElements;
	
	static {
		startElements = new ArrayList<>();

		for (GEN_Functionality element : values()) {
			if (element.start) {
				startElements.add(element);
			}
		}
	}

	public static GEN_Functionality [] getStartElements() {
		return startElements.toArray(new GEN_Functionality[startElements.size()]);
	}

	@Deprecated // probably not necessary since start is every element that do not follow ant other
	private final boolean start;
	private final boolean stop;

	private final GEN_TestCase_Applicability testCaseApplicability;
	private final GEN_Test_Applicability testApplicability;
	private final GEN_Functionality  [] follows;

	boolean isStop() {
		return stop;
	}

	boolean follows(GEN_Functionality functionality) {
		if (functionality == null) {
			throw new IllegalArgumentException("functionality == null");
		}

		boolean ret;

		if (follows == null) {
			ret = false;
		}
		else {
			ret = false;
			
			for (GEN_Functionality f : follows) {
				if (f.equals(functionality)) {
					ret = true;
				}
			}
		}

		return ret;
	}

	private GEN_Functionality(boolean start, boolean stop) {
		this.start = start;
		this.stop = stop;
		this.testCaseApplicability = null;
		this.testApplicability = null;
		this.follows = null;
	}

	private GEN_Functionality(boolean start, boolean stop, GEN_Functionality ... follows) {

		this.start = start;
		this.stop = stop;
		this.testCaseApplicability = null;
		this.testApplicability = null;
		this.follows = follows;
	}

	private GEN_Functionality(
			boolean start, boolean stop,
			GEN_TestCase_Applicability testCaseApplicability,
			GEN_Test_Applicability testApplicability,
			GEN_Functionality ... follows) {

		this.start = start;
		this.stop = stop;
		this.testCaseApplicability = testCaseApplicability;
		this.testApplicability = testApplicability;
		this.follows = follows;
	}
	
	boolean isTestCaseApplicable(Stack<GEN_Functionality> stack) {
		return testCaseApplicability == null || testCaseApplicability.isApplicable(stack);
	}
	
	boolean isTestApplicable(EQueryResultGathering gathering, EQueryResultDimension dimension, EFieldAccessType accessType) {
		return testApplicability == null || testApplicability.isApplicable(gathering, dimension, accessType);
	}

	public String getSourceCodeName() {
		return StringUtils.camelCase(name());
	}
}
