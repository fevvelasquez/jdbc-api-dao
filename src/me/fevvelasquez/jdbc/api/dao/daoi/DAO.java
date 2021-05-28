/**
 * ----------------------------
 * Java JDBC API basis, coding exercises.
 * ----------------------------
 * 
 */
package me.fevvelasquez.jdbc.api.dao.daoi;

import java.util.List;

import me.fevvelasquez.jdbc.api.dao.daoe.DAOException;

/**
 * Generic DAO interface.
 * 
 * @version 0.1.1. Creating DAO classes.
 * @author fevvelasquez@gmail.com
 *
 * @param <T> The table object model type.
 * @param <K> The primary Key type.
 */
public interface DAO<T, K> {
	
	/**
	 * Insert row into table.
	 * 
	 * @param t object to insert.
	 */
	void insert(T t) throws DAOException;

	/**
	 * Update row into table.
	 * 
	 * @param t object to update.
	 */
	void update(T t) throws DAOException;

	/**
	 * Delete row from table.
	 * 
	 * @param Primary key of the row to delete.
	 */
	void delete(K key) throws DAOException;

	/**
	 * Select All.
	 * 
	 * @return A list of all T's in the table.
	 */
	List<T> getAll() throws DAOException;

	/**
	 * Select T where primary key equals the given param.
	 * 
	 * @param key primary key.
	 * @return T which corresponds to the give key, if exists. Otherwise null.
	 */
	T getOne(K key) throws DAOException;

}
