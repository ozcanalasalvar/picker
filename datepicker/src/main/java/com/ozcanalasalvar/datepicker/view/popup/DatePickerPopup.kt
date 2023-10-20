package com.ozcanalasalvar.datepicker.view.popup

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ozcanalasalvar.library.R
import com.ozcanalasalvar.datepicker.model.Date
import com.ozcanalasalvar.datepicker.view.datepicker.DatePicker

class DatePickerPopup(private val context: Context) : BottomSheetDialogFragment() {
    private var listener: DateSelectListener? = null

    lateinit var picker: DatePicker

    private var selectedDate: Long? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        val view = LayoutInflater.from(context).inflate(R.layout.picker_popup_layout, container)

        val confirm = view.findViewById<TextView>(R.id.text_confirm)
        val cancel = view.findViewById<TextView>(R.id.text_cancel)
        val container = view.findViewById<LinearLayout>(R.id.popup_container)

        confirm.setOnClickListener { _: View? ->
            if (listener != null && selectedDate != null) {
                val date = Date(selectedDate!!)
                listener!!.onDateSelected(
                    picker, date.date, date.day, date.month, date.year
                )
            }
            dismiss()
        }
        cancel.setOnClickListener { view: View? ->
            dismiss()
        }


        container.removeAllViews()
        container.addView(picker)

        picker.setDateChangeListener(object : DatePicker.DateChangeListener {
            override fun onDateChanged(date: Long, day: Int, month: Int, year: Int) {
                selectedDate = date
            }
        })

        setCancelable(false)
        return view
    }


    fun setListener(listener: DateSelectListener?) {
        this.listener = listener
    }

    class Builder {
        private var context: Context? = null
        private var datePicker: DatePicker? = null
        private var listener: DateSelectListener? = null
        fun from(context: Context?): Builder {
            this.context = context
            datePicker = DatePicker(context!!)
            return this
        }

        fun textSize(textSize: Int): Builder {
            datePicker!!.setTextSize(textSize)
            return this
        }

        @Deprecated("Unused method")
        fun startDate(startDate: Long): Builder {
            return this
        }

        @Deprecated("Unused method")
        fun endDate(endDate: Long): Builder {
            return this
        }

        @Deprecated("")
        fun currentDate(currentDate: Long): Builder {
            datePicker!!.setDate(currentDate)
            return this
        }

        fun selectedDate(currentDate: Long): Builder {
            datePicker!!.setDate(currentDate)
            return this
        }

        fun offset(offset: Int): Builder {
            datePicker!!.setOffset(offset)
            return this
        }

        fun darkModeEnabled(darkModeEnabled: Boolean): Builder {
            datePicker!!.setDarkModeEnabled(darkModeEnabled)
            return this
        }

        @Deprecated("")
        fun pickerMode(appearanceMode: Int): Builder {
            datePicker!!.setPickerMode(appearanceMode)
            return this
        }

        fun listener(listener: DateSelectListener?): Builder {
            this.listener = listener
            return this
        }

        fun build(): DatePickerPopup {
            val popup = DatePickerPopup(context!!)
            datePicker?.let {
                popup.picker = it
            }
            popup.setListener(listener)
            return popup
        }

        fun build(theme: Int): DatePickerPopup {
            val popup = DatePickerPopup(context!!)
            popup.setListener(listener)
            return popup
        }
    }

    interface DateSelectListener {
        fun onDateSelected(dp: DatePicker?, date: Long, day: Int, month: Int, year: Int)
    }
}