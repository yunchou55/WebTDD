package com.example.ou.webtdd;

import junit.framework.TestCase;

import java.util.HashMap;

/**
 * Created by ou on 2018/9/20.
 */
public class TemperatureConverterTestJuint3 extends TestCase {
    private static final HashMap<Double,Double> conversionTableDouble = new HashMap<Double, Double>();
    static {
        conversionTableDouble.put(0.0,32.0);
        conversionTableDouble.put(100.0,212.0);
        conversionTableDouble.put(-1.0,30.20);
        conversionTableDouble.put(-100.0,-148.0);
        conversionTableDouble.put(32.0,89.60);
        conversionTableDouble.put(-40.0,-40.0);
        conversionTableDouble.put(-273.0,-459.40);
    }

    public TemperatureConverterTestJuint3(String name) {
        super(name);
    }

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
    }

    public void testFtoc() throws Exception {
        for (double c:conversionTableDouble.keySet()){
            final double f = conversionTableDouble.get(c);
            final double ca = TemperatureConverter.ftoc(f);
            final double delta = Math.abs(ca-c);
            final String msg = ""+f+"F -> "+c+"C but is "+ca+" (delta "+delta+")";
            assertTrue(msg,delta<0.0001);
        }
    }

    public void testCtof() throws Exception {
        for (double c:conversionTableDouble.keySet()){
            final double f = conversionTableDouble.get(c);
            final double fa = TemperatureConverter.ctof(c);
            final double delta = Math.abs(fa-f);
            final String msg = ""+c+"C -> "+f+"F but is "+fa+" (delta "+delta+")";
            assertTrue(msg,delta<0.0001);
        }
    }
    public final void testExceptionForLessThnAbsolureZeroF(){
        try{
            TemperatureConverter.ctof(TemperatureConverter.ABSOLUTE_ZERO_F-1);
            fail();
        }catch (InvalidTemperatureException ex){

        }
    }
    public final void testExceptionCorLessThnAbsolureZeroF(){
        try{
            TemperatureConverter.ctof(TemperatureConverter.ABSOLUTE_ZERO_C-1);
            fail();
        }catch (InvalidTemperatureException ex){

        }
    }

}