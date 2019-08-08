import java.io.File

fun main() {
    var freq = 0
    var visited = ArrayList<Int>()
    var found = false
    while (!found) {
        File("input.txt").forEachLine {
            if (!found) {
                freq += it.toInt()
                if (freq in visited) {
                    println(freq)
                    found = true
                }
                visited.add(freq)
            }
        }
    }
}