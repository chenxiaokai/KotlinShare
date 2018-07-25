package my.day2

class Address {
    //1. 类中申明属性
    lateinit var name: String
    var street: String = "b"
    var city: String = "c"
    var state: String = "d"
    var zip: String = "e"

    //lateinit 延迟初始化，必须在 init中 初始化，否则访问会报错
    lateinit var lateVar: String
    init {
        lateVar = "zhaoyun"
        name = "hello"
    }

    /*
      2. 声明属性的完整语法
         var <propertyName>[: <PropertyType>] [= <property_initializer>]
                           [<getter>]
                           [<setter>]

      3. 幕后字段field，主要防止get 和 set 方法重复调用，导致堆栈溢出
     */

    var stringInit: String = "abc"
        public get() {
            return field
        }
        public set(value) {
            field = "test $value"
        }

    override fun toString(): String {
        return "Address(name='$name', street='$street', city='$city', state='$state', zip='$zip')"
    }
}

// 4. 编译期常量
const val compileConst: Int = 1

fun main(args:Array<String>) {
    val address = Address()
    address.name = "1"
    address.street = "2"
    address.city = "3"
    address.state = "4"
    address.zip = "5"
    println(address)

    println(address.stringInit)

    address.stringInit = "cxk"
    println(address.stringInit)

    println(address.lateVar)

    var video = MovieBean.Video(1,"/mnt/sdcard","test",
            "testName","/mnt/sdcard/path","type/mp4",
            500,"20")
    var bean = MovieBean(video,"100")
    println(bean)
}

//这才是定义java中 Bean类的 正确写法
data class MovieBean(var video: Video? = null, var size: String? = "") {
    data class Video(
            val id: Int? = 0,
            var path: String? = null,
            var title: String? = null,
            var displayName:String? = null,
            var thumbPath: String? = null,
            var mimeType:String? = null,
            var duration:Int? = 0,
            var size: String? = ""
    )
}