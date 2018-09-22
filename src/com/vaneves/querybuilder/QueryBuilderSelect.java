package com.vaneves.querybuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * @author vaneves
 */
public class QueryBuilderSelect {

	private String[] columns;
	private String table;
	private ArrayList<String> wheres = new ArrayList<String>();
	private ArrayList<String> orders = new ArrayList<String>();

	public QueryBuilderSelect(String[] columns) {
		this.columns = columns;
		for (int i = 0; i < columns.length; i++) {
			if (columns[i] != "*") {
				this.columns[i] = this.getColumn(columns[i]);
			}
		}
	}

	public QueryBuilderSelect from(String table) {
		this.table = table;
		return this;
	}

	private String getEscaped(String item) {
		return "`" + item + "`";
	}

	private String getColumn(String column) {
		if (this.table != null) {
			return this.getEscaped(this.table) + "." + this.getEscaped(column);
		}
		return this.getEscaped(column);
	}

	private String contact(String column, String condition, String value) {
		return this.getColumn(column) + " " + condition + " " + value;
	}

	public QueryBuilderSelect whereSafe(String column) {
		this.wheres.add(this.contact(column, "=", "?"));
		return this;
	}

	public QueryBuilderSelect where(String column, String value) {
		this.wheres.add(this.contact(column, "=", value));
		return this;
	}

	public QueryBuilderSelect where(String column, String condition, String value) {
		this.wheres.add(this.contact(column, condition, value));
		return this;
	}

	public QueryBuilderSelect orderBy(String column) {
		this.orders.add(this.getColumn(column) + " ASC");
		return this;
	}

	public QueryBuilderSelect orderByDesc(String column) {
		this.orders.add(this.getColumn(column) + " DESC");
		return this;
	}

	public String getSql() {
		String sql = "SELECT " + String.join(", ", this.columns) + " FROM " + this.getEscaped(this.table);
		if (this.wheres.size() > 0) {
			sql += " WHERE " + String.join(" AND ", this.wheres);
		}
		if (this.orders.size() > 0) {
			sql += " ORDER BY " + String.join(", ", this.orders);
		}
		return sql + ";";
	}

	public void execute(ResultSetProcessor processor, Object... params) {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		try {
			connection = ConnectionFactory.getConnection();
			stmt = connection.prepareStatement(this.getSql());
			int count = 0;
			for (Object param : params) {
				stmt.setObject(++count, param);
			}
			result = stmt.executeQuery();
			long rowCount = 0;
			while (result.next()) {
				processor.process(result, rowCount++);
			}
			result.close();
			stmt.close();
			connection.close();
		} catch (Exception e) {
			// Log
		} finally {
			ConnectionFactory.close(connection, stmt, result);
		}
	}
}
