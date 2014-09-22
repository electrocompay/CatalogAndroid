package com.indianwebs.catalog.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Catalog extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        final Button buttonClose = (Button) findViewById(R.id.buttonClose);
        buttonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        final ImageView imageViewTable = (ImageView) findViewById(R.id.imageViewTable);
        imageViewTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                viewCatalog(R.raw.joli_catalogus2014, "catalogustab2014.pdf");

            }
        });

        final ImageView imageViewCabs = (ImageView) findViewById(R.id.imageViewCabs);
        imageViewCabs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewCatalog(R.raw.katalogus_joli_ok_lr, "cataloguscab2014.pdf");
            }
        });

    }

    private void viewCatalog(int id, String name) {
        File outputFile = new File(Environment.getExternalStorageDirectory(), name);
        if (!outputFile.exists()){
            InputStream inputStream = getResources().openRawResource(id);
            try {
                outputFile.createNewFile();
                FileOutputStream outputStream = new FileOutputStream(outputFile);

                byte[] buf = new byte[8192];
                while (true) {
                    int length = inputStream.read(buf);
                    if (length < 0)
                        break;
                    outputStream.write(buf, 0, length);
                }

                inputStream.close();
                outputStream.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        showPDF(outputFile.getPath());
    }


    private void showPDF(String s) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(new File(s)),"application/pdf");
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        try {
            startActivity(intent);
        } catch (Exception e)
        {
            new AlertDialog.Builder(this).setTitle("Error").setMessage("No pdf viewer available.\nPlease, download it from Google Play Store.").setNegativeButton("OK", null).show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.catalog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
