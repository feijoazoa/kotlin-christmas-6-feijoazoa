package christmas

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
class OrderTest {
    private val menu = Menu()
    private val order = Order(menu, "크리스마스파스타-2,레드와인-1,초코케이크-1")

    @Test
    fun `할인 전 주문 총액을 계산한다`() {
        val totalPrice = order.getTotalAmount()
        val expectedTotalAmount = 125000
        assertThat(totalPrice).isEqualTo(expectedTotalAmount)
    }

    @Test
    fun `주문한 메뉴 이름 반환한다`() {
        val menuNames = order.getMenuNames()
        assertThat(menuNames).containsExactlyInAnyOrder("크리스마스파스타", "레드와인", "초코케이크")
    }
    @Test
    fun `디저트 개수를 계산한다`() {
        val dessertCount = order.getDessertCount()
        val expectedDessertCount = 1
        assertThat(dessertCount).isEqualTo(expectedDessertCount)
    }

    @Test
    fun `메인 개수를 계산한다`() {
        val mainCount = order.getMainCount()
        val expectedMainCount = 2
        assertThat(mainCount).isEqualTo(expectedMainCount)
    }
}
