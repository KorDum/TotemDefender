package ru.kordum.totemDefender.util;

import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;

import java.text.NumberFormat;

public class Formatter {
    public static String getStat(TextFormatting color, String key, float value) {
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);
        return getLocalize(color, key, format(value));
    }

    public static String getProp(TextFormatting color, String key, float value, boolean percent) {
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);
        return getProp(color, key, nf.format(value), value > 0, percent);
    }

    public static String getProp(TextFormatting color, String key, int value, boolean percent) {
        return getProp(color, key, String.valueOf(value), value > 0, percent);
    }

    public static String getProp(TextFormatting color, String key, String value, boolean positive, boolean percent) {
        if (percent) {
            value += "%";
        }
        return getLocalize(color, key, (positive ? "+" : "") + value);
    }

    public static String getLocalize(String key, Object... args) {
        TextComponentTranslation translation = new TextComponentTranslation(key, args);
        return translation.getFormattedText();
    }

    public static String getLocalize(TextFormatting color, String key, Object... args) {
        TextComponentTranslation translation = new TextComponentTranslation(key, args);
        translation.getStyle().setColor(color);
        return translation.getFormattedText();
    }

    private static String format(float d) {
        return d == (int) d
            ? String.format("%d", (int) d)
            : String.format("%s", d);
    }
}
