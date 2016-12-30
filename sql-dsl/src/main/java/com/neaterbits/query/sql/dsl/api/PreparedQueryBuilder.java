package com.neaterbits.query.sql.dsl.api;

import java.util.List;

interface PreparedQueryBuilder {

	void select();
	
	void addEntityResult(Class<?> resultType, String resultVarName);
	
	void addAggregateResult(EAggregateFunction function, FieldReference field);

	void addMappings(List<FieldReference> references);

	void addSelectSources(List<SourceReference> references);
	
	void addOneToManyJoin(String entityAliasName, String collectionAttrName, String joinVarName);
	
	void appendJoinStatement(EJoinType joinType);
	
	void addAllConditions(PreparedQueryConditionsBuilder conditionsBuilder, ParamValueResolver paramValueResolver);
	
	static class SourceReference {
		
		private final Class<?> javaType;
		private final String varName;

		
		public SourceReference(Class<?> javaType, String varName) {
			
			if (javaType == null) {
				throw new IllegalArgumentException("javaType == null");
			}
			
			if (varName == null) {
				throw new IllegalArgumentException("varName == null");
			}
			
			this.javaType = javaType;
			this.varName = varName;
		}


		public Class<?> getJavaType() {
			return javaType;
		}


		public String getVarName() {
			return varName;
		}
	}

	static class FieldReference {
		
		private final String varName;
		private final String columnName;
		
		public FieldReference(String varName, String columnName) {
			
			if (varName == null) {
				throw new IllegalArgumentException("varName == null");
			}
			
			if (columnName == null) {
				throw new IllegalArgumentException("columnName == null");
			}
			
			this.varName = varName;
			this.columnName = columnName;
		}

		public String getVarName() {
			return varName;
		}

		public String getColumnName() {
			return columnName;
		}
	}
}



