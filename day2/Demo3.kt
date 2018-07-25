package my.day2

//fun MutableList<Int>.swap(index1:Int,index2:Int) {
//    //这个this关键字 对应到接受者对象(传过来的在点符号钱的对象)
//    val tmp = this[index1]
//    this[index1] = this[index2]
//    this[index2] = tmp
//}

//1. 给MutableList 定义一个扩展函数
fun <T> MutableList<T>.swap(index1:Int, index2:Int) {
    val tmp = this[index1]
    this[index1] = this[index2]
    this[index2] = tmp
}

/*
  2. 扩展函数是静态的不能进行动态绑定
  3. 如果有内部函数和扩展函数，优先调用内部函数
 */
open class C {
    fun funC(){
        println("member function")
    }
}
class D: C()

//C类定义一个扩展函数
fun C.foo():String {
  return "c"
}
fun C.funC() {
    println("extension function")
}

fun C.funC(name:String){

}



//D类定义一个扩展函数
fun D.foo() = "d"

fun printFoo(c: C) {
    println(c.foo())
}


fun main(args:Array<String>) {
    val l = mutableListOf(1,2,3)
    l.swap(0,2)
    println(l)

    val l2 = mutableListOf("1","2","3")
    l2.swap(0,2)
    println(l2)

    var c = C()
//    //如果一个类定义一个成员函数 同时定义一个扩展函数，这种情况总是取成员函数
    c.funC()
    var d = D()
    printFoo(d)
//    printFoo(d)
}