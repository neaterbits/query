package com.neaterbits.query.sql.dsl.api.entity;

import java.lang.reflect.Method;

public interface IEntityModelUtil {

	IEntity getEntityInfo(Class<?> type);
	
	String getEntityFieldNameForGetter(Class<?> type, Method getter);

	String getColumnNameForGetter(Class<?> type, Method getter);

	ETemporalType getTemporalTypeForGetter(Class<?> type, Method getter);
}
