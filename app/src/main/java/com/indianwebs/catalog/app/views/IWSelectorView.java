package com.indianwebs.catalog.app.views;

import android.content.Context;
import android.os.Build;
import android.text.style.TextAppearanceSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TabHost;
import android.widget.TextView;

import com.indianwebs.catalog.app.R;
import com.indianwebs.catalog.app.model.IWColor;
import com.indianwebs.catalog.app.util.Utils;

import java.util.ArrayList;

/**
 * Created by abelmiranda on 7/6/14.
 */
public class IWSelectorView extends FrameLayout implements TabHost.TabContentFactory {


    @Override
    public View createTabContent(String s) {
        return this;
    }

    public interface IWSelectorViewControllerDelegate
    {
        public void didSelectColor(IWSelectorView selectorViewController, IWColor color);
    }

    private ArrayList<IWColor> items;
    private ArrayList<String> filteredItems;
    private IWColor selectedColor;
    private int selectedIndex;
    private IWSelectorViewControllerDelegate delegate;
    private String propertyName = "";
    private String headerTitle = "";


    private RelativeLayout scrollView1;
    private IWScrollView scrollView;
    private IWOptionView selectedView;
    private TextView headerLabel;
    private TextView propertyNameView;
    ArrayList<IWColor> filteredList;
    ArrayList<View> subviews;
    private View marker;
    private View marker_back;

    public void setSelection(int index)
    {
        selectedIndex = index;
        selectedColor = filteredList.get(selectedIndex);
        IWOptionView optionView = (IWOptionView) subviews.get(selectedIndex);
        for (View oView : subviews)
        {
            ((IWOptionView) oView).clearSelection();
        }
        optionView.setSelected(true);
        selectedView.getLabel().setText(optionView.getLabel().getText());
        selectedView.setImage(optionView.getImage());
        if (delegate != null) {
            delegate.didSelectColor(this ,selectedColor);
        }
    }

    public IWSelectorView(Context context) {
        super(context);
        init();
    }

    public IWSelectorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public IWSelectorView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.selector_view, this);

        scrollView1 = (RelativeLayout) findViewById(R.id.scrollLayout);
        scrollView = (IWScrollView) findViewById(R.id.scrollView);
        scrollView.setOnScrollListener(new IWScrollView.OnScrollListener() {
            @Override
            public void scrolled() {
                updateMarkers();
            }
        });
        selectedView = (IWOptionView) findViewById(R.id.selectedView);
        headerLabel = (TextView) findViewById(R.id.headerLabel);
        propertyNameView = (TextView) findViewById(R.id.propertyNameView);
        marker = findViewById(R.id.marker);
        marker_back = findViewById(R.id.marker_back);

        setSelected(true);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {

            setId(Utils.generateViewId());

        } else {

            setId(View.generateViewId());
        }
    }

    @Override
    protected void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        if (visibility == View.VISIBLE)
        {
            propertyNameView.setText(String.format("Active %s", propertyName));
            updateMarkers();
        }
    }

    public void prepare(){
        propertyNameView.setText(String.format("Active %s", propertyName));
        updateMarkers();
    }

    private void updateMarkers() {
        if (scrollView.getScrollX() == 0)
            marker_back.setVisibility(View.INVISIBLE);
        else
            marker_back.setVisibility(View.VISIBLE);
        if (scrollView.getScrollX() == scrollView1.getWidth() - scrollView.getWidth())
           marker.setVisibility(View.INVISIBLE);
        else
           marker.setVisibility(View.VISIBLE);
    }

    public ArrayList<IWColor> getItems() {
        return items;
    }

    public void setItems(ArrayList<IWColor> items) {

        this.items = items;


        String uniqueCategory = items.get(0).getCategory();
        filteredList = new ArrayList<IWColor>();
        for (IWColor color : items) {
            if (filteredItems == null || colorFiltered(color.getCode())) {
                if (uniqueCategory != null) {
                    if (uniqueCategory != color.getCategory()){
                        uniqueCategory = null;
                    }
                }
                filteredList.add(color);
            }
        }

        scrollView1.removeAllViews();
        subviews = new ArrayList<View>();
        IWOptionView optionView;
        int page = 0;

        if (uniqueCategory != null) {
            headerLabel.setText(String.format("Choose %s", uniqueCategory));
        } else {
            headerLabel.setVisibility(View.GONE);
        }

        String priorCategory = null;
        for (IWColor color : filteredList) {
            optionView = new IWOptionView(getContext());
            optionView.setId(Utils.generateViewId());
            optionView.getLabel().setText(color.getName());
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            if (subviews.size() > 0) {
                params.addRule(RelativeLayout.RIGHT_OF, subviews.get(subviews.size() - 1).getId());
                params.addRule(RelativeLayout.ALIGN_TOP, subviews.get(subviews.size() - 1).getId());
                params.setMargins(10, 0, 0, 0);
            }
            else {
                params.setMargins(10, 10, 0, 0);
                params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                if (uniqueCategory != null)
                    params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            }
            scrollView1.addView(optionView);
            subviews.add(optionView);
            optionView.setTag(page);
            optionView.setLayoutParams(params);
            page++;
            optionView.setClickable(true);
            optionView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    optionSelected(view);
                }
            });
            optionView.setImage(color.getFile());
            if (uniqueCategory == null && priorCategory != color.getCategory()) {
                TextView newLabel = new TextView(getContext());
                newLabel.setTextAppearance(getContext(), android.R.style.TextAppearance_Medium);
                newLabel.setId(Utils.generateViewId());
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                layoutParams.addRule(RelativeLayout.ALIGN_LEFT, optionView.getId());
                params.addRule(RelativeLayout.BELOW, newLabel.getId());
                newLabel.setText(color.getCategory());
                newLabel.setLayoutParams(layoutParams);
                scrollView1.addView(newLabel);

                priorCategory = color.getCategory();
            }
        }
        if (subviews.size() > 0) {
            optionView = (IWOptionView) subviews.get(0);
            optionView.setSelected(true);
            setSelection((Integer) optionView.getTag());
        }
        prepare();
    }

    private void optionSelected(View view) {
        setSelection((Integer) view.getTag());
    }

    boolean colorFiltered(String code)
    {
        for (String filteredCode : filteredItems) {
        if (filteredCode.equals(code)) {
            return true;
        }
    }
        return false;
    }

    public ArrayList<String> getFilteredItems() {
        return filteredItems;
    }

    public void setFilteredItems(ArrayList<String> filteredItems) {
        this.filteredItems = filteredItems;
    }

    public IWColor getSelectedColor() {
        return selectedColor;
    }

    public void setSelectedColor(IWColor selectedColor) {
        this.selectedColor = selectedColor;
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getHeaderTitle() {
        return headerTitle;
    }

    public void setHeaderTitle(String headerTitle) {
        this.headerTitle = headerTitle;
    }

    public IWSelectorViewControllerDelegate getDelegate() {
        return delegate;
    }

    public void setDelegate(IWSelectorViewControllerDelegate delegate) {
        this.delegate = delegate;
    }
}
