package org.study.pma.Entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
public class Project {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="project_seq" )
    @SequenceGenerator(name="project_seq",sequenceName="project_seq", allocationSize = 1)

	private long projectId;
	
	@NotBlank
	private String name;
	
	private String stage; // NOTSTARTED, COMPLETED, INPROGRESS
	
	private String description;

	@NotBlank(message="date cannot be empty")
	private Date startDate;
	
	@NotBlank(message="date cannot be empty")
	private Date endDate;
	
	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST},
			   fetch = FetchType.EAGER)
	@JoinTable(name="project_employee",
				joinColumns=@JoinColumn(name="project_id"), 
				inverseJoinColumns= @JoinColumn(name="employee_id"))
	@JsonIgnore
	private List<Employee> employees;
	



	public Project() {
		super();
	}



	public Project(String name, String stage, String description) {
		super();
		this.name = name;
		this.stage = stage;
		this.description = description;
	}
	

	public long getProjectId() {
		return projectId;
	}



	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getStage() {
		return stage;
	}



	public void setStage(String stage) {
		this.stage = stage;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}

	
	
	
	
	public Date getStartDate() {
		return startDate;
	}



	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}



	public Date getEndDate() {
		return endDate;
	}



	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}



	public List<Employee> getEmployees() {
		return employees;
	}



	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}



		// to add employees to a list then to the project method 
		public void addEmployee(Employee emp) {
			if(employees==null) {
				employees = new ArrayList<>();
			}
			employees.add(emp);
		}



		@Override
		public String toString() {
			return "Project [projectId=" + projectId + ", name=" + name + ", stage=" + stage + ", description="
					+ description + ", employees=" + employees + "]";
		}

	

}
