package application;

import java.io.IOException;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class TableController {

	
	//declare the different tables in the main window
	@FXML
	private TableView<Employee> tableView;
	    
	@FXML
	private RadioButton CSVRadioButton;

	@FXML
	private RadioButton BinaryRadioButton;

	@FXML
	private RadioButton XMLRadioButton;

	@FXML
	private RadioButton JSONRadioButton;
	
	@FXML
	private ToggleGroup employeeType;
	
	@FXML
    private TableColumn<Employee, String> SSNColumn;

    @FXML
    private TableColumn<Employee, String> firstNameColumn;

    @FXML
    private TableColumn<Employee, String> lastNameColumn;

    @FXML
    private TableColumn<Employee, String> birthdayColumn;

    @FXML
    private TableColumn<Employee, String> ageColumn;

    @FXML
    private TableColumn<Employee, String> earningsColumn;
    
    EmployeeListModel employeeList = new EmployeeListModel();
    
    
    //Initialize the columns when the form is loaded
	@FXML
	private void initialize() {
		
		SSNColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("SSI"));
		firstNameColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("FirstName"));
		lastNameColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("LastName"));
		birthdayColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("birthday"));
		ageColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("Age"));
		earningsColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("Earnings"));
	
		tableView.setItems(getEmployees());
		
		CSVRadioButton.setSelected(true);
	}
	
	//when the read file button is clicked
    @FXML
    void readFileClicked(ActionEvent event) throws IOException, ClassNotFoundException {
    	
    	
    	if(CSVRadioButton.isSelected() == true) {
    		tableView.setItems(employeeList.readFileCSV());
    	}
    	if(BinaryRadioButton.isSelected() == true) {
    		tableView.setItems(employeeList.readFileBinary());
    	}
    	if(XMLRadioButton.isSelected() == true) {
    		tableView.setItems(employeeList.readFileXML());
    	}
    	if(JSONRadioButton.isSelected() == true) {
    		tableView.setItems(employeeList.readFileJSON());
    	}
    
    }
    
    //when the save button is clicked
    @FXML
    void saveFileClicked(ActionEvent event) throws IOException {
    	
    	if(CSVRadioButton.isSelected() == true) {
    		employeeList.saveFileCSV(tableView.getItems());
    	}
    	if(BinaryRadioButton.isSelected() == true) {
    		employeeList.saveFileBinary(tableView.getItems());
    	}
    	if(XMLRadioButton.isSelected() == true) {
    		employeeList.saveFileXML(tableView.getItems());
    	}
    	if(JSONRadioButton.isSelected() == true) {
    		employeeList.saveFileJSON(tableView.getItems());
    	}
    	
    	
    }

	//when the add button is clicked allow the user to add a new employee
    @FXML
    void addClicked(ActionEvent event) throws IOException {
    	//load the UI of the new screen from the .fxml file		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("editEmployee.fxml"));
		
		Parent root = loader.load();		
		
		//create a new Scene using the object returned by loading the fxml file
		Scene editRestaurantScene = new Scene(root, 650, 400);		
		
		Stage mainWindow = (Stage) ((Node)event.getSource()).getScene().getWindow();
		
		mainWindow.setScene(editRestaurantScene);
		
		//get the controller associated with the FXML file
		EditEmployeeController controller = loader.<EditEmployeeController>getController();
		
		//pass the list of items in the table and null for the value of employee to editEmployee
		controller.initializeData(null, tableView.getItems());
		
		mainWindow.show();
    }

    //delete the currently selected row
    @FXML
    void deleteClicked(ActionEvent event) {
    	Employee employee = tableView.getSelectionModel().getSelectedItem();
		
		tableView.getItems().remove(employee);
    }

    //edit the currently selected row
    @FXML
    void editClicked(ActionEvent event) throws IOException {
    	
		//load the EditEmployee fxml	
		FXMLLoader loader = new FXMLLoader(getClass().getResource("editEmployee.fxml"));
		
		Parent root = loader.load();		
		
		//create a new Scene 
		Scene editEmployeeScene = new Scene(root, 650, 400);		
		
	
		//from the event, which is a parameter, cast to a Node, then get the event source, then get the scene that caused
		//the event, then get the window/stage that contains the scene
		Stage mainWindow = (Stage) ((Node)event.getSource()).getScene().getWindow();
		
		mainWindow.setScene(editEmployeeScene);
		
		//get the currently selected employee

		Employee employee = tableView.getSelectionModel().getSelectedItem();
		
		//get the controller associated with the FXML file
		EditEmployeeController controller = loader.<EditEmployeeController>getController();
		
		//pass the employee to edit and the collection of employees as parameters
		controller.initializeData(employee, tableView.getItems());
		
		mainWindow.show();
    }
    
    //display the employees in the main table
	public void setEmployees(ObservableList<Employee> employees) {
		tableView.getItems().clear();
		tableView.setItems(employees);
	}
	
	//returns a list of all the employees
	private ObservableList<Employee> getEmployees(){
		
		ObservableList<Employee> employees = FXCollections.observableArrayList();
		
		SalariedEmployee salariedEmployee = new SalariedEmployee("John", "Smith", "111-11-1111", 6, 15, 1944, 800.00);
		HourlyEmployee hourlyEmployee = new HourlyEmployee("Karen", "Price", "222-22-2222", 12, 29, 1960, 10, 67);
	    CommissionedEmployee commissionedEmployee = new CommissionedEmployee("Sue", "Jones", "333-33-3333", 9, 8, 1954, 10000, .06); 
	    BasePlusCommissionEmployee basePlusCommissionEmployee = new BasePlusCommissionEmployee("Bob", "Lewis", "444-44-4444", 3, 2, 1965, 5000, .04, 30);
	
		employees.add(salariedEmployee);
	
		employees.add(hourlyEmployee);
		
		employees.add(commissionedEmployee);
		
		employees.add(basePlusCommissionEmployee);
		
		return employees;
	}

}

