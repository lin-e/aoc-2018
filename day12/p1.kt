import java.io.File

val initial = "###..###....####.###...#..#...##...#..#....#.##.##.#..#.#..##.#####..######....#....##..#...#...#.#"

fun main() {
    val rules = mutableMapOf<String, String>()
    File("input.txt").forEachLine { line ->
        val parts = line.split(" ")
        rules.put(parts.get(0), parts.get(2))
    }
    val iterations = 20
    val padding = iterations * 2
    val state = ".".repeat(padding) + initial + ".".repeat(padding)
    for (i in 1..iterations) {

    }
}