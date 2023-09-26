package com.ozcanalasalvar.library.view.popup

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ozcanalasalvar.library.R
import com.ozcanalasalvar.library.view.datePicker.DatePicker

class DatePickerPopup(private val context: Context) : BottomSheetDialogFragment() {
    private var listener: OnDateSelectListener? = null

    /*constructor(context: Context) : super(context) {
        val picker = DatePicker(context)
        init(picker)
    }

    constructor(context: Context, picker: DatePicker) : super(context) {
        init(picker)
    }

    constructor(context: Context, theme: Int, picker: DatePicker) : super(context, theme) {
        init(picker)
    }*/

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = LayoutInflater.from(context).inflate(R.layout.picker_popup_layout,container)

        val confirm = view.findViewById<TextView>(R.id.text_confirm)
        val cancel = view.findViewById<TextView>(R.id.text_cancel)
        val container = view.findViewById<LinearLayout>(R.id.popup_container)
        confirm.setOnClickListener { view: View? ->
            dismiss()
        }
        cancel.setOnClickListener { view: View? ->
            dismiss()
        }


        container.addView(
            DatePicker(context)
        )

        setCancelable(false)
        return view
    }

    private fun init(picker: DatePicker) {
        setCancelable(false)
       // setCanceledOnTouchOutside(true)
//        confirm.setOnClickListener { view: View? ->
//            if (listener != null) {
//                val dateModel = DateModel(picker.date)
//                listener!!.onDateSelected(
//                    picker,
//                    dateModel.date,
//                    dateModel.day,
//                    dateModel.month,
//                    dateModel.year
//                )
//            }
//            dismiss()
//        }
//        addView(picker)
    }

    fun setListener(listener: OnDateSelectListener?) {
        this.listener = listener
    }

    class Builder {
        private var context: Context? = null
        private var datePicker: DatePicker? = null
        private var listener: OnDateSelectListener? = null
        fun from(context: Context?): Builder {
            this.context = context
            datePicker = DatePicker(context!!)
            return this
        }

        fun textSize(textSize: Int): Builder {
            datePicker!!.setTextSize(textSize)
            return this
        }

        fun startDate(startDate: Long): Builder {
            datePicker!!.minDate = startDate
            return this
        }

        fun endDate(endDate: Long): Builder {
            datePicker!!.maxDate = endDate
            return this
        }

        fun currentDate(currentDate: Long): Builder {
            datePicker!!.date = currentDate
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

        fun pickerMode(appearanceMode: Int): Builder {
            datePicker!!.setPickerMode(appearanceMode)
            return this
        }

        fun listener(listener: OnDateSelectListener?): Builder {
            this.listener = listener
            return this
        }

        fun build(): DatePickerPopup {
            val popup = DatePickerPopup(context!!)
            popup.setListener(listener)
            return popup
        }

        fun build(theme: Int): DatePickerPopup {
            val popup = DatePickerPopup(context!!)
            popup.setListener(listener)
            return popup
        }
    }

    interface OnDateSelectListener {
        fun onDateSelected(dp: DatePicker?, date: Long, day: Int, month: Int, year: Int)
    }
}