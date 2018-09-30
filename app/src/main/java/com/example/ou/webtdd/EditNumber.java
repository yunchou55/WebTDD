package com.example.ou.webtdd;

import android.content.Context;
import android.text.method.DigitsKeyListener;
import android.util.AttributeSet;

import java.io.InputStream;

/**
 * Created by ou on 2018/9/20.
 */

public class EditNumber extends android.support.v7.widget.AppCompatEditText {
    private static final String DEFAULT_FORMAT = "%.2f";
    public EditNumber(Context context) {
        super(context);
        init();
    }

    public EditNumber(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EditNumber(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        final DigitsKeyListener[] filters = new DigitsKeyListener[]{    //**************************
                DigitsKeyListener.getInstance(true,true)};
        setFilters(filters);
    }

    public void clear() {
        setText("");
    }

    public void setNumber(double number) {
        super.setText(String.format(DEFAULT_FORMAT,number));
    }
    public double getNumber(){
        return Double.valueOf(getText().toString());
    }
}
