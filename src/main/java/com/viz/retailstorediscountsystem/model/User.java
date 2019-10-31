package com.viz.retailstorediscountsystem.model;

import java.io.Serializable;

/**
 * This is User class a model for Users
 * 
 * @author Vijendra-Singh
 *
 */
public class User implements Serializable{

	private static final long serialVersionUID = 2725633837014331956L;
	private int id;
	private String name;
	private String doa;
	private boolean employee;
	private boolean affiliated;

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
