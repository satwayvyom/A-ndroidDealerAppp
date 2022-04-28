package com.Satway.gpstracker;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import android.graphics.Color;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
//
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.Satway.gpstracker.model.UserDetailsResponse;
import com.Satway.gpstracker.ui.AutoSuggestAdapter;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpResponse;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
//
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//Device activity page
public class DeviceActivity extends AppCompatActivity{
    private static final String TAG = "DeviceActivity";
    SharedPreferences sharedPreferences;
     SharedPreferences dealername1;
    AutoCompleteTextView autoCompleteTextView3;
    TextView textView, textbsnl, textvoda, textView2, txtinput1,txtinput2, simpleToggleButton1, txtview;
    Button txtreboot, txttilt,trest,txtswitch,txtsystem;
    TextView txtpositionsettings, active,ignition,running,batterytxt,dummy,firmwareversiontxt,currentsimtxt;
    Button toggleButton1, toggleButton2;
    Animation scaleup, scaledown;
    Button mapbutton,serverdialogcancelbutton;
    ImageButton imgbtn, imeibutton;
    CardView serverdialog;
    ProgressBar progressBar;
    CardView blur;

    TextView systemupdatetxtview,locationTrans,location;
    Button maptrans;
    ImageButton imgtrans;

    String stringAutocomplete;
    Animation anim  = new AlphaAnimation(0.0f, 1.0f);

    int error_no;
    int succes_no;

    int no_of_networkcall=0;




    final String imei = "http://68.183.92.233:8000/api/imeifetch_dealer_android";
    final String url1 ="http://68.183.92.233:8000/api/android_imei_dealer";
    final String url3="http://68.183.92.233:8000/api/android_commands";
    final String url78="http://68.183.92.233:8000/api/vehicle_location";
    final String urlfirwaredata="http://68.183.92.233:8000/api/getfirmware";



     List <String> imeiList1 = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device);
        dummy=findViewById(R.id.dummy);
        textbsnl = (TextView)findViewById(R.id.textView6);
        textvoda = (TextView)findViewById(R.id.textView7);
        toggleButton1 = (Button)findViewById(R.id.toggleButton);
        toggleButton2 = (Button)findViewById(R.id.toggleButton2);
        txtreboot = (Button) findViewById(R.id.okreset);
        txtpositionsettings = (TextView) findViewById(R.id.okposition);
        txttilt =(Button) findViewById(R.id.oktilt);
        trest = (Button) findViewById(R.id.okreset);
        textView2 =(TextView)findViewById(R.id.textView2);
        txtinput1 = (TextView)findViewById(R.id.textView6);
        txtinput2 = (TextView)findViewById(R.id.textView7);
        mapbutton = (Button) findViewById(R.id.map);
//        simpleToggleButton1 = (TextView) findViewById(R.id.textView0);
        imgbtn = (ImageButton) findViewById(R.id.imageButton2);
        autoCompleteTextView3=findViewById(R.id.imeis);
        imeibutton = findViewById(R.id.imeiBtn);
        txtswitch = findViewById(R.id.switchcommandbtn);
        txtsystem = findViewById(R.id.systemupdatebtn);
        batterytxt=findViewById(R.id.battery);
        batterytxt.setSingleLine(false);
        active=findViewById(R.id.textView103);
        active.setSingleLine(false);
//        ignition=findViewById(R.id.textView101);
        running=findViewById(R.id.textView102);
        running.setSingleLine(false);
        scaleup = AnimationUtils.loadAnimation(DeviceActivity.this,R.anim.scale_up);
        scaledown = AnimationUtils.loadAnimation(DeviceActivity.this,R.anim.scale_down);
        firmwareversiontxt=findViewById(R.id.firmwareversion);
        currentsimtxt=findViewById(R.id.currentsim);

        systemupdatetxtview=findViewById(R.id.systemupdate);
        locationTrans=findViewById(R.id.locationTrans);
        maptrans=findViewById(R.id.maptrans);
        imgtrans=findViewById(R.id.imgtrans);
        location=findViewById(R.id.textView20);
         serverdialog = findViewById(R.id.servererrordialog);
         serverdialogcancelbutton=findViewById(R.id.serverdialogcancel);
        progressBar = findViewById(R.id.progressBar);
        blur = findViewById(R.id.blur);






      sharedPreferences=getSharedPreferences("cridential", Context.MODE_PRIVATE);
        textView =(TextView) findViewById(R.id.textView3);

        dealername1 = getSharedPreferences("cridential", Context.MODE_PRIVATE);
        final String hellos = dealername1.getString("dealername", "");
        System.out.println(hellos);





        getDropDown();
        imeibutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                error_no=0;
                succes_no=0;
                blur.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.VISIBLE);
                textbsnl.setText("");
                textvoda.setText("");
                active.setText("");
                running.setText("");
                batterytxt.setText("");
                txtpositionsettings.setText("");
                currentsimtxt.setText("");
                anim.cancel();
                firmwareversiontxt.setText("");
                dummy.setVisibility(View.GONE);
                txtsystem.setVisibility(View.GONE);

                hideKeybpard(autoCompleteTextView3);







                if(!(autoCompleteTextView3.getText().toString().equals("")))
                {
                    Log.d("jishnu","autocomplete text view is not empty");

                    Log.d("jishnu","cerrorno @ on click"+error_no);

                    blur.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.VISIBLE);


//                    ConnectivityManager conMgr = (ConnectivityManager) getSystemService (Context.CONNECTIVITY_SERVICE);
//                    // ARE WE CONNECTED TO THE NET
//                    if (conMgr.getActiveNetworkInfo() != null) {
//                        error_no++;
//                        Log.d("jishnu","connection was set to ok"+error_no);
//
//                    }
//                    else {
//                        Log.d("jishnu", "network is not connected");
//                        progressBar.setVisibility(View.GONE);
//                        blur.setVisibility(View.GONE);
//
//                        Toast.makeText(DeviceActivity.this,"Please Check Your Internet Connection",Toast.LENGTH_SHORT).show();
//                    }

//                    request2();
                    stringAutocomplete = autoCompleteTextView3.getText().toString();
                    getdata(stringAutocomplete);
//                    request3();

                    System.out.println("ddf" + autoCompleteTextView3.getText().toString() + "-------------------------------------------");

                    System.out.println(stringAutocomplete);
//                    request4();












//
                }
                else {
                    blur.setVisibility(View.GONE);
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(DeviceActivity.this,"Please Enter A Valid  IMEI",Toast.LENGTH_SHORT).show();
                }
            }









        });



        toggleButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (autoCompleteTextView3.getText().toString().isEmpty()) {
                    Toast.makeText(DeviceActivity.this, "Provide Imei Number", Toast.LENGTH_SHORT).show();
                } else {

                    toggleButton1.startAnimation(scaleup);

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DeviceActivity.this);
                    alertDialogBuilder.setTitle("Are you sure wants to ON emergency status?");
                    alertDialogBuilder
                            .setCancelable(false)
                            .setPositiveButton("Yes",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {


                                            Toast toast = Toast.makeText(DeviceActivity.this, "Emergency Status On", Toast.LENGTH_SHORT);
                                            toast.setGravity(Gravity.CENTER, 0, 0);
                                            toast.show();
                                            RequestQueue MyRequestQueue = Volley.newRequestQueue(DeviceActivity.this);

                                            final StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url3, new com.android.volley.Response.Listener<String>() {
                                                @Override

                                                public void onResponse(String response) {
//
                                                }


                                            }, new com.android.volley.Response.ErrorListener() {
                                                @Override
                                                public void onErrorResponse(VolleyError error) {

                                                }
                                            }) {
                                                protected Map<String, String> getParams() {
                                                    Map<String, String> MyData = new HashMap<String, String>();
                                                    MyData.put("command", "1");
                                                    MyData.put("imei_c", stringAutocomplete);
                                                    return MyData;
                                                }
                                            };
                                            MyRequestQueue.add(MyStringRequest);

                                        }
                                    })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    dialog.cancel();

                                }
                            });
                    AlertDialog alert = alertDialogBuilder.create();

                    alert.setTitle("Are you sure wants to ON emergency status?");
                    alert.show();
                }

            }
        });

        toggleButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (autoCompleteTextView3.getText().toString().isEmpty()) {
                    Toast.makeText(DeviceActivity.this, "Provide Imei Number", Toast.LENGTH_SHORT).show();
                } else {

                    toggleButton2.startAnimation(scaleup);

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DeviceActivity.this);
                    alertDialogBuilder.setTitle("Are you sure wants to OFF emergency status?");
                    alertDialogBuilder
                            .setCancelable(false)
                            .setPositiveButton("Yes",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {

                                            Toast toast = Toast.makeText(DeviceActivity.this, "Emergency Status Off", Toast.LENGTH_SHORT);
                                            toast.setGravity(Gravity.CENTER, 0, 0);
                                            toast.show();
                                            RequestQueue MyRequestQueue = Volley.newRequestQueue(DeviceActivity.this);

                                            final StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url3, new com.android.volley.Response.Listener<String>() {
                                                @Override

                                                public void onResponse(String response) {
                                                    System.out.println(response);
                                                    System.out.println(response);
                                                    System.out.println("-------------------------------------------------");
                                                    Log.d(TAG, "onResponse: respomseCommand" + response);
//
                                                }


                                            }, new com.android.volley.Response.ErrorListener() {
                                                @Override
                                                public void onErrorResponse(VolleyError error) {

                                                }
                                            }) {
                                                protected Map<String, String> getParams() {
                                                    Map<String, String> MyData = new HashMap<String, String>();
                                                    MyData.put("command", "2");
                                                    MyData.put("imei_c", stringAutocomplete);
                                                    return MyData;
                                                }
                                            };
                                            MyRequestQueue.add(MyStringRequest);

                                        }
                                    })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    //  Action for 'NO' Button
                                    dialog.cancel();

                                }
                            });
                    AlertDialog alert = alertDialogBuilder.create();
                    //Setting the title manually
                    alert.setTitle("Are you sure wants to OFF emergency status?");
                    alert.show();
                }
            }
        });

        txttilt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (autoCompleteTextView3.getText().toString().isEmpty()) {
                    Toast.makeText(DeviceActivity.this, "Provide Imei Number", Toast.LENGTH_SHORT).show();
                } else {

                    txttilt.startAnimation(scaleup);
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DeviceActivity.this);
                    alertDialogBuilder.setTitle("Are you sure want to fix tilt alert OR device position?");
                    alertDialogBuilder
                            .setCancelable(false)
                            .setPositiveButton("Yes",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {

                                            RequestQueue MyRequestQueue = Volley.newRequestQueue(DeviceActivity.this);

                                            final StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url3, new com.android.volley.Response.Listener<String>() {
                                                @Override

                                                public void onResponse(String response) {

                                                }


                                            }, new com.android.volley.Response.ErrorListener() {
                                                @Override
                                                public void onErrorResponse(VolleyError error) {

                                                }
                                            }) {
                                                protected Map<String, String> getParams() {
                                                    Map<String, String> MyData = new HashMap<String, String>();
                                                    MyData.put("command", "3");
                                                    MyData.put("imei_c", stringAutocomplete);
                                                    return MyData;
                                                }
                                            };
                                            MyRequestQueue.add(MyStringRequest);


                                        }
                                    })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    //  Action for 'NO' Button
                                    dialog.cancel();

                                }
                            });
                    AlertDialog alert = alertDialogBuilder.create();
                    alert.setTitle("Are you sure want to fix tilt alert OR device position?");
                    alert.show();
                }
            }
        });

        txtreboot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (autoCompleteTextView3.getText().toString().isEmpty()) {
                    Toast.makeText(DeviceActivity.this, "Provide Imei Number", Toast.LENGTH_SHORT).show();
                } else {

                    txtreboot.startAnimation(scaleup);

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DeviceActivity.this);
                    alertDialogBuilder.setTitle("Are you sure want to reboot the device?");
                    alertDialogBuilder
                            .setCancelable(false)
                            .setPositiveButton("Yes",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {

                                            RequestQueue MyRequestQueue = Volley.newRequestQueue(DeviceActivity.this);

                                            final StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url3, new com.android.volley.Response.Listener<String>() {
                                                @Override

                                                public void onResponse(String response) {

                                                }


                                            }, new com.android.volley.Response.ErrorListener() {
                                                @Override
                                                public void onErrorResponse(VolleyError error) {

                                                }
                                            }) {
                                                protected Map<String, String> getParams() {
                                                    Map<String, String> MyData = new HashMap<String, String>();
                                                    MyData.put("command", "4");
                                                    MyData.put("imei_c", stringAutocomplete);
                                                    return MyData;
                                                }
                                            };
                                            MyRequestQueue.add(MyStringRequest);

                                        }
                                    })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    //  Action for 'NO' Button
                                    dialog.cancel();

                                }
                            });
                    AlertDialog alert = alertDialogBuilder.create();
                    //Setting the title manually
                    alert.setTitle("Are you sure want to reboot the device?");
                    alert.show();
                }
            }
        });

        txtswitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (autoCompleteTextView3.getText().toString().isEmpty()) {
                    Toast.makeText(DeviceActivity.this, "Provide Imei Number", Toast.LENGTH_SHORT).show();
                } else {

                    txtswitch.startAnimation(scaleup);
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DeviceActivity.this);
                    alertDialogBuilder.setTitle("Are you sure want to SIM Switch?");
                    alertDialogBuilder
                            .setCancelable(false)
                            .setPositiveButton("Yes",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {

                                            RequestQueue MyRequestQueue = Volley.newRequestQueue(DeviceActivity.this);

                                            final StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url3, new com.android.volley.Response.Listener<String>() {
                                                @Override
                                                public void onResponse(String response) {

                                                }
                                            }, new com.android.volley.Response.ErrorListener() {
                                                @Override
                                                public void onErrorResponse(VolleyError error) {

                                                }
                                            }) {
                                                protected Map<String, String> getParams() {
                                                    Map<String, String> MyData = new HashMap<String, String>();
                                                    MyData.put("command", "5");
                                                    MyData.put("imei_c", stringAutocomplete);
                                                    return MyData;
                                                }
                                            };
                                            MyRequestQueue.add(MyStringRequest);

                                        }
                                    })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    //  Action for 'NO' Button
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert = alertDialogBuilder.create();
                    alert.setTitle("Are you sure want to SIM Switch?");
                    alert.show();
                }
            }
        });

        txtsystem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {





                if (autoCompleteTextView3.getText().toString().isEmpty()) {
                    Toast.makeText(DeviceActivity.this, "Provide Imei Number", Toast.LENGTH_SHORT).show();
                } else {

                    txtsystem.startAnimation(scaleup);
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DeviceActivity.this);
                    alertDialogBuilder.setTitle("Are you sure want Update the System?");
                    alertDialogBuilder
                            .setCancelable(false)
                            .setPositiveButton("Yes",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {

                                            RequestQueue MyRequestQueue = Volley.newRequestQueue(DeviceActivity.this);

                                            final StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url3, new com.android.volley.Response.Listener<String>() {
                                                @Override

                                                public void onResponse(String response) {

                                                }


                                            }, new com.android.volley.Response.ErrorListener() {
                                                @Override
                                                public void onErrorResponse(VolleyError error) {

                                                }
                                            }) {
                                                protected Map<String, String> getParams() {
                                                    Map<String, String> MyData = new HashMap<String, String>();
                                                    MyData.put("command", "8");
                                                    MyData.put("imei_c", stringAutocomplete);
                                                    return MyData;
                                                }
                                            };
                                            MyRequestQueue.add(MyStringRequest);


                                        }
                                    })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    //  Action for 'NO' Button
                                    dialog.cancel();

                                }
                            });
                    AlertDialog alert = alertDialogBuilder.create();
                    alert.setTitle("Are you sure want to Update the System?");
                    alert.show();
                }
            }
        });

        mapbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("jishnu","map button clicked");
                if (autoCompleteTextView3.getText().toString().isEmpty()) {
                    Toast.makeText(DeviceActivity.this, "Provide Imei Number", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(DeviceActivity.this, MapActivity2.class);
                    intent.putExtra("imei_c", autoCompleteTextView3.getText().toString());
                    startActivity(intent);

                }
            }
        });

        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (autoCompleteTextView3.getText().toString().isEmpty()) {
                    Toast.makeText(DeviceActivity.this, "Provide Imei Number", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(DeviceActivity.this, MapActivity2.class);
                    intent.putExtra("imei_c", autoCompleteTextView3.getText().toString());
                    startActivity(intent);
                }
            }
        });
    }

    public void hideKeybpard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
    }

    public void onclick(View view) {
    }


    public void errorResponse()
    {
        Log.d("jishnu","error called respone =="+error_no);
        if(error_no==1)
        {
            serverdialog.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            serverdialogcancelbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    serverdialog.setVisibility(View.GONE);
                    blur.setVisibility(View.GONE);
                }
            });

        }




    }

    public void succesResponse()
    {

            blur.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);

            Log.d("server","success response called");





    }




    public void getDropDown()
    {

        RequestQueue MyRequestQueue1 = Volley.newRequestQueue(DeviceActivity.this);
        final StringRequest MyStringRequest1 = new StringRequest(Request.Method.POST, imei, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    no_of_networkcall=0;

                    Gson GSON = new Gson();
                    UserDetailsResponse userDetailsResponse = new UserDetailsResponse();
                    userDetailsResponse = GSON.fromJson(response,UserDetailsResponse.class);
                    imeiList1 = userDetailsResponse.getImeilist();
                    Log.d(TAG, "onResponse: imeiList"+imeiList1);

                    AutoSuggestAdapter adapter = new AutoSuggestAdapter(DeviceActivity.this, android.R.layout.simple_list_item_1, imeiList1);
                    autoCompleteTextView3.setAdapter(adapter);
                    autoCompleteTextView3.setThreshold(3);
                }
                catch (Exception e){
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("jishnu","amal        "+error.getNetworkTimeMs());
                no_of_networkcall=no_of_networkcall+1;
                Log.d("server","from getDropdown error no="+no_of_networkcall);
                if(no_of_networkcall==20)
                {
                    errorResponse();
                    Log.d("server","from getDropdown");

                }
                else {
                    getDropDown();
                }


            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("dealername", sharedPreferences.getString("dealername",""));
                return MyData;
            }
        };
        MyRequestQueue1.add(MyStringRequest1);
        String autovalue =autoCompleteTextView3.getText().toString();
        Log.d(TAG, "onCreate: autovalue: "+autovalue);


        autoCompleteTextView3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                textbsnl.setText("");
                textvoda.setText("");
                active.setText("");
                running.setText("");
                batterytxt.setText("");
                txtpositionsettings.setText("");
                anim.cancel();
                firmwareversiontxt.setText("");
                dummy.setVisibility(View.GONE);
                txtsystem.setVisibility(View.GONE);
                currentsimtxt.setText("");

            }
        });



    }


    public void getdata(String imei)
    {
        Log.d("jishnu",imei);

        Call<DeviceActivityPOJO> call =RetrofitDeviceActivity.getInstance().getMyApi().getdevicedata(imei.toString());
        call.enqueue(new Callback<DeviceActivityPOJO>() {
            @Override
            public void onResponse(Call<DeviceActivityPOJO> call, Response<DeviceActivityPOJO> response) {
                Log.d("jishnu","success..................................");

                DeviceActivityPOJO data= response.body();

                if(response.body().bsnl.equals("none"))
                {
                    Toast.makeText(getApplicationContext(),"Enter A Valid Imei",Toast.LENGTH_SHORT);
                    blur.setVisibility(View.GONE);
                    progressBar.setVisibility(View.GONE);
                }
                else {
                    setdata(data);
                    blur.setVisibility(View.GONE);
                    progressBar.setVisibility(View.GONE);
                }


                Log.d("jishnu",data.vi.toString());
            }

            @Override
            public void onFailure(Call<DeviceActivityPOJO> call, Throwable t) {
                Log.d("jishnu","failure");
            }
        });



    }

    public void setdata(DeviceActivityPOJO data)
    {

        Log.d("jishnu",data.bsnl+".......................................+++++");
        Log.d("jishnu",data.last_date_time+"...........................+++++++++");

        textbsnl.setText(data.bsnl);
        textvoda.setText(data.vi);
        Log.d("jishnu",data.renewal_date);
        String dateRevenual =data.renewal_date;
        if (!dateRevenual.equals("")) {
            Date c = Calendar.getInstance().getTime();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            String formattedDate = df.format(c);

            try {
                String CurrentDate = formattedDate;
                String FinalDate = dateRevenual;
                Date date1;
                Date date2;
                SimpleDateFormat dates = new SimpleDateFormat("yyyy-MM-dd");
                date1 = dates.parse(CurrentDate);
                date2 = dates.parse(FinalDate);
                long difference = Math.abs(date1.getTime() - date2.getTime());
                long differenceDates = difference / (24 * 60 * 60 * 1000);
                String dayDifference = Long.toString(differenceDates);

                int number = Integer.parseInt(dayDifference);
                System.out.println(number);
                int number1 = 365;
                int dat = Math.abs(number1 - number);
                System.out.println(dat);

                if (dat > 0) {
                    active.setText("SIM \n ACTIVE");
                    active.setTextColor(Color.parseColor("#2eb82e"));
                } else {
                    active.setText("SIM \n INACTIVE");
                    active.setTextColor(Color.parseColor("#ff0000"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }





        }



        String primaryNumber1 = data.ignition;
        Log.d("jishnu","it is "+primaryNumber1);
        if (primaryNumber1 .equals("1")) {
            running.setText("IGNITION\n"+"ON");
            running.setTextColor(Color.parseColor("#2eb82e"));
        } else {
            running.setText("IGNITION\n"+"OFF");
            running.setTextColor(Color.parseColor("#ff0000"));
        }



        String battery = data.mains_connected;
        Log.d("jishnu","battery="+battery);
        if (battery.equals("1")) {
            batterytxt.setText("MAINS\n"+"CONNECTED");
            batterytxt.setTextColor(Color.parseColor("#2eb82e"));
        } else {
            batterytxt.setText("MAINS\n"+"DISCONNECTED");
            batterytxt.setTextColor(Color.parseColor("#ff0000"));
        }



        String lastTime = data.last_date_time;
        txtpositionsettings.setText(lastTime);

        anim.setDuration(2000); //You can manage the blinking time with this parameter
        anim.setStartOffset(-700);
        anim.getFillBefore();
        anim.setRepeatMode(Animation.RESTART);
        anim.setRepeatCount(Animation.INFINITE);
        txtpositionsettings.startAnimation(anim);




        String firmwaredeviceversion = data.firmware_version;
        String latestfirmware = data.latest_firmware;
        String currentsim = data.current_sim;


        if (firmwaredeviceversion.equals("TRANS")) {
            systemupdatetxtview.setVisibility(View.GONE);
            firmwareversiontxt.setVisibility(View.GONE);
            locationTrans.setVisibility(View.VISIBLE);
            maptrans.setVisibility(View.VISIBLE);
            imgtrans.setVisibility(View.VISIBLE);
            mapbutton.setVisibility(View.GONE);
            imgbtn.setVisibility(View.GONE);
            location.setVisibility(View.GONE);


        } else {
            systemupdatetxtview.setVisibility(View.VISIBLE);
            firmwareversiontxt.setVisibility(View.VISIBLE);
            locationTrans.setVisibility(View.GONE);
            maptrans.setVisibility(View.GONE);
            imgtrans.setVisibility(View.GONE);
            mapbutton.setVisibility(View.VISIBLE);
            imgbtn.setVisibility(View.VISIBLE);
            location.setVisibility(View.VISIBLE);
            firmwareversiontxt.setText(firmwaredeviceversion);
            if (!firmwaredeviceversion.equals(latestfirmware)) {
                txtsystem.setVisibility(View.VISIBLE);
                dummy.setVisibility(View.VISIBLE);
            }

        }


        if (currentsim.equals("Vodafone Mobile")) {
            currentsimtxt.setText("VI SIM");
        } else if (currentsim.equals("BSNL Mobile")) {
            currentsimtxt.setText("BSNL SIM");
        } else {
            currentsimtxt.setText("Not Available");
        }

        Log.d("jishnu", firmwaredeviceversion);
       Button map1= (Button) findViewById(R.id.maptrans);


        map1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("jishnu","map button clicked");
                if (autoCompleteTextView3.getText().toString().isEmpty()) {
                    Toast.makeText(DeviceActivity.this, "Provide Imei Number", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(DeviceActivity.this, MapActivity2.class);
                    intent.putExtra("imei_c", autoCompleteTextView3.getText().toString());
                    startActivity(intent);

                }
            }
        });



    }



}


























































