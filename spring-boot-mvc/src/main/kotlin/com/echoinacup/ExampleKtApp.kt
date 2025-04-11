package com.echoinacup

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient


@SpringBootApplication
@EnableDiscoveryClient
class ExampleKtApp

fun main(args: Array<String>) {
    SpringApplication.run(ExampleKtApp::class.java, *args)
}
