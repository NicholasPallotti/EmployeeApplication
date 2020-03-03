package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CSVFile{
		
		//creates a csv file if one doesn't exist, otherwise references the existing file
		private File file = new File("Employees.csv");
		
		CSVFile(){		
		}
		
		//reads the csv file and returns the objects stored as a list
		public ObservableList<Employee> readFile() throws IOException {
			
			//create a new  FileReader and BufferedReader for reading the csv file
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			//string current line holds the current lines(which holds the employee's) info
			String currentLine;
			//boolean firstLine is to prevent reading the header in
			boolean firstLine = true;
			
			//creates an array list to store the read employees
			ObservableList<Employee> employeesFromFile = FXCollections.observableArrayList();
			
			//while there are still employees that need to be put in employeesFromFile
			while((currentLine = bufferedReader.readLine()) != null) {
				
				//if the current line isn't the header
				if (firstLine == false) {
					
					//split the line so that each piece of info is an element in employeeInfo
					String[] employeeInfo = currentLine.split(",");
					
					//finds the correct employee type and creates the corresponding employee with
					//the info in employeeInfo, then adds that employee to employeesFromFile
					if(employeeInfo[0].equals("Salaried")) {
						
						SalariedEmployee newEmployee = new SalariedEmployee();
						
						newEmployee.setFirstName(employeeInfo[1]);
						newEmployee.setLastName(employeeInfo[2]);
						newEmployee.setSSI(employeeInfo[3]);
						
						String birthday = employeeInfo[4];
						String[] birthdayInParts = birthday.split("/");
						
			    		int month = Integer.valueOf(birthdayInParts[0]);
			    		int day = Integer.valueOf(birthdayInParts[1]);
			    		int year = Integer.valueOf(birthdayInParts[2]); 
						
						newEmployee.setBirthday(month, day, year);
						
		    			 newEmployee.setWeeklySalary(Double.valueOf(employeeInfo[5]));
		    			 
		    			 employeesFromFile.add(newEmployee);
		    			
		    		}
		    		
		    		else if(employeeInfo[0].equals("Hourly")) {
		    			
		    			HourlyEmployee newEmployee = new HourlyEmployee();
						
						newEmployee.setFirstName(employeeInfo[1]);
						newEmployee.setLastName(employeeInfo[2]);
						newEmployee.setSSI(employeeInfo[3]);
						
						String birthday = employeeInfo[4];
						String[] birthdayInParts = birthday.split("/");
						
			    		int month = Integer.valueOf(birthdayInParts[0]);
			    		int day = Integer.valueOf(birthdayInParts[1]);
			    		int year = Integer.valueOf(birthdayInParts[2]); 
						
						newEmployee.setBirthday(month, day, year);;
		    			
		    			newEmployee.setWage(Double.valueOf(employeeInfo[6]));
		    			newEmployee.setHours(Double.valueOf(employeeInfo[7]));
		    			
		    			employeesFromFile.add(newEmployee);
		    		}
		    		
		    		else if(employeeInfo[0].equals("Commissioned")) {
		    			
		    			CommissionedEmployee newEmployee = new CommissionedEmployee();
						
						newEmployee.setFirstName(employeeInfo[1]);
						newEmployee.setLastName(employeeInfo[2]);
						newEmployee.setSSI(employeeInfo[3]);
						
						String birthday = employeeInfo[4];
						String[] birthdayInParts = birthday.split("/");
						
			    		int month = Integer.valueOf(birthdayInParts[0]);
			    		int day = Integer.valueOf(birthdayInParts[1]);
			    		int year = Integer.valueOf(birthdayInParts[2]); 
						
						newEmployee.setBirthday(month, day, year);
						
		    			newEmployee.setCommissionRate(Double.valueOf(employeeInfo[8]));
		    			newEmployee.setGrossSales(Double.valueOf(employeeInfo[9]));
		    			
		    			employeesFromFile.add(newEmployee);
		    		}
		    		
		    		else {
		    			
		    			BasePlusCommissionEmployee newEmployee = new BasePlusCommissionEmployee();
						
						newEmployee.setFirstName(employeeInfo[1]);
						newEmployee.setLastName(employeeInfo[2]);
						newEmployee.setSSI(employeeInfo[3]);
						
						String birthday = employeeInfo[4];
						String[] birthdayInParts = birthday.split("/");
						
			    		int month = Integer.valueOf(birthdayInParts[0]);
			    		int day = Integer.valueOf(birthdayInParts[1]);
			    		int year = Integer.valueOf(birthdayInParts[2]); 
						
						newEmployee.setBirthday(month, day, year);
		    			
		    			newEmployee.setCommissionRate(Double.valueOf(employeeInfo[8]));
		    			newEmployee.setGrossSales(Double.valueOf(employeeInfo[9]));
		    			newEmployee.setBaseSalary(Double.valueOf(employeeInfo[10]));
		    			
		    			employeesFromFile.add(newEmployee);
		    		}
							
				}
				
				//lets the loop know that the current line is not the header
				firstLine = false;
				
			}
			
			bufferedReader.close();
			
			return employeesFromFile;
		}
		
		//Receives a list of Employees and saves it to the csv file
		public void saveFile(ObservableList<Employee> employees) {
			
			try {
				
				//create a new file writer for the csv file
				FileWriter writer = new FileWriter(file);
				//create the header for the csv file
				String header = "Employee type, First name, Last Name, SSI, Birthday, Salary, Wage, Hours, Commission rate, Sales, Base Salary";
				
				//write the header
				writer.write(header);
				writer.write(System.lineSeparator());
				
				//for all the employees in the table
				for(int i = 0; i < employees.size(); i++) {
					
					//will check the employee type, then write the correct info for each employee type, then start a new line
					if(employees.get(i) instanceof SalariedEmployee) {
						
						SalariedEmployee employee = (SalariedEmployee) employees.get(i);
						
						String info = "Salaried," + employee.getFirstName() + "," + employee.getLastName() + "," + employee.getSSI() + "," 
						+ employee.getBirthday() + "," + employee.getWeeklySalary() + ",null,null,null,null,null" ;
						
						writer.write(info);
						writer.write(System.lineSeparator());
						
					}
					
					else if(employees.get(i) instanceof HourlyEmployee) {
						
						HourlyEmployee employee = (HourlyEmployee) employees.get(i);
						
						String info = "Hourly," + employee.getFirstName() + "," + employee.getLastName() + "," + employee.getSSI() + "," 
						+ employee.getBirthday() + ",null," + employee.getWage() + "," + employee.getHours() + ",null,null,null";
						
						writer.write(info);
						writer.write(System.lineSeparator());
					}

					else if(employees.get(i) instanceof BasePlusCommissionEmployee) {
						BasePlusCommissionEmployee employee = (BasePlusCommissionEmployee) employees.get(i);
						
						String info = "BasePlusCommission," + employee.getFirstName() + "," + employee.getLastName() + "," + employee.getSSI() + "," 
						+ employee.getBirthday() + ",null,null,null," +employee.getCommissionRate() + "," + employee.getGrossSales() + "," 
						+ employee.getBaseSalary();
						
						writer.write(info);
						writer.write(System.lineSeparator());
					}

					else {
						CommissionedEmployee employee = (CommissionedEmployee) employees.get(i);
						
						String info = "Commissioned," + employee.getFirstName() + "," + employee.getLastName() + "," + employee.getSSI() + "," 
						+ employee.getBirthday() + ",null,null,null," +employee.getCommissionRate() + "," + employee.getGrossSales() + "," 
						+ "null";
						
						writer.write(info);
						writer.write(System.lineSeparator());
					}
					
				}
				
				writer.close();
				
			}catch(IOException e) {
				
				e.printStackTrace();
			}	
		}
		
}
