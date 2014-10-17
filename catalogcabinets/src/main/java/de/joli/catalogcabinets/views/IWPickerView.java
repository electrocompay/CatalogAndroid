package de.joli.catalogcabinets.views;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;

import de.joli.catalogcabinets.R;
import de.joli.cataloglib.model.IWColor;
import de.joli.cataloglib.util.Utils;
import de.joli.cataloglib.views.IWScrollView;
import kankan.wheel.widget.OnWheelChangedListener;
import kankan.wheel.widget.OnWheelScrollListener;
import kankan.wheel.widget.WheelView;
import kankan.wheel.widget.adapters.WheelViewAdapter;

/**
 * Created by abel.miranda on 10/4/14.
 */
public class IWPickerView extends FrameLayout implements WheelViewAdapter, OnWheelScrollListener {


    @Override
    public void onScrollingStarted(WheelView wheel) {

    }

    @Override
    public void onScrollingFinished(WheelView wheel) {
        int newValue = wheel.getCurrentItem();
        if (selectionInvalid(newValue)) {
            _pickerView.setCurrentItem(_lastRow, true);
        } else {
            IWColor newSelection = items.get(newValue);
            if (items.indexOf(newSelection) != _lastRow) {
                _lastRow = newValue;
          //      selection = newSelection;
                if (onPickerViewChanged != null) {
                    onPickerViewChanged.didSelectRow(this, newSelection);
                }
            }
        }

    }

    public interface OnPickerViewChanged {

        public void didSelectRow(IWPickerView pikerView, IWColor color);

    }


    private ArrayList<IWColor> items;
    private OnPickerViewChanged onPickerViewChanged;

    private String title;
//    private IWColor selection;
    private boolean pkEnabled;
//    private int selectedIndex;
    private int pkleft;
    private int pkright;
    private boolean dissableMoreThan1;

    private WheelView _pickerView;
    private TextView _label;
    int _lastRow;


    public void reloadAllComponents(){
        _pickerView.refreshDrawableState();
        _pickerView.setCurrentItem(0, false);
        //selection = items.get(0);
    };

    public void reset(){
        if (items.size() > 0) {
            _pickerView.setCurrentItem(0);
           // selection = items.get(0);
        }
    };

    public void resetAndDisable(){
        reset();
        setEnabled(false);
    };

    public void refresh(){

    };

    public IWPickerView(Context context) {
        super(context);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {

            setId(Utils.generateViewId());

        } else {

            setId(View.generateViewId());
        }
        init();
    }

    public IWPickerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public IWPickerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        View view = LayoutInflater.from(getContext()).inflate(R.layout.iw_picker_view, null);
        addView(view);
        _pickerView = (WheelView) findViewById(R.id.picker_view);
        _label = (TextView) findViewById(R.id.textView);
     //   _pickerView.addChangingListener(this);
        _pickerView.addScrollingListener(this);

    }

    public ArrayList<IWColor> getItems() {
        return items;
    }

    public void setItems(ArrayList<IWColor> items) {
        this.items = items;
        if (_pickerView != null){
            _pickerView.setViewAdapter(null);
            _pickerView.setViewAdapter(this);
        }
        invalidate();
        requestLayout();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        _label.setText(title);
    }

    public IWColor getSelection() {
        return items.get(getSelectedIndex());
    }

    public void setSelection(IWColor selection) {
        setSelectedIndex(items.indexOf(selection));
    }

    public int getSelectedIndex() {
        //return selectedIndex;
        return _pickerView.getCurrentItem();
    }

    public void setSelectedIndex(int selectedIndex) {
//        this.selectedIndex = selectedIndex;
        _pickerView.setCurrentItem(0);
    }

    public boolean isDissableMoreThan1() {
        return dissableMoreThan1;
    }

    public void setDissableMoreThan1(boolean dissableMoreThan1) {
        this.dissableMoreThan1 = dissableMoreThan1;
    }


    public boolean isPkEnabled() {
        return pkEnabled;
    }

    public void setPkEnabled(boolean pkEnabled) {
        this.pkEnabled = pkEnabled;
    }

    public int getPkleft() {
        return pkleft;
    }

    public void setPkleft(int pkleft) {
        this.pkleft = pkleft;
    }

    public int getPkright() {
        return pkright;
    }

    public void setPkright(int pkright) {
        this.pkright = pkright;
    }

    @Override
    public int getItemsCount() {
        if (items != null) {
            return items.size();
        }
        return 0;
    }

    @Override
    public View getItem(int index, View convertView, ViewGroup parent) {
        TextView pickerLabel = (TextView) convertView;

//        StrokeLabel stroke;
        if (pickerLabel == null) {
            pickerLabel = new TextView(getContext());
            pickerLabel.setGravity(Gravity.CENTER_HORIZONTAL + Gravity.CENTER_VERTICAL);
           // pickerLabel. setLineBreakMode:NSLineB
        } else {
            //  stroke = pickerLabel.subviews[0];
        }


        pickerLabel.setLines(2);
        if (selectionInvalid(index)) {
         //   stroke.hidden = NO;
            pickerLabel.setTextColor(Color.rgb(255/2, 255/2, 255/2));
        } else {
         //   stroke.hidden = YES;reakByWordWrapping];
//            pickerLabel.setBackground(new ColorDrawable(Color.TRANSPARENT));
//            stroke = new StrokeLabel(getContext());
            //   pickerLabel. addSubview:stroke];
            //   stroke.frame = pickerLabel.bounds;
            pickerLabel.setTextColor(Color.BLACK);
        }

        IWColor color = items.get(index);
        pickerLabel.setText(color.getName());

        return pickerLabel;
    }

    private boolean selectionInvalid(int row){
        IWColor color = items.get(row);
        if (color == null || color.getCode() == null) return false;
        return (pkleft == 1 || pkright == 1) && color.getCode().equals("1,0") || (dissableMoreThan1 && row > 1);
    }


    @Override
    public View getEmptyItem(View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    public void setOnPickerViewChanged(OnPickerViewChanged onPickerViewChanged) {
        this.onPickerViewChanged = onPickerViewChanged;
    }

    public OnPickerViewChanged getOnPickerViewChanged() {
        return onPickerViewChanged;
    }
}
