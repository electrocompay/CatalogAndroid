package de.joli.cataloglib.util;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.RecoverySystem;
import android.util.Log;
import android.widget.ProgressBar;

import com.android.vending.expansion.zipfile.APEZProvider;
import com.android.vending.expansion.zipfile.APKExpansionSupport;
import com.android.vending.expansion.zipfile.ZipResourceFile;

import de.joli.cataloglib.model.IWColor;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * Created by abelmiranda on 7/20/14.
 */
public class Utils {


    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);
    private static final int BUFFER_SIZE = 8192;
    private static final String OBB_FILE = "main.1.de.joli.catalog";

    /**
     * Generate a value suitable for use in .
     * This value will not collide with ID values generated at build time by aapt for R.id.
     *
     * @return a generated ID value
     */
    public static int generateViewId() {
        for (;;) {
            final int result = sNextGeneratedId.get();
            // aapt-generated IDs have the high byte nonzero; clamp to the range under that.
            int newValue = result + 1;
            if (newValue > 0x00FFFFFF) newValue = 1; // Roll over to 1, not 0.
            if (sNextGeneratedId.compareAndSet(result, newValue)) {
                return result;
            }
        }
    }

    public static String getExpansionFileDirectory(Context context){
        return Environment.getExternalStorageDirectory() + "/Android/obb/" + context.getPackageName() + "/";
    }

    public static String getExpansionFile(Context context){
        return getExpansionFileDirectory(context) + OBB_FILE + ".obb";
    }

    public static String getResourcesPath(Context context){
        return getExpansionFileDirectory(context) + OBB_FILE + "/";
    }

    public static Bitmap getBitmapFromAsset(Context context, String filePath) {
        Bitmap bitmap = null;
        try {
            File file = new File(getResourcesPath(context) + "/" + filePath);
            InputStream inputStream = new FileInputStream(file);
            if (inputStream != null) {
                bitmap = BitmapFactory.decodeStream(inputStream);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    public static void unzip(Context context, String zipFile, String location, OnProgressListener onProgressListener) throws IOException {
        int size;
        byte[] buffer = new byte[BUFFER_SIZE];

        try {
            if ( !location.endsWith("/") ) {
                location += "/";
            }
            File f = new File(location);
            if(!f.isDirectory()) {
                f.mkdirs();
            }

            ZipInputStream zin = new ZipInputStream(new BufferedInputStream(new FileInputStream(zipFile), BUFFER_SIZE));
            try {
                int counter = 0;

                ZipFile zFile = new ZipFile(zipFile);
                int totalEntries = zFile.size();
                onProgressListener.getTotalEntries(totalEntries);
                ZipEntry ze = null;
                while ((ze = zin.getNextEntry()) != null) {
                    counter++;
                    String path = location + ze.getName();
                    File unzipFile = new File(path);

                    if (ze.isDirectory()) {
                        if(!unzipFile.isDirectory()) {
                            unzipFile.mkdirs();
                        }
                    } else {
                        // check for and create parent directories if they don't exist
                        File parentDir = unzipFile.getParentFile();
                        if ( null != parentDir ) {
                            if ( !parentDir.isDirectory() ) {
                                parentDir.mkdirs();
                            }
                        }

                        // unzip the file
                        FileOutputStream out = new FileOutputStream(unzipFile, false);
                        BufferedOutputStream fout = new BufferedOutputStream(out, BUFFER_SIZE);
                        try {
                            while ( (size = zin.read(buffer, 0, BUFFER_SIZE)) != -1 ) {
                                fout.write(buffer, 0, size);
                            }

                            zin.closeEntry();
                        }
                        finally {
                            fout.flush();
                            fout.close();
                        }
                    }

                    if (onProgressListener != null){
                        onProgressListener.progress(counter);
                    }
                }
            }
            finally {
                zin.close();
            }
        }
        catch (Exception e) {
            Log.e("TAG", "Unzip exception", e);
        }
    }

    public static ArrayList<IWColor> colorListWithoutColor(ArrayList<IWColor> list, String code)
    {
        ArrayList<IWColor> array = new ArrayList<IWColor>(list);

        String[] colorCodes = code.split(",");
        for (String colorCode : colorCodes) {
            for (IWColor color : array) {
                if (color.getCode().equals(colorCode)) {
                    array.remove(color);
                    break;
                }
            }
        }
        return array;
    }
}
