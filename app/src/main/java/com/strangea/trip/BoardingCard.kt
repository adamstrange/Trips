package com.strangea.trip

import java.lang.StringBuilder

abstract class BoardingCard(val from: String = "", val to: String = "", val name : String, val seat: String = ""){

    var description: String

    init {
        description = createDescription().toString()
    }

    open fun createDescription(): StringBuilder {
        val stringBuilder = takeText(StringBuilder())
        fromText(stringBuilder)
        toText(stringBuilder)
        seatingText(stringBuilder)

        return stringBuilder
    }

    private fun seatingText(stringBuilder: StringBuilder):StringBuilder{
        return if(!seat.isNullOrEmpty()){
            stringBuilder.append("Sit in seat ").append(seat).append(".")
        } else {
            stringBuilder.append("No seat assignment.")
        }
    }

    fun toText(stringBuilder: StringBuilder):StringBuilder{
        return stringBuilder.append(" to ").append(to).append(". ")
    }

    fun fromText(stringBuilder: StringBuilder):StringBuilder {
        return if (stringBuilder.isEmpty()) {
            stringBuilder.append("From ").append(from)
        } else {
            stringBuilder.append(" from ").append(from)
        }
    }

    abstract fun takeText(stringBuilder: StringBuilder):StringBuilder

    class DestinationBoardingCard:
        BoardingCard("", "", "", ""){

        override fun takeText(stringBuilder: StringBuilder): StringBuilder {
            return stringBuilder
        }

        override fun createDescription(): StringBuilder {
            return StringBuilder("You have arrived at your final destination.")
        }
    }

    class TrainBoardingCard(from: String, to: String, name: String, seat: String) : BoardingCard(from, to, name, seat) {
        override fun takeText(stringBuilder: StringBuilder): StringBuilder {
            return stringBuilder.append("Take train").append(" ").append(name)
        }
    }

    class BusBoardingCard(from: String, to: String, name: String, seat: String = "") : BoardingCard(from, to, name, seat) {
        override fun takeText(stringBuilder: StringBuilder): StringBuilder {
            return stringBuilder.append("Take the ").append(name).append(" bus")
        }
    }

    class PlaneBoardingCard(from: String = "", to: String = "", name : String, seat: String = "", val gate: String, val baggageDrop: String = ""):
        BoardingCard(from, to, name, seat) {

        init {
            description = createDescription().toString()
        }

        override fun takeText(stringBuilder: StringBuilder): StringBuilder {
            return stringBuilder.append(", take flight ").append(name)
        }

        override fun createDescription(): StringBuilder {
            val stringBuilder = fromText(StringBuilder())
            takeText(stringBuilder)
            toText(stringBuilder)
            stringBuilder.append("Gate ").append(gate).append(", seat ").append(seat).append(". ")

            if(baggageDrop.isNullOrEmpty()){
               stringBuilder.append("Baggage will be automatically transferred from your last leg.")
            } else {
                stringBuilder.append("Baggage drop at ticket counter ").append(baggageDrop).append(".")
            }

            return stringBuilder
        }
    }

}