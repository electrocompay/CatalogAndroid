package com.indianwebs.catalog.app;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.indianwebs.catalog.app.drawers.IWDrawerChair;
import com.indianwebs.catalog.app.drawers.IWDrawerTable;
import com.indianwebs.catalog.app.model.IWChair;
import de.joli.cataloglib.model.IWColor;
import de.joli.cataloglib.model.IWColors;
import de.joli.cataloglib.model.IWModel;
import com.indianwebs.catalog.app.model.IWTable;
import de.joli.cataloglib.util.Utils;
import de.joli.cataloglib.views.IWSelectorView;

import java.util.ArrayList;
import java.util.Arrays;

import de.joli.cataloglib.ModelActivity;


public class TCModelActivity extends ModelActivity {

    protected ViewGroup selectorView;
    private IWTable table;
    private IWDrawerTable drawer;
    private IWChair chair;
    private IWDrawerChair drawerChair;
    private IWSelectorView selectorModelView;
    private IWSelectorView selectorTableColorView;
    private IWSelectorView selectorTableLegsColorView;
    private IWSelectorView selectorChairModelView;
    private IWSelectorView selectorChairLegsColorView;
    private IWSelectorView selectorChairColorView;
    private IWDrawerTable thumbDrawer;
    private IWDrawerChair thumbDrawerChair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lockDraw();
        selectorModelView.setSelection(0);
        selectorChairModelView.setSelection(0);
        unlockDraw();
        drawAll();
    }

    @Override
    protected void createViews() {
        selectorView = (ViewGroup) findViewById(R.id.selectorView);
        selectorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                drawer.setFrontView(!drawer.isFrontView());
                drawerChair.setFrontView(drawer.isFrontView());
                drawAll();
            }
        });
    }

    @Override
    protected void createDrawers() {
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

    @Override
    protected void createTabs() {
        super.createTabs();
        selectorModelView = addTabWithSelector("modelTab", "1. Table Model", IWColors.getTableModels(), "model");
        selectorTableColorView = addTabWithSelector("colorTab", "2. Table Color", IWColors.getTableColors(), "color");
        selectorTableLegsColorView = addTabWithSelector("legTab", "3. Table Legs Color", IWColors.getTableLegColors(), "leg color");
        selectorChairModelView = addTabWithSelector("chairModelTab", "4. Chair Model", IWColors.getChairModels(), "model");
        selectorChairColorView = addTabWithSelector("chairColorTab", "5. Chair Color", IWColors.getChairColors(), "color");
        selectorChairLegsColorView = addTabWithSelector("chairLegTab", "6. Chair Legs Color", IWColors.getChairLegColors(), "leg color");
    }


    @Override
    public void didSelectColor(IWSelectorView selectorViewController, IWColor color) {
        lockDraw();

        if (selectorViewController == selectorModelView)
        {
            table.setModel((IWModel) color);
            selectorTableColorView.setFilteredItems(table.getModel().getColors());
            selectorTableColorView.setItems(IWColors.getTableColors());
            selectorTableLegsColorView.setFilteredItems(table.getModel().getLegColors());
            selectorTableLegsColorView.setItems(IWColors.getTableLegColors());
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
            } else {
                selectorChairColorView.setFilteredItems(chair.getModel().getColors());
                selectorChairColorView.setItems(IWColors.getChairColors());
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
          /*  selectorChairLegsColorView.setFilteredItems(chair.getModel().getLegColors());
            selectorChairLegsColorView.setItems(IWColors.getChairLegColors());*/

        } else if (selectorViewController == selectorChairLegsColorView)
        {
            chair.setLegsColor(color);
        }

        unlockDraw();
        drawAll();

    }

    private ArrayList<IWColor> colorsRemoveIndex(ArrayList<IWColor> colors, int index) {
        ArrayList<IWColor> array = new ArrayList<IWColor>(colors);
        array.remove(index);
        return array;
    }

    @Override
    protected void drawAll() {
        if (lockDraw == 0) {
            drawer.clear();
            drawerChair.clear();

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
