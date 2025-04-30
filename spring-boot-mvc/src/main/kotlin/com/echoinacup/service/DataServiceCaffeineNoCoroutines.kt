package com.echoinacup.service

import com.github.benmanes.caffeine.cache.Cache
import org.springframework.stereotype.Service
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Service
class DataServiceCaffeineNoCoroutines(private val dataRepo: DataRepo, private val dataCache: Cache<String, DataDto>) {
    private val executorService: ExecutorService = Executors.newCachedThreadPool()

    fun loadDataToCacheAsync(): CompletableFuture<Unit> {
        return CompletableFuture.supplyAsync({
            // Only populate cache if the first key is missing
            println("🔄 Cache is empty. Loading all data into cache.")
            val allData = dataRepo.fetchAll()

            dataCache.putAll(allData.associateBy { it.strVal })
        }, executorService)
    }
}