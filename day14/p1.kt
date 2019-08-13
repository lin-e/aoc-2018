val input = 635041

fun main() {
    val recipes = mutableListOf<Int>()
    var e1 = 0
    var e2 = 1
    recipes.add(3)
    recipes.add(7)
    while (recipes.size < input + 10) {
        var sum = recipes.get(e1) + recipes.get(e2)
        if (sum > 9) {
            sum -= 10
            recipes.add(1)
        }
        recipes.add(sum)
        e1 = (e1 + recipes.get(e1) + 1) % recipes.size
        e2 = (e2 + recipes.get(e2) + 1) % recipes.size
    }
    (input..(input + 9)).forEach { print(recipes.get(it)) }
}