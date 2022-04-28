package com.Satway.gpstracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.product;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

// inventory activity -> list of details
public class Renewal extends AppCompatActivity {

    private static final String TAG = "Renewal";
    Button allimeis, soldimeis, validities;
    SharedPreferences dealername;
    String imeiss;
    TextView datesmult, imeimult, dealermulti, textimeiauto, textdateauto, textdealerauto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renewal);


        allimeis = findViewById(R.id.allimei);
        soldimeis = findViewById(R.id.sold);
        validities = findViewById(R.id.validity);
        dealername = getSharedPreferences("cridential", Context.MODE_PRIVATE);
        final String hello = dealername.getString("dealername", "");
        System.out.println(hello);
        datesmult = findViewById(R.id.imeimulti);
        imeimult = findViewById(R.id.imeimulti1);
        dealermulti = findViewById(R.id.imeimulti2);
        textimeiauto=findViewById(R.id.textViewimei);
        textdateauto=findViewById(R.id.textViewdate);
        textdealerauto=findViewById(R.id.textViewdealername);
        final String url77 = "http://144.126.252.202/api/getimei";

        allimeis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                RequestQueue MyRequestQueue = Volley.newRequestQueue(Renewal.this);
                final StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url77, new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject object = new JSONObject(response);
                            System.out.println(response);
                            JSONObject imeivaluess = object.getJSONObject("owned");
                            System.out.println(imeivaluess + "========================");
                            Iterator<String> imeiIterators = imeivaluess.keys();
                            System.out.println(imeiIterators);
                            while (imeiIterators.hasNext()) {
                                String key = imeiIterators.next();
                                System.out.println(key + "##################");
                                imeimult.append(key);
                                textimeiauto.setVisibility(view.getVisibility());
                                imeimult.append("\n\n");
                                String imeissss = imeivaluess.getJSONArray(key).getString(0);
                                System.out.println(imeissss + "............");
                                Log.d(TAG, "onResponse:primary " + imeissss);
                                datesmult.append(imeissss);
                                textdateauto.setVisibility(view.getVisibility());
                                datesmult.append("\n\n");
                                String dealernamemult = imeivaluess.getJSONArray(key).getString(1);
                                dealermulti.append(dealernamemult);
                                textdealerauto.setVisibility(view.getVisibility());
                                dealermulti.append("\n\n");

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }) {
                    protected Map<String, String> getParams() {
                        Map<String, String> MyData = new HashMap<String, String>();
                        MyData.put("dealernames", hello);
                        return MyData;
                    }
                };
                MyRequestQueue.add(MyStringRequest);
            }
        });

        soldimeis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                RequestQueue MyRequestQueue = Volley.newRequestQueue(Renewal.this);
                final StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url77, new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject object = new JSONObject(response);
                            System.out.println(response);
                            JSONObject imeivaluess = object.getJSONObject("owned");
                            System.out.println(imeivaluess + "========================");
                            Iterator<String> imeiIterators = imeivaluess.keys();
                            System.out.println(imeiIterators);

                            while (imeiIterators.hasNext()) {
                                String key = imeiIterators.next();
                                System.out.println(key + "##################");
                                if (!key.equals(hello)){
                                    imeimult.append(key);
                                    textimeiauto.setVisibility(view.getVisibility());
                                    imeimult.append("\n\n");
                                }

                                String imeissss = imeivaluess.getJSONArray(key).getString(0);
                                if(!imeissss.equals(hello)) {
                                    System.out.println(imeissss + "............");
                                    Log.d(TAG, "onResponse:primary " + imeissss);
                                    datesmult.append(imeissss);
                                    textdateauto.setVisibility(view.getVisibility());
                                    datesmult.append("\n\n");
                                }

                                String dealernamemult = imeivaluess.getJSONArray(key).getString(1);
                                if(!dealernamemult.equals(hello)){
                                    dealermulti.append(dealernamemult);
                                    textdealerauto.setVisibility(view.getVisibility());
                                    dealermulti.append("\n\n");
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }) {
                    protected Map<String, String> getParams() {
                        Map<String, String> MyData = new HashMap<String, String>();
                        MyData.put("dealernames", hello);
                        return MyData;
                    }
                };
                MyRequestQueue.add(MyStringRequest);
            }
        });


    }

    public void hideKeybpard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
    }
}




















