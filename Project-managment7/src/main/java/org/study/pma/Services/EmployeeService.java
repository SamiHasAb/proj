package org.study.pma.Services;

import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.study.pma.DTO.EmployeeProject;
import org.study.pma.Entities.Employee;
import org.study.pma.Entities.Project;
import org.study.pma.Repository.EmployeeRepository;


@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository empRepo;
	
	
	public Employee save(Employee employee) {
		return empRepo.save(employee);
	}

	public Iterable<Employee> findAll() {
		return empRepo.findAll();
	}
	public Page<Employee> findAll( Pageable pagable) {
		return empRepo.findAll(pagable);
	}


	public List<EmployeeProject> employeeProjects() {
		return empRepo.employeeProjects();
	}


	public Employee findByEmployeeId(long theId) {
		return empRepo.findByEmployeeId(theId);
	}


	public void delete(Employee theEmp) {
		empRepo.delete(theEmp);
		
	}

	public List<Employee> findAllList() {

		return empRepo.findAllList();
		}



	
}
