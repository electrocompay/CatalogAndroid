package com.indianwebs.catalog.app.model;

import de.joli.cataloglib.model.IWColor;
import de.joli.cataloglib.model.IWFurniture;
import de.joli.cataloglib.model.IWModel;

/**
 * Created by abelmiranda on 6/30/14.
 */
public class IWChair extends IWFurniture {

    private IWModel model;
    private IWColor color;
    private IWColor legsColor;

    public IWModel getModel() {
        return model;
    }

    public void setModel(IWModel model) {
        this.model = model;
    }

    public IWColor getColor() {
        return color;
    }

    public void setColor(IWColor color) {
        this.color = color;
    }

    public IWColor getLegsColor() {
        return legsColor;
    }

    public void setLegsColor(IWColor legsColor) {
        this.legsColor = legsColor;
    }

    public IWChair() {
        color = IWColor.colorWithName("", "01", null);
        legsColor = IWColor.colorWithName("", "27", null);
    }

}
