package com.ozcanalasalvar.library.view.timePicker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.ozcanalasalvar.library.R;
import com.ozcanalasalvar.library.factory.TimeFactory;
import com.ozcanalasalvar.library.factory.TimeFactoryListener;
import com.ozcanalasalvar.library.view.WheelView;

import java.util.ArrayList;
import java.util.List;

public class TimePicker extends LinearLayout implements TimeFactoryListener {
    private WheelView emptyView1;
    private WheelView emptyView2;
    private Context context;
    private LinearLayout container;
    private int offset = 3;
    private int textSize = 19;

    private final static int MAX_TEXT_SIZE = 20;
    private final static int MAX_OFFSET = 3;
    private boolean darkModeEnabled = true;

    private boolean isNightTheme = false;
    private WheelView hourView;
    private WheelView minuteView;
    private WheelView formatView;
    private TimeFactory factory;

    public TimePicker(Context context) {
        super(context);
        init(context);
    }

    public TimePicker(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setAttributes(context, attrs);
        init(context);
    }

    public TimePicker(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setAttributes(context, attrs);
        init(context);
    }

    public TimePicker(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setAttributes(context, attrs);
        init(context);
    }

    @SuppressLint("NonConstantResourceId")
    private void setAttributes(Context context, @Nullable AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.Picker);
        final int N = a.getIndexCount();
        for (int i = 0; i < N; ++i) {
            int attr = a.getIndex(i);
            if (attr == R.styleable.Picker_offset) {
                this.offset = Math.min(a.getInteger(attr, 3), MAX_OFFSET);
            } else if (attr == R.styleable.Picker_darkModeEnabled) {
                this.darkModeEnabled = a.getBoolean(attr, true);
            } else if (attr == R.styleable.Picker_textSize) {
                this.textSize = Math.min(a.getInt(attr, MAX_TEXT_SIZE), MAX_TEXT_SIZE);
            }
        }
        a.recycle();
    }

    private void init(Context context) {
        this.context = context;
        this.setOrientation(LinearLayout.HORIZONTAL);
        factory = new TimeFactory(this);
        container = new LinearLayout(context);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        container.setLayoutParams(layoutParams);
        container.setOrientation(LinearLayout.HORIZONTAL);
        this.addView(container);
        setUpInitialViews();
    }


    private void setUpInitialViews() {
        if (darkModeEnabled) {
            checkDarkMode();
        } else {
            isNightTheme = false;
        }
        container.removeAllViews();
        container.addView(createEmptyView1(context));
        container.addView(createHourView(context));
        container.addView(createMinuteView(context));
        container.addView(createEmptyView2(context));
        setupEmptyViews();
    }

    private void checkDarkMode() {
        int nightModeFlags =
                getContext().getResources().getConfiguration().uiMode &
                        Configuration.UI_MODE_NIGHT_MASK;
        switch (nightModeFlags) {
            case Configuration.UI_MODE_NIGHT_YES:
                isNightTheme = true;
                break;
            case Configuration.UI_MODE_NIGHT_NO:
            case Configuration.UI_MODE_NIGHT_UNDEFINED:
                isNightTheme = false;
                break;
        }
    }


    private View createMinuteView(Context context) {
        minuteView = new WheelView(context);
        LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        minuteView.setLayoutParams(lp);
        minuteView.setOnWheelViewListener((selectedIndex, item) -> factory.setMinute(selectedIndex));
        List<String> minutes = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            String str = "";
            if (i < 10)
                str += "0";
            str += "" + (i);
            minutes.add(str);
        }
        minuteView.isNightTheme = isNightTheme;
        minuteView.setOffset(offset);
        minuteView.setTextSize(textSize);
        minuteView.setAlignment(View.TEXT_ALIGNMENT_CENTER);
        minuteView.setGravity(Gravity.CENTER);
        minuteView.setItems(minutes);
        minuteView.setSelection(factory.getMinute());
        LinearLayout ly = wheelContainerView(1.0f);
        ly.addView(minuteView);
        return ly;
    }

    private View createHourView(Context context) {
        hourView = new WheelView(context);
        LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        hourView.setLayoutParams(lp);
        hourView.setOnWheelViewListener((selectedIndex, item) -> factory.setHour(selectedIndex));
        List<String> hours = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            String str = "";
            if (i < 10)
                str += "0";
            str += "" + (i);
            hours.add(str);
        }
        hourView.isNightTheme = isNightTheme;
        hourView.setOffset(offset);
        hourView.setTextSize(textSize);
        hourView.setAlignment(View.TEXT_ALIGNMENT_CENTER);
        hourView.setGravity(Gravity.CENTER);
        hourView.setItems(hours);
        hourView.setSelection(factory.getHour());
        LinearLayout ly = wheelContainerView(1.0f);
        ly.addView(hourView);
        return ly;
    }


    private LinearLayout createEmptyView1(Context context) {
        emptyView1 = createEmptyWheel(context);
        LinearLayout ly = wheelContainerView(2.0f);
        ly.addView(emptyView1);
        return ly;
    }

    private LinearLayout createEmptyView2(Context context) {
        emptyView2 = createEmptyWheel(context);
        LinearLayout ly = wheelContainerView(2.0f);
        ly.addView(emptyView2);
        return ly;
    }

    private void setupEmptyViews() {
        List<String> array = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            array.add("");
        }
        emptyView1.setTextSize(textSize);
        emptyView2.setTextSize(textSize);
        emptyView1.setOffset(offset);
        emptyView2.setOffset(offset);
        emptyView1.setItems(array);
        emptyView2.setItems(array);
    }

    private WheelView createEmptyWheel(Context context) {
        WheelView view = new WheelView(context);
        LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return view;
    }

    private LinearLayout wheelContainerView(float weight) {
        LinearLayout layout = new LinearLayout(context);
        LayoutParams layoutParams = new LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, weight);
        layout.setLayoutParams(layoutParams);
        layout.setOrientation(LinearLayout.VERTICAL);
        return layout;
    }

    @Override
    public void onHourChanged(int hour) {
        if (hourView.getSelectedIndex() != hour)
            hourView.setSelection(hour);
        notifyTimeSelect();
    }

    @Override
    public void onMinuteChanged(int minute) {
        if (minuteView.getSelectedIndex() != minute)
            minuteView.setSelection(minute);
        notifyTimeSelect();
    }

    public interface TimeSelectListener {
        void onTimeSelected(int hour, int minute);
    }

    private TimeSelectListener timeSelectListener;

    public void setTimeSelectListener(TimeSelectListener dataSelectListener) {
        this.timeSelectListener = dataSelectListener;
    }

    private void notifyTimeSelect() {
        if (timeSelectListener != null)
            timeSelectListener.onTimeSelected(factory.getHour(), factory.getMinute());
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
        setUpInitialViews();
    }

    public void setTextSize(int textSize) {
        this.textSize = Math.min(textSize, MAX_TEXT_SIZE);
        setUpInitialViews();
    }

    public void setHour(int hour) {
        factory.setHour(hour);
    }

    public int getHour() {
        return factory.getHour();
    }

    public void setMinute(int minute) {
        factory.setMinute(minute);
    }

    public int getMinute() {
        return factory.getMinute();
    }

    public void setTime(int hour, int minute) {
        factory.setHour(hour);
        factory.setMinute(minute);
    }

}
