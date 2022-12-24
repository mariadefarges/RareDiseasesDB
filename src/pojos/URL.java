package pojos;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class URL implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3483328264243869399L;
	
	private Integer idURL;
	private String language;
	private String address;
	private String location;
	
	private List<Resource> resources;

	public URL(Integer idURL, String language, String address, String location, List<Resource> resources) {
		super();
		this.idURL = idURL;
		this.language = language;
		this.address = address;
		this.location = location;
		this.resources = resources;
	}
	
	public URL(Integer idURL, String language, String address, String location) {
		super();
		this.idURL = idURL;
		this.language = language;
		this.address = address;
		this.location = location;
	}

	/*public URL(String language, String address, String locatio2) {
		this.language = language;
		this.address = address;
		this.location = location;
	}*/

	public Integer getIdURL() {
		return idURL;
	}

	public void setIdURL(Integer idURL) {
		this.idURL = idURL;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<Resource> getResources() {
		return resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, idURL, language, location, resources);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		URL other = (URL) obj;
		return Objects.equals(address, other.address) && Objects.equals(idURL, other.idURL)
				&& Objects.equals(language, other.language) && Objects.equals(location, other.location)
				&& Objects.equals(resources, other.resources);
	}

	@Override
	public String toString() {
		return "URL [idURL=" + idURL + ", language=" + language + ", address=" + address + ", location=" + location
				+ ", resources=" + resources + "]";
	}

}
