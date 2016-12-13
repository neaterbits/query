package com.neaterbits.query.util.java8;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

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

	@SuppressWarnings("unchecked")
	private static <T> T createProxy(Class<T> cl, Enhancer enhancer) {
		final T proxy;
		
		try {
			proxy = (T)enhancer.create();
		}
		catch (RuntimeException ex) {
			throw new IllegalStateException("Failed to create proxy for class " + cl.getName(), ex);
		}

		return proxy;
	}
	
	private static <T> Method findMethodOrNull(Class<T> cl, Consumer<T> consumer) {
		
		if (cl == null) {
			throw new IllegalArgumentException("cl == null");
		}
		
		if (consumer == null) {
			throw new IllegalArgumentException("consumer == null");
		}
		
		final Enhancer enhancer = new Enhancer();
		
		enhancer.setSuperclass(cl);
		
		final MethodStore methodStore = new MethodStore();

		enhancer.setCallback(methodStore);
	
		final T proxy = createProxy(cl, enhancer);
		
		if (proxy == null) {
			throw new IllegalStateException("proxy == null");
		}
		
		consumer.accept(proxy);
		
		return methodStore.method == null ? null : methodStore.method;
	}
	
	public static <T> T enhance(Class<T> cl, Class<?> [] interfaces, InvocationHandler invocationHandler) {

		final Enhancer enhancer = new Enhancer();
		
		enhancer.setSuperclass(cl);
		
		if (interfaces != null) {
			enhancer.setInterfaces(interfaces);
		}
		
		final MethodInterceptor cgLibCallback = new MethodInterceptor() {

			@Override
			public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
				
				return invocationHandler.invoke(obj, method, args);
			}
		};
		
		enhancer.setCallback(cgLibCallback);
		
		final T proxy = createProxy(cl, enhancer);

		return proxy;
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

	public static <R> Method findOrNull(Class<R> cl, Supplier<R> func) {
		return findMethodOrNull(cl, o -> func.get());
	}
	
	public static <T, R, S> Method findOrNull(Class<T> cl, BiFunction<T, S, R> func) {
		return findMethodOrNull(cl, o -> func.apply(o, null));
	}

	public static <T, R> Method findOrNull(Class<T> cl, Consumer<T> func) {
		return findMethodOrNull(cl, o -> func.accept(o));
	}

	public static <T, S> Method findOrNull(Class<T> cl, BiConsumer<T, S> func) {
		
		if (cl == null) {
			throw new IllegalArgumentException("cl == null");
		}
		
		if (func == null) {
			throw new IllegalArgumentException("func == null");
		}
		
		final Consumer<T> consumer = o -> func.accept(o, null);
		
		return findMethodOrNull(cl, consumer);
	}

	public static <S, T, U> Method findOrNUll(Class<S> cl, TriConsumer<S, T, U> func) {
		return findMethodOrNull(cl, o -> func.accept(o, null, null));
	}
}
