package com.example.calendar.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.calendar.R
import com.example.calendar.databinding.ItemCalendarHolidayListBinding
import com.example.calendar.model.HolidayList
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CalenderHolidayListAdapter(var context: Context, var holidayList: ArrayList<HolidayList>):
    RecyclerView.Adapter<CalenderHolidayListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CalenderHolidayListAdapter.ViewHolder {
        val binding: ItemCalendarHolidayListBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_calendar_holiday_list, parent, false
        )
        return ViewHolder(binding)
    }



    override fun onBindViewHolder(holder: CalenderHolidayListAdapter.ViewHolder, position: Int) {
        holder.bind(holidayList[position])
    }

    override fun getItemCount(): Int {
        return  holidayList.size;
    }
    inner class ViewHolder(private val binding: ItemCalendarHolidayListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: HolidayList) {
            binding.date.setText(convertDateStringToMonthYearFormat(item.date))
            binding.festivalName.setText(item.festivalName)
            binding.executePendingBindings()
        }
    }

    fun convertDateStringToMonthYearFormat(inputDate: String?): String? {


        return try {
            var inputFormat = SimpleDateFormat("dd-MM-yyyy", Locale.US)


            val outputFormat = SimpleDateFormat("dd MMM", Locale.US)
            val date: Date = inputFormat.parse(inputDate)
            outputFormat.format(date)

        } catch (e: ParseException) {
            e.printStackTrace()
            "" // Handle the error as needed
        }
    }
}