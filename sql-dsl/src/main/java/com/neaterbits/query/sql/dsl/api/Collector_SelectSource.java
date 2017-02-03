package com.neaterbits.query.sql.dsl.api;


abstract class Collector_SelectSource<
				MODEL,
				RESULT,
				NAMED_WHERE_OR_JOIN extends IClassicLogical_WhereOrJoin_Named_Base<MODEL, RESULT>,
				ALIAS_WHERE_OR_JOIN extends IClassicLogical_WhereOrJoin_Alias_Base<MODEL, RESULT>> 

		extends BaseQueryEntity<MODEL>
		implements 
				IClassic_From_Named_Base<MODEL, RESULT, NAMED_WHERE_OR_JOIN>,
				IClassic_From_Alias_Base<MODEL, RESULT, ALIAS_WHERE_OR_JOIN>,
				IShared_From_AliasAlias<MODEL, RESULT> {

//	private Class<?> [] classes;
//	private Alias<?> [] aliases;

	Collector_SelectSource(CollectedQueryResult result, ModelCompiler<MODEL> modelCompiler) {
		super(new QueryCollectorImpl(result), modelCompiler);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public final NAMED_WHERE_OR_JOIN from(Class<?> ... classes) {
		
		if (classes.length == 0) {
			throw new IllegalArgumentException("no classes");
		}
		
//		this.classes = classes;
//		this.aliases = null;
		
		getQueryCollector().setSources(new CollectedSelectSource_Named(classes));
		
		final Classic_Collector_Where_Or_Join_Named<MODEL, RESULT> ret =  new Classic_Collector_Where_Or_Join_Named<MODEL, RESULT>(this);
		
		return (NAMED_WHERE_OR_JOIN)ret;
	}

	@Override
	public final ISharedLogical_Where<MODEL, RESULT> from(Alias<?> ... aliases) {
//		if (classes.length == 0) {
//			throw new IllegalArgumentException("no classes");
//		}
//
//		this.classes = null;
//		this.aliases = aliases;

		getQueryCollector().setSources(new CollectedSelectSource_AliasAliases(aliases));

		final Classic_Collector_Where_Or_Join_Alias<MODEL, RESULT> ret = new Classic_Collector_Where_Or_Join_Alias<MODEL, RESULT>(this);
		
		return ret;
	}

	@SuppressWarnings("unchecked")
	@Override
	public final ALIAS_WHERE_OR_JOIN from(Object... aliases) {
		
		if (aliases.length == 0) {
			throw new IllegalArgumentException("no aliases");
		}
		
		getQueryCollector().setSources(new CollectedSelectSource_Aliases(aliases));

		final Classic_Collector_Where_Or_Join_Alias<MODEL, RESULT> ret = new Classic_Collector_Where_Or_Join_Alias<MODEL, RESULT>(this);
		
		return (ALIAS_WHERE_OR_JOIN)ret;
	}
	

//	Class<?>[] getClasses() {
//		return classes;
//	}
//
//	Alias<?>[] getAliases() {
//		return aliases;
//	}
}
