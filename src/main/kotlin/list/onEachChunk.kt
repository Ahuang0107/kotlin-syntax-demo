package list

/**
 * 总计有 4700条数据
 * limit 1000 offset 0 表示筛选从 1-1000 的数据
 * limit 1000 offset 1000 表示筛选 1001-2000 的数据
 * limit 1000 offset 2000 表示筛选 2001-3000 的数据
 * limit 1000 offset 3000 表示筛选 3001-4000 的数据
 * limit 1000 offset 4000 表示筛选 4001-4700 的数据
 */
inline fun onEachChunk(
    total: Int, chunkSize: Int,
    block: (
        limit: Int,
        offset: Int,
        size: Int,
        total: Int,
    ) -> Unit
) {
    var offset = 0
    while (offset < total) {
        val rest = total - offset
        block(chunkSize, offset, if (rest < chunkSize) rest else chunkSize, total)
        offset += chunkSize
    }
}

fun main() {
    onEachChunk(4700, 1000) { _, offset, size, _ ->
        println("处理${offset + 1}-${offset + size}区间的数据")
        println("---------------------------------------")
    }
}