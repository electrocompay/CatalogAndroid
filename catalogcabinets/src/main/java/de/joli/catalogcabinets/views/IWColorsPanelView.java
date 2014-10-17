package de.joli.catalogcabinets.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import de.joli.catalogcabinets.R;
import de.joli.catalogcabinets.model.IWCabinet;
import de.joli.cataloglib.model.IWColor;

/**
 * Created by abel.miranda on 10/9/14.
 */
public class IWColorsPanelView extends FrameLayout implements IWColorSelectorView.IWColorSelectorViewDelegate {

    public interface IWColorsPanelViewDelegate {

        public void didChange(IWColorsPanelView colorsPanelView);

        public void didSelectButton(IWColorsPanelView colorsPanelView, int tag);

    }

    private ViewGroup containter;
    private IWColorSelectorView picDrawer1;
    private IWColorSelectorView picDrawer2;
    private IWColorSelectorView picDrawer3;
    private IWColorSelectorView door1;
    private IWColorSelectorView door2;
    private IWColorSelectorView door3;
    private IWColorSelectorView door4;
    private IWColorSelectorView door5;
    private IWColorSelectorView door6;
    private IWColorSelectorView door7;
    private IWColorSelectorView door8;
    private IWColorSelectorView door9;
    private IWColorSelectorView stripe;


    private IWCabinet cabinet;
    private IWColorsPanelViewDelegate delegate;
    private boolean oneSelectionMode;
    private IWColorSelectorView selectedView;
    private String stripeText;

    public IWColorsPanelView(Context context) {
        super(context);
    }

    public static IWColorsPanelView colorsPanelNineDoors(Context context) {
        IWColorsPanelView panel = new IWColorsPanelView(context);
        panel.containter = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.colors_panel_nine_doors, panel);
        panel.init();
        return panel;
    }

    private void init() {
        picDrawer1 = (IWColorSelectorView) findViewById(R.id.picDrawer1);
        picDrawer2 = (IWColorSelectorView) findViewById(R.id.picDrawer2);
        picDrawer3 = (IWColorSelectorView) findViewById(R.id.picDrawer3);
        door1 = (IWColorSelectorView) findViewById(R.id.door1);
        door2 = (IWColorSelectorView) findViewById(R.id.door2);
        door3 = (IWColorSelectorView) findViewById(R.id.door3);
        door4 = (IWColorSelectorView) findViewById(R.id.door4);
        door5 = (IWColorSelectorView) findViewById(R.id.door5);
        door6 = (IWColorSelectorView) findViewById(R.id.door6);
        door7 = (IWColorSelectorView) findViewById(R.id.door7);
        door8 = (IWColorSelectorView) findViewById(R.id.door8);
        door9 = (IWColorSelectorView) findViewById(R.id.door9);
        stripe = (IWColorSelectorView) findViewById(R.id.stripe);

    }

    public static IWColorsPanelView colorsPanelFourDoors(Context context) {
        IWColorsPanelView panel = new IWColorsPanelView(context);
        LayoutInflater.from(context).inflate(R.layout.colors_panel_four_doors, panel);
        panel.init();
        return panel;
    }

    public static IWColorsPanelView colorsPanelModuleDoors(Context context) {
        IWColorsPanelView panel = new IWColorsPanelView(context);
        LayoutInflater.from(context).inflate(R.layout.colors_panel_module, panel);
        panel.init();
        return panel;
    }

    public void setDelegate(IWMultipleSelectorView delegate) {
        this.delegate = delegate;
    }

    public void setCabinet(IWCabinet cabinet) {
        this.cabinet = cabinet;
        if (cabinet.getStripe() == null) {
            this.setStripeText("Optional");
        }
        updateLayout();
    }


    private void updateLayout() {
        if (cabinet.getColors().size() > 0) {
            door1.setEnabled(true);
            door1.setColor(cabinet.getColors().get(0));
        } else if (door1 != null) {
            door1.setEnabled(false);
        }

        if (cabinet.getColors().size() > 1 && !cabinet.isOneColorMode()) {
            door2.setEnabled(true);
            door2.setColor(cabinet.getColors().get(1));
        } else if (door2 != null) {
            door2.setEnabled(false);
        }
        if (cabinet.getColors().size() > 2 && !cabinet.isOneColorMode()) {
            door3.setEnabled(true);
            door3.setColor(cabinet.getColors().get(2));
        } else if (door3 != null) {
            door3.setEnabled(false);
        }
        if (cabinet.getColors().size() > 3 && !cabinet.isOneColorMode()) {
            door4.setEnabled(true);
            door4.setColor(cabinet.getColors().get(3));
        } else if (door4 != null) {
            door4.setEnabled(false);
        }
        if (cabinet.getColors().size() > 4 && !cabinet.isOneColorMode()) {
            door5.setEnabled(true);
            door5.setColor(cabinet.getColors().get(4));
        } else if (door5 != null) {
            door5.setEnabled(false);
        }
        if (cabinet.getColors().size() > 5 && !cabinet.isOneColorMode()) {
            door6.setEnabled(true);
            door6.setColor(cabinet.getColors().get(5));
        } else if (door6 != null) {
            door6.setEnabled(false);
        }
        if (cabinet.getColors().size() > 6 && !cabinet.isOneColorMode()) {
            door7.setEnabled(true);
            door7.setColor(cabinet.getColors().get(6));
        } else if (door7 != null) {
            door7.setEnabled(false);
        }
        if (cabinet.getColors().size() > 7 && !cabinet.isOneColorMode()) {
            door8.setEnabled(true);
            door8.setColor(cabinet.getColors().get(7));
        } else if (door8 != null) {
            door8.setEnabled(false);
        }
        if (cabinet.getColors().size() > 8 && !cabinet.isOneColorMode()) {
            door9.setEnabled(true);
            door9.setColor(cabinet.getColors().get(8));
        } else if (door9 != null) {
            door9.setEnabled(false);
        }

        if (cabinet.useModules()) {
            if (cabinet.getDrawers().size() > 0) {
                picDrawer1.setEnabled(true);
                picDrawer1.setColor(cabinet.getDrawers().get(0));
            } else if (picDrawer1 != null) {
                picDrawer1.setEnabled(false);
            }
            if (cabinet.getDrawers().size() > 1 && !cabinet.isOneColorMode()) {
                picDrawer2.setEnabled(true);
                picDrawer2.setColor(cabinet.getDrawers().get(1));
            } else if (picDrawer2 != null) {
                picDrawer2.setEnabled(false);
            }
            if (cabinet.getDrawers().size() > 2 && !cabinet.isOneColorMode()) {
                picDrawer3.setEnabled(true);
                picDrawer3.setColor(cabinet.getDrawers().get(2));
            } else if (picDrawer3 != null) {
                picDrawer3.setEnabled(false);
            }
            if (cabinet.getColors().size() > 0) {
                door1.setEnabled(true);
                door1.setColor(cabinet.getColors().get(0));
            } else if (door1 != null) {
                door1.setEnabled(false);
            }
            if (cabinet.getColors().size() > 1 && !cabinet.isOneColorMode()) {
                door2.setEnabled(true);
                door2.setColor(cabinet.getColors().get(1));
            } else if (door2 != null) {
                door2.setEnabled(false);
            }

            if (stripe != null) {
                stripe.setEnabled(cabinet.isUseStripe());
            }
        }
    }

    public IWCabinet getCabinet() {
        return cabinet;
    }

    public void setOneSelectionMode(boolean checked) {
        this.oneSelectionMode = checked;
        cabinet.setOneColorMode(oneSelectionMode);
        updateLayout();
    }

    public boolean isOneSelectionMode() {
        return oneSelectionMode;
    }

    public String getStripeText() {
        return stripeText;
    }

    public void setStripe(IWColorSelectorView stripe) {
        this.stripe = stripe;
    }

    public IWColorSelectorView getSelectedView() {
        return selectedView;
    }

    public IWColorsPanelViewDelegate getDelegate() {
        return delegate;
    }

    public void setDelegate(IWColorsPanelViewDelegate delegate) {
        this.delegate = delegate;
    }

    public void setColorToSelection(IWColor color) {

        if (!cabinet.useModules()) {

            if (cabinet.getColors().size() > 0 && (door1.isSelected() || cabinet.isOneColorMode())) {
                cabinet.getColors().set(0, color);
                door1.setColor(color);
            }
            if (cabinet.getColors().size() > 1 && (door2.isSelected() || cabinet.isOneColorMode())) {
                cabinet.getColors().set(1, color);
                door2.setColor(color);
            }

            if (cabinet.getColors().size() > 2 && (door3.isSelected() || cabinet.isOneColorMode())) {
                cabinet.getColors().set(2, color);
                door3.setColor(color);
            }
            if (cabinet.getColors().size() > 3 && (door4.isSelected() || cabinet.isOneColorMode())) {
                cabinet.getColors().set(3, color);
                door4.setColor(color);
            }
            if (cabinet.getColors().size() > 4 && (door5.isSelected() || cabinet.isOneColorMode())) {
                cabinet.getColors().set(4, color);
                door5.setColor(color);
            }
            if (cabinet.getColors().size() > 5 && (door6.isSelected() || cabinet.isOneColorMode())) {
                cabinet.getColors().set(5, color);
                door6.setColor(color);
            }
            if (cabinet.getColors().size() > 6 && (door7.isSelected() || cabinet.isOneColorMode())) {
                cabinet.getColors().set(6, color);
                door7.setColor(color);
            }
            if (cabinet.getColors().size() > 7 && (door8.isSelected() || cabinet.isOneColorMode())) {
                cabinet.getColors().set(7, color);
                door8.setColor(color);
            }
            if (cabinet.getColors().size() > 8 && (door9.isSelected() || cabinet.isOneColorMode())) {
                cabinet.getColors().set(8, color);
                door9.setColor(color);
            }
        } else {
            int selectedTag = findSelectedTag(door1.getRootView());

            if (selectedTag < 2) {
                if (cabinet.getColors().size() > 0 && (door1.isSelected() || cabinet.isOneColorMode())) {
                    cabinet.getColors().set(0, color);
                    door1.setColor(color);
                }
                if (cabinet.getColors().size() > 1 && (door2.isSelected() || cabinet.isOneColorMode())) {
                    cabinet.getColors().set(1, color);
                    door2.setColor(color);
                }
            } else if (selectedTag < 5) {
                if (cabinet.getDrawers().size() > 0 && (picDrawer1.isSelected() || cabinet.isOneColorMode())) {
                    cabinet.getDrawers().set(0, color);
                    picDrawer1.setColor(color);
                }
                if (cabinet.getDrawers().size() > 1 && (picDrawer2.isSelected() || cabinet.isOneColorMode())) {
                    cabinet.getDrawers().set(1, color);
                    picDrawer2.setColor(color);
                }
                if (cabinet.getDrawers().size() > 2 && (picDrawer3.isSelected() || cabinet.isOneColorMode())) {
                    cabinet.getDrawers().set(2, color);
                    picDrawer3.setColor(color);
                }
            } else {
                if (stripe.isSelected()) {
                    cabinet.setStripe(color);
                    stripe.setColor(color);
                }
            }
        }

        if (delegate != null) {
            delegate.didChange(this);
        }
    }

    public int findSelectedTag(View view) {
        IWColorSelectorView selector = null;
        if (door1 != null && door1.isSelected()) selector = door1;
        if (door2 != null && door2.isSelected()) selector = door2;
        if (door3 != null && door3.isSelected()) selector = door3;
        if (door4 != null && door4.isSelected()) selector = door4;
        if (door5 != null && door5.isSelected()) selector = door5;
        if (door6 != null && door6.isSelected()) selector = door6;
        if (door7 != null && door7.isSelected()) selector = door7;
        if (door8 != null && door8.isSelected()) selector = door8;
        if (door9 != null && door9.isSelected()) selector = door9;
        if (picDrawer1 != null && picDrawer1.isSelected()) selector = picDrawer1;
        if (picDrawer2 != null && picDrawer2.isSelected()) selector = picDrawer2;
        if (picDrawer3 != null && picDrawer3.isSelected()) selector = picDrawer3;
        if (stripe != null && stripe.isSelected()) selector = stripe;

        if (selector != null) return Integer.parseInt((String) selector.getTag());

      /*  if (view instanceof IWColorSelectorView) {
            IWColorSelectorView selector = (IWColorSelectorView) view;
            if (selector.isSelected()) {
                return Integer.parseInt((String) selector.getTag());
            }
        } else if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View childView = viewGroup.getChildAt(i);
                return findSelectedTag(childView);
            }
        }*/
        return 0;
    }

    public void setStripeText(String stripeText) {
        if (stripe == null) return;
        stripe.setText(stripeText);
        picDrawer1.setSelected(false);
        picDrawer2.setSelected(false);
        picDrawer3.setSelected(false);
        door1.setSelected(false);
        if (door2 != null)
            door2.setSelected(false);
        if (door3 != null)
            door3.setSelected(false);
        if (door4 != null)
            door4.setSelected(false);
        if (door5 != null)
            door5.setSelected(false);
        if (door6 != null)
            door6.setSelected(false);
        if (door7 != null)
            door7.setSelected(false);
        if (door8 != null)
            door8.setSelected(false);
        if (door9 != null)
            door9.setSelected(false);
        if (stripe != null) {
            stripe.setSelected(false);
            stripe.setColor(null);
        }
    }

    @Override
    public void didSelection(IWColorSelectorView colorSelectorView, View view) {
        selectedView = (IWColorSelectorView) view;
        if (delegate != null) {
            delegate.didSelectButton(this, Integer.parseInt((String) view.getTag()));
        }

    }


}
