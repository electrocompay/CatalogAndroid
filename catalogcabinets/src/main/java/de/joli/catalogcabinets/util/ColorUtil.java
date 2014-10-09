package de.joli.catalogcabinets.util;

import java.util.ArrayList;

import de.joli.cataloglib.model.IWColor;

/**
 * Created by abel.miranda on 10/2/14.
 */
public class ColorUtil {

    public static IWColor colorByCode(String code, ArrayList<IWColor> colors){

        for (IWColor color: colors) {
            if (color.getCode().equals(code)) {
                return color;
            }
        }
        return null;

    }

    public static ArrayList<IWColor> withoutColor(String code, ArrayList<IWColor> colors){

        ArrayList<IWColor> array = new ArrayList<IWColor>(colors);

        String[] colorCodes = code.split(",");
        for (String colorCode: colorCodes) {
            for (IWColor color: array) {
                if (color.getCode().equals(colorCode)) {
                    array.remove(color);
                    break;
                }
            }
        }
        return array;
    }

}
