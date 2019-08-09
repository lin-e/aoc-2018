import java.io.File

fun main() {
    var claims = Array(1000000) { 0 }
    var candidates = ArrayList<Int>()
    (1..250).forEach { candidates.add(it) }
    File("input.txt").forEachLine { line ->
        val parts = line.split(" ")
        val i = parts.get(0).removePrefix("#").toInt()
        val pos = parts.get(2).split(":").get(0).split(",").map { it.toInt() }
        val size = parts.get(3).split("x").map { it.toInt() }
        for (x in 0..(size.get(0) - 1)) {
            for (y in 0..(size.get(1) - 1)) {
                val index = (pos.get(1) + y) * 1000 + pos.get(0) + x
                if (claims[index] == 0) {
                    claims[index] = i
                } else {
                    candidates.remove(i)
                    candidates.remove(claims[index])
                }
            }
        }
    }
    println(candidates)
}