package dev.paulshields.aoc.year2025

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class Day2Test {
    @Test
    fun `should pass provided test for day 2 part 1`() {
        val input =
            "11-22,95-115,998-1012,1188511880-1188511890,222220-222224," +
                "1698522-1698528,446443-446449,38593856-38593862,565653-565659," +
                "824824821-824824827,2121212118-2121212124"

        val result = findSumOfProductIdsContainingPatternRepeatedTwice(input)

        assertThat(result).isEqualTo(1227775554)
    }

    @ParameterizedTest
    @CsvSource(
        "11-22,33",
        "95-115,99",
        "998-1012,1010",
        "1188511880-1188511890,1188511885",
        "222220-222224,222222",
        "1698522-1698528,0",
        "446443-446449,446446",
        "38593856-38593862,38593859",
        "565653-565659,0",
        "824824821-824824827,0",
        "2121212118-2121212124,0",
    )
    fun `should find the invalid product ids for each range`(inputRange: String, expectedResult: Long) {
        val result = findSumOfProductIdsContainingPatternRepeatedTwice(inputRange)

        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    fun `should pass provided test for day 2 part 2`() {
        val input =
            "11-22,95-115,998-1012,1188511880-1188511890,222220-222224," +
                "1698522-1698528,446443-446449,38593856-38593862,565653-565659," +
                "824824821-824824827,2121212118-2121212124"

        val result = findSumOfProductIdsContainingOnlyRepeatedPattern(input)

        assertThat(result).isEqualTo(4174379265)
    }
}
