package org.study.pma.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.study.pma.DTO.EmployeeProject;
import org.study.pma.Entities.Employee;

//@Repository

@RepositoryRestResource(collectionResourceRel="apiemployees", path="apiemployees")
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long>{
	
	public Page<Employee>  findAll( Pageable pagable ); 

	public Iterable<Employee>  findAll(); 
	 //here we override to get a list instead of iterator
	
	@Query(nativeQuery=true, value="SELECT * from employee;")
	public List<Employee>  findAllList(); 

	
	@Query(nativeQuery=true, value="SELECT e.first_name as firstName, e.last_name as lastName, COUNT(pe.employee_id) as projectCount " + 
			"FROM employee e left join project_employee pe ON pe.employee_id = e.employee_id " + 
			"GROUP BY e.first_name, e.last_name ORDER BY 3 DESC")
	public List<EmployeeProject> employeeProjects();


	public Employee findByEmail(String value);



	public Employee findByEmployeeId(long theId);

	
	
}
