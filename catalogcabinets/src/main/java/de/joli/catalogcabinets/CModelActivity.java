package de.joli.catalogcabinets;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import de.joli.catalogcabinets.drawers.IWDrawerCabinet;
import de.joli.catalogcabinets.model.IWCabinet;
import de.joli.catalogcabinets.util.ColorUtil;
import de.joli.catalogcabinets.views.IWLegsColorSelectorView;
import de.joli.catalogcabinets.views.IWModelSelectorView;
import de.joli.catalogcabinets.views.IWMultipleSelectorView;
import de.joli.cataloglib.ModelActivity;
import de.joli.cataloglib.model.IWColor;
import de.joli.cataloglib.model.IWColors;
import de.joli.cataloglib.views.IWSelectorView;


public class CModelActivity extends ModelActivity implements IWModelSelectorView.OnModelSelectorChanged, IWMultipleSelectorView.IWMultipleSelectorViewDelegate {


    private IWModelSelectorView selectorModelView;
    private IWSelectorView selectorTopView;
    private IWSelectorView selectorSideView;
    private IWMultipleSelectorView selectorDoorsView;
    private IWMultipleSelectorJ193ViewController selectorDoorsJ193View;
    private IWLegsColorSelectorView selectorLegsColorView;
    private IWMultipleSelectorView selectorModule1View;
    private IWMultipleSelectorView selectorModule2View;
    private IWMultipleSelectorView selectorModule3View;
    private IWMultipleSelectorView selectorModule4View;
    private IWDrawerCabinet drawer;
    private IWCabinet cabinet;
    //    private ImageView selectorView;
    private Button button;
    private Button button2;


    private static final String TAB_MODEL_SELECTOR = "TAB_MODEL_SELECTOR";
    private static final String TAB_TOP_SELECTOR = "TAB_TOP_SELECTOR";
    private static final String TAB_SIDE_SELECTOR = "TAB_SIDE_SELECTOR";
    private static final String TAB_MULTIPLE_SELECTOR = "TAB_MULTIPLE_SELECTOR";


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
        View view = (View) findViewById(R.id.selectorView).getParent();
        view.setVisibility(View.GONE);
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

        selectorDoorsView = new IWMultipleSelectorView(this, IWMultipleSelectorView.IWMultipleSelectorMode.MultipleSelectorModeNineColors);
        prepareMultipleSelectorView("4. Door Color", selectorDoorsView);


        selectorDoorsJ193View = new IWMultipleSelectorJ193ViewController(this, IWMultipleSelectorView.IWMultipleSelectorMode.MultipleSelectorModeFourColors);
        prepareMultipleSelectorView("4. Door Color", selectorDoorsJ193View);

        selectorLegsColorView = new IWLegsColorSelectorView(this);
        selectorLegsColorView.setPropertyName("5. Leg color");
        selectorLegsColorView.setCabinet(cabinet);
        prepareSelector(selectorLegsColorView, IWColors.cabinetLegColors());
        addTab(TAB_SIDE_SELECTOR, "5. Leg Color", selectorLegsColorView);

        selectorModule1View = new IWMultipleSelectorView(this, IWMultipleSelectorView.IWMultipleSelectorMode.MultipleSelectorModeModuleColors);
        prepareMultipleSelectorView("4. M1 Color", selectorModule1View);

        selectorModule2View = new IWMultipleSelectorView(this, IWMultipleSelectorView.IWMultipleSelectorMode.MultipleSelectorModeModuleColors);
        prepareMultipleSelectorView("5. M2 Color", selectorModule2View);

        selectorModule3View = new IWMultipleSelectorView(this, IWMultipleSelectorView.IWMultipleSelectorMode.MultipleSelectorModeModuleColors);
        prepareMultipleSelectorView("6. M3 Color", selectorModule3View);

        selectorModule4View = new IWMultipleSelectorView(this, IWMultipleSelectorView.IWMultipleSelectorMode.MultipleSelectorModeModuleColors);
        prepareMultipleSelectorView("7. M4 Color", selectorModule4View);

        unlockDraw();

        didSelect(selectorModelView, cabinet.getModel());
   //     drawAll();

    }

    private void prepareMultipleSelectorView(String title, IWMultipleSelectorView multipleSelectorView) {
        multipleSelectorView.setCabinet(cabinet);
        multipleSelectorView.setDelegate(this);
        addTab(TAB_MULTIPLE_SELECTOR, title, multipleSelectorView);
        tabHost.getTabWidget().getChildTabViewAt(tabHost.getTabWidget().getTabCount() - 1).setVisibility(View.GONE);
    }


    private void prepareSelector(IWSelectorView selector, ArrayList<IWColor> colors) {
        selector.setDelegate(this);
        //      tabContent addSubview:selector.view];
        //    selector.view.frame = tabContent.bounds;
        selector.setItems(colors);
    }

    private ArrayList<IWColor> colorsWithoutBrown() {
        ArrayList<IWColor> array = new ArrayList<IWColor>(IWColors.cabinetColors());
        array.remove(array.size() - 1);
        return array;
    }

    private ArrayList<IWColor> sideColorsWithoutBrown() {
        ArrayList<IWColor> array = new ArrayList<IWColor>(IWColors.cabinetSideColors());
        array.remove(array.size() - 1);
        return array;
    }

    private ArrayList<IWColor> topColorsWithoutBrown() {
        ArrayList<IWColor> array = new ArrayList<IWColor>(IWColors.cabinetTopColors());
        array.remove(array.size() - 1);
        return array;
    }


    @Override
    public void didSelectColor(IWSelectorView selectorViewController, IWColor color) {
        if (selectorViewController == selectorTopView) {
            cabinet.setTop(color);
        } else if (selectorViewController == selectorSideView) {
            cabinet.setSide(color);
        } else if (selectorViewController == selectorLegsColorView) {
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
//        selectorDoorsView.setCabinet(cabinet);
        drawAll();
    }

    @Override
    public void didSelect(IWModelSelectorView modelSelectorView, IWColor color) {
        lockDraw();

        for (int i = 3; i < tabHost.getTabWidget().getTabCount(); i++)
            tabHost.getTabWidget().getChildTabViewAt(i).setVisibility(View.GONE);
        if (cabinet.useDoors()) {
            if (cabinet.getModel().getCode().equals("C193")) {
                selectorDoorsJ193View.setCabinet(cabinet);
                tabHost.getTabWidget().getChildTabViewAt(4).setVisibility(View.VISIBLE);
            } else {
                selectorDoorsView.setCabinet(cabinet);
                tabHost.getTabWidget().getChildTabViewAt(3).setVisibility(View.VISIBLE);
            }

        }
        if (cabinet.hasLegs()) {
//            addTab("selectorLegsColorView", "5. Leg color", selectorLegsColorView);
            tabHost.getTabWidget().getChildTabViewAt(5).setVisibility(View.VISIBLE);
        }
        if (cabinet.useModules()) {
            if (cabinet.getSize() != null && cabinet.getSize().getCode() != null)
//                addTab("selectorModule1View", "4. M1 Color", selectorModule1View);
                tabHost.getTabWidget().getChildTabViewAt(6).setVisibility(View.VISIBLE);
            if (cabinet.getModule2().getSize() != null && cabinet.getModule2().getSize().getCode() != null)
                //            addTab("selectorModule1View", "5. M2 Color", selectorModule1View);
                tabHost.getTabWidget().getChildTabViewAt(7).setVisibility(View.VISIBLE);
            if (cabinet.getModule3().getSize() != null && cabinet.getModule3().getSize().getCode() != null)
//                addTab("selectorModule1View", "6. M3 Color", selectorModule1View);
                tabHost.getTabWidget().getChildTabViewAt(8).setVisibility(View.VISIBLE);
            if (cabinet.getModule4().getSize() != null && cabinet.getModule4().getSize().getCode() != null)
//                addTab("selectorModule1View", "7. M4 Color", selectorModule1View);
                tabHost.getTabWidget().getChildTabViewAt(9).setVisibility(View.VISIBLE);

            selectorModule1View.setCabinet(cabinet);
            selectorModule2View.setCabinet(cabinet.getModule2());
            selectorModule3View.setCabinet(cabinet.getModule3());
            selectorModule4View.setCabinet(cabinet.getModule4());
        }
        //      tabController setTabWidth:135;
        //      tabController setSelectedTabIndex:0 animated:false;

        if (cabinet.getModel().getCode().equals("C83") || cabinet.getModel().getCode().equals("J83") || cabinet.getModel().getCode().equals("C193")) {
            selectorDoorsView.setItems(IWColors.cabinetColors());
            selectorSideView.setItems(IWColors.cabinetSideColors());
            ArrayList<IWColor> items = cabinet.getModel().getCode().equals("C193") ? ColorUtil.withoutColor("34,35,41", IWColors.cabinetTopColors()) : IWColors.cabinetTopColors();
            selectorTopView.setItems(items);
        } else {
            selectorDoorsView.setItems(colorsWithoutBrown());
            selectorSideView.setItems(sideColorsWithoutBrown());
            if (cabinet.getModel().getCode().equals("55"))
                selectorTopView.setItems(ColorUtil.withoutColor("40", IWColors.cabinetTopColors()));
            else
                selectorTopView.setItems(ColorUtil.withoutColor("40,41", IWColors.cabinetTopColors()));
            selectorLegsColorView.setItems(IWColors.cabinetLegColors());
        }
        unlockDraw();
        drawAll();
    }

    @Override
    public void didSelectColor(IWMultipleSelectorView selectorViewController, IWColor color, int index) {
        drawAll();
    }

}
