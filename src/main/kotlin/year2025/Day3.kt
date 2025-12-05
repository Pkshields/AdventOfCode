package dev.paulshields.aoc.year2025

import dev.paulshields.aoc.utils.readFileAsStringList

fun calculateTotalMaxJoltageFromAllBanks(banks: List<String>): Int {
    return banks.map(::calculateMaxJoltageFromBank).sumOf { it }
}

private fun calculateMaxJoltageFromBank(bank: String): Int {
    val bankDigits = bank.map { it.digitToInt() }

    val (batteryOneIndex, batteryOne) = bankDigits
        .take(bankDigits.size - 1)
        .mapIndexed { index, value -> Pair(index, value) }
        .maxBy { (_, value) -> value }

    val batteryTwo = bankDigits
        .drop(batteryOneIndex + 1)
        .maxBy { it }

    return (batteryOne * 10) + batteryTwo
}

fun calculateTotalMaxOverrideJoltageFromAllBanks(banks: List<String>): Long {
    return banks.map(::calculateMaxOverrideJoltageFromBank).sumOf { it }
}

private fun calculateMaxOverrideJoltageFromBank(bank: String): Long {
    val bankDigits = bank.map { it.digitToInt() }
    var nextDigitRange = 0 to bankDigits.size - 11
    var result = 0L

    for (i in 1..12) {
        val (digit, index) = findHighestDigit(bankDigits.subList(nextDigitRange.first, nextDigitRange.second))
        nextDigitRange = (nextDigitRange.first + index + 1) to (bankDigits.size - 11 + i)
        result = (result * 10) + digit
    }

    return result
}

private fun findHighestDigit(numbers: List<Int>) =
    numbers
        .mapIndexed { key, value -> value to key }
        .maxBy { (value, _) -> value }

fun main() {
    println("--- Day 3: Lobby ---")
    println("https://adventofcode.com/2025/day/3")

    val day3Input = readFileAsStringList("/year2025/Day3BatteryBanks.txt")

    val part1Result = calculateTotalMaxJoltageFromAllBanks(day3Input)
    println("Part 1: The total output joltage from the battery banks is $part1Result")

    val part2Result = calculateTotalMaxOverrideJoltageFromAllBanks(day3Input)
    println("Part 2: Post joltage override, the total output joltage from the battery banks is $part2Result")
}
