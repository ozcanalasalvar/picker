package com.ozcanalasalvar.datepicker;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class WheelView extends ScrollView {
    public String TAG = WheelView.class.getSimpleName();
//    public static final String DAY = "Day";
//    public static final String MONTH = "month";
//    public static final String YEAR = "Year";
//
//    public void setTag(String tag) {
//        this.TAG = tag;
//    }

    public interface OnWheelViewListener {
        void onSelected(int selectedIndex, String item);
    }


    private Context context;
    private LinearLayout views;

    public WheelView(Context context) {
        super(context);
        init(context);
    }

    public WheelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public WheelView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    List<String> items;
    private int textSize = 19;
    private int ALIGNMENT = View.TEXT_ALIGNMENT_CENTER;
    private int GRAVITY = Gravity.CENTER;
    private int minValidIndex = 0;
    private int maxValidIndex = Integer.MAX_VALUE;

    private List<String> getItems() {
        return items;
    }

    public void setItems(List<String> list) {
        if (null == items) {
            items = new ArrayList<>();
        }
        items.clear();
        items.addAll(list);

        for (int i = 0; i < offset; i++) {
            items.add(0, "");
            items.add("");
        }
        initData();

    }


    public static final int OFF_SET_DEFAULT = 1;
    int offset = OFF_SET_DEFAULT;
    private boolean configChanged = false;

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        if (this.offset != offset) {
            configChanged = true;
        }
        this.offset = offset;
    }

    int displayItemCount;

    int selectedIndex = 1;


    private void init(Context context) {
        this.context = context;

        Log.d(TAG, "parent: " + this.getParent());
        this.setVerticalScrollBarEnabled(false);
        this.setOverScrollMode(View.OVER_SCROLL_NEVER);

        views = new LinearLayout(context);
        views.setOrientation(LinearLayout.VERTICAL);
        this.addView(views);


        scrollerTask = new Runnable() {

            public void run() {

                int newY = getScrollY();
                if (initialY - newY == 0) { // stopped
                    final int remainder = initialY % itemHeight;
                    final int divided = initialY / itemHeight;
                    if (remainder == 0) {
                        selectedIndex = divided + offset;

                        onSelectedCallBack();
                    } else {
                        if (remainder > itemHeight / 2) {
                            WheelView.this.post(new Runnable() {
                                @Override
                                public void run() {
                                    WheelView.this.smoothScrollTo(0, initialY - remainder + itemHeight);
                                    selectedIndex = divided + offset + 1;
                                    onSelectedCallBack();
                                }
                            });
                        } else {
                            WheelView.this.post(new Runnable() {
                                @Override
                                public void run() {
                                    WheelView.this.smoothScrollTo(0, initialY - remainder);
                                    selectedIndex = divided + offset;
                                    onSelectedCallBack();
                                }
                            });
                        }


                    }


                } else {
                    initialY = getScrollY();
                    WheelView.this.postDelayed(scrollerTask, newCheck);
                }
            }
        };


    }

    int initialY;

    Runnable scrollerTask;
    int newCheck = 50;

    public void startScrollerTask() {

        initialY = getScrollY();
        this.postDelayed(scrollerTask, newCheck);
    }

    private void initData() {
        views.removeAllViews();
        displayItemCount = offset * 2 + 1;

        for (String item : items) {
            views.addView(createView(item));
        }

        int scrollHeight = (selectedIndex - offset) * itemHeight;
        refreshItemView(scrollHeight);
    }

    public void setTextSize(int textSize) {
        if (this.textSize != textSize) {
            configChanged = true;
        }
        this.textSize = textSize;
    }

    public void setValidIndex(int min, int max) {
        this.minValidIndex = min + offset;
        this.maxValidIndex = max + offset;
    }

    public void setAlignment(int alignment) {
        this.ALIGNMENT = alignment;
    }

    public void setGravity(int gravity) {
        this.GRAVITY = gravity;
    }

    int itemHeight = 0;


    private LinearLayout createView(String item) {
        LinearLayout viewContainer = new LinearLayout(context);
//        viewContainer.setGravity(Gravity.CENTER);

        TextView tv = new TextView(context);
        tv.setClickable(true);
        LayoutParams textLP = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textLP.gravity = GRAVITY;
        tv.setLayoutParams(textLP);
        tv.setSingleLine(true);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        tv.setText(" " + item);
        tv.setTextAlignment(ALIGNMENT);
        tv.setGravity(Gravity.CENTER);
        int padding = dip2px(1);
        tv.setPadding(padding, padding, padding, padding);

        LayoutParams containerParam = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getViewMeasuredHeight(tv));
        viewContainer.setLayoutParams(containerParam);
        viewContainer.addView(tv);

        if (0 == itemHeight || configChanged) {
            itemHeight = getViewMeasuredHeight(tv);
            Log.d(TAG, "itemHeight: " + itemHeight);

            views.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, itemHeight * displayItemCount));
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) this.getLayoutParams();
            this.setLayoutParams(new LinearLayout.LayoutParams(lp.width, itemHeight * displayItemCount));
            configChanged = false;
        }

        return viewContainer;
    }


    @Override
    protected void onScrollChanged(int l, int t, int oldL, int oldT) {
        super.onScrollChanged(l, t, oldL, oldT);
        refreshItemView(t);
        if (t > oldT) {
            scrollDirection = SCROLL_DIRECTION_DOWN;
        } else {
            scrollDirection = SCROLL_DIRECTION_UP;

        }
    }

    private void refreshItemView(int y) {
        int position = y / itemHeight + offset;
        int remainder = y % itemHeight;
        int divided = y / itemHeight;

        if (remainder == 0) {
            position = divided + offset;
        } else {
            if (remainder > itemHeight / 2) {
                position = divided + offset + 1;
            }
        }

        int childSize = views.getChildCount();
        for (int i = 0; i < childSize; i++) {
            LinearLayout itemView = (LinearLayout) views.getChildAt(i);
            TextView item = (TextView) itemView.getChildAt(0);
            if (null == item) {
                return;
            }
            if (maxValidIndex <= 0)
                maxValidIndex = items.size();
            if (position == i) {
                if (position >= minValidIndex && position <= maxValidIndex) {
                    item.setTextColor(Color.parseColor("#000000"));
                } else {
                    item.setTextColor(Color.parseColor("#999999"));
                }
                if (item.getTextSize() != textSize)
                    item.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);

                String text = item.getText().toString();
                item.setText(text.trim());

            } else if (i < position) {
                if (i == position - 1) {
                    item.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize - 2);
                    item.setTextColor(Color.parseColor("#999999"));
                } else if (i == position - 2) {
                    item.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize - 3);
                    item.setTextColor(Color.parseColor("#bbbbbb"));
                } else {
                    item.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize - 4);
                    item.setTextColor(Color.parseColor("#dfdfdf"));
                }
                String text = item.getText().toString();
                text = "  " + text.trim();
                item.setText(text);
            }

            if (i > position) {
                if (i == position + 1) {
                    item.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize - 2);
                    item.setTextColor(Color.parseColor("#999999"));
                } else if (i == position + 2) {
                    item.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize - 3);
                    item.setTextColor(Color.parseColor("#bbbbbb"));
                } else {
                    item.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize - 4);
                    item.setTextColor(Color.parseColor("#dfdfdf"));
                }
                String text = item.getText().toString();
                text = "  " + text.trim();
                item.setText(text);
            }

        }
    }

    int[] selectedAreaBorder;

    private int[] obtainSelectedAreaBorder() {
        if (null == selectedAreaBorder) {
            selectedAreaBorder = new int[2];
            selectedAreaBorder[0] = itemHeight * offset;
            selectedAreaBorder[1] = itemHeight * (offset + 1);
        }
        return selectedAreaBorder;
    }


    private int scrollDirection = -1;
    private static final int SCROLL_DIRECTION_UP = 0;
    private static final int SCROLL_DIRECTION_DOWN = 1;

    Paint paint;
    int viewWidth;

    @Override
    public void setBackground(Drawable background) {
        if (viewWidth == 0) {
            viewWidth = ((Activity) context).getWindowManager().getDefaultDisplay().getWidth();
            Log.d(TAG, "viewWidth: " + viewWidth);
        }

        if (null == paint) {
            paint = new Paint();
            paint.setColor(Color.parseColor("#bbbbbb"));
            paint.setStrokeWidth(dip2px(1f));
        }

        background = new Drawable() {
            @Override
            public void draw(Canvas canvas) {
                canvas.drawLine(0, obtainSelectedAreaBorder()[0], viewWidth, obtainSelectedAreaBorder()[0], paint);
                canvas.drawLine(0, obtainSelectedAreaBorder()[1], viewWidth, obtainSelectedAreaBorder()[1], paint);
            }

            @Override
            public void setAlpha(int alpha) {

            }

            @Override
            public void setColorFilter(ColorFilter cf) {

            }

            @Override
            public int getOpacity() {
                return 0;
            }
        };

        super.setBackground(background);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.d(TAG, "w: " + w + ", h: " + h + ", oldw: " + oldw + ", oldh: " + oldh);
        viewWidth = w;
        setBackground(null);
    }

    private void onSelectedCallBack() {
        if (null != onWheelViewListener) {
            if (selectedIndex - offset >= 0)
                onWheelViewListener.onSelected(selectedIndex - offset, items.get(selectedIndex));
        }

    }

    public void setSelection(int position) {
        final int p = position;
        selectedIndex = p + offset;
        this.post(new Runnable() {
            @Override
            public void run() {
                WheelView.this.smoothScrollTo(0, p * itemHeight);
            }
        });

    }

    public String getSelectedItem() {
        return items.get(selectedIndex);
    }

    public int getSelectedIndex() {
        return selectedIndex - offset;
    }


    @Override
    public void fling(int velocityY) {
        super.fling(velocityY / 3);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_UP) {

            startScrollerTask();
        }
        return super.onTouchEvent(ev);
    }

    private OnWheelViewListener onWheelViewListener;

    public void setOnWheelViewListener(OnWheelViewListener onWheelViewListener) {
        this.onWheelViewListener = onWheelViewListener;
    }

    private int dip2px(float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    private int getViewMeasuredHeight(View view) {
        int width = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        view.measure(width, expandSpec);
        return view.getMeasuredHeight();
    }

}