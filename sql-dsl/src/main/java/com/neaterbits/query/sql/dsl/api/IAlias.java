package com.neaterbits.query.sql.dsl.api;

import java.lang.reflect.Method;

public interface IAlias {

	Class<?> getType();
	
	Method getLastInvokedMethod();
}
