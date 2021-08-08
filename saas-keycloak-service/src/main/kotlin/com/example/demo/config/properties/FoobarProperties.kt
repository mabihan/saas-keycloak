package com.example.demo.config.properties

import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
class FoobarProperties {
    var rating: Int = 5
        set(value) {
            field = when {
                value > 10 -> 10
                value < 0 -> 0
                else -> value
            }
        }
}
