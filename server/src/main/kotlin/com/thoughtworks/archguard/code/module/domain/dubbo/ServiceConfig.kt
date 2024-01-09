package com.thoughtworks.archguard.code.module.domain.dubbo

import org.archguard.protocol.dubbo.SubModuleDubbo

data class ServiceConfig(
    val id: String,
    val interfaceName: String,
    val ref: String,
    val version: String?,
    val group: String?,
    val subModule: SubModuleDubbo
)
