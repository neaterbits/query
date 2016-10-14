package com.neaterbits.gui.sql.dsl.impl.standalone;

import java.util.HashMap;
import java.util.Map;

import com.neaterbits.gui.sql.dsl.api.standalone.Alias;
import com.neaterbits.gui.sql.dsl.api.standalone.SelectAPI;
import com.neaterbits.gui.sql.dsl.api.standalone.SingleResultToResult;
import com.neaterbits.util.IdentityKey;

final class SelectAPIImpl implements SelectAPI {

	private final Map<IdentityKey<?>, AliasImpl<?>> aliases;

	public SelectAPIImpl() {
		this.aliases = new HashMap<IdentityKey<?>, AliasImpl<?>>();
	}

	@Override
	public <RESULT> SingleResultToResult<RESULT> selectSingle(Class<RESULT> cl) {
		
		if (cl == null) {
			throw new IllegalArgumentException("cl == null");
		}

		
		return null;
	}

	@Override
	public <TABLE> Alias<TABLE> alias(Class<TABLE> aliasType) {

		if (aliasType == null) {
			throw new IllegalArgumentException("aliasType == null");
		}

		final AliasImpl<TABLE> alias = new AliasImpl<TABLE>(aliasType);
		final IdentityKey<AliasImpl<?>> key = new IdentityKey<AliasImpl<?>>(alias);

		aliases.put(key, alias);

		return alias;
	}
}

