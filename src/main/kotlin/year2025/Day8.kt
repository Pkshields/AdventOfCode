package dev.paulshields.aoc.year2025

import dev.paulshields.aoc.utils.readFileAsStringList
import kotlin.math.pow
import kotlin.math.sqrt

/** Puzzle logic **/

class CircuitGenerator(junctionBoxesData: List<String>) {
    private val junctionBoxes = junctionBoxesData.map(::toPoint3D).toSet()

    private val circuitsForEachJunctionBox =
        junctionBoxesData.map(::toPoint3D).associateWith { Circuit() }.toMutableMap()

    private val closestUnconnectedCircuits =
        junctionBoxes.associateWith { calculateClosestJunctionBox(it, junctionBoxes) }.toMutableMap()

    lateinit var lastConnection: Connection

    val circuits: Set<Circuit>
        get() = circuitsForEachJunctionBox.values.toSet()

    fun connectJunctionBoxes(times: Int) = repeat(times) { connectNextClosestJunctionBoxes() }

    fun connectAllJunctionBoxes() {
        while (circuits.size > 1) connectNextClosestJunctionBoxes()
    }

    private fun connectNextClosestJunctionBoxes() {
        lastConnection = closestUnconnectedCircuits.values.minBy { it.distance }
        val newCircuit = circuitsForEachJunctionBox[lastConnection.junctionBoxOne] ?: Circuit()

        newCircuit.joinCircuits(circuitsForEachJunctionBox[lastConnection.junctionBoxTwo])
        newCircuit.addConnection(lastConnection.junctionBoxOne, lastConnection.junctionBoxTwo)
        newCircuit.junctionBoxes.forEach {
            circuitsForEachJunctionBox[it] = newCircuit
        }

        closestUnconnectedCircuits[lastConnection.junctionBoxOne] = calculateClosestJunctionBox(
            lastConnection.junctionBoxOne,
            circuitsForEachJunctionBox.keys,
            newCircuit,
        )
        closestUnconnectedCircuits[lastConnection.junctionBoxTwo] = calculateClosestJunctionBox(
            lastConnection.junctionBoxTwo,
            circuitsForEachJunctionBox.keys,
            newCircuit,
        )
    }

    private fun calculateClosestJunctionBox(junctionBox: Point3D, otherJunctionBoxes: Set<Point3D>) =
        otherJunctionBoxes
            .filter { it != junctionBox }
            .map { it to distanceBetweenTwoPoints(junctionBox, it) }
            .minBy { it.second }
            .let { (closestBox, distance) -> Connection(junctionBox, closestBox, distance) }

    private fun calculateClosestJunctionBox(
        junctionBox: Point3D,
        allJunctionBoxes: Set<Point3D>,
        associatedCircuit: Circuit,
    ): Connection {
        val unconnectedJunctionBoxes =
            allJunctionBoxes - (associatedCircuit.junctionBoxConnections[junctionBox] ?: emptySet()).toSet()

        return calculateClosestJunctionBox(junctionBox, unconnectedJunctionBoxes)
    }
}

/** Context & helper classes **/

data class Point3D(val x: Double, val y: Double, val z: Double)

private fun toPoint3D(input: String) =
    input.split(',').let { Point3D(it[0].toDouble(), it[1].toDouble(), it[2].toDouble()) }

class Circuit {
    val junctionBoxConnections = mutableMapOf<Point3D, MutableSet<Point3D>>()

    val junctionBoxes: Set<Point3D>
        get() = junctionBoxConnections.keys

    val numberOfJunctionBoxes: Int
        get() = junctionBoxConnections.size

    fun addConnection(junctionBoxOne: Point3D, junctionBoxTwo: Point3D) {
        junctionBoxConnections.getOrPut(junctionBoxOne, { mutableSetOf() }).add(junctionBoxTwo)
        junctionBoxConnections.getOrPut(junctionBoxTwo, { mutableSetOf() }).add(junctionBoxOne)
    }

    fun joinCircuits(otherCircuit: Circuit?) {
        junctionBoxConnections.putAll(otherCircuit?.junctionBoxConnections ?: emptyMap())
    }
}

data class Connection(val junctionBoxOne: Point3D, val junctionBoxTwo: Point3D, val distance: Float)

private fun distanceBetweenTwoPoints(one: Point3D, two: Point3D) =
    sqrt(
        (one.x - two.x).toFloat().pow(2) +
                (one.y - two.y).toFloat().pow(2) +
                (one.z - two.z).toFloat().pow(2),
    )

/** AOC Answer calculations **/

fun calculateAnswerForPart1(result: Set<Circuit>) =
    result
        .sortedByDescending { it.numberOfJunctionBoxes }
        .take(3)
        .fold(1) { current, next -> current * next.numberOfJunctionBoxes }

fun calculateAnswerForPart2(connection: Connection) =
    connection.junctionBoxOne.x.toLong() * connection.junctionBoxTwo.x.toLong()

/** Runner **/

fun main() {
    println("--- Day 8: Playground ---")
    println("https://adventofcode.com/2025/day/8")

    val day8Input = readFileAsStringList("/year2025/Day8JunctionBoxLocations.txt")

    val part1CircuitGenerator = CircuitGenerator(day8Input)
    part1CircuitGenerator.connectJunctionBoxes(1000)
    println(
        "Part 1: After connecting the closest junction boxes 1000 times, " +
                "the answer to part 1 is ${calculateAnswerForPart1(part1CircuitGenerator.circuits)}.",
    )

    val part2CircuitGenerator = CircuitGenerator(day8Input)
    part2CircuitGenerator.connectAllJunctionBoxes()
    println(
        "Part 2: After connecting junction boxes until a single circuit is formed, " +
                "the answer to part 2 is ${calculateAnswerForPart2(part2CircuitGenerator.lastConnection)}.",
    )
}
