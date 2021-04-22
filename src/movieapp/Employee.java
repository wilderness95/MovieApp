package movieapp;

/**
 *
 * @author Wilderness
 */

public class Employee {

	String employeeName;
	int employeeId;
	String password;

	public Employee(String name, int employeeId) {
		this.employeeName = name;
		this.employeeId = employeeId;
		this.password = "1234";

	}

	public String getEmployeename() {
		return employeeName;
	}

	public void setEmployeename(String name) {
		this.employeeName = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	@Override
	public String toString() {
		return "employeeId: " + employeeId + " Name: " + employeeName;
	}

}