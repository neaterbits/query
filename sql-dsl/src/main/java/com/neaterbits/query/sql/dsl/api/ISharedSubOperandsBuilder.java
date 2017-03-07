package com.neaterbits.query.sql.dsl.api;

@FunctionalInterface
public interface ISharedSubOperandsBuilder<T> {

	ISharedSubOperand_End<T> build();

}
