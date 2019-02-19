package com.strangea.trip

class Sort{

    fun sort1(list: List<BoardingCard>):List<BoardingCard>{
        val originalList = arrayListOf<BoardingCard>()
        originalList.addAll(list)
        val newList = ArrayList<BoardingCard>()
        var found: Boolean
        for (card in list){
            found = false
            for(card2 in list){
                if(card.from == card2.to){
                    found = true
                    break
                }
            }
            if(!found){
                newList.add(card)
                originalList.remove(card)
                break
            }
        }
        return sort1Recursion(originalList, newList)
    }

    private fun sort1Recursion(list: ArrayList<BoardingCard>, newList: ArrayList<BoardingCard>):List<BoardingCard>{
        for(card in list){
            if(card.from == newList[newList.size-1].to){
                newList.add(card)
                list.remove(card)
                break
            }
        }
        if(list.isEmpty()){
            return newList
        }
        return sort1Recursion(list, newList)
    }

    fun sort2(list: List<BoardingCard>):List<BoardingCard>{
        val originalList = ArrayList<BoardingCard>()
        originalList.addAll(list)
        val newList = ArrayList<BoardingCard>()
        newList.add(originalList[0])
        originalList.remove(originalList[0])
        return sort2Recursion(originalList, newList)
    }

    private fun sort2Recursion(list: ArrayList<BoardingCard>, newList: ArrayList<BoardingCard>):List<BoardingCard>{
        for(card in list){
            if(card.from == newList[newList.size-1].to){
                newList.add(card)
                list.remove(card)
                break
            } else if(card.to == newList[0].from){
                newList.add(0, card)
                list.remove(card)
                break
            }
        }
        if(list.isEmpty()){
            return newList
        }
        return sort2Recursion(list, newList)
    }
}