package com.neaterbits.query.sql.dsl.api;

enum ConditionsType {
	AND {

		@Override
		public ConditionsType opposite() {
			return ConditionsType.OR;
		}
	},
	OR {
		@Override
		public ConditionsType opposite() {
			return ConditionsType.AND;
		}
	},
	SINGLE {

		@Override
		public ConditionsType opposite() {
			throw new UnsupportedOperationException("Only for AND and OR");
		}
	},
	NONE {

		@Override
		public ConditionsType opposite() {
			throw new UnsupportedOperationException("Only for AND and OR");
		}
	};
	
	public abstract ConditionsType opposite();
}
