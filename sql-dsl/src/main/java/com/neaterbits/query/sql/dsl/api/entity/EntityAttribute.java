package com.neaterbits.query.sql.dsl.api.entity;

import java.lang.reflect.Member;
import java.util.List;
import java.util.Set;

class EntityAttribute implements IEntityAttribute {

	private final String name;
	private final String [] columns;
	private final AttributeType type;
	private final Class<?> javaType;
	private final Member javaMember;
	
	// For collections
	private final Class<?> collectionMemberType;
	private final EntityCollectionType collectionType;

	EntityAttribute(String name, String [] columns, AttributeType type, Class<?> javaType, Member member, Class<?> memberType) {
		this.name = name;
		this.type = type;
		this.columns = columns;
		this.javaMember = member;
		this.javaType = javaType;
		this.collectionMemberType = memberType;
		
		if (memberType == null) {
			this.collectionType = null;
		}
		else if (javaType.equals(Set.class)) {
			this.collectionType = EntityCollectionType.SET;
		}
		else if (javaType.equals(List.class)) {
			this.collectionType = EntityCollectionType.LIST;
		}
		else {
			throw new UnsupportedOperationException("Unsupported member type " + javaType.getClass().getName());
		}
	}

	public final Class<?> getJavaMemberType() {
		return collectionMemberType;
	}
	

	public final EntityCollectionType getCollectionType() {
		return collectionType;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String [] getColumns() {
		return columns;
	}

	@Override
	public AttributeType getType() {
		return type;
	}

	@Override
	public Class<?> getJavaType() {
		return javaType;
	}

	@Override
	public Member getJavaMember() {
		return javaMember;
	}
}
