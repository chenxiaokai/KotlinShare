package com.cxk.mylib

/*
《Kotlin核心编程》 第6章

let函数：let函数是Kotlin中最常用的函数之一，在函数中可以使用it引用调用该函数的对象。它的作用是在代码块中使用it来执行一些非空性检查，并且避免了空指针异常。它的返回值是表达式的值，也就是执行lambda表达式后的结果
val name: String? = "Tom"
name?.let {
    println(it.length)
}

run函数：run函数是一个严格的作用域函数，可以访问调用函数对象的属性、方法。在函数内，this指向调用函数的对象，它的返回值是lambda表达式中的最后一个语句或者指定的返回值。
val name: String? = "Tom"
val length = name?.run {
    println(this.length)
    this.length
}

also函数：also函数和let函数类似，在函数内可以通过it引用调用该函数的对象，并且避免了空指针异常。不同的是，它的返回值是调用该函数的对象本身，而不是lambda表达式的结果
val name: String? = "Tom"
name?.also {
    println(it.length)
}

apply函数：apply函数是创建一个对象并初始化属性的最好方式之一，它的返回值是该对象本身。它内部使用this表示对象本身，在函数中可以调用对象的方法和属性进行初始化。
val user = User().apply {
    name = "Tom"
    age = 18
}

with函数：with函数是一种简化调用函数的方式，它接收一个对象和一个lambda表达式作为参数，使得在lambda表达式中可以直接操作该对象的属性和方法，而无需使用this关键字，也无需返回值。
val user = User()
with(user) {
    name = "Tom"
    age = 18
}
总体来讲，let和also都是用于针对对象执行某些操作，但返回值不同；run和with都是执行某些操作后返回结果，但调用方式和作用域不同；apply则更多用于创建对象并初始化属性。
----------------------------------------------------------------------------------------------------------------------------------------------------------------
val newList = list.map{it * 2}  //我们给集合中的每个元素都乘以2，最后得到了一个新的集合。

val newList = list.map {el -> el * 2}

fun foo(bar: Int) = bar * 2
val newList = list.map{foo(it)}
上面3种的map使用形式，都是一样的，都为list中每个元素 乘以2

----------------------------------------------------------------------------------------------------------------------------------------------------------------
data class Student (val name: String, val age: Int, val sex: String, val score: Int)
val jilen = Student("Jilen", 30, "m", 85)
val shaw = Student("Shaw", 18, "m", 90)
val yison = Student("Yison", 40, "f", 59)
val jack = Student("Jack", 30, "m", 70)
val lisa = Student("Lisa", 25, "f", 88)
val pan = Student("Pan", 36, "f", 55)
val students = listOf(jilen, shaw, yison, jack, lisa, pan)

val mStudents = students.filter {it.sex == "m"} //通过filter方法，筛选出性别为男的学生。
val fStudents = students.filterNot {it.sex == "m"} //筛选出性别不是男的学生， filterNotNull 用来过滤掉值为null的元素
val countMStudent = students.count {it.sex == "m"}  //统计除学生为男的学生

val scoreTotal = students.sumBy {it.score}  //求取所有学生的总分

//sum函数 只能对一些数值类型的列表求和
val a = listOf(1,2,3,4,5)
val b = listOf(1.1,2.5,3.0,4.5)
val aTotal = a.sum()
val bTotal = b.sum()

//fold接受两个参数，第1个参数initial通常称为初始值，第2个参数operation是一个函数。在实现的时候，通过for语句来遍历集合中的每个元素，每次都会调用operation函数，而该函数的参数有两个，
//一个是上一次调用该函数的结果（如果是第一次调用，则传入初始值initial），另外一个则是当前遍历到的结合元素
val scoreTotal = students.fold(0) {accumulator, student -> accumulator + student.score}  //遍历当前学生分数，并实现求和的操作

//reduce方法和fold非常相似，唯一的区别就是reduce方法没有初始值。所以默认的初始值是集合中的第1个元素
val scoreTotal = students.reduce {accumulator, student -> accumulator + student.score} //采用reduce求 学生集合中总分数

students.groupBy {it.sex} //返回数据结构类型是 Map<String, List<Student>>, 其中有两个分组，一个是性别男对应的分组，一个是性别女对应的分组

map函数，被很多人翻译为“遍历”，我觉得不太恰当。诚然，该函数的确会对调用函数的Collection每个元素进行遍历，但是其主要功能是对每个元素进行一个变换，然后返回变换后的新的Collection
val numbers = setOf(1,2,3)
println(numbers.map {it *3 })  //打印结果: [3,6,9]

flatten函数的功能其实就是“展开”，即将嵌套的Collection，展开为最底层的元素
注意这个函数应该只能用于直接嵌套的情况，亦即由Collection组成的Collection。而对于间接嵌套的情况，例如一个由对象构成的Collection，每个对象包含一个Collection类型的属性，则需要用到下面的flatMap函数。
val numberSets = listOf(setOf(1,2,3), setOf(4,5,6), setOf(1,2))
println(numberSets.flatten()) //打印结果：[1,2,3,4,5,6,1,2]

flatMap函数的词根是 map，而前缀才是 flat，因此是映射基础上的展开。实际上可以按照次序先后拆分为一个map函数和一个flatten函数。即先对调用的Collection进行映射，生成一个新的Collection，然后再对新生成的Collection的每个（Collection）元素进行展开。
data class Player(
    val name: String,
    val items: List<Item>
)
data class Item(
    val name: String,
    val damage: Int,
    val defense: Int
)
fun main(args:Array<String>) {
    val itemsList1 = listOf(Item("one剑",86,0), Item("oneone剑",102,0))
    val itemsList2 = listOf(Item("two剑",0,48), Item("twotwo剑",0,68))
    val player1 = Player("赵云",itemsList1)
    val player2 = Player("马超", itemsList2)
    val playerList = listOf(player1,player2)
    println(playerList.flatMap { it.items })
    //等价于 println(playerList.map { it.items }.flatten())
    //打印结果：[Item(name=one剑, damage=86, defense=0), Item(name=oneone剑, damage=102, defense=0), Item(name=two剑, damage=0, defense=48), Item(name=twotwo剑, damage=0, defense=68)]
}

val list = mutableListOf(1,2,3,4,5) //这个是可变集合，这里可以改变集合中的元素
list[0] = 0 //这个可以的
val list = listOf(1,2,3,4,5) //这个是只读集合，不能更改集合内容
list[0] = 0 //这样做了，就会抛出错误异常


val list = listOf(1,2,3,4,5)
list.filter {it > 2}.map {it * 2}  //但是，当list中的元素非常多的时候(比如超过10万)，上面的操作在处理集合的时候就会显得比较低效，因为list会先调用filter方法，然后产生的集合会再次调用map方法。如果list中的元素非常多，这将会是一笔不小的开销，为了解决这一问题，序列(Sequnce)就出现了

list.asSequence().filter {it > 2}.map {it * 2}.toList()



















































 */
data class Player(
    val name: String,
    val items: List<Item>
)
data class Item(
    val name: String,
    val damage: Int,
    val defense: Int
)
fun main(args:Array<String>) {
    val itemsList1 = listOf(Item("one剑",86,0), Item("oneone剑",102,0))
    val itemsList2 = listOf(Item("two剑",0,48), Item("twotwo剑",0,68))
    val player1 = Player("赵云",itemsList1)
    val player2 = Player("马超", itemsList2)
    val playerList = listOf(player1,player2)
    println(playerList.flatMap { it.items }) //[Item(name=one剑, damage=86, defense=0), Item(name=oneone剑, damage=102, defense=0), Item(name=two剑, damage=0, defense=48), Item(name=twotwo剑, damage=0, defense=68)]
}