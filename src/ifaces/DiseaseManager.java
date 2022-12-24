package ifaces;

import java.sql.SQLException;
import java.util.List;

import pojos.Disease;

public interface DiseaseManager {
	
public Disease searchDiseasebyId (int idDisease) throws SQLException;
	
	public List<Disease> searchDiseasebyNameORAffectedSystem (String name, String filters) throws SQLException;
	
	public String searchDiseasebyPrevalence ( String option) throws SQLException;
	
	public List<Disease> getDiseasesofResource (int idResource) throws SQLException;
	
}
