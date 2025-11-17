package com.letmecook.backend.user

import com.letmecook.backend.user.User;
import spock.lang.Specification

class UserUtils extends Specification {

    public static def getValidUser(Map<String, Object> properties = [:]) {

        def SAMPLE_VALID_USER = [username:"ValidUsername", firstName:"John", lastName:"Doe", avatarUrl:"http://example.com/avatar.png"]

        properties = SAMPLE_VALID_USER + properties

        return new User().builder()
                .username(properties.username as String)
                .firstName(properties.firstName as String)
                .lastName(properties.lastName as String)
                .avatarUrl(properties.avatarUrl as String)
                .build()
    }

}


