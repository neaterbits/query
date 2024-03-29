package com.neaterbits.query.sql.dsl.api;

abstract class CompiledSelectSource extends CompiledQueryElement<CollectedSelectSource> implements TypeMapSource {

	private final Class<?> type;
	private final String name;
	private final int idx;
	
	CompiledSelectSource(CollectedSelectSource original, Class<?> type, String name, int idx) {

		super(original);

		if (original == null) {
			throw new IllegalArgumentException("original == null");
		}

		if (type == null) {
			throw new IllegalArgumentException("type == null");
		}
		
		if (name == null) {
			throw new IllegalArgumentException("name == null");
		}
		
		this.type = type;
		this.name = name;
		this.idx = idx;
	}
	
	CompiledSelectSource(Class<?> type, String name, int idx) {
		super(null);

		if (type == null) {
			throw new IllegalArgumentException("type == null");
		}
		
		if (name == null) {
			throw new IllegalArgumentException("name == null");
		}
		
		this.type = type;
		this.name = name;
		this.idx = idx;
	}

	static String getName(int aliasNo) {
		return "al" + aliasNo;
	}
	
	@Override
	public final Class<?> getType() {
		return type;
	}

	@Override
	public final String getName() {
		return name;
	}

	@Override
	public int getIdx() {
		return idx;
	}

	@Override
	public final int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public final boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompiledSelectSource other = (CompiledSelectSource) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
