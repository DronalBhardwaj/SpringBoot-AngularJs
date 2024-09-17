package employee.crud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import employee.crud.beans.Employee;
import employee.crud.db.DbConnection;

public class EmployeeDAOImpl implements EmployeeDAO
{
	private static Connection con=DbConnection.con;

	@Override
	public boolean addEmployee(Employee employee)
	{
		System.out.println("addEmployee Started");
		try
		{
			String insertStatement="INSERT INTO employee_db.employee (name, email, phone, address) VALUES (?, ?, ?, ?)"+ "";
			PreparedStatement prep=con.prepareStatement(insertStatement);
			prep.setString(1, employee.getName());
			prep.setString(2, employee.getEmail());
			prep.setString(3, employee.getNumber());
			prep.setString(4, employee.getAddress());
			
			int result=prep.executeUpdate();
			return result== 1? true :false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateEmployee(Employee employee)
	{
		System.out.print("updateEmployee started :");
		try
		{
			String updateStatement="update employee_db.employee SET NAME=?, email=?, phone=?, address=? where id=?";
			PreparedStatement preparedStatement=con.prepareStatement(updateStatement);
			
			preparedStatement.setString(1, employee.getName());
			preparedStatement.setString(2, employee.getEmail());
			preparedStatement.setString(3, employee.getNumber());
			preparedStatement.setString(4, employee.getAddress());
			preparedStatement.setInt(5, employee.getId());
			int result=preparedStatement.executeUpdate();
			return result==1? true:false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteEmployee(int employeeId)
	{
		System.out.println("deleteEmployee started !");
		try
		{
			String deleteStatement="delete from employee_db.employee where ID=?";
			PreparedStatement preparedStatement=con.prepareStatement(deleteStatement);
			preparedStatement.setInt(1, employeeId);
			int result=preparedStatement.executeUpdate();
			return result==1? true:false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Employee> getAllEmployees()
	{
		System.out.println("getAllEmployees started ! ");
		try
		{
			String getAllEmployeesStatement="select * from employee_db.employee";
			PreparedStatement preparedStatement=con.prepareStatement(getAllEmployeesStatement);
			ResultSet resultSet=preparedStatement.executeQuery();
			List<Employee> employees=new ArrayList<Employee>();
			while(resultSet.next())
			{
				Employee employee=new Employee();
				employee.setId(resultSet.getInt("id"));
				employee.setName(resultSet.getString("name"));
				employee.setEmail(resultSet.getString("email"));
				employee.setNumber(resultSet.getString("phone"));
				employee.setAddress(resultSet.getString("address"));
				employees.add(employee);
			}
			return employees;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Employee getEmployee(int employeeId) {
		System.out.println("getEmployee started ! ");
		try
		{
			String getEmployeeStatement="select * from employee_db.employee where id=?";
			PreparedStatement preparedStatement=con.prepareStatement(getEmployeeStatement);
			preparedStatement.setInt(1, employeeId);
			ResultSet resultSet=preparedStatement.executeQuery();
			Employee employee=new Employee();
			while(resultSet.next())
			{
				
				employee.setId(resultSet.getInt("id"));
				employee.setName(resultSet.getString("name"));
				employee.setEmail(resultSet.getString("email"));
				employee.setNumber(resultSet.getString("phone"));
				employee.setAddress(resultSet.getString("address"));
			}
			return employee;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String args[])
	{
		Employee employee=new Employee();
		employee.setId(10);
		employee.setName("dbdro");
		employee.setEmail("email@gmail.com");
		employee.setNumber("0000000000");
		employee.setAddress("Mathura");
		
		EmployeeDAOImpl employeeDAOImpl=new EmployeeDAOImpl();
		//System.out.println(employeeDAOImpl.addEmployee(employee));
		//System.out.println(employeeDAOImpl.updateEmployee(employee));
		//System.out.println(employeeDAOImpl.deleteEmployee(11));
		//System.out.println(employeeDAOImpl.getAllEmployees().size()); //gets total entries in number .get(id) gets a particular entry 
		System.out.println(employeeDAOImpl.getEmployee(5));
	}
}