package com.neaterbits.query.jpatest;


import com.neaterbits.query.sql.dsl.api.BaseJPATest;
import com.neaterbits.query.sql.dsl.api.CodeWriter;
import com.neaterbits.query.sql.dsl.api.IShort;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.tools.ToolProvider;

public class GEN_BaseTestCase extends BaseJPATest {

	protected static final IShort select = com.neaterbits.query.sql.dsl.api.IShortSelect.get();

	private static String getJavaClassName(File javaFile) {
		final String javaFileName = javaFile.getName();
		
		if (!javaFileName.endsWith(".java")) {
			throw new IllegalStateException("Not a java file");
		}
		
		return javaFileName.substring(0, javaFileName.length() - ".java".length());
	}
	
	private File writeToTempQueryClass(String alias, Class<?> aliasType, String s) {

		final File file; 

		try {
			file = File.createTempFile("query_unittest_try_compile", ".java");
		}
		catch (IOException ex) {
			throw new RuntimeException("Failed to create temp file", ex);
		}

		file.deleteOnExit();
		
		try (CodeWriter codeWriter = new CodeWriter(new FileOutputStream(file))) {
			
			writeQueryClass(codeWriter, getJavaClassName(file), alias, aliasType, s);
			
		}
		catch (IOException ex) {
			throw new RuntimeException("Failed to open file for output: " + file);
		}
		
		return file;
	}

	private void writeQueryClass(CodeWriter codeWriter, String className, String alias, Class<?> aliasType, String s) throws IOException {
		
		codeWriter.append("package com.neaterbits.query.compiletest;").newLine();
		
		codeWriter.newLine().newLine();
		codeWriter.append("import com.neaterbits.query.sql.dsl.api.IShort;").newLine();
		codeWriter.append("import com.neaterbits.query.test.model.mapped.FarmInfo;").newLine();
		codeWriter.append("import com.neaterbits.query.test.model.Farm;").newLine();
		codeWriter.append("import com.neaterbits.query.test.model.land.LandPlot;").newLine();
		codeWriter.append("import java.math.BigDecimal;").newLine();
		
		codeWriter.append("class ").append(className).append(" {").newLine();
		codeWriter.addIndent();

		// now write query selector
		codeWriter.append("protected static final IShort select = com.neaterbits.query.sql.dsl.api.IShortSelect.get();");
		
		
		codeWriter.newLine().newLine();

		if (alias != null && aliasType != null) {
		
			codeWriter.append("private final ").append(aliasType.getName()).append(' ').append(alias).append(" = select.alias(").append(aliasType.getName()).append(".class);")
					.newLine();
		}
		else if (alias != null || aliasType != null) {
			throw new IllegalArgumentException("one of alias or alias type set");
		}

		codeWriter.append("public void testMethod() {").newLine();
		
		codeWriter.addIndent();
		
		codeWriter.append("select.").append(s).append(';');

		codeWriter.subIndent();
		
		codeWriter.append('}')
		.newLine();
		
		codeWriter.append('}')
			.newLine();
	}
	
	private boolean doesCompile(String alias, Class<?> aliasType, String s) {
		final File file = writeToTempQueryClass(alias, aliasType, s);

		final String classFileName =  getJavaClassName(file)
					+ ".class";
		
		final File classFile = new File(file.getParentFile(), classFileName); 

		boolean ret = false;

		try (FileInputStream inputStream = new FileInputStream(file)){
			final int exitCode = ToolProvider.getSystemJavaCompiler().run(
					System.in,
					System.out,
					System.err,
					file.getAbsolutePath());

			ret = exitCode == 0;

			if (ret && !classFile.exists()) {
				throw new RuntimeException("Returned OK but no class file at " + classFile.getAbsolutePath());
			}
		}
		catch (IOException ex) {
			throw new RuntimeException("Failed to open input file " + file);
		}
		finally {
			file.delete();
			
			// Delete class file too
			classFile.delete();
		}

		return ret;
	}
	
	protected final void verifyIsCompilable(String s) {
		assertThat(doesCompile(null, null, s)).isEqualTo(true);
	}
	
	protected final void verifyIsNotCompilable(String s) {
		assertThat(doesCompile(null, null, s)).isEqualTo(false);
	}

	protected final void verifyIsCompilable(String alias, Class<?> aliasType, String s) {
		assertThat(doesCompile(alias, aliasType, s)).isEqualTo(true);
	}

	protected final void verifyIsNotCompilable(String alias, Class<?> aliasType, String s) {
		assertThat(doesCompile(alias, aliasType, s)).isEqualTo(false);
	}
}
