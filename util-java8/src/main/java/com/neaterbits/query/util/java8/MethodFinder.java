package com.neaterbits.query.util.java8;

import java.lang.reflect.Method;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;



public class MethodFinder {

	private static class MethodStore implements MethodInterceptor {
		
		private Method method;
		
		@Override
		public Object intercept(Object obj, Method method, Object[] args,
				MethodProxy proxy) throws Throwable {
			
			this.method = method;
			
			return null;
		}
	}
	
	private static <T> Method findMethod(Class<T> cl, Consumer<T> consumer) {
		
		final Method ret = findMethodOrNull(cl, consumer);
		
		if (ret == null) {
			throw new IllegalStateException("Failed to get method");
		}
		
		return ret;
	}
		
	private static <T> Method findMethodOrNull(Class<T> cl, Consumer<T> consumer) {
		
		final Enhancer enhancer = new Enhancer();
		
		enhancer.setSuperclass(cl);
		
		final MethodStore methodStore = new MethodStore();

		enhancer.setCallback(methodStore);
		
		@SuppressWarnings("unchecked")
		final T proxy = (T)enhancer.create();
		
		consumer.accept(proxy);
		
		return methodStore.method == null ? null : methodStore.method;
	}

	public static <T, R> Method find(Class<T> cl, Function<T, R> func) {
		return findMethod(cl, o -> func.apply(o));
	}

	public static <T, R, S> Method find(Class<T> cl, BiFunction<T, S, R> func) {
		return findMethod(cl, o -> func.apply(o, null));
	}

	public static <T, R> Method find(Class<T> cl, Consumer<T> func) {
		return findMethod(cl, o -> func.accept(o));
	}

	public static <T, S> Method find(Class<T> cl, BiConsumer<T, S> func) {
		return findMethod(cl, o -> func.accept(o, null));
	}

	public static <S, T, U> Method find(Class<S> cl, TriConsumer<S, T, U> func) {
		return findMethod(cl, o -> func.accept(o, null, null));
	}

	// Find or null
	public static <T, R> Method findOrNull(Class<T> cl, Function<T, R> func) {
		return findMethodOrNull(cl, o -> func.apply(o));
	}

	public static <T, R, S> Method findOrNull(Class<T> cl, BiFunction<T, S, R> func) {
		return findMethodOrNull(cl, o -> func.apply(o, null));
	}

	public static <T, R> Method findOrNull(Class<T> cl, Consumer<T> func) {
		return findMethodOrNull(cl, o -> func.accept(o));
	}

	public static <T, S> Method findOrNull(Class<T> cl, BiConsumer<T, S> func) {
		return findMethodOrNull(cl, o -> func.accept(o, null));
	}

	public static <S, T, U> Method findOrNUll(Class<S> cl, TriConsumer<S, T, U> func) {
		return findMethodOrNull(cl, o -> func.accept(o, null, null));
	}
}
