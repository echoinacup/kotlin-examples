package com.echoinacup.mix

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class SpringBootLanguagesMixApp

fun main(args: Array<String>) {
    SpringApplication.run(SpringBootLanguagesMixApp::class.java, *args)
}