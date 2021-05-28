/**
 * ----------------------------
 * Java JDBC API basis, coding exercises.
 * ----------------------------
 * 
 */
package me.fevvelasquez.jdbc.api.dao.daoe;

/**
 * Custom exception for DAO operations.
 * 
 * @version 0.1.1. Creating DAO classes.
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
