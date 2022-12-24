package jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ifaces.ResourceTypeManager;
import pojos.ResourceType;

public class JDBCResourceTypeManager implements ResourceTypeManager {

	private JDBCManager manager;

	public JDBCResourceTypeManager(JDBCManager manager) {
		super();
		this.manager = manager;
	}

	@Override
	public List<ResourceType> getResourceTypes(int idResource) throws SQLException {
		ResourceType rstype = null;
		String sql = "SELECT * FROM ResourcesType AS rt JOIN Resources_ResourcesType AS rrt ON rt.idType = rrt.idResourceType WHERE rrt.idResource = ?";
		PreparedStatement prep = manager.getConnection().prepareStatement(sql);
		prep.setInt(1, idResource);
		ResultSet rs = prep.executeQuery();
		List <ResourceType> rstypes = new ArrayList<ResourceType>();
		while(rs.next()){
			int idType = rs.getInt("idType");
			String type = rs.getString("Type");
			String description = rs.getString("Description");
			rstype = new ResourceType (idType, type, description);
			rstypes.add(rstype);
		}
		rs.close();	
		return rstypes;
	}



}
