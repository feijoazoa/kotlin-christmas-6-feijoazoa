package christmas

import camp.nextstep.edu.missionutils.Console

class InputView {
    fun readDate(): Int {
        println(WELCOME_MESSAGE)
        println(REQUEST_DATE_INPUT_MESSAGE)
        while (true) {
            val inputDate = Console.readLine() ?: ""
            if (isValidDate(inputDate)) return inputDate.toInt()
        }
    }
    private fun isValidDate(inputDate: String): Boolean {
        return try {
            validateDateInput(inputDate)
            true
        } catch (e: IllegalArgumentException) {
            println(e.message)
            false
        }
    }

    fun getOrder(menu: Menu): String {
        while (true) {
            println(REQUEST_MENU_ORDER_MESSAGE)
            val order = Console.readLine()?.trim()
            if (isValidOrder(order, menu)) return order!!
            println(INVALID_ORDER_INPUT_ERROR)
        }
    }
    private fun isValidOrder(order: String?, menu: Menu): Boolean {
        return order != null && menu.isValidMenuOrder(order)
    }
    private fun validateDateInput(inputDate: String) {
        require(inputDate.isNotBlank()) { INVALID_DATE_INPUT_ERROR }
        require(inputDate.toIntOrNull() != null) { INVALID_DATE_INPUT_ERROR }
        val validDate = inputDate.toInt()
        require(validDate in VALID_DATE_RANGE) { INVALID_DATE_INPUT_ERROR }
    }


    companion object {
        private const val WELCOME_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."
        private const val REQUEST_DATE_INPUT_MESSAGE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"
        private const val REQUEST_MENU_ORDER_MESSAGE = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"
        internal const val INVALID_DATE_INPUT_ERROR = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."
        internal const val INVALID_ORDER_INPUT_ERROR = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."
        private val VALID_DATE_RANGE = 1..31
    }
}

