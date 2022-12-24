package controllers;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import jdbc.JDBCDiseaseManager;
import jdbc.JDBCManager;
import jdbc.JDBCResourceManager;
import jdbc.JDBCResourceTypeManager;
import jdbc.JDBCURLManager;
import pojos.Disease;
import pojos.Resource;

public class FirstScreenController {

	static Connection c;
	public static JDBCManager manager = new JDBCManager();
	public static JDBCDiseaseManager diseaseManager = new JDBCDiseaseManager(manager);
	public static JDBCResourceTypeManager resourcetypeManager = new JDBCResourceTypeManager(manager);
	public static JDBCURLManager urlManager = new JDBCURLManager(manager);
	public static JDBCResourceManager resourceManager = new JDBCResourceManager(manager, diseaseManager,
			resourcetypeManager, urlManager);

	private Parent root;
	private Stage stage;
	private Scene scene;

	@FXML
	TextField searchDiseasefield, searchResourcefield, searchLocationfield;

	String prevalence = null;
	String language = null;
	String type = null;
	String price = null;
	String finality = null;
	String access = null;
	String location = null;

	// FILTERS
	@FXML
	private void hightolow(ActionEvent e) {
		prevalence = "high-low";
	}

	@FXML
	private void lowtohigh(ActionEvent e) {
		prevalence = "low-high";
	}
	
	@FXML
	private void spanish(ActionEvent e) {
		language = "Spanish";
	}

	@FXML
	private void english(ActionEvent e) {
		language = "English";
	}
	
	@FXML
	private void spanish_english(ActionEvent e) {
		language = "Spanish/English";
	}
	
	@FXML
	private void medical_center(ActionEvent e) {
		type = "Medical Center";
	}
	
	@FXML
	private void website(ActionEvent e) {
		type = "Website";
	}
	
	@FXML
	private void organization(ActionEvent e) {
		type = "Organization";
	}
	
	@FXML
	private void editorial(ActionEvent e) {
		type = "Editorial";
	}
	
	@FXML
	private void medical_magazine(ActionEvent e) {
		type = "Medical Magazine";
	}
	
	@FXML
	private void charity(ActionEvent e) {
		type = "Charity";
	}
	
	@FXML
	private void app(ActionEvent e) {
		type = "App";
	}
	
	@FXML
	private void university(ActionEvent e) {
		type = "University";
	}
	
	@FXML
	private void laboratory(ActionEvent e) {
		type = "Laboratory";
	}
	
	@FXML
	private void databases(ActionEvent e) {
		type = "Databases";
	}
	
	@FXML
	private void encyclopedia(ActionEvent e) {
		type = "Encyclopedia";
	}
	
	@FXML
	private void official_school(ActionEvent e) {
		type = "Official School";
	}
	
	@FXML
	private void company(ActionEvent e) {
		type = "Company";
	}
	
	@FXML
	private void specialized_center(ActionEvent e) {
		type = "Specialized Center";
	}

	@FXML
	private void investigation_center(ActionEvent e) {
		type = "Investigation Center";
	}
	
	@FXML
	private void free(ActionEvent e) {
		price = "Free";
	}
	
	@FXML
	private void paid(ActionEvent e) {
		price = "Paid";
	}
	
	@FXML
	private void academic(ActionEvent e) {
		finality = "Academic";
	}
	
	@FXML
	private void informative(ActionEvent e) {
		finality = "Informative";
	}
	
	@FXML
	private void academic_informative(ActionEvent e) {
		finality = "Academic/Informative";
	}
	
	@FXML
	private void publicaccess(ActionEvent e) {
		access = "Public";
	}
	@FXML
	private void privateaccess (ActionEvent e) {
		finality = "Private";
	}
	
	@FXML
	private void getLocationText(KeyEvent ke) {
		if (ke.getCode().equals(KeyCode.ENTER)) {
			manager.getConnection();
			location = searchLocationfield.getText();
		}		
	}
	
	@FXML
	private void switchToResultsofDiseases(ActionEvent e) throws IOException, SQLException, NotBoundException {
		diseaseManager.setRsmanager(resourceManager);
		manager.getConnection();
		String diseasefield = searchDiseasefield.getText();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmlfiles/resultsofsearchdiseases.fxml"));
		root = loader.load();
		DiseasesResultsController rs = loader.getController();

		if (diseasefield != null) {
			String filters = diseaseManager.searchDiseasebyPrevalence(prevalence);
			List<Disease> diseases = diseaseManager.searchDiseasebyNameORAffectedSystem(diseasefield, filters);
			rs.getResultsofDiseases(diseases);
		}

		stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}

	@FXML
	private void switchToResultsofDisease(KeyEvent ke) throws IOException, SQLException, NotBoundException {
		if (ke.getCode().equals(KeyCode.ENTER)) {
			diseaseManager.setRsmanager(resourceManager);
			manager.getConnection();
			String diseasefield = searchDiseasefield.getText();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmlfiles/resultsofsearchdiseases.fxml"));
			root = loader.load();
			DiseasesResultsController rs = loader.getController();

			if (diseasefield != null) {
				String filters = diseaseManager.searchDiseasebyPrevalence(prevalence);
				List<Disease> diseases = diseaseManager.searchDiseasebyNameORAffectedSystem(diseasefield, filters);
				rs.getResultsofDiseases(diseases);
			}

			stage = (Stage) ((Node) ke.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}

	}

	@FXML
	private void switchToResultsofResources(ActionEvent e) throws IOException, SQLException, NotBoundException {
		diseaseManager.setRsmanager(resourceManager);
		manager.getConnection();
		String resourcefield = searchResourcefield.getText();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmlfiles/resultsofsearchresources.fxml"));
		root = loader.load();
		ResourcesResultsController ds = loader.getController();

		if (resourcefield != null) {
			String filters = resourceManager.filterResourcesfilterResources(language, type, price, finality, access,
					location);
			List<Resource> resources = resourceManager.searchResourcebyName(resourcefield, filters);
			ds.getResultsofResources(resources);
		}
		stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}

	@FXML
	private void switchToResultsofResource(KeyEvent ke) throws IOException, SQLException, NotBoundException {
		if (ke.getCode().equals(KeyCode.ENTER)) {
			diseaseManager.setRsmanager(resourceManager);
			manager.getConnection();
			String resourcefield = searchResourcefield.getText();

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmlfiles/resultsofsearchresources.fxml"));
			root = loader.load();
			ResourcesResultsController ds = loader.getController();

			if (resourcefield != null) {
				String filters = resourceManager.filterResourcesfilterResources(language, type, price, finality, access,
						location);
				List<Resource> resources = resourceManager.searchResourcebyName(resourcefield, filters);
				ds.getResultsofResources(resources);
			}
			stage = (Stage) ((Node) ke.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();

		}
	}

}
