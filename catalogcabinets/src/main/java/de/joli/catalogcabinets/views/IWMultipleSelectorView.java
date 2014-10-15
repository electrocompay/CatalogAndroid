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
import de.joli.catalogcabinets.util.ColorUtil;
import de.joli.cataloglib.model.IWColor;
import de.joli.cataloglib.model.IWColors;
import de.joli.cataloglib.views.IWSelectorView;

/**
 * Created by abel.miranda on 10/7/14.
 */
public class IWMultipleSelectorView extends FrameLayout implements TabHost.TabContentFactory, CompoundButton.OnCheckedChangeListener, IWColorsPanelView.IWColorsPanelViewDelegate, IWSelectorView.IWSelectorViewControllerDelegate {

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

    public void setDelegate(CModelActivity delegate){
        this.delegate = delegate;
    }

    @Override
    public View createTabContent(String s) {
        return customView;
    }

    public IWMultipleSelectorView(Context context, IWMultipleSelectorMode mode){
        super(context);
        customView = LayoutInflater.from(getContext()).inflate(R.layout.multiple_selector_view, this);

        multipleContainer = (ViewGroup) customView.findViewById(R.id.multiples_container);
        selectorview = (IWSelectorView) customView.findViewById(R.id.selectorView);
        selectorview.setSelectedAreaVisible(false);
        switch1 = (Switch) customView.findViewById(R.id.switch1);
        switch2 = (Switch) customView.findViewById(R.id.switch2);

        this.mode = mode;
        switch (mode) {
            case MultipleSelectorModeNineColors:
                panelColors = IWColorsPanelView.colorsPanelNineDoors(context);
                break;
            case MultipleSelectorModeFourColors:
                panelColors = IWColorsPanelView.colorsPanelFourDoors(context);
                break;
            case MultipleSelectorModeModuleColors:
                panelColors = IWColorsPanelView.colorsPanelModuleDoors(context);
                break;

            default:
                break;
        }
        panelColors.setDelegate(this);
        multipleContainer.addView(panelColors);
        selectorview.setItems(IWColors.cabinetColors());
        selectorview.setDelegate(this);
//        panelColors.setCabinet(cabinet);
        switch1.setOnCheckedChangeListener(this);
        switch2.setOnCheckedChangeListener(this);

    }

    public void setCabinet(IWCabinet cabinet) {
        this.cabinet = cabinet;
        panelColors.setCabinet(cabinet);
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

    @Override
    public void didChange(IWColorsPanelView colorsPanelView) {
        if (delegate != null) {
            delegate.didSelectColor(this, null, 0);
        }
    }

    @Override
    public void didSelectButton(IWColorsPanelView colorsPanelView, int tag) {
        if (mode == IWMultipleSelectorMode.MultipleSelectorModeModuleColors) {
            if (tag < 2) {
                ArrayList<IWColor> items = IWColors.cabinetColors();
                if (items != selectorview.getItems()) {
                    selectorview.setItems(IWColors.cabinetColors());
                }
            } else if (tag < 5){
                ArrayList<IWColor> items = IWColors.cabinetDrawerColors();
                if (items != selectorview.getItems()) {
                    selectorview.setItems(IWColors.cabinetDrawerColors());
                }
            } else {
                ArrayList<IWColor> items = IWColors.cabinetStripeColors();
                if (items != selectorview.getItems()) {
                    selectorview.setItems(IWColors.cabinetStripeColors());
                }
            }

        }
        IWColor color = ColorUtil.colorByCode(colorsPanelView.getSelectedView().getColor().getCode(), selectorview.getItems());
        int index = selectorview.getItems().indexOf(color);
        if (index < selectorview.getItems().size()) {
            selectorview.setSelection(index);
        }

    }

    @Override
    public void didSelectColor(IWSelectorView selectorViewController, IWColor color) {
        panelColors.setColorToSelection(color);
        didChange(panelColors);
    }

    public void setItems(ArrayList<IWColor> items) {
        selectorview.setItems(items);
    }

}
