package com.vaneves.querybuilder;

/**
 * @author vaneves
 */
public class QueryBuilder {

	public static QueryBuilderSelect select(String... columns) {
		return new QueryBuilderSelect(columns);
	}

	public static QueryBuilderInsert insert(String table) {
		return new QueryBuilderInsert(table);
	}

	public static QueryBuilderUpdate update(String table) {
		return new QueryBuilderUpdate(table);
	}

	public static QueryBuilderDelete delete(String table) {
		return new QueryBuilderDelete(table);
	}
}
