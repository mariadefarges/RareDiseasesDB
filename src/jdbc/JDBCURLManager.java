package jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ifaces.URLManager;
import pojos.URL;

public class JDBCURLManager implements URLManager {

private JDBCManager manager;
	

	public JDBCURLManager(JDBCManager manager) {
		super();
		this.manager = manager;
	}

	@Override
	public List<URL> getURLsofResource(int idResource) throws SQLException {
		URL url = null;
		String sql = "SELECT * FROM URL AS url JOIN Resources_URL AS ru ON url.idURL = ru.idURL WHERE ru.idResource = ?";
		PreparedStatement prep = manager.getConnection().prepareStatement(sql);
		prep.setInt(1, idResource);
		ResultSet rs = prep.executeQuery();
		List <URL> urls = new ArrayList<URL>();
		while(rs.next()){
			int idURL = rs.getInt(1);
			String language = rs.getString("Language");
			String address = rs.getString("Address");
			String location = rs.getString("Location");
			url = new URL (idURL, language, address, location);
			urls.add(url);
		}
		rs.close();	
		return urls;
	}
}
