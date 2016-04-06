package oracle.academy.model;

public class User {
	
	private Long id;
	private String email;
	private String firstName;
	private String lastName;
	private Integer age;
	private Role role;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	public User(){
		
	}
	
	public User(Long id, String email, String firstName, String lastName, Integer age, Role role){
		this.id=id;
		this.email=email;
		this.firstName=firstName;
		this.lastName=lastName;
		this.age=age;
		this.role=role;
	}
}
