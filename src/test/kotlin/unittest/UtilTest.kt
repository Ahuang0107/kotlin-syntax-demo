package unittest

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.test.Test
import kotlin.test.assertEquals

internal class UtilTest {
    private val util = Util()

    @Test
    fun testSum() {
        assertEquals(42.0, util.sum(40.0, 2.0))
    }

    @ParameterizedTest
    @CsvSource(
        "0,1,1",
        "1,2,3",
        "49,51,100",
        "1,100,101"
    )
    fun borderTest(first: Double?, second: Double?, expectedResult: Double) {
        assertEquals(expectedResult, util.sum(first, second), "$first + $second should equal $expectedResult")
    }
}