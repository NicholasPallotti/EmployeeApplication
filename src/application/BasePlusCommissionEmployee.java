package application;

import java.security.InvalidParameterException;


@SuppressWarnings("serial")
public class BasePlusCommissionEmployee extends CommissionedEmployee { 

	//all other variables are handled in CommissionedEmployee
	private double baseSalary;
	
	BasePlusCommissionEmployee(String firstName, String lastName, String SSI, int month, int day, int year, double grossSales, double commissionRate, double baseSalary) { //constructor for the base plus Commission employee 
		super(firstName, lastName, SSI, month, day, year, grossSales, commissionRate);
		setBaseSalary(baseSalary);
	}

	public BasePlusCommissionEmployee() {
	}

	/******************/
	/*****setters******/
	/******************/
	
	public void setBaseSalary(double baseSalary) { //setter for the employees base salary
		if(baseSalary < 0) {
			throw new InvalidParameterException("base salary cannot be negative");
		}
		this.baseSalary = baseSalary;
	}
	
	public double getBaseSalary() { //getter for the employees base salary
		return baseSalary;
	}
	
	/******************/
	/*****getters******/
	/******************/
	
	@Override
	public double getEarnings() { 
		return (super.getCommissionRate() * super.getGrossSales()) + getBaseSalary();
	}
	
	@Override 
	public double getEarnings(double rate) { //get earnings emthod for if the employee earned overtime
		setBaseSalary(getBaseSalary() + (getBaseSalary() * rate));
		return getEarnings();
	}
	
	@Override
	public String toString() { //returns the employees info, including their gross sales, commission rate and base salary
		String info = "";
		info = "\nEmployee type: " + "BasePlusCommission" + "\nFirst Name: " + getFirstName() + "\nLast Name: " + getLastName() + "\nSSI: " + getSSI() + "\nbirthday: " + getBirthday()
		+ "\nGross Sales: " + getGrossSales() + "\nCommission Rate: " + getCommissionRate() + "\nBase Salary: " + getBaseSalary();
		return info;
	}

}	