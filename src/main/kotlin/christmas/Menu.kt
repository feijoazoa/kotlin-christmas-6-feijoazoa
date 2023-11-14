package christmas

class Menu {

    private val appetizers = mapOf("양송이수프" to 6000, "타파스" to 5500, "시저샐러드" to 8000)
    private val mains = mapOf("티본스테이크" to 55000, "바비큐립" to 54000, "해산물파스타" to 35000, "크리스마스파스타" to 25000)
    private val desserts = mapOf("초코케이크" to 15000, "아이스크림" to 5000)
    private val drinks = mapOf("제로콜라" to 3000, "레드와인" to 60000, "샴페인" to 25000)
    private val menu = appetizers + mains + desserts + drinks

    fun isValidMenuOrder(order: String): Boolean {
        val orderedItems = order.split(",")
        val orderedItemNames = orderedItems.map { it.split("-")[0] }

        if (orderedItems.size > 20 || orderedItems.size != orderedItemNames.distinct().size || orderedItemNames.all { it in drinks }) {
            return false
        }

        for (item in orderedItems) {
            val (name, quantity) = item.split("-")
            if (name !in menu.keys || quantity.toIntOrNull() == null || quantity.toInt() <= 0) {
                return false
            }
        }
        return true
    }
    fun getAppetizers(): Map<String, Int> {
        return appetizers
    }

    fun getMains(): Map<String, Int> {
        return mains
    }

    fun getDesserts(): Map<String, Int> {
        return desserts
    }

    fun getDrinks(): Map<String, Int> {
        return drinks
    }
    fun getPrice(name: String): Int {
        return menu[name] ?: 0
    }
}
