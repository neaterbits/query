package com.neaterbits.query.sql.dsl.api;

import java.util.List;

import com.neaterbits.query.sql.dsl.api.entity.Relation;

abstract class QueryDialect_SQL extends QueryDialect_Base {

	abstract void appendAliasFieldReference(QueryBuilder sb, FieldReferenceAlias ref);

	abstract void appendEntityFieldReference(QueryBuilder sb, FieldReferenceEntity ref);
	
	
	/**
	 * Whether supports join fields natively, ie. ON table1.somefield = table2.somefiled.
	 * This is not true for JPQL and we have to simulate this by implicit join in WHERE and then add conditions to the WHERE clause 
	 *  
	 * @return
	 */
	abstract boolean supportsNonRelationJoins();
	
	//abstract void select();
	
	abstract void addEntityResult(QueryBuilder sb, FieldReferenceType fieldReferenceType, SourceReference sourceReference);
	
	abstract void addAggregateResult(QueryBuilder sb, EAggregateFunction function, FieldReference field);

	//abstract void addMappings(List<FieldReference> references);

	//abstract void addFromSelectSources(FieldReferenceType fieldReferenceType, List<SourceReference> references);
	
	abstract void addOneToManyJoin(QueryBuilder sb, Relation relation, FieldReferenceType fieldReferenceType, SourceReference from, SourceReference to);
	
	abstract void addComparisonJoin(QueryBuilder sb, List<JoinFieldComparison> fieldComparisons, SourceReference from, SourceReference to);
	
	// abstract void addOneToManyJoin(Relation relation, String entityAliasName, String collectionAttrName, String joinVarName);
	
	abstract void appendJoinStatement(QueryBuilder sb, EJoinType joinType);

	abstract void addSelectSource(QueryBuilder sb, FieldReferenceType fieldReferenceType, SourceReference ref);
	
	
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

	//@Override
	final void addMappings(QueryBuilder sb, List<FieldReference> references) {
		appendFieldReferences(sb, references);
	}
	
	private void appendFieldReferences(QueryBuilder sb,  List<FieldReference> fieldReferences) {
		QueryStringUtil.commaSeparated(sb, fieldReferences, (s, r) -> {

			appendFieldReference(s, r);
			
		});
	}
	
	final void appendFieldReference(QueryBuilder s ,FieldReference r) {
		if (r instanceof FieldReferenceAlias) {
			appendAliasFieldReference(s, (FieldReferenceAlias)r);
		}
		else if (r instanceof FieldReferenceEntity) {
			appendEntityFieldReference(s, (FieldReferenceEntity)r);
		}
		else {
			throw new UnsupportedOperationException("Unknown field reference type " + r.getClass().getName());
		}
	}

	//@Override
	final void addFromSelectSources(QueryBuilder sb, FieldReferenceType fieldReferenceType, List<SourceReference> references) {

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