package dev.paulshields.aoc.year2025

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Day4Test {
    @Test
    fun `should pass provided test for day 4 part 1`() {
        val input = listOf(
            "..@@.@@@@.",
            "@@@.@.@.@@",
            "@@@@@.@.@@",
            "@.@@@@..@.",
            "@@.@@@@.@@",
            ".@@@@@@@.@",
            ".@.@.@.@@@",
            "@.@@@.@@@@",
            ".@@@@@@@@.",
            "@.@.@@@.@.",
        )

        val result = countInitialForkliftAccessibleRollsOfPaper(input)

        assertThat(result).isEqualTo(13)
    }

    @Test
    fun `should pass provided test for day 4 part 2`() {
        val input = listOf(
            "..@@.@@@@.",
            "@@@.@.@.@@",
            "@@@@@.@.@@",
            "@.@@@@..@.",
            "@@.@@@@.@@",
            ".@@@@@@@.@",
            ".@.@.@.@@@",
            "@.@@@.@@@@",
            ".@@@@@@@@.",
            "@.@.@@@.@.",
        )

        val result = countTotalNumberOfRollsOfPaperMoved(input)

        assertThat(result).isEqualTo(43)
    }
}
