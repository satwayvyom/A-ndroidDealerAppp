package com.Satway.gpstracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
//import android.app.AlertDialog;
//import android.app.Dialog;
//import android.content.Context;
//import android.content.DialogInterface;
import android.content.Intent;
//import android.content.SharedPreferences;
//import android.graphics.Color;
//import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
//import android.text.InputFilter;
//import android.text.Spanned;
import android.text.TextWatcher;
import android.util.Log;
//import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.AutoCompleteTextView;
//import android.widget.Button;
import android.widget.EditText;
//import android.widget.ImageButton;
//import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


//purchase activity page

public class PurchaseActivity extends AppCompatActivity {
    private static final String TAG = "PurchaseActivity";

    EditText devicequantity, panicbtnquantity;
    TextView perpurchaseamnt,panictotal,devicetotal,panictotall,grandtotl,corriartotal,pay;
    Double PriceInt1,PriceInt2,totalrate1,totalrate2,totaldevice,totalpanic,corriar1,totalcorrier,totalll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);

        devicequantity=findViewById(R.id.purchaseautono);
        perpurchaseamnt=findViewById(R.id.purchaseautonumber);
        panicbtnquantity=findViewById(R.id.purchasepanicno);
        panictotal=findViewById(R.id.textView16);
        devicetotal=findViewById(R.id.demo);
        panictotall=findViewById(R.id.granttotal);
        grandtotl=findViewById(R.id.grant);
        corriartotal=findViewById(R.id.corriartotal);
        pay=findViewById(R.id.total);

        devicequantity.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String edit1= devicequantity.getText().toString();
                if(edit1.equals("") || edit1==null){
                    perpurchaseamnt.setText("");
                    return;
                }
                int num=Integer.parseInt(edit1);
                if (num == 0) {
                    perpurchaseamnt.setText("0");
                }else {
                    if (num >= 1 && num <= 10){
                        perpurchaseamnt.setText("5200");
                    }else if(num >=11 && num <= 20){
                        perpurchaseamnt.setText("5100");
                    }else if(num >= 21 && num <= 50){
                        perpurchaseamnt.setText("5000");
                    }else if(num >= 51){
                        perpurchaseamnt.setText("4900");
                    }
                }
                }

            @Override
            public void afterTextChanged(Editable s) {

                try {

                    PriceInt1 = Double.parseDouble(devicequantity.getText().toString());
                    PriceInt2 = Double.parseDouble(perpurchaseamnt.getText().toString());
                    totalrate1 = (PriceInt1 * PriceInt2);
                    String tot = new Double(totalrate1).toString();
                    devicetotal.setText(tot);
                } catch (Exception e) {

                }

                String edit2=devicequantity.getText().toString();
                if(edit2.equals("") || edit2==null){
                    return;
                }
                edit2= devicequantity.getText().toString();
                int num=Integer.parseInt(edit2);
                if (num >= 1 && num <= 5) {
                    corriartotal.setText("0");
                }else if(num >= 6 && num <= 10){
                    corriar1=new Double(2 * 175);
                    String tot = new Double(corriar1).toString();
                    corriartotal.setText(tot);
                }else if(num >= 11 && num <= 15){
                    corriar1=new Double(3 * 175);
                    String tot = new Double(corriar1).toString();
                    corriartotal.setText(tot);
                }else if(num >= 16 && num <= 20){
                    corriartotal.setText("RS:360");
                }else if(num >= 21 && num <= 25){
                    corriar1=new Double(360 + 175);
                    String tot = new Double(corriar1).toString();
                    corriartotal.setText(tot);
                }else if(num >= 26 && num <= 30){
                    corriar1=new Double(360+(175 * 2));
                    String tot = new Double(corriar1).toString();
                    corriartotal.setText(tot);
                }else if(num >= 31 && num <= 35){
                    corriar1=new Double(360 + (175 * 3));
                    String tot = new Double(corriar1).toString();
                    corriartotal.setText(tot);
                }else if(num >= 36 && num <= 40){
                    corriar1=new Double(360 +360);
                    String tot = new Double(corriar1).toString();
                    corriartotal.setText(tot);
                }else if(num >= 41 && num <= 45){
                    corriar1=new Double(720 + 175);
                    String tot = new Double(corriar1).toString();
                    corriartotal.setText(tot);
                }else if(num >= 46 && num <= 50){
                    corriar1=new Double(720 + (175*2));
                    String tot = new Double(corriar1).toString();
                    corriartotal.setText(tot);
                }else if(num >= 51 && num <= 55){
                    corriar1=new Double(720 + (175*3));
                    String tot = new Double(corriar1).toString();
                    corriartotal.setText(tot);
                }else if(num >= 55 && num <= 60) {
                    corriar1 = new Double( (720+360));
                    String tot = new Double(corriar1).toString();
                    corriartotal.setText(tot);
                }else if(num >= 61 && num <= 65) {
                    corriar1 = new Double( (1080+175));
                    String tot = new Double(corriar1).toString();
                    corriartotal.setText(tot);
                }else if(num >= 66 && num <= 70) {
                    corriar1 = new Double( 1080+(175*2));
                    String tot = new Double(corriar1).toString();
                    corriartotal.setText(tot);
                }else if(num >= 71 && num <= 75) {
                    corriar1 = new Double( 1080+(175*3));
                    String tot = new Double(corriar1).toString();
                    corriartotal.setText(tot);
                }else if(num >= 76 && num <= 80) {
                    corriar1 = new Double( 1080+360);
                    String tot = new Double(corriar1).toString();
                    corriartotal.setText(tot);
                }else if(num >= 81 && num <= 85) {
                    corriar1 = new Double( 1440+175);
                    String tot = new Double(corriar1).toString();
                    corriartotal.setText(tot);
                }else if(num >= 86 && num <= 90) {
                    corriar1 = new Double( 1440+(175*2));
                    String tot = new Double(corriar1).toString();
                    corriartotal.setText(tot);
                }else if(num >= 91 && num <= 95) {
                    corriar1 = new Double( 1440+(175*3));
                    String tot = new Double(corriar1).toString();
                    corriartotal.setText(tot);
                }else if(num >= 96 && num <= 100) {
                    corriar1 = new Double( 360*4);
                    String tot = new Double(corriar1).toString();
                    corriartotal.setText(tot);
                }

                try {
                    totaldevice=Double.parseDouble(devicetotal.getText().toString());
                    totalcorrier=Double.parseDouble(corriartotal.getText().toString());
                    totalll=(totaldevice  + totalcorrier);
                    String total2 = new Double(totalll).toString();
                    grandtotl.setText(total2);
                }catch (Exception e){

                }
            }

        });


        panicbtnquantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                panictotal.setText("180");

            }
            @Override
            public void afterTextChanged(Editable s) {

                        try {
                            PriceInt1 = Double.parseDouble(panicbtnquantity.getText().toString());
                            totalrate2=(PriceInt1 * 180);
                            String tot = new Double(totalrate2).toString();
                            panictotall.setText(tot);
                        }catch (Exception e){

                        }

                    }
            });

        panictotall.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                try {
                    totaldevice=Double.parseDouble(devicetotal.getText().toString());
                    totalpanic=Double.parseDouble(panictotall.getText().toString());
                    totalcorrier=Double.parseDouble(corriartotal.getText().toString());
                    totalll=(totaldevice + totalpanic + totalcorrier);
                    String total2 = new Double(totalll).toString();
                    grandtotl.setText(total2);
                }catch (Exception e){

                }
            }
        });

        corriartotal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                try {
                    totaldevice=Double.parseDouble(devicetotal.getText().toString());
                    totalpanic=Double.parseDouble(panictotall.getText().toString());
                    totalcorrier=Double.parseDouble(corriartotal.getText().toString());
                    totalll=(totaldevice + totalpanic + totalcorrier);
                    String total2 = new Double(totalll).toString();
                    grandtotl.setText(total2);
                }catch (Exception e){

                }
            }
        });

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (grandtotl.length() == 0) {
                    Toast.makeText(PurchaseActivity.this, "please enter quantity of device", Toast.LENGTH_SHORT).show();
                } else {
                    String value = grandtotl.getText().toString();
                    String value1 = devicequantity.getText().toString();
                    String value2 = panicbtnquantity.getText().toString();
                    String value3 = devicetotal.getText().toString();
                    String value4 = panictotal.getText().toString();
                    String value5 = corriartotal.getText().toString();
                    String value6 = grandtotl.getText().toString();
                    String value7 = perpurchaseamnt.getText().toString();
                    System.out.println(value7);
                    System.out.println(value2);
                    String value8 = panictotall.getText().toString();

                    Intent intent = new Intent(PurchaseActivity.this, PaytmActivity.class);
                    intent.putExtra("key", value);
                    intent.putExtra("key1", value1);
                    intent.putExtra("key2", value2);
                    intent.putExtra("key3", value3);
                    intent.putExtra("key4", value4);
                    intent.putExtra("key5", value5);
                    intent.putExtra("key6", value6);
                    intent.putExtra("key7", value7);
                    System.out.println(value7);
                    Log.d(TAG, "onClick: value7"+value7);
                    intent.putExtra("key8", value8);
                    System.out.println(value8);

                    startActivity(intent);
                }
            }
        });

    }
    public void hideKeybpard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
    }
}





