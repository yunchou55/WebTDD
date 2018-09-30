package com.example.ou.webtdd;

import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import static android.test.ViewAsserts.assertLeftAligned;
import static android.test.ViewAsserts.assertOnScreen;
import static android.test.ViewAsserts.assertRightAligned;

/**
 * Created by ou on 2018/9/20.
 */

public class TemperatureConverActivityTests extends ActivityInstrumentationTestCase2<TemperatureConverActivity> {
    private TemperatureConverActivity temperatureConverActivity;
    private EditNumber Celsius;
    private EditNumber Fahrenheit;
    private TextView celsius_lable;
    private TextView fahrenheit_lable;
    public TemperatureConverActivityTests() {
        this("TemperatureConverActivity");
    }
    public TemperatureConverActivityTests(String name) {
        super(TemperatureConverActivity.class);
        setName(name);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        temperatureConverActivity = getActivity();
        Celsius = temperatureConverActivity.findViewById(R.id.celsius);
        Fahrenheit = temperatureConverActivity.findViewById(R.id.fahrenheit);
        celsius_lable = temperatureConverActivity.findViewById(R.id.celsius_layout);
        fahrenheit_lable = temperatureConverActivity.findViewById(R.id.fahrenheit_label);
    }
    public final void testPreconditions(){
        assertNotNull(temperatureConverActivity);
    }
    public final void testHasInputFields(){
        assertNotNull(Celsius);
        assertNotNull(Fahrenheit);
    }
//    public final void testFieldsShouldStartEmpty(){
//        assertEquals("EditText" ,Celsius.getText().toString());
//        assertEquals("EditText",Fahrenheit.getText().toString());
//    }
    //屏幕位置
    public final void testFieldsOnScreen(){
        final Window window = temperatureConverActivity.getWindow();
        final View orgin = window.getDecorView();
        assertOnScreen(orgin,Celsius);
        assertOnScreen(orgin,Fahrenheit);
    }
    public final void testAlignment(){
        assertLeftAligned(celsius_lable,Celsius,6);
        assertLeftAligned(fahrenheit_lable,Fahrenheit,6);
        assertLeftAligned(Celsius,Fahrenheit);
        assertRightAligned(Celsius,Fahrenheit);
    }
    //字体大小
    public final void testFontSize(){
        final float expected = 24.0f;
        assertEquals(expected,celsius_lable.getTextSize());
        assertEquals(expected,fahrenheit_lable.getTextSize());
    }
    //页边距测试
    public final void testMargins(){
        LinearLayout.LayoutParams lp;
        final int expected = 6;
        lp = (LinearLayout.LayoutParams) Celsius.getLayoutParams();
        assertEquals(expected,lp.leftMargin);
        assertEquals(expected,lp.rightMargin);
        lp = (LinearLayout.LayoutParams) Fahrenheit.getLayoutParams();
        assertEquals(expected,lp.leftMargin);
        assertEquals(expected,lp.rightMargin);
    }
    //测试输入框的输入值位置
    public final void testJustification(){
        final int expected = Gravity.RIGHT|Gravity.CENTER_VERTICAL;
        int actual = Celsius.getGravity();
        assertEquals(String.format("Expected 0x%02x but was 0x%02x",expected,actual),expected,actual);
        actual = Fahrenheit.getGravity();
        assertEquals(String.format("Expected 0x%02x but was 0x%02x",expected,actual),expected,actual);
    }
    //测试键盘预留位置
    public final void testVirtualKeyboardSpaceRecserved(){
        final int exected = 280;
        final int actual = Fahrenheit.getBottom();
        assertTrue(actual <= exected);
    }
    //温度装换
    @UiThreadTest
    public final void testF2CConversion(){
        Celsius.clear();
        Fahrenheit.clear();
        final double f = 32.5;
        Fahrenheit.requestFocus();
        Fahrenheit.setNumber(f);
        Celsius.requestFocus();
        final double epectedC = TemperatureConverter.ftoc(f);
        final double actualC = Celsius.getNumber();
        final double detlta = Math.abs(epectedC - actualC);
        final String msg = ""+f+"F -> "+epectedC+"C but was "+actualC+"C (delta "+detlta+")";
        assertTrue(msg,detlta<0.005);
    }
}
