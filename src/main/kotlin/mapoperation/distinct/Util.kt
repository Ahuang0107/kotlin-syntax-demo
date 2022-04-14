package mapoperation.distinct

import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.jvm.isAccessible

/**
 * 希望时指定一个类型的List和需要检查的属性，然后检查这个List的元素这几个属性有没有重复的，如果有重复的话是哪些重复了
 */
inline fun <reified T : Distinct> findDuplication(list: List<T>, keys: List<String>): Set<Map<String, Any>> {

    val properties = T::class.declaredMemberProperties.filter { it.isAccessible }.map { it.name }

    keys.forEach {
        if (properties.contains(it)) throw Exception("使用了不存在的属性作为key")
    }

    val bucket = mutableSetOf<Map<String, Any>>()

    val duplicateSet = mutableSetOf<Map<String, Any>>()

    list.forEach { data ->
        data.getMemberReflect().filter { it.key in keys }.let {
            if (bucket.contains(it)) {
                duplicateSet.add(it)
            } else {
                bucket.add(it)
            }
        }
    }

    return duplicateSet
}

inline fun <reified T : Distinct> T.getMemberReflect(): Map<String, Any> {
    // todo javaClass 是什么，为什么只有实现了某个接口的 data class 才有 javaClass 属性
    val fields = javaClass.declaredFields
    val notAccessibleFields = fields.filter { !it.canAccess(this) }

    val result = fields.map {
        it.isAccessible = true
        it
    }.associate {
        it.name to it.get(this)
    }

    notAccessibleFields.forEach { it.isAccessible = false }
    return result
}