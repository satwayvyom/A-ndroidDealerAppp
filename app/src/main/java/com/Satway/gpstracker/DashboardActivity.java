 package com.Satway.gpstracker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

//import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
//import android.text.Layout;
//import android.util.Base64;
import android.view.View;
//import android.view.animation.Animation;
//import android.view.animation.AnimationUtils;
//import android.view.inputmethod.InputMethodManager;
//import android.widget.AdapterView;
import android.widget.Button;
//import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
//import android.widget.Toast;

import com.Satway.gpstracker.activities.MainActivity;
//import com.daimajia.androidanimations.library.Techniques;
//import com.daimajia.androidanimations.library.YoYo;

 //Dashboard activity 1st -> Dealers lopgin dashboard
public class DashboardActivity extends AppCompatActivity {

    Button buttonsignout;
    ImageView img1, img2, img3, img4,img5;
    TextView txt1, txt2, txt3, txt4,txt5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        final SharedPreferences sharedPreferences = getSharedPreferences("cridential", Context.MODE_PRIVATE);
        img1=findViewById(R.id.usercreate1);
        img2=findViewById(R.id.deviceactivity1);
        img3=findViewById(R.id.paytm1);
        img4=findViewById(R.id.inv1);
       // img5=findViewById(R.id.usercreates);
        txt1=findViewById(R.id.createUser);
        txt2=findViewById(R.id.deviceActivity);
        txt3=findViewById(R.id.payments);
        txt4=findViewById(R.id.inventory);
//        txt5=findViewById(R.id.invent);
        buttonsignout=findViewById(R.id.textView2);
        LinearLayout menu_photos = (LinearLayout )findViewById(R.id.linear11);
        LinearLayout menu_photos1 = (LinearLayout )findViewById(R.id.linear22);
        LinearLayout menu_photos2=(LinearLayout)findViewById(R.id.linear33);
        LinearLayout menu_photo3=(LinearLayout)findViewById(R.id.linear44);
//        LinearLayout menu_photo4=(LinearLayout)findViewById(R.id.linear44);

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(DashboardActivity.this,CreateAccountActivity.class);
                startActivity(intent);
            }
        });

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this,DeviceActivity.class);
                startActivity(intent);
            }
        });

        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this,PurchaseActivity.class);
                startActivity(intent);
            }
        });

        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this,InventoryActivity.class);
                startActivity(intent);
            }
        });

//        img5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(DashboardActivity.this,Renewal.class);
//                startActivity(intent);
//            }
//        });

        txt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this,CreateAccountActivity.class);
                startActivity(intent);
            }
        });

        txt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this,DeviceActivity.class);
                startActivity(intent);
            }
        });

        txt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this,PurchaseActivity.class);
                startActivity(intent);
            }
        });

        txt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this,InventoryActivity.class);
                startActivity(intent);
            }
        });

//        txt5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(DashboardActivity.this,Renewal.class);
//                startActivity(intent);
//            }
//        });


        menu_photos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent picture_intent = new Intent(DashboardActivity.this, CreateAccountActivity.class);
                startActivity(picture_intent);
            }
        });

        menu_photos1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent picture_intent = new Intent(DashboardActivity.this, DeviceActivity.class);
                startActivity(picture_intent);
            }
        });

        menu_photos2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent picture_intent = new Intent(DashboardActivity.this, PurchaseActivity.class);
                startActivity(picture_intent);
            }
        });

        menu_photo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent picture_intent = new Intent(DashboardActivity.this, InventoryActivity.class);
                startActivity(picture_intent);
            }
        });

//        menu_photo4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent picture_intent = new Intent(DashboardActivity.this, Renewal.class);
//                startActivity(picture_intent);
//            }
//        });

        buttonsignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DashboardActivity.this);
                alertDialogBuilder.setTitle("Are you sure wants to logout?");
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Yes",
                                new DialogInterface.OnClickListener() {
                                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                                    public void onClick(DialogInterface dialog, int id) {
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.clear();
                                        editor.apply();
                                        finishAndRemoveTask();
                                        finish();
                                        startActivity(new Intent(DashboardActivity.this, MainActivity.class));

                                    }
                                })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                dialog.cancel();

                            }
                        });
                AlertDialog alert = alertDialogBuilder.create();

                alert.setTitle("Are you sure wants logout");
                alert.show();

            }
        });
    }
}




