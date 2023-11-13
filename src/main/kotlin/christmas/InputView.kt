package christmas

import camp.nextstep.edu.missionutils.Console

class InputView {
    fun readDate(): Int {
        println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.")
        println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)")
        while (true) {
            val inputDate = Console.readLine() ?: ""
            try {
                validateDateInput(inputDate)
                return inputDate.toInt()
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
    }

    fun getOrder(menu: Menu): String {
        while (true) {
            println("주문할 메뉴와 개수를 입력해주세요 (e.g. 해산물파스타-2,레드와인-1,초코케이크-1): ")
            val order = Console.readLine()?.trim()
            if (order != null && menu.isValidMenuOrder(order)) {
                return order
            } else {
                println("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.")
            }
        }
    }
    private fun validateDateInput(inputDate: String) {
        require(inputDate.isNotBlank()) { EMPTY_INPUT_ERROR }
        require(inputDate.toIntOrNull() != null) { NON_NUMERIC_INPUT_ERROR }
        val validDate = inputDate.toInt()
        require(validDate in 1..31) { INVALID_DATE_INPUT_ERROR }
    }

    companion object {
        private const val EMPTY_INPUT_ERROR = "[ERROR] 값을 입력해 주세요."
        private const val NON_NUMERIC_INPUT_ERROR = "[ERROR] 숫자만 입력해 주세요."
        private const val INVALID_DATE_INPUT_ERROR = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."
    }
}

