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
import com.ozcanalasalvar.datepicker.model.Time
import com.ozcanalasalvar.datepicker.view.timepicker.TimePicker

class TimePickerPopup(private val context: Context) : BottomSheetDialogFragment() {

    private var listener: TimeSelectListener? = null

    lateinit var picker: TimePicker

    private var selectedTime: Time? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        val view = LayoutInflater.from(context).inflate(R.layout.picker_popup_layout, container)

        val confirm = view.findViewById<TextView>(R.id.text_confirm)
        val cancel = view.findViewById<TextView>(R.id.text_cancel)
        val container = view.findViewById<LinearLayout>(R.id.popup_container)

        confirm.setOnClickListener { view: View? ->
            if (listener != null && selectedTime != null) {
                listener!!.onTimeSelected(
                    picker,
                    selectedTime!!.hour,
                    selectedTime!!.minute,
                    selectedTime!!.format
                )
            }
            dismiss()
        }
        cancel.setOnClickListener { view: View? ->
            dismiss()
        }


        container.removeAllViews()
        container.addView(picker)

        picker.setTimeChangeListener(object : TimePicker.TimeChangeListener {
            override fun onTimeChanged(hour: Int, minute: Int, timeFormat: String?) {
                selectedTime = Time(hour, minute, timeFormat)
            }

        })

        setCancelable(false)
        return view
    }

    fun setListener(listener: TimeSelectListener?) {
        this.listener = listener
    }

    class Builder {
        private var context: Context? = null
        private var timePicker: TimePicker? = null
        private var listener: TimeSelectListener? = null
        fun from(context: Context?): Builder {
            this.context = context
            timePicker = TimePicker(context!!)
            return this
        }

        fun textSize(textSize: Int): Builder {
            timePicker!!.setTextSize(textSize)
            return this
        }

        fun offset(offset: Int): Builder {
            timePicker!!.setOffset(offset)
            return this
        }

        fun setTime(hour: Int, minute: Int): Builder {
            timePicker!!.setTime(hour, minute)
            return this
        }

        fun darkModeEnabled(darkModeEnabled: Boolean): Builder {
            timePicker!!.setDarkModeEnabled(darkModeEnabled)
            return this
        }

        fun setTimeFormat(timeFormat: Int): Builder {
            timePicker!!.setTimeFormat(timeFormat)
            return this
        }

        fun listener(listener: TimeSelectListener?): Builder {
            this.listener = listener
            return this
        }

        fun build(): TimePickerPopup {
            val popup = TimePickerPopup(context!!)
            timePicker?.let {
                popup.picker = it
            }
            popup.setListener(listener)
            return popup
        }

        fun build(theme: Int): TimePickerPopup {
            val popup = TimePickerPopup(context!!)
            popup.setListener(listener)
            return popup
        }
    }

    interface TimeSelectListener {
        fun onTimeSelected(timePicker: TimePicker?, hour: Int, minute: Int, format: String?)
    }
}