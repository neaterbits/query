package com.neaterbits.query.sql.dsl.api;

final class SharedSelectSource_Alias extends SharedSelectSource {

	private final IAlias alias;

	SharedSelectSource_Alias(IAlias alias) {
		
		if (alias == null) {
			throw new IllegalArgumentException("alias == null");
		}
		
		this.alias = alias;
	}

	IAlias getAlias() {
		return alias;
	}

	@Override
	Class<?> getType() {
		return alias.getType();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alias == null) ? 0 : alias.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SharedSelectSource_Alias other = (SharedSelectSource_Alias) obj;
		if (alias == null) {
			if (other.alias != null)
				return false;
		} else if (!alias.equals(other.alias))
			return false;
		return true;
	}
}
