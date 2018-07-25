package my.day1

/**
 * 1 使用关键字class声明类，在 Kotlin 中所有类都有一个共同的超类 Any，这对于没有超类型声明的类是默认超类
 */
class Invoice {

}

/**
 * 2 一个类可以用一个主构造函数 和 一个或者多个次构造函数，主构造函数constructor可以省略
 */
//如果一个非抽象类没有声明任何（主或次）构造函数，它会有一个生成的不带参数的主构造函数
class Person(val name: String) { //主构造函数

    init {
        //主构造函数，可以在init模块中使用，并且init 在次构造函数之前调用
        println("First initializer block that prints $name")
    }

    constructor(name: String, sex: String) : this(name){
        println("Second constructor block")
    }
}

/**
 * 3 构造函数默认是public的 可以用成private
 */
class DontCreateMe private constructor() {

}

/**
 * 5 类实例的继承,并研究类的初始化顺序
 */
open class Base(val name: String) {  //kotlin中所有要被继承的类，需要用open来标识，没有open标识的类就是final，不能被继承
    open fun v() {}   //open 标注的方法才能被子类覆盖
    fun nv() {}

    //open 标注的属性才能被子类覆盖
    open val size: Int = name.length.also {
        println("Initializing size int Base: $it")
    }

    init {
        println("Initializing Base")
    }
}
class Derived(name: String, val lastName: String) : Base(name.capitalize().also {
    println("Argument for Base: $it ")
    "hello".capitalize().also({it2->
        println("xxxxxxx  $it  $it2")
    })
}) {
    override fun v() {}

    init {
        println("Initializing Derived")
    }

    override val size: Int = (super.size + lastName.length).also {
        println("Initializing size in Derived: $it ")
    }
}



fun main(args: Array<String>) {
    /**
     * 4 类实例的构造
     */
//    val p: Person = Person("cxk","男")
//    val p2: Person = Person("cxk")

    val d: Derived = Derived("cxk","enen")
}