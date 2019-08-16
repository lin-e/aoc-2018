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
    var coords = listOf<Int>()

    constructor (raw: String) {
        coords = raw.split(",").map { it.toInt() }
    }
    fun taxi(o: Star): Int {
        return (0..3).map { (o.coords[it] - coords[it]).absoluteValue }.sum()
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