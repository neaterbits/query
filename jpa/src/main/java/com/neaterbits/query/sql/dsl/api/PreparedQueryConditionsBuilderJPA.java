package com.neaterbits.query.sql.dsl.api;

import java.util.function.BiConsumer;

final class PreparedQueryConditionsBuilderJPA extends PreparedQueryConditionsBuilderORM {

	private final PreparedQueryBuilderORM queryBuilderORM;
	
	PreparedQueryConditionsBuilderJPA(PreparedQueryBuilderORM queryBuilderORM, EConditionsClause conditionsClause, boolean atRoot) {
		super(queryBuilderORM, conditionsClause, atRoot);

		this.queryBuilderORM = queryBuilderORM;
	}

	@Override
	PreparedQueryConditionsBuilder createConditionsBuilder(EConditionsClause conditionsClause, boolean atRoot) {
		return new PreparedQueryConditionsBuilderJPA(queryBuilderORM, conditionsClause, atRoot);
	}

	@Override
	void resolveFunction(FunctionBase function, int idx, QueryBuilder sb, BiConsumer<Integer, QueryBuilder> appendNext) {
		
		final String functionName = function.visit(functionToNameVisitor, null);
		
		sb.append(functionName).append('(');
		
		// recursively append
		appendNext.accept(idx, sb);
		
		sb.append(')');
	}
	
	private static final FunctionVisitor<Void, String> functionToNameVisitor = new FunctionVisitor<Void, String>() {
		
		@Override
		public String onStringUpper(Function_String_Upper function, Void param) {
			return "upper";
		}
		
		@Override
		public String onStringTrim(Function_String_Trim function, Void param) {
			return "trim";
		}
		
		@Override
		public String onStringLower(Function_String_Lower function, Void param) {
			return "lower";
		}
		
		@Override
		public String onArithmeticSqrt(Function_Arithmetic_Sqrt function, Void param) {
			return "sqrt";
		}
		
		@Override
		public String onArithmeticAbs(Function_Arithmetic_Abs function, Void param) {
			return "abs";
		}
	};
}
