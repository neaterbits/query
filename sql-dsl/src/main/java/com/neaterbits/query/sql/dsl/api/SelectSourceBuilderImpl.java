package com.neaterbits.query.sql.dsl.api;


abstract class SelectSourceBuilderImpl<MODEL, RESULT> extends BaseQueryEntity<MODEL>
		implements 
				SelectSourceBuilderTable<MODEL, RESULT>,
				SelectSourceBuilderAlias<MODEL, RESULT>,
				SelectSourceBuilderAliasAlias<MODEL, RESULT> {

//	private Class<?> [] classes;
//	private Alias<?> [] aliases;

	SelectSourceBuilderImpl(Class<?> resultType, ModelCompiler<MODEL> modelCompiler) {
		super(new QueryCollectorImpl(resultType), modelCompiler);
	}
	
	@Override
	public final WhereClauseBuilderTable<MODEL, RESULT> from(Class<?> ... classes) {
		
		if (classes.length == 0) {
			throw new IllegalArgumentException("no classes");
		}
		
//		this.classes = classes;
//		this.aliases = null;
		
		getQueryCollector().setSources(new SelectSourceClassesImpl(classes));
		
		final WhereClauseBuilderImpl<MODEL, RESULT> ret =  new WhereClauseBuilderImpl<MODEL, RESULT>(this);

		getQueryCollector().setClauses(ret.clauseCollector);
		
		return ret;
	}

	@Override
	public final WhereClauseBuilder<MODEL, RESULT> from(Alias<?> ... aliases) {
//		if (classes.length == 0) {
//			throw new IllegalArgumentException("no classes");
//		}
//
//		this.classes = null;
//		this.aliases = aliases;

		getQueryCollector().setSources(new SelectSourceAliasAliasesImpl(aliases));

		final WhereClauseBuilderImpl<MODEL, RESULT> ret = new WhereClauseBuilderImpl<MODEL, RESULT>(this);
		
		getQueryCollector().setClauses(ret.clauseCollector);
		
		return ret;
	}

	@Override
	public WhereClauseBuilderAlias<MODEL, RESULT> from(Object... aliases) {
		
		if (aliases.length == 0) {
			throw new IllegalArgumentException("no aliases");
		}
		
		getQueryCollector().setSources(new SelectSourceAliasesImpl(aliases));

		final WhereClauseBuilderImpl<MODEL, RESULT> ret = new WhereClauseBuilderImpl<MODEL, RESULT>(this);
		
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
