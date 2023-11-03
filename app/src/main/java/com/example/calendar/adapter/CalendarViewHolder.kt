package com.example.calendar.adapter

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.calendar.R

class CalendarViewHolder(itemView: View, private val onItemListener: CalendarAdapter.OnItemListener) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val dayOfMonth: TextView = itemView.findViewById(R.id.cellDayText)
        val mainLayout: ConstraintLayout = itemView.findViewById(R.id.mainLayout)

        init {
        itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
        onItemListener.onItemClick(layoutPosition, dayOfMonth.text.toString())
        }
        }
