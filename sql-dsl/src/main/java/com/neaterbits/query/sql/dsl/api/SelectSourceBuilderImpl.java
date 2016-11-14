package com.neaterbits.query.sql.dsl.api;


abstract class SelectSourceBuilderImpl<RESULT> extends BaseQueryEntity implements SelectSourceBuilder<RESULT> {

//	private Class<?> [] classes;
//	private Alias<?> [] aliases;

	SelectSourceBuilderImpl(Class<?> resultType) {
		super(new QueryCollectorImpl(resultType));
	}
	
	@Override
	public final WhereClauseBuilder<Void, ?> from(Class<?> ... classes) {
		
		if (classes.length == 0) {
			throw new IllegalArgumentException("no classes");
		}
		
//		this.classes = classes;
//		this.aliases = null;
		
		getQueryCollector().setSources(new SelectSourceClassesImpl(classes));
		
		final WhereClauseBuilderImpl<Void, ?> ret =  new WhereClauseBuilderImpl<Void, RESULT>(this);

		getQueryCollector().setClauses(ret.clauseCollector);
		
		return ret;
	}

	@Override
	public final WhereClauseBuilder<Void, ?> from(Alias<?> ... aliases) {
//		if (classes.length == 0) {
//			throw new IllegalArgumentException("no classes");
//		}
//
//		this.classes = null;
//		this.aliases = aliases;

		getQueryCollector().setSources(new SelectSourceAliasesImpl(aliases));

		final WhereClauseBuilderImpl<Void, ?> ret = new WhereClauseBuilderImpl<Void, RESULT>(this);
		
		getQueryCollector().setClauses(ret.clauseCollector);
		
		return ret;
	}

//	Class<?>[] getClasses() {
//		return classes;
//	}
//
//	Alias<?>[] getAliases() {
//		return aliases;
//	}
}
