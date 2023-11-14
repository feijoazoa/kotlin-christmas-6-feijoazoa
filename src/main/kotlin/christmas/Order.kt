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