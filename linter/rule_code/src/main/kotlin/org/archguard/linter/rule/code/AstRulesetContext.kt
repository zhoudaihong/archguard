package org.archguard.linter.rule.code

class AstPath(
    // like
    val pathItem: String,
    // http operation sample: ^(get|put|post|delete|options|head|patch|trace)$
    val operationObject: String,
) {
    fun resolveAlias() {

    }
}

class AstRulesetContext(
    path: AstPath
) {

}