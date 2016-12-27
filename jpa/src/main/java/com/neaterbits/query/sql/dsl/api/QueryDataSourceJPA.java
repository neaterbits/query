package com.neaterbits.query.sql.dsl.api;

import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.ManagedType;
import javax.persistence.metamodel.Metamodel;

import com.neaterbits.query.sql.dsl.api.entity.Relation;

/**
 * Query data source implementation for JPA
 * @author nhl
 *
 */

public final class QueryDataSourceJPA extends QueryDataSourceBase {
	

	private final EntityManager em;
	private final JPACompiledQueryResultVisitor queryResultVisitor;
	private final JPAEntityModel entityModel;
	private final JPAEntityModelUtil entityModelUtil;

	public QueryDataSourceJPA(EntityManager entityManager) {

		if (entityManager == null) {
			throw new IllegalArgumentException("entityManager == null");
		}

		this.em = entityManager;
		this.queryResultVisitor = new JPACompiledQueryResultVisitor(em);
		this.entityModel = new JPAEntityModel(em.getMetamodel());
		this.entityModelUtil = new JPAEntityModelUtil(entityModel);
	}
	
	private JPABasePreparedQuery prepareQuery(CompiledQuery query) {

		final StringBuilder sb = new StringBuilder();

		return prepare(sb, query);
	}
	

	private String entityName(Class<?> entityType) {
		return entityType.getSimpleName();
	}

	
	private JPABasePreparedQuery prepare(StringBuilder sb, CompiledQuery query) {
		
		sb.append("SELECT ");
		
		query.getResult().visit(queryResultVisitor, sb);

		sb.append("\n").append("FROM ");
		
		final CompiledSelectSources<CompiledSelectSource> sources = (CompiledSelectSources<CompiledSelectSource>)query.getSelectSources();
		
		// Add all select sources
		prepareSelectSources(sb, sources);

		List<CompiledJoinConditionComparison> addJoinToWhere = new ArrayList<>();
		
		if (query.getJoins() != null) {
			prepareJoins(sb, query.getJoins(), sources, addJoinToWhere);
		}
		
		// Prepare conditions if present
		final JPABasePreparedQuery ret;
		
		if (query.getConditions() != null) {
			
			final ParamNameAssigner paramNameAssigner = new ParamNameAssigner();
			final CompileConditionParam param = new CompileConditionParam(paramNameAssigner, em);
			final CompiledConditions conditions = query.getConditions();

			final JPAOp op = prepareConditions(conditions, param, addJoinToWhere);

			if (param.hasUnresolved()) {
				ret = new JPAHalfwayPreparedQuery(query, paramNameAssigner, sb.toString(), op, param.getConditions(), em);
			}
			else {
				param.addAllConditions(sb, null);

				ret = makeComplete(query, paramNameAssigner, sb);
			}
		}
		else {
			ret = makeComplete(query, null, sb);
		}

		return ret;
	}
	
	private JPACompletePreparedQuery makeComplete(CompiledQuery query, ParamNameAssigner paramNameAssigner, StringBuilder sb) {
		final String jpql = sb.toString();
		
		System.out.println("## jpql:\n" + jpql);
		
		final javax.persistence.Query jpaQuery = em.createQuery(jpql);

		return new JPACompletePreparedQuery(query, paramNameAssigner, jpaQuery);
	}

	private void prepareSelectSources(StringBuilder sb, CompiledSelectSources<CompiledSelectSource> sources) {

		JPAUtil.commaSeparated(sb, sources.getSources(), (CompiledSelectSource source) -> {

			final String entityName = entityName(source.getType());

			sb.append(entityName).append(' ').append(source.getName());
		});
	}
	
	static void prepareFieldReference(Consumer<String> c, CompiledFieldReference field, EntityManager em) {

		final TypeMapSource source = field.getSource();

		final CompiledGetter getter = field.getGetter();

		final String columnName = getColumnNameForGetter(source, getter, em);

		c.accept(source.getName());
		c.accept(".");
		c.accept(columnName);
	}
	
	private void prepareJoins(StringBuilder sb, CompiledJoins joins, CompiledSelectSources<CompiledSelectSource> sources, List<CompiledJoinConditionComparison> addJoinToWhere) {
		
		int joinParamIdx = 0;
		
		for (CompiledJoin join : joins.getJoins()) {
			joinParamIdx = addRelationFromMetaModel(sb, join, sources, joinParamIdx, addJoinToWhere);
		}
	}
	
	private void appendJoinStatement(StringBuilder sb, CompiledJoin join) {
		switch (join.getJoinType()) {
		
		case LEFT:
			sb.append(" LEFT JOIN ");
			break;
			
		case INNER:
			sb.append(" INNER JOIN ");
			break;
			
		default:
			throw new UnsupportedOperationException("Unknown join condition ");
		}
	}

	private int addRelationFromMetaModel(
			StringBuilder sb,
			CompiledJoin join,
			CompiledSelectSources<CompiledSelectSource> sources,
			int joinParamIdx,
			List<CompiledJoinConditionComparison> addJoinToWhere) {

		// First must find the types for this join
		final Class<?> leftType  = join.getLeft().getType();
		final Class<?> rightType = join.getRight().getType();

		final ManagedType<?> leftEntityType  =  entityModel.getManaged(leftType);
		final ManagedType<?> rightEntityType =  entityModel.getManaged(rightType);

		if (leftEntityType.equals(rightEntityType)) {
			throw new IllegalStateException("Left entity equals right entity: "  + leftEntityType + " / " + rightEntityType);
		}

		// Look at conditions to see if there is a join on any of the conditions
		if (join.getConditions().isEmpty()) {
			appendJoinStatement(sb, join);
			
			// Must find exactly one relation from this one to other
			final List<Relation> leftToRight =  entityModelUtil.findRelations(leftType, rightType);
			final List<Relation> rightToLeft =  entityModelUtil.findRelations(leftType, rightType);
			
			if (leftToRight.size() != 1) {
				throw new IllegalStateException("Expected exactly one relation from " + leftType + " to " + rightType);
			}
			
			// final Relation leftRelation = leftToRight.get(0);
			
			if (rightToLeft.size() != 1) {
				throw new IllegalStateException("Expected exactly one relation from " + rightType + " to " + leftType);
			}
			
			// Got one relation
			//final Relation rightRelation = rightToLeft.get(0);
		}
		else {
			// Must find metamodel entry that matches the foreign key patterns specified
			for (CompiledJoinCondition condition : join.getConditions()) {
				// Find something that matches this condition in correcto order

				if (condition instanceof CompiledJoinConditionComparison) {
					
					if (join.getJoinType() != EJoinType.INNER) {
						throw new IllegalStateException("Can only do inner joins when performin field comparison");
					}

					final CompiledJoinConditionComparison comparison = (CompiledJoinConditionComparison)condition;

					// Joining two fields. TODO Which version of JPA are we?

					// Add as where-queries for later on
					
					addJoinToWhere.add(comparison);
				}
				else if (condition instanceof CompiledJoinConditionOneToMany) {
					
					appendJoinStatement(sb, join);

					final CompiledJoinConditionOneToMany oneToMany = (CompiledJoinConditionOneToMany)condition;

					final CompiledGetter getter = oneToMany.getCollectionGetter();
					
					// Must figure out which relation attribute this is ?
					final Relation relation = entityModelUtil.findOneToManyRelation(oneToMany.getLeft().getType(), oneToMany.getRight().getType(), getter.getGetterMethod());
					
					if (relation == null) {
						throw new IllegalStateException("Failed to find relation for " + getter.getGetterMethod());
					}
					
					final String entityAliasName = condition.getLeft().getName();
					final String collectionAttrName = relation.getFrom().getAttribute().getName();
					
					
					// Output JPA join on collection
					// from name in from-clause
					sb.append(" ")
					  .append(entityAliasName).append(".").append(collectionAttrName)
					  .append(" ")
					  .append(" join").append(joinParamIdx ++);

					// TODO: Join comparison on rhs?
				}
				else {
					throw new UnsupportedOperationException("Unknown join condition type " + condition.getClass().getName());
				}
			}
		}
		
		return joinParamIdx;
	}
	
	// Must find whether there are multiple or just one mathing join between two tables.
	// should usually be just one
	
	private static final JPAConditionToOperatorVisitor conditionToOperatorVisitor = new JPAConditionToOperatorVisitor();

	private JPAOp getJPAOp(CompiledConditions conditions) {
		final JPAOp op;
		
		if (conditions instanceof CompiledConditionsAnd) {
			op = JPAOp.AND;
		}
		else if (conditions instanceof CompiledConditionsOr) {
			op = JPAOp.OR;
		}
		else if (conditions instanceof CompiledConditionsSingle) {
			op = null;
		}
		else {
			throw new IllegalStateException("unknown conditions " + conditions.getClass());
		}

		return op;
	}
	
	private JPAOp prepareConditions(CompiledConditions conditions, CompileConditionParam param, List<CompiledJoinConditionComparison> joinComparisonConditions) {

		
		final JPAOp op = getJPAOp(conditions);
		
		String os = "WHERE";
		
		final boolean nestConditions =
				
				       JPAOp.OR.equals(op)
					&& !conditions.getConditions().isEmpty()
					&& !joinComparisonConditions.isEmpty();
		
		if (!joinComparisonConditions.isEmpty()) {
			
			for (CompiledJoinConditionComparison comparison : joinComparisonConditions) {
				
				if (os == null) {
					throw new IllegalStateException("os == null");
				}

				param.append(" ").append(os).append(" ");

				// Output join condition as regular join
				prepareFieldReference(param::append, comparison.getLhs(), em);

				param.append(" = ");

				prepareFieldReference(param::append, comparison.getRhs(), em);

				os = "AND";
			}

			if (nestConditions) {
				os = "AND (";
			}
		}

		for (CompiledCondition condition : conditions.getConditions()) {

			if (os == null) {
				throw new IllegalStateException("os == null");
			}
			
			param.append(" ").append(os).append(" ");

			prepareFieldReference(param::append, condition.getLhs(), em);
			
			param.append(" ");

			param.setValue(condition.getValue());
			
			// Operator and value
			condition.getOriginal().visit(conditionToOperatorVisitor, param);

			os = op != null ? op.getOpString() : null;
		}

		if (nestConditions) {
			param.append(" )");
		}

		return op;
	}
	
	


	private static String getColumnNameForGetter(TypeMapSource source, CompiledGetter getter, EntityManager em) {

		// Look up in entity manager
		
		final Metamodel metaModel = em.getEntityManagerFactory().getMetamodel();
				final EntityType<?> entityType = metaModel.entity(source.getType());

		if (entityType == null) {
			throw new IllegalStateException("No entity type for " + source.getType());
		}

		final Attribute<?, ?> attr = findAttr(entityType, getter.getGetterMethod());
		
		if (attr == null) {
			throw new IllegalArgumentException("No attribute for getter " + getter);
		}

		return attr.getName();
	}
	
	private static Attribute<?, ?> findAttr(EntityType<?> entityType, Method getterMethod) {
		Attribute<?, ?> found = null;
		
		// Find attribute
		for (Attribute<?, ?> attr : entityType.getAttributes()) {
			final Member m  = attr.getJavaMember();

			if (m instanceof Method) {
				final Method method = (Method)m;
				
				if (method.equals(getterMethod)) {
					found = attr;
				}
			}
			else {
				throw new UnsupportedOperationException("Does not support field members for now");
			}
		}

		return found;
	}
	

	@Override
	DSPreparedQuery prepareSingleQuery(CompiledQuery query) {

		if (query == null) {
			throw new IllegalArgumentException("query == null");
		}

		return prepareQuery(query);
	}

	@Override
	DSPreparedQuery prepareMultiQuery(CompiledQuery query) {

		if (query == null) {
			throw new IllegalArgumentException("query == null");
		}

		return prepareQuery(query);
	}
}

