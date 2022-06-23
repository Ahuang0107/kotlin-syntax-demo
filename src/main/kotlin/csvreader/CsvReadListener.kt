package csvreader

abstract class CsvReadListener {
  abstract var head: MutableMap<String, Int>
  abstract var data: MutableList<Map<Int, String>>

  abstract fun invokeHead(head: Map<String, Int>)

  abstract fun invoke(data: Map<Int, String>)

  abstract fun doAfterAllAnalysed()
}
