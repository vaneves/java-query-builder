package com.vaneves.querybuilder;

/**
 * @author vaneves
 */
public class QueryBuilderInsert extends QueryBuilderBase {

	public QueryBuilderInsert(String table) {
		super(table);
		this.operation = Operation.INSERT;
	}

	public QueryBuilderInsert set(String column, String value) {
		this.columns.add(this.getEscaped(column));
		this.values.add(value);
		return this;
	}

	public QueryBuilderInsert columns(String... columns) {
		for (int i = 0; i < columns.length; i++) {
			this.columns.add(this.getEscaped(columns[i]));
			this.values.add("?");
		}
		return this;
	}
}
