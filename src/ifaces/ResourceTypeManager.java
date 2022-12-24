package ifaces;

import java.sql.SQLException;
import java.util.List;

import pojos.ResourceType;

public interface ResourceTypeManager {
	
	//public ResourceType searchResourceTypebyId (int idType) throws SQLException;
	
		//public List<ResourceType> searchResourceTypebyType (String type) throws SQLException;
		
		public List<ResourceType> getResourceTypes (int idResource) throws SQLException;

}
