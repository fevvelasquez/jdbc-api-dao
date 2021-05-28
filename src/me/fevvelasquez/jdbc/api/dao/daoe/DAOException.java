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
package me.fevvelasquez.jdbc.api.dao.daoe;

/**
 * Custom exception for DAO operations.
 * 
 * @version 0.3.x. Demo version.
 * @author fevvelasquez@gmail.com
 */
@SuppressWarnings("serial")
public class DAOException extends Exception {

	/**
	 * Custom exception for DAO operations.
	 */
	public DAOException() {
		super();
	}

	/**
	 * Custom exception for DAO operations.
	 */
	public DAOException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Custom exception for DAO operations.
	 */
	public DAOException(String message) {
		super(message);
	}

	/**
	 * Custom exception for DAO operations.
	 */
	public DAOException(Throwable cause) {
		super(cause);
	}

}
