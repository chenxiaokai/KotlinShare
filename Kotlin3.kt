package com.cxk.mylib

//《Kotlin核心编程》 第四章 代数数据类型 和 模式匹配
/*
封闭类也是 Kotlin 的一个语法糖。可以把它理解为枚举的扩展。一个封闭类，前面用sealed关键字标识。可以有任意多个子类或对象。封闭类的值只能是这些子类或对象。使用封闭类的好处主要是 when 表达式，不需要再使用 else 形式了
sealed class Expr
data class Const(val number: Double) : Expr()
data class Sum(val e1:Expr, val e2:Expr) : Expr()
object NotANumber : Expr()

fun eval(expr: Expr): Double = when(expr) {
    is Const -> expr.number
    is Sum -> eval(expr.e1) + eval(expr.e2)
    NotANumber -> Double.NaN
    //这里并不要求使用else子句匹配其他所有的情况
}
一个密封类是自身抽象的，它不能直接实例化并可以有抽象（abstract）成员。密封类不允许有非-private 构造函数（其构造函数默认为 private）。请注意，扩展密封类子类的类（间接继承者）可以放在任何位置，而无需在同一个文件中。使用密封类的关键好处在于使用 when 表达式


































 */
