package de.joli.catalogcabinets.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.Switch;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;

import de.joli.catalogcabinets.CModelActivity;
import de.joli.catalogcabinets.R;
import de.joli.catalogcabinets.model.IWCabinet;
import de.joli.cataloglib.model.IWColor;
import de.joli.cataloglib.model.IWColors;
import de.joli.cataloglib.views.IWSelectorView;

/**
 * Created by abel.miranda on 10/7/14.
 */
public class IWMultipleSelectorView extends FrameLayout implements TabHost.TabContentFactory, CompoundButton.OnCheckedChangeListener {

    public enum IWMultipleSelectorMode{
        MultipleSelectorModeNineColors,
        MultipleSelectorModeFourColors,
        MultipleSelectorModeModuleColors
    };


    public interface IWMultipleSelectorViewDelegate {

        public void didSelectColor(IWMultipleSelectorView selectorViewController, IWColor color, int index);

    }

    private  IWMultipleSelectorViewDelegate delegate;
    private  String propertyName;
    private  String headerTitle;
    private  IWCabinet cabinet;
    private  IWMultipleSelectorMode mode;

    private ViewGroup multipleContainer;
    private IWColorsPanelView panelColors;
    private Switch switch1;
    private Switch switch2;
    private TextView labelAllColors;
    private TextView labelAllColor1;
    private TextView labelColorDoor;

    private View customView;
    private IWSelectorView selectorview;

    public void setDelegate(CModelActivity cModelActivity){
    }

    @Override
    public View createTabContent(String s) {
        return customView;
    }

    public IWMultipleSelectorView(Context context, IWMultipleSelectorMode mode){
        super(context);

        this.mode = mode;
        switch (mode) {
            case MultipleSelectorModeNineColors:
                panelColors = IWColorsPanelView.colorsPanelNineDoors();
                break;
            case MultipleSelectorModeFourColors:
                panelColors = IWColorsPanelView.colorsPanelFourDoors();
                break;
            case MultipleSelectorModeModuleColors:
                panelColors = IWColorsPanelView.colorsPanelModuleDoors();
                break;

            default:
                break;
        }
/*        panelColors.setDelegate(this);
        multipleContainer.addView(panelColors);
        selectorview.setItems(IWColors.cabinetColors());
        panelColors.setCabinet(cabinet);
        switch1.setOnCheckedChangeListener(this);
        switch2.setOnCheckedChangeListener(this);

        customView = LayoutInflater.from(getContext()).inflate(R.layout.multiple_selector_view, this);*/
    }

    public void setCabinet(IWCabinet cabinet) {

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (compoundButton == switch1) {
            switch2.setChecked(!switch1.isChecked());
        } else {
            switch1.setChecked(!switch2.isChecked());
        }
        panelColors.setOneSelectionMode(switch1.isChecked());
    }


}
