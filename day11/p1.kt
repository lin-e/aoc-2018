val input = 2568

fun main() {
    var levels = Array(300 * 300) { 0 }
    for (x in 1..300) {
        for (y in 1..300) {
            val index = (y - 1) * 300 + (x - 1)
            val rack = x + 10
            levels[index] = ((((input + (rack * y)) * rack) / 100) % 10) - 5
        }
    }
    val squares = (0..(298 * 298 - 1)).map{ flat ->
        val x = flat % 298
        val y = flat / 298
        (0..8).map { off ->
            val xp = x + (off % 3)
            val yp = y + (off / 3)
            levels[yp * 300 + xp]
        }.sum()
    }
    val max = squares.indexOf(squares.max())
    println("(${(max % 298) + 1}, ${(max / 298) + 1})")
}