package dev.paulshields.aoc.year2025

import dev.paulshields.aoc.utils.readFileAsString

fun findSumOfProductIdsContainingPatternRepeatedTwice(input: String) =
    input
        .split(',')
        .map(::rangeIntoSequence)
        .map { range ->
            range.filter(::productIdIsPatternRepeatedTwice).sumOf { it }
        }.sumOf { it }

private fun rangeIntoSequence(range: String): Sequence<Long> {
    val (start, end) = range.split('-')
    return generateSequence(start.toLong()) { previous -> previous.inc().takeIf { it != end.toLong().inc() } }
}

private fun productIdIsPatternRepeatedTwice(numericProductId: Long): Boolean {
    val productId = numericProductId.toString()
    val halfLength = (productId.length / 2)
    return productId.length % 2 == 0 &&
        productId.take(halfLength) == productId.substring(halfLength, productId.length)
}

fun findSumOfProductIdsContainingOnlyRepeatedPattern(input: String) =
    input
        .split(',')
        .map(::rangeIntoSequence)
        .map { range ->
            range.filter(::productIdOnlyContainsARepeatedPattern).sumOf { it }
        }.sumOf { it }

private fun productIdOnlyContainsARepeatedPattern(numericProductId: Long): Boolean {
    val productId = numericProductId.toString()
    val halfLength = (productId.length / 2)
    for (i in 1..halfLength) {
        val potentialPattern = productId.take(i)
        if (productId.replace(potentialPattern, "").isEmpty()) return true
    }
    return false
}

fun main() {
    println("--- Day 2: Gift Shop ---")
    println("https://adventofcode.com/2025/day/2")

    val day2Input = readFileAsString("/year2025/Day2GiftShopProductIds.txt")

    val part1Result = findSumOfProductIdsContainingPatternRepeatedTwice(day2Input)
    println("Part 1: The sum of all product IDs containing pattern repeated twice is $part1Result")

    val part2Result = findSumOfProductIdsContainingOnlyRepeatedPattern(day2Input)
    println("Part 2: The sum of all product IDs containing only repeated pattern is $part2Result")
}
