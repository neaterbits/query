package com.neaterbits.query.util.java8;

import java.lang.reflect.Method;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MethodUtilTest {

	interface TestInterface {
		void foo(int arg);
	}
	
	static class TestClass implements TestInterface{
		
		private int bar;

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + bar;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			TestClass other = (TestClass) obj;
			if (bar != other.bar)
				return false;
			return true;
		}

		@Override
		public void foo(int arg) {
		}
	}
	
	@Test
	public void testFindClassMethod() throws NoSuchMethodException, SecurityException {
		
		final Method equals = TestClass.class.getMethod("equals", Object.class);
		
		assertThat(equals).isNotNull();
		assertThat(equals.getDeclaringClass()).isEqualTo(TestClass.class);
		
		final Class<?> original = MethodUtil.getClassWithOriginalPrototypeForMethod(equals);
		
		assertThat(original).isEqualTo(Object.class);
	}
	
	@Test
	public void testFindInterfaceMethod() throws NoSuchMethodException, SecurityException {
		
		final Method foo = TestClass.class.getMethod("foo", int.class);
		
		assertThat(foo).isNotNull();
		assertThat(foo.getDeclaringClass()).isEqualTo(TestClass.class);
		
		final Class<?> original = MethodUtil.getClassWithOriginalPrototypeForMethod(foo);
		
		assertThat(original).isEqualTo(TestInterface.class);
	}
}
