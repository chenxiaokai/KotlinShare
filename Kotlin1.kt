package com.cxk.mylib

/*
Kotlin核心编程 第2章 基础语法
因此，1. val声明的变量是只读变量，它的引用不可更改，但并不代表其引用对象也不可变
 */
class Book(var name: String) { //用var声明的参数name引用可被改变
    fun printName() {
        println(this.name)
    }
}

/*
    2. 优先使用val来声明变量,来防止副作用
 */
var a = 1
fun count(x: Int) {
    a = a + 1
    println(x + a)
}

/*
    3. 我们可以在一个函数内部定义一个局部函数
    foo(1) //运行结果2
 */
fun foo(x: Int) {
    fun double(y: Int): Int {
        return y*2
    }
    println(double(x))
}

/*
    4. 函数的类型： 1. 通过->符号来组织参数类型和返回值类型，左边是参数类型，右边是返回值类型；2. 必须用一个括号来包裹参数类型； 3. 返回值类型即使是Unit，也必须显式声明。
        () -> Unit  //没有参数的函数类型
        (Int, String) -> Unit  //多个参数用逗号来进行分隔
        (errCode: Int, errMsg: String) -> Unit   //支持为声明参数指定名字
        (errCode: Int, errMsg: String?) -> Unit  //如果errMsg在某种情况下为空
        ((errCode: Int, errMsg: String?) -> Unit)?  //该函数类型的变量也是可选的话，我们还可以把整个函数类型变成可选
        (Int) -> ((Int) -> Unit)  //这表⽰传⼊⼀个类型为Int的参数，然后返回另⼀个类型为（Int）->Unit的函数，我们可以把后半部分的括号给省略：(Int) -> Int -> Unit
        ((Int) -> Int) -> Unit  //它表示的是传入一个函数类型的参数，再返回一个Unit

 */

/*
    5. 方法和成员引用
        我们有一个CountryTest类的对象实例countryTest，引用它的isBigEuropeanCountry方法： countryTest::isBigEuropeanCountry

        class Book(val name: String)
        fun main(args: Array<String>) {
            val getBook = ::Book  //getBook的类型为 (name: String) -> Book
            println(getBook("Dive into Kotlin").name)
        }

        Book::name //引用Book中的成员name变量
 */

/*
    6. 匿名函数
        fun(country: Country):Boolean { //没有函数名字
            return country.continient == "EU" && country.population >10000
        }
 */

/*
    7. Lambda表达式
        val sum:(Int, Int) -> Int = {x:Int, y:Int -> x + y}
        val sum = {x:Int, y:Int -> x + y}
        val sum:(Int,Int) -> Int = {x , y -> x + y}  //以上3个lambda表达式都是一样的

        Lambda语法总结：
            1. 一个Lambda表达式必须通过{}来包裹
            2. 如果Lambda声明了参数部分的类型，且返回值类型支持类型推导，那么Lambda变量就可以省略函数类型声明
                例如：val sum = {x:Int, y:Int -> x + y}
            3. 如果Lambda变量声明了函数类型，那么Lambda的参数部分类型可以省略
                例如：val sum:(Int,Int) -> Int = {x , y -> x + y}
            4. 如果Lambda表达式返回的不是Unit，那么默认最后一行表达式的值类型就是返回值类型
                例如：val foo = {x:Int ->
                        val y = x + 1
                        y  //返回值是y
                     }

 */

/*
    8. 高阶函数(函数返回类型是 函数)
        fun sum(x:Int, y:Int, z:Int) = x + y + z
        调用：sum(1,2,3) 改成高阶函数

        fun sum(x: Int) = { y: Int ->
            {z:Int -> x + y + z}
        }
        sum(1)(2)(3)


       如果一个函数只有一个参数，且该参数为函数类型，那么在调佣该函数时，外面的括号就可以省略
       fun omitParentheses(block:() -> Unit) {
            block()
       }
       omitParentheses {
            println("parentheses is omitted")
       }

       如果参数不止一个，且最后一个参数为函数类型时，就可以采用类似柯里化风格的调用：
       fun curryingLike(content: String, block:(String) -> Unit) {
            block(content)
       }
       curryintLike("looks like currying style"){
            content ->
            println(content)
       }  //运行结果：looks like currying style
 */

/*
    9. when 表达式具体语法
        fun foo(a: Int) = when(a) {
            1 -> 1
            2 -> 2
            else -> 0
        }

        foo(1)  //打印 1
 */

/*
    10. 函数可变参数
        Kotlin通过vararg关键字来定义函数中的可变参数。需要注意的是，Java中的可变参数必须是最后一个参数，Kotlin中没有这个限制。

        fun printLetters(vararg letters: String, count: Int):Unit {
            print("${count} letters are ")
            for (letter in letters) print(letter)
            println()
        }

        printLetters("a","b","c", count = 3) // 打印： 3 letters are abc

        我们可以使用*(星号) 来传入外部的变量作为可变参数的变量
        val letters = arrayOf("a","b","c")
        printLetters(*letters, count = 3) // 打印：3 letters are abc
 */


fun main(args: Array<String>) {
    val book = Book("Thinking in java") //用val声明的book对象的引用不可更改
    book.name = "Diving into Kotlin"
    book.printName() //Diviing into Kotlin

    //多次调用count(1)得到的结果并不相同，显然这是受到了外部变量a的影响，这个就是典型的副作用
    count(1) //3
    count(1) //4
    //因为引用不可变，val声明的变量只能被赋值一次，且在声明时不能省略变量类型
    val a: Int
    a = 1
    println(a) // 运行结果为1

    val bookNames = listOf(
        Book("Thinking in Java"),
        Book("Dive into Kotlin")
    ).map(Book::name)
    println(bookNames)  //打印结果 [Thinking in Java, Dive into Kotlin]

    //Kotlin可以用 “?” 来表示一种类型的可空性，我们可以用 “?:” 来给一种可空类型的变量指定为空情况下得值
    val maybeInt: Int? = null
    println(maybeInt ?: 1)  //打印结果： 1
}