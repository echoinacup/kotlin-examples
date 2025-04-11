package com.echoinacup

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class GatewayApp

fun main(args: Array<String>) {
    SpringApplication.run(GatewayApp::class.java, *args)
}