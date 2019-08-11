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
    var workers = Array(5) { Worker() }
    var time = 0
    while (completed.size < 26) {
        workers.forEach {
            if (it.tick()) {
                completed.add(it.current)
            }
         }
        workers.forEach {
            if (it.remaining == 0) {
                val ready = requirements.keys.firstOrNull {
                    requirements.get(it)!!.all { it in completed }
                }
                if (ready != null) {
                    it.start(ready)
                    requirements.remove(ready)
                }
            }
        }
        workers.forEach { println("W: (${it.current}, ${it.remaining})") }
        println(completed.joinToString(separator = ""))
        println(time)
        time++
        println("---")
    }
    println(time - 1)
}

class Worker {
    var current: String
    var remaining: Int
    init {
        current = "."
        remaining = 0
    }
    fun tick(): Boolean {
        if (remaining > 0) {
            remaining--
            if (remaining == 0) {
                return true
            }
        }
        return false
    }
    fun start(s: String) {
        val c = s[0]
        current = s
        remaining = 61 + c.toInt() - 'A'.toInt()
    }
}