package christmas

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate

class EvetTest {

    private val lowPriceOrder = Order(Menu(), "양송이수프-1")
    private val christmasDate = LocalDate.of(2023, 12, 25)
    private val lowPriceEvent = Event(lowPriceOrder, christmasDate)


    @Test
    fun `주문 금액이 10,000원 미만일 경우 이벤트 적용 결과를 반환한다`() {
        val eventResult = lowPriceEvent.applyEvents()

        assertThat(eventResult.benefits).isEmpty()
        assertThat(eventResult.gifts).isEmpty()
        assertThat(eventResult.totalDiscount).isEqualTo(0)
        assertThat(eventResult.totalAmountAfterDiscount).isEqualTo(lowPriceOrder.getTotalAmount())
    }

}