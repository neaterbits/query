package com.neaterbits.query.sql.dsl.api;

import java.util.List;
import java.util.function.BiConsumer;

class PreparedQueryBuilderUtil {

	static void resolveFunction(QueryDialect_SQL dialect, List<? extends FunctionBase> funcs, FieldReference field, QueryBuilder sb) {
		final AppendNextFunction appendNext = new AppendNextFunction(funcs, field, dialect);

		resolveFunction(dialect, funcs.get(0), 0, sb, appendNext);
	}
	
	private static void resolveFunction(QueryDialect_SQL dialect, FunctionBase function, int idx, QueryBuilder sb, BiConsumer<Integer, QueryBuilder> appendNext) {
		
		final String functionName = dialect.getFunctionName(function);
		
		sb.append(functionName).append('(');
		
		// recursively append
		appendNext.accept(idx, sb);
		
		sb.append(')');
	}

	private static class AppendNextFunction implements BiConsumer<Integer, QueryBuilder> {

		private final List<? extends FunctionBase> funcs;
		private final FieldReference field;
		private final QueryDialect_SQL dialect;
		private final int num;
		
		AppendNextFunction(List<? extends FunctionBase> funcs, FieldReference field, QueryDialect_SQL dialect) {

			if (funcs == null) {
				throw new IllegalArgumentException("funcs == null");
			}
			
			if (field == null) {
				throw new IllegalArgumentException("field == null");
			}
			
			if (dialect == null) {
				throw new IllegalArgumentException("dialect == null");
			}

			this.funcs = funcs;
			this.field = field;
			this.dialect = dialect;
			this.num = funcs.size();
		 }


		@Override
		public void accept(Integer idx, QueryBuilder sb) {
			
			if (idx < 0) {
				throw new IllegalArgumentException("idx < 0");
			}
			else if (idx == num - 1) {
				// at last idx, return getter
				dialect.appendFieldReference(sb, field);
			}
			else if (idx < num - 1) {
				// resolve next
				final int nextIdx = idx + 1;
				
				final FunctionBase func = funcs.get(nextIdx);
				
				// resolve next call
				PreparedQueryBuilderUtil.resolveFunction(dialect, func, nextIdx, sb, this);
			}
			else {
				throw new IllegalStateException("idx out of range");
			}
		}
	}
}
