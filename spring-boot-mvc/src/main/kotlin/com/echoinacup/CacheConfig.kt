package com.echoinacup

import com.echoinacup.service.DataDto
import com.github.benmanes.caffeine.cache.Cache
import com.github.benmanes.caffeine.cache.Caffeine
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.TimeUnit

@Configuration
class CacheConfig {
    @Bean
    fun dataCache(): Cache<String, DataDto> {
        return Caffeine.newBuilder()
            .initialCapacity(5)
            .expireAfterWrite(1, TimeUnit.DAYS)
            .build()
    }
}