/**
 * ----------------------------
 * Java JDBC API basis, coding exercises.
 * ----------------------------
 * 
 */
package me.fevvelasquez.jdbc.api.dao.app;

import java.util.logging.Logger;

import me.fevvelasquez.jdbc.api.dao.model.Employee;
import me.fevvelasquez.jdbc.api.dao.model.Office;

/**
 * Demo, examples of use.
 * 
 * @version 0.1.0. Creating model objects to represent database tables.
 * @author fevvelasquez@gmail.com
 */
public class Demo {
	private static final Logger logger = Logger.getLogger(Demo.class.getName());

	/**
	 * Main method.
	 * 
	 * @param args No args required.
	 */
	public static void main(String[] args) {

		try {

			var office100 = new Office(100, "2330 Montes Urales", "Mexico City", "MX");
			var employee100 = new Employee(100, "Juia", "Estrada", "IT Manager", 80_000, null, office100);
			var employee200 = new Employee(200, "Aaron", "Lopez", "IT Developer", 40_000, employee100, office100);
			logger.info("\n" + employee100.toString());
			logger.info("\n" + employee200.toString());

		} catch (Exception e) {
			logger.severe(e.getMessage());
		}
		logger.info("END.");
	}

}
