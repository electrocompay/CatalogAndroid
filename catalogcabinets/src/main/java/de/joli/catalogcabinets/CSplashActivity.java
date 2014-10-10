package de.joli.catalogcabinets;

import de.joli.cataloglib.SplashActivity;

/**
 * Created by abel.miranda on 9/24/14.
 */
public class CSplashActivity extends SplashActivity {

    @Override
    protected int getImageResourceId() {
        return R.drawable.splash_cabinets_1024_768;
    }

    @Override
    protected Class<?> getActivityType() {
        return CModelActivity.class;
    }

    @Override
    protected boolean expansionFilesDelivered() {
        return true;
    }

}
