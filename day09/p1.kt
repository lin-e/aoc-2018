val players = 400
// val last = 7186400 // - part 2
val last = 71864

fun main() {
    var lowest = 1
    var player = 0
    var current = 0
    var scores = Array(players) { 0 }
    var circle = mutableListOf<Int>()
    circle.add(0)
    while (lowest < last) {
        if (lowest % 23 == 0) {
            scores[player] += lowest
            current = (current + circle.size - 7) % circle.size
            scores[player] += circle[current]
            circle.removeAt(current)
        } else {
            current += 2
            if (current == circle.size) {
                circle.add(lowest)
            } else {
                current = current % circle.size
                circle.add(current, lowest)
            }
        }
        lowest++
        player = (player + 1) % players
        println("${lowest.toDouble() / last}")
    }
    println(scores.max())
}