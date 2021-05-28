/**
 * ----------------------------
 * Java JDBC API basis, coding exercises.
 * ----------------------------
 * 
 */
package me.fevvelasquez.jdbc.api.dao.model;

/**
 * The model class that represents the {@code offices} table.
 * 
 * @version 0.2.0. Creating Data Access Objects to implement CRUD to the
 *          database tables.
 * @author fevvelasquez@gmail.com
 */
public class Office {
	private final Integer officeId;
	private final String address;
	private final String city;
	private final String state;

	/**
	 * @param productId from {@code offices} table.
	 * @param address   from {@code offices} table.
	 * @param city      from {@code offices} table.
	 * @param state     from {@code offices} table.
	 */
	public Office(Integer officeId, String address, String city, String state) {
		this.officeId = officeId;
		this.address = address;
		this.city = city;
		this.state = state;
	}

	/**
	 * Office identifier.
	 * 
	 * @return the officeId
	 */
	public Integer getOfficeId() {
		return officeId;
	}

	/**
	 * Office address.
	 * 
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * City name.
	 * 
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * State id, 2 letters.
	 * 
	 * @return the state id
	 */
	public String getState() {
		return state;
	}

	@Override
	public String toString() {
		return "Office [officeId=" + officeId + ", address=" + address + ", city=" + city + ", state=" + state + "]";
	}

}
