package my.day1

/**
 * break 和 Continue 标签
 */
fun label(){
    loop@ for (i in 1..10) {
        for (j in 1..10) {
            if (j == 5) break@loop   // 跳出最外层循环
            println("i = $i j = $j  total = ${i*j}")
        }
    }
}

/**
 * return 返回到指定位置
 */
fun returnPos(){
    listOf<Int>(1,2,3,4,5).forEach{
        if (it == 3) return   //返回退出整个 returnPos函数
        print(" ${it} ")
    }
    println("this point is unreachable")
}

fun returnPos1(){
    listOf<Int>(1,2,3,4,5).forEach lit@{
        if (it == 3) return@lit   //返回退出 forEach 函数
        print(" ${it} ")
    }
    println()
    print("done with explicit label")
}

fun returnPos2(){
    listOf<Int>(1,2,3,4,5).forEach {
        if (it == 3) return@forEach
        print(" ${it} ")
    }
    println()
    print("done with explicit label")
}

fun main(args: Array<String>) {
    label()
//    returnPos()
//    returnPos1()
//    returnPos2()
}