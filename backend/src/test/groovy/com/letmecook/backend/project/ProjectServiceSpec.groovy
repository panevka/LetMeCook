package com.letmecook.backend.project;

import spock.lang.Specification

class ProjectServiceSpec extends Specification {

	def IProjectRepository projectRepository = new InMemoryProjectRepository()
	def projectService = new ProjectService(projectRepository)

	def "project service allows project creation"(){
		given:
		def title = "Project Title"
		
		when:
		projectService.createProject(title);
		
		then:
		noExceptionThrown()
	}
	
	def "project service must return project with identical params as passed"(){
		
		given:
		def title = "Some Project Title"
		
		when:
		def createdProject = projectService.createProject(title)

		then:
		createdProject.getTitle() == title
	}

	def "project service must allow retrieval of created project"() {
		given:
		def title = "Some Project Title"
		
		when:
		def createdProject = projectService.createProject(title)
		def listOfProjects = projectService.getAllProjects()
	
		then:
		createdProject == listOfProjects[0]
	}

	def "project service must not allow project creation with empty title"() {
		given:
		def title = ""
		
		when:
		projectService.createProject(title)
		
		then:
		thrown(IllegalArgumentException)
	}

	def "project service must not allow project creation with title below 5 characters"() {
		given:
		def title = "Abc"
		
		when:
		projectService.createProject(title)
		
		then:
		thrown(IllegalArgumentException)
	}

	def "project service must not allow project creation with title above 50 characters"() {
		given:
		def title = "A" * 51
		
		when:
		projectService.createProject(title)
		
		then:
		thrown(IllegalArgumentException)
	}

	def "project service must allow project creation with title of exactly 5 characters"() {
		given:
		def title = "A" * 5
		
		when:
		projectService.createProject(title)
		
		then:
		noExceptionThrown()
	}

	def "project service must allow project creation with title of exactly 50 characters"() {
		given:
		def title = "A" * 50
		
		when:
		projectService.createProject(title)
		
		then:
		noExceptionThrown()
	}

	def "project service must allow creation and retrieval of multiple projects"() {
		given:
		def title1 = "Project One"
		def title2 = "Project Two"
		
		when:
		def project1 = projectService.createProject(title1)
		def project2 = projectService.createProject(title2)
		
		then:
		project1.getTitle() == title1
		project2.getTitle() == title2
	}

	def "project service must allow retrieval of multiple created projects"() {
		given:
		def title1 = "Project One"
		def title2 = "Project Two"
		
		when:
		def project1 = projectService.createProject(title1)
		def project2 = projectService.createProject(title2)
		def retrievedProjects = projectService.getAllProjects()
		
		then:
		retrievedProjects.contains(project1)
		retrievedProjects.contains(project2)
	}


	def "project service must allow retrieval of a specific project from all that were created"() {
		given:
		def title1 = "Project One"
		def title2 = "Project Two"
		
		when:
		def project1 = projectService.createProject(title1)
		def project2 = projectService.createProject(title2)

		def retrievedProject = projectService.getProjectById(project2.getId())
		
		then:
		retrievedProject == project2
	}

}
