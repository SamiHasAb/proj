package org.study.pma.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.study.pma.DTO.ChartData;
import org.study.pma.DTO.TimeChartData;
import org.study.pma.Entities.Project;
import org.study.pma.Repository.ProjectRepository;


@Service
public class ProjectService {
	@Autowired
	ProjectRepository proRepo;

	
	public Project save(Project project) {
		return proRepo.save(project);
	}

	public Iterable<Project> findAll() {
		return proRepo.findAll();
	}
	public Page<Project> findAll(Pageable pageable) {
		return proRepo.findAll( pageable);
	}
	
	public List<ChartData> getProjectStatus(){
		return proRepo.getProjectStatus();
	}



	
	
	public List<TimeChartData> getTimeData(){
		return proRepo.getTimeData();
	}

	public void delete(Project theProject) {
		proRepo.delete(theProject);		
	}

	public Project findByProjecteId(long theId) {
		// TODO Auto-generated method stub
		return proRepo.findByProjectId(theId);
	}

	public void deleteProRelationWithEmp(long projectId) {

	proRepo.deleteProRelationWithEmp(projectId); 
	}

	


}
