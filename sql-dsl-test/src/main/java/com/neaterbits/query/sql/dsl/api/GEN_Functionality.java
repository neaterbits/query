package com.neaterbits.query.sql.dsl.api;

import java.util.ArrayList;
import java.util.List;

public enum GEN_Functionality {

	AGGREGATE(true, true),
	ENTITY(true, true),
	MAPPED(true, false),

	JOIN(false, true, AGGREGATE, ENTITY, MAPPED), // Choose one when traversing
	
	WHERE(false, true, JOIN, ENTITY, AGGREGATE, MAPPED),
	
	WHERE_FUNCTION(false, true, JOIN, ENTITY, AGGREGATE, MAPPED), // not all combinations needer here??
	
	AND(false, true, WHERE), 
	OR(false, true, WHERE),
	
	AND_FUNCTION(false, true, WHERE),
	OR_FUNCTION(false, true, WHERE),
	
	AND_NEST(false, false, WHERE, AND),
	OR_NEST(false, false, WHERE, OR, AND_NEST),

	GROUP_BY(false, true, MAPPED, WHERE, WHERE_FUNCTION, AND, AND_FUNCTION, OR, OR_FUNCTION),
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
		this.follows = null;
	}
	
	private GEN_Functionality(boolean start, boolean stop, GEN_Functionality ... follows) {
		this.start = start;
		this.stop = stop;
		this.follows = follows;
	}

	public String getSourceCodeName() {
		// Convert to camel case
		final StringBuilder sb = new StringBuilder(name().length());

		final String lowercase = name().toLowerCase();
		
		boolean uppercaseNext = true;
		
		for (int i = 0; i < lowercase.length(); ++ i) {
			
			final char c = lowercase.charAt(i);
			
			if (c == '_') {
				uppercaseNext = true;
				continue;
			}
			
			sb.append(uppercaseNext ? Character.toUpperCase(c) : c);
			uppercaseNext = false;
		}

		return sb.toString();
	}
}
