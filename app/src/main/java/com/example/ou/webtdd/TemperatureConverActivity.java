package com.example.ou.webtdd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

public class TemperatureConverActivity extends AppCompatActivity {
    private EditNumber Celsius;
    private EditNumber Fahrenheit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Celsius=findViewById(R.id.celsius);
        Fahrenheit=findViewById(R.id.fahrenheit);
        Celsius.addTextChangedListener(new TemperatureChangedWatcher(TemperatureConverter.OP.C2F));
        Fahrenheit.addTextChangedListener(new TemperatureChangedWatcher(TemperatureConverter.OP.F2C));

    }
    private class TemperatureChangedWatcher implements TextWatcher {
        private final EditNumber mSource;
        private final EditNumber mDest;
        private TemperatureConverter.OP mop;

        private TemperatureChangedWatcher(TemperatureConverter.OP mop) {
            if (mop == TemperatureConverter.OP.C2F){
                this.mSource = Celsius;
                this.mDest = Fahrenheit;
            }else {
                this.mSource = Fahrenheit;
                this.mDest = Celsius;
            }
            this.mop = mop;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (!mDest.hasWindowFocus()||mDest.hasFocus()||charSequence==null){
                return;
            }
            final String string = charSequence.toString();
            if ("".equals(string)){
                mDest.setText("");
                return;
            }
            try{
                final double temp = Double.parseDouble(string);
                final double result = (mop == TemperatureConverter.OP.C2F?TemperatureConverter.ctof(temp):TemperatureConverter.ftoc(temp));
                final String resultString = String.format("%.2f",result);
                mDest.setNumber(result);
                mDest.setSelection(resultString.length());
            }catch (Exception e){
                mSource.setError("Error"+e.getLocalizedMessage());
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }
}
