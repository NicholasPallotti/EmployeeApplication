package application;

import java.security.InvalidParameterException;


@SuppressWarnings("serial")
public class HourlyEmployee extends Employee{ 
	
	
	private double wage; //how much the employee makes per hour
	private double hours; //how many hours the employee has worked
	
	HourlyEmployee(String firstName, String lastName, String SSI, int month, int day, int year, double hours, double wage){ //constructor for a hourly employee
		setFirstName(firstName);
		setLastName(lastName);
		setSSI(SSI);
		setBirthday(month, day, year);
		setHours(hours);
		setWage(wage);
	}
	
	public HourlyEmployee() {
	}
	
	@Override //returns the employees info
	public String toString() {
		String info = "";
		info = "\nEmployee type: Hourly" + "\nFirst Name: " + getFirstName() + "\nLast Name: " + getLastName() + "\nSSI: " + getSSI() + "\nbirthday: " + getBirthday()
		+ "\nHours: " + getHours() + "\nWage: " + getWage();
		return info;
	}
	
	public void setWage(double wage) { 
		if(wage <= 0) {
				throw new InvalidParameterException("wage must be greater than 0");
		}
		
		this.wage = wage;
	}
	
	public void setHours(double hours) { 
		if(hours < 0) {
			throw new InvalidParameterException("hours cannot be negative");
		}
		this.hours = hours;
	}
	
	@Override
	public void setEarnings(String earnings) {		
	}

	/******************/
	/*****getters******/
	/******************/
	
	public double getWage() {
		return wage;
	}
	
	public double getHours() { 
		return hours;
	}
	
	@Override
	public double getEarnings() {
		return hours * wage;
	}
	
	@Override
	public double getEarnings(double bonus) {
		return getEarnings() + bonus;
	}


	
}
