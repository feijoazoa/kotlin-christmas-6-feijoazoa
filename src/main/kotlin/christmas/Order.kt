package christmas

class Order (val date: Int, val menu: List<Menu>){

    private val menus = mutableListOf<Menu>()

    fun addMenu(menu: Menu): {
        menus.add(menu)
        return menus
    }

    fun calculateOriginTotalPrice(menu: Menu) {
        val originTotalPrice = 0
        for ( item in menus) {
            total += item.price * item.quantity
        }
        return price
    }

}