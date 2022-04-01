package unittest

import kotlin.test.Test
import kotlin.test.assertEquals

internal class UtilTest {
    private val util = Util()

    @Test
    fun testSum() {
        assertEquals(42.0, util.sum(40.0, 2.0))
    }

}