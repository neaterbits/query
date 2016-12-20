package com.neaterbits.query.sql.dsl.api;

import java.util.Set;

import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EmbeddableType;
import javax.persistence.metamodel.IdentifiableType;
import javax.persistence.metamodel.ManagedType;

import com.neaterbits.query.sql.dsl.api.entity.EntityModelUtil;

final class JPAEntityModelUtil extends EntityModelUtil<ManagedType<?>, EmbeddableType<?>, IdentifiableType<?>, Attribute<?, ?>, Set<Attribute<?, ?>>> {

	JPAEntityModelUtil(JPAEntityModel model) {
		super(model);
	}
}
