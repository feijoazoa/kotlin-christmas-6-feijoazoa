package christmas

data class MenuItem(val name: String, val price: Int)

enum class MenuCategory {
    APPETIZER, MAIN, DESSERT, BEVERAGE
}

class Menu {
    private val menuItems = mapOf(
        MenuCategory.APPETIZER to listOf(
            MenuItem("양송이수프", 6000),
            MenuItem("타파스", 5500),
            MenuItem("시저샐러드", 8000)
        ),
        MenuCategory.MAIN to listOf(
            MenuItem("티본스테이크", 55000),
            MenuItem("바비큐립", 54000),
            MenuItem("해산물파스타", 35000),
            MenuItem("크리스마스파스타", 25000)
        ),
        MenuCategory.DESSERT to listOf(
            MenuItem("초코케이크", 15000),
            MenuItem("아이스크림", 5000)
        ),
        MenuCategory.BEVERAGE to listOf(
            MenuItem("제로콜라", 3000),
            MenuItem("레드와인", 60000),
            MenuItem("샴페인", 25000)
        )
    )

    fun isValidMenuOrder(order: String): Boolean {
        val items = order.split(",")
        if (items.size > 20) return false

        var beverageOnly = true
        val orderedItems = mutableSetOf<String>()
        for (item in items) {
            val nameQuantity = item.split("-")
            if (nameQuantity.size != 2) return false
            val name = nameQuantity[0].trim()
            val quantity = nameQuantity[1].trim().toIntOrNull() ?: return false
            if (quantity <= 0 || !menuItems.values.flatten().any { it.name == name }) return false
            if (orderedItems.contains(name)) return false
            if (beverageOnly && menuItems[MenuCategory.BEVERAGE]?.none { it.name == name } == true) {
                beverageOnly = false
            }
            orderedItems.add(name)
        }

        return !beverageOnly
    }


    fun getMenuItemsByCategory(category: MenuCategory): List<MenuItem> {
        return menuItems[category] ?: emptyList()
    }
}
