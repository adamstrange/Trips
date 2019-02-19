package com.strangea.trip

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(sdk = intArrayOf(27))
@RunWith(RobolectricTestRunner::class)
class BoardingCardViewModelTest{

    @Test
    fun testSort1(){
        repeat(5) {
            val classUnderTest = BoardingCardViewModel()
            classUnderTest.sortList(BoardingCardViewModel.SORT_1)
            assertListInCorrectOrder(classUnderTest)
        }
    }

    @Test
    fun testSort2(){
        repeat(5) {
            val classUnderTest = BoardingCardViewModel()
            classUnderTest.sortList(BoardingCardViewModel.SORT_2)
            assertListInCorrectOrder(classUnderTest)
        }
    }

    private fun assertListInCorrectOrder(classUnderTest: BoardingCardViewModel){
        assertEquals("Madrid", classUnderTest.boardingCards.value!![0].from)
        assertEquals("Barcelona", classUnderTest.boardingCards.value!![1].from)
        assertEquals("Girona Airport", classUnderTest.boardingCards.value!![2].from)
        assertEquals("London", classUnderTest.boardingCards.value!![3].from)
    }
}