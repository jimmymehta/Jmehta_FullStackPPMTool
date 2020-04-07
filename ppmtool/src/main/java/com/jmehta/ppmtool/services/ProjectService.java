package com.jmehta.ppmtool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jmehta.ppmtool.domain.Project;
import com.jmehta.ppmtool.exceptions.ProjectIdException;
import com.jmehta.ppmtool.repositories.ProjectRepository;

@Service
public class ProjectService {
	
	@Autowired
	ProjectRepository projectRepo;
	
	public Project saveOrUpdateProject(Project project) {
		try {
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			return projectRepo.save(project);
		}catch(Exception e) {
			throw new ProjectIdException("Project Id '" + project.getProjectIdentifier().toUpperCase()+"' already exists");
		}
	}
	
	public Project findProjectByIdentifier(String projectId) {
		
		Project project = projectRepo.findByProjectIdentifier(projectId.toUpperCase());
		
		if(project == null) {
			throw new ProjectIdException("Project Id '" + projectId +"' does not exist");
		}
		
		return project;
	}
	
	public Iterable<Project> findAllProjects(){
		return projectRepo.findAll();
	}
	
	public void deleteProjectByIdentifier(String projectId) {
		
		Project project = projectRepo.findByProjectIdentifier(projectId);
		
		if(project == null) {
			throw new ProjectIdException("Cannot delete Porject with ID ' "+ projectId+ "'. This project dont exist");
			
		}
		
		projectRepo.delete(project);
	}
}
