package com.vaneves.querybuilder;

/**
 * @author vaneves
 */
public class QueryBuilderDelete extends QueryBuilderBase {

	public QueryBuilderDelete(String table) {
		super(table);
		this.operation = Operation.DELETE;
	}

	public QueryBuilderDelete whereSafe(String column) {
		this.wheres.add(this.contact(column, "=", "?"));
		return this;
	}

	public QueryBuilderDelete where(String column, String value) {
		this.wheres.add(this.contact(column, "=", value));
		return this;
	}

	public QueryBuilderDelete where(String column, String condition, String value) {
		this.wheres.add(this.contact(column, condition, value));
		return this;
	}
}
