package com.echoinacup


class ObjectWithFields(var field1: String, var field2: String)

/*
* We can call let on an object and do something is it is nullable or else it will return null
* Handy instead of if else.
* */
fun doSomethingToNullableObject(objectWithFields: ObjectWithFields?): ObjectWithFields? {
    return objectWithFields?.let {
        println("Fields: ${it.field1} and ${it.field2}")
        it.field1 = "updatedFieldOne"
        return objectWithFields
    }
}

/*
* Using .let and Elvis operator with default fallback on something we want instead of null.
* */
fun doSomethingToNullableObjectReturningDefault(objectWithFields: ObjectWithFields? = null): ObjectWithFields {
    return objectWithFields?.let {
        println("Fields: ${it.field1} and ${it.field2}")
        it.field1 = "updatedFieldOne"
        return objectWithFields
    } ?: ObjectWithFields("default1", "default2")
}