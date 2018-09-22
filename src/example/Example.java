package example;

import java.util.ArrayList;

import com.vaneves.querybuilder.QueryBuilder;

public class Example {
	public static void main(String[] args) {
		
		QueryBuilder.delete("user").where("id", ">=", "?").execute(1);
		
		QueryBuilder.insert("user")
			.columns("id", "name")
			.execute(1, "Van Neves");
		
		QueryBuilder.insert("user")
			.columns("id", "name")
			.execute(2, "Jõao Victor");
		
		QueryBuilder.insert("user")
			.columns("id", "name")
			.execute(3, "Glaucia Carvalho");
		
		QueryBuilder.insert("user")
			.columns("id", "name")
			.execute(4, "Fábio Ricardo");
		
		QueryBuilder.insert("user")
			.columns("id", "name")
			.execute(5, "Paulo Rorberto");
	
		QueryBuilder.update("user")
			.set("name", "?")
			.where("id", "?")
			.execute("Nyl Marcos", 4);
		
		ArrayList<User> users = new ArrayList<User>();
		QueryBuilder.select("id", "name")
			.from("user")
			.where("id", ">", "?")
			.orderBy("name")
			.execute((rs, cnt)-> {
				users.add(new User(rs.getInt("id"), rs.getString("name")));
			}, 1);
		
		for (User model : users) {
			System.out.println(model.getId() +": "+ model.getName());
		}
	}
}

class User {
	private int id;
	private String name;
	
	public User(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
}
