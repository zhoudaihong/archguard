package com.thoughtworks.archguard.insights

import com.thoughtworks.archguard.insights.domain.ScaModelDto
import org.archguard.domain.insight.FieldFilter

interface InsightRepository {
    abstract fun filterByConditionWithSystemId(id: Long, models: List<FieldFilter>): List<ScaModelDto>
    abstract fun filterByCondition(models: List<FieldFilter>): List<ScaModelDto>
    abstract fun saveInsight(insight: CustomInsight): Long
    abstract fun getInsightByName(name: String): CustomInsight?
    abstract fun deleteInsightByName(name: String): Int
}
