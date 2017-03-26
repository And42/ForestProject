package ForestItems.AdditionalItems

import java.util.*

private val random = Random()

fun String.marginString() = this.split("\n").map { "  " + it }.joinToString("\n")

fun genRandom(max: Int) = random.nextInt(max)