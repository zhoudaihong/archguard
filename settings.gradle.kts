/*
 * This file was generated by the Gradle 'init' task.
 *
 * This project uses @Incubating APIs which are subject to change.
 */

rootProject.name = "ArchGuard Scanner"

// common for share code repository
// TODO need to refactor as http, related https://github.com/archguard/archguard/issues/43, https://github.com/archguard/scanner/issues/3
include(":common_code_repository")

include(":diff_changes")

// core rule model
include(":rule_core")
// standard scanner model
include(":scanner_core")
include(":scanner_cli")

// official analyser implementations
include(":analyser_template")
include(":analyser_sourcecode:lang_kotlin")
include(":analyser_sourcecode:lang_java")
include(":analyser_sourcecode:lang_typescript")
include(":analyser_sourcecode:feat_apicalls")

// legacy scanners, should replace by analyser
include(":scan_git")
include(":scan_jacoco")
include(":scan_sourcecode")
include(":scan_sql")
include(":scan_test_badsmell")
include(":scan_bytecode")

include(":analyser_sca")

include(":linter:rule_sql")

// adapter for ci
include(":collector_ci")
