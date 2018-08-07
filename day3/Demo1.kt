package my.day3

//定义一个泛型接口
interface Factory<T> {
    fun create(): T
}

class MyClass {
    //伴生对象仅被允许定义一个在Int的内部类
    companion object InnerObject : Factory<Int>{
        override fun create(): Int {
            return 1
        }

        @JvmStatic
        fun companionFun() {
            println("companionFun")
        }
    }
}

class MyClass2 {
    //伴生对象省略伴生对象名定义一个String的内部类
    companion object : Factory<String>{
        override fun create(): String {
            return "test"
        }

        fun companionFun2(){
            println("companionFun2")
        }
    }
}

fun main(args:Array<String>) {
//    MyClass.InnerObject.companionFun()
    //允许通过类名限定符来调用方法，看起来像静态成员，"但运行时任然是真是对象的实例成员"
//    MyClass.companionFun()
//    println(MyClass.create())

    //---------------------------------------------------
    MyClass2.Companion.companionFun2()
    MyClass2.companionFun2()
    println(MyClass2.create())
}