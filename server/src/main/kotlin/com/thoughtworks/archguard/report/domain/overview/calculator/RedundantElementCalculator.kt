package com.thoughtworks.archguard.report.domain.overview.calculator

import com.thoughtworks.archguard.report.domain.redundancy.DataClassRepository
import com.thoughtworks.archguard.report.domain.redundancy.RedundancyRepository
import org.archguard.smell.BadSmellResult
import org.archguard.smell.BadSmellLevelCalculator
import org.springframework.stereotype.Component

@Component
class RedundantElementCalculator(
    val redundancyRepository: RedundancyRepository,
    val dataClassRepository: DataClassRepository
) : BadSmellLevelCalculator {

    override fun getCalculateResult(systemId: Long): BadSmellResult {
        val oneMethodClassCount = redundancyRepository.getOneMethodClassCount(systemId)
        val oneMethodLevel = getOneMethodLevel(oneMethodClassCount, getOneMethodCountLevelRanges())

        val oneFieldClassCount = dataClassRepository.getAllDataClassWithOnlyOneFieldCount(systemId)
        val oneFieldLevel = getOneFieldLevel(oneFieldClassCount, getOneFieldCountLevelRanges())

        return oneMethodLevel.plus(oneFieldLevel)
    }

    private fun getOneMethodLevel(oneMethodClassCount: Long, range: Array<LongRange>): BadSmellResult {
        return when (oneMethodClassCount) {
            in range[0] -> BadSmellResult(1L, 0L, 0L)
            in range[1] -> BadSmellResult(0L, 1L, 0L)
            in range[2] -> BadSmellResult(0L, 0L, 1L)
            else -> {
                BadSmellResult(0L, 0L, 0L)
            }
        }
    }

    private fun getOneMethodCountLevelRanges(): Array<LongRange> {
        val countRangeLevel1 = 10L until 30L
        val countRangeLevel2 = 30L until 100L
        val countRangeLevel3 = 100L until Long.MAX_VALUE
        return arrayOf(countRangeLevel1, countRangeLevel2, countRangeLevel3)
    }

    private fun getOneFieldLevel(oneFieldClassCount: Long, range: Array<LongRange>): BadSmellResult {
        return when (oneFieldClassCount) {
            in range[0] -> BadSmellResult(1L, 0L, 0L)
            in range[1] -> BadSmellResult(0L, 1L, 0L)
            in range[2] -> BadSmellResult(0L, 0L, 1L)
            else -> {
                BadSmellResult(0L, 0L, 0L)
            }
        }
    }

    private fun getOneFieldCountLevelRanges(): Array<LongRange> {
        val countRangeLevel1 = 10L until 30L
        val countRangeLevel2 = 30L until 100L
        val countRangeLevel3 = 100L until Long.MAX_VALUE
        return arrayOf(countRangeLevel1, countRangeLevel2, countRangeLevel3)
    }
}
