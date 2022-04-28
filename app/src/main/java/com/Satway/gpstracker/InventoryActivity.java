package com.Satway.gpstracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

//Renewal activity page
public class InventoryActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final String TAG = "InventoryActivity";
    String[] country = {"IMEI", "ICCID", "UIN"};
    EditText val;
    String valuess;
    String exam;
    String amountvalue;
    ImageButton btnexample;
    Button paymentbtn;
    String mmeetthhood;
    TextView txt1,txt2,txt3,txt11,txt22,txt33;
    String btnvalue ,imeiid,iccidid,uinid;
    SharedPreferences namedealer;
    final String url11 ="http://144.126.252.202/api/getdevicedetails";

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        namedealer = getSharedPreferences("cridential", Context.MODE_PRIVATE);
        final String helloss = namedealer.getString("dealername", "");
        System.out.println(helloss);
        Spinner spin = (Spinner) findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(InventoryActivity.this);
        val = findViewById(R.id.example);
        txt1=findViewById(R.id.texteg11);
        txt2=findViewById(R.id.texteg22);
        txt3=findViewById(R.id.texteg33);
        txt11=findViewById(R.id.texteg1);
        txt22=findViewById(R.id.texteg2);
        txt33=findViewById(R.id.texteg3);
        btnexample=findViewById(R.id.imageButtonex);
        paymentbtn=findViewById(R.id.sendpaytmeg);

        paymentbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amountvalue="2900";
                Intent intent=new Intent(InventoryActivity.this,RenewalPayment.class);
                intent.putExtra("keykey", amountvalue);
                System.out.println(amountvalue);
                intent.putExtra("imeikey",imeiid);
                System.out.println(imeiid);
                intent.putExtra("iccidkey",iccidid);
                System.out.println(iccidid);
                intent.putExtra("upikey",uinid);
                System.out.println(uinid);
                intent.putExtra("namekey",helloss);
                System.out.println(helloss);
                startActivity(intent);
            }
        });

        btnexample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (val.getText().toString().length()==15 && valuess.equals("1")){
                        exam=val.getText().toString().replaceAll("\\s+", "");
                        btnvalue=valuess;
//                        Toast.makeText(InventoryActivity.this, exam, Toast.LENGTH_SHORT).show();
                }else if(val.getText().toString().length()==17 && valuess.equals("2")){
                        exam=val.getText().toString().replaceAll("\\s+", "");
                        btnvalue=valuess;
//                        Toast.makeText(InventoryActivity.this, exam, Toast.LENGTH_SHORT).show();
                }else if(val.getText().toString().length()==19 && valuess.equals("3")){
                    exam=val.getText().toString().replaceAll("\\s+", "");
                    btnvalue=valuess;
//                    Toast.makeText(InventoryActivity.this, exam, Toast.LENGTH_SHORT).show();
                }else {
                    val.setError("Invalid ID");
                }
                RequestQueue MyRequestQueue = Volley.newRequestQueue(InventoryActivity.this);
                final StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url11, new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            imeiid =obj.getString("imei");
                            System.out.println(imeiid);
                            iccidid=obj.getString("iccid");
                            System.out.println(iccidid);
                            uinid=obj.getString("uniqueid");
                            System.out.println(uinid);
                            System.out.println(obj);

                            if(val.length()==15){
                                txt1.setVisibility(View.VISIBLE);
                                txt2.setVisibility(View.VISIBLE);
                                txt3.setVisibility(View.VISIBLE);
                                txt11.setVisibility(View.VISIBLE);
                                txt22.setVisibility(View.VISIBLE);
                                txt33.setVisibility(View.VISIBLE);
                                paymentbtn.setVisibility(View.VISIBLE);
                                txt11.setText("ICCID");
                                txt1.setText(iccidid);
                                txt22.setText("UIN");
                                txt2.setText(uinid);
                                txt33.setText("Renewal Date");
                                txt3.setText("2250");
                            }else if(val.length()==17){
                                txt1.setVisibility(View.VISIBLE);
                                txt2.setVisibility(View.VISIBLE);
                                txt3.setVisibility(View.VISIBLE);
                                txt11.setVisibility(View.VISIBLE);
                                txt22.setVisibility(View.VISIBLE);
                                txt33.setVisibility(View.VISIBLE);
                                paymentbtn.setVisibility(View.VISIBLE);
                                txt11.setText("IMEI");
                                txt1.setText(imeiid);
                                txt22.setText("ICCID");
                                txt2.setText(iccidid);
                                txt33.setText("Renewal Amount");
                                txt3.setText("2250");
                            }else if(val.length()==19){
                                txt1.setVisibility(View.VISIBLE);
                                txt2.setVisibility(View.VISIBLE);
                                txt3.setVisibility(View.VISIBLE);
                                txt11.setVisibility(View.VISIBLE);
                                txt22.setVisibility(View.VISIBLE);
                                txt33.setVisibility(View.VISIBLE);
                                paymentbtn.setVisibility(View.VISIBLE);
                                txt11.setText("IMEI");
                                txt1.setText(imeiid);
                                txt22.setText("ICCID");
                                txt2.setText(iccidid);
                                txt33.setText("Renewal Date");
                                txt3.setText("2250");
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
                        MyData.put("id",btnvalue);
                        System.out.println(valuess);
                        MyData.put("idvalue",exam);
                        System.out.println(exam);
                        return MyData;
                    }
                };
                MyRequestQueue.add(MyStringRequest);
            }
        });

        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, country);
//        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        aa.setDropDownViewResource(R.layout.simple_spinner_custom);
        spin.setAdapter(aa);
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (country[position].equals("IMEI")){
                valuess="1";
                System.out.println(valuess);
                System.out.println(country[position]);
            }else if(country[position].equals("ICCID")){
                valuess="2";
                System.out.println(valuess);
                System.out.println(country[position]);
            }else if(country[position].equals("UIN")){
                valuess="3";
                System.out.println(valuess);
                System.out.println(country[position]);
            }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public void hideKeybpard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
    }

}