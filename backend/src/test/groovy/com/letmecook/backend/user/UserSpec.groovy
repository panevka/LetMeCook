package com.letmecook.backend.user;

import com.letmecook.backend.user.User;
import static com.letmecook.backend.user.UserUtils.sampleNewUser;
import spock.lang.Specification

class UserSpec extends Specification {

        def "A user must always have a username"() {
                when:
                sampleNewUser(username: null)

                then:
                thrown(IllegalArgumentException)
        }

        def "Cannot create a user with empty username"() {
                when:
                sampleNewUser(username: "")

                then:
                thrown(IllegalArgumentException)
        }

        def "Username has to be at least 3 characters long"() {
                when:
                sampleNewUser(username: "AA")

                then:
                thrown(IllegalArgumentException)
        }

        def "Username cannot be above 30 characters"() {
                given: "username above 30 chars"
                def maxLength = 30
                def tooLongUsername = "a" * maxLength + 1

                when:
                sampleNewUser(username: tooLongUsername)

                then:
                thrown(IllegalArgumentException)
        }

        def "should accept a valid username"() {
                given: "a username that meets all rules"
                def validUsername = "player123"

                when:
                def createdUser = sampleNewUser(username: validUsername)

                then:
                createdUser.getUsername() == validUsername
        }

        def "A user should be able to have a first name"() {
                given: "firstName that meets all rules"
                def validFirstName = "Thomas"

                when:
                def createdUser = sampleNewUser(firstName: validFirstName)

                then:
                createdUser.getFirstName() == validFirstName
        }

        def "A user should be able to not have a first name"() {
                when:
                def createdUser = sampleNewUser(firstName: null)

                then:
                createdUser.getFirstName() == null
        }

        def "A user should not be able to have a blank first name"() {
                when:
                sampleNewUser(firstName: "")

                then:
                thrown(IllegalArgumentException)
        }

        def "A user should not be able to have a first name shorter than 2 characters"() {
                when:
                sampleNewUser(firstName: "A")

                then:
                thrown(IllegalArgumentException)
        }

        def "A user should not be able to have a first name longer than 30 characters"() {
                given: "firstName that is too long"
                def maxLength = 30
                def tooLongFirstName = "A" * (maxLength + 1)

                when:
                sampleNewUser(firstName: tooLongFirstName)

                then:
                thrown(IllegalArgumentException)
        }

        def "A user should be able to have a last name"() {
                given: "lastName that meets all rules"
                def validLastName = "Smith"

                when:
                def createdUser = sampleNewUser(lastName: validLastName)

                then:
                createdUser.getLastName() == validLastName
        }

        def "A user should be able to not have a last name"() {
            when:
            sampleNewUser(lastName: null)

            then:
            noExceptionThrown()
        }

        def "A user should not be able to have a blank last name"() {
                when:
                sampleNewUser(lastName: "")

                then:
                thrown(IllegalArgumentException)
        }

        def "A user should not be able to have a last name shorter than 2 characters"() {
                given: "lastName that is too short"
                def tooShortLastName = "A"

                when:
                sampleNewUser(lastName: tooShortLastName)

                then:
                thrown(IllegalArgumentException)
        }

        def "A user should not be able to have a last name longer than 30 characters"() {
                given: "last name that is too long"
                def maxLength = 30
                def tooLongLastName = "A" * (maxLength + 1)

                when:
                sampleNewUser(lastName: tooLongLastName)

                then:
                thrown(IllegalArgumentException)
        }

        def "A user should be able to have null avatarUrl"() {
                when:
                sampleNewUser(avatarUrl: null)

                then:
                noExceptionThrown()
        }

        def "A user should be able to have null avatarUrl"() {
                when:
                sampleNewUser(avatarUrl: "")

                then:
                thrown(IllegalArgumentException)
        }

}
