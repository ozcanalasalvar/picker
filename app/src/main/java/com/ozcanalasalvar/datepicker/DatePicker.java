package com.ozcanalasalvar.datepicker;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatePicker extends LinearLayout {

    private Context context;
    private LinearLayout container;
    private int offset = 3;

    private DateModel minDate;
    private DateModel maxDate;
    private DateModel date;

    private WheelView dayView;
    private WheelView monthView;
    private WheelView yearView;
    private WheelView emptyView1;
    private WheelView emptyView2;

    private final static int MAX_TEXT_SIZE = 19;
    private int textSize = 19;


    public DatePicker(Context context) {
        super(context);
        init(context);
    }

    public DatePicker(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DatePicker(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public DatePicker(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        this.setOrientation(LinearLayout.HORIZONTAL);
        container = new LinearLayout(context);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        container.setLayoutParams(layoutParams);
        container.setOrientation(LinearLayout.HORIZONTAL);
        this.addView(container);
        setUpInitialDates();
        setUpInitialViews();
    }

    private void setUpInitialDates() {
        int minYear = 1970;
        int minMonth = 0;//max 11
        int maxYear = 2050;
        int maxMonth = 12;
        minDate = new DateModel(DateUtils.getTimeMiles(minYear, minMonth, 1));
        maxDate = new DateModel(DateUtils.getTimeMiles(maxYear, maxMonth, 1));
        date = new DateModel(DateUtils.getCurrentTime());
    }

    private void setUpInitialViews() {
        container.addView(createEmptyView1(context));
        container.addView(createMonthPickerView(context));
        container.addView(createDayPickerView(context));
        container.addView(createYearPickerView(context));
        container.addView(createEmptyView2(context));
        setUpCalendar();
    }

    public void setUpCalendar() {
        Log.i("Calendar", "setUp = " + date.toString());
        setUpYearView();
        setUpMonthView();
        setUpDayView();
        setupEmptyViews();
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

    private void notifyMonthView() {
        int min = (date.getYear() != minDate.getYear() && date.getMonth() != minDate.getMonth()) ? 0 : minDate.getMonth();
        int max = (date.getYear() == maxDate.getYear() && date.getMonth() >= maxDate.getMonth()) ? maxDate.getMonth() : monthView.items.size();
        monthView.setValidIndex(min, max);
        monthView.setSelection(date.getMonth());
    }

    private void notifyDayView() {
        setUpDayView();
    }

    private void setUpYearView() {
        Log.i("Calendar", "setUpYearView = " + date.toString());
        int yearCount = Math.abs(minDate.getYear() - maxDate.getYear()) + 1;
        List<String> years = new ArrayList<>();
        for (int i = 0; i < yearCount; i++) {
            years.add("" + (minDate.getYear() + i));
        }
        yearView.setValidIndex(years.indexOf("" + minDate.getYear()), years.indexOf("" + maxDate.getYear()));
        yearView.setOffset(offset);
        yearView.setTextSize(textSize);
        yearView.setAlignment(View.TEXT_ALIGNMENT_CENTER);
        yearView.setGravity(Gravity.END);
        yearView.setOffset(offset);
        yearView.setItems(years);
        yearView.setSelection(years.indexOf("" + date.getYear()));
    }

    private void setUpMonthView() {
        Log.i("Calendar", "setUpMonthView = " + date.toString());

        List<String> months = new ArrayList<>();
        months.add("January");
        months.add("February");
        months.add("March");
        months.add("April");
        months.add("May");
        months.add("June");
        months.add("July");
        months.add("August");
        months.add("September");
        months.add("October");
        months.add("November");
        months.add("December");

        monthView.setTextSize(textSize);
        monthView.setGravity(Gravity.START);
        monthView.setAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        monthView.setOffset(offset);
        monthView.setItems(months);

        int min = (date.getYear() == minDate.getYear() && date.getMonth() == minDate.getMonth()) ? minDate.getMonth() : 0;
        int max = (date.getYear() == maxDate.getYear() && date.getMonth() == maxDate.getMonth()) ? maxDate.getMonth() : monthView.items.size();
        monthView.setValidIndex(min, max);
        monthView.setSelection(date.getMonth());
    }

    private void setUpDayView() {
        Log.i("Calendar", "setUpDayView = " + date.toString());
        List<String> days = new ArrayList<>();
        int count = DateUtils.getMonthDayCount(date.getDate());
        for (int i = 0; i < count; i++) {
            days.add("" + (i + 1));
        }
        int min = 0;
        int max = 0;
        if (date.getYear() == maxDate.getYear()) {
            if (date.getMonth() == maxDate.getMonth()) {
                max = maxDate.getDay() - 1;
            } else if (date.getMonth() > maxDate.getMonth()) {
                max = -1;
            } else
                max = days.size();

            if (date.getMonth() == minDate.getMonth()) {
                min = minDate.getDay() - 1;
            } else if (date.getMonth() < minDate.getMonth()) {
                min = days.size();
            } else
                min = -1;

        } else {
            max = days.size();
            min = -1;
        }

        Log.i("date", "min= " + min);
        Log.i("date", "max= " + max);

        dayView.setValidIndex(min, max);
        dayView.setOffset(offset);
        dayView.setTextSize(textSize);
        dayView.setGravity(Gravity.END);
        dayView.setAlignment(View.TEXT_ALIGNMENT_TEXT_END);
        dayView.setOffset(offset);
        dayView.setItems(days);
        dayView.setSelection((date.getDay() - 1)); //Day start from 1
    }

    private LinearLayout createYearPickerView(Context context) {
        yearView = new WheelView(context);
        LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        yearView.setLayoutParams(lp);
        yearView.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                date.setYear(Integer.parseInt(item));
                notifyMonthView();
                notifyDayView();
                notifyDateSelect();
            }
        });
        LinearLayout ly = wheelContainerView(2.0f);
        ly.addView(yearView);
        return ly;
    }


    private LinearLayout createMonthPickerView(Context context) {
        monthView = new WheelView(context);
        LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        monthView.setLayoutParams(lp);
        monthView.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                date.setMonth(selectedIndex);
                notifyMonthView();
                notifyDayView();
                notifyDateSelect();
            }
        });
        LinearLayout ly = wheelContainerView(2.0f);
        ly.addView(monthView);
        return ly;
    }


    private LinearLayout createDayPickerView(Context context) {
        dayView = new WheelView(context);
        LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dayView.setLayoutParams(lp);
        dayView.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                date.setDay(selectedIndex + 1);
                notifyDayView();
                notifyDateSelect();
            }
        });
        LinearLayout ly = wheelContainerView(2.0f);
        ly.addView(dayView);
        return ly;
    }


    private LinearLayout createEmptyView1(Context context) {
        emptyView1 = createEmptyWheel(context);
        LinearLayout ly = wheelContainerView(1.0f);
        ly.addView(emptyView1);
        return ly;
    }

    private LinearLayout createEmptyView2(Context context) {
        emptyView2 = createEmptyWheel(context);
        LinearLayout ly = wheelContainerView(1.0f);
        ly.addView(emptyView2);
        return ly;
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

    /**
     * Implement current min date
     *
     * @param date
     */
    public void setMinDate(long date) {
        this.minDate = new DateModel(date);
        setUpCalendar();
    }

    /**
     * Implement current max date
     *
     * @param date
     */
    public void setMaxDate(long date) {
        this.maxDate = new DateModel(date);
        setUpCalendar();
    }

    /**
     * Implement current selected date
     *
     * @param date
     */
    public void setDate(long date) {
        this.date = new DateModel(date);
        setUpCalendar();
    }

    /**
     * @return minDate
     */
    public long getMinDate() {
        return minDate.getDate();
    }

    /**
     * @return maxDate
     */
    public long getMaxDate() {
        return maxDate.getDate();
    }

    /**
     * @return date
     */
    public long getDate() {
        return date.getDate();
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
        setUpCalendar();
    }

    public void setTextSize(int textSize) {
        this.textSize = Math.min(textSize, MAX_TEXT_SIZE);
        setUpCalendar();
    }

    private DataSelectListener dataSelectListener;

    public void setDataSelectListener(DataSelectListener dataSelectListener) {
        this.dataSelectListener = dataSelectListener;
    }

    public interface DataSelectListener {
        void onDateSelected(long date, int day, int month, int year);
    }

    private void notifyDateSelect() {
        dataSelectListener.onDateSelected(date.getDate(), date.getDay(), date.getMonth(), date.getYear());
    }
}
