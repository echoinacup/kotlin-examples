package com.echoinacup.service

import com.github.benmanes.caffeine.cache.Cache
import kotlinx.coroutines.*
import org.springframework.stereotype.Service

@Service
class DataServiceCoroutine(private val dataRepo: DataRepo, private val dataCache: Cache<String, DataDto>) {

    /*
    *   Job() is created explicitly to be able explicitly complete it and
    *   for possible monitoring purposes.
    *   E.g. 	•	Wait for completion (job.join())
    *           •	Cancel the job if needed (job.cancel())
    * */
    fun loadDataToCacheAsync(): CompletableJob {
        val job = Job()
        CoroutineScope(Dispatchers.IO + job).launch { // Dispatchers.IO is used here for db like calls.
            dataCache.putAll(dataRepo.fetchAll().associateBy { it.strVal })
            job.complete()
        }
        return job
    }

    fun loadDataToCacheAsyncVoid() {
        CoroutineScope(Dispatchers.IO).launch { // Dispatchers.IO is used here for db like calls.
            dataCache.putAll(dataRepo.fetchAll().associateBy { it.strVal })
        }
    }

    // could be more methods to clean it etc.
}