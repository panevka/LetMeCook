package com.letmecook.backend.user

import spock.lang.Specification

class UserServiceSpec extends Specification {

    def "user service exists" () {
        when:
        def userService = new UserService()
        then:
        noExceptionThrown()
    }

    def "user service creates a user" () {
        given:
        def userService = new UserService()
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
        def createdUser = User.create(username)

        then:
        thrown(IllegalArgumentException)
    }

    def "user service must not allow username above 30 characters"() {
        given: 'username that is too long'
        def username = 'a' * 31

        when:
        def createdUser = User.create(username)

        then:
        thrown(IllegalArgumentException)
    }

    def "user service must username that is 3 characters long"() {
        given:
        def username = 'A' * 3

        when:
        def createdUser = User.create(username)

        then:
        noExceptionThrown()
    }

    def "user service must not allow firstName above 30 characters"() {
        given:
        def username = 'Some Username'
        def firstName = 'a' * 31

        when:
        def createdUser = User.create(username, firstName)

        then:
        thrown(IllegalArgumentException)
    }

    def "user service must not allow firstName below 3 characters"() {
        given:
        def username = 'Some Username'
        def firstName = 'a'

        when:
        def createdUser = User.create(username, firstName)

        then:
        thrown(IllegalArgumentException)
    }

    def "user service must allow firstName with 3 characters"() {
        given:
        def username = 'Some Username'
        def firstName = 'a' * 3

        when:
        def createdUser = User.create(username, firstName)

        then:
        noExceptionThrown()
    }

    def "user service must not allow lastName below 3 characters"() {
        given:
        def username = 'Some Username'
        def firstName = 'Some Firstname'
        def lastName = 'a'

        when:
        def createdUser = User.create(username, firstName, lastName)

        then:
        thrown(IllegalArgumentException)
    }

    def "user service must not allow lastName above 30 characters"() {
        given:
        def username = 'Some Username'
        def firstName = 'Some Firstname'
        def lastName = 'a' * 31

        when:
        def createdUser = User.create(username, firstName, lastName)

        then:
        thrown(IllegalArgumentException)
    }

    def "user service must allow lastName with exactly 3 characters"() {
        given:
        def username = 'Some Username'
        def firstName = 'Some Firstname'
        def lastName = 'a' * 3

        when:
        def createdUser = User.create(username, firstName, lastName)

        then:
        noExceptionThrown()
    }

    def "user service must allow lastName with exactly 30 characters"() {
        given:
        def username = 'Some Username'
        def firstName = 'Some Firstname'
        def lastName = 'a' * 30

        when:
        def createdUser = User.create(username, firstName, lastName)

        then:
        noExceptionThrown()
    }

}
