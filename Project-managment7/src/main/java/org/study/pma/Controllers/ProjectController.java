package org.study.pma.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.study.pma.DTO.TimeChartData;
import org.study.pma.Entities.Employee;
import org.study.pma.Entities.Project;
import org.study.pma.Services.EmployeeService;
import org.study.pma.Services.ProjectService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Controller
@RequestMapping("/projects")
public class ProjectController {

	@Autowired
	ProjectService proService;
	
	@Autowired
	EmployeeService empService;
	
	// used to display table when request "/projects"
	//its by default a get method
	@RequestMapping()
	public String showProjects(Model model , Pageable pageable) {
		
		Page<Project> projects = proService.findAll(pageable);
		model.addAttribute("projects", projects);
		
		return"/project/list-projects";
	}
	
	//@RequestMapping(value = "/new", method = RequestMethod.GET)

	@GetMapping("/new")
	public String displayProjectForm(Model model  ) {
		Project aProject = new Project();
		
		 List<Employee> employees= empService.findAllList(); 
		
		
		model.addAttribute("project",aProject);
		
		model.addAttribute("employeesList",employees);
		
		return "/project/new-project";
	}
	
	@PostMapping("/save")
	public String createProject(Project project , Model model) {
		
		proService.save(project);
		
		
		// info <!-- *{name}=${project.name} -->

		//used when @many to one
		//  need to write @RequestParam List<Long> employees
		// we give the name employees b/c its the same name coming from the form

	/*	Iterable<Employee> chosenEmployees = empRepo.findAllById(employees);

		//Iterate by foreach and save
		
		for(Employee item : chosenEmployees) {
			item.setProject(project);
			empRepo.save(item);
		} */
				
		//use redirect to prevent duplicate submissions
		
		return "redirect:/projects/new";
	}
	
	@GetMapping("/update")
	public String displayProjectUpdateForm(@RequestParam("id") long theId, Model model ) {
		
		Project theProject= proService.findByProjecteId(theId);
		 List<Employee> theEmployees= theProject.getEmployees();

		proService.delete(theProject);
		
		 theProject.getEmployees().clear();


		
		
		
		
		Iterable<Employee> employees= empService.findAll() ;
		
		
		
		
//		Long ProjectId = theProject.getProjectId();
		
//		proService.deleteProRelationWithEmp(ProjectId);
		

		 model.addAttribute("project",theProject);
		
		model.addAttribute("employeesList",employees);
		
		
		return "/project/new-project";

		
	}
	
	@GetMapping("delete")
	public String deleteProject(@RequestParam("id") long theId, Model model) {
		Project theProject = proService.findByProjecteId(theId);
			
			
		proService.delete(theProject);
		return "redirect:/projects";
	}	

	
	
	@GetMapping("/timelines")
	public String displayProjectTimelines(Model model) throws JsonProcessingException {
		
		List<TimeChartData> timelineData = proService.getTimeData();
		
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonTimelineString = objectMapper.writeValueAsString(timelineData);

		System.out.println("---------- project timelines ----------");
		System.out.println(jsonTimelineString);
		
		model.addAttribute("projectTimeList", jsonTimelineString);
		
		return "project/project-timelines";
	}

}
