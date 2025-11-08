package com.letmecook.backend

import spock.lang.Specification

class AppTest extends Specification {

def "adding two numbers should return the correct sum"() {
        expect:
        2 + 3 == 6
    }
}
