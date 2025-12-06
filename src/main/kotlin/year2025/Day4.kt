package dev.paulshields.aoc.year2025

import dev.paulshields.aoc.utils.readFileAsStringList

data class FloorPosition(val row: Int, val column: Int, val floorSpace: Char)

fun countInitialForkliftAccessibleRollsOfPaper(diagram: List<String>): Int =
    positionsOfForkliftAccessibleRollsOfPaper(diagram).size

private fun positionsOfForkliftAccessibleRollsOfPaper(diagram: List<String>): List<FloorPosition> =
    diagram.mapIndexed { rowIndex, row ->
        row.mapIndexed { columnIndex, floorSpace -> FloorPosition(rowIndex, columnIndex, floorSpace) }
            .filter { positionContainsAccessibleRollOfPaper(it, diagram) }
    }.flatten()

private fun positionContainsAccessibleRollOfPaper(floorPosition: FloorPosition, diagram: List<String>): Boolean {
    if (floorPosition.floorSpace != '@') return false

    val adjacentPositions = listOf(
        floorPosition.row - 1 to floorPosition.column - 1,
        floorPosition.row - 1 to floorPosition.column,
        floorPosition.row - 1 to floorPosition.column + 1,
        floorPosition.row to floorPosition.column - 1,
        floorPosition.row to floorPosition.column + 1,
        floorPosition.row + 1 to floorPosition.column - 1,
        floorPosition.row + 1 to floorPosition.column,
        floorPosition.row + 1 to floorPosition.column + 1,
    )

    val numberOfAdjacentRolls = adjacentPositions.count { (row, column) ->
        diagram.getOrNull(row)?.getOrNull(column) == '@'
    }

    return numberOfAdjacentRolls < 4
}

fun countTotalNumberOfRollsOfPaperMoved(diagram: List<String>): Int {
    var currentDiagram = diagram
    var numberOfMovedPaperRolls = 0
    do {
        val accessiblePaperRolls = positionsOfForkliftAccessibleRollsOfPaper(currentDiagram)
        numberOfMovedPaperRolls += accessiblePaperRolls.size
        currentDiagram = removePaperRollsFromDiagram(currentDiagram, accessiblePaperRolls)
    } while (accessiblePaperRolls.isNotEmpty())

    return numberOfMovedPaperRolls
}

private fun removePaperRollsFromDiagram(diagram: List<String>, paperRollPositions: List<FloorPosition>): List<String> {
    val mutableDiagram = diagram.map { it.toMutableList() }.toMutableList()
    paperRollPositions.forEach { mutableDiagram[it.row][it.column] = '.' }
    return mutableDiagram.map { it.joinToString("") }.toList()
}

fun main() {
    println("--- Day 4: Printing Department ---")
    println("https://adventofcode.com/2025/day/4")

    val day4Input = readFileAsStringList("/year2025/Day4PaperRollsDiagram.txt")

    val part1Result = countInitialForkliftAccessibleRollsOfPaper(day4Input)
    println("Part 1: The number of initially accessible rolls of paper is $part1Result")

    val part2Result = countTotalNumberOfRollsOfPaperMoved(day4Input)
    println("Part 2: The total number of rolls of paper moved is $part2Result")
}
