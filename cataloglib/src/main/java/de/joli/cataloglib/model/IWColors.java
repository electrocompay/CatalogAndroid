package de.joli.cataloglib.model;

import java.util.ArrayList;

import de.joli.cataloglib.model.IWColor;
import de.joli.cataloglib.model.IWModel;

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
    private static ArrayList<IWColor> cabinetModels;
    private static ArrayList<IWColor> cabinet40Types;
    private static ArrayList<IWColor> cabinet40HSizes;
    private static ArrayList<IWColor> cabinet40BSizes;
    private static ArrayList<IWColor> cabinet40KSizes;
    private static ArrayList<IWColor> cabinet55Types;
    private static ArrayList<IWColor> cabinet55HSizes;
    private static ArrayList<IWColor> cabinet55BSizes;
    private static ArrayList<IWColor> cabinet55KSizes;
    private static ArrayList<IWColor> cabinetsColors;
    private static ArrayList<IWColor> cabinets83Modules;
    private static ArrayList<IWColor> cabinetC193Sizes;
    private static ArrayList<IWColor> cabinetStripeColors;
    private static ArrayList<IWColor> cabinetSideColors;
    private static ArrayList<IWColor> cabinetTopColors;
    private static ArrayList<IWColor> cabinetLegColors;
    private static ArrayList<IWColor> cabinetDrawerColors;

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


    public static ArrayList<IWColor> cabinetModels(){

        if (cabinetModels == null) {
            cabinetModels = new ArrayList<IWColor>();
            cabinetModels.add(IWModel.modelWithName("Cube 40", "40", "40-H01-29-22.png", null, null));
            cabinetModels.add(IWModel.modelWithName("Cube 55", "55" , "55-H01-29-22.png", null, null));
            cabinetModels.add(IWModel.modelWithName("Cosy 83", "C83", null, null, "27"));
            cabinetModels.add(IWModel.modelWithName("Cosy 193", "C193", null, null, null));
            cabinetModels.add(IWModel.modelWithName("Joli 83", "J83", null, null, null));
        }

        return cabinetModels;
    }

    public static ArrayList<IWColor> cabinet40Types(){

        if (cabinet40Types == null) {
            cabinet40Types = new ArrayList<IWColor>();
            cabinet40Types.add(IWColor.colorWithName("Cupboard 40", "H", null));
            cabinet40Types.add(IWColor.colorWithName("Wall Cabinet 40", "B", null));
            cabinet40Types.add(IWColor.colorWithName("Column Cabinet 40", "K", null));
        }

        return cabinet40Types;

    }


    public static ArrayList<IWColor> cabinet40HSizes()
    {
        if (cabinet40HSizes == null) {
            cabinet40HSizes = new ArrayList<IWColor>();
            cabinet40HSizes.add(IWColor.colorWithName("2 doors (80x40x40)", "01", null));
            cabinet40HSizes.add(IWColor.colorWithName("3 doors (120x40x40)", "02", null));
            cabinet40HSizes.add(IWColor.colorWithName("4 doors (160x40x40)", "03", null));
            cabinet40HSizes.add(IWColor.colorWithName("5 doors (200x40x40)", "04", null));
            cabinet40HSizes.add(IWColor.colorWithName("6 doors (240x40x40)", "05", null));
            cabinet40HSizes.add(IWColor.colorWithName("7 doors (280x40x40)", "06", null));
            cabinet40HSizes.add(IWColor.colorWithName("8 doors (320x40x40)", "07", null));
        }

        return cabinet40HSizes;

    }



    public static ArrayList<IWColor> cabinet40BSizes(){
        if (cabinet40BSizes == null) {
            cabinet40BSizes = new ArrayList<IWColor>();
            cabinet40BSizes.add(IWColor.colorWithName("4 doors (80x40x40)", "08", null));
            cabinet40BSizes.add(IWColor.colorWithName("6 doors (120x40x40)", "09", null));
            cabinet40BSizes.add(IWColor.colorWithName("6 doors (160x40x40)", "10", null));
            cabinet40BSizes.add(IWColor.colorWithName("9 doors (200x40x40)", "11", null));
        }

        return cabinet40BSizes;

    }

    public static ArrayList<IWColor> cabinet40KSizes(){
        if (cabinet40KSizes == null) {
            cabinet40KSizes = new ArrayList<IWColor>();
            cabinet40KSizes.add(IWColor.colorWithName("2 doors (80x40x40)", "12", null));
            cabinet40KSizes.add(IWColor.colorWithName("3 doors (120x40x40)", "13", null));
            cabinet40KSizes.add(IWColor.colorWithName("4 doors (160x40x40)", "14", null));
        }

        return cabinet40KSizes;

    }

    public static ArrayList<IWColor> cabinet55Types(){
        if (cabinet55Types == null) {
            cabinet55Types = new ArrayList<IWColor>();
            cabinet55Types.add(IWColor.colorWithName("Cupboard 55", "H", null));
            cabinet55Types.add(IWColor.colorWithName("Wall Cabinet 55", "B", null));
            cabinet55Types.add(IWColor.colorWithName("Column Cabinet 55", "K", null));
        }

        return cabinet55Types;

    }

    public static ArrayList<IWColor> cabinet55HSizes(){
        if (cabinet55HSizes == null) {
            cabinet55HSizes = new ArrayList<IWColor>();
            cabinet55HSizes.add(IWColor.colorWithName("2 doors (80x40x40)", "01", null));
            cabinet55HSizes.add(IWColor.colorWithName("3 doors (120x40x40)", "02", null));
            cabinet55HSizes.add(IWColor.colorWithName("4 doors (160x40x40)", "03", null));
            cabinet55HSizes.add(IWColor.colorWithName("5 doors (200x40x40)", "04", null));
            cabinet55HSizes.add(IWColor.colorWithName("6 doors (240x40x40)", "05", null));
        }

        return cabinet55HSizes;

    }

    public static ArrayList<IWColor> cabinet55BSizes(){
        if (cabinet55BSizes == null) {
            cabinet55BSizes = new ArrayList<IWColor>();
            cabinet55BSizes.add(IWColor.colorWithName("4 doors (80x40x40)", "06", null));
            cabinet55BSizes.add(IWColor.colorWithName("6 doors (120x40x40)", "07", null));
            cabinet55BSizes.add(IWColor.colorWithName("6 doors (160x40x40)", "08", null));
            cabinet55BSizes.add(IWColor.colorWithName("9 doors (200x40x40)", "09", null));
        }

        return cabinet55BSizes;

    }

    public static ArrayList<IWColor> cabinet55KSizes(){
        if (cabinet55KSizes == null) {
            cabinet55KSizes = new ArrayList<IWColor>();
            cabinet55KSizes.add(IWColor.colorWithName("2 doors (80x40x40)", "10", null));
            cabinet55KSizes.add(IWColor.colorWithName("3 doors (120x40x40)", "11", null));
            cabinet55KSizes.add(IWColor.colorWithName("4 doors (160x40x40)", "12", null));
        }

        return cabinet55KSizes;

    }

    public static ArrayList<IWColor> cabinetColors(){
        if (cabinetsColors == null) {
            cabinetsColors = new ArrayList<IWColor>();
            cabinetsColors.add(IWColor.colorWithName("Ultra white", "29", "colors/ultra_white.jpg").withCategory("Xeramica"));
            cabinetsColors.add(IWColor.colorWithName("Sahara", "30", "colors/sahara.jpg").withCategory("Xeramica"));
            cabinetsColors.add(IWColor.colorWithName("Blue Stone", "31", "colors/blue_stone.jpg").withCategory("Xeramica"));
            cabinetsColors.add(IWColor.colorWithName("Wood grey", "32", "colors/wood_grey.jpg").withCategory("Xeramica"));
            cabinetsColors.add(IWColor.colorWithName("Uni grey", "33", "colors/uni_grey.jpg").withCategory("Xeramica"));
            cabinetsColors.add(IWColor.colorWithName("White", "36", "colors/white.jpg").withCategory("Glass"));
            cabinetsColors.add(IWColor.colorWithName("Taupe", "37", "colors/taupe.jpg").withCategory("Glass"));
            cabinetsColors.add(IWColor.colorWithName("Grey", "39", "colors/grey.jpg").withCategory("Glass"));
            cabinetsColors.add(IWColor.colorWithName("Brown", "40", "colors/brown.jpg").withCategory("Glass"));
        }

        return cabinetsColors;

    }

    public static ArrayList<IWColor> cabinet83Modules(){
        if (cabinets83Modules == null) {
            cabinets83Modules = new ArrayList<IWColor>();
            cabinets83Modules.add(IWColor.colorWithName("---", null, null));
            cabinets83Modules.add(IWColor.colorWithName("1 door\n(55x83x50)", "1,0", null));
            cabinets83Modules.add(IWColor.colorWithName("2 door\n(55x83x50)", "2,0", null));
            cabinets83Modules.add(IWColor.colorWithName("2 door + 1 drawer\n(55x83x50)", "2,1", null));
            cabinets83Modules.add(IWColor.colorWithName("3 drawers\n(55x83x50)", "0,3", null));
        }
        return cabinets83Modules;
    }

    public static ArrayList<IWColor> cabinetC193Sizes(){
        if (cabinetC193Sizes == null) {
            cabinetC193Sizes = new ArrayList<IWColor>();
            cabinetC193Sizes.add(IWColor.colorWithName("1 door (55x193x40)", "1", null));
            cabinetC193Sizes.add(IWColor.colorWithName("2 door (55x193x40)", "2", null));
            cabinetC193Sizes.add(IWColor.colorWithName("3 door (55x193x40)", "3", null));
            cabinetC193Sizes.add(IWColor.colorWithName("4 door (55x193x40)", "4", null));
        }
        return cabinetC193Sizes;
    }

    public static ArrayList<IWColor> cabinetStripeColors(){
        if (cabinetStripeColors == null) {
            cabinetStripeColors = new ArrayList<IWColor>();
            cabinetStripeColors.add(IWColor.colorWithName("Ultra white", "29", "colors/ultra_white.jpg").withCategory("Xeramica"));
            cabinetStripeColors.add(IWColor.colorWithName("Sahara", "30", "colors/sahara.jpg").withCategory("Xeramica"));
            cabinetStripeColors.add(IWColor.colorWithName("Blue Stone", "31", "colors/blue_stone.jpg").withCategory("Xeramica"));
            cabinetStripeColors.add(IWColor.colorWithName("Wood grey", "32", "colors/wood_grey.jpg").withCategory("Xeramica"));
            cabinetStripeColors.add(IWColor.colorWithName("Uni grey", "33", "colors/uni_grey.jpg").withCategory("Xeramica"));
            cabinetStripeColors.add(IWColor.colorWithName("White", "36", "colors/white.jpg").withCategory("Glass"));
            cabinetStripeColors.add(IWColor.colorWithName("Taupe", "37", "colors/taupe.jpg").withCategory("Glass"));
            cabinetStripeColors.add(IWColor.colorWithName("Grey", "39", "colors/grey 21.jpg").withCategory("Glass"));
            cabinetStripeColors.add(IWColor.colorWithName("Brown", "40", "colors/brown.jpg").withCategory("Glass"));
            cabinetStripeColors.add(IWColor.colorWithName("No Stripe" ,null, "colors/no_stripe.jpg").withCategory("Glass"));
        }

        return cabinetStripeColors;

    }

    public static ArrayList<IWColor> cabinetSideColors(){
        if (cabinetSideColors == null) {
            cabinetSideColors = new ArrayList<IWColor>();
            cabinetSideColors.add(IWColor.colorWithName("Ultra white", "29", "colors/ultra_white.jpg").withCategory("Xeramica"));
            cabinetSideColors.add(IWColor.colorWithName("Sahara", "30", "colors/sahara.jpg").withCategory("Xeramica"));
            cabinetSideColors.add(IWColor.colorWithName("Blue Stone", "31", "colors/blue_stone.jpg").withCategory("Xeramica"));
            cabinetSideColors.add(IWColor.colorWithName("Wood grey", "32", "colors/wood_grey.jpg").withCategory("Xeramica"));
            cabinetSideColors.add(IWColor.colorWithName("Uni grey", "33", "colors/uni_grey.jpg").withCategory("Xeramica"));
            cabinetSideColors.add(IWColor.colorWithName("White", "36", "colors/white.jpg").withCategory("Glass"));
            cabinetSideColors.add(IWColor.colorWithName("Taupe", "37", "colors/taupe.jpg").withCategory("Glass"));
            cabinetSideColors.add(IWColor.colorWithName("Grey", "39", "colors/grey_21.jpg").withCategory("Glass"));
            cabinetSideColors.add(IWColor.colorWithName("Brown", "40", "colors/brown.jpg").withCategory("Glass"));
        }

        return cabinetSideColors;
    }

    public static ArrayList<IWColor> cabinetTopColors(){
        if (cabinetTopColors == null) {
            cabinetTopColors = new ArrayList<IWColor>();
            cabinetTopColors.add(IWColor.colorWithName("Ultra white", "29", "colors/ultra_white.jpg").withCategory("Xeramica"));
            cabinetTopColors.add(IWColor.colorWithName("Sahara", "30", "colors/sahara.jpg").withCategory("Xeramica"));
            cabinetTopColors.add(IWColor.colorWithName("Blue Stone", "31", "colors/blue_stone.jpg").withCategory("Xeramica"));
            cabinetTopColors.add(IWColor.colorWithName("Wood grey", "32", "colors/wood_grey.jpg").withCategory("Xeramica"));
            cabinetTopColors.add(IWColor.colorWithName("Uni grey", "33", "colors/uni_grey.jpg").withCategory("Xeramica"));
            cabinetTopColors.add(IWColor.colorWithName("Old Oak", "34", "colors/old_oak.jpg").withCategory("Xeramica"));
            cabinetTopColors.add(IWColor.colorWithName("Smoke Oak", "35", "colors/smoke_oak.jpg").withCategory("Xeramica"));
            cabinetTopColors.add(IWColor.colorWithName("Calacatta", "41", "colors/caltatta.jpg").withCategory("Xeramica"));
            cabinetTopColors.add(IWColor.colorWithName("White", "36", "colors/white.jpg").withCategory("Glass"));
            cabinetTopColors.add(IWColor.colorWithName("Taupe", "37", "colors/taupe.jpg").withCategory("Glass"));
            cabinetTopColors.add(IWColor.colorWithName("Carrara", "38", "colors/matrilux_carara.jpg").withCategory("Glass"));
            cabinetTopColors.add(IWColor.colorWithName("Grey", "39", "colors/grey_21.jpg").withCategory("Glass"));
            cabinetTopColors.add(IWColor.colorWithName("Brown", "40", "colors/brown.jpg").withCategory("Glass"));
        }

        return cabinetTopColors;
    }

    public static ArrayList<IWColor> cabinetLegColors(){
        if (cabinetLegColors == null) {
            cabinetLegColors = new ArrayList<IWColor>();
            cabinetLegColors.add(IWColor.colorWithName("White", "22", "colors/white.jpg").withCategory("legs color"));
            cabinetLegColors.add(IWColor.colorWithName("Taupe", "23", "colors/taupe.jpg").withCategory("legs color"));
            cabinetLegColors.add(IWColor.colorWithName("Brown", "24", "colors/brown.jpg").withCategory("legs color"));
            cabinetLegColors.add(IWColor.colorWithName("Antraciet", "25", "colors/antraciet_25.jpg").withCategory("legs color"));
            cabinetLegColors.add(IWColor.colorWithName("Black", "26", "colors/black_90.jpg").withCategory("legs color"));
            cabinetLegColors.add(IWColor.colorWithName("Chrome", "27", "colors/chrome_inox.jpg").withCategory("legs color"));
            cabinetLegColors.add(IWColor.colorWithName("Wood", "28", "colors/wood.jpg").withCategory("legs color"));
        }

        return cabinetLegColors;
    }

    public static ArrayList<IWColor> cabinetDrawerColors(){
        if (cabinetDrawerColors == null) {
            cabinetDrawerColors = new ArrayList<IWColor>();
            cabinetDrawerColors.add(IWColor.colorWithName("Ultra white", "29", "colors/ultra_white.jpg").withCategory("Xeramica"));
            cabinetDrawerColors.add(IWColor.colorWithName("Sahara", "30", "colors/sahara.jpg").withCategory("Xeramica"));
            cabinetDrawerColors.add(IWColor.colorWithName("Blue Stone", "31", "colors/blue_stone.jpg").withCategory("Xeramica"));
            cabinetDrawerColors.add(IWColor.colorWithName("Wood grey", "32", "colors/wood_grey.jpg").withCategory("Xeramica"));
            cabinetDrawerColors.add(IWColor.colorWithName("Uni grey", "33", "colors/uni_grey.jpg").withCategory("Xeramica"));
            cabinetDrawerColors.add(IWColor.colorWithName("Old Oak", "34", "colors/old_oak.jpg").withCategory("Melamine"));
            cabinetDrawerColors.add(IWColor.colorWithName("Smoke Oak", "35", "colors/smoke_oak.jpg").withCategory("Melamine"));
            cabinetDrawerColors.add(IWColor.colorWithName("White", "36", "colors/white.jpg").withCategory("Glass"));
            cabinetDrawerColors.add(IWColor.colorWithName("Taupe", "37", "colors/taupe.jpg").withCategory("Glass"));
            cabinetDrawerColors.add(IWColor.colorWithName("Grey", "39", "colors/grey_21.jpg").withCategory("Glass"));
            cabinetDrawerColors.add(IWColor.colorWithName("Brown", "40", "colors/brown.jpg").withCategory("Glass"));
        }

        return cabinetDrawerColors;
    }


}
