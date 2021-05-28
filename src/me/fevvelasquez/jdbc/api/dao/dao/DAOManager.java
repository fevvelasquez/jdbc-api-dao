/**
 * ----------------------------
 * Java JDBC API basis, coding exercises.
 * Exploring: java.sql.DriverManager , java.sql.Connection , java.sql.PreparedStatement , java.sql.ResultSet features.
 * ----------------------------
 * 
 * fevvelasquez writes an exercise using Java JDBC API for 
 * DAO pattern to get separate data access from business logic.
 * 
 * DAO classes provide CRUD database operations coding, including SQL statements needed.
 * 
 * 
 * Even if MySql is used for the demo, a distinct RDBMS or distinct database can be used.
 */
package me.fevvelasquez.jdbc.api.dao.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import me.fevvelasquez.jdbc.api.dao.daoi.EmployeeDAOI;
import me.fevvelasquez.jdbc.api.dao.daoi.OfficeDAOI;

/**
 * Singleton DAOManager.
 * 
 * @version 0.3.x. Demo version.
 * @author fevvelasquez@gmail.com
 */
public class DAOManager {

	private static final Logger logger;
	private static final Connection connection;
	private static final OfficeDAOI officeDAO;
	private static final EmployeeDAOI employeeDAO;

	static {
		//
		logger = Logger.getLogger(DAOManager.class.getName());
		// ------------------------------------------------------------------------------------

		// Connection resources:
		connection = getConnection();
		officeDAO = new OfficeDAO(connection);
		employeeDAO = new EmployeeDAO(connection);
		// ------------------------------------------------------------------------------------
	}

	private static Connection getConnection() {
		ResourceBundle db = ResourceBundle.getBundle("db");
		Connection connection_test = null;
		try {
			// Get connection to the database.
			connection_test = DriverManager.getConnection(db.getString("url"), db.getString("username"),
					db.getString("password"));
			// ------------------------------------------------------------------------------------
		} catch (SQLException e) {
			logger.severe("Connection Error: " + e.getMessage());
		}
		return connection_test;
	}

	/**
	 * {@link OfficeDAO} to perform database operation on {@code offices} table.
	 * 
	 * @return the OfficeDAO immutable instance.
	 */
	public static OfficeDAOI getOfficeDAO() {
		return officeDAO;
	}

	/**
	 * {@link EmployeeDAO} to perform database operation on {@code employees} table.
	 * 
	 * @return the EmployeeDAO immutable instance.
	 */
	public static EmployeeDAOI getEmployeeDAO() {
		return employeeDAO;
	}

	/**
	 * Release connection resources.
	 */
	public static void close() {
		try {
			connection.close();
			logger.info("Connection closed.");
		} catch (Exception e) {
			logger.severe("Closing Connection Error: " + e.getMessage());
		}
	}

}
