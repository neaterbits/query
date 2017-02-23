package com.neaterbits.query.sql.dsl.api;


abstract class Collector_SelectSource<
				MODEL,
				RESULT,
				NAMED_WHERE_OR_JOIN extends IClassicLogical_WhereOrJoin_Named_Base<MODEL, RESULT>,
				ALIAS_WHERE_OR_JOIN extends IClassicLogical_WhereOrJoin_Alias_Base<MODEL, RESULT>> 

		extends Collector_Base<MODEL>
		implements 
				IClassic_From_Named_Base<MODEL, RESULT, NAMED_WHERE_OR_JOIN>,
				IClassic_From_Alias_Base<MODEL, RESULT, ALIAS_WHERE_OR_JOIN>,
				IShared_From_AliasAlias<MODEL, RESULT> {

//	private Class<?> [] classes;
//	private Alias<?> [] aliases;

	abstract NAMED_WHERE_OR_JOIN createWhereOrJoinForNamed();
	abstract ALIAS_WHERE_OR_JOIN createWhereOrJoinForAlias();
					
	Collector_SelectSource(CollectedQueryResult result, ModelCompiler<MODEL> modelCompiler) {
		super(new QueryCollectorImpl<MODEL>(modelCompiler, result));
	}
	
	@Override
	public final NAMED_WHERE_OR_JOIN from(Class<?> ... classes) {
		
		if (classes.length == 0) {
			throw new IllegalArgumentException("no classes");
		}
		
//		this.classes = classes;
//		this.aliases = null;
		
		getQueryCollector().setSources(new CollectedSelectSource_Named(classes));
		
		//final Classic_Collector_Where_Or_Join_Named<MODEL, RESULT> ret = new Classic_Collector_Where_Or_Join_Named<MODEL, RESULT>(this);
		
		return createWhereOrJoinForNamed();
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

		// final Classic_Collector_WhereOrJoin_Alias_Base<MODEL, RESULT, ?> ret = new Classic_Collector_WhereOrJoin_Alias_Base<MODEL, RESULT>(this);
		
		return createWhereOrJoinForAlias();
	}

	@Override
	public final ALIAS_WHERE_OR_JOIN from(Object... aliases) {
		
		if (aliases.length == 0) {
			throw new IllegalArgumentException("no aliases");
		}
		
		getQueryCollector().setSources(new CollectedSelectSource_Aliases(aliases));

		// final Classic_Collector_WhereOrJoin_Alias_Base<MODEL, RESULT, ?> ret = new Classic_Collector_WhereOrJoin_Alias_Base<MODEL, RESULT>(this);
		
		return createWhereOrJoinForAlias();
	}
	

//	Class<?>[] getClasses() {
//		return classes;
//	}
//
//	Alias<?>[] getAliases() {
//		return aliases;
//	}
}
