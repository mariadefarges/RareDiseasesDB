package jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ifaces.DiseaseManager;
import pojos.Disease;

public class JDBCDiseaseManager implements DiseaseManager {

	private JDBCManager manager;
	private JDBCResourceManager rsmanager;

	public JDBCDiseaseManager(JDBCManager manager) {
		super();
		this.manager = manager;
	}

	public void setRsmanager(JDBCResourceManager rsmanager) {
		this.rsmanager = rsmanager;
	}

	@Override
	public Disease searchDiseasebyId(int idDisease) throws SQLException {
		Disease disease = null;
		String sql = "SELECT * FROM Diseases WHERE idDisease= ?";
		PreparedStatement prep = manager.getConnection().prepareStatement(sql);
		prep.setInt(1, idDisease);
		ResultSet rs = prep.executeQuery();
		while (rs.next()) {
			String name = rs.getString("diseaseName");
			String prevalence = rs.getString("Prevalence");
			String affectedsystem = rs.getString("AffectedSystem");
			String treatment = rs.getString("Treatment");
			String diagnosis = rs.getString("Diagnosis");
			String description = rs.getString("Description");
			Float prevalencef = rs.getFloat("Prevalence_f");		
			disease = new Disease (idDisease, name, prevalence, affectedsystem, treatment, diagnosis, description, prevalencef);		
			disease.setResources(rsmanager.getResourcesofDisease(idDisease));

		}
		prep.close();
		rs.close();
		return disease;
	}

	@Override
	public List<Disease> searchDiseasebyNameORAffectedSystem(String name, String filters) throws SQLException {
		Disease disease = null;
		String namesearch = "%" + name + "%";
		String sql = "SELECT idDisease, diseaseName FROM Diseases WHERE diseaseName LIKE ? OR AffectedSystem LIKE ?" + filters;
		PreparedStatement prep = manager.getConnection().prepareStatement(sql);
		prep.setString(1, namesearch);
		prep.setString(2, namesearch);
		ResultSet rs = prep.executeQuery();
		List<Disease> diseases = new ArrayList<Disease>();
		while (rs.next()) {
			int idDisease = rs.getInt("idDisease");
			String realname = rs.getString("diseaseName");
			disease = new Disease(idDisease, realname);
			disease.setResources(rsmanager.getResourcesofDisease(idDisease));
			diseases.add(disease);
			
		}
		rs.close();
		return diseases;
	}

	@Override
	public String searchDiseasebyPrevalence(String option) throws SQLException {
		String filters = "";
		if (option != null) {
			switch (option) {
			case "high-low":
				filters = " ORDER BY Prevalence_f DESC";
			case "low-high":
				filters = " ORDER BY Prevalence_f ASC";

			}
		}
		return filters;
	}

	@Override
	public List<Disease> getDiseasesofResource(int idResource) throws SQLException {
		Disease disease = null;
		String sql = "SELECT * FROM Diseases AS d JOIN Resources_Diseases AS rd ON d.idDisease = rd.idDisease WHERE rd.idResource = ?";
		PreparedStatement prep = manager.getConnection().prepareStatement(sql);
		prep.setInt(1, idResource);
		ResultSet rs = prep.executeQuery();
		List<Disease> diseases = new ArrayList<Disease>();
		while (rs.next()) {
			//idDisease is an ambiguous column --> fixed
			int idDisease = rs.getInt(1);
			String name = rs.getString("diseaseName");
			disease = new Disease(idDisease, name);
			diseases.add(disease);
		}
		rs.close();
		return diseases;
	}

}
