package com.neaterbits.query.sql.dsl.api;

import java.util.Set;

import javax.persistence.JoinColumn;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.BasicType;
import javax.persistence.metamodel.EmbeddableType;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.IdentifiableType;
import javax.persistence.metamodel.ManagedType;
import javax.persistence.metamodel.Metamodel;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.Type;

import com.neaterbits.query.sql.dsl.api.entity.AttributeType;
import com.neaterbits.query.sql.dsl.api.entity.ComplexType;
import com.neaterbits.query.sql.dsl.api.entity.EntityModel;
import com.neaterbits.query.sql.dsl.api.entity.IdType;
import com.neaterbits.query.sql.dsl.api.entity.ScalarType;

public class JPAEntityModel implements EntityModel<
									ManagedType<?>,
									EmbeddableType<?>,
									IdentifiableType<?>,
									Attribute<?, ?>,
									Set<Attribute<?, ?>>

									> {

	private final Metamodel metamodel;
	
	JPAEntityModel(Metamodel metamodel) {
		if (metamodel == null) {
			throw new IllegalArgumentException("metamodel == null");
		}

		this.metamodel = metamodel;
	}

	

	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	public Set<Attribute<?, ?>> getAttributes(ManagedType<?> entity) {
		return (Set)entity.getAttributes();
	}

	@Override
	public ManagedType<?> getManaged(Class<?> type) {
		return metamodel.managedType(type);
	}

	@Override
	public ComplexType getComplexType(ManagedType<?> managed) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public IdentifiableType<?> getIdentifiable(ManagedType<?> managed) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public IdType getIdType(IdentifiableType<?> identifiable) {
		
		final IdType ret;
		
		final Type<?> idType = identifiable.getIdType();
		
		if (idType instanceof BasicType) {
			ret = IdType.SINGLE_SCALAR;
		}
		else if (idType instanceof EmbeddableType<?>) {
			ret = IdType.SINGLE_EMBEDDED;
		}
		else {
			throw new UnsupportedOperationException("Unknown ID type " + idType.getJavaType().getName());
		}

		return ret;
	}


	@Override
	public EmbeddableType<?> getEmbeddedIdType(IdentifiableType<?> identifiable) {
		return (EmbeddableType<?>)identifiable.getIdType();
	}



	@Override
	public ScalarType getScalarIdType(IdentifiableType<?> identifiable) {

		final BasicType<?> basicType = (BasicType<?>)identifiable.getIdType();

		final Class<?> javaType = basicType.getJavaType();
		
		final ScalarType ret = ScalarType.fromType(javaType);
		
		if (ret == null) {
			throw new IllegalStateException("No scalar type from java type " + javaType.getName());
		}
		
		return ret;
	}

	@Override
	public boolean isAssociation(Attribute<?, ?> attribute) {
		return attribute.isAssociation();
	}
	
	@Override
	public AttributeType getAttributeType(Attribute<?, ?> attribute) {
		
		final AttributeType ret;
		
		switch (attribute.getPersistentAttributeType()) {
		case BASIC:
			break;

		case EMBEDDED:
		case ONE_TO_ONE:
			
		case MANY_TO_MANY:
		case ONE_TO_MANY:
		}

		ret = null;
		
		return ret;
	}

	@Override
	public String getAttributeName(Attribute<?, ?> attribute) {
		return attribute.getName();
	}


	private static Attribute<?, ?> findToAttrForManyToOneOrException(EntityType<?> toEntity, JoinColumn joinColumn) {
		
		final Attribute<?, ?> ret;
		
		if (joinColumn.referencedColumnName() != null) {
			ret = toEntity.getAttribute(joinColumn.referencedColumnName());
		}
		else {
			throw new UnsupportedOperationException();
		}
		
		return ret;
	}

	private static Attribute<?, ?> findToAttrOrForMappedByException(EntityType<?> toEntity, String toName) {
		final Attribute<?, ?>  ret = toEntity.getAttribute(toName);
		
		if (ret == null) {
			throw new IllegalStateException("No attribute found in entity " + toEntity.getName() + " of name " + toName);
		}

		return ret;
	}

	static void findEntityField(Class<?> entity1, Class<?> entity2, Metamodel model) {

		final EntityType<?> entity = model.entity(entity1);
		
		for (Attribute<?, ?> attr : entity.getAttributes()) {
			
			System.out.println("Got attr of name " + attr.getName());

			if (attr.isAssociation()) {
				System.out.println("## found association attribute " + attr.getName());
			}

			if (attr.isCollection()) {
				System.out.println("## found collection attribute " + attr.getName());
			}
			else {
				System.out.println("## found singular attribute " + attr.getName());
			}
		}
		
	}
	
}

