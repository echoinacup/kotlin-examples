package com.echoinacup.service

import org.springframework.stereotype.Service

@Service
class ProcessingService(private val dataServiceCoroutine: DataServiceCoroutine) {


    fun process() {
        dataServiceCoroutine.loadDataToCacheAsync() // this one will go using coroutines async way.

        //todo the main work
    }
}