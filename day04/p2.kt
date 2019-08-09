import java.io.File

fun main() {
    val events = mutableListOf<String>()
    val days = arrayListOf<Day>()
    val guards = hashMapOf<Int, Array<Int>>()
    File("input.txt").useLines { l -> events.addAll(l) }
    events.sort()
    events.forEach { line ->
        val parts = line.split(" ")
        when (parts.get(2)) {
            "Guard" -> {
                val g = parts.get(3).removePrefix("#").toInt()
                days.add(Day(g))
                guards.set(g, Array(60) { 0 })
            }
            "wakes", "falls" -> days.last().status(parts.get(1).split(":").get(1).removeSuffix("]").toInt(), parts.get(2))
        }
    }
    var guard = -1
    var highest = 0
    var minute = 0
    days.forEach { day ->
        for (i in 0..59) {
            var sleep = guards.get(day.guard)
            if (sleep != null) {
                sleep[i] += day.sleep[i]
                if (sleep[i] > highest) {
                    highest = sleep[i]
                    guard = day.guard
                    minute = i
                }
            }
        }
    }
    println(guard * minute)
}

class Day {
    var guard: Int = 0
    var sleep: Array<Int> = Array(60) { 0 }
    constructor (id: Int) {
        guard = id
    }
    fun status(min: Int, s: String) {
        for (i in min..59) {
            sleep[i] = if (s == "wakes") 0 else 1
        }
    }
}