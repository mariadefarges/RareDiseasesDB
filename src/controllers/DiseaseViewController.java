package controllers;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.sql.SQLException;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import jdbc.JDBCDiseaseManager;
import jdbc.JDBCManager;
import jdbc.JDBCResourceManager;
import jdbc.JDBCResourceTypeManager;
import jdbc.JDBCURLManager;
import pojos.Disease;
import pojos.Resource;

public class DiseaseViewController {
	
	public static JDBCManager manager = new JDBCManager();
	public static JDBCDiseaseManager diseaseManager = new JDBCDiseaseManager(manager);
	public static JDBCResourceTypeManager resourcetypeManager = new JDBCResourceTypeManager(manager);
	public static JDBCURLManager urlManager = new JDBCURLManager(manager);
	public static JDBCResourceManager resourceManager = new JDBCResourceManager(manager, diseaseManager,
			resourcetypeManager, urlManager);

	Disease disease;
	private Parent root;
	private Stage stage;
	private Scene scene;

	@FXML
	Button returnbutton;

	@FXML
	Label diseaseName, idNumber, prevalenceText, affectedSystemText, treatmentText, diagnosisText, descriptionText;

	@FXML
	TableView<Resource> ResourcesRelatedTable;

	@FXML
	TableColumn<Resource, String> tableName = new TableColumn<>("tableName");

	public static DiseasesResultsController diseaseresultscontroller;

	public void DiseaseView(Disease diseaseselected) {
		diseaseManager.setRsmanager(resourceManager);
		manager.getConnection();
		try {
			this.disease = diseaseManager.searchDiseasebyId(diseaseselected.getIdDisease());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		diseaseName.setText(disease.getDiseaseName());
		idNumber.setText(disease.getIdDisease().toString());
		prevalenceText.setText(disease.getPrevalence());
		affectedSystemText.setText(disease.getAffectedSystem());
		diagnosisText.setText(disease.getDiagnosis());
		treatmentText.setText(disease.getTreatment());
		descriptionText.setText(disease.getDescription());
		descriptionText.setWrapText(true);

		if (!disease.getResources().isEmpty()) {
			ResourcesRelatedTable.getItems().clear();
			ResourcesRelatedTable.getColumns().clear();
			ResourcesRelatedTable.getItems().addAll(disease.getResources());
			tableName.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));
			ResourcesRelatedTable.getColumns().addAll(tableName);
		}
	}
	
	@FXML
	private void selectedResource(MouseEvent Mevent) throws IOException {
		Resource resource = ResourcesRelatedTable.getSelectionModel().getSelectedItem();
		if (resource == null) {
			//Error
			System.out.println("Nothing selected");
		}
		else {		
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmlfiles/resourceview.fxml"));
			root = loader.load();
			ResourceViewController rsv = loader.getController();	
			rsv.ResourceView(resource);
			stage = (Stage) ((Node) Mevent.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}		
	}

	@FXML
	private void returntoFirstScreen(ActionEvent e) throws IOException, SQLException, NotBoundException {
		root = FXMLLoader.load(getClass().getResource("/fxmlfiles/firstscreen.fxml"));
		stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}

}
