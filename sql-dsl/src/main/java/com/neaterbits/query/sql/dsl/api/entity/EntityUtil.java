package com.neaterbits.query.sql.dsl.api.entity;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class EntityUtil {
	

	public static Class<?> getGenericCollectionType(Collection<?> collection) {
	
		final Class<?> type = collection.getClass();
		
		final Type [] genericTypes = type.getGenericInterfaces();
		final Type genericSuperClass = type.getGenericSuperclass();
		
		System.out.println("Type: " + type);
		System.out.println("Generic types: " + Arrays.toString(genericTypes));
		System.out.println("Actual types: " + Arrays.toString(type.getTypeParameters()));

		System.out.println("Type parameters: " + Arrays.toString(type.getTypeParameters()));
		System.out.println("Generic supertype: " + type.getGenericSuperclass());
		System.out.println("Generic supertype type: " + genericSuperClass.getClass().getSimpleName());
		
		final Class<?> ret;
		
		if (genericSuperClass instanceof ParameterizedType) {
			final ParameterizedType parameterizedType = (ParameterizedType)genericSuperClass;
			
			final Type [] args = parameterizedType.getActualTypeArguments();
			
			System.out.println("Type parameters: " + Arrays.toString(args));
			
			if (args.length != 1) {
				throw new IllegalArgumentException("expected one generic arg");
			}
			
			final TypeVariable<?> arg0 = (TypeVariable<?>)args[0];
			
			System.out.println("Arg 0: " + arg0.getClass().getName());
			
			ret = null; // (Class<?>)arg0;
		}
		else {
			ret = null;
		}
		

		return ret;
	}
	
	public static Class<?> getGenericCollectionMemberType(AccessibleObject accessible) {
		
		if (accessible == null) {
			throw new IllegalArgumentException("accessible == null");
		}

		final Type genericType;
		final Class<?> cl;
		
		if (accessible instanceof Field) {
			
			final Field f = (Field)accessible;
			
			genericType = f.getGenericType();
			cl = f.getType();
		}
		else if (accessible instanceof Method) {
			final Method m = (Method)accessible;
			
			if (m.getParameterTypes().length != 0) {
				throw new UnsupportedOperationException("Expected getter");
			}
			
			genericType = m.getGenericReturnType();
			cl = m.getReturnType();
		}
		else {
			throw new UnsupportedOperationException("Unsupported accessible type: " + accessible.getClass().getName());
		}
		
		if (!
				(    Collection.class.equals(cl)
			      || List.class.equals(cl)
			      || Set.class.equals(cl))				
		   ) {
			throw new UnsupportedOperationException("Not a supported collection class: " + cl);
		}

		final Class<?> ret;

		if (genericType instanceof ParameterizedType) {

			final ParameterizedType parameterizedType = (ParameterizedType)genericType;
			final Type[] typeArgs = parameterizedType.getActualTypeArguments();

			if (typeArgs.length != 1) {
				throw new UnsupportedOperationException("Expected exactly one type arg, got " + Arrays.toString(typeArgs));
			}

			ret = (Class<?>)typeArgs[0];
		}
		else {
			ret = null;
		}

		return ret;
	}
}

