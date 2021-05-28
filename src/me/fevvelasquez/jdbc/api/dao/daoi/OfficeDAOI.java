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
package me.fevvelasquez.jdbc.api.dao.daoi;

import me.fevvelasquez.jdbc.api.dao.model.Office;

/**
 * OfficeDAOI interface.
 * 
 * @version  0.3.x. Demo version.
 * @author fevvelasquez@gmail.com
 */
public interface OfficeDAOI extends DAO<Office, Integer> {

}
