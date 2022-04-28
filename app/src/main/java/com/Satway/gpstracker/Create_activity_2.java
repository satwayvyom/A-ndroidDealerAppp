package com.Satway.gpstracker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
//import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
//
//import com.Satway.gpstracker.model.UserData;
import com.Satway.gpstracker.model.UserDetailsResponse;
import com.Satway.gpstracker.ui.AutoSuggestAdapter;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
//
//import timber.log.Timber;

import static android.content.Intent.createChooser;

//user details activity  page

public class Create_activity_2 extends AppCompatActivity {
    private static final String TAG = "Create_activity_2";
    SharedPreferences sharedPreferences;
    String userName;
    String phnone;
    String emailno;
    String passno;
    String methods;
    String imeLists;
    Calendar c;
    String mrthodvals;
    EditText vehicleNumber, odometer;
    TextView dateTXT;
    ImageButton btn1, btn2, btn3;
    ImageView imageView1, imageView2, imageView3, cal;
    AutoCompleteTextView autoCompleteTextView1;
//    Spinner spl;
    String getdatevals;
    String getmethodsval;
    String getmethodvals11;
    Dialog dialog;
    String x, y;
    String dealname, valueofdate;
    String dateValue;
    private int mDate, mMonth, mYear;
    String imageString1 = "", imageString2 = "", imageString3 = "";
    Bitmap photo, photo1, photo2, photo3, photo4, photo5;
    String values, vehicle, date, imei,odo0;
    VehicleAdapter adapter;
//    String datestring,vehiclestringle,autocompleteimeistring;
    Button submitbtn;
//    String[] names = {"Bus", "Car", "Lorry", "Bike"};
//    int[] images = {R.drawable.satwaybuzz, R.drawable.caratway, R.drawable.truckatway, R.drawable.bycicle};
    final String url1 = "http://144.126.252.202/api/postdata_dealer_android";
    final List<String> stringList1 = new ArrayList<String>();
    List<String> imeiList = new ArrayList<>();
    String nameuser=userName;
    String passuser=passno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_2);

        final Intent intent = getIntent();
        userName = intent.getStringExtra("username");
        System.out.println(userName);
        phnone = getIntent().getStringExtra("phone");
        System.out.println(phnone);
        emailno = getIntent().getStringExtra("email");
        System.out.println(emailno);
        passno = getIntent().getStringExtra("password");
        System.out.println(passno);
        methods = getIntent().getStringExtra("methodId");
        System.out.println(methods+"methods");
        mrthodvals=methods;
        System.out.println(mrthodvals+"/////////////////////");
        imeLists = getIntent().getStringExtra("userandzimeiList");
        System.out.println(imeLists);

        Gson GSON = new Gson();
        UserDetailsResponse userDetailsResponse = new UserDetailsResponse();
        userDetailsResponse = GSON.fromJson(imeLists,UserDetailsResponse.class);
        imeiList = userDetailsResponse.getImeilist();

        submitbtn = findViewById(R.id.submitbtn);
        btn1 = findViewById(R.id.adharBtn);
        imageView1 = findViewById(R.id.AdharPic);
        btn2 = findViewById(R.id.RcBtn);
        imageView2 = findViewById(R.id.RcPic);
        imageView3 = findViewById(R.id.VehiclePic);
        btn3 = findViewById(R.id.VehicleBtn);
//        spl = findViewById(R.id.spinner);
        dateTXT=findViewById(R.id.date);
        cal = findViewById(R.id.datepicker);
        autoCompleteTextView1 = (AutoCompleteTextView) findViewById(R.id.imei);
       vehicleNumber=findViewById(R.id.vehicleNo);
       odometer=findViewById(R.id.odo);
        dialog = new Dialog(Create_activity_2.this);
//        adapter = new VehicleAdapter(this, names, images);
//        spl.setAdapter(adapter);
        sharedPreferences = getSharedPreferences("cridential", Context.MODE_PRIVATE);
        dealname=sharedPreferences.getString("dealername","");

//        imeishare = getSharedPreferences("cridential", Context.MODE_PRIVATE);
//        String imeisharedvalue=imeishare.getString("imei","");

        final AutoSuggestAdapter adapt = new AutoSuggestAdapter(Create_activity_2.this, android.R.layout.simple_dropdown_item_1line, imeiList);
            autoCompleteTextView1.setAdapter(adapt);
            autoCompleteTextView1.setThreshold(1);
            adapt.notifyDataSetChanged();

            autoCompleteTextView1.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });


        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { final Calendar cal = Calendar.getInstance();
                mDate = cal.get(Calendar.DATE);
                mMonth = cal.get(Calendar.MONTH);
                mYear = cal.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(Create_activity_2.this, android.R.style.Theme_DeviceDefault_Dialog, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month+1;

                        if (month<10)
                        {
                            dateTXT.setText(year + "-" +"0"+ month + "-" + dayOfMonth);
                            valueofdate=dateTXT.getText().toString();
                            getdatevals=valueofdate;
                            System.out.println(valueofdate+"====================");
                            System.out.println(getdatevals+"====================");
                        }
                        else
                            dateTXT.setText(year + "-" + month + "-" + dayOfMonth);
                        valueofdate=dateTXT.getText().toString();
                        System.out.println(valueofdate+"+++++++++++++++++++++");
                    }
                }, mYear, mMonth, mDate);
                datePickerDialog.show();
            }
        });

        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                autoCompleteTextView1.getText().toString().replace(" ", "");
                System.out.println(autoCompleteTextView1.getText().toString()+ " "+autoCompleteTextView1.length()+"autoCompleteTextView1");
                vehicleNumber.getText().toString().replace(" ", "");
                System.out.println(vehicleNumber.getText().toString()+"vehicleNumber");
                odo0=odometer.getText().toString();
                System.out.println(odo0);
                getdatevals=dateTXT.getText().toString();
                phnone.replace(" ", "");
                System.out.println(phnone+"phnone");
                emailno.replace(" ", "");
                System.out.println(emailno+"emailno");
                userName.replace(" ", "");
                System.out.println(userName+"userName");
                passno.replace(" ", "");
                System.out.println(passno+"passno");

                  if (odometer.equals("") ){
                    odometer.setText("0");
                    odo0=odometer.getText().toString();
                      Log.d(TAG, "odometer: "+ odo0);
                }else {
                    String strArray = odometer.getText().toString().replaceAll("\\s+", "");;
                    odometer.setText(strArray);
                    odo0=odometer.getText().toString();
                      Log.d(TAG, "odometer: "+ odo0);
                }

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                if (photo != null) {
                    Bitmap resized = Bitmap.createScaledBitmap(photo, 40, 40, true);
                    resized.compress(Bitmap.CompressFormat.JPEG, 10, baos);
                    byte[] imageBytes = baos.toByteArray();
                    imageString1 = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                } else if (photo == null) {
                    imageString1 = "";

                }
                if (photo1 != null) {
                    Bitmap resized = Bitmap.createScaledBitmap(photo1, 40, 40, true);
                    resized.compress(Bitmap.CompressFormat.JPEG, 10, baos);
                    byte[] imageBytes = baos.toByteArray();
                    imageString1 = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                } else if (photo1 == null && imageString1.equals("")) {
                    imageString1 = "-";
                }
                if (photo2 != null) {
                    Bitmap resized = Bitmap.createScaledBitmap(photo2, 40, 40, true);
                    resized.compress(Bitmap.CompressFormat.JPEG, 10, baos);
                    byte[] imageBytes = baos.toByteArray();
                    imageString2 = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                } else if (photo2 == null) {
                    imageString2 = "";
                }
                if (photo3 != null) {
                    Bitmap resized = Bitmap.createScaledBitmap(photo3, 40, 40, true);
                    resized.compress(Bitmap.CompressFormat.JPEG, 10, baos);
                    byte[] imageBytes = baos.toByteArray();
                    imageString2 = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                } else if (photo3 == null && imageString2.equals("")) {
                    imageString2 = "-";                }
                if (photo4 != null) {
                    Bitmap resized = Bitmap.createScaledBitmap(photo4, 40, 40, true);
                    resized.compress(Bitmap.CompressFormat.JPEG, 10, baos);
                    byte[] imageBytes = baos.toByteArray();
                    imageString3 = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                } else if (photo4 == null) {
                    imageString3 = "-";

                }
                if (photo5 != null) {
                    Bitmap resized = Bitmap.createScaledBitmap(photo5, 40, 40, true);
                    resized.compress(Bitmap.CompressFormat.JPEG, 10, baos);
                    byte[] imageBytes = baos.toByteArray();
                    imageString3 = Base64.encodeToString(imageBytes, Base64.DEFAULT);

                }
                else if (photo5 == null && imageString3.equals("")) {
                    imageString3 = "-";
                }
                if (vehicleNumber.length() == 0) {
                    String strArray = vehicleNumber.getText().toString().replaceAll("\\s+", "");
                    vehicleNumber.setText(strArray);
                    vehicleNumber.setError("Enter vehicle number");

                }
                else if (autoCompleteTextView1.length() == 0) {
                    String strArray = autoCompleteTextView1.getText().toString().replaceAll("\\s+", "");
                    autoCompleteTextView1.setText(strArray);
                    autoCompleteTextView1.setError("Enter imei number");
                }
                else if (dateTXT.length() == 0) {
                    String strArray = dateTXT.getText().toString().replaceAll("\\s+", "");
                    dateTXT.setText(strArray);
//                    dateValue=dateTXT.getText().toString();
//                    Log.d(TAG, "onClick:valueofDate "+valueofDate);
                    dateTXT.setError("Enter date");
                }


                else {
                    RequestQueue MyRequestQueue = Volley.newRequestQueue(Create_activity_2.this);

                    final StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url1, new com.android.volley.Response.Listener<String>() {
                        @Override
                        public void onResponse(final String response) {



                            imeiList.removeAll(Arrays.asList(autoCompleteTextView1.getText().toString()));
//                           autoCompleteTextView1.getText().clear();

                            System.out.println(response+"------------------------------");
                            if(response.equals("Created"))
                            {
                                Toast.makeText(Create_activity_2.this, response, Toast.LENGTH_SHORT).show();
                            }
                            else if(response.equals("successful")) {
                                Toast.makeText(Create_activity_2.this, response, Toast.LENGTH_SHORT).show();
                              showAlert();
//                                ViewDialog alert = new ViewDialog();
//                                alert.showDialog(Create_activity_2.this, "Error de conexión al servidor");
                            }
                            else if (response.equals("Invalid Transaction")){
                                Toast.makeText(Create_activity_2.this, response, Toast.LENGTH_SHORT).show();

                            }

                            if (methods.equals("1")){

                                userName = intent.getStringExtra("username").replace(" ", "");
                                vehicle=vehicleNumber.getText().toString().replace(" ", "");
                                imei=autoCompleteTextView1.getText().toString().replace(" ", "");
                                date=dateTXT.getText().toString().replace(" ", "");
                                System.out.println(date);
                                odo0=odometer.getText().toString().replace(" ", "");
                                methods = getIntent().getStringExtra("methodId").replace(" ", "");
                                finish();

                                } else if(methods.equals("2")) {

                                userName = intent.getStringExtra("username");
                                vehicle=vehicleNumber.getText().toString();
                                imei=autoCompleteTextView1.getText().toString();
//                                date=dateValue;
                                date=dateTXT.getText().toString().replace(" ", "");
                                System.out.println(date);
                                odo0=odometer.getText().toString();
                                phnone = getIntent().getStringExtra("phone");
                                emailno = getIntent().getStringExtra("email");
                                passno = getIntent().getStringExtra("password");
                                methods = getIntent().getStringExtra("methodId");
//                                    Toast.makeText(Create_activity_2.this, "Added Sucessful", Toast.LENGTH_SHORT).show();

                                } else {
                                Toast.makeText(Create_activity_2.this, "error", Toast.LENGTH_SHORT).show();
                            }
                            }
                    }, new com.android.volley.Response.ErrorListener() { //Create an error listener to handle errors appropriately.
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //This code is executed if there is an error.
                        }
                    }) {
                        protected Map<String, String> getParams() {
                            Map<String, String> MyData = new HashMap<String, String>();
                            if(methods.equals("1")){
                                MyData.put("dealername",dealname);
                                System.out.println(dealname);
                                MyData.put("username", userName);
                                System.out.println(userName);
                                MyData.put("imei", autoCompleteTextView1.getText().toString().replace(" ", ""));
                                Log.d(TAG, "imei: "+autoCompleteTextView1.getText().toString());
                                System.out.println(autoCompleteTextView1.getText().toString());
                                MyData.put("vehicle_no", vehicleNumber.getText().toString().replace(" ", ""));
                                System.out.println(vehicleNumber.getText().toString());
                                MyData.put("odometer",  odo0=odometer.getText().toString());
                                System.out.println(odo0);
                                MyData.put("installation_date", getdatevals=dateTXT.getText().toString());
                                System.out.println("getdatevals"+"jjjjkkj");
                                System.out.println(dateValue);
                                MyData.put("method",methods);
                                System.out.println(methods);
//                                MyData.put("image", values.replace(" ", ""));
                                MyData.put("image1", imageString1);
                                MyData.put("image2", imageString2);
                                MyData.put("image3", imageString3);

                            }else  if(methods.equals("2")) {
                                MyData.put("dealername",dealname);
                                MyData.put("imei", autoCompleteTextView1.getText().toString().replace(" ", ""));
                                System.out.println(autoCompleteTextView1.getText().toString()+ " "+autoCompleteTextView1.length());
                                MyData.put("vehicle_no", vehicleNumber.getText().toString().replace(" ", ""));
                                System.out.println(vehicleNumber.getText().toString());
                                MyData.put("odometer",  odo0=odometer.getText().toString());
                                System.out.println(odo0);
                                MyData.put("installation_date", getdatevals=dateTXT.getText().toString());
                                System.out.println(getdatevals+"getdatevals");
//                                System.out.println(dateTXT);
                                MyData.put("method", methods.replace(" ", ""));
                                System.out.println(methods);
                                MyData.put("phone", phnone.replace(" ", ""));
                                System.out.println(phnone);
                                MyData.put("email", emailno.replace(" ", ""));
                                System.out.println(emailno);
                                MyData.put("username", userName.replace(" ", ""));
                                System.out.println(userName);
                                MyData.put("password", passno.replace(" ", ""));
                                System.out.println(passno);
//                                MyData.put("image", values.replace(" ", ""));
                                MyData.put("image1", imageString1);
                                MyData.put("image2", imageString2);
                                MyData.put("image3", imageString3);
                            }
                                return MyData;

                        }
                    };
                    MyRequestQueue.add(MyStringRequest);
                }
            }
        });

//        spl.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
//                String text = parent.getItemAtPosition(i).toString();
//                if (spl.getSelectedItem().toString().equals(parent.getItemAtPosition(i).toString())) {
//                    values = spl.getSelectedItem().toString();
//                }
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//            }
//        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage1();
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage2();
            }
        });
    }

    public void showAlert() {
            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(Create_activity_2.this);
            final View customLayout = getLayoutInflater().inflate(R.layout.alert_dialogs, null);
            alertDialog.setView(customLayout);
            final AlertDialog alert = alertDialog.create();
            TextView tt1 = customLayout.findViewById(R.id.username1);
            TextView tt2 = customLayout.findViewById(R.id.pass11);
            tt1.setText("Username : " + userName);
            tt2.setText("Password : " + passno);
            x = userName;
            y = passno;
            ImageView submit;
            submit = customLayout.findViewById(R.id.closebtn);
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alert.dismiss();
                    finish();
//                    Intent intent = new Intent(Create_activity_2.this, CreateAccountActivity.class);
//                    startActivity(intent);
                }
            });

            alert.show();


              Button submit1s;
          submit1s =customLayout.findViewById(R.id.okbtn);
          submit1s.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  String whatsAppMessage = "User name : " + x+ "\nPassword :"+y+"\nApp_Link: " + "https://play.google.com/store/apps/details?id=com.vyom.gpstrackersatway";
                  System.out.println(whatsAppMessage);
                  Intent sendIntent = new Intent();
                  sendIntent.setAction(Intent.ACTION_SEND);
                  sendIntent.putExtra(Intent.EXTRA_TEXT, whatsAppMessage);
                  sendIntent.setType("text/plain");
                  sendIntent.setPackage("com.whatsapp");
                  startActivity(sendIntent);
                  alert.dismiss();
                  finish();
              }
          });
          alert.setCanceledOnTouchOutside(false);
          alert.show();
      }

    private void selectImage() {
        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(Create_activity_2.this);
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo")) {
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(createChooser(cameraIntent, CAMERA_SERVICE), 1);
                } else if (options[item].equals("Choose from Gallery")) {
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 2);
                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
    private void selectImage1() {
        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(Create_activity_2.this);
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo")) {
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(createChooser(cameraIntent, CAMERA_SERVICE), 3);
                } else if (options[item].equals("Choose from Gallery")) {
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 4);
                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void selectImage2() {
        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(Create_activity_2.this);
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo")) {
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(createChooser(cameraIntent, CAMERA_SERVICE), 5);
                } else if (options[item].equals("Choose from Gallery")) {
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 6);
                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (data != null) {
                try {
                    photo = (Bitmap) data.getExtras().get("data");
                    imageView1.setImageBitmap(photo);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (requestCode == 2) {
            if (data != null) {
                Uri filePath = data.getData();

                try {
                    photo1 = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                    imageView1.setImageBitmap(photo1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (requestCode == 3) {
            if (data != null) {
                try {
                    photo2 = (Bitmap) data.getExtras().get("data");
                    imageView2.setImageBitmap(photo2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (requestCode == 4) {
            Uri filePath = data.getData();
            if (filePath != null) {

                try {
                    photo3 = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                    imageView2.setImageBitmap(photo3);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (requestCode == 5) {
            if (data != null) {
                try {
                    photo4 = (Bitmap) data.getExtras().get("data");
                    imageView3.setImageBitmap(photo4);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (requestCode == 6) {
            Uri filePath = data.getData();
            if (filePath != null) {

                try {
                    photo5 = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                    imageView3.setImageBitmap(photo5);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
        public void hideKeybpard (View view){
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }



