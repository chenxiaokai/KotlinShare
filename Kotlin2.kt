package com.cxk.mylib

/*
//Kotlin 中的接口
interface Flyer {
    //为接口中变量 height 赋值
    val height
        get() = 100
    val speed: Int
    fun kind()
    fun fly() {
        println("I can fly")
    }
}

------------------------------------------------------------------------------------------------------------------------------------

第1种类的定义方式
class Bird(val weight: Double = 0.00, val age: Int = 0, val color: String = "blue") {
    fun fly() {}  //全局可见
}

第2种类的定义方式
class Bird {
    /*
        Kotlin中，除⾮显式地声明延迟初始化，不然就需要指定属性的默认值
        Kotlin类中的成员默认是全局可见
     */
    val weight: Double = 500.0
    val color: String = "blue"
    val age: Int = 1
    fun fly() {}  //全局可见
}

第3种类的定义方式
class Bird(weight: Double = 0.00, age: Int = 0, color: String = "blue") {
    val weight: Double
    val color: String
    val age: Int
    fun fly() {}  //全局可见

    //Bird类的构造⽅法在类的外部，它只能对参数进⾏赋值。如果我们需要在初始化时进⾏其他的额外操作，那么我们就可以使⽤init语句块来执⾏
    init {
        this.weight = weight
        this.age = age
        this.color = color
    }
}

第4种类的定义方式
class Bird(weight: Double = 0.00, age: Int = 0, color: String = "blue") {
    val weight: Double = weight
    val color: String = color
    val age: Int = age
    fun fly() {}  //全局可见
}

上面4种Bird 类的定义方式是一样的

------------------------------------------------------------------------------------------------------------------------------------

class Bird(weight: Double = 0.00, age: Int = 0, color: String = "blue") {
    val weight: Double
    val color: String
    val age: Int
    fun fly() {}  //全局可见

    //我们的构造方法还可以拥有多个init，他们会在对象被创建时按照类中 从上到下 的顺序先后执行
    init {
        this.weight = weight
        println("The bird's weight is ${this.weight}.")
        this.age = age
        println("The bird's age is ${this.age}.")
    }
    init {
        this.color = color
        println("The bird's color is ${this.color}.")
    }
}


    打印结果：
        The bird's weight is 1000.0.
        The bird's age is 2.
        The bird's color is blue.

    val bird2 = Bird(1000.0, 2, "blue")

------------------------------------------------------------------------------------------------------------------------------------

class Bird(val weight: Double, val age: Int, val color: String) {
    val sex: String  //Error: sex Property must be initialized or be abstract
    fun printSex() {
        //Error: Val sex cannot be reassigned
        this.sex = if (this.color == "yellow") "male" else "female"
        println(this.sex)
    }
}
fun main(args: Array<String>) {
    val bird = Bird(1000.0, 2, "bule")
    bird.printSex()
}

1. 正常情况下，Kotlin规定类中的所有⾮抽象属性成员都必须在对象创建时被初始化值
2. 由于sex必须被初始化值，上述的printSex⽅法中，sex会被视为⼆次赋值，这对val声明的变量来说也是不允许的


延迟初始化： by lazy 和 lateinit 即它可以不用在类对象初始化的时候就必须有值


如果用val声明的变量，我们可以用 by lazy来修饰
class Bird(val weight: Double, val age: Int, val color: String) {
    val sex: String by lazy {
        if (this.color == "yellow") "male" else "female"
    }
}
by lazy语法特点：
1. 该变量必须是引用不可变得，而不能通过var来声明
2. 在被首次调佣时，才会进行赋值操作。一旦被赋值，后续它将不能被更改

与lazy不同，lateinit主要⽤于var声明的变量，然⽽它不能⽤于基本数据类型，如Int、Long等，我们需要⽤Integer这种包装类作为替代
class Bird(val weight: Double, val age: Int, val color: String) {
    lateinit var sex: String // sex 可以延迟初始化
    fun printSex() {
        this.sex = if (this.color == "yellow") "male" else "female"
        println(this.sex)
    }
}
fun main(args: Array<String>) {
    val bird = Bird(1000.0, 2, "bule")
    bird.printSex()
}
// 运⾏结果: female


//如果主构造方法存在注解或可见性修饰符，也必须像从构造方法一样加上constructor关键字
internal public Bird @inject constructor(age: Int) {
    val age: Int
    init {
        this.age = age
    }
    //这个是 从构造函数
    constructor(birth: DateTime): this(getAgeByBirth(birth)) {

    }
}
------------------------------------------------------------------------------------------------------------------------------------
open class Bird {
    open fun fly() {
        println("I can fly")
    }
}

class Penguin : Bird() {
    override fun fly() {
        println("I can't fly actually")
    }
}

1. Kotlin中使用":" 来代替类的继承和接口实现
2. Kotlin中类和方法默认是不可被继承或重写的，所以必须加上open修饰符


//Kotlin通过sealed关键字来修饰⼀个类为密封类，若要继承则需要将⼦类定义在同⼀个⽂件中，其他⽂件中的类将⽆法继承它。但这种⽅式有它的局限性，即它不能被初始化，因为它背后是基于⼀个抽象类实现的
//密封类的使⽤场景有限，它其实可以看成⼀种功能更强⼤的枚举，所以它在模式匹配中可以起到很⼤的作⽤。
sealed class Bird {
    open fun fly() = "I can fly"
    class Eagle: Bird()
}


internal在Kotlin中的作⽤域可以被称作“ 模块内访问” 。那么到底什么算是模块呢？以下⼏种情况可以算作⼀个模块：
    - 一个Eclipse项目；一个Intellij IDEA项目；一个Maven项目；一个Grandle项目；一组由一次Ant任务执行编译的代码

------------------------------------------------------------------------------------------------------------------------------------

//实现多继承
interface Flyer {
    fun fly()
    fun kind() = "flying animals"
}
interface Animal {
    val name: String
    fun eat()
    fun kind() = "flying animals"
}
class Bird(override val name: String) : Flyer, Animal {
    override fun fly() {
        println("I can fly")
    }

    override fun eat() {
        println("I can eat")
    }

    override fun kind() = super<Flyer>.kind() //利用super来指定继承哪个父接口的方法
    // override fun kind() = "a flying ${this.name}"  //我们可以主动实现，覆盖父接口的方法
}
fun main(args: Array<String>) {
    val bird = Bird("sparrow")
    println(bird.kind()) //运行结果：flying animals
}

------------------------------------------------------------------------------------------------------------------------------------
//Kotlin中 默认是一个嵌套类，必须加上inner关键字才是一个内部类。
    1. 内部类包含着对其外部类实例的引⽤，在内部类中我们可以使⽤外部类中的属性，⽐如上⾯例⼦中的name属性；⽽嵌套类不包含对其外部类实例的引⽤，所以它⽆法调⽤其外部类的属性。
class OuterKotlin {
    val name = "This is truely Kotlin's inner class syntax."
    //如果要在Kotlin中声明一个内部类，我们必须在这个类前面加一个inner关键字, inner不能定义内部变量
    inner class InnerKotlin {
        fun printName() {
            print("the name is $name")
        }
    }
}

class OuterKotlin {
    val name = "This is not Kotlin's inner class syntax."
    class ErrorInnerKotlin { // 其实是嵌套类
        fun printName() {
            print("the name is $name") //Error:(5,32) Unresolved reference: name
        }
    }
}


open class Horse { //马
    fun runFast() {
        println("I can run fast")
    }
}
open class Donkey { //驴
    fun doLongTimeThing() {
        println("I can do some thing long time")
    }
}
class Mule { //骡⼦
    fun runFast() {
        HorseC().runFast()
    }
    fun doLongTimeThing() {
        DonkeyC().doLongTimeThing()
    }
    private inner class HorseC : Horse()
    private inner class DonkeyC : Donkey()
}
//inner不能定义内部变量
1. 我们可以在⼀个类内部定义多个内部类，每个内部类的实例都有⾃⼰的独⽴状态，它们与外部对象的信息相互独⽴
2. 通过让内部类HorseC、DonkeyC分别继承Horse和Donkey这两个外部类，我们就可以在Mule类中定义它们的实例对象，从⽽获得了Horse和Donkey两者不同的状态和⾏为
3. 我们可以利⽤private修饰内部类，使得其他类都不能访问内部类，具有⾮常良好的封装性

//通过委托来代替多继承实现需求
interface CanFly {
    fun fly()
}
interface CanEat {
    fun eat()
}
open class Flyer : CanFly {
    override fun fly() {
        println("I can fly")
    }
}
open class Animal : CanEat {
    override fun eat() {
        println("I can eat")
    }
}
class Bird(flyer: Flyer, animal: Animal) : CanFly by flyer, CanEat by animal {}
fun main(args: Array<String>) {
    val flyer = Flyer()
    val animal = Animal()
    val b = Bird(flyer, animal)
    b.fly()  //打印结果：I can fly
    b.eat()  //打印结果：I can eat
}

------------------------------------------------------------------------------------------------------------------------------------
//通过 data 关键字来定义类，来代替java中的 javaBean
data class Bird(var weight: Double, var age: Int, var color: String)

------------------------------------------------------------------------------------------------------------------------------------
伴生对象：“伴生”是相较于一个类而言的，意为伴随某个类的对象，它属于这个类所有，因此伴生对象跟java中static修饰效果性质一样，全局只有一个单例。
class Prize(val name: String, val count: Int, val type: Int) {
    companion object {
        val TYPE_REDPACK = 0
        val TYPE_COUPON = 1
        fun isRedpack(prize: Prize): Boolean {
            return prize.type == TYPE_REDPACK
        }
    }
}
fun main(args: Array<String>) {
    val prize = Prize("红包",10, Prize.TYPE_REDPACK)
    println(Prize.isRedpack(prize)) //打印结果：true
}

伴生对象的另一个作用是可以实现工厂方法模式。
class Prize private constructor(val name:String, val count:Int, val type:Int) {
    companion object {
        val TYPE_COMMON = 1
        val TYPE_REDPACK = 2
        val TYPE_COUPON = 3

        val defaultCommonPrize = Prize("普通奖品",10, Prize.TYPE_COMMON)
        fun newRedpackPrize(name:String, count:Int) = Prize(name,count,Prize.TYPE_REDPACK)
        fun newCouponPrize(name:String,count:Int) = Prize(name,count,Prize.TYPE_COUPON)
        fun defaultCommonPrize() = defaultCommonPrize
    }
}
fun main(args: Array<String>) {
    val redpackPrize = Prize.newRedpackPrize("红包",10)
    val couponPrize = Prize.newCouponPrize("十元代金券",10)
    val commonPrize = Prize.defaultCommonPrize()
}

------------------------------------------------------------------------------------------------------------------------------------

//object 全局声明的对象只有一个，所以它并不用语法上的初始化，甚至都不需要构造方法。因此 object创造的是天生的单例。单例对象会在系统加载的时候初始化，当然全局就只有一个。
object DatabaseConfig {
    var host: String = "127.0.0.1"
    var port: Int = 3306
    var username: String = "root"
    var password: String = ""
}

//object 除了能表达 伴生对象，单例 之外，还可以表达 匿名内部类
val comparator = object : Comparator<String> {
    override fun compare(s1: String?, s2: String?): Int {
        if (s1 == null)
            return -1
        else if (s2 == null)
            return 1
        return s1.compareTo(s2)
    }
}
Collections.sort(list,comparator)

























 */
