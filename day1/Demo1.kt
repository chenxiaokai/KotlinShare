/**
 * 1 定义包名 package my.demo
 */
package my.day1

/**
 * 2 定义函数
 */
fun sum(a: Int,b: Int): Int {
    return a+b;
}

fun sum1(a: Int,b: Int) = a + b

/**
 * 3  Unit 返回类型可以省略， Unit表示无意义的值
 */
fun printSum(a:Int, b: Int): Unit {
    println("sum of $a and $b is ${a+b}")
}

/**
 * 5 使用字符串模板
 */
fun printStr(){
    var a = 1
    val s1 = "a is $a"
    a = 2

    val s2 = "${s1.replace("is","was")}, but now is $a"
    println(s2)
}


/**
 * 6 使用条件表达式
 */
fun maxOf(a:Int ,b:Int):Int {
    if(a > b){
        return a
    } else {
        return b
    }
}

fun maxOf2(a:Int,b:Int) = if(a > b) a else b

/**
 * 7 使用is 运算符检测一个表达式是否某类型的一个实例
 *
 *   Any 是基类，相当于java中的 Object
 *   Int? 表示函数可以返回为null
 */
fun getStringLength(obj: Any): Int? {
    if (obj is String) {
        return obj.length
    }
    return null
}

/**
 *  8 使用for循环
 */
fun useForCircle(){
    //创建一个List集合
    val items = listOf("apple","banana","kiwifruit")
    for (item in items) {
        println(item)
    }

    for (index in items.indices) {
        println("item at $index is ${items[index]}")
    }
}

/**
 * 9 使用while 循环
 */
fun useWhileCircle(){
    val items = listOf<String>("apple","banana","kiwifruit")
    var index = 0
    while (index < items.size) {
        println("item at $index is ${items[index]}")
        index++
    }
}

/**
 * 10 使用when 表达式 有点像 switch
 */
fun useWhen(obj: Any): String {
    val items = listOf<String>("juicy","apple","banana")
    return when(obj) {
        1   -> "one"
        "Hello" -> "Greeting"
        is Long -> "Long"
        !is String -> "Not a String"
        //判断字符串 是否在 items 集合中
        "orange" in items -> "juicy"
        "apple" in items -> "apple is fine too"
        else -> "Unknown"
    }
}

/**
 *  11 使用区娟 range
 */
fun useRange(){
    //使用in 运算符来检测某个数字是否在制定区间内
    val x = 10
    val y = 9
    if (x in 1..y+1) {  // 闭区间 [1,10]
        println("fits in range")
    }

    //检测某个数字是否在指定区间外
    val list = listOf<String>("a","b","c")
    if (-1 !in 0..list.lastIndex) {
        println("-1 is out of range")
    }
    if (list.size !in list.indices) {
        println("list size is out of valid list indices rnage too")
    }

    //迭代区间
    for (x in 1..5) {
        print(x)
    }

    println()
    //数列迭代
    for (x in 1..10 step 2) {
        print(x)
    }
    println()
    for (x in 9 downTo 0 step 3) {
        print(x)
    }
}

fun main(args: Array<String>){
    /**
     * 4 定义只读的局部变量
     */
    val a: Int = 1  // 立即赋值
    val b = 2   //自动推断出 Int 类型
    val c: Int    //如果没有初始值类型不能省略Int,否则编译报错
    c = 3   //明确赋值

    //定义可变变量
    var x = 5  //自动推断出 Int 类型
    x += 1

    println(sum(1,2))
//    printSum(1,2)
//    printStr()
//    println(maxOf(2,3))
//    println(getStringLength("abc"))
//    useForCircle()
//    useWhileCircle()
//    println(useWhen(1))
//    useRange()
//    println("Hello World")
}