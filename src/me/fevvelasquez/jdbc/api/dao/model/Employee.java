/**
 * ----------------------------
 * Java JDBC API basis, coding exercises.
 * ----------------------------
 * 
 */
package me.fevvelasquez.jdbc.api.dao.model;

/**
 * The model class that represents the {@code employees} table.
 * 
 * @version 0.1.0. Creating model objects to represent database tables.
 * @author fevvelasquez@gmail.com
 */
public class Employee {
	private final Integer employeeId;
	private final String firstName;
	private final String lastName;
	private final String jobTitle;
	private final Integer salary;
	private final Employee reportsTo;
	private final Office office;

	/**
	 * @param employee_id from {@code employees} table.
	 * @param firstName   from {@code employees} table.
	 * @param lastName    from {@code employees} table.
	 * @param jobTitle    from {@code employees} table.
	 * @param salary      from {@code employees} table.
	 * @param reportsTo   from {@code employees} table.
	 * @param office      from {@code employees} table.
	 */
	public Employee(Integer employeeId, String firstName, String lastName, String jobTitle, Integer salary,
			Employee reportsTo, Office office) {
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.jobTitle = jobTitle;
		this.salary = salary;
		this.reportsTo = reportsTo;
		this.office = office;
	}

	/**
	 * Employee number.
	 * 
	 * @return the employee_id
	 */
	public Integer getEmployeeId() {
		return employeeId;
	}

	/**
	 * Employee first name.
	 * 
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Employee last name.
	 * 
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Employee job title.
	 * 
	 * @return the jobTitle
	 */
	public String getJobTitle() {
		return jobTitle;
	}

	/**
	 * Employee salary
	 * 
	 * @return the salary
	 */
	public Integer getSalary() {
		return salary;
	}

	/**
	 * Employee's boss.
	 * 
	 * @return the reportsTo
	 */
	public Employee getReportsTo() {
		return reportsTo;
	}

	/**
	 * Employee's office.
	 * 
	 * @return the office
	 */
	public Office getOffice() {
		return office;
	}

	@Override
	public String toString() {
		return "Employee <<employeeId=" + employeeId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", jobTitle=" + jobTitle + ", salary=" + salary + ", \nreportsTo="
				+ ((reportsTo == null) ? "null"
						: "Employee(employeeId=" + reportsTo.employeeId + ", jobTitle=" + reportsTo.jobTitle + ")")
				+ "\noffice=" + office + ">>";
	}

}
