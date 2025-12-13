package dev.paulshields.aoc.year2025

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Day8Test {
    @Test
    fun `should pass provided test for day 8 part 1`() {
        val input = listOf(
            "162,817,812",
            "57,618,57",
            "906,360,560",
            "592,479,940",
            "352,342,300",
            "466,668,158",
            "542,29,236",
            "431,825,988",
            "739,650,466",
            "52,470,668",
            "216,146,977",
            "819,987,18",
            "117,168,530",
            "805,96,715",
            "346,949,466",
            "970,615,88",
            "941,993,340",
            "862,61,35",
            "984,92,344",
            "425,690,689",
        )
        val target = CircuitGenerator(input)

        target.connectJunctionBoxes(10)

        assertThat(calculateAnswerForPart1(target.circuits)).isEqualTo(40)
    }

    @Test
    fun `should pass provided test for day 8 part 2`() {
        val input = listOf(
            "162,817,812",
            "57,618,57",
            "906,360,560",
            "592,479,940",
            "352,342,300",
            "466,668,158",
            "542,29,236",
            "431,825,988",
            "739,650,466",
            "52,470,668",
            "216,146,977",
            "819,987,18",
            "117,168,530",
            "805,96,715",
            "346,949,466",
            "970,615,88",
            "941,993,340",
            "862,61,35",
            "984,92,344",
            "425,690,689",
        )
        val target = CircuitGenerator(input)

        target.connectAllJunctionBoxes()

        assertThat(calculateAnswerForPart2(target.lastConnection)).isEqualTo(25272)
    }
}
