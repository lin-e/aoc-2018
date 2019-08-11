import java.io.File

fun main() {
    val stars = File("input.txt").readLines().map {
        val parts = it.split("<")
        val pos = parts.get(1).split(',')
        val vel = parts.get(2).split(',')
        Star(
            pos.get(0).trim(' ').toInt(),
            pos.get(1).split(">").get(0).trim(' ').toInt(),
            vel.get(0).trim(' ').toInt(),
            vel.get(1).split(">").get(0).trim(' ').toInt()
        )
    }
    var found = false
    var lastH = 0
    var ticks = 0
    while (!found) {
        val xs = stars.map { it.posX }
        val ys = stars.map { it.posY }
        val xMin = xs.min() ?: 0
        val xMax = xs.max() ?: 0
        val yMin = ys.min() ?: 0
        val yMax = ys.max() ?: 0
        val h = yMax - yMin + 1
        for (y in yMin..yMax) {
            for (x in xMin..xMax) {
                print(if (stars.any { it.posX == x && it.posY == y }) "#" else " ")
            }
            println()
        }
        continue
        if (lastH != 0 && h > lastH) {
            println(lastH)
            println(ticks)
            found = true
            for (y in yMin..yMax) {
                for (x in xMin..xMax) {
                    print(if (stars.any { it.posX == x && it.posY == y }) "#" else " ")
                }
                println()
            }
        } else {
            lastH = h
        }
        if (h == 8) {
            found = true
            for (y in yMin..yMax) {
                for (x in xMin..xMax) {
                    print(if (stars.any { it.posX == x && it.posY == y }) "#" else " ")
                }
                println()
            }
        }
        ticks++
        stars.forEach { it.tick() }
    }
}

class Star {
    var posX: Int
    var posY: Int
    var velX: Int
    var velY: Int
    constructor (pX: Int, pY: Int, vX: Int, vY: Int) {
        posX = pX
        posY = pY
        velX = vX
        velY = vY
    }
    fun tick() {
        posX += velX
        posY += velY
    }
}