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
import pojos.Resource;

public class ResourcesResultsController {

	public static ResourcesResultsController rs;
	private Parent root;
	private Stage stage;
	private Scene scene;

	@FXML
	TableView<Resource> ResultsTableView;

	@FXML
	TableColumn<Resource, String> tableID = new TableColumn<>("tableID");

	@FXML
	TableColumn<Resource, String> tableName = new TableColumn<>("tableName");

	@FXML
	Button returnbutton;
	
	List<Resource> resources;
	
	public static ResourcesResultsController resultsofsearchcontroller;

	public void setPatientController(ResourcesResultsController rs) {
		resultsofsearchcontroller = rs;
	}
	
	public void getResultsofResources(List<Resource> rs) throws IOException {
		resources = rs;
		setTablesResources();
	}
	
	private void setTablesResources() {
		try {
			if (!resources.isEmpty()) {
				ResultsTableView.getItems().clear();
				ResultsTableView.getColumns().clear();
				tableID.setCellValueFactory(data -> new SimpleStringProperty(Integer.toString(data.getValue().getIdResource())));
				tableName.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));
				ResultsTableView.getColumns().addAll(tableID, tableName);
				ResultsTableView.getItems().addAll(resources);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// ErrorPopup.errorPopup(0);
			return;
		}
		
	}
	
	@FXML
	private void selectedResource(MouseEvent Mevent) throws IOException {
		Resource resource = ResultsTableView.getSelectionModel().getSelectedItem();
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
	private void returnToFirstScreen(ActionEvent e) throws IOException, SQLException, NotBoundException {
		root = FXMLLoader.load(getClass().getResource("/fxmlfiles/firstscreen.fxml"));
		stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}

}