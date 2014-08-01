package com.indianwebs.catalog.app.model;

/**
 * Created by abelmiranda on 6/30/14.
 */
public class IWTable extends IWFurniture {

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

    public IWTable()
    {
        color = IWColor.colorWithName(null, "29", null);
        legsColor = IWColor.colorWithName(null, "27", null);
    }
}
