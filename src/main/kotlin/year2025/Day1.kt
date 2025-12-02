package dev.paulshields.aoc.year2025

import dev.paulshields.aoc.utils.readFileAsStringList
import kotlin.math.abs

fun calculateSecretDoorPassword(instructions: List<String>): Int {
    var password = 0
    var position = 50

    splitRotationInstructions(instructions)
        .forEach { (direction, rotationCount) ->
            if (direction == 'L') {
                position -= rotationCount % 100
                position = if (position < 0) 100 + position else position
            } else {
                position += rotationCount % 100
                position = if (position > 99) position % 100 else position
            }

            if (position == 0) ++password
        }

    return password
}

fun calculateSecretDoorPasswordWithMethod0x434C49434B(instructions: List<String>): Int {
    var password = 0
    var position = 50

    splitRotationInstructions(instructions)
        .forEach { (direction, rotationCount) ->
            val rotationCountWithoutLoops = rotationCount % 100
            password += rotationCount / 100

            if (direction == 'L') {
                position -= rotationCountWithoutLoops
                if (position < 1 && abs(position) != rotationCountWithoutLoops) ++password
            } else {
                position += rotationCountWithoutLoops
                if (position > 99) ++password
            }

            position = if (position < 0) position + 100 else position % 100
        }

    return password
}

fun splitRotationInstructions(instructions: List<String>) =
    instructions.map { Pair(it.first(), it.substring(1).toInt()) }

fun main() {
    println("--- Day 1: Secret Entrance ---")
    println("https://adventofcode.com/2025/day/1")

    val day1Input = readFileAsStringList("/year2025/Day1PasswordDocument.txt")

    val part1Result = calculateSecretDoorPassword(day1Input)
    println("Part 1: The password for the door is $part1Result")

    val part2Result = calculateSecretDoorPasswordWithMethod0x434C49434B(day1Input)
    println("Part 2: The password for the door is $part2Result")
}
