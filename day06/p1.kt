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
}

fun taxicab(x1: Int, y1: Int, x2: Int, y2: Int): Int {
    return (x1 - x2).absoluteValue + (y1 - y2).absoluteValue
}