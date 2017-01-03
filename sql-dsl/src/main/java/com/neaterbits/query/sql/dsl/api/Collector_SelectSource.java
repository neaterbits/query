package com.neaterbits.query.sql.dsl.api;


abstract class Collector_SelectSource<MODEL, RESULT> extends BaseQueryEntity<MODEL>
		implements 
				IClassicSelectSourceBuilderNamed<MODEL, RESULT>,
				IClassicSelectSourceBuilderAlias<MODEL, RESULT>,
				ISharedSelectSourceBuilderAliasAlias<MODEL, RESULT> {

//	private Class<?> [] classes;
//	private Alias<?> [] aliases;

	Collector_SelectSource(CollectedQueryResult result, ModelCompiler<MODEL> modelCompiler) {
		super(new QueryCollectorImpl(result), modelCompiler);
	}
	
	@Override
	public final IClassicWhereOrJoinBuilderNamed<MODEL, RESULT> from(Class<?> ... classes) {
		
		if (classes.length == 0) {
			throw new IllegalArgumentException("no classes");
		}
		
//		this.classes = classes;
//		this.aliases = null;
		
		getQueryCollector().setSources(new CollectedSelectSource_Named(classes));
		
		final ClassicWhereOrJoinClauseBuilder<MODEL, RESULT> ret =  new ClassicWhereOrJoinClauseBuilder<MODEL, RESULT>(this);

		getQueryCollector().setClauses(ret.clauseCollector);
		
		return ret;
	}

	@Override
	public final ISharedWhereClauseBuilder<MODEL, RESULT> from(Alias<?> ... aliases) {
//		if (classes.length == 0) {
//			throw new IllegalArgumentException("no classes");
//		}
//
//		this.classes = null;
//		this.aliases = aliases;

		getQueryCollector().setSources(new CollectedSelectSource_AliasAliases(aliases));

		final ClassicWhereOrJoinClauseBuilder<MODEL, RESULT> ret = new ClassicWhereOrJoinClauseBuilder<MODEL, RESULT>(this);
		
		getQueryCollector().setClauses(ret.clauseCollector);
		
		return ret;
	}

	@Override
	public final IClassicWhereOrJoinBuilderAlias<MODEL, RESULT> from(Object... aliases) {
		
		if (aliases.length == 0) {
			throw new IllegalArgumentException("no aliases");
		}
		
		getQueryCollector().setSources(new CollectedSelectSource_Aliases(aliases));

		final ClassicWhereOrJoinClauseBuilder<MODEL, RESULT> ret = new ClassicWhereOrJoinClauseBuilder<MODEL, RESULT>(this);
		
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
