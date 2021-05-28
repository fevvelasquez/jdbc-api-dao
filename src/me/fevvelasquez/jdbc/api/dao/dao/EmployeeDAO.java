/**
 * ----------------------------
 * Java JDBC API basis, coding exercises.
 * ----------------------------
 * 
 */
package me.fevvelasquez.jdbc.api.dao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import me.fevvelasquez.jdbc.api.dao.daoe.DAOException;
import me.fevvelasquez.jdbc.api.dao.daoi.EmployeeDAOI;
import me.fevvelasquez.jdbc.api.dao.model.Employee;

/**
 * DAO for {@link Employee} model class.
 * 
 * @version 0.3. Creating singleton DAOManager to perform DAO operations.
 * @author fevvelasquez@gmail.com
 */
class EmployeeDAO implements EmployeeDAOI {
	private static final Logger logger = Logger.getLogger(EmployeeDAO.class.getName());
	private static final String SQL_INSERT = "INSERT INTO employees(employee_id, first_name, last_name, job_title, salary, reports_to, office_id) VALUES(?,?,?,?,?,?,?)";
	private static final String SQL_UPDATE = "UPDATE employees SET first_name=?, last_name=?, job_title=?, salary=?, reports_to=?, office_id=? WHERE employee_id=?";
	private static final String SQL_DELETE = "DELETE FROM employees WHERE employee_id=?";
	private static final String SQL_SELECT_ALL = "SELECT * FROM employees";
	private static final String SQL_SELECT_ONE = "SELECT * FROM employees WHERE employee_id=?";

	private final Connection connection;

	/**
	 * Create {@link Employee} instance.
	 * 
	 * @param Database connection.
	 */
	public EmployeeDAO(Connection connection) {
		this.connection = connection;
	}

	/**
	 * Insert {@link Employee} into {@code employees} table.
	 * 
	 * @param employee object to insert.
	 */
	@Override
	public void insert(Employee employee) throws DAOException {
		try (final PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT);) {
			preparedStatement.setInt(1, employee.getEmployeeId());
			preparedStatement.setString(2, employee.getFirstName());
			preparedStatement.setString(3, employee.getLastName());
			preparedStatement.setString(4, employee.getJobTitle());
			preparedStatement.setInt(5, employee.getSalary());
			if (employee.getReportsTo() != null)
				preparedStatement.setInt(6, employee.getReportsTo());
			else
				preparedStatement.setNull(6, Types.INTEGER);
			preparedStatement.setInt(7, employee.getOfficeId());
			preparedStatement.executeUpdate();
			logger.info("Employee inserted with employee_id:" + employee.getEmployeeId() + ".");
		} catch (SQLException e) {
			throw new DAOException("DAO: Error inserting row. " + e.getMessage());
		}
	}

	/**
	 * Update {@link Employee} into {@code employees} table.
	 * 
	 * @param employee object to update.
	 */
	@Override
	public void update(Employee employee) throws DAOException {
		try (final PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE);) {
			preparedStatement.setString(1, employee.getFirstName());
			preparedStatement.setString(2, employee.getLastName());
			preparedStatement.setString(3, employee.getJobTitle());
			preparedStatement.setInt(4, employee.getSalary());
			if (employee.getReportsTo() != null)
				preparedStatement.setInt(5, employee.getReportsTo());
			else
				preparedStatement.setNull(5, Types.INTEGER);
			preparedStatement.setInt(6, employee.getOfficeId());
			preparedStatement.setInt(7, employee.getEmployeeId());
			preparedStatement.executeUpdate();
			logger.info("Employee updated with employee_id:" + employee.getEmployeeId() + ".");
		} catch (SQLException e) {
			throw new DAOException("DAO: Error updating row. " + e.getMessage());
		}
	}

	@Override
	public void delete(Integer employeeId) throws DAOException {
		try (final PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE);) {
			preparedStatement.setInt(1, employeeId);
			preparedStatement.executeUpdate();
			logger.info("Employee deleted with employeeId:" + employeeId + ".");
		} catch (SQLException e) {
			throw new DAOException("DAO: Error deleting row. " + e.getMessage());
		}
	}

	/**
	 * Get a list of all the {@link Employee} objects in {@code employees} table.
	 * 
	 * @return A list of all the employees.
	 */
	@Override
	public List<Employee> getAll() throws DAOException {
		List<Employee> list = null;
		try (final PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL);
				final ResultSet resultSet = preparedStatement.executeQuery();) {
			list = new ArrayList<Employee>();
			while (resultSet.next()) {
				Employee employee = extract(resultSet);
				list.add(employee);
			}
		} catch (SQLException e) {
			throw new DAOException("DAO: Error getting rows. " + e.getMessage());
		}
		return list;
	}

	/**
	 * Get the {@link Employee} with the given Employee in {@code employees} table.
	 * 
	 * @return A employee that corresponds to the given employee_id. Null if there
	 *         is no match.
	 */
	@Override
	public Employee getOne(Integer employeeId) throws DAOException {
		Employee employee = null;
		try (final PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ONE);) {
			preparedStatement.setInt(1, employeeId);
			final ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				employee = extract(resultSet);
			}
			preparedStatement.close();
		} catch (SQLException e) {
			throw new DAOException("DAO: Error getting row. " + e.getMessage());
		}
		return employee;
	}

	private Employee extract(ResultSet resultSet) throws SQLException, DAOException {
		Integer employeeId = Integer.valueOf(resultSet.getInt("employee_id"));
		String firstName = resultSet.getString("first_name");
		String lastName = resultSet.getString("last_name");
		String jobTitle = resultSet.getString("job_title");
		Integer salary = resultSet.getInt("salary");
		Integer reportsTo = resultSet.getInt("reports_to");
		reportsTo = (resultSet.wasNull()) ? null : reportsTo;
		Integer officeId = resultSet.getInt("office_id");

		return new Employee(employeeId, firstName, lastName, jobTitle, salary, reportsTo, officeId);
	}

}