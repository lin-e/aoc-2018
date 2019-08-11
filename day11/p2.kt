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
    var sizes = mutableMapOf<Int, List<Int>>()
    for (s in 1..300) {
        println(s)
        val dim = 301 - s
        val squares = (0..(dim * dim - 1)).map{ flat ->
            val x = flat % dim
            val y = flat / dim
            (0..(s * s - 1)).map { off ->
                val xp = x + (off % s)
                val yp = y + (off / s)
                levels[yp * 300 + xp]
            }.sum()
        }
        sizes.put(s, squares)
    }
    val max = sizes.values.map { it.max()!! }.max()!!
    val size = sizes.keys.first { max in sizes.get(it)!! }
    val point = sizes.get(size)!!.indexOf(max)
    println("(${(point % (301 - size)) + 1}, ${(point / (301 - size)) + 1}, $size)")
    // println("(${(max % 298) + 1}, ${(max / 298) + 1})")
}