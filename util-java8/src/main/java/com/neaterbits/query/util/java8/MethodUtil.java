package com.neaterbits.query.util.java8;

import java.lang.reflect.Method;
import java.util.Arrays;

public class MethodUtil {
	// Get the class that contains the original definition for a method
	public static Class<?> getClassWithOriginalPrototypeForMethod(Method method) {
		return getClassWithOriginalPrototypeForMethod(method.getDeclaringClass(), method);
	}
	
	private static boolean sameMethod(Method m1, Method m2) {
		return     m1.getReturnType().equals(m2.getReturnType())
				&& m1.getName().equals(m2.getName())
				&& Arrays.equals(m1.getParameterTypes(), m2.getParameterTypes());
	}

	private static Class<?> getClassFromInterfaces(Class<?> curClass, Method method) {
		// Look at all interfaces and super-classes if method with same signature exists there
		for (Class<?> intf : curClass.getInterfaces()) {
			for (Method m : intf.getDeclaredMethods()) {
				if (sameMethod(m, method)) {
					return intf;
				}
			}

			// Check super interfaces recursively
			final Class<?> superIntf = getClassFromInterfaces(intf, method);
			if (superIntf != null) {
				return superIntf;
			}
		}
		
		return null;
	}
	
	private static Class<?> getClassWithOriginalPrototypeForMethod(Class<?> curClass, Method method) {


		// Not found in super-interfaces, look for abstract method in superclasses
		final Class<?> intf = getClassFromInterfaces(curClass, method);
		
		final Class<?> ret;
		if (intf != null) {
			ret = intf;
		}
		else {
		
		Class<?> whereFound = curClass;
		
			for (Class<?> superClass = curClass.getSuperclass(); superClass != null; superClass = superClass.getSuperclass()) {
				for (Method m : superClass.getDeclaredMethods()) {
					if (sameMethod(m, method)) {
						whereFound = superClass;
					}
				}
			}

			ret = whereFound;
		}

		return ret;
	}


}
