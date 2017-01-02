package com.neaterbits.query.sql.dsl.api;


final class PreparedQueryConditionsBuilderJPA extends PreparedQueryConditionsBuilderORM {

	private final PreparedQueryBuilderORM queryBuilderORM;
	
	PreparedQueryConditionsBuilderJPA(PreparedQueryBuilderORM queryBuilderORM, boolean atRoot) {
		super(queryBuilderORM, atRoot);

		this.queryBuilderORM = queryBuilderORM;
	}

	@Override
	PreparedQueryConditionsBuilder createConditionsBuilder(boolean atRoot) {
		return new PreparedQueryConditionsBuilderJPA(queryBuilderORM, atRoot);
	}
}
