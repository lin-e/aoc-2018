import kotlin.math.*
import java.io.File

fun main() {
    val points = File("input.txt").readLines().map { raw ->
        val parts = raw.split(" ")
        Pair(parts.get(0).removeSuffix(",").toInt(), parts.get(1).toInt())
    }
    val xs = points.map { it.first }
    val ys = points.map { it.second }
    val xMin = xs.min() ?: 0
    val xMax = xs.max() ?: 0
    val yMin = ys.min() ?: 0
    val yMax = ys.max() ?: 0
    val w = xMax - xMin + 1
    val h = yMax - yMin + 1
    val infinite = mutableListOf<Int>()
    var closest = Array(w * h) { -1 }
    for (x in xMin..xMax) {
        for (y in yMin..yMax) {
            val index = (y - yMin) * w + (x - xMin)
            val distances = points.map { taxicab(x, y, it.first, it.second) }
            val shortest = distances.min()
            val regions = points.indices.filter { distances[it] == shortest }
            if (regions.size == 1) {
                closest[index] = regions[0]
            }
            if (x == xMin || x == xMax || y == yMin || y == yMax) {
                regions.forEach { infinite.add(it) }
            }
        }
    }
    val sizes = points.indices.map { id ->
        closest.filter { it == id }.size
    }
    println(points.indices.filter { !(it in infinite) }.map { sizes[it] }.max())
}

fun taxicab(x1: Int, y1: Int, x2: Int, y2: Int): Int {
    return (x1 - x2).absoluteValue + (y1 - y2).absoluteValue
}