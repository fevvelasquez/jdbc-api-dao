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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import me.fevvelasquez.jdbc.api.dao.daoe.DAOException;
import me.fevvelasquez.jdbc.api.dao.daoi.OfficeDAOI;
import me.fevvelasquez.jdbc.api.dao.model.Office;

/**
 * DAO for {@link Office} model class.
 * 
 * @version  0.3. Creating singleton DAOManager to perform DAO operations.
 * @author fevvelasquez@gmail.com
 */
class OfficeDAO implements OfficeDAOI {
	private static final Logger logger = Logger.getLogger(OfficeDAO.class.getName());

	private static final String SQL_INSERT = "INSERT INTO offices(office_id, address, city, state) VALUES(?,?,?,?)";
	private static final String SQL_UPDATE = "UPDATE offices SET address=?, city=?, state=? WHERE office_id=?";
	private static final String SQL_DELETE = "DELETE FROM offices WHERE office_id=?";
	private static final String SQL_SELECT_ALL = "SELECT * FROM offices";
	private static final String SQL_SELECT_ONE = "SELECT * FROM offices WHERE office_id=?";

	private final Connection connection;

	/**
	 * Create {@link Office} instance.
	 * 
	 * @param Database connection.
	 */
	public OfficeDAO(Connection connection) {
		this.connection = connection;
	}

	/**
	 * Insert {@link Office} into {@code offices} table.
	 * 
	 * @param office object to insert.
	 */
	@Override
	public void insert(Office office) throws DAOException {
		try (final PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT);) {
			preparedStatement.setInt(1, office.getOfficeId());
			preparedStatement.setString(2, office.getAddress());
			preparedStatement.setString(3, office.getCity());
			preparedStatement.setString(4, office.getState());
			preparedStatement.executeUpdate();
			logger.info("Office inserted with office_id:" + office.getOfficeId() + ".");
		} catch (SQLException e) {
			throw new DAOException("DAO: Error inserting row. " + e.getMessage());
		}
	}

	/**
	 * Update {@link Office} into {@code offices} table.
	 * 
	 * @param office object to update.
	 */
	@Override
	public void update(Office office) throws DAOException {
		try (final PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE);) {
			preparedStatement.setString(1, office.getAddress());
			preparedStatement.setString(2, office.getCity());
			preparedStatement.setString(3, office.getState());
			preparedStatement.setInt(4, office.getOfficeId());
			preparedStatement.executeUpdate();
			logger.info("Office updated with office_id:" + office.getOfficeId() + ".");
		} catch (SQLException e) {
			throw new DAOException("DAO: Error updating row. " + e.getMessage());
		}
	}

	/**
	 * Delete {@link Office} from {@code offices} table.
	 * 
	 * @param officeId of the office to delete.
	 */
	@Override
	public void delete(Integer officeId) throws DAOException {
		try (final PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE);) {
			preparedStatement.setInt(1, officeId);
			preparedStatement.executeUpdate();
			logger.info("Office deleted with office_id:" + officeId + ".");
		} catch (SQLException e) {
			throw new DAOException("DAO: Error deleting row. " + e.getMessage());
		}
	}

	/**
	 * Get a list of all the {@link Office} objects in {@code offices} table.
	 * 
	 * @return A list of all the offices.
	 */
	@Override
	public List<Office> getAll() throws DAOException {
		List<Office> list = null;
		try (final PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL);
				final ResultSet resultSet = preparedStatement.executeQuery();) {
			list = new ArrayList<Office>();
			while (resultSet.next()) {
				Office office = extract(resultSet);
				list.add(office);
			}
		} catch (SQLException e) {
			throw new DAOException("DAO: Error getting rows. " + e.getMessage());
		}
		return list;
	}

	/**
	 * Get the {@link Office} with the given office_id in {@code offices} table.
	 * 
	 * @return A office that corresponds to the given key. Null if there is no
	 *         match.
	 */
	@Override
	public Office getOne(Integer officeId) throws DAOException {
		Office office = null;
		try (final PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ONE);) {
			preparedStatement.setInt(1, officeId);
			final ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				office = extract(resultSet);
			}
			preparedStatement.close();
//			logger.info("Get Office with office_id:" + officeId + ".");
		} catch (SQLException e) {
			throw new DAOException("DAO: Error getting row. " + e.getMessage());
		}
		return office;
	}

	private Office extract(ResultSet resultSet) throws SQLException {
		Integer officeId = Integer.valueOf(resultSet.getInt("office_id"));
		String address = resultSet.getString("address");
		String city = resultSet.getString("city");
		String state = resultSet.getString("state");

		return new Office(officeId, address, city, state);
	}

}
