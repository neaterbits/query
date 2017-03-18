package com.neaterbits.query.sql.dsl.api;

final class GEN_TestCodeGenerator {

	public static void main(String [] args) {
		
		final GEN_TestCaseProvider testCaseProvider = new GEN_Functionality_TestCaseProvider();
		
		
		testCaseProvider.forAllTestCases(testCase -> {
			final String [] names = testCase.getFunctionalitySourceCodeNames();
			
			for (String name : names) {
				System.out.print(name);
				
			}
			System.out.println();
			
		});
	}
}
