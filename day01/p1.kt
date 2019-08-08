import java.io.File

fun main() {
    var freq = 0
    File("input.txt").forEachLine {
        freq += it.toInt()
    }
    println(freq);
}