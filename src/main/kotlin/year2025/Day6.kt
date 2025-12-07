package dev.paulshields.aoc.year2025

import dev.paulshields.aoc.utils.readFileAsString

fun sumOfCephalopodsMathsHomework(homework: String): Long {
    val homeworkAsGrid = homework.lines().map { it.trim().split("\\s+".toRegex()) }
    val properlyStructuredSums = (0 until homeworkAsGrid[0].size).map { index -> homeworkAsGrid.map { it[index] } }
    return properlyStructuredSums.sumOf { sum ->
        val numbers = sum.dropLast(1).map { it.toLong() }
        if (sum.last() == "+") {
            numbers.sumOf { it }
        } else {
            numbers.reduce { current, next -> current * next }
        }
    }
}

fun correctedSumOfCephalopodsMathsHomework(homework: String) =
    splitHomeworkIntoProblemColumns(homework)
        .sumOf { (digitLines, operator) -> solveProblem(digitLines, operator) }

private fun splitHomeworkIntoProblemColumns(homework: String): List<Pair<List<String>, Char>> {
    val (digitLines, operatorLine) = homework
        .lines()
        .let { it.dropLast(1) to it.last() }

    val operatorPositions = operatorLine.mapIndexedNotNull { index, operator -> if (operator != ' ') index else null }
    val problemEdges = operatorPositions.zip(operatorPositions.drop(1) + (digitLines[0].length + 1))

    return problemEdges.map { (start, end) ->
        val problemDigitLines = digitLines.map { it.substring(start, end - 1) }
        problemDigitLines to operatorLine[start]
    }
}

private fun solveProblem(digitLines: List<String>, operator: Char): Long {
    val numbers = mutableListOf<Long>()
    for (column in digitLines[0].length - 1 downTo 0) {
        val number = digitLines
            .mapNotNull { it.getOrNull(column) }
            .joinToString("")
            .trim()
            .toLong()
        numbers.add(number)
    }

    return if (operator == '+') {
        numbers.sumOf { it }
    } else {
        numbers.reduce { current, next -> current * next }
    }
}

fun main() {
    println("--- Day 6: Trash Compactor ---")
    println("https://adventofcode.com/2025/day/6")

    val day6Input = readFileAsString("/year2025/Day6CephalopodsMathsHomework.txt")

    val part1Result = sumOfCephalopodsMathsHomework(day6Input)
    println("Part 1: The sum of all maths problems in the Cephalopod's homework is $part1Result")

    val part2Result = correctedSumOfCephalopodsMathsHomework(day6Input)
    println("Part 2: The corrected sum of all maths problems in the Cephalopod's homework is $part2Result")
}
