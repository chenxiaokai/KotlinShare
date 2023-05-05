package com.cxk.mylib

//参考：https://blog.csdn.net/qq_31339141/article/details/108411934
class Person {
    var instanceField: String = "instanceFieldValue"

    constructor()

    init {
        println("init block initialized...")
    }

    constructor(instanceField: String) {
        this.instanceField = instanceField
        println("constructor initialized...")
    }

    fun invokeMethod() {
        println("invokeMethod")
    }

    fun invokeMethodWithParam(param: String) {
        println("invokeMethodWithParam, param = $param")
    }

    companion object {
        var companionField: String = "companionFieldValue"

        init {
            println("companion init block initialized...")
        }

        fun invokeCompanionMethod() {
            println("invokeCompanionMethod")
        }

        fun invokeCompanionMethodWithParam(param: String) {
            println("invokeCompanionMethodWithParam, param = $param")
        }
    }
}

fun main(args:Array<String>) {
    println("-----------------------------------------------------")
    println(Person().javaClass) //Class 对象
    println("-----------------------------------------------------")
    println(Person()::class.java) //Class 对象
    println("-----------------------------------------------------")
    println(Person::class.java) //Class 对象


    println("-----------------------------------------------------")
    println(Person().javaClass.kotlin) // KClass 对象
    println("-----------------------------------------------------")
    println(Person::class) // KClass 对象
    println("-----------------------------------------------------")
    println(Class.forName("com.cxk.mylib.Person").kotlin) // KClass 对象

    val personClass = Class.forName("com.cxk.mylib.Person").kotlin
    val hasParamConstructor = personClass.constructors.find { it.parameters.size == 1 }
    val obj = hasParamConstructor?.call("cxkInitializeFieldValue")
    
}