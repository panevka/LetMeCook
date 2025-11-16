package com.letmecook.backend.project;

import spock.lang.Specification

class ProjectSpec extends Specification {

	def "a project should have a name"() {
		when:
		def project = Project.create(projectName)

		then:
		project.getTitle() == expectedName

		where:
		projectName    || expectedName
		"Test Project" || "Test Project"
		"Top project"  || "Top project"
		"Uno projecto" || "Uno projecto"
	}

	def "a project title should not be null"() {
		given:
		def title = null

		when:
		def ex = Project.create(title)

		then:
		thrown(IllegalArgumentException)
	}

	def "a project title should not be blank"() {
		given:
		def title = ""

		when:
		def ex = Project.create(title)

		then:
		thrown(IllegalArgumentException)
	}

	def "a project title should not exceed 50 characters"() {
		given:
		def maxLength = 50;
		def title = "a" * (maxLength + 1)
		
		when:
		Project.create(title)
		
		then:
		thrown(IllegalArgumentException)
	}

	def "a project title should be at least 5 characters long"() {
		given:
		def minLength = 5;
		def title = "a" * (minLength - 1)
		
		when:
		Project.create(title)
		
		then:
		thrown(IllegalArgumentException)
	}

	def "a project title can be exactly 50 characters"() {
		given:
		def maxLength = 50;
		def title = "a" * maxLength
		
		when:
		Project.create(title)
		
		then:
		noExceptionThrown()
	}


}
