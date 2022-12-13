package org.archguard.scanner.gradle.plugin

import org.gradle.api.Action
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project
import javax.inject.Inject

@Suppress("UnnecessaryAbstractClass")
abstract class ArchguardExtension @Inject constructor(project: Project) {
    var slot: NamedDomainObjectContainer<SlotConfiguration>

    init {
        this.slot = project.container(SlotConfiguration::class.java, SlotConfigurationFactory(project))
    }

    /**
     * The server url of Archguard backend, default to [http://localhost:8088]
     */
    var serverUrl: String = "http://localhost:8080"

    /**
     * The supported languages: [java, kotlin, javascript, typescript, python, go ...], default to [java]
     */
    var language: String = "java"

    /**
     * The supported features: ["apicalls", ""]
     */
    var features: List<String> = listOf()

    /**
     * The Archguard Slots configuration for the project
     */
    fun slots(action: Action<NamedDomainObjectContainer<SlotConfiguration>>) {
        action.execute(slot)
    }
}

