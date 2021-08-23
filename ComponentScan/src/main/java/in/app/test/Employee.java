package in.app.test;

import org.springframework.stereotype.Component;

@Component
public class Employee {

	private Integer Id;

	private String name;
	
	public Employee() {
		super();
	}

	public Employee(Integer id, String name) {
		super();
		Id = id;
		this.name = name;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Employee [Id=" + Id + ", name=" + name + "]";
	}
	

}
