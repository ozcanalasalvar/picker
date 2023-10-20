package com.ozcanalasalvar.datepicker.view.timepicker

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.google.android.material.timepicker.TimeFormat
import com.ozcanalasalvar.library.R
import com.ozcanalasalvar.datepicker.compose.timepicker.TimePickerComposeView
import com.ozcanalasalvar.datepicker.model.Time
import com.ozcanalasalvar.datepicker.utils.DateUtils

class TimePicker : LinearLayout {

    private var context: Context? = null
    private var pickerView: TimePickerComposeView? = null
    private var offset = 3
    private var textSize = 19
    private var darkModeEnabled = true
    private var timeFormat = TimeFormat.CLOCK_24H
    private var startTime = Time(DateUtils.getCurrentHour(), DateUtils.getCurrentMinute())

    constructor(context: Context) : super(context) {
        init(context, null, 0)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        setAttributes(context, attrs)
        init(context, attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        setAttributes(context, attrs)
        init(context, attrs, defStyleAttr)
    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        setAttributes(context, attrs)
        init(context, attrs, defStyleAttr)
    }

    @SuppressLint("NonConstantResourceId")
    private fun setAttributes(context: Context, attrs: AttributeSet?) {
        val a = context.obtainStyledAttributes(attrs, R.styleable.Picker)
        val N = a.indexCount
        for (i in 0 until N) {
            val attr = a.getIndex(i)
            if (attr == R.styleable.Picker_offset) {
                offset = a.getInteger(attr, 3)
            } else if (attr == R.styleable.Picker_darkModeEnabled) {
                darkModeEnabled = a.getBoolean(attr, true)
            } else if (attr == R.styleable.Picker_textSize) {
                textSize = a.getInt(attr, 20)
            } else if (attr == R.styleable.Picker_is24HourViewEnabled) {
                timeFormat =
                    if (a.getBoolean(attr, true)) TimeFormat.CLOCK_24H else TimeFormat.CLOCK_12H
            }
        }
        a.recycle()
    }

    private fun init(context: Context, attrs: AttributeSet?, defStyleAttr: Int) {
        this.context = context

        pickerView = TimePickerComposeView(
            context = context,
            attrs = attrs,
            defStyle = defStyleAttr,
        )

        setAttributes()
        this.addView(pickerView)
    }


    private fun setAttributes() {
        pickerView?.offset = offset
        pickerView?.selectorEffectEnabled = true
        pickerView?.textSize = textSize
        pickerView?.darkModeEnabled = darkModeEnabled
        pickerView?.timeFormat = timeFormat
        pickerView?.startTime = startTime

        pickerView?.setTimeChangeListener(timeChangeListener)
    }

    interface TimeChangeListener {
        fun onTimeChanged(hour: Int, minute: Int, timeFormat: String?)
    }

    private var timeChangeListener: TimeChangeListener? = null
    fun setTimeChangeListener(dataSelectListener: TimeChangeListener) {
        timeChangeListener = dataSelectListener
        setAttributes()
    }


    fun setOffset(offset: Int) {
        this.offset = offset
        setAttributes()
    }

    fun setTextSize(textSize: Int) {
        this.textSize = textSize
        setAttributes()
    }

    /**
     * @param darkModeEnabled
     */
    fun setDarkModeEnabled(darkModeEnabled: Boolean) {
        this.darkModeEnabled = darkModeEnabled
        setAttributes()
    }


    /**
     * @param timeFormat
     */
    fun setTimeFormat(timeFormat: Int) {
        this.timeFormat = timeFormat
        setAttributes()
    }

    @Deprecated("Unused method")
    fun setHour(hour: Int) {
    }

    @Deprecated("Unused method")
    fun setMinute(minute: Int) {
    }

    fun setTime(hour: Int, minute: Int) {
        startTime = Time(hour, minute)
        setAttributes()
    }
}