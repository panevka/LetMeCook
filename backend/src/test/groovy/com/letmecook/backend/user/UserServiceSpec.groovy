package com.letmecook.backend.user

import spock.lang.Specification
import static com.letmecook.backend.user.UserUtils.sampleNewUser;

class UserServiceSpec extends Specification {

    def userRepository = new InMemoryUserRepository()
    def userService = new UserService(userRepository)

    def "user service creates a user" () {
        given:
        def user = sampleNewUser(username: "testuser")

        when:
        def createdUser = userService.createUser(user)

        then:
        createdUser != null
    }

    def "user service must allow retrieval of created user"() {
        given:
        def user = sampleNewUser()

        when:
        def createdUser = userService.createUser(user)
        def retrievedUser = userService.getUserById(createdUser.getId())

        then:
        createdUser == retrievedUser
    }

    def "user service must allow retrieval of multiple created users"() {
        given: "two new users"
        def u1 = sampleNewUser()
        def u2 = sampleNewUser()

        when: "create and retrieve both users"
        def createdUser = userService.createUser(u1)
        def retrievedUser = userService.getUserById(createdUser.getId())

        def anotherCreatedUser = userService.createUser(u2)
        def anotherRetrievedUser = userService.getUserById(anotherCreatedUser.getId())

        then: "both users are correctly retrieved"
        createdUser == retrievedUser
        anotherCreatedUser == anotherRetrievedUser
    }

}
