package dev.paulshields.aoc.year2025

import dev.paulshields.aoc.utils.pop
import dev.paulshields.aoc.utils.readFileAsStringList
import kotlin.math.max
import kotlin.math.min

fun countNumberOfFreshIngredients(database: List<String>): Int {
    val (ingredientRanges, ingredientIds) = parseDatabase(database)
    return ingredientIds.count { ingredientId -> ingredientRanges.any { it.contains(ingredientId) } }
}

private fun parseDatabase(database: List<String>): Pair<List<LongRange>, List<Long>> {
    val databaseTables = database.split { it.isEmpty() }
    val ingredientRanges = databaseTables[0].map {
        val (start, end) = it.split('-')
        start.toLong()..end.toLong()
    }
    val ingredientIds = databaseTables[1].map { it.toLong() }
    return ingredientRanges to ingredientIds
}

private fun <T> List<T>.split(splitPredicate: (T) -> Boolean): List<List<T>> {
    val splitPoints = this
        .mapIndexedNotNull { index, value -> if (splitPredicate(value)) index else null }
        .toMutableList()
    splitPoints += this.size

    return splitPoints.mapIndexed { index, end ->
        val start = splitPoints.getOrNull(index - 1) ?: -1
        this.subList(start + 1, end)
    }
}

fun countNumberOfValidFreshIngredientIDs(database: List<String>): Long {
    var (ingredientRanges, _) = parseDatabase(database)
    ingredientRanges = mergeOverlappingIdRanges(ingredientRanges)
    return ingredientRanges.map { it.last - it.first + 1 }.sumOf { it }
}

private fun mergeOverlappingIdRanges(initialIngredientRanges: List<LongRange>): List<LongRange> {
    val ingredientRanges = initialIngredientRanges.sortedBy { it.first }.toMutableList()
    val resultingRanges = mutableListOf<LongRange>()

    do {
        val range = ingredientRanges.pop()
        val combinedRange = ingredientRanges
            .takeWhile { it.first <= range.last }
            .filter { range.overlaps(it) }
            .also { ingredientRanges.removeAll(it) }
            .fold(range) { wipRange, overlappingRange -> wipRange.rangeUnion(overlappingRange) }

        if (combinedRange != range) {
            ingredientRanges.add(0, combinedRange)
        } else {
            resultingRanges.add(range)
        }
    } while (ingredientRanges.isNotEmpty())

    return resultingRanges.toList()
}

private fun LongRange.overlaps(other: LongRange) =
    this.contains(other.first) ||
        this.contains(other.last) ||
        other.contains(this.first) ||
        other.contains(this.last)

private fun LongRange.rangeUnion(other: LongRange) =
    min(this.first, other.first)..max(this.last, other.last)

fun main() {
    println("--- Day 5: Cafeteria ---")
    println("https://adventofcode.com/2025/day/5")

    val day5Input = readFileAsStringList("/year2025/Day5IngredientDatabase.txt")

    val part1Result = countNumberOfFreshIngredients(day5Input)
    println("Part 1: The number of fresh ingredients is $part1Result")

    val part2Result = countNumberOfValidFreshIngredientIDs(day5Input)
    println("Part 2: The number of valid fresh ingredient IDs is $part2Result")
}
