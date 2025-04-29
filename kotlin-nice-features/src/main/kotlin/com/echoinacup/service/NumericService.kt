package com.echoinacup.service

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

class NumericService {
    companion object {
        private val logger: Logger = LogManager.getLogger(NumericService::class.java)
    }

    fun parseStringToLong(longStr: String): Long {
        try {
            logger.info("parsing potential long string to Long")
            return longStr.toLong()
        } catch (nfe: NumberFormatException) {
            throw CustomNumericException("Please validate logs, the value is incorrect: $longStr", nfe)
        }
    }
}