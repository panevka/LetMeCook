package com.letmecook.backend.user

import spock.lang.Specification

class UserServiceSpec extends Specification {

    def userRepository = new InMemoryUserRepository()
    def userService = new UserService(userRepository)

    def "user service creates a user" () {
        given:
        def username = 'testuser'

        when:
        def createdUser = userService.createUser(username)

        then:
        createdUser.getUsername() == username
    }

    def "user service must not allow username below 3 characters"() {
        given: 'username that is too short'
        def username = 'A'

        when:
        def createdUser = userService.createUser(username)

        then:
        thrown(IllegalArgumentException)
    }

    def "user service must not allow username above 30 characters"() {
        given: 'username that is too long'
        def username = 'a' * 31

        when:
        def createdUser = userService.createUser(username)

        then:
        thrown(IllegalArgumentException)
    }

    def "user service must username that is 3 characters long"() {
        given:
        def username = 'A' * 3

        when:
        def createdUser = userService.createUser(username)

        then:
        noExceptionThrown()
    }

    def "user service must not allow firstName above 30 characters"() {
        given:
        def username = 'Some Username'
        def firstName = 'a' * 31

        when:
        def createdUser = userService.createUser(username, firstName)

        then:
        thrown(IllegalArgumentException)
    }

    def "user service must not allow firstName below 3 characters"() {
        given:
        def username = 'Some Username'
        def firstName = 'a'

        when:
        def createdUser = userService.createUser(username, firstName)

        then:
        thrown(IllegalArgumentException)
    }

    def "user service must allow firstName with 3 characters"() {
        given:
        def username = 'Some Username'
        def firstName = 'a' * 3

        when:
        def createdUser = userService.createUser(username, firstName)

        then:
        noExceptionThrown()
    }

    def "user service must not allow lastName below 3 characters"() {
        given:
        def username = 'Some Username'
        def firstName = 'Some Firstname'
        def lastName = 'a'

        when:
        def createdUser = userService.createUser(username, firstName, lastName)

        then:
        thrown(IllegalArgumentException)
    }

    def "user service must not allow lastName above 30 characters"() {
        given:
        def username = 'Some Username'
        def firstName = 'Some Firstname'
        def lastName = 'a' * 31

        when:
        def createdUser = userService.createUser(username, firstName, lastName)

        then:
        thrown(IllegalArgumentException)
    }

    def "user service must allow lastName with exactly 3 characters"() {
        given:
        def username = 'Some Username'
        def firstName = 'Some Firstname'
        def lastName = 'a' * 3

        when:
        def createdUser = userService.createUser(username, firstName, lastName)

        then:
        noExceptionThrown()
    }

    def "user service must allow lastName with exactly 30 characters"() {
        given:
        def username = 'Some Username'
        def firstName = 'Some Firstname'
        def lastName = 'a' * 30

        when:
        def createdUser = userService.createUser(username, firstName, lastName)

        then:
        noExceptionThrown()
    }

    def "user service must allow creating user with given parameters"() {
        given:
        def username = 'Some Username'
        def firstName = 'Some Firstname'
        def lastName = 'Some Lastname'

        when:
        def createdUser = userService.createUser(username, firstName, lastName)

        then:
        createdUser.getUsername() == username
        createdUser.getFirstName() == firstName
        createdUser.getLastName() == lastName
    }

    def "user service must allow retrieval of created user"() {
        given:
        def username = 'Some Username'
        def firstName = 'Some Firstname'
        def lastName = 'Some Lastname'

        when:
        def createdUser = userService.createUser(username, firstName, lastName)
        def retrievedUser = userService.getUserById(createdUser.getId())

        then:
        retrievedUser == createdUser
    }

    def "user service must allow retrieval of multiple created users"() {
        given:
        def username1 = 'Some Username'
        def firstName1 = 'Some Firstname'
        def lastName1 = 'Some Lastname'

        def username2 = 'Some Username'
        def firstName2 = 'Some Firstname'
        def lastName2 = 'Some Lastname'

        when:
        def createdUser = userService.createUser(username1, firstName1, lastName1)
        def retrievedUser = userService.getUserById(createdUser.getId())

        def anotherCreatedUser = userService.createUser(username2, firstName2, lastName2)
        def anotherRetrievedUser = userService.getUserById(anotherCreatedUser.getId())

        then:
        createdUser == retrievedUser
        anotherCreatedUser == anotherRetrievedUser
    }

}
