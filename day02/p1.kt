import java.io.File

fun main() {
    var two = 0
    var three = 0
    File("input.txt").forEachLine { line ->
        val map = HashMap<Char, Int>()
        line.forEach { chr: Char ->
            map.set(chr, (map.get(chr) ?: 0) + 1)
        }
        if (map.values.any { it == 2 }) two++
        if (map.values.any { it == 3 }) three++
    }
    println(two * three)
}