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


}
