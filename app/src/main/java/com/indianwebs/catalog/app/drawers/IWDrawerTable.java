package com.indianwebs.catalog.app.drawers;

import android.content.Context;

import com.indianwebs.catalog.app.model.IWFurniture;
import com.indianwebs.catalog.app.model.IWTable;

/**
 * Created by abelmiranda on 7/1/14.
 */
public class IWDrawerTable extends IWDrawer {


    public IWDrawerTable(Context context) {
        super(context);
    }

    @Override
    public void drawFurniture(IWFurniture forniture) {
        super.drawFurniture(forniture);
        IWTable table = (IWTable) forniture;
        String replaceString = "";
        if (this.isFrontView()) {
            replaceString = "_b.png";
        } else {
            replaceString = "_a.png";
        }
        if (!(table.getModel() == null || table.getColor() == null)) {
            String filename = table.getModel().getFile().replace(".jpg", replaceString);
            addLayer(filename);
            String tableCode = table.getModel().getCode() + replaceString;
            filename = tableCode.replace("CC", table.getColor().getCode());
            filename = filename.replace("LL", "00");
            addLayer(filename);
            filename = tableCode.replace("LL", table.getLegsColor().getCode());
            filename = filename.replace("CC", "00");
            addLayer(filename);
        }
    }
}
