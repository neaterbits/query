package com.neaterbits.query.sql.dsl.api;

import java.util.List;

public abstract class TransactionalDataStore<CTX, ENTITIES, TRANSACTION> extends DataStore<ENTITIES> {

	private final CTX ctx;

	final CTX getCtx() {
		return ctx;
	}

	protected final ENTITIES openEntities() {
		return intOpenEntities(ctx);
	}

	protected abstract ENTITIES intOpenEntities(CTX ctx);
	
	protected abstract List<?> executeSql(ENTITIES entities, String sql);
	
	protected abstract Object getPrimaryKey(Object instance);

	protected abstract TRANSACTION beginTransaction(ENTITIES entities);

	protected abstract void persist(ENTITIES entities, Object instance);

	protected abstract void remove(ENTITIES entities, Object instance, Object pk);

	protected abstract void commitTransaction(TRANSACTION transaction);

	protected abstract boolean isTransactionActive(TRANSACTION transaction);

	protected abstract void rollbackTransaction(TRANSACTION transaction);
	
	protected abstract void closeEntities(ENTITIES entities);

	protected TransactionalDataStore(CTX ctx) {
		this.ctx = ctx;
	}
}
