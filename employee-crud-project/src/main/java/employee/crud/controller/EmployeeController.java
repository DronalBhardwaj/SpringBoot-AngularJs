package employee.crud.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import employee.crud.beans.Employee;
import employee.crud.dao.EmployeeDAO;
import employee.crud.dao.EmployeeDAOImpl;

/**
 * Servlet implementation class EmployeeController
 */
@WebServlet("/")
public class EmployeeController extends HttpServlet
{
	EmployeeDAO employeeDAO=null;
	private static final long serialVersionUID = 1L;
       
    
    public EmployeeController()
    {
        super();
        
    }
    
    @Override
	public void init() throws ServletException
	{
		super.init();
		employeeDAO=new EmployeeDAOImpl();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println("EmployeeController, dopost method started");
		String action=request.getServletPath();
		System.out.println("dopost, action "+action);
		switch (action)
		{
		case "/add":
		{
			addNewEmployee(request,response);
			break;
		}
		case "/update":
		{
			updateEmployee(request,response);
			break;
		}
		case "/delete":
		{
			deleteEmployee(request,response);
			break;
		}
		case "/list":
		{
			getAllEmployees(request,response);
			break;
		}
		case "/get":
		{
			getEmployee(request,response);
			break;
		}
		default:
			getAllEmployees(request,response);
			break;
		}
		
	}

	private void getEmployee(HttpServletRequest request, HttpServletResponse response)
	{
		System.out.println("getEmployee started ");
		
		int id=Integer.parseInt(request.getParameter("id"));
		System.out.println("getEmployee, EmployeeId "+id);
		
		Employee employee=employeeDAO.getEmployee(id);
		System.out.println("getEmployee, result "+employee);
		
		
		try
		{
			//for converting JSON object from AngularJS to java object and viceversa
			ObjectWriter mapper=new ObjectMapper().writer().withDefaultPrettyPrinter();
			String employeeStr=mapper.writeValueAsString(employee);
			
			ServletOutputStream outputStream=response.getOutputStream();
			outputStream.write(employeeStr.getBytes());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
	}

	private void getAllEmployees(HttpServletRequest request, HttpServletResponse response)
	{
		System.out.println("getAllEmployees started");
		
		List<Employee> employees=employeeDAO.getAllEmployees();
		System.out.println("getAllEmployees, employee size "+employees.size());
		
		try
		{
			RequestDispatcher dispatcher=request.getRequestDispatcher("/employeesView.jsp");
			request.setAttribute("employees", employees);
			dispatcher.forward(request, response);
		}
		catch (ServletException | IOException e)
		{
			e.printStackTrace();
		}
		
	}

	private void deleteEmployee(HttpServletRequest request, HttpServletResponse response)
	{
		System.out.println("deleteEmployee started");
		String id=request.getParameter("id");
		System.out.println("deleteEmployee, EmployeeId "+id);
		
		StringTokenizer stringTokenizer=new StringTokenizer(id, ",");
		while(stringTokenizer.hasMoreElements())
		{
			int employeeId=Integer.parseInt(stringTokenizer.nextToken());
			boolean result=employeeDAO.deleteEmployee(employeeId);
			System.out.println("deleteEmployee, result "+result);
		}
		
		List<Employee> employees=employeeDAO.getAllEmployees();
		System.out.println("getAllEmployees, employee size "+employees.size());
		
		try
		{
			RequestDispatcher dispatcher=request.getRequestDispatcher("/employeeView.jsp");
			request.setAttribute("employees", employees);
			dispatcher.forward(request, response);
		} 
		catch (ServletException | IOException e)
		{
			e.printStackTrace();
		}
	}

	private void updateEmployee(HttpServletRequest request, HttpServletResponse response)
	{
		System.out.println("Start updateEmployee");
		int id=Integer.parseInt(request.getParameter("id"));
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String number=request.getParameter("phone");
		String address=request.getParameter("address");
		
		Employee employee=new Employee(id, name, email, number, address);
		System.out.println("updateEmployee, Employee Details"+employee);
		
		boolean result=employeeDAO.updateEmployee(employee);
		System.out.println("updateEmployee, result is "+result);
		
		List<Employee> employees=employeeDAO.getAllEmployees();
		System.out.println("getAllEmployees, employee size "+employees.size());
		try
		{
			RequestDispatcher dispatcher=request.getRequestDispatcher("/employeesView.jsp");
			request.setAttribute("employees", employees);
			dispatcher.forward(request, response);
		}
		catch (ServletException | IOException e)
		{
			e.printStackTrace();
		}
	}

	private void addNewEmployee(HttpServletRequest request, HttpServletResponse response)
	{
		System.out.print("Start addNewEmployee : ");
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String number=request.getParameter("phone");
		String address=request.getParameter("address");
		
		Employee employee=new Employee(name,email,number,address);
		System.out.println("addNewEmployee, Employee Details : "+ employee);
		
		boolean result=employeeDAO.addEmployee(employee);
		System.out.println("addNewEmployee, result is : "+ result);
		
		List<Employee> employees=employeeDAO.getAllEmployees();
		System.out.println("getAllEmployees, employee size "+employees.size());
		
		try
		{
			RequestDispatcher dispatcher=request.getRequestDispatcher("/employeesView.jsp");
			request.setAttribute("employees", employees);
			dispatcher.forward(request, response);
		}
		 catch (ServletException | IOException e)
		{
			e.printStackTrace();
		}
		
	}

}
