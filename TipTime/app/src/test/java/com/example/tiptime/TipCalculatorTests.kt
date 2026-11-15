package com.example.tiptime

import org.junit.Assert.assertEquals
import org.junit.Test
import java.text.NumberFormat

class TipCalculatorTests {

    @Test
    fun calculateTip_20PercentNoRoundup() {
        // 1. Дано: счет 10.00, чаевые 20%
        val amount = 10.00
        val tipPercent = 20.00

        // 2. Ожидаем: 2.00 (в формате валюты)
        val expectedTip = NumberFormat.getCurrencyInstance().format(2)

        // 3. Вызываем нашу функцию из MainActivity
        val actualTip = calculateTip(amount = amount, tipPercent = tipPercent, false)

        // 4. Проверяем: ожидаемое == реальному
        assertEquals(expectedTip, actualTip)
    }
}