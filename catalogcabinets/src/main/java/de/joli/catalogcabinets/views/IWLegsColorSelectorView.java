package de.joli.catalogcabinets.views;

import android.content.Context;
import android.util.AttributeSet;

import de.joli.catalogcabinets.model.IWCabinet;
import de.joli.cataloglib.views.IWSelectorView;

/**
 * Created by abel.miranda on 10/7/14.
 */
public class IWLegsColorSelectorView extends IWSelectorView{

    private IWCabinet cabinet;

    public IWLegsColorSelectorView(Context context) {
        super(context);
    }

    public IWLegsColorSelectorView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public IWLegsColorSelectorView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setCabinet(IWCabinet cabinet) {
        this.cabinet = cabinet;
    }
}
