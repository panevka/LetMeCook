package com.letmecook.backend.user;

import com.letmecook.backend.user.User;
import spock.lang.Specification

class UserSpec extends Specification {

        def "A user must always have a username"() {
                when:
                User.create(null)

                then:
                def ex = thrown(IllegalArgumentException)
        }

        def "Cannot create a user with empty username"() {
                when:
                User.create("")

                then:
                def ex = thrown(IllegalArgumentException)
        }

        def "Username has to be at least 3 characters long"() {
                when:
                User.create("AA")

                then:
                def ex = thrown(IllegalArgumentException)
        }

        def "Username cannot be above 30 characters"() {
                given: "username above 30 chars"
                def maxLength = 30
                def tooLongUsername = "a" * maxLength

                when:
                User.create(tooLongUsername)

                then:
                def ex = thrown(IllegalArgumentException)
        }

        def "should accept a valid username"() {
                given: "a username that meets all rules"
                def validUsername = "player123"

                when:
                def createdUser = User.create(validUsername)

                then:
                createdUser.getUsername() == validUsername
        }

        def "A user should be able to have a first name"() {
                given: "firstName that meets all rules"
                def validUsername = "Player123"
                def firstName = "Thomas"

                when:
                def createdUser = User.create(validUsername, firstName)

                then:
                createdUser.getFirstName() == firstName
        }

        def "A user should be able to not have a first name"() {
                given: "firstName that is null"
                def validUsername = "Player123"
                def firstName = null

                when:
                def createdUser = User.create(validUsername, firstName)

                then:
                createdUser.getFirstName() == firstName
        }

        def "A user should not be able to have a blank first name"() {
                given: "firstName that is blank"
                def validUsername = "Player123"
                def firstName = ""

                when:
                def createdUser = User.create(validUsername, firstName)

                then:
                def ex = thrown(IllegalArgumentException)
        }

        def "A user should not be able to have a first name shorter than 2 characters"() {
                given: "firstName that is blank"
                def validUsername = "Player123"
                def tooShortFirstName = "A"

                when:
                def createdUser = User.create(validUsername, tooShortFirstName)

                then:
                def ex = thrown(IllegalArgumentException)
        }

        def "A user should not be able to have a first name longer than 30 characters"() {
                given: "firstName that is blank"
                def validUsername = "Player123"
                def maxLength = 30
                def tooLongFirstName = "A" * (maxLength + 1)

                when:
                def createdUser = User.create(validUsername, tooLongFirstName)

                then:
                def ex = thrown(IllegalArgumentException)
        }

}
