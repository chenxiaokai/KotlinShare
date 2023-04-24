package com.cxk.mylib

//《Kotlin核心编程》 第5章 类型系统
/*
kotlin中表示可空类型，可以在任何类型后面加上"?"，比如 Int? 实际上等同于 Int可能为null

//Kotlin 安全的调用 ?. , 以下是java调用代码
if (seat.student != null) {
    if (seat.student.glasses != null) {
        System.out.println("该位置上学生眼镜度数："+seat.student.glassess.degreeOfMyopia)
    }
}

//Kotlin调用代码 等同于上面 java调用代码 如下：
println("该位置上学生眼镜度数: ${s.student?.glasses?.degreeOfMyopia}")

//java中的三目运算符
double result = student.glasses != null ? student.glasses.degreeOfMyopia : -1
//Kotlin中也有类似的运算符
val result = student.glasses?.degreeOfMyopia ?: -1

val result = student!!.glasses  //当这个学生不戴眼镜时，程序就会抛出NPE的异常

public inline fun<T, R> T.let(block: (T) -> R): R=block(this)
功能：调用某对象的let函数，该对象会作为函数的参数，在函数块内可以通过it指代该对象。返回值为函数块的最后一行或指定return表达式

在java中，一般使用 A instanceof T 来判断A是T 或者 T的子类的一个实例。 在Kotlin中，我们可以用 “is” 来判断。

Kotlin中将一个变量的类型转变为另一种类型，它是隐式完成的。
val stu: Any = Student(Glasses(189.00))
if (stu is Student) println(stu.glasses)

var stu: Student? = getStu() as? Student // 这时如果stu为空将不会抛出异常，而是返回转换结果null

Any类型是Kotlin中所有非空类型(如String，Int)的超类
Any? 才是所有类型(可空和非空类型) 的根类。 Any? 是 Any 的父类型，而且是所有类型的根类型。

Nothing是没有实例的类型。Nothing类型的表达式不会产生任何值。需要注意的是，任何返回值为Nothing的表达式之后的语句都是无法执行的。你是不是感觉有点像return或者break的作用？
没错，Kotlin中 return、throw等(流程控制中 与 跳转相关的表达式) 返回值都为Nothing

Nothing? 是 Nothing的父类型。 Kotlin类型层级结构的最底层是Nothing类型。

//Kotlin中定义数组类型
val funList = arrayOf<T>(n1, n2, n3...,nt)  //T我们可以手动指定数组类型，还可以使用 IntArray、CharArray、ShortArray等












 */