package com.neaterbits.query.sql.dsl.api;

import java.lang.reflect.Method;
import java.util.Set;

import javax.persistence.metamodel.Metamodel;

import com.neaterbits.query.sql.dsl.api.entity.QueryMetaModel;
import com.neaterbits.query.sql.dsl.api.entity.Relation;

class JPAQueryMetaModel implements QueryMetaModel {

	
	private final JPAEntityModelUtil util;
	
	JPAQueryMetaModel(Metamodel metamodel) {
		this.util = new JPAEntityModelUtil(new JPAEntityModel(metamodel));
	}

	@Override
	public Relation resolveOneToMany(Class<?> type1, Class<?> type2, Method collectionGetter) {

		return util.findOneToManyRelation(type1, type2, collectionGetter);
	}

	@Override
	public Set<Class<?>> getAllManagedTypes() {
		return util.getModel().getAllManagedTypes();
	}
}


	