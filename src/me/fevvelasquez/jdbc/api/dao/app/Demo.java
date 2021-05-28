/**
 * ----------------------------
 * Java JDBC API basis, coding exercises.
 * ----------------------------
 * 
 */
package me.fevvelasquez.jdbc.api.dao.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import me.fevvelasquez.jdbc.api.dao.dao.EmployeeDAO;
import me.fevvelasquez.jdbc.api.dao.dao.OfficeDAO;
import me.fevvelasquez.jdbc.api.dao.daoe.DAOException;
import me.fevvelasquez.jdbc.api.dao.model.Employee;
import me.fevvelasquez.jdbc.api.dao.model.Office;

/**
 * Demo, examples of use.
 * 
 * @version 0.2.0. Creating Data Access Objects to implement CRUD to the
 *          database tables.
 * @author fevvelasquez@gmail.com
 */
public class Demo {
	private static final Logger logger;
	private static Connection connection;
	private static OfficeDAO officeDAO;
	private static EmployeeDAO employeeDAO;

	static {
		logger = Logger.getLogger(Demo.class.getName());
		// Get connection to the database.
		ResourceBundle db = ResourceBundle.getBundle(Demo.class.getPackageName() + ".db");
		try {
			connection = DriverManager.getConnection(db.getString("url"), db.getString("username"),
					db.getString("password"));

			officeDAO = new OfficeDAO(connection);
			employeeDAO = new EmployeeDAO(connection);
		} catch (SQLException e) {
			logger.info(e.getMessage());
		}
		// ------------------------------------------------------------------------------------
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
			officeDAO.delete(officeId);
			logger.info("\n");
			// Insert office
			insertOffice(officeId, "3320 Montes Urales", "Ciudad de Mexico", "ME");
			// Update office
			updateOffice(officeId, "1022 Fanta Fe", "Mexico City", "MX");
			// Select All
			List<Office> officeList = officeDAO.getAll();
			officeList.stream().forEach(o -> logger.info("--- --- " + o.toString() + " --- ---"));
			// ------------------------------------------------------------------------------------

			// EMPLOYEE:
			Integer employeeId = 120;
			// Delete office
			employeeDAO.delete(employeeId);
			logger.info("\n");
			// Insert office
			insertEmployee(employeeId, "Eduardo", "Santamarina", "Mechanic Asesor", 30_000, null, 2);
			// Update office
			updateEmployee(employeeId, "Eduardo Saul", "Santamarina", "Mechanic Asesor Sr", 40_000, null, 3);
			// Select All
			List<Employee> employeeList = employeeDAO.getAll();
			employeeList.stream().forEach(e -> logger.info("--- --- " + e.toString() + " --- ---"));
			// ------------------------------------------------------------------------------------

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
		officeDAO.insert(office);
		selectOneOffice(officeId);
	}

	@SuppressWarnings("unused")
	private static void updateOffice(Integer officeId, String address, String city, String state) throws DAOException {
		Office office = new Office(officeId, address, city, state);
		officeDAO.update(office);
		selectOneOffice(officeId);
	}

	@SuppressWarnings("unused")
	private static void selectOneOffice(int officeId) throws DAOException {
		Office office = officeDAO.getOne(officeId);
		logger.info("<<< --- " + office.toString() + " --- >>>");
		logger.info("\n");
	}

	// EMPLOYEE
	// temporary aux methods
	@SuppressWarnings("unused")
	private static void insertEmployee(Integer employeeId, String firstName, String lastName, String jobTitle,
			Integer salary, Integer reportsTo, Integer officeId) throws DAOException {
		Employee employee = new Employee(employeeId, firstName, lastName, jobTitle, salary, reportsTo, officeId);
		employeeDAO.insert(employee);
		selectOneEmployee(employeeId);
	}

	@SuppressWarnings("unused")
	private static void updateEmployee(Integer employeeId, String firstName, String lastName, String jobTitle,
			Integer salary, Integer reportsTo, Integer officeId) throws DAOException {
		Employee employee = new Employee(employeeId, firstName, lastName, jobTitle, salary, reportsTo, officeId);
		employeeDAO.update(employee);
		selectOneEmployee(employeeId);
	}

	@SuppressWarnings("unused")
	private static void selectOneEmployee(int employeeId) throws DAOException {
		Employee employee = employeeDAO.getOne(employeeId);
		logger.info("<<< --- " + employee.toString() + " --- >>>");
		logger.info("\n");
	}

}
