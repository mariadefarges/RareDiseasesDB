package pojos;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;


public class Resource implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5577716483693558852L;
	
	private Integer idResource;
	private String name;
	private String finality;
	private String price;
	private String access;
	
	private List<Disease> diseases;
	private List<ResourceType> resourcetypes;
	private List<URL> URLs;
	
	public Resource(Integer idResource, String name, String finality, String price, String access,
			List<Disease> diseases, List<ResourceType> resourcetypes, List<URL> uRLs) {
		super();
		this.idResource = idResource;
		this.name = name;
		this.finality = finality;
		this.price = price;
		this.access = access;
		this.diseases = diseases;
		this.resourcetypes = resourcetypes;
		this.URLs = uRLs;
	}
	
	public Resource(Integer idResource, String name) {
		super();
		this.idResource = idResource;
		this.name = name;
	}
	
	public Resource(Integer idResource, String name, String finality, String price, String access) {
		super();
		this.idResource = idResource;
		this.name = name;
		this.finality = finality;
		this.price = price;
		this.access = access;
	}

	public Integer getIdResource() {
		return idResource;
	}

	public void setIdResource(Integer idResource) {
		this.idResource = idResource;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFinality() {
		return finality;
	}

	public void setFinality(String finality) {
		this.finality = finality;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getAccess() {
		return access;
	}

	public void setAccess(String access) {
		this.access = access;
	}

	public List<Disease> getDiseases() {
		return diseases;
	}

	public void setDiseases(List<Disease> diseases) {
		this.diseases = diseases;
	}

	public List<ResourceType> getResourcetypes() {
		return resourcetypes;
	}

	public void setResourcetypes(List<ResourceType> resourcetypes) {
		this.resourcetypes = resourcetypes;
	}

	public List<URL> getURLs() {
		return URLs;
	}

	public void setURLs(List<URL> uRLs) {
		URLs = uRLs;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idResource, URLs, access, diseases, finality, name, price, resourcetypes);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Resource other = (Resource) obj;
		return Objects.equals(idResource, other.idResource) && Objects.equals(URLs, other.URLs)
				&& Objects.equals(access, other.access) && Objects.equals(diseases, other.diseases)
				&& Objects.equals(finality, other.finality) && Objects.equals(name, other.name)
				&& Objects.equals(price, other.price) && Objects.equals(resourcetypes, other.resourcetypes);
	}

	@Override
	public String toString() {
		return "Resource [IdResource=" + idResource + ", name=" + name + ", finality=" + finality + ", price=" + price
				+ ", access=" + access + ", diseases=" + diseases + ", resourcetypes=" + resourcetypes + ", URLs="
				+ URLs + "]";
	}

}
