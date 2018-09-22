package com.vaneves.querybuilder;

/**
 * @author vaneves
 */
public class QueryBuilderUpdate extends QueryBuilderBase {

	public QueryBuilderUpdate(String table) {
		super(table);
		this.operation = Operation.UPDATE;
	}

	public QueryBuilderUpdate setSafe(String column) {
		this.columns.add(this.contact(column, "=", "?"));
		return this;
	}

	public QueryBuilderUpdate set(String column, String value) {
		this.columns.add(this.contact(column, "=", value));
		return this;
	}

	public QueryBuilderUpdate whereSafe(String column) {
		this.wheres.add(this.contact(column, "=", "?"));
		return this;
	}

	public QueryBuilderUpdate where(String column, String value) {
		this.wheres.add(this.contact(column, "=", value));
		return this;
	}

	public QueryBuilderUpdate where(String column, String condition, String value) {
		this.wheres.add(this.contact(column, condition, value));
		return this;
	}
}
