package com.vaneves.querybuilder;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author vaneves
 */

@FunctionalInterface
public interface ResultSetProcessor {

	public void process(ResultSet resultSet, long currentRow) throws SQLException;
}