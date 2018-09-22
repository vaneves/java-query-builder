# ☕ Java Query Builder

A simple library for database with Java.

## Examples



### Insert

```java
QueryBuilder.insert("user")
	.columns("id", "name")
	.execute(1, "Van Neves");
```

| Method | Params | Return |
| --- | --- | --- |
| `insert()` | Table name | `QueryBuilderInsert` |
| `columns()` | Columns name | `QueryBuilderInsert` |
| `execute()` | Values | `boolean` |

### Update

```java
QueryBuilder.update("user")
	.set("name", "?")
	.where("id", "?")
	.execute("Nyl Marcos", 4);
```

| Method | Params | Return |
| --- | --- | --- |
| `update()` | Table name | `QueryBuilderUpdate` |
| `set()` | The column and new value  | `QueryBuilderUpdate` |
| `setSafe()` | The column (the value will be passed in the `execute()` method)  | `QueryBuilderUpdate` |
| `where()` | Column, comparator and value | `QueryBuilderUpdate` |
| `whereSafe()` | The column (the value will be passed in the `execute()` method) | `QueryBuilderUpdate` |
| `execute()` | Values | `boolean` |

### Delete

```java
QueryBuilder.delete("user")
	.where("id", ">=", "?")
	.execute(1);
```

| Method | Params | Return |
| --- | --- | --- |
| `delete()` | Table name | `QueryBuilderDelete` |
| `where()` | Column, comparator and value | `QueryBuilderDelete` |
| `whereSafe()` | The column (the value will be passed in the `execute()` method) | `QueryBuilderDelete` |
| `execute()` | Values | `boolean` |

### List

```java
ArrayList<User> users = new ArrayList<User>();

QueryBuilder.select("id", "name")
	.from("user")
	.where("id", ">", "?")
	.orderBy("name")
	.execute((rs, cnt)-> {
		users.add(new User(rs.getInt("id"), rs.getString("name")));
	}, 1);
```

| Method | Params | Return |
| --- | --- | --- |
| `select()` | Columns name | `QueryBuilderSelect` |
| `from()` | The table name  | `QueryBuilderSelect` |
| `where()` | Column, comparator and value | `QueryBuilderSelect` |
| `whereSafe()` | The column (the value will be passed in the `execute()` method) | `QueryBuilderSelect` |
| `orderBy()` | The column name | `QueryBuilderSelect` |
| `orderByDesc()` | The column name | `QueryBuilderSelect` |
| `execute()` | Lambda and values | `void` |

### Get

```java
User user;

QueryBuilder.select("id", "name")
	.from("user")
	.where("id", "=", "?")
	.execute((rs, cnt)-> {
		user = new User(rs.getInt("id"), rs.getString("name"));
	}, 1);
```