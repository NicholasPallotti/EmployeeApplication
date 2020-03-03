package application;

import java.security.InvalidParameterException;


@SuppressWarnings("serial")
public class CommissionedEmployee extends Employee {
	
	//class variables
	private double grossSales;
	private double commissionRate;
	
	CommissionedEmployee(String firstName, String lastName, String SSI, int month, int day, int year, double grossSales, double commissionRate){ //constructor for hourly employee
		setFirstName(firstName);
		setLastName(lastName);
		setSSI(SSI);
		setBirthday(month, day, year);
		setGrossSales(grossSales);
		setCommissionRate(commissionRate);
	}

	public CommissionedEmployee() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public double getEarnings() { //returns how much the Employee has made by multiplying their gross sales by their commission rate
		return grossSales * commissionRate;
	}
	
	@Override
	public double getEarnings(double bonus) {
		return getEarnings() + bonus;
	}
	
	@Override
	public String toString() { //returns the employees info, including their gross sales and commission rate
		String info = "";
		info = "\nEmployee type: Commissioned" + "\nFirst Name: " + getFirstName() + "\nLast Name: " + getLastName() + "\nSSI: " + getSSI() + "\nbirthday: " + getBirthday()
		+ "\nGross Sales: " + getGrossSales() + "\nCommission Rate: " + getCommissionRate();
		return info;
	}
	
	public void setGrossSales(double grossSales) { //setter for the employees gross sales
		if(grossSales < 0) {
			throw new InvalidParameterException("gross sales cannot be negative");
		}
		this.grossSales = grossSales;
	}
	
	public void setCommissionRate(double commissionRate) { //setter for the employees commission rate
		if(commissionRate >= 1 || commissionRate <= 0) {
			throw new InvalidParameterException("commission rate must be greater than 0");
		}
		this.commissionRate = commissionRate;
	}
	
	public double getGrossSales() { //getter for the employees gross sales
		return grossSales;
	}
	
	public double getCommissionRate() { //getter for the employees commission rate
		return commissionRate;
	}

	@Override
	public void setEarnings(String earnings) {
		// TODO Auto-generated method stub
		
	}
	
	

}
