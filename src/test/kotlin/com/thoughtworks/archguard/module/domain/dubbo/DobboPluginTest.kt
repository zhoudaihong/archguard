package com.thoughtworks.archguard.module.domain.dubbo

import com.thoughtworks.archguard.clazz.domain.ClazzType
import com.thoughtworks.archguard.clazz.domain.JClass
import com.thoughtworks.archguard.clazz.domain.JClassRepository
import com.thoughtworks.archguard.module.domain.model.Dependency
import com.thoughtworks.archguard.module.domain.model.JMethodVO
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class DobboPluginTest {

    @MockK
    lateinit var jClassRepository: JClassRepository

    @InjectMockKs
    private var dubboPlugin = DubboPlugin()

    @BeforeEach
    internal fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    internal fun should_fix_methodDependencies_not_with_xmlConfig() {
        // given
        val projectId:Long = 1
        val class1 = JClass("any", "caller", "module1")
        val class2 = JClass("any", "callee", "module2")
        class2.addClassType(ClazzType.INTERFACE)
        val class3 = JClass("any", "impl", "module3")
        val class4 = JClass("any", "impl", "module4")

        val method1 = JMethodVO("method", class1.toVO(), "any", listOf())
        val method2 = JMethodVO("method", class2.toVO(), "any", listOf())
        val method3 = JMethodVO("method", class3.toVO(), "any", listOf())
        val method4 = JMethodVO("method", class4.toVO(), "any", listOf())

        val methodDependencies = listOf(Dependency(method1, method2))

        every { jClassRepository.getJClassesHasModules(projectId) } returns listOf(class2)
        every { jClassRepository.findClassImplements(projectId, class2.name, class2.module) } returns listOf(class3, class4)


        // when
        val fixedMethodDependencies = dubboPlugin.fixMethodDependencies(projectId, methodDependencies)

        // then
        assertEquals(2, fixedMethodDependencies.size)
        assertThat(fixedMethodDependencies).usingDefaultElementComparator().containsExactlyElementsOf(listOf(Dependency(method1, method3), Dependency(method1, method4)))
    }

}
