package ifaces;

import java.sql.SQLException;
import java.util.List;

import pojos.URL;

public interface URLManager {
	
	//public URL getURLbyId (int idURL) throws SQLException;
	
		//public URL searchURLbyAddress (String address) throws SQLException;
		
		public List<URL> getURLsofResource (int idResource) throws SQLException;

}
