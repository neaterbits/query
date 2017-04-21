package com.neaterbits.query.sql.dsl.api;

import java.util.List;

interface ICollectorClause {

	EConditionsClause getConditionsClause();

	ConditionsType getConditionsType();

	ICollectorClause addConditionsType(ConditionsType conditionsType);

	List<CollectedCondition> getConditions();
	
	boolean isEmpty();
	
	void add(CollectedCondition_Nested nested);

	void add(CollectedCondition_NonNested nonNested);
}
