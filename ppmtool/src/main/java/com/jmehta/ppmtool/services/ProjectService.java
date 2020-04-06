package com.jmehta.ppmtool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jmehta.ppmtool.domain.Project;
import com.jmehta.ppmtool.repositories.ProjectRepository;

@Service
public class ProjectService {
	
	@Autowired
	ProjectRepository projectRepo;
	
	public Project saveOrUpdateProject(Project project) {
		
		return projectRepo.save(project);
	}
	
	

}
