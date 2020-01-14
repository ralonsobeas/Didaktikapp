package com.app.didaktikapp.CardStack


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