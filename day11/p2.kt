val input = 2568

fun main() {
    var levels = Array(300 * 300) { 0 }
    (1..300).forEach { x ->
        (1..300).forEach { y ->
            val index = (y - 1) * 300 + (x - 1)
            val rack = x + 10
            levels[index] = ((((input + (rack * y)) * rack) / 100) % 10) - 5
        }
    }
    var sizes = mutableMapOf<Int, List<Int>>()
    sizes.put(1, levels.toList())
    (2..300).forEach { s ->
        val dim = 301 - s
        val squares = (0..(dim * dim - 1)).map { flat ->
            val x = flat % dim
            val y = flat / dim
            val pIndex = y * (302 - s) + x
            sizes.get(s - 1)!!.get(pIndex) + (0..(s - 1)).map {
                levels[(y + s - 1) * 300 + (x + it)]
            }.sum() + (0..(s - 2)).map {
                levels[(y + it) * 300 + (x + s - 1)]
            }.sum()
        }
        sizes.put(s, squares)
    }
    val max = sizes.values.map { it.max()!! }.max()!!
    val size = sizes.keys.first { max in sizes.get(it)!! }
    val point = sizes.get(size)!!.indexOf(max)
    println("(${(point % (301 - size)) + 1}, ${(point / (301 - size)) + 1}, $size)")
}