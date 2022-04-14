package mapoperation.distinct

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class DistinctTest {

    @Test
    fun `不存在重复数据`() {
        findDuplication(
            listOf(
                FeeList(10, 20, 10000.0),
                FeeList(20, 20, 20000.0),
                FeeList(30, 20, 30000.0),
                FeeList(10, 10, 40000.0),
            ),
            listOf(
                FeeList::eicId.name,
                FeeList::picId.name
            )
        ).let {
            assertEquals(
                setOf(),
                it
            )
        }
    }

    @Test
    fun `存在一条重复数据`() {
        findDuplication(
            listOf(
                FeeList(10, 20, 10000.0),
                FeeList(20, 20, 20000.0),
                FeeList(10, 20, 30000.0),
                FeeList(10, 10, 40000.0),
            ),
            listOf(
                FeeList::eicId.name,
                FeeList::picId.name
            )
        ).let {
            assertEquals(
                setOf(
                    mapOf(
                        Pair("eicId", 10L),
                        Pair("picId", 20L),
                    )
                ),
                it
            )
        }
    }

    @Test
    fun `存在多条重复数据`() {
        findDuplication(
            listOf(
                FeeList(10, 20, 10000.0),
                FeeList(10, 20, 20000.0),
                FeeList(20, 20, 30000.0),
                FeeList(20, 10, 40000.0),
                FeeList(20, 10, 50000.0),
            ),
            listOf(
                FeeList::eicId.name,
                FeeList::picId.name
            )
        ).let {
            assertEquals(
                setOf(
                    mapOf(
                        Pair("eicId", 10L),
                        Pair("picId", 20L),
                    ),
                    mapOf(
                        Pair("eicId", 20L),
                        Pair("picId", 10L),
                    )
                ),
                it
            )
        }
    }
}