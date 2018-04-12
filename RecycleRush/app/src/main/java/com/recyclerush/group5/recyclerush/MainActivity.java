package com.recyclerush.group5.recyclerush;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.recyclerush.group5.recyclerush.itemObject;
import com.recyclerush.group5.recyclerush.SecondActivity;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    HashMap<String, itemObject> map = new HashMap<String, itemObject>();
    // Create two objects, one for snus and one for redbull
    itemObject redbull = new itemObject("Redbull","7340131610000", true, "metal" );
    itemObject snus = new itemObject("Snus", "7311250004360", true, "plastic, paper");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);
        displayHelper("7340131610000");

        map.put("7340131610000", redbull);
        map.put("7311250004360", snus);
        openCameraIfAllowed();
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

    private itemObject getScannedItem(String id){
            return map.get(id);
    }
}