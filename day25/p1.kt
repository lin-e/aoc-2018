import kotlin.math.*
import java.io.File

fun main() {
    val stars = File("input.txt").readLines().map { Star(it) }
    var constellations = mutableListOf<Constellation>()
    stars.forEach { s ->
        val candidates = constellations.filter { c -> c.stars.any { it.taxi(s) <= 3 } }
        when (candidates.size) {
            0 -> constellations.add(Constellation(s))
            else -> candidates.forEach { it.add(s) }
        }
    }
    stars.forEach { s ->
        val groups = constellations.filter { it.stars.contains(s) }
        if (groups.size > 1) {
            (1..(groups.size - 1)).forEach {
                constellations.remove(groups.get(it))
                groups.get(0).merge(groups.get(it))
            }
        }
    }
    println(constellations.size)
}

class Star {
    var w: Int
    var x: Int
    var y: Int
    var z: Int

    constructor (raw: String) {
        val parts = raw.split(",")
        w = parts.get(0).toInt()
        x = parts.get(1).toInt()
        y = parts.get(2).toInt()
        z = parts.get(3).toInt()
    }
    fun taxi(o: Star): Int {
        return (o.w - w).absoluteValue + (o.x - x).absoluteValue + (o.y - y).absoluteValue + (o.z - z).absoluteValue;
    }
}

class Constellation {
    var stars: MutableList<Star>

    init {
        stars = mutableListOf<Star>()
    }
    constructor (s: Star) {
        add(s)
    }
    fun add(s: Star) {
        stars.add(s)
    }
    fun merge(c: Constellation) {
        c.stars.forEach { add(it) }
    }
}