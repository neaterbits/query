package com.neaterbits.query.sql.dsl.api;


interface PrepareQueryFieldReferenceBuilder {
	void appendAliasFieldReference(QueryBuilder sb, FieldReferenceAlias ref);

	void appendEntityFieldReference(QueryBuilder sb, FieldReferenceEntity ref);

}
