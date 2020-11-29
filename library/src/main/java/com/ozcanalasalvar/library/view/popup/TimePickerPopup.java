package com.ozcanalasalvar.library.view.popup;

import android.content.Context;

import androidx.annotation.NonNull;

import com.ozcanalasalvar.library.view.timePicker.TimePicker;

public class TimePickerPopup extends PickerPopup {

    private OnTimeSelectListener listener;

    public TimePickerPopup(@NonNull Context context) {
        super(context);
        TimePicker timePicker = new TimePicker(context);
        init(timePicker);
    }

    public TimePickerPopup(@NonNull Context context, @NonNull TimePicker timePicker) {
        super(context);
        init(timePicker);
    }

    public TimePickerPopup(@NonNull Context context, int theme, @NonNull TimePicker timePicker) {
        super(context, theme);
        init(timePicker);
    }

    private void init(TimePicker timePicker) {
        setCancelable(false);
        setCanceledOnTouchOutside(true);
        confirm.setOnClickListener(view -> {
            if (listener != null)
                listener.onTimeSelected(timePicker, timePicker.getHour(), timePicker.getMinute());
            dismiss();
        });
        addView(timePicker);
    }

    public void setListener(OnTimeSelectListener listener) {
        this.listener = listener;
    }


    public static final class Builder {
        private Context context;
        private TimePicker timePicker;
        private OnTimeSelectListener listener;

        public Builder() {
        }

        public Builder from(Context context) {
            this.context = context;
            this.timePicker = new TimePicker(context);
            return this;
        }

        public Builder textSize(int textSize) {
            this.timePicker.setTextSize(textSize);
            return this;
        }


        public Builder offset(int offset) {
            this.timePicker.setOffset(offset);
            return this;
        }

        public Builder setTime(int hour, int minute) {
            this.timePicker.setTime(hour, minute);
            return this;
        }

        public Builder listener(OnTimeSelectListener listener) {
            this.listener = listener;
            return this;
        }

        public TimePickerPopup build() {
            TimePickerPopup popup = new TimePickerPopup(context, timePicker);
            popup.setListener(listener);
            return popup;
        }

        public TimePickerPopup build(int theme) {
            TimePickerPopup popup = new TimePickerPopup(context, theme, timePicker);
            popup.setListener(listener);
            return popup;
        }
    }

    public interface OnTimeSelectListener {
        void onTimeSelected(TimePicker timePicker, int hour, int minute);
    }

}
