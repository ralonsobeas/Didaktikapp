package com.app.didaktikapp.CardStack

/**
 * Clase Spot para el lugar e introducirlo en la carta.
 * I have a pen, I have a apple
 * @author gennakk
 */
data class Spot(
        val id: Long ,
        val name: String,
        val city: String,
        val url: Int
) {
    companion object {
        private var counter = 0L
    }
}