package com.letmecook.backend.project;

import spock.lang.Specification

class ProjectSpec extends Specification {

	def "a project should have a name"() {
		when:
		def project = new Project(projectName)

		then:
		project.getTitle() == expectedName

		where:
		projectName    || expectedName
		"Test Project" || "Test Project"
		"Top project"  || "Top project"
		"Uno projecto" || "Uno projecto"
	}


}
