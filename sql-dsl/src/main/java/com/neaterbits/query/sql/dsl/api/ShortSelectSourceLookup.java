package com.neaterbits.query.sql.dsl.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import com.neaterbits.query.sql.dsl.api.CompiledSelectSources_Alias.AliasMethod;
import com.neaterbits.query.util.java8.Coll8;

final class ShortSelectSourceLookup extends SelectSourceLookup {
	
	
	private EFieldAccessType accessType;
	
	// All entity types from entity model, to scan through for named query
	private final Set<Class<?>> allEntityTypes;
	
	// All aliases that have been registered to this context, for alias queries.
	private final List<IAlias> allAliases;
	
	private final List<Class<?>> knownTypes;
	
	private List<CompiledSelectSource_Named> namedSources;
	private List<CompiledSelectSource_Alias> aliasSources;
	
	ShortSelectSourceLookup(Set<Class<?>> allEntityTypes, List<IAlias> allAliases) {
		
		if (allEntityTypes == null) {
			throw new IllegalArgumentException("allEntityTypes == null");
		}
		
		if (allAliases == null) {
			throw new IllegalArgumentException("allAliases == null");
		}
			
		this.allEntityTypes = allEntityTypes;
		this.allAliases = allAliases;

		this.knownTypes = new ArrayList<>();
	}
	

	
	@Override
	public TypeMapSource getNamedSource(Class<?> type) {

		if (type == null) {
			throw new IllegalArgumentException("type == null");
		}

		if (this.accessType != null) {
			if (this.accessType != EFieldAccessType.NAMED) {
				throw new IllegalStateException("Set to other than named: " + accessType);
			}
		}
		else {
			this.accessType = EFieldAccessType.NAMED;
			this.namedSources = new ArrayList<>();
		}
			
		// Try to find
			
		final int idx = Coll8.idxOf(namedSources, s -> type.equals(s.getType()));
		
		final CompiledSelectSource_Named ret;
		
		if (idx < 0) {
			final String name = CompiledSelectSource_Named.getName(type, namedSources);
			// Not found, must create
			ret = new CompiledSelectSource_Named(type, name, namedSources.size());
			
			namedSources.add(ret);
			
			knownTypes.add(ret.getType());
		}
		else {
			ret = namedSources.get(idx);
		}

		return ret;
	}
		
	@Override
	TypeMapSource getAliasesSource(IAlias alias) {
		if (alias == null) {
			throw new IllegalArgumentException("type == null");
		}

		if (this.accessType != null) {
			if (this.accessType != EFieldAccessType.ALIAS) {
				throw new IllegalStateException("Set to other than alias: " + accessType);
			}
		}
		else {
			this.accessType = EFieldAccessType.ALIAS;
			this.aliasSources = new ArrayList<>();
		}
			
		// Try to find
			
		final int idx = Coll8.idxOf(aliasSources, s -> s.getAlias() == alias);
		
		final CompiledSelectSource_Alias ret;
		
		if (idx < 0) {
			final String name = CompiledSelectSource_Alias.getName(namedSources.size());
			// Not found, must create
			ret = new CompiledSelectSource_Alias(alias, name, namedSources.size());
			
			aliasSources.add(ret);

			knownTypes.add(ret.getType());
		}
		else {
			ret = aliasSources.get(idx);
		}
		

		return ret;
	}

	@Override
	CompiledFieldReference makeFieldReference(CollectedItem original, Getter inputGetter) throws CompileException {

		final CompiledFieldReference ret;
		
		if (inputGetter instanceof FunctionGetter) {
			
			final FunctionGetter functionGetter = (FunctionGetter)inputGetter;
			
			final CompiledGetter compiled = findFunctionGetter(functionGetter.getter);
			
			// Must now find the select-source, or create one
			
			final Class<?> type = compiled.getGetterMethod().getDeclaringClass();
			final TypeMapSource found = getNamedSource(type);
			
			ret = new CompiledFieldReference(original, found, compiled);
		}
		else if (inputGetter instanceof SupplierGetter) {
		
			final SupplierGetter supplierGetter = (SupplierGetter)inputGetter;
			
			final AliasMethod<IAlias> aliasMethod
				= CompiledSelectSources_Alias.findMethodForAlias(supplierGetter, allAliases, alias -> alias);
			
			
			final CompiledGetter compiledGetter = new CompiledGetterSupplier(supplierGetter.getGetter(), aliasMethod.method);
			
			
			final TypeMapSource compiledAlias = getAliasesSource(aliasMethod.element);
			
			
			ret = new CompiledFieldReference(original, compiledAlias, compiledGetter);
		}
		else {
			throw new UnsupportedOperationException("Unknown intput getter type: " + inputGetter.getClass().getSimpleName());
		}

		return ret;
	}

	private CompiledGetter findFunctionGetter(Function<?, ?> getter) throws CompileException {
		CompiledGetter func = cache.findGetterFromTypes(this.knownTypes, getter);

		if (func == null) {
			// try get from all
			func = cache.findGetterFromTypes(this.allEntityTypes, getter);
		}
	
		return func;
	}
	

	@Override
	CompiledGetterFunction findGetter(Function<?, ?> getter) throws CompileException {
		return (CompiledGetterFunction)findFunctionGetter(getter);
	}
	
	
	@Override
	CompiledSelectSources<?> compile(CollectedQueryResult queryResult) {
		
		final CompiledSelectSources<?> ret;
		
		if (accessType == null) {
			
			final SharedSelectSource selectSource = ((CollectedQueryResult_Entity)queryResult).getSelectSource();
			// no sources found, so should just be a list(SomeEntity.class) query, return no select sources
			ret = new CompiledSelectSources_ListAllOfOneEntity((selectSource));
		}
		else {
		
			switch (accessType) {
			case NAMED:
				ret = new CompiledSelectSources_Named(namedSources);
				break;
				
			case ALIAS:
				ret = new CompiledSelectSources_Alias(aliasSources);
				break;
				
			default:
				throw new UnsupportedOperationException("Unknown access type " + accessType);
			}
		}
		
		return ret;
	}
}
