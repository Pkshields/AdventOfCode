package dev.paulshields.aoc.utils

private val anyObject = object { }

fun readFileAsString(filePath: String) =
    anyObject
        .javaClass
        .getResource(filePath)
        ?.readText()
        ?.trim()
        ?: ""

fun readFileAsStringList(filePath: String): List<String> {
    val stringList = readFileAsString(filePath).lines()
    val indexOfLastNonEmptyString = stringList.indexOfLast { it.isNotEmpty() }
    return if (indexOfLastNonEmptyString == -1) {
        emptyList()
    } else {
        stringList.take(indexOfLastNonEmptyString + 1)
    }
}
