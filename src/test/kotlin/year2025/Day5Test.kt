package dev.paulshields.aoc.year2025

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Day5Test {
    @Test
    fun `should pass provided test for day 4 part 1`() {
        val input = listOf(
            "3-5",
            "10-14",
            "16-20",
            "12-18",
            "",
            "1",
            "5",
            "8",
            "11",
            "17",
            "32",
        )

        val result = countNumberOfFreshIngredients(input)

        assertThat(result).isEqualTo(3)
    }

    @Test
    fun `should pass provided test for day 4 part 2`() {
        val input = listOf(
            "3-5",
            "10-14",
            "16-20",
            "12-18",
            "",
            "1",
            "5",
            "8",
            "11",
            "17",
            "32",
        )

        val result = countNumberOfValidFreshIngredientIDs(input)

        assertThat(result).isEqualTo(14)
    }
}
