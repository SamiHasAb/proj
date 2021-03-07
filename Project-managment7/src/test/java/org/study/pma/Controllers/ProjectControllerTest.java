package org.study.pma.Controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.study.pma.Entities.Project;
import org.study.pma.Repository.ProjectRepository;

class ProjectControllerTest {

	@Autowired
	ProjectRepository proRepo;
	

	
	


	/*@Test
	void testCreateProject(Project project, Model model) {
		 project = new Project("dcsdf","dfdsdf","erfrefer");
		proRepo.save(project);
		
		assertEquals(5, proRepo.findAll().size());


		
	}*/

}
