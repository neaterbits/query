package com.neaterbits.query.sql.dsl.api;


interface PrepareQueryFieldReferenceBuilder {
	void appendAliasFieldReference(StringBuilder sb, FieldReferenceAlias ref);

	void appendEntityFieldReference(StringBuilder sb, FieldReferenceEntity ref);

}
