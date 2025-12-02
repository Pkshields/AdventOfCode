package dev.paulshields.aoc.utils

import assertk.assertThat
import assertk.assertions.isEmpty
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class FileUtilsTest {
    private val readFileAsStringTestFileLocation = "/ReadFileAsStringTestFile.txt"
    private val readFileAsStringWithWhitespaceTestFileLocation = "/ReadFileAsStringTestFileWithWhitespace.txt"
    private val readFileAsStringListTestFileLocation = "/ReadFileAsStringListTestFile.txt"
    private val brokenFileLocation = "/FileThatDoesNotExist.txt"
    private val contentsOfReadFileAsStringTestFile = "December is far too busy!"
    private val contentsOfReadFileAsStringListTestFile = listOf("December", "is", "far", "too", "busy!")

    @Nested
    inner class ReadFileAsStringTests {
        @Test
        fun `should read contents of file`() {
            val contents = readFileAsString(readFileAsStringTestFileLocation)

            assertThat(contents).isEqualTo(contentsOfReadFileAsStringTestFile)
        }

        @Test
        fun `should trim contents of file`() {
            val contents = readFileAsString(readFileAsStringWithWhitespaceTestFileLocation)

            assertThat(contents).isEqualTo(contentsOfReadFileAsStringTestFile)
        }

        @Test
        fun `should handle file not found`() {
            val contents = readFileAsString(brokenFileLocation)

            assertThat(contents).isEmpty()
        }
    }

    @Nested
    inner class ReadFileAsStringListTests {
        @Test
        fun `should read contents of file as a string list`() {
            val contents = readFileAsStringList(readFileAsStringListTestFileLocation)

            assertThat(contents).isEqualTo(contentsOfReadFileAsStringListTestFile)
        }

        @Test
        fun `should handle file not found`() {
            val contents = readFileAsStringList(brokenFileLocation)

            assertThat(contents).isEqualTo(emptyList())
        }
    }
}
