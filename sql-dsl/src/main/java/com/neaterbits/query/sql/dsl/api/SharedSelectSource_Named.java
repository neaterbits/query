package com.neaterbits.query.sql.dsl.api;

final class SharedSelectSource_Named extends SharedSelectSource {

	private final Class<?> type;

	SharedSelectSource_Named(Class<?> type) {
		
		if (type == null) {
			throw new IllegalArgumentException("type == null");
		}
		
		this.type = type;
	}

	Class<?> getType() {
		return type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		SharedSelectSource_Named other = (SharedSelectSource_Named) obj;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
}
