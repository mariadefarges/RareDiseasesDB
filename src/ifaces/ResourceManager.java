package ifaces;

import java.sql.SQLException;
import java.util.List;

import pojos.Resource;

public interface ResourceManager {
	
public Resource searchResourcebyId (int idResource) throws SQLException;
	
	public List<Resource> searchResourcebyName (String name, String filters) throws SQLException;
	
	public String filterResourcesfilterResources (String languagefilt, String typefilt, String pricefilt, 
			String finalityfilt, String accessfilt, String locatiofilt) throws SQLException;

	public List<Resource> getResourcesofDisease (int idDisease) throws SQLException;

}
