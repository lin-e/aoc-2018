import java.io.File

fun main() {
    var old = ""
    var new = File("input.txt").readText()
    while (old.length != new.length) {
        old = new
        ('a'..'z').map { Character.toString(it) }.forEach {
            new = new.replace(it + it.toUpperCase(), "").replace(it.toUpperCase() + it, "")
        }
    }
    println(new.length)
}