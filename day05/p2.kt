import java.io.File

fun main() {
    val raw = File("input.txt").readText()
    val low = ('a'..'z').map { Character.toString(it) }.map { rem ->
        var old = ""
        var new = raw.replace(rem, "").replace(rem.toUpperCase(), "")
        while (old.length != new.length) {
            old = new
            ('a'..'z').map { Character.toString(it) }.forEach {
                new = new.replace(it + it.toUpperCase(), "").replace(it.toUpperCase() + it, "")
            }
        }
        new.length
    }.min() ?: 0
    println(low)
}