package com.Satway.gpstracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
//import android.view.Gravity;
import android.view.View;
import android.widget.Button;
//import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
//import android.widget.Toast;

import com.Satway.gpstracker.activities.MainActivity;
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;
//
//import org.json.JSONArray;
//import org.json.JSONObject;
//
//import java.util.HashMap;
//import java.util.Map;

//dashboard activity 2nd-> Sub Dealer login dashboard
public class Dashboard2Activity extends AppCompatActivity {

    Button buttonsignout;
    ImageView img11, img22,img23, img24;
    TextView txt11, txt22,txt23, txt24;
    String loggedUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard2);

        final SharedPreferences sharedPreferences1 = getSharedPreferences("cridential", Context.MODE_PRIVATE);
               loggedUser=getIntent().getStringExtra("Dealername");
        img11 = findViewById(R.id.usercreate2);
        img22 = findViewById(R.id.deviceactivity2);
        img23=findViewById(R.id.inv1);
//        img24=findViewById(R.id.inv1f);
        txt11 = findViewById(R.id.createUser2);
        txt22 = findViewById(R.id.deviceActivity2);
        txt23 = findViewById(R.id.inventorytext1);
//        txt24=findViewById(R.id.inventorytext1e);
        buttonsignout = findViewById(R.id.textView2);
        LinearLayout menu_pic = (LinearLayout )findViewById(R.id.linear111);
        LinearLayout menu_pic1 = (LinearLayout )findViewById(R.id.llinear222);
        LinearLayout menu_pic2 = (LinearLayout )findViewById(R.id.linear55);
//        LinearLayout menu_pic3 = (LinearLayout )findViewById(R.id.linear555);

        img11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard2Activity.this, CreateAccountActivity.class);
                startActivity(intent);
            }
        });

        img22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard2Activity.this, DeviceActivity.class);
                startActivity(intent);
            }
        });
//        img24.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Dashboard2Activity.this, Renewal.class);
//                startActivity(intent);
//            }
//        });
        img23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard2Activity.this, InventoryActivity.class);
                startActivity(intent);
            }
        });

        txt11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard2Activity.this, CreateAccountActivity.class);
                startActivity(intent);
            }
        });

        txt22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard2Activity.this, DeviceActivity.class);
                startActivity(intent);
            }
        });

        txt23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard2Activity.this, InventoryActivity.class);
                startActivity(intent);
            }
        });
//
//        txt24.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Dashboard2Activity.this, Renewal.class);
//                startActivity(intent);
//            }
//        });

        menu_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent picture_intent = new Intent(Dashboard2Activity.this, CreateAccountActivity.class);
                startActivity(picture_intent);
            }
        });

        menu_pic1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent picture_intent = new Intent(Dashboard2Activity.this, DeviceActivity.class);
                startActivity(picture_intent);
            }
        });

        menu_pic2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent picture_intent = new Intent(Dashboard2Activity.this, InventoryActivity.class);
                startActivity(picture_intent);
            }
        });

//        menu_pic3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent picture_intent = new Intent(Dashboard2Activity.this, Renewal.class);
//                startActivity(picture_intent);
//            }
//        });

        buttonsignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Dashboard2Activity.this);
                alertDialogBuilder.setTitle("Are you sure wants to logout?");
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Yes",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        SharedPreferences.Editor editor = sharedPreferences1.edit();
                                        editor.clear();
                                        editor.apply();
                                        finishAndRemoveTask();
                                        finish();
                                        startActivity(new Intent(Dashboard2Activity.this, MainActivity.class));
                                   }
                                })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                dialog.cancel();

                            }
                        });
                AlertDialog alert = alertDialogBuilder.create();

                alert.setTitle("Are you sure wants to logout?");
                alert.show();

            }
        });
    }
}

