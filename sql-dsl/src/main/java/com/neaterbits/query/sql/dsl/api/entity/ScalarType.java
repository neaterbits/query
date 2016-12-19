package com.neaterbits.query.sql.dsl.api.entity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public enum ScalarType {

	BYTE	(byte.class, 		Byte.class),
	CHAR	(char.class, 		Character.class),
	SHORT	(short.class, 		Short.class),
	INTEGER	(int.class, 		Integer.class),
	LONG	(long.class, 		Long.class),
	FLOAT	(float.class, 		Float.class),
	DOUBLE	(double.class, 		Double.class),
	BOOLEAN	(boolean.class, 	Boolean.class),

	STRING	(null, 				String.class),
	DECIMAL	(null, 				BigDecimal.class),
	BIGINT	(null, 				BigInteger.class),
	DATE	(null, 				Date.class)
	;

	private static final Map<Class<?>, ScalarType> byType;

	static {

		byType = new HashMap<>();

		for (ScalarType st : values()) {
			if (st.primitiveClass != null) {
				if (byType.put(st.primitiveClass, st) != null) {
					throw new IllegalStateException("Already set: " + st);
				}
			}

			if (byType.put(st.objectType, st) != null) {
				throw new IllegalStateException("Already set: " + st);
			}
		}
	}

	private final Class<?> primitiveClass;
	private final Class<?> objectType;

	private ScalarType(Class<?> primitiveClass, Class<?> objectType) {
		
		if (objectType == null) {
			throw new IllegalArgumentException("objectType == null");
		}
		
		this.primitiveClass = primitiveClass;
		this.objectType = objectType;
	}
	
	public static ScalarType fromType(Class<?> type) {
		
		if (type == null) {
			throw new IllegalArgumentException("type == null");
		}

		return byType.get(type);
	}
}


