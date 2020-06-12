package com.utek.android.utekapp.screen.account

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.utek.android.utekapp.R
import kotlinx.android.synthetic.main.row.view.*
import java.lang.IllegalArgumentException

class RecyclerViewAdapter(var list: ArrayList<Data>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        private const val TYPE_DATA = 0
        private const val TYPE_PROGRESS = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_DATA -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false)
                DataViewHolder(view)
            }

            TYPE_PROGRESS -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.progressbar, parent, false)
                ProgressHolder(view)
            }

            else -> throw IllegalArgumentException("Different View Type")
        }
    }


    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is DataViewHolder) {
            holder.textViewTitle.text = list[position].title
            holder.textViewSubtitle.text = list[position].subtitle
        }
    }

    override fun getItemViewType(position: Int): Int {

        return when (list[position].category) {
            "data" -> TYPE_DATA
            else -> TYPE_PROGRESS
        }
    }

    inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewTitle: TextView = itemView.title
        var textViewSubtitle: TextView = itemView.subtitle

        init {
            itemView.setOnClickListener {
                Toast.makeText(itemView.context, "Clicked Item : ${list[adapterPosition].subtitle}", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    class ProgressHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}