package com.neaterbits.query.jpatest;


import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

import javax.tools.ToolProvider;

import com.neaterbits.query.sql.dsl.api.BaseJPATest;
import com.neaterbits.query.sql.dsl.api.CodeWriter;
import com.neaterbits.query.sql.dsl.api.IShort;

public class GEN_BaseTestCase extends BaseJPATest {

	protected static final IShort select = com.neaterbits.query.sql.dsl.api.IShortSelect.get();

	private static String getJavaClassName(File javaFile) {
		final String javaFileName = javaFile.getName();
		
		if (!javaFileName.endsWith(".java")) {
			throw new IllegalStateException("Not a java file");
		}
		
		return javaFileName.substring(0, javaFileName.length() - ".java".length());
	}
	
	private static class Alias {
		private final Class<?> aliasType;
		private final String alias;
		
		Alias(Class<?> aliasType, String alias) {
			if (aliasType == null) {
				throw new IllegalArgumentException("aliasType == null");
			}
			
			if (alias == null) {
				throw new IllegalArgumentException("alias == null");
			}

			this.aliasType = aliasType;
			this.alias = alias;
		}
	}
	
	private File writeToTempQueryClass(List<Alias> aliases, String s) {

		final File file; 

		try {
			file = File.createTempFile("query_unittest_try_compile", ".java");
		}
		catch (IOException ex) {
			throw new RuntimeException("Failed to create temp file", ex);
		}

		file.deleteOnExit();
		
		try (CodeWriter codeWriter = new CodeWriter(new FileOutputStream(file))) {
			
			writeQueryClass(codeWriter, getJavaClassName(file), aliases, s);
			
		}
		catch (IOException ex) {
			throw new RuntimeException("Failed to open file for output: " + file);
		}
		
		return file;
	}

	private void writeQueryClass(CodeWriter codeWriter, String className, List<Alias> aliases, String s) throws IOException {
		
		codeWriter.append("package com.neaterbits.query.compiletest;").newLine();
		
		codeWriter.newLine().newLine();
		codeWriter.append("import com.neaterbits.query.sql.dsl.api.IShort;").newLine();
		codeWriter.append("import com.neaterbits.query.test.model.mapped.FarmInfo;").newLine();
		codeWriter.append("import com.neaterbits.query.test.model.mapped.FarmLand;").newLine();
		codeWriter.append("import com.neaterbits.query.test.model.Farm;").newLine();
		codeWriter.append("import com.neaterbits.query.test.model.land.LandPlot;").newLine();
		codeWriter.append("import java.math.BigDecimal;").newLine();
		
		codeWriter.append("class ").append(className).append(" {").newLine();
		codeWriter.addIndent();

		// now write query selector
		codeWriter.append("protected static final IShort select = com.neaterbits.query.sql.dsl.api.IShortSelect.get();");
		
		
		codeWriter.newLine().newLine();

		if (aliases != null) {
			
			for (Alias alias : aliases) {
				codeWriter
				
					.append("private final ").append(alias.aliasType.getName()).append(' ').append(alias.alias)
					.append(" = select.alias(").append(alias.aliasType.getName()).append(".class);")
					.newLine();
			}
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
	
	
	private List<Alias> buildAliases(Consumer<AliasBuilder> aliasBuilder) {
		
		final List<Alias> ret = new ArrayList<>();
		
		final AliasBuilder ab = new AliasBuilder() {
			
			@Override
			public AliasBuilder add(Class<?> aliasType, String alias) {

				ret.add(new Alias(aliasType, alias));

				return this;
			}
		};
		
		aliasBuilder.accept(ab);
		
		return ret;
	}
	
	
	private boolean doesCompile(Consumer<AliasBuilder> aliasBuilder, String s) {
		
		final List<Alias> aliases = aliasBuilder == null ? null : buildAliases(aliasBuilder);
		
		final File file = writeToTempQueryClass(aliases, s);

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
		assertThat(doesCompile(null, s)).isEqualTo(true);
	}
	
	protected final void verifyIsNotCompilable(String s) {
		assertThat(doesCompile(null, s)).isEqualTo(false);
	}

	protected final void verifyIsCompilable(Class<?> aliasType, String alias, String s) {
		assertThat(doesCompile(ab -> ab.add(aliasType, alias), s)).isEqualTo(true);
	}

	protected final void verifyIsNotCompilable(Class<?> aliasType, String alias, String s) {
		assertThat(doesCompile(ab -> ab.add(aliasType, alias), s)).isEqualTo(false);
	}

	protected final void verifyIsCompilable(Consumer<AliasBuilder> aliasBuilder, String s) {
		assertThat(doesCompile(aliasBuilder, s)).isEqualTo(true);
	}

	protected final void verifyIsNotCompilable(Consumer<AliasBuilder> aliasBuilder, String s) {
		assertThat(doesCompile(aliasBuilder, s)).isEqualTo(false);
	}
	
	@FunctionalInterface
	protected interface AliasBuilder {
		AliasBuilder add(Class<?> aliasType, String alias);
	}
	
    
    protected static final Date date(String s) {
    	final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    	try {
    		return dateFormat.parse(s);
		}
		catch (ParseException ex) {
			throw new RuntimeException("Failed to parse \"" + s + "\"", ex);
		}
    }
}
