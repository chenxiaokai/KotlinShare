package my.day3

/*
    密封类用来表示受限的类继承结构：当一个值为有限集中的类型、而不能有任何其他类型时。在某种意义上，
    他们是枚举类的扩展：枚举类型的值集合也是受限的，但每个枚举常量只存在一个实例，而密封类的一个子类可以有可包含状态的多个实例。
 */
sealed class Expr
data class Const(val number: Double) : Expr()
data class Sum(val e1: Expr, val e2: Expr) : Expr()
object NotANumber : Expr()

//1. 密封类的关键好处在于使用 when表达式 ，如果能够验证语句覆盖了所有情况，就不需要为该语句再添加一个else子句了
fun eval(expr: Expr): Double = when(expr) {
    is Const -> expr.number
    is Sum -> eval(expr.e1) + eval(expr.e2)
    NotANumber -> Double.NaN
}


//2. 内部类
class Outer {
    private val bar: Int = 1

    //标记为inner 的内部类，才可以访问外部变量
    inner class Nested {
        fun foo() = 2
        fun foo1() = bar
    }
}

//3. 对象表达式
fun testFun() {
    //创建一个匿名对象
    val adHoc = object {
        var x: Int = 1
        var y: Int = 2
    }
    println(adHoc.x + adHoc.y)
}

fun main(args:Array<String>) {

    println("test".testFun())

    //sealed 封闭类有点像，java的枚举变量
    println(eval(Sum(Const(1.2),Const(2.3))))

    //访问内部类中的函数方法
    val demo = Outer().Nested().foo()
    println("demo = "+demo)


    testFun()
}