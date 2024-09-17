package employee.crud.beans;

public class Employee
{
private int id;
private String name;
private String email;
private String number;
private String address;

public Employee() {
	super();
}

public Employee(String name, String email, String number, String address) {
	super();
	this.name = name;
	this.email = email;
	this.number = number;
	this.address = address;
}

public Employee(int id, String name, String email, String number, String address) {
	super();
	this.id = id;
	this.name = name;
	this.email = email;
	this.number = number;
	this.address = address;
}

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getNumber() {
	return number;
}
public void setNumber(String number) {
	this.number = number;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}

@Override
public String toString() {
	return "Employee [id=" + id + ", name=" + name + ", email=" + email + ", number=" + number + ", address=" + address
			+ "]";
}

}
