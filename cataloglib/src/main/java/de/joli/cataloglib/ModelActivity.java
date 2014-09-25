package de.joli.cataloglib;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import de.joli.cataloglib.model.IWColor;
import de.joli.cataloglib.views.IWSelectorView;

/**
 * Created by abel.miranda on 9/24/14.
 */
public abstract class ModelActivity extends Activity implements IWSelectorView.IWSelectorViewControllerDelegate{

    private static final int REQUEST_SHARE_ACTION = 1;
    protected ViewGroup content;
    protected int lockDraw = 0;
    protected TabHost tabHost;
    private RelativeLayout homeMenu;
    private RelativeLayout extraMenu;
    private View extraTR;
    private View homeTR;
    private String localAbsoluteFilePath;

    public static void addImageToGallery(final String filePath, final Context context) {

        ContentValues values = new ContentValues();

        values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis());
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        values.put(MediaStore.MediaColumns.DATA, filePath);

        context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        Toast.makeText(context, "Image Saved Successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_model);
        content = (ViewGroup) findViewById(R.id.content);

        findViewById(android.R.id.content).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                closeMenus();
                return false;
            }
        });

        createViews();
        
        createHomeMenu();
        createExtraMenu();
        createDrawers();
        createTabs();
        updateTabTextColor();
        drawAll();
    }

    protected abstract void drawAll();

    protected abstract void createViews();

    protected abstract void createDrawers();

    protected void createTabs() {
        tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                updateTabTextColor();
            }
        });
    }

    private void closeMenus() {
        homeMenu.setVisibility(View.GONE);
        homeTR.setVisibility(View.GONE);
        extraMenu.setVisibility(View.GONE);
        extraTR.setVisibility(View.GONE);
    }

    private void createExtraMenu() {
        extraMenu = (RelativeLayout) findViewById(R.id.extraMenu);
        extraTR = findViewById(R.id.extraTR);
        ListView extraMenuList = (ListView) findViewById(R.id.extraMenuList);

        extraMenuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i){
                    case 0: {
                        String fileSaved = captureView("image_joli.png");
                        if (fileSaved != null)
                           addImageToGallery(fileSaved, getBaseContext());
                        break;
                    }
                    case 1: doPhotoPrint();
                        break;
                    case 2: browseTo("http://joli.be/contact");
                        break;
                    default:
                        break;
                }
                extraMenu.setVisibility(View.GONE);
                extraTR.setVisibility(View.GONE);

            }
        });

        ImageView extraButton = (ImageView) findViewById(R.id.extraButton);
        extraButton.setClickable(true);
        extraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (extraMenu.getVisibility() == View.VISIBLE) {
                    extraMenu.setVisibility(View.GONE);
                    extraTR.setVisibility(View.GONE);
                } else {
                    homeMenu.setVisibility(View.GONE);
                    homeTR.setVisibility(View.GONE);
                    extraTR.setVisibility(View.VISIBLE);
                    extraTR.bringToFront();
                    extraMenu.setVisibility(View.VISIBLE);
                    extraMenu.bringToFront();
                }
            }
        });

        ArrayAdapter<String> extraMenuAdapter = new ArrayAdapter<String>(this, R.layout.menu_item, R.id.textView, new String[]{"Save", "Share"});
        extraMenuList.setAdapter(extraMenuAdapter);
    }

    private void createHomeMenu() {
        homeMenu = (RelativeLayout) findViewById(R.id.homeMenu);
        homeTR = (View) findViewById(R.id.homeTR);
        ListView homeMenuList = (ListView) findViewById(R.id.homeMenuList);


        homeMenuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i){
                    case 0: browseTo("http://joli.be");
                        break;
                    case 1: showCatalogue();
                        break;
                    case 2: browseTo("http://joli.be/contact");
                        break;
                    default:
                        break;
                }
                homeMenu.setVisibility(View.GONE);
                homeTR.setVisibility(View.GONE);
            }
        });

        ImageView homeButton = (ImageView) findViewById(R.id.homeButton);
        homeButton.setClickable(true);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (homeMenu.getVisibility() == View.VISIBLE) {
                    homeMenu.setVisibility(View.GONE);
                    homeTR.setVisibility(View.GONE);
                } else {
                    extraMenu.setVisibility(View.GONE);
                    extraTR.setVisibility(View.GONE);
                    homeTR.setVisibility(View.VISIBLE);
                    homeTR.bringToFront();
                    homeMenu.setVisibility(View.VISIBLE);
                    homeMenu.bringToFront();
                    findViewById(android.R.id.content).invalidate();
                }
            }
        });

        ArrayAdapter<String> homeMenuAdapter = new ArrayAdapter<String>(this, R.layout.menu_item, R.id.textView, new String[]{"Visit our web", "Catalogues", "Contact us"});
        homeMenuList.setAdapter(homeMenuAdapter);
    }

    public Bitmap captureView(){
        View view = findViewById(R.id.relativeLayout);
        //Create a Bitmap with the same dimensions
        Bitmap image = Bitmap.createBitmap(view.getWidth(),
                view.getHeight(),
                Bitmap.Config.ARGB_8888);
        //Draw the view inside the Bitmap
        Canvas canvas = new Canvas(image);
        canvas.drawColor(Color.WHITE);
        View logo = findViewById(R.id.logo);
        logo.draw(canvas);
        canvas.translate(0, logo.getHeight());
        content.draw(canvas);
        return image;
    }

    public String captureView(String filename){
        Bitmap image = captureView();

        //Store to sdcard
        try {
            String path = Environment.getExternalStorageDirectory().toString();
            File myFile = new File(path,filename);
            FileOutputStream out = new FileOutputStream(myFile);

            image.compress(Bitmap.CompressFormat.PNG, 90, out); //Output
            return myFile.getPath();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void doPhotoPrint() {
        Bitmap icon = captureView();
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("image/jpeg");

        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "title");
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        Uri uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                values);
        localAbsoluteFilePath = uri.getPath();

        OutputStream outstream;
        try {
            outstream = getContentResolver().openOutputStream(uri);
            icon.compress(Bitmap.CompressFormat.JPEG, 100, outstream);
            outstream.close();
        } catch (Exception e) {
            System.err.println(e.toString());
        }

        share.putExtra(Intent.EXTRA_STREAM, uri);
        startActivityForResult(Intent.createChooser(share, "Share Image"), REQUEST_SHARE_ACTION);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // deal with this with whatever constant you use. i have a navigator object to handle my navigation so it also holds all mys constants for intents
        if (requestCode== REQUEST_SHARE_ACTION) {
            // delete temp file
            File file = new File (localAbsoluteFilePath);
            file.delete();
        }


    }

    private void showCatalogue() {
        Intent intent = new Intent(getBaseContext(), Catalog.class);
        startActivity(intent);
    }

    private void browseTo(String URL) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(URL));
        startActivity(i);
    }

    protected void unlockDraw() {
        lockDraw--;
    }

    protected void lockDraw() {
        lockDraw++;
    }

    protected void updateTabTextColor() {
        for (int i = 0; i < tabHost.getTabWidget().getTabCount(); i++) {
            TextView textView = (TextView) tabHost.getTabWidget().getChildTabViewAt(i).findViewById(R.id.tabTextView);
            if (tabHost.getTabWidget().getChildTabViewAt(i) != tabHost.getCurrentTabView()){
                textView.setTextColor(Color.WHITE);
            } else {
                textView.setTextColor(Color.BLACK);
            }

        }
    }

    protected IWSelectorView addTabWithSelector(String tag, String title, ArrayList<IWColor> colors, String propertyName)
    {
        IWSelectorView newSelectorView = new IWSelectorView(this);
        newSelectorView.setPropertyName(propertyName);
        newSelectorView.setDelegate(this);
        newSelectorView.setItems(colors);
        addTab(tag, title, newSelectorView);
        return newSelectorView;
     }

    private void addTab(String tag, String title, TabHost.TabContentFactory view)
    {
        TabHost.TabSpec tabSpec1 = tabHost.newTabSpec(tag);
        View customTab = getLayoutInflater().inflate(R.layout.tab, null);
        TextView textView = (TextView) customTab.findViewById(R.id.tabTextView);
        textView.setText(title);
        tabSpec1.setIndicator(customTab);
        tabSpec1.setContent(view);
        tabHost.addTab(tabSpec1);
    }
}
