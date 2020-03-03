package application;

import java.io.IOException;


import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class EditEmployeeController {
	
	//variables for the employee passed to the edit employee window and the list of employees
	private Employee employee;
	private ObservableList<Employee> employees;
	
	//declaration of fields
	@FXML
	GridPane gridPane;
	
	@FXML
	private ToggleGroup employeeType;
    
    @FXML
    private RadioButton hourlyRadioButton;

    @FXML
    private RadioButton salariedRadioButton;

    @FXML
    private RadioButton basePlusRadioButton;

    @FXML
    private RadioButton commissionRadioButton;

    @FXML
    private TextField SSITextBox;
    
    @FXML
    private TextField firstNameTextBox;

    @FXML
    private TextField lastNameTextBox;

    @FXML
    private TextField birthdateTextBox;

    @FXML
    private TextField hourlySalaryTextBox;
    
    @FXML
    private TextField commissionSalaryTextBox;

    @FXML
    private TextField weeklySalaryTextBox;

    @FXML
    private TextField baseSalaryTextBox;
    
    @FXML
    private TextField hoursWorkedTextBox;

    @FXML
    private TextField grossSalesTextBox;

    //calculates the new earnings When the calculate new earnings button is clicked
    @FXML
    void calculateNewEarnings(ActionEvent event) {
    		
    	//finds which employee type is selected, creates an temporary employee object of that subclass and displays 
    	//what the employees new earnings would be in an alert window until the user closes the window
		if(hourlyRadioButton.isSelected()) {
			
				Employee temp = new HourlyEmployee();
				
				((HourlyEmployee) temp).setWage(Double.valueOf(hourlySalaryTextBox.getText()));
				((HourlyEmployee) temp).setHours(Double.valueOf(hoursWorkedTextBox.getText()));
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("new Earnings");
				alert.setHeaderText("this employees new earnings will be");
				alert.setContentText("The selected item is " + temp.getEarnings());

				alert.showAndWait();
		}
		
		if(salariedRadioButton.isSelected()) {
				
				Employee temp = new SalariedEmployee();
				
				((SalariedEmployee) temp).setWeeklySalary(Double.valueOf(weeklySalaryTextBox.getText()));				
			
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("new Earnings");
				alert.setHeaderText("this employees new earnings will be");
				alert.setContentText("The selected item is " + temp.getEarnings());

				alert.showAndWait();
		}
		
		if(commissionRadioButton.isSelected()) {
				
				Employee temp = new CommissionedEmployee();
				
				((CommissionedEmployee) temp).setCommissionRate(Double.valueOf(commissionSalaryTextBox.getText()));
				((CommissionedEmployee) temp).setGrossSales(Double.valueOf(grossSalesTextBox.getText()));
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("new Earnings");
				alert.setHeaderText("this employees new earnings will be");
				alert.setContentText("The selected item is " + temp.getEarnings());

				alert.showAndWait();
		}
		
		if(basePlusRadioButton.isSelected()) {
				
				Employee temp = new BasePlusCommissionEmployee();
				
				((BasePlusCommissionEmployee) temp).setCommissionRate(Double.valueOf(commissionSalaryTextBox.getText()));
				((BasePlusCommissionEmployee) temp).setGrossSales(Double.valueOf(grossSalesTextBox.getText()));
				((BasePlusCommissionEmployee) temp).setBaseSalary(Double.valueOf(baseSalaryTextBox.getText()));
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("new Earnings");
				alert.setHeaderText("this employees new earnings will be");
				alert.setContentText("earnings: " + temp.getEarnings());

				alert.showAndWait();
			
		}

    }
    
    //puts the data of the current employee into the fields of the tableController window, or creates a new employee if add is being used
	public void initializeData(Employee currentEmployee, ObservableList<Employee> employees) {
		
		this.employees = employees;
		
		//if an employee is not passed the add button was clicked and a new employee will be made
		if(currentEmployee == null) {
			
			this.employee = new HourlyEmployee();
			this.employees.add(this.employee);
		}
		else {
			this.employee = currentEmployee;
		}
			
		//fill the text fields with the current employees info, if a new employee was made these fields will be blank
		SSITextBox.setText((String)employee.getSSI());
		firstNameTextBox.setText(employee.getFirstName());
		lastNameTextBox.setText(employee.getLastName());
		birthdateTextBox.setText(employee.getBirthday());
		
		
		//set the radio button that matches the employees type to be selected, then disables/enables the proper wage text boxes
		if(employee instanceof HourlyEmployee) {
			hourlyRadioButton.setSelected(true);
			
			hourlySalaryTextBox.setDisable(false);
			hoursWorkedTextBox.setDisable(false);
			commissionSalaryTextBox.setDisable(true);
			grossSalesTextBox.setDisable(true);
			weeklySalaryTextBox.setDisable(true);
			baseSalaryTextBox.setDisable(true);
			
			hourlySalaryTextBox.setText(String.valueOf(((HourlyEmployee) employee).getWage()));
			hoursWorkedTextBox.setText(String.valueOf(((HourlyEmployee) employee).getHours()));
		}
		
		else if(employee instanceof SalariedEmployee) {
			salariedRadioButton.setSelected(true);
			
			hourlySalaryTextBox.setDisable(true);
			hoursWorkedTextBox.setDisable(true);
			commissionSalaryTextBox.setDisable(true);
			grossSalesTextBox.setDisable(true);
			weeklySalaryTextBox.setDisable(false);
			baseSalaryTextBox.setDisable(true);
			
			weeklySalaryTextBox.setText(String.valueOf(((SalariedEmployee) employee).getWeeklySalary()));
		}
		
		else if(employee instanceof BasePlusCommissionEmployee) {
			basePlusRadioButton.setSelected(true);
			
			hourlySalaryTextBox.setDisable(true);
			hoursWorkedTextBox.setDisable(true);
			commissionSalaryTextBox.setDisable(false);
			grossSalesTextBox.setDisable(false);
			weeklySalaryTextBox.setDisable(true);
			baseSalaryTextBox.setDisable(false);
			
			baseSalaryTextBox.setText(String.valueOf(((BasePlusCommissionEmployee) employee).getBaseSalary()));
			grossSalesTextBox.setText(String.valueOf(((BasePlusCommissionEmployee) employee).getGrossSales()));
			commissionSalaryTextBox.setText(String.valueOf(((BasePlusCommissionEmployee) employee).getCommissionRate()));
		}
		
		else if(employee instanceof CommissionedEmployee) { 
			commissionRadioButton.setSelected(true);
			
			hourlySalaryTextBox.setDisable(true);
			hoursWorkedTextBox.setDisable(true);
			commissionSalaryTextBox.setDisable(false);
			grossSalesTextBox.setDisable(false);
			weeklySalaryTextBox.setDisable(true);
			baseSalaryTextBox.setDisable(true);
			
			commissionSalaryTextBox.setText(String.valueOf(((CommissionedEmployee) employee).getCommissionRate()));
			grossSalesTextBox.setText(String.valueOf(((CommissionedEmployee) employee).getGrossSales()));
		}
		
	}
	
	//when the button to save changes is clicked
	@FXML
	private void saveClicked(ActionEvent event) throws IOException {
		
		
		//if the employee type is being changed or a new employee is being added the correct type of employee object will be created and added to the employees list
		if(hourlyRadioButton.isSelected()) {
			
			if(!(employee instanceof HourlyEmployee)) {
				this.employees.remove(employees.size() - 1);
				
				this.employee = new HourlyEmployee();
				
				((HourlyEmployee) employee).setWage(Double.valueOf(hourlySalaryTextBox.getText()));
				
				this.employees.add(this.employee);
			}
		}
		
		if(salariedRadioButton.isSelected()) {
			
			if(!(employee instanceof SalariedEmployee)) {
				this.employees.remove(employees.size() - 1);
				
				this.employee = new SalariedEmployee();
				
				((SalariedEmployee) employee).setWeeklySalary(Double.valueOf(weeklySalaryTextBox.getText()));
				
				this.employees.add(this.employee);
			}
		}
		
		if(commissionRadioButton.isSelected()) {
			
			if(!(employee instanceof CommissionedEmployee)) {
				this.employees.remove(employees.size() - 1);
				
				this.employee = new CommissionedEmployee();
				
				((CommissionedEmployee) employee).setCommissionRate(Double.valueOf(commissionSalaryTextBox.getText()));
				
				this.employees.add(this.employee);
			}
		}
		
		if(basePlusRadioButton.isSelected()) {
			
			if(!(employee instanceof BasePlusCommissionEmployee)) {
				this.employees.remove(employees.size() - 1);
				
				this.employee = new BasePlusCommissionEmployee();
				
				((BasePlusCommissionEmployee) employee).setCommissionRate(Double.valueOf(commissionSalaryTextBox.getText()));
				
				((BasePlusCommissionEmployee) employee).setBaseSalary(Double.valueOf(baseSalaryTextBox.getText()));
				
				this.employees.add(this.employee);
			}
		}
		
		//gather up what the user entered
		employee.setSSI(SSITextBox.getText());
		employee.setFirstName(firstNameTextBox.getText());
		employee.setLastName(lastNameTextBox.getText());
		
		//chop the text in the birthday text box into a string array and convert the elements to integers to work with the employee classes setter for birthday
		String birthday = birthdateTextBox.getText();
		String[] birthdayAsArray = birthday.split("/");
		int month = Integer.valueOf(birthdayAsArray[0]);
		int day = Integer.valueOf(birthdayAsArray[1]);
		int year = Integer.valueOf(birthdayAsArray[2]);
		
		employee.setBirthday(month, day, year);
		
		//load the UI of the new screen from the .fxml file		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("tableView.fxml"));
		
		Parent root = loader.load();		
		
		//create a new Scene using the object returned by loading the fxml file
		Scene mainScene = new Scene(root, 610, 400);		
		Stage mainWindow = (Stage) ((Node)event.getSource()).getScene().getWindow();
		
		mainWindow.setScene(mainScene);
		
		//get the controller associated with the FXML file
		TableController controller = loader.<TableController>getController();
		
		//pass the list of employees back to the list form
		controller.setEmployees(employees);
		
		mainWindow.show();
	}
	
	//methods to disable/enable the correct fields when a different employee type is selected using the radio buttons

    @FXML
    void basePlusSelected(ActionEvent event) {
    	hourlySalaryTextBox.setDisable(true);
		hoursWorkedTextBox.setDisable(true);
		commissionSalaryTextBox.setDisable(false);
		grossSalesTextBox.setDisable(false);
		weeklySalaryTextBox.setDisable(true);
		baseSalaryTextBox.setDisable(false);
    }
    
    @FXML
    void commissionSelected(ActionEvent event) {
    	hourlySalaryTextBox.setDisable(true);
		hoursWorkedTextBox.setDisable(true);
		commissionSalaryTextBox.setDisable(false);
		grossSalesTextBox.setDisable(false);
		weeklySalaryTextBox.setDisable(true);
		baseSalaryTextBox.setDisable(true);
    }

    @FXML
    void hourlySelected(ActionEvent event) {
    	hourlySalaryTextBox.setDisable(false);
		hoursWorkedTextBox.setDisable(false);
		commissionSalaryTextBox.setDisable(true);
		grossSalesTextBox.setDisable(true);
		weeklySalaryTextBox.setDisable(true);
		baseSalaryTextBox.setDisable(true);
    }

    @FXML
    void salariedSelected(ActionEvent event) {
    	hourlySalaryTextBox.setDisable(true);
		hoursWorkedTextBox.setDisable(true);
		commissionSalaryTextBox.setDisable(true);
		grossSalesTextBox.setDisable(true);
		weeklySalaryTextBox.setDisable(false);
		baseSalaryTextBox.setDisable(true);
    }
}
