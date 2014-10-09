package de.joli.catalogcabinets.drawers;

import android.content.Context;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import de.joli.catalogcabinets.model.IWCabinet;
import de.joli.cataloglib.drawers.IWDrawer;
import de.joli.cataloglib.model.IWColor;
import de.joli.cataloglib.model.IWFurniture;

/**
 * Created by abel.miranda on 10/2/14.
 */
public class IWDrawerCabinet extends IWDrawer {

    private int activeModule;
    private boolean doAnimate;


    public IWDrawerCabinet(Context context) {
        super(context);
        activeModule = -1;
        doAnimate = false;
    }

    public void activateModule(int module){
        activeModule = module;
        doAnimate = module > -1;
    }


    public void drawCabinet(IWCabinet cabinet, int position, boolean sufix){
        if (cabinet == null) {
            return;
        }

        doAnimate =  position == activeModule;

        String modelCode = cabinet.getModel().getCode();
        if (modelCode.equals("J83")) {
            modelCode = "C83";
        }
        String strSufix = "";
        if (sufix)
        {
            strSufix = "a";
        }
        String mask = "%s-%s%s-%s-%s%s-%d%s";
        String modelMask = "";
        if (cabinet.getSize().getCode().equals("1,0")) {
            modelMask = "1D";
        } else if (cabinet.getSize().getCode().equals("2,0")){
            modelMask = "2D";
        } else if (cabinet.getSize().getCode().equals("2,1")){
            modelMask = "LDD";
        } else if (cabinet.getSize().getCode().equals("0,3")){
            modelMask = "3L";
        }
        if ((position == 4 && !sufix) || (position == 3 && modelMask.equals("1D"))) {
            modelMask = modelMask.concat("a");
        }
        String filename;

        filename = String.format(mask, modelCode, modelMask, strSufix, "29", "F", "", position, strSufix);
        filename = filename.replace("F-", "");
        addLayer(filename);

        if (cabinet.getSize().getCode().equals("1,0") || cabinet.getSize().getCode().equals("2,0")) {
            for (int i = 0; i < cabinet.getColors().size(); i++) {
                IWColor color = cabinet.getColors().get(i);
                String index = cabinet.getColors().size() > 1 ? String.format("%d", i + 1) : "";
                if (cabinet.getSize().getCode().equals("2,1")) {
                    filename = String.format(mask, modelCode, modelMask, strSufix, color.getCode(), "F", index, position, strSufix);
                    addLayer(filename);
                }
            }
        }


        if (cabinet.getSize().getCode().equals("2,1")) {
            filename = String.format(mask, modelCode, modelMask, strSufix, cabinet.getDrawers().get(0).getCode(), "F", "1", position, strSufix);
            addLayer(filename);
            filename = String.format(mask, modelCode, modelMask, strSufix, cabinet.getColors().get(0).getCode(), "F", "2", position, strSufix);
            addLayer(filename);
            filename = String.format(mask, modelCode, modelMask, strSufix, cabinet.getColors().get(1).getCode(),  "F", "3", position, strSufix);
            addLayer(filename);
        }

        if (cabinet.getSize().getCode().equals("0,3")) {
        for (int i = 0; i < cabinet.getDrawers().size(); i++) {
            IWColor color = cabinet.getDrawers().get(i);
            String index = String.format("%d", i + 1);
            filename = String.format(mask,  modelCode,  modelMask, strSufix, color.getCode(), "F", index , position, strSufix);
            addLayer(filename);
        }
    }


        filename = String.format(mask, modelCode, modelMask, strSufix, cabinet.getTop().getCode(), "T", "", position, strSufix);
        addLayer(filename);


        filename = String.format(mask, modelCode, modelMask, strSufix, cabinet.getSide().getCode(), "S", "", position, strSufix);
        addLayer(filename);

        if (cabinet.isUseStripe()) {
            filename = String.format(mask, modelCode, modelMask, strSufix, cabinet.getStripe().getCode(), "F", "", position, strSufix);
            filename = filename.replace("C83", "J83");
            addLayer(filename);
            filename = String.format("%s-%dD%-%s-F-%d%", cabinet.getModel().getCode(), cabinet.getColors().size(), strSufix, cabinet.getStripe().getCode(), position, strSufix);
            addLayer(filename);
            filename = String.format("%s-%dL%-%s-F-%d%", cabinet.getModel().getCode(), cabinet.getDrawers().size(), strSufix, cabinet.getStripe().getCode(), position, strSufix);
            addLayer(filename);
        }

        if (doAnimate) {
            filename = String.format(mask, modelCode, modelMask, strSufix, cabinet.getTop().getCode(), "TR", "", position, strSufix);
            final View trView = addLayer(filename);
            AlphaAnimation animation = new AlphaAnimation(233, 0);
            animation.setDuration(300);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    AlphaAnimation newAnimation = new AlphaAnimation(233, 0);
                    newAnimation.setDuration(800);
                    trView.startAnimation(newAnimation);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            trView.startAnimation(animation);

        }

    }

    public void drawForniture(IWFurniture forniture){
        super.drawFurniture(forniture);
        try {
        IWCabinet cabinet = (IWCabinet) forniture;
        String filename;

        if (cabinet.useModules()) {
            boolean sufix = false;
            sufix = cabinet.getModule2().getColors().size() == 1;
            drawCabinet(cabinet.getModule4(), 4, sufix);
            drawCabinet(cabinet.getModule3(), 3, sufix);
            drawCabinet(cabinet.getModule2(), 2, false);
            drawCabinet(cabinet, 1, false);
            activeModule = -1;
        } else {

            if (cabinet.isShowLegs()) {
                filename = String.format("%s-%s%s-00-%s", cabinet.getModel().getCode(), cabinet.getType().getCode(), cabinet.getSize().getCode(), cabinet.getLegsColor().getCode());
                addLayer(filename);
            }

            for (int i = 0; i<cabinet.getColors().size(); i++) {
                IWColor color = cabinet.getColors().get(i);
                filename = String.format("%s-%s%s-%s-00-F%d", cabinet.getModel().getCode(), cabinet.getType().getCode(), cabinet.getSize().getCode(), color.getCode(), i + 1);
                addLayer(filename);
            }

            filename = String.format("%s-%s%s-%s-00-T", cabinet.getModel().getCode(), cabinet.getType().getCode(), cabinet.getSize().getCode(), cabinet.getTop().getCode());
            addLayer(filename);

            filename = String.format("%s-%s%s-%s-00-S", cabinet.getModel().getCode(), cabinet.getType().getCode(), cabinet.getSize().getCode(), cabinet.getSide().getCode());
            addLayer(filename);



        /* WHITHOUT LEGS*/

            filename = String.format("%s-%s%s-%s", cabinet.getModel().getCode(), cabinet.getType().getCode(), cabinet.getSize().getCode(), cabinet.getLegsColor().getCode());
            addLayer(filename);

            if (cabinet.getModel().getCode().equals("C193")) {
                filename = String.format("%s-%dD-29-T%s", cabinet.getModel().getCode(), cabinet.getColors().size(), "34");
                addLayer(filename);
            }

            for (int i = 0; i<cabinet.getColors().size(); i++) {
                IWColor color = cabinet.getColors().get(i);
            /*if (i == 0) {
                filename = String.format("%s-%s%s-%s-F", cabinet.getModel().getCode(), cabinet.getType().getCode(), cabinet.getSize().getCode(), color.getCode());
            } else {*/
                filename = String.format("%s-%s%s-%s-F%d", cabinet.getModel().getCode(), cabinet.getType().getCode(), cabinet.getSize().getCode(), color.getCode(), i +1);
                // }

                if (cabinet.getModel().getCode().equals("C193")) {
                    filename = String.format("%s-%dD-%s-T", cabinet.getModel().getCode(), cabinet.getColors().size(), cabinet.getTop().getCode());
                    addLayer(filename);
                    if (cabinet.getColors().size() == 1) {
                        filename = String.format("%s-%dD-%s-F", cabinet.getModel().getCode(), cabinet.getColors().size(), color.getCode());
                    } else {
                        filename = String.format("%s-%dD-%s-F%d", cabinet.getModel().getCode(), cabinet.getColors().size(), color.getCode(), i + 1);
                    }
                    addLayer(filename);
                    filename = String.format("%s-%dD-%s-S", cabinet.getModel().getCode(), cabinet.getColors().size(), cabinet.getSide().getCode());
                    addLayer(filename);
                    filename = String.format("%s-%dD-29-T%s-V", cabinet.getModel().getCode(), cabinet.getColors().size(), cabinet.getInteriorColor().getCode());
                    addLayer(filename);
                }
                addLayer(filename);

            }

            filename = String.format("%s-%s%s-%s-T", cabinet.getModel().getCode(), cabinet.getType().getCode(), cabinet.getSize().getCode(), cabinet.getTop().getCode());
            addLayer(filename);

            filename = String.format("%s-%s%s-%s-S", cabinet.getModel().getCode(), cabinet.getType().getCode(), cabinet.getSize().getCode(), cabinet.getSide().getCode());
            addLayer(filename);
        } }
        catch (Exception e){
                e.printStackTrace();
            }

    }

    @Override
    public ImageView addLayer(String imageName){
        ImageView layer = super.addLayer(imageName + ".png");
        if (layer != null) {
            layer.setScaleType(ImageView.ScaleType.FIT_CENTER);
        }
        return layer;
    }


}
