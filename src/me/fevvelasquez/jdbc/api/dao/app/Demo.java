/**
 * ----------------------------
 * Java JDBC API basis, coding exercises.
 * ----------------------------
 * 
 */
package me.fevvelasquez.jdbc.api.dao.app;

import java.util.List;
import java.util.logging.Logger;

import me.fevvelasquez.jdbc.api.dao.dao.DAOManager;
import me.fevvelasquez.jdbc.api.dao.daoe.DAOException;
import me.fevvelasquez.jdbc.api.dao.model.Employee;
import me.fevvelasquez.jdbc.api.dao.model.Office;

/**
 * Demo, examples of use.
 * 
 * @version 0.3. Creating singleton DAOManager to perform DAO operations.
 * @author fevvelasquez@gmail.com
 */
public class Demo {
	private static final Logger logger;

	static {
		logger = Logger.getLogger(Demo.class.getName());
	}

	/**
	 * Main method.
	 * 
	 * @param args No args required.
	 */
	public static void main(String[] args) {

		try {

			// OFFICE:
			Integer officeId = 120;
			// Delete office
			DAOManager.getOfficeDAO().delete(officeId);
			logger.info("\n");
			// Insert office
			insertOffice(officeId, "3320 Montes Urales", "Ciudad de Mexico", "ME");
			// Update office
			updateOffice(officeId, "1022 Fanta Fe", "Mexico City", "MX");
			// Select All
			List<Office> officeList = DAOManager.getOfficeDAO().getAll();
			officeList.stream().forEach(o -> logger.info("--- --- " + o.toString() + " --- ---"));
			// ------------------------------------------------------------------------------------

			logger.info("\n");
			
			// EMPLOYEE:
			Integer employeeId = 120;
			// Delete office
			DAOManager.getEmployeeDAO().delete(employeeId);
			logger.info("\n");
			// Insert office
			insertEmployee(employeeId, "Eduardo", "Santamarina", "Mechanic Asesor", 30_000, null, 2);
			// Update office
			updateEmployee(employeeId, "Eduardo Saul", "Santamarina", "Mechanic Asesor Sr", 40_000, null, 3);
			// Select All
			List<Employee> employeeList = DAOManager.getEmployeeDAO().getAll();
			employeeList.stream().forEach(e -> logger.info("--- --- " + e.toString() + " --- ---"));
			// ------------------------------------------------------------------------------------
			DAOManager.close();

		} catch (Exception e) {
			logger.severe(e.getMessage());
		}
		logger.info("END.");
	}

	// OFFICE
	// temporary aux methods
	@SuppressWarnings("unused")
	private static void insertOffice(Integer officeId, String address, String city, String state) throws DAOException {
		Office office = new Office(officeId, address, city, state);
		DAOManager.getOfficeDAO().insert(office);
		selectOneOffice(officeId);
	}

	@SuppressWarnings("unused")
	private static void updateOffice(Integer officeId, String address, String city, String state) throws DAOException {
		Office office = new Office(officeId, address, city, state);
		DAOManager.getOfficeDAO().update(office);
		selectOneOffice(officeId);
	}

	@SuppressWarnings("unused")
	private static void selectOneOffice(int officeId) throws DAOException {
		Office office = DAOManager.getOfficeDAO().getOne(officeId);
		logger.info("<<< --- " + office.toString() + " --- >>>");
		logger.info("\n");
	}

	// EMPLOYEE
	// temporary aux methods
	@SuppressWarnings("unused")
	private static void insertEmployee(Integer employeeId, String firstName, String lastName, String jobTitle,
			Integer salary, Integer reportsTo, Integer officeId) throws DAOException {
		Employee employee = new Employee(employeeId, firstName, lastName, jobTitle, salary, reportsTo, officeId);
		DAOManager.getEmployeeDAO().insert(employee);
		selectOneEmployee(employeeId);
	}

	@SuppressWarnings("unused")
	private static void updateEmployee(Integer employeeId, String firstName, String lastName, String jobTitle,
			Integer salary, Integer reportsTo, Integer officeId) throws DAOException {
		Employee employee = new Employee(employeeId, firstName, lastName, jobTitle, salary, reportsTo, officeId);
		DAOManager.getEmployeeDAO().update(employee);
		selectOneEmployee(employeeId);
	}

	@SuppressWarnings("unused")
	private static void selectOneEmployee(int employeeId) throws DAOException {
		Employee employee = DAOManager.getEmployeeDAO().getOne(employeeId);
		logger.info("<<< --- " + employee.toString() + " --- >>>");
		logger.info("\n");
	}

}
