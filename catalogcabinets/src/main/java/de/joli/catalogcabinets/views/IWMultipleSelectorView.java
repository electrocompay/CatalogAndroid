package de.joli.catalogcabinets.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TabHost;

import java.util.ArrayList;

import de.joli.catalogcabinets.CModelActivity;
import de.joli.catalogcabinets.R;
import de.joli.catalogcabinets.model.IWCabinet;
import de.joli.cataloglib.model.IWColor;

/**
 * Created by abel.miranda on 10/7/14.
 */
public class IWMultipleSelectorView extends FrameLayout implements TabHost.TabContentFactory{


    private id<IWMultipleSelectorViewControllerDelegate> delegate;
    private  NSString* propertyName;
    private  NSString* headerTitle;
    private  IWCabinet* cabinet;
    private  IWMultipleSelectorMode mode;

    IBOutlet UIScrollView* scrollView;
    IBOutlet UILabel* headerLabel;
    IBOutlet UILabel *propertyNameView;
    IBOutlet UIView *multipleContainer;
    NSMutableArray* filteredList;
    NSMutableArray* subviews;
    IWColorsPanelView *panelColors;
    IBOutlet UISwitch *switch1;
    IBOutlet UISwitch *switch2;
    IBOutlet UILabel *labelAllColors;
    IBOutlet UILabel *labelAllColor1;
    IBOutlet UILabel *labelColorDoor;
    IBOutlet UIView *marker;
    IBOutlet UIView *marker_back;


    private final View customView;

    public enum MultipleSelectorMode{
        MultipleSelectorModeNineColors,
        MultipleSelectorModeFourColors,
        MultipleSelectorModeModuleColors
    };

    public void setDelegate(CModelActivity cModelActivity){
    }

    @Override
    public View createTabContent(String s) {
        return customView;
    }

    public IWMultipleSelectorView(Context context, MultipleSelectorMode mode){
        super(context);
        customView = LayoutInflater.from(getContext()).inflate(R.layout.multiple_selector_view, this);
    }

    public void setCabinet(IWCabinet cabinet) {

    }
}
