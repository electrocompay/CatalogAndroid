package de.joli.catalogcabinets.model;

import java.util.ArrayList;

import de.joli.catalogcabinets.util.ColorUtil;
import de.joli.cataloglib.model.IWColor;
import de.joli.cataloglib.model.IWColors;
import de.joli.cataloglib.model.IWFurniture;
import de.joli.cataloglib.model.IWModel;

/**
 * Created by abelmiranda on 6/30/14.
 */
public class IWCabinet extends IWFurniture {

    private boolean subModule;
    private IWModel model;
    private IWColor color;
    private ArrayList<IWColor> colors;
    private IWColor legsColor;
    private IWColor type;
    private IWColor size;
    private IWColor side;
    private IWColor top;
    private IWCabinet module2;
    private IWCabinet module3;
    private IWCabinet module4;
    private boolean useDoors;
    private boolean hasLegs;
    private boolean useModules;
    private ArrayList<IWColor> drawers;
    private IWColor stripe;
    private boolean oneColorMode;
    private IWCabinet parentCabinet;
    private IWColor interiorColor;
    private boolean useStripe;
    private boolean showLegs;

    public IWCabinet() {
        model = (IWModel) IWColors.cabinetModels().get(0);
        color = IWColors.cabinetColors().get(0);
        legsColor = IWColor.colorWithName(null, "27", null);
        top = IWColors.cabinetColors().get(0);
        side = IWColors.cabinetColors().get(0);
        legsColor = IWColor.colorWithName(null, "22", null);
        colors = new ArrayList<IWColor>();
        drawers = new ArrayList<IWColor>();
        subModule = false;
        oneColorMode = true;
        showLegs = true;
    }

    public IWCabinet(IWCabinet cabinet){
        this();
        subModule = true;
        model = cabinet.model;
        parentCabinet = cabinet;
    }

    public IWModel getModel() {
        return model;
    }

    public void setModel(IWModel model) {
        if (this.model != model) {
            this.model = model;
            if (useModules && !subModule) {
                module2 = new IWCabinet(this);
                module3 = new IWCabinet(this);
                module4 = new IWCabinet(this);
            } else {
                module2 = null;
                module3 = null;
                module4 = null;
            }
        }
    }

    public IWColor getColor() {
        return color;
    }

    public void setColor(IWColor color) {
        this.color = color;
    }

    public ArrayList<IWColor> getColors() {
        return colors;
    }

    public void setColors(ArrayList<IWColor> colors) {
        this.colors = colors;
    }

    public IWColor getLegsColor() {
        return legsColor;
    }

    public void setLegsColor(IWColor legsColor) {
        this.legsColor = legsColor;
    }

    public IWColor getType() {
        return type;
    }

    public void setType(IWColor type) {
        this.type = type;
    }

    public IWColor getSize() {
        return size;
    }

    public void setSize(IWColor size) {
        if (this.size != size) {
            this.size = size;
            if (this.useModules) {
                String[] params = size.getCode().split(",");
                int ndoors = Integer.parseInt(params[0]);
                colors.clear();
                for (int i = 0; i < ndoors; i++) {
                    colors.add(color);
                }
                int ndrawers = Integer.parseInt(params[1]);
                IWColor drawerColor = model.getCode().equals("J83") ? ColorUtil.colorByCode("29", IWColors.cabinetDrawerColors()) : ColorUtil.colorByCode("35", IWColors.cabinetDrawerColors());
                drawers.clear();
                for (int i = 0; i < ndrawers; i++) {
                    drawers.add(drawerColor);
                }
            } else {
                colors.clear();
                int nsize = Integer.parseInt(size.getName().substring(0, 1));
                for (int i = 0; i < nsize; i++) {
                    colors.add(color);
                }
            }
        }
    }

    public IWColor getSide() {
        return side;
    }

    public void setSide(IWColor side) {
        this.side = side;
    }

    public IWColor getTop() {
        return top;
    }

    public void setTop(IWColor top) {
        this.top = top;
    }

    public IWCabinet getModule2() {
        return module2;
    }

    public void setModule2(IWCabinet module2) {
        this.module2 = module2;
    }

    public IWCabinet getModule3() {
        return module3;
    }

    public void setModule3(IWCabinet module3) {
        this.module3 = module3;
    }

    public IWCabinet getModule4() {
        return module4;
    }

    public void setModule4(IWCabinet module4) {
        this.module4 = module4;
    }

    public boolean useDoors() {
        return model.getCode().equals("40") || model.getCode().equals("55") || model.getCode().equals("55") || model.getCode().equals("C193");
    }

    public boolean hasLegs() {
        return (model.getCode().equals("40") || model.getCode().equals("55")) && type.getCode().equals("H");
    }

    public boolean useModules() {
        return model.getCode().equals("C83") || model.getCode().equals("J83");
    }

    public void setUseModules(boolean useModules) {
        this.useModules = useModules;
    }

    public ArrayList<IWColor> getDrawers() {
        return drawers;
    }

    public void setDrawers(ArrayList<IWColor> drawers) {
        this.drawers = drawers;
    }

    public IWColor getStripe() {
        return stripe;
    }

    public void setStripe(IWColor stripe) {
        this.stripe = stripe;
    }

    public boolean isOneColorMode() {
        return oneColorMode;
    }

    public void setOneColorMode(boolean oneColorMode) {
        this.oneColorMode = oneColorMode;
    }

    public IWCabinet getParentCabinet() {
        return parentCabinet;
    }

    public void setParentCabinet(IWCabinet parentCabinet) {
        this.parentCabinet = parentCabinet;
    }

    public IWColor getInteriorColor() {
        return interiorColor;
    }

    public void setInteriorColor(IWColor interiorColor) {
        this.interiorColor = interiorColor;
    }

    public boolean isUseStripe() {
        return useStripe;
    }

    public void setUseStripe(boolean useStripe) {
        this.useStripe = useStripe;
    }

    public boolean isShowLegs() {
        return showLegs;
    }

    public void setShowLegs(boolean showLegs) {
        this.showLegs = showLegs;
    }
}
