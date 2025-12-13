package dev.paulshields.aoc.utils

fun <T> MutableList<T>.pop() = this.first().also { this.remove(it) }

fun <T> MutableList<T>.pushToFront(element: T) = this.add(0, element)
