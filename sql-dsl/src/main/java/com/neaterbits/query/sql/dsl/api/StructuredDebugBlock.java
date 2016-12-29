package com.neaterbits.query.sql.dsl.api;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

final class StructuredDebugBlock {

	private final int indent;
	private final String prefix;
	private final List<String[]> lines;
 
	StructuredDebugBlock(int indent, String prefix) {
		this.indent = indent;
		this.prefix = prefix;
		this.lines = new ArrayList<>();
	}

	String getPrefix() {
		return prefix;
	}

	void addLine(String [] columns) {
		
		if (columns == null) {
			throw new IllegalArgumentException("columns == null");
		}

		this.lines.add(columns);
	}
	
	void output(PrintStream out) {
		

		final int [] columnLengths = getColumnLengths();
		
		for (String [] line : lines) {

			// Output indent
			pad(out, indent * 2);
			
			// Output prefix if any
			if (prefix != null) {
				out.append(prefix).append(": ");
			}
			
			for (int i = 0; i < line.length; ++ i) {
				final String column = line[i];

				final int len = column == null ? 0 : column.length();

				final int columnLen = columnLengths[i];
				
				if (columnLen < len) {
					throw new IllegalStateException("columnLen < len");
				}
				
				final int padding = columnLen - len;

				if (column != null) {
					out.append(column);
				}

				pad(out, padding);
			}
			
			System.out.println();
		}
	}
	
	
	private static void pad(PrintStream out, int padding) {
		for (int i = 0; i < padding; ++ i) {
			out.append(' ');
		}
	}
	
	private int [] getColumnLengths() {
		
		int [] ret = null;
		
		// Find the longest length for each column
		for (String [] line : lines) {
			
			if (ret == null) {
				ret = new int[line.length];
			}
			else {
				ret = Arrays.copyOf(ret, line.length);
			}

			for (int i = 0; i < line.length; ++ i) {
				final String column = line[i];
				
				if (column == null) {
					continue;
				}

				final int len = column.length();

				// Update max if more than current max for column
				if (len > ret[i]) {
					ret[i] = len;
				}
			}
		}
		
		return ret;
	}
}
