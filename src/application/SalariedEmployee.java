package application;

import java.security.InvalidParameterException;


@SuppressWarnings("serial")
public class SalariedEmployee extends Employee {
	
	private double weeklySalary; //how much the employee makes per week
	
	SalariedEmployee(String firstName, String lastName, String SSI, int month, int day, int year, double weeklySalary){ //constructor for a salaried employee
		setFirstName(firstName);
		setLastName(lastName);
		setSSI(SSI);
		setBirthday(month, day, year);
		setWeeklySalary(weeklySalary);
	}
	
	public SalariedEmployee() {
	}
	
	@Override
	public String toString() { //returns the employees info
		String info = "";
		info = "\nEmployee type: Salaried" + "\nFirst Name: " + getFirstName() + " \nLast Name: " + getLastName() + " \nSSI: " + getSSI() + " \nbirthday: " + getBirthday() 
		+ " \nWeekly Salary: " + getWeeklySalary();
		
		return info;
	}
	
	/******************/
	/*****setters******/
	/******************/
	
	public void setWeeklySalary(double weeklySalary) { 
		if(weeklySalary <= 0) {
			throw new InvalidParameterException("wekkly salary must be greater than 0");
		}
		this.weeklySalary = weeklySalary;
	}

	@Override
	public void setEarnings(String earnings) {
		// TODO Auto-generated method stub
		
	}

	/******************/
	/*****getters******/
	/******************/
	
	public double getWeeklySalary() { 
		return weeklySalary;
	}

	@Override
	public double getEarnings() { 
		return weeklySalary;
	}
	
	@Override
	public double getEarnings(double bonus) { //returns the employees earnings plus a passed bonus
		return getEarnings() + bonus;
	}

}
