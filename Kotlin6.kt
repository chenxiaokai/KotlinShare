package com.cxk.mylib

/*
//Kotlin中扩展函数，我们可以把扩展函数理解为静态方法，扩展函数不会带来额外的性能消耗
package com.example.extension
fun MutableList<Int>.exchange(fromIndex:Int, toIndex:Int) { //给 MutableList定义一个扩展函数
    val tmp = this[fromIndex]
    this[fromIndex] = this[toIndex]  //这里的this代表的是接受者类型的对象
    this[toIndex] = tmp
}

一般我们习惯将扩展函数直接定义在包内，例如之前的exchange例子，我们将其放在com.example.extension包下。如果需要在其他包中调用，只需要import相应的方法即可，这与java全局静态方法类似。
除此之外，实际开发时我们也可能会将扩展函数定义在一个Class内部统一管理。
class Extends {
    fun MutableList<Int>.exchange(fromIndex:Int, toIndex:Int) {
        val tmp = this[fromIndex]
        this[fromIndex] = this[toIndex]
        this[toIndex] = tmp
    }
}
当扩展函数定义在Extends类内部时，情况就与之前不⼀样了这个时候你会发现，之前的exchange⽅法⽆法调⽤了（之前调⽤位置在Extends类外部）。
借助IDEA我们可以查看到它对应的Java代码，exchange⽅法上已经没有static关键字的修饰了。所以当扩展⽅法在⼀个Class内部时，我们只能在该类和该类的⼦类中进⾏调⽤

//Kotlin中扩展属性
⽐如我们想给 MutableList<Int>添加判断⼀个判断和是否为偶数的属性sumIsEven
val MutableList<Int>.sumIsEven: Boolean
    get() = this.sum() % 2 == 0

//编译错误：扩展属性不能有初始化器
val MutableList<Int>.sumIsEven: Boolean = false
    get() = this.sum() % 2 == 0
其实，与扩展函数⼀样，其本质也是对应Java中的静态⽅法（我们反编译成Java代码后可以看到⼀个getSumIsEven的静态⽅法，与扩展函数类似）。由于扩展没有实际地将成员插⼊类中，因此对扩展属性来说幕后字段是⽆效的。这就是为什么扩展属性不能有初始化器的原因。它们的⾏为只能由显式提供的getters和setters定义。


//幕后字段
在Kotlin中，如果属性中存在访问器使⽤默认实现，那么Kotlin会⾃动提供幕后字段filed，其仅可⽤于⾃定义getter和setter中。


在Kotlin中，如果你需要声明一个静态的扩展函数，开发者必须将其定义在伴生对象(companion object)上。所以我们需要这样定义带有伴生对象的类
class Son {
    companion object {
        val age = 10
    }
}
Son类中已经有一个伴生对象，如果我们现在不想再Son中定义扩展函数，而是在Son的伴生对象上定义，可以这么写：
fun Son.Companion.foo() {
    println("age = $age")
}
这样，我们就能在Son没有实例对象的情况下，也能调佣到这个扩展函数，语法类似于Java静态方法。
object Test {
    @JvmStatic
    fun main(args: Array<String>) {
        Son.foo()
    }
}

//成员方法优先级总高于扩展函数
class Son {
    fun foo() = println("son called member foo")
}
fun Son.foo() = println("son called extention foo")
object Test {
    @JvmStatic
    fun main(args: Array<String>) {
        Son().foo() //打印结果：son called member foo。 这表明：当扩展函数和现有类的成员⽅法同时存在时，Kotlin将会默认使⽤类的成员⽅法。
    }
}

class Son {
    fun foo() {
        println("foo in Class Son")
    }
}
object Parent {
    fun foo() {
        println("foo in Class Parent")
    }
    @JvmStatic
    fun main(args: Array<String>) {
        fun Son.foo2() { //Son对象增加扩展函数
            this.foo()
            this@Parent.foo()
        }
        Son().foo2() //调用Son对象的foo2()函数
    }
}

class Son {
    fun foo() {
        println("foo in Class Son")
    }
}
object Parent {
    fun foo() {
        println("foo in Class Parent")
    }
    @JvmStatic
    fun main(args: Array<String>) {
        fun Son.foo2() { //Son对象增加扩展函数
            this.foo()
            this@Parent.foo()  //我们可以用this@类名来强行指定调用的this。
        }
        Son().foo2() //调用Son对象的foo2()函数
        /*
        打印结果:
            foo in Class Son
            foo in Class Parent
         */
    }
}

class Son {
    fun foo() {
        println("foo in Class Son")
    }
}
class Parent {
    fun foo() {
        println("foo in Class Parent")
    }

    fun Son.foo2() { //另外值得一提的是：如果Son扩展函数在Parent类内，我们将无法对其调用
        this.foo()
        this@Parent.foo()
    }
}
object Test {
    @JvmStatic
    fun main(args: Array<String>) {
        Son().foo2() //这里无法调用foo2()函数
    }
}

//takeIf类似下面的作用
 if (someObject != null && status) {
    doThis()
 }
 上面代码等价下面代码
 someObject?.takeIf{ status }?.apply { doThis() }
























































 */
