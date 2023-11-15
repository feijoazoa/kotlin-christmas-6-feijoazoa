package christmas

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
class EventResultTest {
    @Test
    fun `EventResult 객체가 정상적으로 생성된다`() {
        val benefits = listOf("benefit1", "benefit2")
        val gifts = listOf("gift1", "gift2")
        val totalDiscount = 5000
        val totalAmountAfterDiscount = 10000

        val eventResult = EventResult(benefits, gifts, totalDiscount, totalAmountAfterDiscount)

        assertThat(eventResult.benefits).isEqualTo(benefits)
        assertThat(eventResult.gifts).isEqualTo(gifts)
        assertThat(eventResult.totalDiscount).isEqualTo(totalDiscount)
        assertThat(eventResult.totalAmountAfterDiscount).isEqualTo(totalAmountAfterDiscount)
    }
}