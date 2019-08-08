import java.io.File

fun main() {
    val lines = File("input.txt").readLines()
    for (i in lines.indices) {
        for (j in (i + 1)..(lines.size - 1)) {
            val difs = lines.get(i).indices.filter {
                lines.get(i).get(it) != lines.get(j).get(it)
            }
            if (difs.size == 1) {
                println(lines.get(i))
                println(lines.get(j))
            }
        }
    }
}