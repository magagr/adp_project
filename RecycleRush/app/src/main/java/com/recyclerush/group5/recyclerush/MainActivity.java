package com.recyclerush.group5.recyclerush;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.recyclerush.group5.recyclerush.itemObject;
import com.recyclerush.group5.recyclerush.SecondActivity;


public class MainActivity extends AppCompatActivity {


    // Create two objects, one for snus and one for redbull
    itemObject redbull = new itemObject("Redbull","7340131610000", true, "metal" );
    itemObject snus = new itemObject("Snus", "7311250004360", true, "plastic, paper");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("mainactivity", "test");
        super.onCreate(savedInstanceState);
        openScanner();
        //displayHelper("7340131610000");
        //openCameraIfAllowed();

    }

    private void openScanner() {
        IntentIntegrator scanIntegrator = new IntentIntegrator(this);
        scanIntegrator.initiateScan();
    }

    private void openCameraIfAllowed() {
        PackageManager packageManager = getPackageManager();

        if (packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            Intent openCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivity(openCamera);
        }
    }


    // use functions in the itemobjectclass to retrieve information about each object.
    //String snusname = snus.getName();
    //String snusID = snus.getScanId();

    private void displayHelper(String scanId) {
        if (scanId.equals("7340131610000")) {
            display(redbull);
        } else  if (scanId.equals("7311250004360")){
            display(snus);
        }
    }

    private void display(itemObject obj) {
        Intent displayInfo = new Intent(this, SecondActivity.class);
        displayInfo.putExtra("scanId", obj.getScanId());
        displayInfo.putExtra("name", obj.getName());
        displayInfo.putExtra("materials", obj.getMaterials());
        if (obj.isRecycleable()) {
            displayInfo.putExtra("recyc", "Recycable!");
        } else {
            displayInfo.putExtra("recyc", "Not Recycable!");
        }
        startActivity(displayInfo);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent in) {
        super.onActivityResult(requestCode, resultCode, in);
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, in);
        if (scanningResult != null) {
            try {
                Log.i("barcode", in.getStringExtra("SCAN_RESULT"));
                setContentView(R.layout.second_layout);
                displayHelper(in.getStringExtra("SCAN_RESULT"));
            }
            catch (NullPointerException e){}
        }
    }
}