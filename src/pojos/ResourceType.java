package pojos;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;


public class ResourceType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5425872834315176906L;

	private Integer idType;
	private String type;
	private String description;
	
	private List<Resource> resources;

	public ResourceType(Integer idType, String type, String description, List<Resource> resources) {
		super();
		this.idType = idType;
		this.type = type;
		this.description = description;
		this.resources = resources;
	}
	
	public ResourceType(Integer idType, String type, String description) {
		super();
		this.idType = idType;
		this.type = type;
		this.description = description;
	}

	public Integer getIdType() {
		return idType;
	}

	public void setIdType(Integer idType) {
		this.idType = idType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Resource> getResources() {
		return resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, idType, resources, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResourceType other = (ResourceType) obj;
		return Objects.equals(description, other.description) && Objects.equals(idType, other.idType)
				&& Objects.equals(resources, other.resources) && Objects.equals(type, other.type);
	}

	@Override
	public String toString() {
		return "ResourceType [idType=" + idType + ", type=" + type + ", description=" + description + ", resources="
				+ resources + "]";
	}
}
