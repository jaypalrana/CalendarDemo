package com.example.calendar.activity

import android.graphics.Color
import android.os.Build
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.calendar.R
import com.example.calendar.adapter.CalendarAdapter
import com.example.calendar.adapter.CalenderHolidayListAdapter
import com.example.calendar.base.BaseActivity
import com.example.calendar.databinding.ActivityCalendarBinding
import com.example.calendar.model.HolidayList
import com.example.calendar.viewmodel.CalendarActivityViewModel
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

class CalenderActivity : BaseActivity<ActivityCalendarBinding, CalendarActivityViewModel>(),
    CalendarAdapter.OnItemListener {

    override fun getLayoutId(): Int = R.layout.activity_calendar
    lateinit var  calendarHolidayAdapter : CalenderHolidayListAdapter
    private lateinit var selectedDate: LocalDate
    var holidayList = ArrayList<HolidayList>()

    override fun getViewModel(): CalendarActivityViewModel {
        return ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory(application)
        )[CalendarActivityViewModel::class.java]
    }

    @RequiresApi(Build.VERSION_CODES.S)
    override fun init() {
      setDataToAdapter()
        selectedDate = LocalDate.now()
        setMonthView()


    }

     private fun setDataToAdapter(){

         holidayList.add(HolidayList("12-11-2023","Diwali"))
         holidayList.add(HolidayList("13-11-2023","New year"))
         calendarHolidayAdapter = CalenderHolidayListAdapter(applicationContext, holidayList)
         getViewBinding().holidaysRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
         getViewBinding().holidaysRecyclerView.adapter = calendarHolidayAdapter
     }

    override fun setOnClick() {
        getViewBinding().showOrHideMyTimeOffBalance.setOnClickListener {
            if(getViewBinding().myTimeOfBalanceBottomView.visibility == View.VISIBLE){
                getViewBinding().showOrHideMyTimeOffBalance.setImageDrawable(ContextCompat.getDrawable(applicationContext,
                    R.drawable.chevron_up
                ))
                getViewBinding().myTimeOfBalanceBottomView.visibility = View.GONE
            }else{
                getViewBinding().showOrHideMyTimeOffBalance.setImageDrawable(ContextCompat.getDrawable(applicationContext,
                    R.drawable.chevron_down
                ))
                getViewBinding().myTimeOfBalanceBottomView.visibility = View.VISIBLE
                getViewBinding().nestedScrollView.post(Runnable {getViewBinding().nestedScrollView.fullScroll(View.FOCUS_DOWN) })

            }
        }


        getViewBinding().showOrHideHolidayList.setOnClickListener {
            if(getViewBinding().holidaysRecyclerView.visibility == View.VISIBLE){
                getViewBinding().showOrHideHolidayList.setImageDrawable(ContextCompat.getDrawable(applicationContext,
                    R.drawable.chevron_up
                ))
                getViewBinding().holidaysRecyclerView.visibility = View.GONE
            }else{
                getViewBinding().showOrHideHolidayList.setImageDrawable(ContextCompat.getDrawable(applicationContext,
                    R.drawable.chevron_down
                ))
                getViewBinding().holidaysRecyclerView.visibility = View.VISIBLE
                getViewBinding().nestedScrollView.post(Runnable {getViewBinding().nestedScrollView.fullScroll(View.FOCUS_DOWN) })
            }
        }

    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun setMonthView() {
        getViewBinding().monthYearTV.text = monthYearFromDate(selectedDate)
        val daysInMonth: ArrayList<Pair<String, Int>> = daysInMonthArray(selectedDate)
        val calendarAdapter = CalendarAdapter(daysInMonth, this, this,monthYearFromDate(selectedDate).toString(),holidayList)
        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(applicationContext, 7)
        getViewBinding().calendarRecyclerView.layoutManager = layoutManager
        getViewBinding().calendarRecyclerView.adapter = calendarAdapter
    }

    override fun apiObserve() {
    }


    override fun onResume() {
        super.onResume()

    }


    override fun onDestroy() {
        super.onDestroy()

    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun daysInMonthArray(date: LocalDate): ArrayList<Pair<String, Int>> {
        val daysInMonthArray = ArrayList<Pair<String, Int>>()
        val yearMonth = YearMonth.from(date)
        val daysInMonth = yearMonth.lengthOfMonth()
        //val firstOfMonth = date.withDayOfMonth(1)
        val dayOfWeek = date.dayOfWeek.value-1

        // Calculate the number of days to show from the previous month
        val daysBefore = dayOfWeek - 1

        // Calculate the number of days to show from the next month
        val daysAfter = 42 - (daysBefore + daysInMonth)

        // Add the days from the previous month
        val previousMonth = date.minusMonths(1)
        val previousMonthDays = YearMonth.from(previousMonth).lengthOfMonth()
        for (i in 1..daysBefore) {
            daysInMonthArray.add(Pair((previousMonthDays - daysBefore + i).toString(), Color.GRAY))
        }

        // Add the days from the current month
        for (i in 1..daysInMonth) {
            // Pair the day with the color (e.g., Default color for days from the current month)
            daysInMonthArray.add(
                Pair(
                    i.toString(),
                    Color.BLACK
                )
            ) // You can use the appropriate color here
        }

        // Add the days from the next month only if there are exactly 36 days in total
        for (i in 1..daysAfter) {
            if (daysInMonthArray.size != 35) {
                daysInMonthArray.add(Pair(i.toString(), Color.GRAY))
            }
        }

        return daysInMonthArray
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun monthYearFromDate(date: LocalDate): String? {
        val formatter = DateTimeFormatter.ofPattern("MMMM yyyy")
        return date.format(formatter)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun previousMonthAction(view: View?) {
        selectedDate = selectedDate.minusMonths(1)
        setMonthView()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun nextMonthAction(view: View?) {
        selectedDate = selectedDate.plusMonths(1)
        setMonthView()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun previousYearAction(view: View?) {
        selectedDate = selectedDate.minusYears(1)
        setMonthView()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun nextYearAction(view: View?) {
        selectedDate = selectedDate.plusYears(1)
        setMonthView()
    }

    @RequiresApi(Build.VERSION_CODES.O)
   override fun onItemClick(position: Int, dayText: String) {
        if (dayText != "") {
            val message = "Selected Date " + dayText + " " + monthYearFromDate(selectedDate)
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        }
    }


}