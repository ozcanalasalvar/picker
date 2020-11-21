package com.ozcanalasalvar.datepicker.view.timePicker;

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

import com.ozcanalasalvar.datepicker.R;
import com.ozcanalasalvar.datepicker.factory.TimeFactory;
import com.ozcanalasalvar.datepicker.factory.TimeFactoryListener;
import com.ozcanalasalvar.datepicker.model.DateModel;
import com.ozcanalasalvar.datepicker.view.WheelView;

import java.util.ArrayList;
import java.util.List;

public class TimePicker extends LinearLayout implements TimeFactoryListener {
    private WheelView emptyView1;
    private WheelView emptyView2;
    private Context context;
    private LinearLayout container;
    private int offset = 3;
    private int textSize = 19;

    private final static int MAX_TEXT_SIZE = 19;
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
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DatePicker);
        final int N = a.getIndexCount();
        for (int i = 0; i < N; ++i) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.DatePicker_offset:
                    this.offset = Math.min(a.getInteger(attr, 3), MAX_OFFSET);
                    break;
                case R.styleable.DatePicker_darkModeEnabled:
                    this.darkModeEnabled = a.getBoolean(attr, true);
                    break;
                case R.styleable.DatePicker_textSize:
                    this.textSize = Math.min(a.getInt(attr, MAX_TEXT_SIZE), MAX_TEXT_SIZE);
                    break;
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
        minuteView.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                factory.setMinute(selectedIndex);
            }
        });
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
        minuteView.setSelection(factory.getMinute() - 1);
        LinearLayout ly = wheelContainerView(1.0f);
        ly.addView(minuteView);
        return ly;
    }

    private View createHourView(Context context) {
        hourView = new WheelView(context);
        LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        hourView.setLayoutParams(lp);
        hourView.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                factory.setHour(selectedIndex + 1);
            }
        });
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
        hourView.setSelection(factory.getHour() - 1);
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
    public void onHourChanged() {
        notifyTimeSelect();
    }

    @Override
    public void onMinuteChanged() {
        notifyTimeSelect();
    }

    public interface TimeSelectListener {
        void onTimeSelected(int hour, int minute);
    }

    private TimeSelectListener timeSelectListener;

    public void setDataSelectListener(TimeSelectListener dataSelectListener) {
        this.timeSelectListener = dataSelectListener;
    }

    private void notifyTimeSelect() {
        if (timeSelectListener != null)
            timeSelectListener.onTimeSelected(factory.getHour(), factory.getMinute());
    }
}