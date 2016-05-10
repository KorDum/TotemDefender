package ru.kordum.totemDefender.common.utils;

import net.minecraft.util.StatCollector;

import java.text.NumberFormat;

public class Formatter {

    //---------------------------------------------------------------------------
    //
    // PUBLIC METHODS
    //
    //---------------------------------------------------------------------------

    public static String getProp(String key, float value, boolean percent) {
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);
        return getProp(key, nf.format(value), value > 0, percent);
    }

    public static String getProp(String key, int value, boolean percent) {
        return getProp(key, String.valueOf(value), value > 0, percent);
    }

    public static String getProp(String key, String value, boolean positive, boolean percent) {
        if (percent) {
            value += "%";
        }

        return StatCollector.translateToLocalFormatted(
            key,
            (positive ? "+" : "") + value
        );
    }
}
