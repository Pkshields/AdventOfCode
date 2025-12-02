package dev.paulshields.aoc.year2025

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Day1Test {
    @Test
    fun `should pass provided test for day 1 part 1`() {
        val input =
            """
            L68
            L30
            R48
            L5
            R60
            L55
            L1
            L99
            R14
            L82
            """.trimIndent().split("\n")

        val result = calculateSecretDoorPassword(input)

        assertThat(result).isEqualTo(3)
    }

    @Test
    fun `should pass provided test for day 1 part 2`() {
        val input =
            """
            L68
            L30
            R48
            L5
            R60
            L55
            L1
            L99
            R14
            L82
            """.trimIndent().split("\n")

        val result = calculateSecretDoorPasswordWithMethod0x434C49434B(input)

        assertThat(result).isEqualTo(6)
    }
}
