package dev.paulshields.aoc.year2025

import dev.paulshields.aoc.utils.readFileAsStringList

fun countTachyonBeamSplits(manifold: List<String>): Int {
    var beamPositions = setOf(manifold.first().indexOf('S'))
    var numSplits = 0

    for (i in 1 until manifold.size) {
        beamPositions = beamPositions.flatMap {
            if (manifold[i][it] == '^') {
                ++numSplits
                listOf(it - 1, it + 1)
            } else {
                listOf(it)
            }
        }.toSet()
    }

    return numSplits
}

fun countTachyonParticleTimelines(manifold: List<String>): Long {
    val timelines = mutableListOf(TachyonParticlePosition(manifold.first().indexOf('S')))
    var numCompletedTimelines = 0L
    val pathHistory = TachyonPathHistory()

    while (timelines.isNotEmpty()) {
        val tachyonParticlePath = timelines.pop()
        val nextLayer = tachyonParticlePath.depth + 1

        pathHistory.getCountForCoordinates(tachyonParticlePath)?.let {
            numCompletedTimelines += it
            pathHistory.addCountToHistoryForPath(tachyonParticlePath.timelinePositions.dropLast(1), it)
        } ?: if (nextLayer == manifold.size) {
            ++numCompletedTimelines
            pathHistory.addCountToHistoryForPath(tachyonParticlePath.timelinePositions, 1L)
        } else if (manifold[nextLayer][tachyonParticlePath.currentPosition] == '^') {
            timelines.pushToFront(tachyonParticlePath.nextBranchWithOffset(-1))
            timelines.pushToFront(tachyonParticlePath.nextBranchWithOffset(1))
        } else {
            timelines.pushToFront(tachyonParticlePath.nextBranchWithOffset(0))
        }
    }

    return numCompletedTimelines
}

private class TachyonParticlePosition(val timelinePositions: List<Int>) {
    val depth = timelinePositions.size - 1
    val currentPosition = timelinePositions.last()
    val currentCoordinates = depth to currentPosition

    constructor(timelinePosition: Int) : this(listOf(timelinePosition))

    fun nextBranchWithOffset(offset: Int) = TachyonParticlePosition(timelinePositions + (currentPosition + offset))
}

private class TachyonPathHistory {
    private val pathHistory = mutableMapOf<Pair<Int, Int>, Long>()

    fun getCountForCoordinates(particlePosition: TachyonParticlePosition) =
        pathHistory[particlePosition.currentCoordinates]

    fun addCountToHistoryForPath(particlePath: List<Int>, numberOfNewPaths: Long) {
        particlePath.forEachIndexed { index, position ->
            val currentCount = pathHistory[index to position] ?: 0L
            pathHistory[index to position] = currentCount + numberOfNewPaths
        }
    }
}

private fun <T> MutableList<T>.pop() = this.elementAt(0).also { this.remove(it) }

private fun <T> MutableList<T>.pushToFront(element: T) = this.add(0, element)

fun main() {
    println("--- Day 7: Laboratories ---")
    println("https://adventofcode.com/2025/day/7")

    val day7Input = readFileAsStringList("/year2025/Day7TachyonManifold.txt")

    val part1Result = countTachyonBeamSplits(day7Input)
    println("Part 1: The number of tachyon beam splits that occur in the manifold is $part1Result")

    val part2Result = countTachyonParticleTimelines(day7Input)
    println("Part 2: The number of timelines that a tachyon particle travels on is $part2Result")
}
