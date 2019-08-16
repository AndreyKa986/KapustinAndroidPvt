package by.letum8658.homework

import by.letum8658.homework.dz13.ExtraTask
import org.junit.Assert.assertEquals
import org.junit.Test

class ExtraTaskTest {

    private val codeList: List<List<String>> = listOf(listOf("apple", "apple"), listOf("banana", "anything", "banana"))
    private val shoppingCart: List<String> = listOf("orange", "apple", "apple", "banana", "orange", "banana")
    private val shoppingCart2: List<String> =
        listOf("apple", "apple", "orange", "orange", "banana", "apple", "banana", "banana")
    private val codeList3: List<List<String>> =
        listOf(listOf("orange"), listOf("apple", "apple"), listOf("banana", "orange", "apple"), listOf("banana"))
    private val shoppingCart3: List<String> = listOf("orange", "apple", "apple", "banana", "orange", "apple", "banana")
    private val codeList4: List<List<String>> = listOf(
        listOf("apple", "apricot"), listOf("banana", "anything", "guava"), listOf("papaya", "anything")
    )
    private val shoppingCart4: List<String> = listOf("banana", "orange", "guava", "apple", "apricot", "papaya", "kiwi")

    private val testClass = ExtraTask()

    @Test
    fun checkWinIsCorrect() {
        assertEquals(1, testClass.checkWin(codeList, shoppingCart))
        assertEquals(1, testClass.checkWin(codeList, shoppingCart2))
        assertEquals(1, testClass.checkWin(codeList3, shoppingCart3))
        assertEquals(0, testClass.checkWin(codeList4, shoppingCart4))
    }
}