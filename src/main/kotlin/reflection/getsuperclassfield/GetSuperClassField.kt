package reflection.getsuperclassfield

import kotlin.reflect.full.memberProperties

fun main() {
    /* 可以获取到父类的属性 age name */
    Dog::class.memberProperties.forEach {
        println(it.name)
    }
    println("------------------------")

    /* 不能获取到父类的属性 age */
    Dog::class.java.declaredFields.forEach {
        println(it.name)
    }
    println("------------------------")

    /* 不能获取到父类的属性 */
    Dog::class.java.fields.forEach {
        println(it.name)
    }
}