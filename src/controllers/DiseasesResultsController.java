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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pojos.Disease;

public class DiseasesResultsController {

	public static DiseasesResultsController rs;
	private Parent root;
	private Stage stage;
	private Scene scene;

	@FXML
	TableView<Disease> ResultsTableView;

	@FXML
	TableColumn<Disease, String> tableID = new TableColumn<>("tableID");

	@FXML
	TableColumn<Disease, String> tableName = new TableColumn<>("tableName");

	@FXML
	Button returnbutton;

	List<Disease> diseases;
	
	public static DiseasesResultsController resultsofsearchcontroller;


	public void getResultsofDiseases(List<Disease> ds) throws IOException {
		diseases = ds;
		setTablesDiseases();
	}
	
	public void setTablesDiseases() {
		try {
			if (!diseases.isEmpty()) {
				ResultsTableView.getItems().clear();
				ResultsTableView.getColumns().clear();
				ResultsTableView.getItems().addAll(diseases);
				tableID.setCellValueFactory(data -> new SimpleStringProperty(Integer.toString(data.getValue().getIdDisease())));
				tableName.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDiseaseName()));
				ResultsTableView.getColumns().addAll(tableID, tableName);			
			
			}
		} catch (Exception e) {
			e.printStackTrace();
			// ErrorPopup.errorPopup(0);
			return;
		}
	}
	
	@FXML
	private void selectedDisease(MouseEvent Mevent) throws IOException {
		Disease disease = ResultsTableView.getSelectionModel().getSelectedItem();
		if (disease == null) {
			//Error
			System.out.println("Nothing selected");
		}
		else {		
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmlfiles/diseaseview.fxml"));
			root = loader.load();
			DiseaseViewController drs = loader.getController();	
			drs.DiseaseView(disease);
			stage = (Stage) ((Node) Mevent.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}		
	}

	@FXML
	private void returnToFirstScreen(ActionEvent e) throws IOException, SQLException, NotBoundException {
		root = FXMLLoader.load(getClass().getResource("/fxmlfiles/firstscreen.fxml"));
		stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}

}