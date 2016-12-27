package com.neaterbits.query.sql.dsl.api;

class Bar {
	
	private final int fooId;
	private final String baz;

	public Bar(int fooId, String baz) {
		this.fooId = fooId;
		this.baz = baz;
	}
	
	int getFooId() {
		return fooId;
	}

	String getBaz() {
		return baz;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((baz == null) ? 0 : baz.hashCode());
		result = prime * result + fooId;
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
		Bar other = (Bar) obj;
		if (baz == null) {
			if (other.baz != null)
				return false;
		} else if (!baz.equals(other.baz))
			return false;
		if (fooId != other.fooId)
			return false;
		return true;
	}
}
