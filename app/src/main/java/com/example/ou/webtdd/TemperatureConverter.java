package com.example.ou.webtdd;

import javax.xml.transform.sax.TemplatesHandler;

/**
 * Created by ou on 2018/9/20.
 */

public class TemperatureConverter {
    public static final double ABSOLUTE_ZERO_C = -273.15d;
    public static final double ABSOLUTE_ZERO_F = -459.67d;
    public static final String ERROR_MESSAGE_BELOW_ZERO_FMT= "Invalid temperatue: %.2f%c below absolute zero";
    public static enum OP{C2F,F2C};
    public static double ftoc(double f){
        if (f<ABSOLUTE_ZERO_F){
            throw new InvalidTemperatureException(
                    String.format(ERROR_MESSAGE_BELOW_ZERO_FMT,f,'F')
            );
        }
        return ((f-32)/1.8d);
    }
    public static double ctof(double c) {
        if (c < ABSOLUTE_ZERO_C) {
            throw new InvalidTemperatureException(
                    String.format(ERROR_MESSAGE_BELOW_ZERO_FMT, c, 'C')
            );
        }
        return (c * 1.8d + 32);
    }
}
