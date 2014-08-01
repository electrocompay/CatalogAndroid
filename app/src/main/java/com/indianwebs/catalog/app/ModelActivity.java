package com.indianwebs.catalog.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.indianwebs.catalog.app.drawers.IWDrawerChair;
import com.indianwebs.catalog.app.drawers.IWDrawerTable;
import com.indianwebs.catalog.app.model.IWChair;
import com.indianwebs.catalog.app.model.IWColor;
import com.indianwebs.catalog.app.model.IWColors;
import com.indianwebs.catalog.app.model.IWModel;
import com.indianwebs.catalog.app.model.IWTable;
import com.indianwebs.catalog.app.util.Utils;
import com.indianwebs.catalog.app.views.IWSelectorView;

import java.util.ArrayList;
import java.util.Arrays;


public class ModelActivity extends Activity implements IWSelectorView.IWSelectorViewControllerDelegate {

    private TabHost tabHost;
    private IWTable table;
    private IWDrawerTable drawer;
    private IWChair chair;
    private IWDrawerChair drawerChair;
    private ViewGroup content;
    private IWSelectorView selectorModelView;
    private IWSelectorView selectorTableColorView;
    private IWSelectorView selectorTableLegsColorView;
    private IWSelectorView selectorChairModelView;
    private IWSelectorView selectorChairLegsColorView;
    private IWSelectorView selectorChairColorView;
    private int lockDraw = 0;
    private RelativeLayout homeMenu;
    private RelativeLayout extraMenu;
    private ViewGroup selectorView;
    private IWDrawerTable thumbDrawer;
    private IWDrawerChair thumbDrawerChair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_model);
        content = (ViewGroup) findViewById(R.id.content);
        selectorView = (ViewGroup) findViewById(R.id.selectorView);
        selectorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                drawer.setFrontView(!drawer.isFrontView());
                drawerChair.setFrontView(drawer.isFrontView());
                drawAll();
            }
        });

        createHomeMenu();
        createExtraMenu();
        createDrawers();
        createTabs();
        lockDraw();
        selectorModelView.setSelection(0);
        selectorChairModelView.setSelection(0);
        unlockDraw();
        drawAll();
    }

    private void createDrawers() {
        table = new IWTable();
        drawer = new IWDrawerTable(this);
        drawer.setView(content);
        drawer.setOffsetY(50);
        chair = new IWChair();
        drawerChair = new IWDrawerChair(this);
        drawerChair.setView(content);
        drawerChair.setOffsetY(50);

        thumbDrawer = new IWDrawerTable(this);
        thumbDrawer.setView(selectorView);

        thumbDrawerChair = new IWDrawerChair(this);
        thumbDrawerChair.setView(selectorView);
    }

    private void createTabs() {
        tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();

        selectorModelView = addTabWithSelector("modelTab", "1. Table Model", IWColors.getTableModels());
        selectorTableColorView = addTabWithSelector("colorTab", "2. Table Color", IWColors.getTableColors());
        selectorTableLegsColorView = addTabWithSelector("legTab", "3. Table Legs Color", IWColors.getTableLegColors());
        selectorChairModelView = addTabWithSelector("chairModelTab", "4. Chair Model", IWColors.getChairModels());
        selectorChairColorView = addTabWithSelector("chairColorTab", "5. Chair Color", IWColors.getChairColors());
        selectorChairLegsColorView = addTabWithSelector("chairLegTab", "6. Chair Legs Color", IWColors.getChairLegColors());
    }

    private IWSelectorView addTabWithSelector(String tag, String title, ArrayList<IWColor> colors)
    {
        IWSelectorView newSelectorView = new IWSelectorView(this);
        newSelectorView.setDelegate(this);
        newSelectorView.setItems(colors);
        addTab(tag, title, newSelectorView);
        return newSelectorView;
     }

    private void addTab(String tag, String title, TabHost.TabContentFactory view)
    {
        TabHost.TabSpec tabSpec1 = tabHost.newTabSpec(tag);
        View customTab = getLayoutInflater().inflate(R.layout.tab, null);
        TextView textView = (TextView) customTab.findViewById(R.id.tabTextView);
        textView.setText(title);
        tabSpec1.setIndicator(customTab);
        tabSpec1.setContent(view);
        tabHost.addTab(tabSpec1);
    }

    private void createExtraMenu() {
        extraMenu = (RelativeLayout) findViewById(R.id.extraMenu);
        ListView extraMenuList = (ListView) findViewById(R.id.extraMenuList);
        ImageView extraButton = (ImageView) findViewById(R.id.extraButton);
        extraButton.setClickable(true);
        extraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (extraMenu.getVisibility() == View.VISIBLE) {
                    extraMenu.setVisibility(View.GONE);
                } else {
                    homeMenu.setVisibility(View.GONE);
                    extraMenu.setVisibility(View.VISIBLE);
                    extraMenu.bringToFront();
                }
            }
        });

        ArrayAdapter<String> extraMenuAdapter = new ArrayAdapter<String>(this, R.layout.menu_item, R.id.textView, new String[]{"Hola", "Como", "Estan"});
        extraMenuList.setAdapter(extraMenuAdapter);
    }

    private void createHomeMenu() {
        homeMenu = (RelativeLayout) findViewById(R.id.homeMenu);
        ListView homeMenuList = (ListView) findViewById(R.id.homeMenuList);
        ImageView homeButton = (ImageView) findViewById(R.id.homeButton);
        homeButton.setClickable(true);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (homeMenu.getVisibility() == View.VISIBLE) {
                    homeMenu.setVisibility(View.GONE);
                } else {
                    extraMenu.setVisibility(View.GONE);
                    homeMenu.setVisibility(View.VISIBLE);
                    homeMenu.bringToFront();
                    findViewById(android.R.id.content).invalidate();
                }
            }
        });

        ArrayAdapter<String> homeMenuAdapter = new ArrayAdapter<String>(this, R.layout.menu_item, R.id.textView, new String[]{"Hola", "Como", "Estan"});
        homeMenuList.setAdapter(homeMenuAdapter);
    }

    @Override
    public void didSelectColor(IWSelectorView selectorViewController, IWColor color) {
        lockDraw();

        if (selectorViewController == selectorModelView)
        {
            table.setModel((IWModel) color);
        } else if (selectorViewController == selectorTableColorView)
        {
            table.setColor(color);
        } else if (selectorViewController == selectorTableLegsColorView)
        {
            table.setLegsColor(color);
        } else if (selectorViewController == selectorChairModelView)
        {
            chair.setModel((IWModel) color);
            ArrayList<IWColor> legsColors = IWColors.getChairLegColors();
            IWColor wood = legsColors.get(legsColors.size() - 1);
            if (!color.getCode().contains("Van Gogh")) {
                wood.setFile("Wood VG.jpg");
                wood.setName("Black Wood");
                selectorChairLegsColorView.setItems(legsColors);
            } else {
                wood.setFile("Wood.jpg");
                wood.setName("Wood");
                selectorChairLegsColorView.setItems(legsColors);
            }

            int index = IWColors.getChairModels().indexOf(color);
            if (index >= 0) {
                if (index == 4 || index == 5 || index==13 || index==14) {
                    selectorChairColorView.setItems(colorsRemoveIndex(IWColors.getChairColors(), 12));
                } else {
                    selectorChairColorView.setItems(IWColors.getChairColors());
                }
                if (index == 19 || index == 20) {
                    selectorChairColorView.setItems(colorsRemoveIndex(IWColors.getChairColors(), 15));
                } else {
                    selectorChairColorView.setItems(IWColors.getChairColors());
                }
            }

            if (chair.getModel().getName().equals("Rafael-A") || chair.getModel().getName().equals("Rafael-S")) {
            selectorChairColorView.setItems(Utils.colorListWithoutColor(IWColors.getChairColors(), ("15")));
        }
        } else if (selectorViewController == selectorChairColorView)
        {
            chair.setColor(color);
            if (chair.getModel().getName().equals("Rafael-A")) {
                if (color.getCode().equals("14")) {
                    chair.getModel().setLegColors((ArrayList<String>) Arrays.asList("22".split(",")));
                } else if (color.getCode().equals("15")) {
                    chair.getModel().setLegColors((ArrayList<String>) Arrays.asList("23".split(",")));
                } else if (color.getCode().equals("18")) {
                    chair.getModel().setLegColors((ArrayList<String>) Arrays.asList("26".split(",")));
                } else if (color.getCode().equals("42")) {
                    chair.getModel().setLegColors((ArrayList<String>) Arrays.asList("24".split(",")));
            }
        }
            selectorChairLegsColorView.setFilteredItems(chair.getModel().getLegColors());
            selectorChairLegsColorView.setItems(IWColors.getChairLegColors());

        } else if (selectorViewController == selectorChairLegsColorView)
        {
            chair.setLegsColor(color);
        }

        unlockDraw();
        drawAll();

    }

    private void unlockDraw() {
        lockDraw--;
    }

    private void lockDraw() {
        lockDraw++;
    }

    private ArrayList<IWColor> colorsRemoveIndex(ArrayList<IWColor> colors, int index) {
        ArrayList<IWColor> array = new ArrayList<IWColor>(colors);
        array.remove(index);
        return array;
    }

    private void drawAll() {
        if (lockDraw == 0) {
            drawer.clear();

            drawerChair.drawFurniture(chair);
            drawer.drawFurniture(table);
            thumbDrawerChair.setFrontView(!drawer.isFrontView());
            thumbDrawer.setFrontView(!drawer.isFrontView());

            thumbDrawer.clear();
            thumbDrawerChair.drawFurniture(chair);
            thumbDrawer.drawFurniture(table);
        }
    }
}
