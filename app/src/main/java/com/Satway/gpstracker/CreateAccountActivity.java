package com.Satway.gpstracker;
//
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.io.Serializable;
import java.util.ArrayList;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

//import android.Manifest;
import android.app.Activity;
//import android.app.AlertDialog;
//import android.app.Dialog;
//import android.app.ProgressDialog;
//import android.content.DialogInterface;
//import android.graphics.Color;
//import android.graphics.drawable.ColorDrawable;
//import android.graphics.drawable.Drawable;
import android.content.Intent;
import android.content.SharedPreferences;
//import android.graphics.drawable.BitmapDrawable;
//import android.graphics.drawable.Drawable;
//import android.nfc.Tag;
import android.text.Editable;
import android.text.TextWatcher;
//import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
//import android.widget.Spinner;
//import android.content.pm.PackageManager;
//import android.database.Cursor;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.net.Uri;
//import android.os.Environment;
//import android.provider.MediaStore;
//import android.telephony.SmsManager;
//import android.util.Base64;
import android.util.Log;
//import android.view.Menu;
import android.view.View;
//import android.content.Intent;
//import android.net.Uri;
import android.os.Bundle;
//import android.provider.MediaStore;

import android.content.Context;
//
//import androidx.core.app.ActivityCompat;
//import androidx.core.content.ContextCompat;
//
//import android.os.Bundle;

import java.util.HashMap;
import java.util.List;
//
//import android.app.DatePickerDialog;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
//import android.widget.DatePicker;
import android.widget.EditText;
//import android.widget.ImageButton;
//import android.widget.ImageView;
import android.widget.Button;
//import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
//import android.widget.Toast;
//
//import com.Satway.gpstracker.activities.MainActivity;
import com.Satway.gpstracker.model.UserData;
import com.Satway.gpstracker.model.UserDetailsResponse;
import com.Satway.gpstracker.ui.AutoSuggestAdapter;
//import com.android.volley.AuthFailureError;
//import com.android.volley.DefaultRetryPolicy;
//import com.android.volley.NetworkError;
//import com.android.volley.NoConnectionError;
//import com.android.volley.ParseError;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
//import com.android.volley.RetryPolicy;
//import com.android.volley.ServerError;
//import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
//
//import java.util.Calendar;
import java.util.Map;
//
//import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//import java.util.UUID;
//
//import static android.content.Intent.createChooser;
//import static android.media.MediaRecorder.VideoSource.CAMERA;
//import static org.bouncycastle.crypto.tls.BulkCipherAlgorithm.rc2;

public class CreateAccountActivity extends AppCompatActivity {
    private static final String TAG = "CreateAccountActivity";

    SharedPreferences sharedPreferences;

    SharedPreferences shared;
    AutoCompleteTextView autoCompleteTextView;
    EditText phno, email, password, confirmPassword;
    TextView method;
    Button nextButton;
    boolean flag=false;
    final List<String> stringList = new ArrayList<String>();
    List<UserData> userDataList = new ArrayList<>();
    List<String> imeiList = new ArrayList<>();
    String userAndImeiListData = "";
    Intent i;
    Toast toast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

//        LayoutInflater inflater = getLayoutInflater();
//        View layout = inflater.inflate(R.layout.custtoast,(ViewGroup)findViewById(R.id.cust_toast));
        //final  Toast toast = new Toast(getApplicationContext());
//        toast = new Toast(getApplicationContext());
//        toast.setGravity(Gravity.CENTER,0,0);
//        toast.setDuration(Toast.LENGTH_SHORT);
//        toast.setView(layout);

        shared = getSharedPreferences("cridential", Context.MODE_PRIVATE);
        String a=shared.getString("dealername", "");
        Log.d(TAG, "onCreate: shared"+a);
        final String url = "http://144.126.252.202/api/imeifetch_dealer_android";
        final String url1 = "http://144.126.252.202/api/get_data";

        autoCompleteTextView = findViewById(R.id.user);
        phno = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        password = findViewById(R.id.pass);
        confirmPassword = findViewById(R.id.conpass);
        nextButton = findViewById(R.id.nxtbtn);
        method = findViewById(R.id.enable);
        Log.d(TAG, "urlimei: "+userDataList);
        autoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String name = editable.toString();
                for (UserData userData : userDataList) {
                    if (name.equals(userData.getName())) {
                        phno.setText(userData.getPhone());
                        email.setText(userData.getEmail());
                        password.setText("....");
                        confirmPassword.setText("....");
                        method.setText("1");
                        break;
                    }else {
                        method.setText("2");
                    }
                }
            }
        });

        RequestQueue MyRequestQueue = Volley.newRequestQueue(CreateAccountActivity.this);
        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObj = new JSONObject(response);
                    Log.d(TAG, "Response" + response);
                    userAndImeiListData = response;

                    Gson GSON = new Gson();
                    UserDetailsResponse userDetailsResponse = new UserDetailsResponse();
                    userDetailsResponse = GSON.fromJson(response, UserDetailsResponse.class);
                    userDataList = userDetailsResponse.getUserlist();
                    imeiList = userDetailsResponse.getImeilist();
                    System.out.println(imeiList);


                    for (UserData userData : userDataList) {
                        stringList.add(userData.getName());
                    }
                    final AutoSuggestAdapter adapt = new AutoSuggestAdapter(CreateAccountActivity.this, android.R.layout.simple_list_item_1, stringList);
                    autoCompleteTextView.setAdapter(adapt);
                    autoCompleteTextView.setThreshold(3);
                    adapt.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("dealername", shared.getString("dealername", ""));
                Log.d(TAG, "getParams: dealername"+shared.getString("dealername", ""));
                MyData.put("username", autoCompleteTextView.getText().toString());
                MyData.put("password", password.getText().toString());
                return MyData;
            }
        };
        MyRequestQueue.add(MyStringRequest);

        nextButton.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View view){
                //toast.show();

                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                String MobilePattern = "[0-9]{10}";

                if (autoCompleteTextView.length() == 0) {
                    String strArray = autoCompleteTextView.getText().toString().replaceAll("\\s+", "");;
                    autoCompleteTextView.setText(strArray);
//                    autoCompleteTextView.getText().toString().replace(" ", "");
                    autoCompleteTextView.setError("Enter user name");
                } else if ((phno.length() == 0)) {
                    String strArray = phno.getText().toString().replaceAll("\\s+", "");;
                    phno.setText(strArray);
                    phno.setError("Enter phone number");
                } else if ((!phno.getText().toString().matches(MobilePattern))) {
                    String strArray = phno.getText().toString().replaceAll("\\s+", "");;
                    phno.setText(strArray);
                    phno.setError("Enter valid Phone number");
                }
                else if (email.length() == 0) {
                    String strArray = email.getText().toString().replaceAll("\\s+", "");;
                    email.setText(strArray);
                    email.setError("Enter valid Email Id");
                } else if (!email.getText().toString().trim().matches(emailPattern)) {
                    String strArray = email.getText().toString().replaceAll("\\s+", "");;
                    email.setText(strArray);
                    email.setError("Enter valid Email Id");
                } else if (password.length() == 0) {
                    String strArray = password.getText().toString().replaceAll("\\s+", "");;
                    password.setText(strArray);
                    password.setError("Enter password");
                } else if (confirmPassword.length() == 0) {
                    String strArray = confirmPassword.getText().toString().replaceAll("\\s+", "");;
                    confirmPassword.setText(strArray);
                    confirmPassword.setError("Enter confirm password");
                } else if (!confirmPassword.getText().toString().equals(password.getText().toString())) {
                    String strArray = confirmPassword.getText().toString().replaceAll("\\s+", "");;
                    confirmPassword.setText(strArray);
                    confirmPassword.setError("Passwords are not matching");
                } else {

                    USerStatusCheck();


                    String userName = autoCompleteTextView.getText().toString().replaceAll("\\s+", "");
                    String phoneNumber = phno.getText().toString().replaceAll("\\s+", "");
                    String EmailId = email.getText().toString().replaceAll("\\s+", "");
                    String PasswordId = password.getText().toString().replaceAll("\\s+", "");
                    String methodId = method.getText().toString().replaceAll("\\s+", "");
                    i = new Intent(CreateAccountActivity.this, Create_activity_2.class);
                    i.putExtra("username", userName.replaceAll("\\s+", ""));
                    i.putExtra("phone", phoneNumber.replaceAll("\\s+", ""));
                    i.putExtra("email", EmailId.replaceAll("\\s+", ""));
                    i.putExtra("password", PasswordId.replaceAll("\\s+", ""));
                    i.putExtra("methodId",methodId.replaceAll("\\s+", ""));
                    System.out.println(methodId.replaceAll("\\s+", ""));
                    Log.d(TAG, "onClick: " + userAndImeiListData);
                    i.putExtra("userandzimeiList", userAndImeiListData);
                    // startActivity(i);
                }
            }
        });

    }

    private void USerStatusCheck() {

        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);

        StringRequest jsonObjRequest = new StringRequest(Request.Method.POST,"http://144.126.252.202/api/checkusername",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("hello", "Success call");

                        try {
                            // on below line we are passing our response
                            // to json object to extract data from it.
                            JSONObject respObj = new JSONObject(response);

                            // below are the strings which we
                            // extract from our json object.
                            String error = respObj.getString("status");

                            Log.d("hello",error);

                            if(error.equals("user under different dealer"))
                            {
                                Log.d("hello","if called");

                                LayoutInflater inflater = getLayoutInflater();
                                View layout = inflater.inflate(R.layout.activity_user_already_exists,(ViewGroup)findViewById(R.id.cust_toast_user_already_exists));
                                //final  Toast toast = new Toast(getApplicationContext());
                                toast = new Toast(getApplicationContext());
                                // toast.setGravity(Gravity.CENTER,0,0);
                                toast.setDuration(Toast.LENGTH_SHORT);
                                toast.setView(layout);
                                toast.show();

                                // Toast.makeText(CreateAccountActivity.this,"USERNAME ALREADY EXISTS",Toast.LENGTH_SHORT);
                            }
                            else
                            {
                                Log.d("hello","else called");
//                                    LayoutInflater inflater = getLayoutInflater();
//                                    View layout = inflater.inflate(R.layout.custtoast,(ViewGroup)findViewById(R.id.cust_toast));
//                                    toast = new Toast(getApplicationContext());
//                                    toast.setGravity(Gravity.CENTER,0,0);
//                                    toast.setDuration(Toast.LENGTH_SHORT);
//                                    toast.setView(layout);
//
//                                    toast.show();
                                startActivity(i);

                            }



                            // on below line we are setting this string s to our text view.

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("volley", "Error: " + error.getMessage());
                error.printStackTrace();
                Log.e("hello", error.getMessage());
            }
        }) {

            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("dealer",shared.getString("dealername", ""));
                params.put("username", autoCompleteTextView.getText().toString());

                return params;
            }

        };

        MyRequestQueue.add(jsonObjRequest);





    }

    public void hideKeybpard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


}