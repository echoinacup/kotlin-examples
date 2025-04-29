package com.echoinacup.service

import java.lang.RuntimeException

class CustomNumericException(msg: String, cause: Throwable) : RuntimeException(msg, cause)