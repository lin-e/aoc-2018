import java.io.File

fun main() {
    println(File("input.txt").readLines().map { it.toInt() }.sum())
}