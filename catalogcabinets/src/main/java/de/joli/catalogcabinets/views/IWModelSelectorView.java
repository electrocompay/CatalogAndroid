package de.joli.catalogcabinets.views;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TabHost;

import java.util.ArrayList;

import de.joli.catalogcabinets.R;
import de.joli.catalogcabinets.model.IWCabinet;
import de.joli.cataloglib.model.IWColor;
import de.joli.cataloglib.model.IWColors;
import de.joli.cataloglib.model.IWModel;
import de.joli.cataloglib.util.Utils;

/**
 * Created by abelmiranda on 7/6/14.
 */
public class IWModelSelectorView extends FrameLayout implements TabHost.TabContentFactory, IWPickerView.OnPickerViewChanged {

    public interface OnModelSelectorChanged {

        public void didSelect(IWModelSelectorView modelSelectorView, IWColor color);

    }

    private IWCabinet cabinet;
    private OnModelSelectorChanged onModelSelectorChanged;

    private IWPickerView pickerModel;
    private IWPickerView picker2;
    private IWPickerView picker3;
    private IWPickerView picker4;
    private IWPickerView picker5;

    private View customView;

    @Override
    public View createTabContent(String s) {
        return this;
    }


    public IWModelSelectorView(Context context) {
        super(context);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {

            setId(Utils.generateViewId());

        } else {

            setId(View.generateViewId());
        }
        init();
    }

    public IWModelSelectorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public IWModelSelectorView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        customView = LayoutInflater.from(getContext()).inflate(R.layout.model_selector_view, this);
        pickerModel = (IWPickerView) customView.findViewById(R.id.pickermodel);
        picker2 = (IWPickerView) customView.findViewById(R.id.picker2);
        picker3 = (IWPickerView) customView.findViewById(R.id.picker3);
        picker4 = (IWPickerView) customView.findViewById(R.id.picker4);
        picker5 = (IWPickerView) customView.findViewById(R.id.picker5);

         pickerModel.setItems(IWColors.cabinetModels());
         pickerModel.setOnPickerViewChanged(this);
         picker2.setOnPickerViewChanged(this);
         picker3.setOnPickerViewChanged(this);
         picker4.setOnPickerViewChanged(this);
         picker5.setOnPickerViewChanged(this);
         didSelectRow(pickerModel, pickerModel.getSelection());
    }


    public IWCabinet getCabinet() {
        return cabinet;
    }

    public void setCabinet(IWCabinet cabinet) {
        this.cabinet = cabinet;
    }

    @Override
    public void didSelectRow(IWPickerView pickerView, IWColor color) {
        if (pickerModel.getSelection() == null)
            return;
        cabinet.setModel((IWModel) pickerModel.getSelection());
        if (cabinet.useDoors()) {
            processSelectionDoors(pickerView, color);
        } else if (cabinet.useModules())
        {
            processSelectionModules(pickerView ,color);
        }

        if (!cabinet.getModel().getCode().equals("C193")) {
         //   picker3.frame = CGRectMake(430, 0, picker3.bounds.size.width, picker3.bounds.size.height);
            picker2.setVisibility(View.VISIBLE);
        }
        if (onModelSelectorChanged != null) {
            onModelSelectorChanged.didSelect(this, color);
        }

    }

    private void processSelectionDoors(IWPickerView pickerView, IWColor color){
        boolean cascadeChange = false;
        if (pickerView == pickerModel) {
            cabinet.setModel((IWModel) pickerModel.getSelection());
            if (color.getCode().equals("40")) {
                picker2.setItems(IWColors.cabinet40Types());
                setModeCube();
            } else if (color.getCode().equals("55")){
                setModeCube();
                picker2.setItems(IWColors.cabinet55Types());
            } else if (color.getCode().equals("C193")){
                setModeCube();
//                picker3.frame = picker2.frame;
                picker2.setVisibility(View.GONE);
                picker3.setItems(IWColors.cabinetC193Sizes());
            }

            picker2.reloadAllComponents();
            cabinet.setType(picker2.getSelection());
            cascadeChange = true;

        }

        if (pickerView == picker2 || cascadeChange){
            cabinet.setType(picker2.getSelection());
            if (cabinet.getModel().getCode().equals("40")) {
                if (cabinet.getType().getCode().equals("H")) {
                    picker3.setItems(IWColors.cabinet40HSizes());
                } else if (cabinet.getType().getCode().equals("B")){
                    picker3.setItems(IWColors.cabinet40BSizes());
                } else if (cabinet.getType().getCode().equals("K")){
                    picker3.setItems(IWColors.cabinet40KSizes());
                }
            } else if (cabinet.getModel().getCode().equals("55")){
                if (cabinet.getType().getCode().equals("H")) {
                    picker3.setItems(IWColors.cabinet55HSizes());
                } else if (cabinet.getType().getCode().equals("B")){
                    picker3.setItems(IWColors.cabinet55BSizes());
                } else if (cabinet.getType().getCode().equals("K")){
                    picker3.setItems(IWColors.cabinet55KSizes());
                }
                cascadeChange = true;
            }
            picker3.reloadAllComponents();
            cabinet.setSize(picker3.getSelection());
        }

        if (pickerView == picker3 || cascadeChange)
        {
            cabinet.setSize(picker3.getSelection());
        }

    }

    private void processSelectionModules(IWPickerView pickerView, IWColor color){

        if (pickerView == pickerModel) {
            setModeCosYJoli83();
        }

        if (pickerView == picker2) {
            cabinet.setSize(picker2.getSelection());
            cabinet.setStripe(null);
            picker3.setPkEnabled(true);
        }

        if (pickerView == picker3 && picker3.getSelection() != null) {
            cabinet.getModule2().setSize(picker3.getSelection());
            cabinet.getModule2().setStripe(null);
            picker4.setPkEnabled(true);
            if (picker3.getSelectedIndex() == 0) {
                picker4.reset();
                picker5.resetAndDisable();
                cabinet.getModule4().setSize(picker5.getSelection());
            }

        }

        if (pickerView == picker4) {
            cabinet.getModule3().setSize(picker4.getSelection());
            cabinet.getModule3().setStripe(null);
            picker5.setPkEnabled(true);
            if (picker4.getSelectedIndex() == 0) {
                picker5.reset();
                cabinet.getModule4().setSize(picker5.getSelection());
            }
        }

        if (pickerView == picker5) {
            cabinet.getModule4().setSize(picker5.getSelection());
            cabinet.getModule4().setStripe(null);
        }

        cabinet.getModule2().setSize(picker3.getSelection());
        cabinet.getModule3().setSize(picker4.getSelection());
        cabinet.getModule4().setSize(picker5.getSelection());
        picker2.setPkleft(0);


        int weight1 =  cabinet.getDrawers().size() > 0 ? 2 : cabinet.getColors().size();
        int weight2 =  cabinet.getModule2().getDrawers().size() > 0 ? 2 : cabinet.getModule2().getColors().size();
        int weight3 =  cabinet.getModule3().getDrawers().size() > 0 ? 2 : cabinet.getModule3().getColors().size();
        int weight4 =  cabinet.getModule4().getDrawers().size() > 0 ? 2 : cabinet.getModule4().getColors().size();

        picker2.setPkright(weight2);
        picker3.setPkleft(weight1);
        picker3.setPkright(weight3);
        picker4.setPkleft(weight2);
        picker4.setPkright(weight4);
        picker5.setPkleft(weight3);
        picker5.setPkright(0);
        if (weight1 + weight2 + weight3 == 5) {
            picker5.setDissableMoreThan1(true);
            if (picker5.getLeft() == 1) {
                picker5.resetAndDisable();
                cabinet.getModule4().setSize(picker5.getSelection());
            }
        } else {
            picker5.setDissableMoreThan1(false);
        }

        picker4.setPkEnabled(picker3.getSelectedIndex() > 0);
        picker5.setPkEnabled(picker4.getSelectedIndex() > 0 && !(picker5.isDissableMoreThan1() && picker5.getPkleft() == 1));
        if (weight1 + weight2 + weight3 == 6) {
            // picker5 resetAndDisable;
            picker5.setDissableMoreThan1(true);
            picker5.setPkleft(1);
            cabinet.getModule4().setSize(picker5.getSelection());
        }

        picker2.refresh();
        picker3.refresh();
        picker4.refresh();
        picker5.refresh();

        onModelSelectorChanged.didSelect(this, color);
    }
    
    

    public void setOnModelSelectorChanged(OnModelSelectorChanged onModelSelectorChanged) {
        this.onModelSelectorChanged = onModelSelectorChanged;
    }

    public OnModelSelectorChanged getOnModelSelectorChanged() {
        return onModelSelectorChanged;
    }

    private void removeOneDoor(IWPickerView picker)
    {
        int currentSelection = picker.getItems().indexOf(picker.getSelection());
        ArrayList<IWColor> array = new ArrayList<IWColor>(picker.getItems());
        IWColor color = array.get(1);
        if (color.getCode().equals("1,0")) {
           array.remove(color);
        }
        picker.setItems(array);

        if (currentSelection > 1) {
            picker.setSelection(array.get(currentSelection--));
        }

    }

    private void setModeCosYJoli83(){
        picker2.setTitle("M1 - Module 1");
        ArrayList<IWColor> array = new ArrayList<IWColor>(IWColors.cabinet83Modules());
        array.remove(0);
        picker2.setItems(array);
        picker3.setItems(IWColors.cabinet83Modules());
        picker4.setItems(IWColors.cabinet83Modules());
        picker5.setItems(IWColors.cabinet83Modules());

        picker2.setSelection(array.get(0));
        processSelectionModules(picker2, picker2.getSelection());
        picker3.setTitle("M2 - Module 2");
        picker4.setTitle("M3 - Module 3");
        picker5.setTitle("M4 - Module 4");
        picker4.setVisibility(View.VISIBLE);
        picker5.setVisibility(View.VISIBLE);
    }

    private void setModeCube(){
        picker2.setTitle("Type");
        picker3.setTitle("Size");
        picker4.setVisibility(View.VISIBLE);
        picker5.setVisibility(View.GONE);
    }

}

