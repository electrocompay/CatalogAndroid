package com.indianwebs.catalog.app.drawers;

import android.content.Context;

import com.indianwebs.catalog.app.model.IWChair;

import de.joli.cataloglib.drawers.IWDrawer;
import de.joli.cataloglib.model.IWFurniture;

/**
 * Created by abelmiranda on 7/24/14.
 */
public class IWDrawerChair extends IWDrawer {

    public IWDrawerChair(Context context) {
        super(context);
    }

    @Override
    public void drawFurniture(IWFurniture furniture) {
        super.drawFurniture(furniture);
        IWChair chair = (IWChair) furniture;
        String replaceString = "";
        if (this.isFrontView()) {
            replaceString = "_b.png";
        } else {
            replaceString = "_a.png";
        }
        if (chair.getModel() != null) {
            String filename = chair.getModel().getFile().replace(".jpg", replaceString);
            addLayer(filename);
            String chairCode = chair.getModel().getCode() + replaceString;
            filename = chairCode.replace("CC", chair.getColor().getCode());
            filename = filename.replace("LL", "00");
            addLayer(filename);
            filename = chairCode.replace("LL", chair.getLegsColor().getCode());
            filename = filename.replace("CC", "00");
            addLayer(filename);
        }
        commitDrawing();
    }
}
