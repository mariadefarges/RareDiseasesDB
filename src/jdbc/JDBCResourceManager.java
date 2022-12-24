package jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ifaces.ResourceManager;
import pojos.Resource;

public class JDBCResourceManager implements ResourceManager {

	private JDBCManager manager;
	private JDBCDiseaseManager dsmanager;
	private JDBCResourceTypeManager rstmanager;
	private JDBCURLManager urlmanager;
		

	public JDBCResourceManager(JDBCManager manager, JDBCDiseaseManager dsmanager, JDBCResourceTypeManager rstmanager,
			JDBCURLManager urlmanager) {
		super();
		this.manager = manager;
		this.dsmanager = dsmanager;
		this.rstmanager = rstmanager;
		this.urlmanager = urlmanager;
	}

	@Override
	public Resource searchResourcebyId(int idResource) throws SQLException {
		Resource resource = null;
		String sql = "SELECT * FROM Resources WHERE idResources= ?";
		PreparedStatement prep = manager.getConnection().prepareStatement(sql);
		prep.setInt(1, idResource);
		ResultSet rs = prep.executeQuery();
		while (rs.next()) {
			String name = rs.getString("Name");
			String finality = rs.getString("Finality");
			String price = rs.getString("Price");
			String access = rs.getString("Access");
			resource = new Resource (idResource, name, finality, price, access);
			
			resource.setDiseases(dsmanager.getDiseasesofResource(idResource));
			resource.setResourcetypes(rstmanager.getResourceTypes(idResource));
			resource.setURLs(urlmanager.getURLsofResource(idResource));	
		}
		prep.close();
		rs.close();
		return resource;
	}

	@Override
	public List<Resource> searchResourcebyName(String name, String filters) throws SQLException {
		Resource resource = null;
		String namesearch = "%" + name +"%";
		String sql = "SELECT idResources, Name FROM Resources AS r JOIN Resources_ResourcesType AS rt ON r.idResources = rt.idResource "
				+ "JOIN ResourcesType AS t ON rt.idResourceType = t.idType JOIN Resources_URL AS ru ON "
				+ "r.idResources = ru.idResource JOIN URL as u ON ru.idURL = u.idURL WHERE r.Name LIKE ?" + filters;
		PreparedStatement prep = manager.getConnection().prepareStatement(sql);
		prep.setString(1, namesearch);
		ResultSet rs = prep.executeQuery();
		List <Resource> resources = new ArrayList<Resource>();
		while(rs.next()){
			int idResource = rs.getInt("idResources");
			String realname = rs.getString("Name");
			resource = new Resource (idResource, realname);
			resources.add(resource);
		}
		rs.close();	
		return resources;
	}

	

	@Override
	public List<Resource> getResourcesofDisease(int idDisease) throws SQLException {
		Resource resource = null;
		String sql = "SELECT idResources, Name FROM Resources AS r JOIN Resources_Diseases AS rd ON r.idResources = rd.idResource "
				+ "WHERE rd.idDisease = ?";
		PreparedStatement prep = manager.getConnection().prepareStatement(sql);
		prep.setInt(1, idDisease);
		ResultSet rs = prep.executeQuery();
		List <Resource> resources = new ArrayList<Resource>();
		while(rs.next()){
			int idResource = rs.getInt("idResources");
			String name = rs.getString("Name");
			resource = new Resource (idResource, name);
			resources.add(resource);
		}
		rs.close();	
		return resources;
	}

	@Override
	public String filterResourcesfilterResources (String languagefilter, String typefilter, String pricefilter, 
			String finalityfilter, String accessfilter, String locationfilter) throws SQLException {
		String language = "";
		String type = "";
		String price = "";
		String finality = "";
		String access = "";
		String location = "";
		
		if (languagefilter !=null) {
			language = "AND u.Language = '" + languagefilter + "' ";
		}
		if (typefilter !=null) {
			type = "AND t.Type = '" + typefilter + "' ";
		}
		if (pricefilter !=null) {
			price = "AND r.Price = '" + pricefilter + "' ";
		}
		if (finalityfilter !=null) {
			finality = "AND r.Finality = '" + finalityfilter + "' ";
		}
		if (accessfilter !=null) {
			access = "AND r.Access = '" + accessfilter + "' ";
		}
		if (locationfilter !=null) {
			location = "AND u.Location LIKE '%" + locationfilter + "%'";
		}
		String filters = language + type + price + finality + access + location ;
		return filters;
	}
		
}
	



