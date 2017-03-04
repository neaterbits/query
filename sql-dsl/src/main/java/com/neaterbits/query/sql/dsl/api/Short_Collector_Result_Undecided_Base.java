package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;
import java.util.function.Supplier;

abstract class Short_Collector_Result_Undecided_Base<
		MODEL,
		RESULT,
		
		MAPPED_NAMED_WHERE_OR_JOIN extends ISQLLogical_WhereOrJoin_Named_Base<MODEL, RESULT>,
		MAPPED_ALIAS_WHERE_OR_JOIN extends ISQLLogical_WhereOrJoin_Alias_Base<MODEL, RESULT>,
		
		MAPPED_SOURCE_NAMED extends ISharedSelectSourceBuilder<MODEL, RESULT>,
		MAPPED_SOURCE_ALIAS extends ISharedSelectSourceBuilder<MODEL, RESULT>,
		
		NAMED_AND_CLAUSES  extends ISharedLogical_And_Named_All <MODEL, RESULT, NAMED_AND_CLAUSES,  NAMED_NESTED_OR_CLAUSES>,
		NAMED_OR_CLAUSES  extends ISharedLogical_Or_Named_All <MODEL, RESULT, NAMED_OR_CLAUSES,  NAMED_NESTED_AND_CLAUSES>,

		NAMED_NESTED_AND_CLAUSES extends ISharedLogical_And_Named_All<MODEL, RESULT, NAMED_NESTED_AND_CLAUSES, NAMED_NESTED_OR_CLAUSES>,
		NAMED_NESTED_OR_CLAUSES  extends ISharedLogical_Or_Named_All<MODEL, RESULT, NAMED_NESTED_OR_CLAUSES,  NAMED_NESTED_AND_CLAUSES>,			

		NAMED_JOIN_CONDITION extends ISQLJoin_Condition_Named_Base<MODEL, RESULT, Object, Object, NAMED_JOIN_CONDITION>,

		NAMED_AND_OR extends ISharedLogical_And_Or_Named_All<
			MODEL,
			RESULT,
			NAMED_AND_CLAUSES,
			NAMED_OR_CLAUSES,
			NAMED_NESTED_AND_CLAUSES,
			NAMED_NESTED_OR_CLAUSES>,
		
		ALIAS_AND_CLAUSES extends ISharedLogical_And_Alias_Base<MODEL, RESULT, ALIAS_AND_CLAUSES, ALIAS_NESTED_OR_CLAUSES>,
		ALIAS_OR_CLAUSES  extends ISharedLogical_Or_Alias_Base <MODEL, RESULT, ALIAS_OR_CLAUSES,  ALIAS_NESTED_AND_CLAUSES>,

		ALIAS_NESTED_AND_CLAUSES extends ISharedLogical_And_Alias_Base<MODEL, RESULT, ALIAS_NESTED_AND_CLAUSES, ALIAS_NESTED_OR_CLAUSES>,
		ALIAS_NESTED_OR_CLAUSES  extends ISharedLogical_Or_Alias_Base <MODEL, RESULT, ALIAS_NESTED_OR_CLAUSES,  ALIAS_NESTED_AND_CLAUSES>,			

		ALIAS_JOIN_CONDITION extends ISQLJoin_Condition_Alias_Base<MODEL, RESULT, ALIAS_JOIN_CONDITION>,

		ALIAS_AND_OR extends ISharedLogical_And_Or_Alias<
			MODEL,
			RESULT,
			ALIAS_AND_CLAUSES,
			ALIAS_OR_CLAUSES,
			ALIAS_NESTED_AND_CLAUSES,
			ALIAS_NESTED_OR_CLAUSES>,
			
			
		AFTER_GROUP_BY
		>

	extends SQL_Collector_WhereOrJoin_Base<
			MODEL,
			RESULT,
			
			NAMED_AND_CLAUSES, NAMED_OR_CLAUSES, NAMED_NESTED_AND_CLAUSES, NAMED_NESTED_OR_CLAUSES, NAMED_JOIN_CONDITION, NAMED_AND_OR, ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>,
			ALIAS_AND_CLAUSES, ALIAS_OR_CLAUSES, ALIAS_NESTED_AND_CLAUSES, ALIAS_NESTED_OR_CLAUSES, ALIAS_JOIN_CONDITION, ALIAS_AND_OR, ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>,
			
			AFTER_GROUP_BY>

	implements ISharedResultMapper_Named<MODEL, RESULT, MAPPED_SOURCE_NAMED>,
			   ISharedResultMapper_Alias<MODEL, RESULT, MAPPED_SOURCE_ALIAS> {

		
	// abstract-method to call whenever collected-result is available at a later time
	// (do not know whether mapped or entity beforehand)
				   
	abstract IMappingCollector<MODEL, RESULT> getMapToResultNamed();

	abstract IMappingCollector<MODEL, RESULT> getMapToResultAlias();
				   
	private final SharedSelectSource selectSource;
	private final ModelCompiler<MODEL> modelCompiler;

	protected Short_Collector_Result_Undecided_Base(SharedSelectSource selectSource, ModelCompiler<MODEL> modelCompiler) {
		super(new QueryCollectorImpl<MODEL>(EQueryStyle.CLASSIC, modelCompiler, null), new Collector_Clause(EConditionsClause.WHERE, ConditionsType.SINGLE));
		
		if (selectSource == null) {
			throw new IllegalArgumentException("selectSource == null");
		}
		
		if (modelCompiler == null) {
			throw new IllegalArgumentException("modelCompiler == null");
		}
		
		this.selectSource = selectSource;
		this.modelCompiler = modelCompiler;
	}
	

	final Class<?> getResultType() {
		return selectSource.getType();
	}

	final SharedSelectSource getSelectSource() {
		return selectSource;
	}

	final ModelCompiler<MODEL> getModelCompiler() {
		return modelCompiler;
	}

	@Override
	public final <T, R> ISharedResultMapperTo<MODEL, RESULT, R, MAPPED_SOURCE_NAMED> map(Function<T, R> getter) {
		return new ResultMapperToImpl<MODEL, RESULT, R, MAPPED_SOURCE_NAMED>(getter, getMapToResultNamed());
	}

	@Override
	public final <T, R> ISharedResultMapperTo<MODEL, RESULT, R, MAPPED_SOURCE_ALIAS> map(Supplier<R> getter) {
		return new ResultMapperToImpl<>(getter, getMapToResultAlias());
	}
}
