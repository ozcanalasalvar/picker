package com.ozcanalasalvar.library.view.datepicker

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import android.util.AttributeSet
import android.widget.LinearLayout
import com.ozcanalasalvar.library.R
import com.ozcanalasalvar.library.compose.datepicker.DatePickerComposeView
import com.ozcanalasalvar.library.model.DateModel
import com.ozcanalasalvar.library.utils.DateUtils

class DatePicker : LinearLayout {
    private var context: Context? = null
    private var pickerView: DatePickerComposeView? = null

    private var date: DateModel = DateModel(DateUtils.getCurrentTime())
    private var offset = 3
    private var textSize = 16
    private var pickerMode = 0
    private var darkModeEnabled = true
    private var isNightTheme = false

    constructor(context: Context) : super(context) {
        init(context, null, 0)
        this.addView(
            DatePickerComposeView(
                context = context,
                attrs = null,
                defStyle = 0,
            )
        )
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
                pickerMode = a.getInt(attr, 0)
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
        pickerView?.setDataSelectListener(dataSelectListener)
    }

    private fun checkDarkMode() {
        val nightModeFlags =
            getContext().resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        when (nightModeFlags) {
            Configuration.UI_MODE_NIGHT_YES -> isNightTheme = true
            Configuration.UI_MODE_NIGHT_NO, Configuration.UI_MODE_NIGHT_UNDEFINED -> isNightTheme =
                false
        }
    }


    @Deprecated("")
    var minDate: Long = 0


    @Deprecated("")
    var maxDate: Long = 0


    fun setDate(_date: Long) {
        date = DateModel(_date)
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

    @Deprecated("")
    fun setPickerMode(pickerMode: Int) {
        this.pickerMode = pickerMode
    }


//    override fun onConfigsChanged() {
//       setAttributes()
//    }

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

    interface DataSelectListener {
        fun onDateSelected(date: Long, day: Int, month: Int, year: Int)
    }

    private var dataSelectListener: DataSelectListener? = null
    fun setDataSelectListener(dataSelectListener: DataSelectListener) {
        this.dataSelectListener = dataSelectListener
        setAttributes()
    }

    companion object {
        const val MONTH_ON_FIRST = 0
        const val DAY_ON_FIRST = 1
        private const val MAX_TEXT_SIZE = 20
        private const val MAX_OFFSET = 3
    }
}