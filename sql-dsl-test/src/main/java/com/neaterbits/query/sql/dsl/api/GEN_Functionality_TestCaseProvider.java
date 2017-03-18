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
	
	private void emit(Stack<GEN_Functionality> stack, Consumer<GEN_TestCase> consumer, GEN_Functionality functionality) {
		
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

			@Override
			public boolean isTestApplicable(EQueryResultGathering gathering, EQueryResultDimension dimension, EFieldAccessType accessType) {
				
				// Must check for all parts of stack, eg. no-single tet for group-by even if last element is having
				final int num = stack.size();

				if (functionality != stack.top()) {
					throw new IllegalStateException("Expected functionality to be top");
				}
				
				for (int i = 0; i < num; ++ i) {
					final GEN_Functionality f = stack.get(i);
					if (!f.isTestApplicable(gathering, dimension, accessType)) {
						return false;
					}
				}
				
				return true;
			}
		});
	}
	

	private void recurse(Stack<GEN_Functionality> stack, Consumer<GEN_TestCase> consumer) {
		
		// recurse downwards for all possibilties
		
		final GEN_Functionality cur = stack.top();
		
		if (cur.isStop()) {
			// Can emit for this
			emit(stack, consumer, cur);
		}
		
		// Find all elements that follows this
		for (GEN_Functionality value : GEN_Functionality.values()) {
			
			if (   value.follows(cur)
				&& value.isTestCaseApplicable(stack)) {
				
				stack.push(value);
				
				recurse(stack, consumer);
				
				stack.pop();
			}
		}
	}
}
