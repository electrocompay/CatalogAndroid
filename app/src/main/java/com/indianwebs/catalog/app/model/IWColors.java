package com.indianwebs.catalog.app.model;

import java.util.ArrayList;

/**
 * Created by abelmiranda on 7/15/14.
 */
public class IWColors {

    private static ArrayList<IWColor> tableModels;
    private static ArrayList<IWColor> tableColors;
    private static ArrayList<IWColor> tableLegColors;
    private static ArrayList<IWColor> chairModels;
    private static ArrayList<IWColor> chairColors;
    private static ArrayList<IWColor> chairLegColors;

    public static ArrayList<IWColor> getTableModels() {
        if (tableModels == null) {
            tableModels = new ArrayList<IWColor>();
            tableModels.add(IWModel.modelWithName("Blucinox", "blucinox_CC_27", "blucinox_29_27.jpg", "29,30,31,32,33,34,35,36,37,38,41", "27").withCategory("model"));
            tableModels.add(IWModel.modelWithName("Bluco", "bluco_CC_LL", "bluco_29_22.jpg", null, "22,23,24,25,26").withCategory("model"));
            tableModels.add(IWModel.modelWithName("Chateau", "chateau_CC_LL", "chateau_29_22.jpg", null, "22,23,24,25,26").withCategory("model"));
            tableModels.add(IWModel.modelWithName("Chrominox", "chrominox_CC_27", "chrominox_29_27.jpg", null, "27").withCategory("model"));
            tableModels.add(IWModel.modelWithName("Elyps", "elyps_CC_LL", "elyps_29_22.jpg",null, "22,23,24,25,26,27").withCategory("model"));
            tableModels.add(IWModel.modelWithName("Extens", "extens_CC_LL", "extens_29_22.jpg", "29,30,31,32,33,36,37,38,41", "22,23,24,25,26,27").withCategory("model"));
            tableModels.add(IWModel.modelWithName("Facet", "facet_CC_LL", "facet_29_22.jpg", "29,30,31,32,33,41,36,37,38", "22,23,24,25,26").withCategory("model"));
            tableModels.add(IWModel.modelWithName("Largo", "largo_CC_LL", "largo_29_22.jpg",null, "22,23,24,25,26,28").withCategory("model"));
            tableModels.add(IWModel.modelWithName("Largo XL", "largo_xl_CC_LL", "largo_xl_29_22.jpg", "29,30,31,32,33,36,37", "22,23,24,25,26").withCategory("model"));
            tableModels.add(IWModel.modelWithName("Luna", "luna_CC_LL", "luna_29_22.jpg",null, "22,23,24,25,26,27").withCategory("model"));
            tableModels.add(IWModel.modelWithName("Propeller", "propeller_CC_LL", "propeller_29_22.jpg",null, "22,23,24,25,26").withCategory("model"));
            tableModels.add(IWModel.modelWithName("Wings", "wings_CC_LL", "wings_29_22.jpg",null, "22,23,24,25,26").withCategory("model"));
        }

        return tableModels;
    }

    public static ArrayList<IWColor> getTableColors() {
        if (tableColors == null) {
            tableColors = new ArrayList<IWColor>();
            tableColors.add(IWColor.colorWithName("Ultra white", "29", "colors/ultra_white.jpg").withCategory("Xeramica"));
            tableColors.add(IWColor.colorWithName("Sahara", "30", "colors/sahara.jpg").withCategory("Xeramica"));
            tableColors.add(IWColor.colorWithName("Blue Stone", "31", "colors/blue_stone.jpg").withCategory("Xeramica"));
            tableColors.add(IWColor.colorWithName("Wood grey" , "32" , "colors/wood_grey.jpg").withCategory("Xeramica"));
            tableColors.add(IWColor.colorWithName("Uni grey" , "33" , "colors/uni_grey.jpg").withCategory("Xeramica"));
            tableColors.add(IWColor.colorWithName("Old Oak" , "34" , "colors/old_oak.jpg").withCategory("Xeramica"));
            tableColors.add(IWColor.colorWithName("Smoke Oak" , "35" , "colors/smoke_oak.jpg").withCategory("Xeramica"));
            tableColors.add(IWColor.colorWithName("Calacatta" , "41" , "colors/caltatta.jpg").withCategory("Xeramica"));
            tableColors.add(IWColor.colorWithName("White" , "36" , "colors/white.jpg").withCategory("Glass"));
            tableColors.add(IWColor.colorWithName("Taupe" , "37" , "colors/taupe.jpg").withCategory("Glass"));
            tableColors.add(IWColor.colorWithName("Carrara" , "38" , "colors/matrilux_carara.jpg").withCategory("Glass"));
        }

        return tableColors;
        
    }

    public static ArrayList<IWColor> getTableLegColors()
    {
        if (tableLegColors == null) {
            tableLegColors = new ArrayList<IWColor>();
            tableLegColors.add(IWColor.colorWithName("White", "22", "colors/white.jpg").withCategory("legs color"));
            tableLegColors.add(IWColor.colorWithName("Taupe", "23", "colors/taupe.jpg").withCategory("legs color"));
            tableLegColors.add(IWColor.colorWithName("Brown", "24", "colors/brown.jpg").withCategory("legs color"));
            tableLegColors.add(IWColor.colorWithName("Antraciet", "25", "colors/antraciet_25.jpg").withCategory("legs color"));
            tableLegColors.add(IWColor.colorWithName("Black", "26", "colors/black_90.jpg").withCategory("legs color"));
            tableLegColors.add(IWColor.colorWithName("Chrome", "27", "colors/chrome_inox.jpg").withCategory("legs color"));
            tableLegColors.add(IWColor.colorWithName("Wood", "28", "colors/wood.jpg").withCategory("legs color"));
        }

        return tableLegColors;
    }

    public static ArrayList<IWColor> getChairLegColors() {
        if (chairLegColors == null) {
            chairLegColors = new ArrayList<IWColor>();
            chairLegColors.add(IWColor.colorWithName("White", "22", "colors/white.jpg").withCategory("legs color"));
            chairLegColors.add(IWColor.colorWithName("Taupe", "23", "colors/taupe.jpg").withCategory("legs color"));
            chairLegColors.add(IWColor.colorWithName("Brown", "24", "colors/brown.jpg").withCategory("legs color"));
            chairLegColors.add(IWColor.colorWithName("Antraciet", "25", "colors/antraciet_25.jpg").withCategory("legs color"));
            chairLegColors.add(IWColor.colorWithName("Black", "26", "colors/black_90.jpg").withCategory("legs color"));
            chairLegColors.add(IWColor.colorWithName("Chrome", "27", "colors/chrome-Inox.jpg").withCategory("legs color"));
            chairLegColors.add(IWColor.colorWithName("Wood", "28", "colors/wood.jpg").withCategory("legs color"));
        }

        return chairLegColors;
    }

    public static ArrayList<IWColor> getChairModels() {
        if (chairModels == null) {
            chairModels = new ArrayList<IWColor>();
            chairModels.add(IWModel.modelWithName("Angelina S", "angelina_s_CC_27", "cadires_jpgs/angelina_s_01_27.jpg", "01,02,03,04,05,06,07,08,09,10,11,12,13,14,15,16,17,18", "27").withCategory("model"));
            chairModels.add(IWModel.modelWithName("Angelina A", "angelina_a_CC_27", "cadires_jpgs/angelina_a_01_27.jpg", "01,02,03,04,05,06,07,08,09,10,11,12,13,14,15,16,17,18", "27").withCategory("model"));
            chairModels.add(IWModel.modelWithName("Angelina W", "angelina_w_CC_27", "cadires_jpgs/angelina_w_01_27.jpg", "01,02,03,04,05,06,07,08,09,10,11,12,13,14,15,16,17,18", "27").withCategory("model"));
            chairModels.add(IWModel.modelWithName("Angelina AW", "angelina_aw_CC_27", "cadires_jpgs/angelina_aw_01_27.jpg", "01,02,03,04,05,06,07,08,09,10,11,12,13,14,15,16,17,18", "27").withCategory("model"));
            chairModels.add(IWModel.modelWithName("Ensor S", "ensor_s_CC_27", "cadires_jpgs/ensor_s_14_27.jpg", "14,16,17,18,42", "27").withCategory("model"));
            chairModels.add(IWModel.modelWithName("Ensor A", "ensor_a_CC_27", "cadires_jpgs/ensor_a_14_27.jpg", "14,16,17,18,42", "27").withCategory("model"));
            chairModels.add(IWModel.modelWithName("Largo U", "largo_u_CC_LL", "cadires_jpgs/largo_u_01_22.jpg", "01,02,03,04,05,06,07,08,09,10,11,12,13,14,15,16,17,18", "22,23,24,25,26").withCategory("model"));
            chairModels.add(IWModel.modelWithName("Moon U", "moon_u_01_LL", "cadires_jpgs/moon_u_01_22.jpg", "01", "22,27").withCategory("model"));
            chairModels.add(IWModel.modelWithName("Monet S", "monet_s_CC_27", "cadires_jpgs/monet_s_01_27.jpg", "01,02,03,04,05,06,07,08,09,10,11,12,13,14,15,16,17,18", "27").withCategory("model"));
            chairModels.add(IWModel.modelWithName("Monet A", "monet_a_CC_27", "cadires_jpgs/monet_a_01_27.jpg", "01,02,03,04,05,06,07,08,09,10,11,12,13,14,15,16,17,18", "27").withCategory("model"));
            chairModels.add(IWModel.modelWithName("Monet W", "monet_w_CC_27", "cadires_jpgs/monet_w_01_27.jpg", "01,02,03,04,05,06,07,08,09,10,11,12,13,14,15,16,17,18", "27").withCategory("model"));
            chairModels.add(IWModel.modelWithName("Monet AW", "monet_aw_CC_27", "cadires_jpgs/monet_aw_01_27.jpg", "01,02,03,04,05,06,07,08,09,10,11,12,13,14,15,16,17,18", "27").withCategory("model"));
            chairModels.add(IWModel.modelWithName("Rafael S", "rafael_s_CC_28", "cadires_jpgs/rafael_s_14_28.jpg", "14,15,18,42", "28").withCategory("model"));
            chairModels.add(IWModel.modelWithName("Rafael A", "rafael_a_CC_LL", "cadires_jpgs/rafael_a_14_22.jpg", "14,15,18,42", "27").withCategory("model"));
            chairModels.add(IWModel.modelWithName("Renoir S", "renoir_s_CC_27", "cadires_jpgs/renoir_s_01_27.jpg", "01,02,03,04,05,06,07,08,09,10,11,12,13,14,15,16,17,18", "27").withCategory("model"));
            chairModels.add(IWModel.modelWithName("Renoir A", "renoir_a_CC_27", "cadires_jpgs/renoir_a_01_27.jpg", "01,02,03,04,05,06,07,08,09,10,11,12,13,14,15,16,17,18", "27").withCategory("model"));
            chairModels.add(IWModel.modelWithName("Renoir W", "renoir_w_CC_27", "cadires_jpgs/renoir_w_01_27.jpg", "01,02,03,04,05,06,07,08,09,10,11,12,13,14,15,16,17,18", "27").withCategory("model"));
            chairModels.add(IWModel.modelWithName("Renoir AW", "renoir_aw_CC_27", "cadires_jpgs/renoir_aw_01_27.jpg", "01,02,03,04,05,06,07,08,09,10,11,12,13,14,15,16,17,18", "27").withCategory("model"));
        /*                chairModels.add(IWModel.modelWithName("Princess_U", "Princess_U_CC_27", "Princess_U_19_27.jpg", "19,20,21", "27").withCategory("model");*/
            chairModels.add(IWModel.modelWithName("Van Gogh S", "van_gogh_s_CC_28", "cadires_jpgs/van_gogh_s_01_28.jpg", "06,10", "28").withCategory("model"));
            chairModels.add(IWModel.modelWithName("Van Gogh A", "van_gogh_a_CC_28", "cadires_jpgs/van_gogh_a_01_28.jpg", "06,10", "28").withCategory("model"));
        }

        return chairModels;
    }

    public static ArrayList<IWColor> getChairColors() {
        if (chairColors == null) {
            chairColors = new ArrayList<IWColor>();
            chairColors.add(IWColor.colorWithName("White 10", "01", "colors/white_10.jpg").withCategory("Leather color"));
            chairColors.add(IWColor.colorWithName("Beige 110", "02", "colors/beige_110.jpg").withCategory("Leather color"));
            chairColors.add(IWColor.colorWithName("Lipstick 80", "03", "colors/lipstick_80.jpg").withCategory("Leather color"));
            chairColors.add(IWColor.colorWithName("Wine Red 18", "04", "colors/wine_red_18.jpg").withCategory("Leather color"));
            chairColors.add(IWColor.colorWithName("Black 08", "10", "colors/black_08.jpg").withCategory("Leather color"));
            chairColors.add(IWColor.colorWithName("Taupe 27", "05", "colors/taupe_27.jpg").withCategory("Leather color"));
            chairColors.add(IWColor.colorWithName("Nutella 58", "06", "colors/nutella.jpg").withCategory("Leather color"));
            chairColors.add(IWColor.colorWithName("Grey 21", "08", "colors/grey_21.jpg").withCategory("Leather color"));
            chairColors.add(IWColor.colorWithName("Antraciet 25", "09", "colors/antraciet_25.jpg").withCategory("Leather color"));
            chairColors.add(IWColor.colorWithName("Aubergine 77", "11", "colors/aubergine_77.jpg").withCategory("Leather color"));
            chairColors.add(IWColor.colorWithName("Choco 57", "12", "colors/choco_57.jpg").withCategory("Leather color"));
            chairColors.add(IWColor.colorWithName("Smoke 17", "13", "colors/smoke_17.jpg").withCategory("Leather color"));
            chairColors.add(IWColor.colorWithName("Mocca 29", "15", "colors/mocca_29.jpg").withCategory("Leather color"));
            chairColors.add(IWColor.colorWithName("White 01", "14", "colors/white_01.jpg").withCategory("Skai color"));
            chairColors.add(IWColor.colorWithName("L. grey 617", "16", "colors/light_grey_617.jpg").withCategory("Skai color"));
            chairColors.add(IWColor.colorWithName("Taupe 062", "15", "colors/taupe_062.jpg").withCategory("Skai color"));
            chairColors.add(IWColor.colorWithName("Antracite 605", "17", "colors/antracite_605.jpg").withCategory("Skai color"));
            chairColors.add(IWColor.colorWithName("Black 901", "18", "colors/black_90.jpg").withCategory("Skai color"));
            chairColors.add(IWColor.colorWithName("Brown", "42", "colors/brown.jpg").withCategory("Skai color"));
            chairColors.add(IWColor.colorWithName("White", "19", "colors/white.jpg").withCategory("Textylene"));
            chairColors.add(IWColor.colorWithName("Taupe", "20", "colors/taupe.jpg").withCategory("Textylene"));
            chairColors.add(IWColor.colorWithName("Grey", "21", "colors/grey_21.jpg").withCategory("Textylene"));
        }

        return chairColors;
    }
}
