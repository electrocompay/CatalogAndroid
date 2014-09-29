package de.joli.cataloglib.util;

import android.widget.ProgressBar;

/**
 * Created by abel.miranda on 9/28/14.
 */
public interface OnProgressListener {

    public void progress(int value);

    void getTotalEntries(int totalEntries);
}
