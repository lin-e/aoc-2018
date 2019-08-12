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
    var state = ".".repeat(padding) + initial + ".".repeat(padding)
    (1..iterations).forEach {
        var nextState = ".."
        (2..(state.length - 3)).forEach { i ->
            nextState += rules.get(state.substring(i - 2, i + 3)) ?: "."
        }
        nextState += ".."
        state = nextState
    }
    println(state.indices.map {
        when (state[it]) {
            '#' -> it - padding
            else -> 0
        }
    }.sum())
}