package com.neaterbits.query.sql.dsl.api;

import java.util.function.Consumer;

import com.neaterbits.util.Stack;

final class GEN_Functionality_TestCaseProvider extends GEN_TestCaseProvider {

	@Override
	void forAllTestCases(Consumer<GEN_TestCase> consumer) {
		
		// Must recursively loop over all test case in the enum and recursively collect them in a stack and emitting each
		
		final Stack<GEN_Functionality> stack = new Stack<>();

		
		final GEN_Functionality [] startElements = GEN_Functionality.getStartElements();
		
		// Must recurse down from every start element and recurse down to all elements that can follow that element
		// to make sure handles all the transitional cases
		
		
		for (GEN_Functionality startElement : startElements) {
			
			stack.push(startElement);
			
			recurse(stack, consumer);
			
			
			stack.pop();
			
		}
		
	}
	
	private void emit(Stack<GEN_Functionality> stack, Consumer<GEN_TestCase> consumer) {
		
		final int num = stack.size();
		final String [] strings = new String[num];

		for (int i = 0; i < num; ++ i) {
			strings[i] = stack.get(i).getSourceCodeName();
		}
		
		consumer.accept(new GEN_TestCase() {
			@Override
			public String[] getFunctionalitySourceCodeNames() {
				return strings;
			}
		});
	}
	

	private void recurse(Stack<GEN_Functionality> stack, Consumer<GEN_TestCase> consumer) {
		
		// recurse downwards for all possibilties
		
		final GEN_Functionality cur = stack.top();
		
		if (cur.isStop()) {
			// Can emit for this
			emit(stack, consumer);
		}
		
		// Find all elements that follows this
		for (GEN_Functionality value : GEN_Functionality.values()) {
			if (value.follows(cur)) {
				stack.push(value);
				
				recurse(stack, consumer);
				
				stack.pop();
			}
		}
	}
}
