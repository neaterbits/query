package com.neaterbits.query.sql.dsl.api;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import com.neaterbits.util.StringUtils;

public final class CodeWriter  implements AutoCloseable {
	
	private int indent;
	
	private final Writer writer;

	public CodeWriter(OutputStream out) {
		this.writer = new OutputStreamWriter(out);
		this.indent = 0;
	}
	
	public CodeWriter addIndent() {
		++ indent;
		
		return this;
	}
	
	
	public CodeWriter subIndent() {

		if (indent == 0) {
			throw new IllegalStateException("already at 0");
		}
		-- indent;

		return this;
	}
	
	public CodeWriter indent() throws IOException {
		return append(StringUtils.indent(indent * 4));
	}
	
	
	public CodeWriter newLine() throws IOException {
		writer.append('\n');
		
		return this;
	}

	public CodeWriter append(String s) throws IOException {
		
		if (s == null) {
			throw new IllegalArgumentException("s == null");
		}

		writer.append(s);

		return this;
	}

	public CodeWriter append(char c) throws IOException {
		
		writer.append(c);

		return this;
	}

	@Override
	public void close() throws IOException {
		writer.close();
	}
}
