package my.day2

//1 定义接口 关键字 interface声明接口
interface MyInterface {
    //接口中的属性不能初始化
    val firstName: String

    fun bar() {
        println("MyInterface")
    }
    fun foo()
}

interface MyInterface2 {
    var lastName: String

    fun bar() {
        println("MyInterface2")
    }
    fun foo1()
}


class Child : MyInterface, MyInterface2 {
    override val firstName: String
        get() = "hello"

    //这里要用内部变量，不是会出现递归调用，导致堆栈溢出
    override var lastName: String = "world"
        get() = field
        set(value) {
            field = "前缀+ $value"
        }

    override fun bar() {
        //区分调用不通的父类的bar()方法
        super<MyInterface>.bar()
        super<MyInterface2>.bar()
    }

    override fun foo1() {

    }

    override fun foo() {

    }

}

fun main(args:Array<String>) {
    var child: Child = Child()

    child.bar()


    println(child.firstName)
    println(child.lastName)
    child.lastName = "world"
    println(child.lastName)
}