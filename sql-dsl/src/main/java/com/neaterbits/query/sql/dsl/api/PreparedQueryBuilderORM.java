package com.neaterbits.query.sql.dsl.api;

import java.util.List;

abstract class PreparedQueryBuilderORM extends PreparedQueryBuilder implements PrepareQueryFieldReferenceBuilder {

	final StringBuilder sb;

	//abstract String getSourceTypeName(Class<?> javaType);
	abstract void addSelectSource(StringBuilder sb, FieldReferenceType fieldReferenceType, SourceReference ref);

	PreparedQueryBuilderORM() {
		this.sb = new StringBuilder();
	}
	
	@Override
	final void select() {
		sb.append("SELECT ");
	}

	@Override
	final void addMappings(List<FieldReference> references) {
		
		
		QueryStringUtil.commaSeparated(sb, references, (s, r) -> {

			appendFieldReference(s, r);
			
		});
	}
	
	final void appendFieldReference(StringBuilder s ,FieldReference r) {
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

	@Override
	final void addSelectSources(FieldReferenceType fieldReferenceType, List<SourceReference> references) {

		sb.append("\n").append("FROM ");

		QueryStringUtil.commaSeparated(sb, references, (s, ref) -> {
			addSelectSource(s, fieldReferenceType, ref);
		});
	}

	@Override
	final void resolveFromParams(PreparedQueryConditionsBuilder conditionsBuilder, ParamValueResolver paramValueResolver) {
		conditionsBuilder.resolveFromParams(sb, paramValueResolver);
	}
}
