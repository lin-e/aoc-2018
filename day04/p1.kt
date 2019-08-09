import java.io.File

fun main() {
    val events = mutableListOf<String>()
    val days = arrayListOf<Day>()
    val guards = hashMapOf<Int, Int>()
    File("input.txt").useLines { l -> events.addAll(l) }
    events.sort()
    events.forEach { line ->
        val parts = line.split(" ")
        when (parts.get(2)) {
            "Guard" -> {
                val g = parts.get(3).removePrefix("#").toInt()
                days.add(Day(g))
                guards.set(g, 0)
            }
            "wakes", "falls" -> days.last().status(parts.get(1).split(":").get(1).removeSuffix("]").toInt(), parts.get(2))
        }
    }
    days.forEach { day ->
        guards.set(day.guard, (guards.get(day.guard) ?: 0) + day.sleep.sum())
    }
    var mins = hashMapOf<Int, Int>()
    val guard = guards.toList().sortedBy { (_, v) -> v }.last().component1()
    val shifts = days.filter { d -> d.guard == guard }
    (0..59).forEach { mins.set(it, shifts.map { d -> d.sleep[it]}.sum()) }
    println(mins.toList().sortedBy { (_, v) -> v }.last().component1() * guard)

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