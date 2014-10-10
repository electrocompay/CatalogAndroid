package de.joli.catalogcabinets.views;

import android.content.Context;
import android.widget.FrameLayout;

import de.joli.catalogcabinets.model.IWCabinet;

/**
 * Created by abel.miranda on 10/9/14.
 */
public class IWColorsPanelView extends FrameLayout {

    public IWColorsPanelView(Context context) {
        super(context);
    }

    public static IWColorsPanelView colorsPanelNineDoors() {
        return null;
    }

    public static IWColorsPanelView colorsPanelFourDoors() {
        return null;
    }

    public static IWColorsPanelView colorsPanelModuleDoors() {
        return null;
    }

    public void setDelegate(IWMultipleSelectorView iwMultipleSelectorView) {
    }

    public void setCabinet(IWCabinet cabinet) {
    }

    public void setOneSelectionMode(boolean checked) {
    }
}
