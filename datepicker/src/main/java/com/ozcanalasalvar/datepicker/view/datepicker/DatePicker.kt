package com.ozcanalasalvar.datepicker.view.datepicker

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.ozcanalasalvar.library.R
import com.ozcanalasalvar.datepicker.compose.datepicker.DatePickerComposeView
import com.ozcanalasalvar.datepicker.model.Date
import com.ozcanalasalvar.datepicker.utils.DateUtils

class DatePicker : LinearLayout {
    private var context: Context? = null
    private var pickerView: DatePickerComposeView? = null

    private var date: Date = Date(DateUtils.getCurrentTime())
    private var offset = 3
    private var textSize = 16
    private var darkModeEnabled = true

    constructor(context: Context) : super(context) {
        init(context, null, 0)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        setAttributes(context, attrs)
        init(context, attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    ) {
        setAttributes(context, attrs)
        init(context, attrs, 0)
    }

    constructor(
        context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        setAttributes(context, attrs)
        init(context, attrs, 0)
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

            }
        }
        a.recycle()
    }

    private fun init(context: Context, attrs: AttributeSet?, defStyleAttr: Int) {
        this.context = context

        pickerView = DatePickerComposeView(
            context = context,
            attrs = attrs,
            defStyle = defStyleAttr,
        )

        setAttributes()
        this.addView(pickerView)

    }


    private fun setAttributes() {
        pickerView?.offset = offset
        pickerView?.yearsRange = IntRange(1922, 2128)
        pickerView?.startDate = date
        pickerView?.selectorEffectEnabled = true
        pickerView?.textSize = textSize
        pickerView?.darkModeEnabled= darkModeEnabled
        pickerView?.setDataChangeListener(dateChangeListener)
    }


    @Deprecated("Unused parameter")
    var minDate: Long = 0


    @Deprecated("Unused parameter")
    var maxDate: Long = 0


    fun setDate(_date: Long) {
        date = Date(_date)
        setAttributes()
    }


    fun setOffset(offset: Int) {
        this.offset = offset
        setAttributes()
    }

    fun setTextSize(textSize: Int) {
        this.textSize = Math.min(textSize, MAX_TEXT_SIZE)
        setAttributes()
    }

    @Deprecated("Unused method")
    fun setPickerMode(pickerMode: Int) {}

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
        setAttributes()
    }

    interface DateChangeListener {
        fun onDateChanged(date: Long, day: Int, month: Int, year: Int)
    }

    private var dateChangeListener: DateChangeListener? = null
    fun setDateChangeListener(dateChangeListener: DateChangeListener) {
        this.dateChangeListener = dateChangeListener
        setAttributes()
    }

    companion object {
        const val MONTH_ON_FIRST = 0
        const val DAY_ON_FIRST = 1
        private const val MAX_TEXT_SIZE = 20
        private const val MAX_OFFSET = 3
    }
}