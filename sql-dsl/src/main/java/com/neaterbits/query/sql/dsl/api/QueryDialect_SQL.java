package com.neaterbits.query.sql.dsl.api;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.List;

import com.neaterbits.query.sql.dsl.api.entity.IEntityModelUtil;
import com.neaterbits.query.sql.dsl.api.entity.Relation;

abstract class QueryDialect_SQL extends QueryDialect_Base {

	abstract ConditionStringBuilder makeConditionStringBuilder(QueryParametersDistinct distinctParams);
	
	abstract void appendAliasFieldReference(QueryBuilder sb, FieldReference_Alias ref);

	abstract void appendEntityFieldReference(QueryBuilder sb, FieldReference_Entity ref);
	
	
	/**
	 * Whether supports join fields natively, ie. ON table1.somefield = table2.somefiled.
	 * This is not true for JPQL and we have to simulate this by implicit join in WHERE and then add conditions to the WHERE clause 
	 *  
	 * @return
	 */
	abstract boolean supportsNonRelationJoins();
	
	/**
	 * Whether requires selected fields to be in From EVEN if in join,
	 * eg native query select.sum(LandPlot::getHectares).innerJoin(Farm::getLandPlots)
	 * requires to have FROM farm, land_plot , so cannot just remove land_plot from 'from' even if part of inner-join
	 */
	
	// abstract boolean requiresSelectedFieldsAsPartOfFrom();
	
	
	//abstract void select();
	
	abstract void addEntityResult(QueryBuilder sb, EFieldAccessType fieldReferenceType, SourceReference sourceReference);
	
	//abstract void addAggregateResult(QueryBuilder sb, EAggregateFunction function, FieldReference field);

	//abstract void addFromSelectSources(FieldReferenceType fieldReferenceType, List<SourceReference> references);
	
	abstract void addOneToManyJoin(QueryBuilder sb, Relation relation, EFieldAccessType fieldReferenceType, SourceReference from, SourceReference to);
	
	abstract void addComparisonJoin(QueryBuilder sb, List<JoinFieldComparison> fieldComparisons, SourceReference from, SourceReference to);
	
	// abstract void addOneToManyJoin(Relation relation, String entityAliasName, String collectionAttrName, String joinVarName);
	
	abstract void appendJoinStatement(QueryBuilder sb, EJoinType joinType);

	abstract void addSelectSource(QueryBuilder sb, EFieldAccessType fieldReferenceType, SourceReference ref);

	abstract String getFieldNameForGetter(IEntityModelUtil entityModelUtil, Class<?> type, Method getter);

	abstract String getBigDecimalLiteral(BigDecimal value);

	final IEntityModelUtil entityModelUtil;

	protected QueryDialect_SQL(IEntityModelUtil entityModelUtil) {
		
		if (entityModelUtil == null) {
			throw new IllegalArgumentException("entityModelUtil == null");
		}

		this.entityModelUtil = entityModelUtil;
	}
	
	
	// **************************** Some abstract methods conditions ****************************
	
	/**
	 * Get name of function as appears in query string
	 * @return
	 */
	String getFunctionName(FunctionBase function) {
		// default function names
		return function.visit(functionToNameVisitor, null);
	}
	
	
	
	// indices starting at 1
	// abstract void appendGroupBy(List<FieldReference> fieldReferences);

	//@Override
	final void select(QueryBuilder sb) {
		sb.append("SELECT ");
	}
	
	String getFunctionName(EAggregateFunction function) {
		return function.name();
	}
	
	void addAggregateResult(QueryBuilder sb, EAggregateFunction function, FieldReference field) {
		
		switch (function) {
		case SUM:
		case MIN:
		case MAX:
		case AVG:
		case COUNT:
			
			sb.append(getFunctionName(function)).append(" (");

			appendFieldReference(sb, field);

			sb.append(")");
			break;
			
			

		default:
			throw new UnsupportedOperationException("Unknown aggregate: " + function);
		}
	}

	

	private void appendFieldReferences(QueryBuilder sb,  List<FieldReference> fieldReferences) {
		QueryStringUtil.commaSeparated(sb, fieldReferences, (s, r) -> {

			appendFieldReference(s, r);
			
		});
	}
	
	final void appendFieldReference(QueryBuilder s, FieldReference r) {
		if (r instanceof FieldReference_Alias) {
			appendAliasFieldReference(s, (FieldReference_Alias)r);
		}
		else if (r instanceof FieldReference_Entity) {
			appendEntityFieldReference(s, (FieldReference_Entity)r);
		}
		else {
			throw new UnsupportedOperationException("Unknown field reference type " + r.getClass().getName());
		}
	}

	//@Override
	final void addFromSelectSources(QueryBuilder sb, EFieldAccessType fieldReferenceType, List<SourceReference> references) {

		sb.append("\n").append("FROM ");

		QueryStringUtil.commaSeparated(sb, references, (s, ref) -> {
			addSelectSource(s, fieldReferenceType, ref);
		});
	}
	
	//@Override
	final void appendGroupBy(QueryBuilder sb, List<FieldReference> fieldReferences) {
		sb.append("\nGROUP BY ");

		appendFieldReferences(sb, fieldReferences);
	}

	//@Override
	final void appendOrderBy(QueryBuilder sb, List<OrderByReference> orderByReferences) {
		sb.append("\nORDER BY ");

		QueryStringUtil.commaSeparated(sb, orderByReferences, (s, r) -> {

			appendFieldReference(s, r.getField());
			
			if (r.getSortOrder() != null) {
				s.append(' ').append(r.getSortOrder() == ESortOrder.ASCENDING ? "asc" : "desc");
			}
		});
	}

	private static final FunctionVisitor<Void, String> functionToNameVisitor = new FunctionVisitor<Void, String>() {

		public String onAggregate(Function_Aggregate function, Void param) {
			return function.getFunction().name().toLowerCase();
		}

		@Override
		public String onStringLength(Function_String_Length function, Void param) {
			return "length";
		}

		@Override
		public String onStringLower(Function_String_Lower function, Void param) {
			return "lower";
		}

		@Override
		public String onStringUpper(Function_String_Upper function, Void param) {
			return "upper";
		}
		
		@Override
		public String onStringTrim(Function_String_Trim function, Void param) {
			return "trim";
		}

		@Override
		public String onStringSubstring(Function_String_Substring function, Void param) {
			return "substring";
		}

		@Override
		public String onArithmeticSqrt(Function_Arithmetic_Sqrt function, Void param) {
			return "sqrt";
		}
		
		@Override
		public String onArithmeticAbs(Function_Arithmetic_Abs function, Void param) {
			return "abs";
		}
		
		@Override
		public String onArithmeticMod(Function_Arithmetic_Mod function, Void param) {
			return "mod";
		}

		@Override
		public String onStringConcat(Function_String_Concat function, Void param) {
			return "concat";
		}
	};
	
}
