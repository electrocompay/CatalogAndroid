package de.joli.catalogcabinets;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

import de.joli.catalogcabinets.drawers.IWDrawerCabinet;
import de.joli.catalogcabinets.model.IWCabinet;
import de.joli.catalogcabinets.views.IWLegsColorSelectorView;
import de.joli.catalogcabinets.views.IWModelSelectorView;
import de.joli.catalogcabinets.views.IWMultipleSelectorView;
import de.joli.cataloglib.ModelActivity;
import de.joli.cataloglib.model.IWColor;
import de.joli.cataloglib.model.IWColors;
import de.joli.cataloglib.views.IWSelectorView;


public class CModelActivity extends ModelActivity implements IWModelSelectorView.OnModelSelectorChanged {


    private IWModelSelectorView selectorModelView;
    private IWSelectorView selectorTopView;
    private IWSelectorView selectorSideView;
    private IWMultipleSelectorView selectorDoorsView;
    private IWMultipleSelectorView selectorDoorsJ193View;
    private IWLegsColorSelectorView selectorLegsColorView;
    private IWMultipleSelectorView selectorModule1View;
    private IWMultipleSelectorView selectorModule2View;
    private IWMultipleSelectorView selectorModule3View;
    private IWMultipleSelectorView selectorModule4View;
    private IWDrawerCabinet drawer;
    private IWCabinet cabinet;
//    private ImageView selectorView;
    private Button button;
    private Button  button2;


    private static final String TAB_MODEL_SELECTOR = "TAB_MODEL_SELECTOR";
    private static final String TAB_TOP_SELECTOR =  "TAB_TOP_SELECTOR";
    private static final String TAB_SIDE_SELECTOR = "TAB_SIDE_SELECTOR";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        cabinet = new IWCabinet();
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void drawAll() {
            drawer.clear();
            drawer.drawForniture(cabinet);
    }

    @Override
    protected void createViews() {
    }

    @Override
    protected void createDrawers() {
        drawer = new IWDrawerCabinet(this);
        drawer.setView(content);
        //drawer.offsetY = 50;
    }

    @Override
    protected void createTabs() {
        super.createTabs();
            lockDraw();
            selectorModelView = new IWModelSelectorView(this);
            selectorModelView.setCabinet(cabinet);
            selectorModelView.setOnModelSelectorChanged(this);
            addTab(TAB_MODEL_SELECTOR, "1. Model", selectorModelView);

            selectorTopView = new IWSelectorView(this);
            selectorTopView.setPropertyName("top color");
            prepareSelector(selectorTopView, topColorsWithoutBrown());
            selectorTopView.setFilteredItems(cabinet.getModel().getColors());
            addTab(TAB_TOP_SELECTOR, "2. Top color", selectorTopView);

            selectorSideView = new IWSelectorView(this);
            selectorSideView.setPropertyName("side color");
            prepareSelector(selectorSideView, sideColorsWithoutBrown());
            selectorSideView.setFilteredItems(cabinet.getModel().getLegColors());
            addTab(TAB_SIDE_SELECTOR, "3. Side Color", selectorSideView);

    /*        selectorDoorsView = IWMultipleSelectorViewController alloc] initWithMode:MultipleSelectorModeNineColors];
            [self prepareMultipleSelectorView:selectorDoorsView];

            selectorDoorsJ193View = [[IWMultipleSelectorJ193ViewController alloc] initWithNibName:@"IWMultipleSelectorJ193ViewController" bundle:nil];
            [selectorDoorsJ193View setMode:MultipleSelectorModeFourColors];

            [self prepareMultipleSelectorView:selectorDoorsJ193View];

            selectorLegsColorView = [[IWLegsColorSelectorViewController alloc] initWithNibName:@"IWLegsColorSelectorViewController" bundle:nil];
            [selectorLegsColorView setPropertyName:@"Leg color"];
            [selectorLegsColorView setCabinet:cabinet];
            [self prepareSelector:selectorLegsColorView withColors:[IWColors cabinetLegColors]];

            selectorModule1View = [[IWMultipleSelectorViewController alloc] initWithMode:MultipleSelectorModeModuleColors];
            [self prepareMultipleSelectorView:selectorModule1View];

            selectorModule2View = [[IWMultipleSelectorViewController alloc] initWithMode:MultipleSelectorModeModuleColors];
            [self prepareMultipleSelectorView:selectorModule2View];

            selectorModule3View = [[IWMultipleSelectorViewController alloc] initWithMode:MultipleSelectorModeModuleColors];
            [self prepareMultipleSelectorView:selectorModule3View];

            selectorModule4View = [[IWMultipleSelectorViewController alloc] initWithMode:MultipleSelectorModeModuleColors];
            [self prepareMultipleSelectorView:selectorModule4View];

            [self BrowserTabView:nil didSelecedAtIndex:0];
*/
            unlockDraw();
            drawAll();

    }

    private void prepareSelector(IWSelectorView selector, ArrayList<IWColor> colors){
        selector.setDelegate(this);
  //      tabContent addSubview:selector.view];
    //    selector.view.frame = tabContent.bounds;
        selector.setItems(colors);
    }

    private ArrayList<IWColor> colorsWithoutBrown(){
        ArrayList<IWColor> array = new ArrayList<IWColor>(IWColors.cabinetColors());
        array.remove(array.size() - 1);
        return array;
    }

    private ArrayList<IWColor> sideColorsWithoutBrown(){
        ArrayList<IWColor> array = new ArrayList<IWColor>(IWColors.cabinetSideColors());
        array.remove(array.size() - 1);
        return array;
    }

    private ArrayList<IWColor> topColorsWithoutBrown(){
        ArrayList<IWColor> array = new ArrayList<IWColor>(IWColors.cabinetTopColors());
        array.remove(array.size() - 1);
        return array;
    }


    @Override
    public void didSelectColor(IWSelectorView selectorViewController, IWColor color) {
        if (selectorViewController == selectorTopView)
        {
            cabinet.setTop(color);
        } else if (selectorViewController == selectorSideView)
        {
            cabinet.setSide(color);
        } else if (selectorViewController == selectorLegsColorView)
        {
            cabinet.setLegsColor(color);
        }

    /*else if (selectorViewController == selectorChairModelView)
     {
     [chair setModel:(IWModel*) color];
     } else if (selectorViewController == selectorChairColorView)
     {
     [chair setColor:color];
     } else if (selectorViewController == selectorChairLegsColorView)
     {
     [chair setLegsColor:color];
     }*/
    //    selectorDoorsView.setCabinet(cabinet);
        drawAll();
    }

    @Override
    public void didSelect(IWModelSelectorView modelSelectorView, IWColor color) {
        lockDraw();
//        selectorDoorsView.setCabinet(cabinet);
//        selectorDoorsJ193View.setCabinet(cabinet);
 /*       tabController removeTabAtIndex:8 animated:false;
        tabController removeTabAtIndex:7 animated:false;
        tabController removeTabAtIndex:6 animated:false;
        tabController removeTabAtIndex:5 animated:false;
        tabController removeTabAtIndex:4 animated:false;
        tabController removeTabAtIndex:3 animated:false;*/
   /*     if (cabinet.useDoors()) {
            tabHost.getTabWidget().setTag();
            tabHost.getTabWidget().tabaddTabWithTitle:@"4. Door color";
        }
        if (cabinet.hasLegs) {
            tabController addTabWithTitle:@"5. Leg color";
        }
        if (cabinet.useModules) {
            if (cabinet.size && cabinet.size.code)
            tabController addTabWithTitle:@"4. M1 Color";
            if (cabinet.module2.size && cabinet.module2.size.code)
            tabController addTabWithTitle:@"5. M2 Color";
            if (cabinet.module3.size && cabinet.module3.size.code)
            tabController addTabWithTitle:@"6. M3 Color";
            if (cabinet.module4.size && cabinet.module4.size.code)
            tabController addTabWithTitle:@"7. M4 Color";
            selectorModule1View setCabinet:cabinet;
            selectorModule2View setCabinet:cabinet.module2;
            selectorModule3View setCabinet:cabinet.module3;
            selectorModule4View setCabinet:cabinet.module4;
        }
        tabController setTabWidth:135;
        tabController setSelectedTabIndex:0 animated:false;

        if (cabinet.model.code isEqualToString:@"C83" || cabinet.model.code isEqualToString:@"J83" || cabinet.model.code isEqualToString:@"C193" ) {
            selectorDoorsView setItems:IWColors cabinetColors;
            selectorSideView setItems:IWColors cabinetSideColors;
            selectorTopView.items = cabinet.model.code isEqualToString:@"C193" ? IWColors cabinetTopColors withoutColor:@"34,35,41" : IWColors cabinetTopColors;
        } else {
            selectorDoorsView setItems:self colorsWithoutBrown;
            selectorSideView setItems:self sideColorsWithoutBrown;
            if (cabinet.model.code isEqualToString:@"55")
            selectorTopView.items = IWColors cabinetTopColors withoutColor:@"40";
            else
            selectorTopView.items = IWColors cabinetTopColors withoutColor:@"40,41";
            selectorLegsColorView setItems:IWColors cabinetLegColors;
        }*/
        unlockDraw();
        drawAll();

    }
}
