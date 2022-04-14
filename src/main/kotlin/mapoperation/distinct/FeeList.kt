package mapoperation.distinct

data class FeeList(
    var eicId: Long? = null,
    var picId: Long = 0L,
    var fee: Double = 0.0,
) : Distinct
