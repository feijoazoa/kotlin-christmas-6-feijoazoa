package christmas
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DiscountTest {
    private val order = Order(Menu(), "크리스마스파스타-2,레드와인-1,초코케이크-1")
    private val discount = Discount(order)

    @Test
    fun `크리스마스 D-Day 할인 금액을 계산한다`() {
        val dayOfMonth = 5
        val expectedDiscount = 1000 + (dayOfMonth - 1) * 100
        val actualDiscount = discount.christmasDdayDiscount(dayOfMonth)
        assertThat(actualDiscount).isEqualTo(expectedDiscount)
    }

    @Test
    fun `주중 할인 금액을 계산한다`() {
        val expectedDiscount = 1 * 2023
        val actualDiscount = discount.weekdayDiscount()
        assertThat(actualDiscount).isEqualTo(expectedDiscount)
    }

    @Test
    fun `주말 할인 금액을 계산한다`() {
        val expectedDiscount = 2 * 2023
        val actualDiscount = discount.weekendDiscount()
        assertThat(actualDiscount).isEqualTo(expectedDiscount)
    }
}
