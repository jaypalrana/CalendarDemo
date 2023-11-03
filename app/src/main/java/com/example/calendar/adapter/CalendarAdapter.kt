package com.example.calendar.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.calendar.R
import com.example.calendar.activity.CalenderActivity
import com.example.calendar.model.HolidayList
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class CalendarAdapter(
    private val daysOfMonth: ArrayList<Pair<String, Int>>,
    private val onItemListener: CalenderActivity,
    private val context: Context,
    private val monthYearFromDate:String,
    private val selectedDayList:ArrayList<HolidayList>
) :
    RecyclerView.Adapter<CalendarViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.calendar_cell, parent, false)
        return CalendarViewHolder(view, onItemListener)
    }

    @SuppressLint("UseCompatLoadingForDrawables", "ResourceAsColor")
    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        val dayPair = daysOfMonth[position]
        holder.dayOfMonth.text = dayPair.first
        holder.dayOfMonth.setTextColor(dayPair.second)
     //
        var dateOfCell = "";
        if(dayPair.first.length == 1){
            dateOfCell =  "0"+dayPair.first
        }else{
            dateOfCell = dayPair.first
        }

        try{

            if((SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date()).toString()).equals(convertDateStringToMonthYearFormat(monthYearFromDate,SimpleDateFormat("MMMM yyyy", Locale.US),SimpleDateFormat("yyyy-MM", Locale.US))+"-"+dateOfCell)){
                holder.dayOfMonth.setTextColor(R.color.purple)
            }
        }catch (e:Exception){

        }

        // Check Saturday and Sunday Cell Background Color
        if (position % 7 == 0 || position % 7 == 6) {
            holder.itemView.background = context.getDrawable(R.drawable.cell_light_gray_back)
        } else {
            holder.itemView.background = context.getDrawable(R.drawable.cell_back)
        }

        for(i in 0 until selectedDayList.size){
            if(convertDateStringToMonthYearFormat(selectedDayList.get(i).date,SimpleDateFormat("dd-MM-yyyy", Locale.US),SimpleDateFormat("MMMM yyyy", Locale.US)).equals(convertDateStringToMonthYearFormat(monthYearFromDate,SimpleDateFormat("MMMM yyyy", Locale.US),SimpleDateFormat("MMMM yyyy", Locale.US))) && dateOfCell.equals(
                    extractDateComponents(selectedDayList.get(i).date)
                )){
                holder.itemView.background = context.getDrawable(R.drawable.holidays_cell)
            }
        }
        if (daysOfMonth.size == 35) {
            if (position == 28) {
                holder.mainLayout.background =
                    context.getDrawable(R.drawable.bottom_left_round)

            }
            if (position == 34) {
                holder.mainLayout.background =
                    context.getDrawable(R.drawable.bottom_right_round)

            }
        } else if (daysOfMonth.size == 42) {
            if (position == 35) {
                holder.mainLayout.background =
                    context.getDrawable(R.drawable.bottom_left_round)

            }
            if (position == 41) {
                holder.mainLayout.background = context.getDrawable(R.drawable.bottom_right_round)
            }
        }
    }

    private fun convertDateStringToMonthYearFormat(inputDate: String?, dateFormat:SimpleDateFormat, outputDateFormat: SimpleDateFormat): String? {


        return try {
            var inputFormat  = dateFormat
            val outputFormat =outputDateFormat
            val date: Date = inputFormat.parse(inputDate)
            outputFormat.format(date)

        } catch (e: ParseException) {
            e.printStackTrace()
            "" // Handle the error as needed
        }
    }

    fun extractDateComponents(inputDate: String?) : String{
        try {
            val inputFormat = SimpleDateFormat("dd-MM-yyyy", Locale.US)
            val date = inputFormat.parse(inputDate)

            val dayFormat = SimpleDateFormat("dd", Locale.US)
            val day = dayFormat.format(date)


            println("Day: $day")
            return day;
        } catch (e: ParseException) {
            e.printStackTrace()
            // Handle the error as needed
        }
        return "";
    }
    override fun getItemCount(): Int {
        return daysOfMonth.size
    }

    interface OnItemListener {
        fun onItemClick(position: Int, dayText: String)
    }
}
