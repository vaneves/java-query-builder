package com.vaneves.querybuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

/**
 * @author vaneves
 */
public abstract class QueryBuilderBase {

	protected String table;
	protected Operation operation;
	protected ArrayList<String> columns = new ArrayList<String>();
	protected ArrayList<String> values = new ArrayList<String>();
	protected ArrayList<String> wheres = new ArrayList<String>();

	public QueryBuilderBase(String table) {
		this.table = table;
	}

	protected String getEscaped(String item) {
		return "`" + item + "`";
	}

	protected String getColumn(String column) {
		return this.getEscaped(this.table) + "." + this.getEscaped(column);
	}

	protected String contact(String column, String condition, String value) {
		return this.getColumn(column) + " " + condition + " " + value;
	}

	protected String getSql() {
		String sql = "";
		if (this.operation == Operation.INSERT) {
			sql = "INSERT INTO " + this.getEscaped(this.table) + " (" + String.join(", ", this.columns) + ") VALUES ("
					+ String.join(", ", this.values) + ");";
		} else if (this.operation == Operation.UPDATE) {
			sql = "UPDATE " + this.getEscaped(this.table) + " SET " + String.join(", ", this.columns) + " WHERE "
					+ String.join(" AND ", this.wheres) + ";";
		} else if (this.operation == Operation.DELETE) {
			sql = "DELETE FROM " + this.getEscaped(this.table) + " WHERE " + String.join(" AND ", this.wheres) + ";";
		}

		return sql;
	}

	public boolean execute(Object... params) {
		boolean result = false;
		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			connection = ConnectionFactory.getConnection();
			stmt = connection.prepareStatement(this.getSql());
			int count = 0;
			for (Object param : params) {
				stmt.setObject(++count, param);
			}
			stmt.execute();
			result = true;
		} catch (Exception e) {
			// Log
		} finally {
			ConnectionFactory.close(connection, stmt);
		}
		return result;
	}
}

enum Operation {
	INSERT, UPDATE, DELETE;
}