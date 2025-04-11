package com.echoinacup

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/examples")
class ExampleController {

    @GetMapping
    fun getExamples(): ResponseEntity<Any> {
        return ResponseEntity.ok(listOf("one", "two"))
    }
}