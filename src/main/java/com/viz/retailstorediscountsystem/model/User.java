package com.viz.retailstorediscountsystem.model;
/**
 * This is User class a model for Users
 * @author Vijendra-Singh
 *
 */
public class User {
	
	private int id;
	private String name;
	private String doa;
	private boolean employee;
	private boolean affiliated;
	
	public User() {
		
	}
	/**
	 * User class constructor
	 * @param id as int 
	 * @param name as String
	 * @param doa as String in format "YYYY-MM-DD"
	 * @param employee as boolean
	 * @param affiliated as boolean
	 */
	public User(int id, String name, String doa, boolean employee, boolean affiliated) {
		this.id = id;
		this.name = name;
		this.doa = doa;
		this.employee = employee;
		this.affiliated = affiliated;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDoa() {
		return doa;
	}
	public void setDoa(String doa) {
		this.doa = doa;
	}
	public boolean isEmployee() {
		return employee;
	}
	public void setEmployee(boolean employee) {
		this.employee = employee;
	}
	public boolean isAffiliated() {
		return affiliated;
	}
	public void setAffiliated(boolean affiliated) {
		this.affiliated = affiliated;
	}
	

}
