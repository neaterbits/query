package com.neaterbits.query.jpatest;


import com.neaterbits.query.sql.dsl.api.BaseJPATest;
import com.neaterbits.query.sql.dsl.api.IShort;

import static org.assertj.core.api.Assertions.assertThat;

public class GEN_BaseTestCase extends BaseJPATest {
	protected static final IShort select = com.neaterbits.query.sql.dsl.api.IShortSelect.get();

	protected final void verifyIsCompilable(String s) {
		assertThat(true).isEqualTo(false);
	}
	
	protected final void verifyIsNotCompilable(String s) {
		assertThat(true).isEqualTo(false);
	}

	protected final void verifyIsCompilable(String alias, Class<?> aliasType, String s) {
		assertThat(true).isEqualTo(false);
	}

	protected final void verifyIsNotCompilable(String alias, Class<?> aliasType, String s) {
		assertThat(true).isEqualTo(false);
	}
}
