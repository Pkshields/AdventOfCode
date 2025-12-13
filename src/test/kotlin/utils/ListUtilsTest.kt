package dev.paulshields.aoc.utils

import assertk.assertThat
import assertk.assertions.containsExactly
import assertk.assertions.hasSize
import assertk.assertions.isEqualTo
import assertk.assertions.isNotEqualTo
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class ListUtilsTest {
    @Nested
    inner class PopTests {
        @Test
        fun `should return first element from list when popping element`() {
            val target = mutableListOf(1, 2, 3, 4, 5)

            val result = target.pop()

            assertThat(result).isEqualTo(1)
        }

        @Test
        fun `should remove first element from list when popping element`() {
            val target = mutableListOf(1, 2, 3, 4, 5)

            target.pop()

            assertThat(target.first()).isNotEqualTo(1)
            assertThat(target).hasSize(4)

        }
    }

    @Nested
    inner class PushToFrontTests {
        @Test
        fun `should push new element to the front of the list`() {
            val target = mutableListOf(1, 2, 3, 4, 5)

            target.pushToFront(6)

            assertThat(target.first()).isEqualTo(6)
        }

        @Test
        fun `should not remove any elements when pushing new element to the front of the list`() {
            val target = mutableListOf(1, 2, 3, 4, 5)

            target.pushToFront(6)

            assertThat(target).containsExactly(6, 1, 2, 3, 4, 5)
        }
    }
}
