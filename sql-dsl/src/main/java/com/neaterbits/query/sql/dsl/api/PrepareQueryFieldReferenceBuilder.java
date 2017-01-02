package com.neaterbits.query.sql.dsl.api;


interface PrepareQueryFieldReferenceBuilder {
	void appendAliasFieldReference(StringBuilder sb, FieldReferenceAlias r);

	void appendEntityFieldReference(StringBuilder sb, FieldReferenceEntity r);

}
