import java.io.File

fun main() {
    val requirements = mutableMapOf<String, MutableList<String>>()
    ('A'..'Z').map { it.toString() }.forEach {
        requirements.put(it, mutableListOf())
    }
    val completed = mutableListOf<String>()
    File("input.txt").forEachLine { line ->
        val parts = line.split(" ")
        val step = parts.get(7)
        val pre = parts.get(1)
        requirements.get(step)!!.add(pre)
    }
    while (requirements.isNotEmpty()) {
        val done = requirements.keys.firstOrNull {
            requirements.get(it)!!.all { it in completed }
        }!!
        completed.add(done)
        requirements.remove(done)
    }
    println(completed.joinToString(separator = ""))
}