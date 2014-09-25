package de.joli.cataloglib.model;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by abelmiranda on 6/30/14.
 */
public class IWModel extends IWColor {

    private ArrayList<String> colors;
    private ArrayList<String> legColors;

    public static IWModel modelWithName(String name, String code, String file, String colors, String legColors)
    {
        IWModel model = new IWModel();
        model.setName(name);
        model.setCode(code);
        model.setFile(file);
        if (colors != null) {
            model.setColors(new ArrayList<String>(Arrays.asList(colors.split(","))));
        }
        if (legColors != null) {
            model.setLegColors(new ArrayList<String>(Arrays.asList(legColors.split(","))));
        }
        return model;
    }


    public ArrayList<String> getColors() {
        return colors;
    }

    public void setColors(ArrayList<String> colors) {
        this.colors = colors;
    }

    public ArrayList<String> getLegColors() {
        return legColors;
    }

    public void setLegColors(ArrayList<String> legColors) {
        this.legColors = legColors;
    }

    public IWModel withCategory(String category)
    {
        this.setCategory(category);
        return this;
    }
}
