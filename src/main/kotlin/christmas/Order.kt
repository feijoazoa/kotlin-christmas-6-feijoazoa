package christmas

class Order(private val menu: Menu, order: String) {
    private val orderedItems: Map<String, Int>

    init {
        val items = mutableMapOf<String, Int>()
        order.split(",").forEach {
            val (name, quantity) = it.split("-")
            items[name.trim()] = quantity.trim().toInt()
        }
        orderedItems = items
    }

    fun getTotalAmount(): Int {
        return orderedItems.entries.sumOf { (name, quantity) ->
            menu.getPrice(name) * quantity
        }
    }

    fun getMenuNames(): List<String> {
        return orderedItems.keys.toList()
    }
}