package controllers;

import java.awt.Desktop;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.rmi.NotBoundException;
import java.sql.SQLException;
import java.util.List;

import javafx.beans.binding.StringExpression;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import jdbc.JDBCDiseaseManager;
import jdbc.JDBCManager;
import jdbc.JDBCResourceManager;
import jdbc.JDBCResourceTypeManager;
import jdbc.JDBCURLManager;
import pojos.Disease;
import pojos.Resource;
import pojos.ResourceType;
import pojos.URL;

public class ResourceViewController {
	
	public static JDBCManager manager = new JDBCManager();
	public static JDBCDiseaseManager diseaseManager = new JDBCDiseaseManager(manager);
	public static JDBCResourceTypeManager resourcetypeManager = new JDBCResourceTypeManager(manager);
	public static JDBCURLManager urlManager = new JDBCURLManager(manager);
	public static JDBCResourceManager resourceManager = new JDBCResourceManager(manager, diseaseManager,
			resourcetypeManager, urlManager);

	Resource resource;
	private Parent root;
	private Stage stage;
	private Scene scene;

	@FXML
	private Button returnbutton;

	@FXML
	private Label resourceName, idNumber, finalityText, accessText, priceText, typeText, locationText, languageText;
	
	@FXML
	private Hyperlink urlText, urlText2, urlText3;

	@FXML
	private TableView<Disease> DiseasesRelatedTable;

	@FXML
	private TableColumn<Disease, String> tableName = new TableColumn<>("tableName");

	
	public static DiseasesResultsController diseaseresultscontroller;

	public void ResourceView(Resource resourceselected) {
		diseaseManager.setRsmanager(resourceManager);
		manager.getConnection();
		try {
			this.resource = resourceManager.searchResourcebyId(resourceselected.getIdResource());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		List<ResourceType> resourcetypes = resource.getResourcetypes();
		List<URL> urls = resource.getURLs();
		urlText.setVisible(false);
		urlText2.setVisible(false);
		urlText3.setVisible(false);
		String types = "";
		if (resourcetypes != null) {
			for (int i = 0; i < resourcetypes.size() - 1; i++) {
				types = types + resourcetypes.get(i).getType() + ", ";
			}
			types = types + resourcetypes.get(resourcetypes.size()-1).getType();
		}

		if (urls != null) {
			
			if (urls.size() > 0) {
				urlText.setVisible(true);
				urlText.setText(urls.get(0).getAddress());
				
			}
			if (urls.size() > 1) {
				urlText2.setVisible(true);
				System.out.println(urls.get(1).getAddress());
				urlText2.setText(urls.get(1).getAddress());
			}
			
			if (urls.size() > 2) {
				urlText3.setVisible(true);
				urlText3.setText(urls.get(2).getAddress());
			}
		}
		
		resourceName.setText(resource.getName());
		idNumber.setText(resource.getIdResource().toString());
		finalityText.setText(resource.getFinality());
		accessText.setText(resource.getAccess());
		priceText.setText(resource.getPrice());
		typeText.setText(types);

		if (resource.getDiseases() != null) {
			DiseasesRelatedTable.getItems().clear();
			DiseasesRelatedTable.getColumns().clear();
			DiseasesRelatedTable.getItems().addAll(resource.getDiseases());
			tableName.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDiseaseName()));
			DiseasesRelatedTable.getColumns().addAll(tableName);
		}

	}

	@FXML
	private void selectedDisease(MouseEvent Mevent) throws IOException {
		Disease disease = DiseasesRelatedTable.getSelectionModel().getSelectedItem();
		if (resource == null) {
			// Error
			System.out.println("Nothing selected");
		} else {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmlfiles/diseaseview.fxml"));
			root = loader.load();
			DiseaseViewController dsc = loader.getController();
			dsc.DiseaseView(disease);
			stage = (Stage) ((Node) Mevent.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
	}
	
	@FXML
	private void linkPressed (MouseEvent Mevent) throws IOException {
		
		Hyperlink hl = (Hyperlink) Mevent.getSource();
		Desktop enlace = Desktop.getDesktop();
		try {
			enlace.browse(new URI(hl.getText()));
		}
		catch(Exception ex) {
			ex.printStackTrace();
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

