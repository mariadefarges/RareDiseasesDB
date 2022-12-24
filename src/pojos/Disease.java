package pojos;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;


public class Disease implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5481381056479102680L;

	private Integer idDisease;
	private String name;
	private String prevalence;
	private String affectedSystem;
	private String treatment;
	private String diagnosis;
	private String description;
	private Float prevalenceF;
	
	private List<Resource> resources;
	
	public Disease(Integer idDisease, String name, String prevalence, String affectedSystem, String treatment,
			String diagnosis, String description, Float prevalenceF) {
		super();
		this.idDisease = idDisease;
		this.name = name;
		this.prevalence = prevalence;
		this.affectedSystem = affectedSystem;
		this.treatment = treatment;
		this.diagnosis = diagnosis;
		this.description = description;
		this.prevalenceF = prevalenceF;
	}
	
	public Disease(Integer idDisease, String name) {
		super();
		this.idDisease = idDisease;
		this.name = name;
	}

	public Disease(Integer idDisease, String name, String prevalence, String affectedSystem, String treatment,
			String diagnosis, String description, List<Resource> resources) {
		super();
		this.idDisease = idDisease;
		this.name = name;
		this.prevalence = prevalence;
		this.affectedSystem = affectedSystem;
		this.treatment = treatment;
		this.diagnosis = diagnosis;
		this.description = description;
		this.resources = resources;
	}

	public Integer getIdDisease() {
		return idDisease;
	}

	public void setIdDisease(Integer idDisease) {
		this.idDisease = idDisease;
	}

	public String getDiseaseName() {
		return name;
	}

	public void setDiseaseName(String diseaseName) {
		this.name = diseaseName;
	}

	public String getPrevalence() {
		return prevalence;
	}

	public void setPrevalence(String prevalence) {
		this.prevalence = prevalence;
	}

	public String getAffectedSystem() {
		return affectedSystem;
	}

	public void setAffectedSystem(String affectedSystem) {
		this.affectedSystem = affectedSystem;
	}

	public String getTreatment() {
		return treatment;
	}

	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
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
		return Objects.hash(affectedSystem, description, diagnosis, idDisease, name, prevalence, resources, treatment);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Disease other = (Disease) obj;
		return Objects.equals(affectedSystem, other.affectedSystem) && Objects.equals(description, other.description)
				&& Objects.equals(diagnosis, other.diagnosis) && Objects.equals(idDisease, other.idDisease)
				&& Objects.equals(name, other.name) && Objects.equals(prevalence, other.prevalence)
				&& Objects.equals(resources, other.resources) && Objects.equals(treatment, other.treatment);
	}

	@Override
	public String toString() {
		return "Disease [idDisease=" + idDisease + ", name=" + name + ", prevalence=" + prevalence + ", affectedSystem="
				+ affectedSystem + ", treatment=" + treatment + ", diagnosis=" + diagnosis + ", description="
				+ description + ", resources=" + resources + "]";
	}
	
	

	/*@Override
	public String toString() {
		return "\nidDisease: " + idDisease + "\nName: " + name + "\nPrevalence: " + prevalence
				+ "\nAffectedSystem: " + affectedSystem + "\nTreatment: " + treatment + "\nDiagnosis: " + diagnosis
				+ "\nDescription: " + description;
	}*/
	
	
}
