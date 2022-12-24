package application;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;

import jdbc.JDBCDiseaseManager;
import jdbc.JDBCManager;
import jdbc.JDBCResourceManager;
import jdbc.JDBCResourceTypeManager;
import jdbc.JDBCURLManager;
import pojos.Disease;
import pojos.Resource;

public class Menu {
	

	public static JDBCManager manager;
	public static JDBCDiseaseManager diseaseManager;
	public static JDBCResourceManager resourceManager;
	public static JDBCResourceTypeManager resourcetypeManager;
	public static JDBCURLManager urlManager;
	
	
	private static BufferedReader reader = new BufferedReader (new InputStreamReader(System.in));
	public static void main(String[] args) {
		manager = new JDBCManager();
		diseaseManager = new JDBCDiseaseManager(manager);
		resourcetypeManager = new JDBCResourceTypeManager(manager);
		urlManager = new JDBCURLManager(manager);
		resourceManager = new JDBCResourceManager(manager, diseaseManager, resourcetypeManager, urlManager);
		diseaseManager.setRsmanager(resourceManager);
		
		try {
			String filters = diseaseManager.searchDiseasebyPrevalence("high-low");
			//String filters = resourceManager.filterResourcesfilterResources("Spanish", null, null, null, null, null);
			List<Resource> resources = resourceManager.searchResourcebyName("española", "");
			System.out.println(resources);
			//System.out.print(d1);
			//String filters = diseaseManager.searchDiseasebyPrevalence(true, 0);
			//List<Disease> diseases = diseaseManager.searchDiseasebyNameORAffectedSystem("anemia", "");
			//System.out.println(diseases);	
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
