import java.io.File

val tracks = mutableListOf<String>()
val carts = mutableListOf<Cart>()

fun main() {
    val input = File("input.txt").readLines()
    val h = input.size
    val w = input.get(0).length
    (0..(h - 1)).forEach { y ->
        var s = ""
        (0..(w - 1)).forEach { x ->
            val c = input.get(y)[x]
            s += when (c) {
                '/', '\\', '-', '|', '+', ' ' -> c.toString()
                else -> {
                    carts.add(Cart(x, y, c))
                    when (c) {
                        '<', '>' -> '-'
                        'v', '^' -> '|'
                        else -> ' '
                    }
                }
            }
        }
        tracks.add(s)
    }
    var collision = false
    while (!collision) {
        carts.sortedWith(CartCompare).forEach {
            it.tick()
            if (!collision && it.collide()) {
                println("(${it.x}, ${it.y})")
                collision = true
            }
        }
    }
    // tracks.forEach { println(it) }
}

enum class Direction { UP, DOWN, LEFT, RIGHT, NONE }

class Cart {
    var state: Int
    var x: Int
    var y: Int

    var d: Int

    init {
        state = 0
        x = 0
        y = 0
        d = -1
        // state = 0 -> left, 1 -> straight, 2 -> right
    }
    constructor (xPos: Int, yPos: Int, dir: Char) {
        x = xPos
        y = yPos
        d = when (dir) {
            '^' -> 0
            '>' -> 1
            'v' -> 2
            '<' -> 3
            else -> -1
        }
    }
    fun tick() {
        x += when (d) {
            3 -> -1
            1 -> 1
            else -> 0
        }
        y += when (d) {
            0 -> -1
            2 -> 1
            else -> 0
        }
        d = when (tracks.get(y)[x]) {
            '+' -> {
                state++
                when ((state - 1) % 3) {
                    0 -> (d + 3) % 4
                    1 -> d
                    2 -> (d + 1) % 4
                    else -> -1
                }
            }
            '/' -> (5 - d) % 4
            '\\' -> 3 - d
            else -> d
        }
    }

    fun collide(): Boolean {
        return carts.filter { it.x == x && it.y == y }.size > 1
    }
}

class CartCompare {
    companion object : Comparator<Cart> {
        override fun compare(a: Cart, b: Cart): Int = when {
            a.y != b.y -> a.y - b.y
            else -> a.x - b.x
        }
    }
}