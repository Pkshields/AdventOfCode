package dev.paulshields.aoc.year2025

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Day6Test {
    @Test
    fun `should pass provided test for day 6 part 1`() {
        val input = "123 328  51 64 \n" +
                " 45 64  387 23 \n" +
                "  6 98  215 314\n" +
                "*   +   *   +  "

        val result = sumOfCephalopodsMathsHomework(input)

        assertThat(result).isEqualTo(4277556)
    }

    @Test
    fun `should pass provided test for day 6 part 2`() {
        val input = "123 328  51 64 \n" +
                " 45 64  387 23 \n" +
                "  6 98  215 314\n" +
                "*   +   *   +  "

        val result = correctedSumOfCephalopodsMathsHomework(input)

        assertThat(result).isEqualTo(3263827)
    }
}
