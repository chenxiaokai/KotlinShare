package my.day3

//1. 为List定义属性, 还有就是扩展属性不能初始化，因为没有幕后字段
val <T> List<T>.myLastIndex: Int
    get() = size -1

//2. 这是个伴生对象定义扩展函数
class Person {
    companion object {
        //内部定义方法回 覆盖外层定义的 扩展方法
        fun printName(){
            println("Person")
        }
    }
}

fun Person.Companion.printName() {
    println("Person.Companion")
}

//3. 扩展声明为成员
class Fish {
    fun say(){
        println("fish say")
    }

    fun breath() {
        println("fish breath")
    }
}

class Dog {
    fun eat() {
        println("dog eat")
    }

    fun breath() {
        println("dog breath")
    }

    fun Fish.sleep() {
        this.say()  //调用Fish的 say方法
        this@Dog.eat()  //调用Dog的 eat方法

        this.breath() //调用Fish的 breath方法
        this@Dog.breath() //调用Dog 的 breath方法
    }

    fun caller(d: Fish) {
        d.sleep()
    }
}

fun String.testFun(): String {
    return "abc123"
}

fun main(args:Array<String>) {
    //1. ---------------------------------------
//    var list = listOf("1","2","3")
//    println(list.myLastIndex)

    //2. -----------------------------------
//    Person.printName()

    //3. -----------------------------------
    var d = Dog()
    var f = Fish()
    d.caller(Fish())
}