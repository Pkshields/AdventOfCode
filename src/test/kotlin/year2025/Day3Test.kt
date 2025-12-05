package dev.paulshields.aoc.year2025

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Day3Test {
    @Test
    fun `should pass provided test for day 3 part 1`() {
        val input = listOf(
            "987654321111111",
            "811111111111119",
            "234234234234278",
            "818181911112111",
        )

        val result = calculateTotalMaxJoltageFromAllBanks(input)

        assertThat(result).isEqualTo(357)
    }

    @Test
    fun `should pass provided test for day 3 part 2`() {
        val input = listOf(
            "987654321111111",
            "811111111111119",
            "234234234234278",
            "818181911112111",
        )

        val result = calculateTotalMaxOverrideJoltageFromAllBanks(input)

        assertThat(result).isEqualTo(3121910778619)
    }
}
