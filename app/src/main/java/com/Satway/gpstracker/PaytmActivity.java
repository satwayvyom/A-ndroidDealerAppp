package com.Satway.gpstracker;
//
//import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
//import androidx.room.FtsOptions;

import android.app.Activity;
//import android.app.AlertDialog;
//import android.app.ProgressDialog;
import android.content.Context;
//import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
//import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
//import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

// purchase payment activity

public class PaytmActivity extends AppCompatActivity {

    TextView amount, name, upivirtualid;
    Button send;
    String TAG = "main";
    final int UPI_PAYMENT = 0;
    SharedPreferences sharedPreferences;
    String value;
    String value1 ;
    String value2 ;
    String value3 ;
    String value4 ;
    String value5 ;
    String value6 ;
    String value7 ;
    String value8 ;

//    String GOOGLE_PAY_PACKAGE_NAME = "com.google.android.apps.nbu.paisa.user";
//    int GOOGLE_PAY_REQUEST_CODE = 123;
//
//    TextView amount, name, upivirtualid;
//    Button send;
//    String TAG = "main";
//    final int UPI_PAYMENT = 0;
//    SharedPreferences sharedPreferences;
//    String value;
//    String value1 ;
//    String value2 ;
//    String value3 ;
//    String value4 ;
//    String value5 ;
//    String value6 ;
//    String value7 ;
//    String value8 ;
//    String value9;
//    String imeikeys1;
//    String iccidkeys1;
//    String uinkeys1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paytm);


        sharedPreferences=getSharedPreferences("cridential", Context.MODE_PRIVATE);
        send =  findViewById(R.id.send);
        amount =  findViewById(R.id.amount_et);
        name =  findViewById(R.id.name);
        upivirtualid =  findViewById(R.id.upi_id);

        value = getIntent().getStringExtra("key");
        value1 = getIntent().getStringExtra("key1");
        value2 = getIntent().getStringExtra("key2");
        value3 = getIntent().getStringExtra("key3");
        value4 = getIntent().getStringExtra("key4");
        value5 = getIntent().getStringExtra("key5");
        value6 = getIntent().getStringExtra("key6");
        value7 = getIntent().getStringExtra("key7");
        value8 = getIntent().getStringExtra("key8");
        amount.setText(value);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Getting the values from the EditTexts
                if (TextUtils.isEmpty(name.getText().toString().trim())){
                    Toast.makeText(PaytmActivity.this," Name is invalid", Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(upivirtualid.getText().toString().trim())){
                    Toast.makeText(PaytmActivity.this," UPI ID is invalid", Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(amount.getText().toString().trim())){
                    Toast.makeText(PaytmActivity.this," Amount is invalid", Toast.LENGTH_SHORT).show();
                }else {
                    payUsingUpi(name.getText().toString(), upivirtualid.getText().toString(), amount.getText().toString());


                }
            }
        });
    }
    void payUsingUpi(  String name,String upiId, String amount) {
        Log.e("main ", "name "+name +"--up--"+upiId+"--"+amount);
        Uri uri = Uri.parse("upi://pay").buildUpon()
                .appendQueryParameter("pa", upiId)
                .appendQueryParameter("pn", name)
                .appendQueryParameter("am", amount)
                .appendQueryParameter("cu", "INR")
                .build();
        Intent upiPayIntent = new Intent(Intent.ACTION_VIEW);
        upiPayIntent.setData(uri);
        // will always show a dialog to user to choose an app
        Intent chooser = Intent.createChooser(upiPayIntent, "Pay with");
        // check if intent resolves
        if(null != chooser.resolveActivity(getPackageManager())) {
            startActivityForResult(chooser, UPI_PAYMENT);
        } else {
            Toast.makeText(PaytmActivity.this,"No UPI app found, please install one to continue",Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("main ", "response "+resultCode );

        switch (requestCode) {
            case UPI_PAYMENT:
                if ((RESULT_OK == resultCode) || (resultCode == 11)) {
                    if (data != null) {
                        String trxt = data.getStringExtra("response");
                        Log.e("UPI", "onActivityResult: " + trxt);
                        ArrayList<String> dataList = new ArrayList<>();
                        dataList.add(trxt);
                        upiPaymentDataOperation(dataList);
                    } else {
                        Log.e("UPI", "onActivityResult: " + "Return data is null");
                        ArrayList<String> dataList = new ArrayList<>();
                        dataList.add("nothing");
                        upiPaymentDataOperation(dataList);
                    }
                } else {
                    Log.e("UPI", "onActivityResult: " + "Return data is null");
                    ArrayList<String> dataList = new ArrayList<>();
                    dataList.add("nothing");
                    upiPaymentDataOperation(dataList);
                }
                break;
        }
    }
    private void upiPaymentDataOperation(ArrayList<String> data) {
        if (isConnectionAvailable(PaytmActivity.this)) {
            String str = data.get(0);
            Log.e("UPIPAY", "upiPaymentDataOperation: "+str);
            String paymentCancel = "";
            if(str == null) str = "discard";
            String status = "";
            String approvalRefNo = "";
            String response[] = str.split("&");
            for (int i = 0; i < response.length; i++) {
                String equalStr[] = response[i].split("=");
                if(equalStr.length >= 2) {
                    if (equalStr[0].toLowerCase().equals("Status".toLowerCase())) {
                        status = equalStr[1].toLowerCase();
                    }
                    else if (equalStr[0].toLowerCase().equals("ApprovalRefNo".toLowerCase()) || equalStr[0].toLowerCase().equals("txnRef".toLowerCase())) {
                        approvalRefNo = equalStr[1];
                    }
                }
                else {
                    paymentCancel = "Payment cancelled by user.";
                }
            }
            if (status.equals("success")) {
                //Code to handle successful transaction here.
                Toast.makeText(PaytmActivity.this, "Transaction successful.", Toast.LENGTH_SHORT).show();
                Log.e("UPI", "payment successfull: "+approvalRefNo);

                Intent intent = new Intent(PaytmActivity.this,reciept.class);
                intent.putExtra("key", value);
                intent.putExtra("key1", value1);
                intent.putExtra("key2", value2);
                intent.putExtra("key3", value3);
                intent.putExtra("key4", value4);
                intent.putExtra("key5", value5);
                intent.putExtra("key6", value6);
                intent.putExtra("key7", value7);
                intent.putExtra("key8", value8);
                startActivity(intent);
            }
            else if("Payment cancelled by user.".equals(paymentCancel)) {

            }
            else {
                Toast.makeText(PaytmActivity.this, "Transaction failed.Please try again", Toast.LENGTH_SHORT).show();
                Log.e("UPI", "failed payment: "+approvalRefNo);
            }
        } else {
            Log.e("UPI", "Internet issue: ");
            Toast.makeText(PaytmActivity.this, "Internet connection is not available. Please check and try again", Toast.LENGTH_SHORT).show();
        }
    }
    public static boolean isConnectionAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnected()
                    && netInfo.isConnectedOrConnecting()
                    && netInfo.isAvailable()) {
                return true;
            }
        }
        return false;
    }

    public void hideKeybpard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
























//
//        sharedPreferences=getSharedPreferences("cridential", Context.MODE_PRIVATE);
//        send =  findViewById(R.id.send);
//        amount =  findViewById(R.id.amount_et);
//        name =  findViewById(R.id.name);
//        upivirtualid =  findViewById(R.id.upi_id);
//        value = getIntent().getStringExtra("key");
//        value1 = getIntent().getStringExtra("key1");
//        value2 = getIntent().getStringExtra("key2");
//        value3 = getIntent().getStringExtra("key3");
//        value4 = getIntent().getStringExtra("key4");
//        value5 = getIntent().getStringExtra("key5");
//        value6 = getIntent().getStringExtra("key6");
//        value7 = getIntent().getStringExtra("key7");
//        value8 = getIntent().getStringExtra("key8");
//        value9 = getIntent().getStringExtra("methodkey");
//        imeikeys1 = getIntent().getStringExtra("imeikey");
//        iccidkeys1 = getIntent().getStringExtra("iccidkey");
//        uinkeys1 = getIntent().getStringExtra("upikey");
//        amount.setText(value);
//        send.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //Getting the values from the EditTexts
//                if (TextUtils.isEmpty(name.getText().toString().trim())){
//                    Toast.makeText(PaytmActivity.this," Name is invalid", Toast.LENGTH_SHORT).show();
//                }else if (TextUtils.isEmpty(upivirtualid.getText().toString().trim())){
//                    Toast.makeText(PaytmActivity.this," UPI ID is invalid", Toast.LENGTH_SHORT).show();
//                }else if (TextUtils.isEmpty(amount.getText().toString().trim())){
//                    Toast.makeText(PaytmActivity.this," Amount is invalid", Toast.LENGTH_SHORT).show();
//                }else {
//                    payUsingUpi(name.getText().toString(), upivirtualid.getText().toString(), amount.getText().toString());
//
//
//                }
//            }
//        });
//    }
//    void payUsingUpi(  String name,String upiId, String amount) {
//        Log.e("main ", "name "+name +"--up--"+upiId+"--"+amount);
//        Uri uri = Uri.parse("upi://pay").buildUpon()
//                .appendQueryParameter("pa", upiId)
//                .appendQueryParameter("pn", name)
//                .appendQueryParameter("am", amount)
//                .appendQueryParameter("cu", "INR")
//                .build();
//        Intent upiPayIntent = new Intent(Intent.ACTION_VIEW);
//        upiPayIntent.setData(uri);
//
////        upiPayIntent.setPackage(GOOGLE_PAY_PACKAGE_NAME);
////        startActivityForResult(upiPayIntent,GOOGLE_PAY_REQUEST_CODE);
//        Intent choose =Intent.createChooser(upiPayIntent,GOOGLE_PAY_PACKAGE_NAME);
//        Intent chooser = Intent.createChooser(upiPayIntent, "Pay with");
//        if(null != chooser.resolveActivity(getPackageManager())) {
//            startActivityForResult(chooser, UPI_PAYMENT);
//        } else if(null!=choose.resolveActivity(getPackageManager())) {
//            startActivityForResult(upiPayIntent, GOOGLE_PAY_REQUEST_CODE);
//        }else {
//            Toast.makeText(PaytmActivity.this,"No UPI app found, please install one to continue",Toast.LENGTH_SHORT).show();
//        }
//    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        Log.e("main ", "response "+resultCode );
//
//        switch (requestCode) {
//            case UPI_PAYMENT:
//                if ((RESULT_OK == resultCode) || (resultCode == 11)) {
//                    if (data != null) {
//                        String trxt = data.getStringExtra("response");
//                        Log.e("UPI", "onActivityResult: " + trxt);
//                        ArrayList<String> dataList = new ArrayList<>();
//                        dataList.add(trxt);
//                        upiPaymentDataOperation(dataList);
//                    } else {
//                        Log.e("UPI", "onActivityResult: " + "Return data is null");
//                        ArrayList<String> dataList = new ArrayList<>();
//                        dataList.add("nothing");
//                        upiPaymentDataOperation(dataList);
//                    }
//                } else {
//                    Log.e("UPI", "onActivityResult: " + "Return data is null");
//                    ArrayList<String> dataList = new ArrayList<>();
//                    dataList.add("nothing");
//                    upiPaymentDataOperation(dataList);
//                }
//                break;
//        }
//    }
//    private void upiPaymentDataOperation(ArrayList<String> data) {
//        if (isConnectionAvailable(PaytmActivity.this)) {
//            String str = data.get(0);
//            Log.e("UPIPAY", "upiPaymentDataOperation: "+str);
//            String paymentCancel = "";
//            if(str == null) str = "discard";
//            String status = "";
//            String approvalRefNo = "";
//            String response[] = str.split("&");
//            for (int i = 0; i < response.length; i++) {
//                String equalStr[] = response[i].split("=");
//                if(equalStr.length >= 2) {
//                    if (equalStr[0].toLowerCase().equals("Status".toLowerCase())) {
//                        status = equalStr[1].toLowerCase();
//                    }
//                    else if (equalStr[0].toLowerCase().equals("ApprovalRefNo".toLowerCase()) || equalStr[0].toLowerCase().equals("txnRef".toLowerCase())) {
//                        approvalRefNo = equalStr[1];
//                    }
//                }
//                else {
//                    paymentCancel = "Payment cancelled by user.";
//                }
//            }
//            if (status.equals("success")) {
//
////                if (value9.equals("0")){
////                    Toast.makeText(PaytmActivity.this, "Transaction successful.", Toast.LENGTH_SHORT).show();
////                    Log.e("UPI", "payment successfull: "+approvalRefNo);
////                    Intent intent = new Intent(PaytmActivity.this,Renewalreceipt.class);
////                    intent.putExtra("key", value);
////                    intent.putExtra("key1", value1);
////                    intent.putExtra("key2", value2);
////                    intent.putExtra("key3", value3);
////                    intent.putExtra("key4", value4);
////                    intent.putExtra("key5", value5);
////                    intent.putExtra("key6", value6);
////                    intent.putExtra("key7", value7);
////                    intent.putExtra("key8", value8);
////                    intent.putExtra("key9", value9);
////                    imeikeys1 = getIntent().getStringExtra("imeikey1");
////                    iccidkeys1 = getIntent().getStringExtra("iccidkey1");
////                    uinkeys1 = getIntent().getStringExtra("upikey1");
////                    startActivity(intent);
////
////                }else {
//
//
//                    //Code to handle successful transaction here.
//                    Toast.makeText(PaytmActivity.this, "Transaction successful.", Toast.LENGTH_SHORT).show();
//                    Log.e("UPI", "payment successfull: " + approvalRefNo);
//
//                    Intent intent = new Intent(PaytmActivity.this, reciept.class);
//                    intent.putExtra("key", value);
//                    intent.putExtra("key1", value1);
//                    intent.putExtra("key2", value2);
//                    intent.putExtra("key3", value3);
//                    intent.putExtra("key4", value4);
//                    intent.putExtra("key5", value5);
//                    intent.putExtra("key6", value6);
//                    intent.putExtra("key7", value7);
//                    intent.putExtra("key8", value8);
//                    startActivity(intent);
////                }
//            }
//            else if("Payment cancelled by user.".equals(paymentCancel)) {
//
//            }
//            else {
//                Toast.makeText(PaytmActivity.this, "Transaction failed.Please try again", Toast.LENGTH_SHORT).show();
//                Log.e("UPI", "failed payment: "+approvalRefNo);
//            }
//        } else {
//            Log.e("UPI", "Internet issue: ");
//            Toast.makeText(PaytmActivity.this, "Internet connection is not available. Please check and try again", Toast.LENGTH_SHORT).show();
//        }
//    }
//    public static boolean isConnectionAvailable(Context context) {
//        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        if (connectivityManager != null) {
//            NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
//            if (netInfo != null && netInfo.isConnected()
//                    && netInfo.isConnectedOrConnecting()
//                    && netInfo.isAvailable()) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public void hideKeybpard(View view) {
//        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
//        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
//    }
//}









































