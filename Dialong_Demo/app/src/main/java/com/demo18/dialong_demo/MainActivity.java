package com.demo18.dialong_demo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView txtNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtNotification = (TextView) findViewById(R.id.textView);
    }

    // nhận even từ confirm
    boolean doubleBackToExitPressedOnce = false;
    long previousTime;

    @Override
    public void onBackPressed() {
        //----------1 2 3 cách nào củng ok
        //------------------------------------1
//        if (doubleBackToExitPressedOnce) {
//            super.onBackPressed();
//            return;
//        }
//
//        this.doubleBackToExitPressedOnce = true;
//        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
//
//        new Handler().postDelayed(new Runnable() {
//
//            @Override
//            public void run() {
//                doubleBackToExitPressedOnce=false;
//            }
//        }, 2000);
//    }
        //--------------------------------------1
        //--------------------------------------2
        {
            if (2000 + previousTime > (previousTime = System.currentTimeMillis())) {
                super.onBackPressed();
            } else {
                Toast.makeText(getBaseContext(), "Tap back button in order to exit", Toast.LENGTH_SHORT).show();
            }
        }
    }

        //--------------------------------------2
    //---------------3
    // Confirm();
    //---------------3
    private void Confirm() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Notification");
        dialog.setIcon(android.R.drawable.ic_delete);
        dialog.setMessage("Do you want to Exit");
        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//            finish();
                MainActivity.super.onBackPressed();
            }
        });
        dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog.show();
    }
}
