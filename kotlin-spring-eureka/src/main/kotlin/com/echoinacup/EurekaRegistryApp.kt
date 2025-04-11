package com.echoinacup

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer

@SpringBootApplication
@EnableEurekaServer
class EurekaRegistryApp

fun main(args: Array<String>) {
    SpringApplication.run(EurekaRegistryApp::class.java, *args)
}