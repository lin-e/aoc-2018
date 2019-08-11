import java.io.File

var index = 0
var dat = listOf<Int>()

fun main() {
    dat = File("input.txt").readText().split(" ").map { it.toInt() }
    val root = Node()
    println(root.value())
}

class Node {
    var header: Pair<Int, Int>
    val children: ArrayList<Node>
    val meta: ArrayList<Int>

    init {
        children = ArrayList<Node>()
        meta = ArrayList<Int>()
    }
    constructor () {
        header = Pair(dat[index], dat[index + 1])
        index += 2
        if (!leaf()) {
            for (c in 1..(header.first)) {
                children.add(Node())
            }
        }
        for (m in 1..(header.second)) {
            meta.add(dat[index])
            index++
        }
    }
    fun value(): Int {
        return if (header.second == 0) meta.sum() else meta.map {
            when (it) {
                in 1..(children.size) -> children.get(it - 1).value()
                else -> 0
            }
        }.sum()
    }
}