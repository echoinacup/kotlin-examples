package com.echoinacup.service

import org.springframework.stereotype.Service

@Service
class DataRepo {

    fun fetchAll(): Set<DataDto> {
        return setOf(DataDto("one"), DataDto("two"))
    }
}