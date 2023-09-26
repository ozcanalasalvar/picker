package com.ozcanalasalvar.library.view.datePicker

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.LinearLayout
import com.ozcanalasalvar.library.R
import com.ozcanalasalvar.library.factory.DateFactoryListener
import com.ozcanalasalvar.library.factory.DatePickerFactory
import com.ozcanalasalvar.library.view.WheelView

class DatePicker : LinearLayout, DateFactoryListener {
    private var context: Context? = null
    private var container: LinearLayout? = null
    private var offset = 3
    private var factory: DatePickerFactory =DatePickerFactory()
    private var dayView: WheelView? = null
    private var monthView: WheelView? = null
    private var yearView: WheelView? = null
    private var emptyView1: WheelView? = null
    private var emptyView2: WheelView? = null
    private var textSize = 19
    private var pickerMode = 0
    private var darkModeEnabled = true
    private var isNightTheme = false

    constructor(context: Context) : super(context) {
        init(context)
        this.addView(
            DatePickerView(
                context = context,
                attrs = null,
                defStyle = 0,
            )
        )
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        setAttributes(context, attrs)
        this.addView(
            DatePickerView(
                context = context,
                attrs = attrs,
                defStyle = 0,
            )
        )
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        this.addView(
            DatePickerView(
                context = context,
                attrs = attrs,
                defStyle = defStyleAttr,
            )
        )
      //  init(context)
    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        setAttributes(context, attrs)
       // init(context)

        this.addView(
            DatePickerView(
                context = context,
                attrs = attrs,
                defStyle = defStyleAttr,
            )
        )
    }

    @SuppressLint("NonConstantResourceId")
    private fun setAttributes(context: Context, attrs: AttributeSet?) {
        val a = context.obtainStyledAttributes(attrs, R.styleable.Picker)
        val N = a.indexCount
        for (i in 0 until N) {
            val attr = a.getIndex(i)
            if (attr == R.styleable.Picker_offset) {
                offset = Math.min(a.getInteger(attr, 3), MAX_OFFSET)
            } else if (attr == R.styleable.Picker_darkModeEnabled) {
                darkModeEnabled = a.getBoolean(attr, true)
            } else if (attr == R.styleable.Picker_textSize) {
                textSize = Math.min(a.getInt(attr, MAX_TEXT_SIZE), MAX_TEXT_SIZE)
            } else if (attr == R.styleable.Picker_pickerMode) {
                pickerMode = a.getInt(attr, 0)
            }
        }
        a.recycle()
    }

    private fun init(context: Context) {
        this.context = context
//        this.orientation = HORIZONTAL
//        this.addView(DatePickerView())
        /*factory = DatePickerFactory(this)
        container = LinearLayout(context)
        val layoutParams =
            LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        container!!.layoutParams = layoutParams
        container!!.orientation = HORIZONTAL
        this.addView(container)
        setUpInitialViews()*/
    }

    private fun checkDarkMode() {
        val nightModeFlags = getContext().resources.configuration.uiMode and
                Configuration.UI_MODE_NIGHT_MASK
        when (nightModeFlags) {
            Configuration.UI_MODE_NIGHT_YES -> isNightTheme = true
            Configuration.UI_MODE_NIGHT_NO, Configuration.UI_MODE_NIGHT_UNDEFINED -> isNightTheme =
                false
        }
    }

    private fun setUpInitialViews() {
        /*container!!.removeAllViews()
        container!!.addView(createEmptyView1(context))
        if (pickerMode == MONTH_ON_FIRST) {
            container!!.addView(createMonthView(context))
            container!!.addView(createDayView(context))
        } else {
            container!!.addView(createDayView(context))
            container!!.addView(createMonthView(context))
        }
        container!!.addView(createYearView(context))
        container!!.addView(createEmptyView2(context))
        setUpCalendar()*/
    }

    fun setUpCalendar() {
      /*  Log.i("Calendar", "setUp = " + factory?.selectedDate.toString())
        if (darkModeEnabled) {
            checkDarkMode()
        } else {
            isNightTheme = false
        }
        setUpYearView()
        setUpMonthView()
        setUpDayView()
        setupEmptyViews()*/
    }

    private fun setupEmptyViews() {
//        val array: MutableList<String> = ArrayList()
//        for (i in 0..29) {
//            array.add("")
//        }
//        emptyView1!!.setTextSize(textSize)
//        emptyView2!!.setTextSize(textSize)
//        emptyView1!!.setOffset(offset)
//        emptyView2!!.setOffset(offset)
//        emptyView1!!.items = array
//        emptyView2!!.items = array
    }

    private fun setUpYearView() {
//        val date = factory!!.selectedDate
//        val years = factory!!.yearList
//        yearView!!.isNightTheme = isNightTheme
//        yearView!!.setOffset(offset)
//        yearView!!.setTextSize(textSize)
//        yearView!!.setAlignment(TEXT_ALIGNMENT_CENTER)
//        yearView!!.setGravity(Gravity.CENTER)
//        yearView!!.items = years
//        yearView!!.setSelection(years.indexOf("" + date.year))
    }

    private fun setUpMonthView() {
//        val months = factory!!.monthList
//        val date = factory!!.selectedDate
//        monthView!!.isNightTheme = isNightTheme
//        monthView!!.setTextSize(textSize)
//        monthView!!.setGravity(Gravity.CENTER)
//        monthView!!.setAlignment(TEXT_ALIGNMENT_CENTER)
//        monthView!!.setOffset(offset)
//        monthView!!.items = months
//        monthView!!.setSelection(date.month - factory!!.monthMin)
    }

    private fun setUpDayView() {
//        val date = factory!!.selectedDate
//        val days = factory!!.dayList
//        dayView!!.isNightTheme = isNightTheme
//        dayView!!.setOffset(offset)
//        dayView!!.setTextSize(textSize)
//        dayView!!.setGravity(Gravity.END)
//        dayView!!.setAlignment(if (pickerMode == DAY_ON_FIRST) TEXT_ALIGNMENT_CENTER else TEXT_ALIGNMENT_TEXT_END)
//        dayView!!.setOffset(offset)
//        dayView!!.items = days
//        dayView!!.setSelection(date.day - 1) //Day start from 1
    }

    private fun createYearView(context: Context?): LinearLayout {
//        yearView = WheelView(context)
//        val lp =
//            LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
//        yearView!!.layoutParams = lp
//        yearView!!.setOnWheelViewListener { selectedIndex: Int, item: String ->
//            factory!!.setSelectedYear(
//                item.toInt()
//            )
//        }
//        val ly = wheelContainerView(3.0f)
//        ly.addView(yearView)
        return LinearLayout(context)
    }

    private fun createMonthView(context: Context?): LinearLayout {
//        monthView = WheelView(context)
//        val lp =
//            LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
//        monthView!!.layoutParams = lp
//        monthView!!.setOnWheelViewListener { selectedIndex: Int, item: String? ->
//            factory!!.setSelectedMonth(
//                factory!!.monthMin + selectedIndex
//            )
//        }
//        val ly = wheelContainerView(3.0f)
//        ly.addView(monthView)
//        return ly

        return LinearLayout(context)
    }

    private fun createDayView(context: Context?): LinearLayout {
//        dayView = WheelView(context)
//        val lp =
//            LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
//        dayView!!.layoutParams = lp
//        dayView!!.setOnWheelViewListener { selectedIndex: Int, item: String? ->
//            factory!!.setSelectedDay(
//                selectedIndex + 1
//            )
//        }
//        val ly = wheelContainerView(2.0f)
//        ly.addView(dayView)
//        return ly

        return LinearLayout(context)
    }

    private fun createEmptyView1(context: Context?): LinearLayout {
//        emptyView1 = createEmptyWheel(context)
//        val ly = wheelContainerView(1.5f)
//        ly.addView(emptyView1)
//        return ly
        return LinearLayout(context)
    }

    private fun createEmptyView2(context: Context?): LinearLayout {
//        emptyView2 = createEmptyWheel(context)
//        val ly = wheelContainerView(1.0f)
//        ly.addView(emptyView2)
//        return ly
        return LinearLayout(context)
    }

    private fun createEmptyWheel(context: Context?): WheelView {
        val view = WheelView(context)
        val lp =
            LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        view.layoutParams = lp
        return view
    }

    private fun wheelContainerView(weight: Float): LinearLayout {
        val layout = LinearLayout(context)
        val layoutParams = LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, weight)
        layout.layoutParams = layoutParams
        layout.orientation = VERTICAL
        return layout
    }
    /**
     * @return minDate
     */
    /**
     * Implement current min date
     *
     * @param date
     */
    var minDate: Long
        get() = factory!!.minDate.date
        set(date) {
            factory!!.setMinDate(date)
        }
    /**
     * @return maxDate
     */
    /**
     * Implement current max date
     *
     * @param date
     */
    var maxDate: Long
        get() = factory!!.maxDate.date
        set(date) {
            factory!!.setMaxDate(date)
        }
    /**
     * @return date
     */
    /**
     * Implement current selected date
     *
     * @param date
     */
    var date: Long
        get() = factory!!.selectedDate.date
        set(date) {
            factory!!.setSelectedDate(date)
        }

    fun getOffset(): Int {
        return offset
    }

    fun setOffset(offset: Int) {
        this.offset = offset
        setUpCalendar()
    }

    fun setTextSize(textSize: Int) {
        this.textSize = Math.min(textSize, MAX_TEXT_SIZE)
        setUpCalendar()
    }

    fun setPickerMode(pickerMode: Int) {
        this.pickerMode = pickerMode
        setUpInitialViews()
    }

    override fun onYearChanged() {
        setUpMonthView()
        setUpDayView()
        notifyDateSelect()
    }

    override fun onMonthChanged() {
        setUpDayView()
        notifyDateSelect()
    }

    override fun onDayChanged() {
        notifyDateSelect()
    }

    override fun onConfigsChanged() {
        setUpCalendar()
    }

    /**
     * @return
     */
    fun isDarkModeEnabled(): Boolean {
        return darkModeEnabled
    }

    /**
     * @param darkModeEnabled
     */
    fun setDarkModeEnabled(darkModeEnabled: Boolean) {
        this.darkModeEnabled = darkModeEnabled
        setUpCalendar()
    }

    interface DataSelectListener {
        fun onDateSelected(date: Long, day: Int, month: Int, year: Int)
    }

    private var dataSelectListener: DataSelectListener? = null
    fun setDataSelectListener(dataSelectListener: DataSelectListener) {
        this.dataSelectListener = dataSelectListener
    }

    private fun notifyDateSelect() {
        val date = factory!!.selectedDate
        if (dataSelectListener != null) dataSelectListener!!.onDateSelected(
            date.date,
            date.day,
            date.month,
            date.year
        )
    }

    companion object {
        const val MONTH_ON_FIRST = 0
        const val DAY_ON_FIRST = 1
        private const val MAX_TEXT_SIZE = 20
        private const val MAX_OFFSET = 3
    }
}