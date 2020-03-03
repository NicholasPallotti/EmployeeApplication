package application;

import java.io.Serializable;
import java.security.InvalidParameterException;
import java.text.ParseException;
import java.text.SimpleDateFormat;  
import java.util.Date;  

public abstract class Employee implements Comparable<Employee>, Serializable {
	
		//variable that allows employee objects to be read into and out of files	
		private static final long serialVersionUID = 1L; 
		
		//class variables
		private String firstName;
		private String lastName;
		private String SSI;
		public String birthday;
	
		public void checkNames() {//method that checks that the Employee'sfirst and last name are not the same
			if(firstName.compareTo(lastName) == 0) {
				throw new InvalidParameterException("first and last names cant match");
			}
		}
		
		public String getAge() throws ParseException {//calculates the Employees age
			
			//convert the string birthday to a date and make a new date for today
			Date birthdayAsDate = new SimpleDateFormat("dd/MM/yyyy").parse(birthday); 
			Date today = new Date();
			
			//calculate the time in milliseconds between the two dates, the convert from milliseconds to years
			long ageInMili = today.getTime() - birthdayAsDate.getTime();
			
			//convert age from milliseconds to days then days to years because i think the number was to big and it wasn't converting correctly
			long milisInMinutes = (1000 * 60 * 60);
			long ageInDays = ageInMili/milisInMinutes;
			long ageInYears = ageInDays/(24 * 365);

			//convert value to a string then return it
			String age = String.format("%d", ageInYears);
			return age;
		}
		
		@Override
		public String toString() { //prints the employees first and last name, SSI, and birthday
			String info = firstName + " " + lastName + " " + birthday + " " + SSI;
			return info;
		}
		
		@Override
		public int compareTo(Employee secondEmployee) {
			return this.lastName.compareTo(secondEmployee.getLastName());
		}

		/******************/
		/*****setters******/
		/******************/
		
		public void setFirstName(String firstName) { 
			this.firstName = firstName;
		}
		
		public void setLastName(String lastName) { 
			this.lastName = lastName;
			checkNames();
		}
		
		public void setSSI(String SSI) { 
			this.SSI = SSI;
		}
		
		public void setBirthday(int month, int day, int year) { 
			
			//convert the employees birth month, day and year into strings
			String monthAsString = String.valueOf(month);
			String dayAsString = String.valueOf(day);
			String yearAsString = String.valueOf(year);
			
			//combine the month day and year strings and make a string for the birthday in the dd/MM/yyyy format
			birthday = monthAsString + "/" + dayAsString + "/" + yearAsString;
		}
		
		public abstract void setEarnings(String earnings) ;
		
		/******************/
		/*****getters******/
		/******************/
		
		public String getFirstName() { 
			return firstName;
		}
		
		public String getLastName() { 
			return lastName;
		}
		
		public String getSSI() { 
			return SSI;
		}
		
		public String getBirthday() { 
			return birthday;
		}
		
		public abstract double getEarnings(); 
			
		public abstract double getEarnings(double bonus) ;


	
		
}
