package com.strangea.trip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.boarding_card.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val adapter = BoardingCardAdapter(BoardingCardCallback())
        recyclerView.adapter = adapter

        val spinnerList = listOf<String>("Sort 1", "Sort 2")
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerList)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sortSpinner.adapter = spinnerAdapter

        val observer = Observer<List<BoardingCard>> { cards ->
            progressBar.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE

            if(sortView.visibility != View.VISIBLE){
                cardStateTextView.text = "Sorted trips:"
            }
            adapter.submitList(cards)
        }

        val viewModel = ViewModelProviders.of(this).get(BoardingCardViewModel::class.java)
        viewModel.boardingCards.observe(this, observer)
    }

    fun onClickSortButton(view: View){
        progressBar.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE
        sortView.visibility = View.GONE
        ViewModelProviders.of(this).get(BoardingCardViewModel::class.java).sortList(sortSpinner.selectedItemPosition)
    }

    class BoardingCardAdapter constructor(diffCallback: DiffUtil.ItemCallback<BoardingCard>) :
        ListAdapter<BoardingCard, BoardingCardAdapter.BoardingCardViewHolder>(diffCallback) {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardingCardViewHolder {
            return BoardingCardViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.boarding_card, parent, false))
        }

        override fun onBindViewHolder(holder: BoardingCardViewHolder, position: Int) {
            holder.bindTo(getItem(position))
        }

        inner class BoardingCardViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer{
            internal fun bindTo(boardingCard: BoardingCard) {
                itemView.boardingCardTextView.text = boardingCard.description
            }
        }
    }

    class BoardingCardCallback : DiffUtil.ItemCallback<BoardingCard>() {

        override fun areItemsTheSame(oldItem: BoardingCard, newItem: BoardingCard): Boolean {
            return oldItem.description == newItem.description
        }

        override fun areContentsTheSame(oldItem: BoardingCard, newItem: BoardingCard): Boolean {
            return true
        }
    }
}
