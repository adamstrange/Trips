package com.strangea.trip

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BoardingCardViewModel: ViewModel() {

    val boardingCards = MutableLiveData<List<BoardingCard>>()

    init {
        val list = ArrayList<BoardingCard>()
        list.add(BoardingCard.TrainBoardingCard("Madrid", "Barcelona", "78A","45B"))
        list.add(BoardingCard.BusBoardingCard( "Barcelona", "Girona Airport", "airport"))
        list.add(BoardingCard.PlaneBoardingCard( "Girona Airport", "London", "SK455", "3A", "45B", "344"))
        list.add(BoardingCard.PlaneBoardingCard( "London", "New York JFK", "SK22", "7B","22"))
        boardingCards.postValue(list.shuffled())
    }

    fun sortList(algorithm : Int){
        boardingCards.value?.let {
            when(algorithm){
                SORT_1 -> {
                    val list = ArrayList<BoardingCard>()
                    list.addAll(Sort().sort1(it))
                    list.add(BoardingCard.DestinationBoardingCard())
                    boardingCards.postValue(list)
                }
                SORT_2 ->{
                    val list = ArrayList<BoardingCard>()
                    list.addAll(Sort().sort2(it))
                    list.add(BoardingCard.DestinationBoardingCard())
                    boardingCards.postValue(list)
                }
            }
        }
    }

    companion object {
        const val SORT_1 = 0
        const val SORT_2 = 1
    }
}
