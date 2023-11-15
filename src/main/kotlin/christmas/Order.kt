package christmas

class Order(private val menu: Menu, order: String) {
    val orderedItems: Map<String, Int>

    init {
        val items = mutableMapOf<String, Int>()
        order.split(",").forEach { item ->
            val (name, quantity) = item.split("-").map(String::trim)
            items[name] = quantity.toInt()
        }
        orderedItems = items.toMap()
    }

    fun getTotalAmount(): Int {
        return orderedItems.entries.sumOf { (name, quantity) ->
            menu.getPrice(name) * quantity
        }
    }

    fun getMenuNames(): List<String> = orderedItems.keys.toList()

    fun getDessertCount(): Int {
        return orderedItems.entries.sumOf { (menuName, quantity) ->
            if (menu.getDesserts().containsKey(menuName)) quantity else 0
        }
    }

    fun getMainCount(): Int {
        return orderedItems.entries.sumOf { (menuName, quantity) ->
            if (menu.getMains().containsKey(menuName)) quantity else 0
        }
    }
}