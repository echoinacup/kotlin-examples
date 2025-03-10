package com.echoinacup

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ObjectRelatedOperationsKtTest {
    private lateinit var testedObj: ObjectWithFields

    @BeforeEach
    fun init(){
        this.testedObj =  ObjectWithFields(field1 = "one", field2 = "two")
    }

    @Test
    fun testDoSomethingToNullableObject() {
        assertThat(doSomethingToNullableObject(null)).isNull()
        assertThat(doSomethingToNullableObject(testedObj))
            .extracting("field1", "field2").isEqualTo(listOf("updatedFieldOne", "two"))
    }

    @Test
    fun testDoSomethingToNullableObjectReturningDefault() {
        assertThat(doSomethingToNullableObjectReturningDefault())
            .extracting("field1", "field2").isEqualTo(listOf("default1", "default2"))
        assertThat(doSomethingToNullableObjectReturningDefault(testedObj))
            .extracting("field1", "field2").isEqualTo(listOf("updatedFieldOne", "two"))
    }
}