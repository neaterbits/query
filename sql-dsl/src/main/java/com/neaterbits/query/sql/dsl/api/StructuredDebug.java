package com.neaterbits.query.sql.dsl.api;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import com.neaterbits.util.Stack;

/**
 * Helper class for structured debug output
 *  - indentation
 *  - column base for readability
 * @author nhl
 *
 */

final class StructuredDebug {

	private int indent;
	private final Stack<StructuredDebugBlock> stack;
	private final List<StructuredDebugBlock> blocks;

	StructuredDebug(String prefix) {
		this.indent = 0;
		this.stack = new Stack<>();
		this.blocks = new ArrayList<>();

		startNewBlock(prefix);
	}

	StructuredDebug addIndent(String prefix) {
		++ indent;

		startNewBlock(prefix);

		return this;
	}

	StructuredDebug subIndent() {
		-- indent;

		// Pop sub block first
		stack.pop();
		
		// Also pop block corresponding to current level, because we will continue on a new one
		final String prefix = stack.pop().getPrefix();
		
		startNewBlock(prefix);

		return this;
	}
	
	StructuredDebug debug(String ... columns) {
		curBlock().addLine(columns);
		
		return this;
	}

	void output(PrintStream out) {
		
		for (StructuredDebugBlock block : blocks) {
			block.output(out);
		}
	}

	private StructuredDebugBlock curBlock() {
		return blocks.get(blocks.size() - 1);
	}

	private void startNewBlock(String prefix) {
		
		final StructuredDebugBlock block = new StructuredDebugBlock(indent, prefix);
		
		stack.push(block);
		blocks.add(block);
	}
}

