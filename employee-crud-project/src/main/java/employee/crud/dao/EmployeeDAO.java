package employee.crud.dao;

import java.util.List;

import employee.crud.beans.Employee;

public interface EmployeeDAO
{
	//Insert Employee
	public boolean addEmployee(Employee employee);
	
	//Update Employee
	public boolean updateEmployee(Employee employee);
	
	//Delete Employee
	public boolean deleteEmployee(int employeeId);
	
	//Get all Employees
	public List <Employee> getAllEmployees();
	
	//Get 1 Employee
	public Employee getEmployee(int employeeId);
}
