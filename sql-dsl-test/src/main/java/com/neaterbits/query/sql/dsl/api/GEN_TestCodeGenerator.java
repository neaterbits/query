package com.neaterbits.query.sql.dsl.api;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.neaterbits.util.StringUtils;


final class GEN_TestCodeGenerator {

	private static final String basePath = "/home/nhl/projects/all-neon/query/gen-test/src/test/java";

	public static void main(String [] args) {
		
		final GEN_TestCaseProvider testCaseProvider = new GEN_Functionality_TestCaseProvider();
		
		
		final TestContext testContext = new TestContext(
				new File(basePath),
				new String [] { "com", "neaterbits", "query","dsl", "gen_test" });
		
		testCaseProvider.forAllTestCases(testCase -> {
			final String [] names = testCase.getFunctionalitySourceCodeNames();
			
			for (String name : names) {
				System.out.print(name);
				
			}

			System.out.println();

			try {
				genFileForTestCase(testContext, testCase, false);
			}
			catch (IOException ex) {
				throw new RuntimeException("Failed to write test files", ex);
			}
		});
	}

	private static void genFileForTestCase(TestContext context, GEN_TestCase testCase, boolean overWrite) throws IOException {

		// Generate output file base on test 
		final String [] names = testCase.getFunctionalitySourceCodeNames();
		
		final File output = context.getFullPath(names);
		
		if (overWrite || !output.exists()) {
			
			// Make sure directory exists
			output.getParentFile().mkdirs();
			
			
			try (FileOutputStream out = new FileOutputStream(output)) {
				
				genForTestCase(context, testCase, out);
			}
		}
	}
	
	
	private static void genForTestCase(TestContext context, GEN_TestCase testCase, OutputStream out) throws IOException {

		
		try	(CodeWriter writer = new CodeWriter(out)) {
			genForTestCase(context, testCase, writer);
		}
	}

			
	private static void genForTestCase(TestContext context, GEN_TestCase testCase, CodeWriter writer) throws IOException {
		final String packageName = context.getPackageName();

		writer.append("package ").append(packageName).append(';')
			.newLine();
		
		
		writer.newLine();
		
		writer.append("import static org.assertj.core.api.Assertions.assertThat;").newLine();
		writer.append("import org.junit.Test;").newLine();
		writer.append("import com.neaterbits.query.jpatest.GEN_BaseTestCase;").newLine();

		writer
			.newLine()
			.newLine();

		final String className = context.getTestClassName(testCase.getFunctionalitySourceCodeNames());
		
		writer.append("public class ").append(className) .append(" extends GEN_BaseTestCase {").newLine();

		writer.addIndent();

		// Iterate over all possible tests
		for (EQueryResultGathering gathering : EQueryResultGathering.values()) {

			for (EQueryResultDimension dimension : EQueryResultDimension.values()) {

				for (EFieldAccessType accessType : EFieldAccessType.values()) {

					if (!testCase.isTestApplicable(gathering, dimension, accessType)) {
						continue;
					}

					writer.newLine().newLine();

					writer.indent().append("@Test").newLine();

					writer
					    .indent()
						.append("public void test")
						.append(StringUtils.camelCase(gathering.name()))
						.append(StringUtils.camelCase(dimension.name()))
						.append(StringUtils.camelCase(accessType.name()))
						.append("() {")
						.newLine();
					
					writer.addIndent();

					writer.indent().append("assertThat(true).isEqualTo(false);").newLine();

					writer.subIndent();

					writer.indent().append('}').newLine();
				}
			}
		}
		

		writer.subIndent();
		writer.indent().append('}')
			.newLine();
	}
	
	private static class TestContext {
		private File basePath;
		private String [] _package;
		
		TestContext(File basePath, String[] _package) {
			
			if (!basePath.exists()) {
				throw new IllegalArgumentException("base path does not exist");
			}
			
			this.basePath = basePath;
			this._package = _package;
		}
		
		String getTestClassName(String [] sourceCodeNames) {
			String name = StringUtils.join(sourceCodeNames);

			name += "Test";
			
			return name;
		}
		

		File getFullPath(String [] sourceCodeNames) {

			final String name = getTestClassName(sourceCodeNames) + ".java";
			

			final String fileName = basePath.getAbsolutePath() 
					+ '/' 
					+ StringUtils.join(_package, '/')
					+ '/'
					+ name;
					
					
			return new File(fileName);
		}

		String getPackageName() {
			return StringUtils.join(_package, '.');
		}
	}
}
