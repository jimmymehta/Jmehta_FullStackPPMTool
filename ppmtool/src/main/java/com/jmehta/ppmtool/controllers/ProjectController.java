package com.jmehta.ppmtool.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage.Body;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jmehta.ppmtool.domain.Project;
import com.jmehta.ppmtool.services.ProjectService;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
	
	@Autowired
	ProjectService proService;
	
	@PostMapping
	public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result){
		
		if(result.hasErrors()) {
			Map<String,String> errorMap = new HashMap<String, String>();
			for(FieldError error: result.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			return new ResponseEntity<Map<String, String>> (errorMap, HttpStatus.BAD_REQUEST);
		}
		
		Project project1 = proService.saveOrUpdateProject(project);
		return new ResponseEntity<Project>(project1, HttpStatus.CREATED);
	}

}
