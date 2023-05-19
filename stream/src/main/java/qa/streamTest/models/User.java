package qa.streamTest.models;

public class User {
	private String name;
	private int age;

	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + "]";
	}

	public User(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

}
