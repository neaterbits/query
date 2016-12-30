package com.neaterbits.query.sql.dsl.api.entity;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class EntityUtilTest {

	@Test
	public void testGetCollectionType() {
		
		final List<Integer> intList = Arrays.asList(1, 2, 3);
		
		final Class<?> type = EntityUtil.getGenericCollectionType(intList);
		
		// TODO: at all doable?
		// assertThat(type).isEqualTo(Integer.class);
	}
	
}
