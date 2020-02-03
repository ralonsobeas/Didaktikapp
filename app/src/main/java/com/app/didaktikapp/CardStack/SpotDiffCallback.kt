package com.app.didaktikapp.CardStack

import androidx.recyclerview.widget.DiffUtil

/**
 * Callback para la lista de cartas.
 * Uh! Apple-pen!
 * @author gennakk
 */
class SpotDiffCallback(
        private val old: List<Spot>,
        private val new: List<Spot>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return old.size
    }

    override fun getNewListSize(): Int {
        return new.size
    }

    override fun areItemsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        return old[oldPosition].id == new[newPosition].id
    }

    override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        return old[oldPosition] == new[newPosition]
    }

}