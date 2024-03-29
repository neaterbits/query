package com.neaterbits.query.sql.dsl.api;


abstract class Classic_Collector_SelectSource<
				MODEL,
				RESULT,
				NAMED_WHERE_OR_JOIN extends ISQLLogical_WhereOrJoin_Named_Base<MODEL, RESULT>,
				ALIAS_WHERE_OR_JOIN extends ISQLLogical_WhereOrJoin_Alias_Base<MODEL, RESULT>,
				AFTER_GROUP_BY> 

		extends Collector_Conditions_Initial<MODEL, RESULT, AFTER_GROUP_BY>
		implements 
				IClassic_From_Named_Base<MODEL, RESULT, NAMED_WHERE_OR_JOIN>,
				IClassic_From_Alias_Base<MODEL, RESULT, ALIAS_WHERE_OR_JOIN> {

//	private Class<?> [] classes;
//	private Alias<?> [] aliases;

	abstract NAMED_WHERE_OR_JOIN createWhereOrJoinForNamed();
	abstract ALIAS_WHERE_OR_JOIN createWhereOrJoinForAlias();
	
	
	// abstract-method to call whenever collected-result is available at a later time
	// (do not know whether mapped or entity beforehand) 
	abstract CollectedQueryResult getCollectedQueryResult();
					
	Classic_Collector_SelectSource(ClassicSelect classic, CollectedQueryResult result, ModelCompiler<MODEL> modelCompiler) {
		super(new QueryCollectorImpl<MODEL>(classic, modelCompiler, result));
	}
	
	
	
	private void assureCollectedResult() {
		if (getQueryCollector().getResult() == null) {
			final CollectedQueryResult collectedResult = getCollectedQueryResult();
		}
	}
	
	@Override
	public final NAMED_WHERE_OR_JOIN from(Class<?> ... classes) {
		
		if (classes.length == 0) {
			throw new IllegalArgumentException("no classes");
		}
		
//		this.classes = classes;
//		this.aliases = null;

		assureCollectedResult();
		
		getQueryCollector().setSources(new CollectedSelectSource_Named(classes));
		
		//final Classic_Collector_Where_Or_Join_Named<MODEL, RESULT> ret = new Classic_Collector_Where_Or_Join_Named<MODEL, RESULT>(this);
		
		return createWhereOrJoinForNamed();
	}

	@Override
	public final ALIAS_WHERE_OR_JOIN from(Object... aliases) {
		
		assureCollectedResult();
		
		if (aliases.length == 0) {
			throw new IllegalArgumentException("no aliases");
		}
		
		getQueryCollector().setSources(new CollectedSelectSource_Aliases(aliases));

		// final Classic_Collector_WhereOrJoin_Alias_Base<MODEL, RESULT, ?> ret = new Classic_Collector_WhereOrJoin_Alias_Base<MODEL, RESULT>(this);
		
		return createWhereOrJoinForAlias();
	}
	@Override
	final Collector_GroupBy<MODEL, RESULT> createGroupByCollector(Collector_Base<MODEL> last, int[] groupByColumns,
			Collector_Conditions_GroupBy<MODEL, RESULT, ?> collectorConditions) {
		throw new UnsupportedOperationException("TODO");
	}

	

//	Class<?>[] getClasses() {
//		return classes;
//	}
//
//	Alias<?>[] getAliases() {
//		return aliases;
//	}
}
