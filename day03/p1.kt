import java.io.File

fun main() {
    var claims = Array(1000000) { 0 }
    File("input.txt").forEachLine { line ->
        val parts = line.split(" ")
        val pos = parts.get(2).split(":").get(0).split(",").map { it.toInt() }
        val size = parts.get(3).split("x").map { it.toInt() }
        for (x in 0..(size.get(0) - 1)) {
            for (y in 0..(size.get(1) - 1)) {
                claims[(pos.get(1) + y) * 1000 + pos.get(0) + x]++
            }
        }
    }
    println((claims.filter { it > 1 }).size)
}