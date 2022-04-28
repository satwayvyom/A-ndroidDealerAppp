package com.Satway.gpstracker.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.Satway.gpstracker.Dashboard2Activity;
import com.Satway.gpstracker.DeviceActivity;
import com.Satway.gpstracker.PaytmActivity;
import com.Satway.gpstracker.PurchaseActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.Satway.gpstracker.DashboardActivity;
import com.Satway.gpstracker.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.InstallState;
import com.google.android.play.core.install.InstallStateUpdatedListener;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.InstallStatus;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.android.play.core.tasks.OnSuccessListener;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

//Main activity

public class MainActivity extends AppCompatActivity {
    private AppUpdateManager mappUpdateManager;
    private  static  final  int RC_APP_UPDATE = 100;

    int no_of_networkcall=0;

    private Button button1,serverdialogcancel_button;
    EditText uname, pass;
    CardView dialog,blur;
    ProgressBar progressBar;
    //        final String url = "http://144.126.252.202/api/client_login_dealer";
//    final String url = "http://167.71.231.84:8080/api/client_login_dealer";
    final String url = "http://68.183.92.233:8000/api/client_login_dealer";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        serverdialogcancel_button=findViewById(R.id.serverdialogcancel_login);
        dialog=findViewById(R.id.servererrordialog_login);
        progressBar=findViewById(R.id.progressBar_severdialog);
        blur=findViewById(R.id.blur_login);

        mappUpdateManager = AppUpdateManagerFactory.create(this);
        mappUpdateManager.getAppUpdateInfo().addOnSuccessListener(new OnSuccessListener<AppUpdateInfo>() {
            @Override
            public void onSuccess(AppUpdateInfo result) {
               if(result.updateAvailability()== UpdateAvailability.UPDATE_AVAILABLE && result.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)){
                   try {
                       mappUpdateManager.startUpdateFlowForResult(result,AppUpdateType.IMMEDIATE,MainActivity.this ,RC_APP_UPDATE);
                   } catch (IntentSender.SendIntentException e) {
                       e.printStackTrace();
                   }
               }
            }
        });

        mappUpdateManager.registerListener(installStateUpdatedListener);


        uname = findViewById(R.id.username);
        pass = findViewById(R.id.pass);
        button1 = findViewById(R.id.btn1);



        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                blur.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.VISIBLE);

                ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                // ARE WE CONNECTED TO THE NET
                if (conMgr.getActiveNetworkInfo() == null) {

                    Log.d("jishnu", "network is not connected");
                    progressBar.setVisibility(View.GONE);
                    blur.setVisibility(View.GONE);

                    Toast.makeText(MainActivity.this, "Please Check Your Internet Connection", Toast.LENGTH_SHORT).show();
                } else {
                    no_of_networkcall=0;

                    getresult();



                }
            }

        });
    }


    public void getresult()
    {
        final SharedPreferences sharedPreferences = getSharedPreferences("cridential", Context.MODE_PRIVATE);
        RequestQueue MyRequestQueue = Volley.newRequestQueue(MainActivity.this);

        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                blur.setVisibility(View.GONE);
                progressBar.setVisibility(View.GONE);

                JSONObject object = null;
                try {
                    object = new JSONObject(response);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                String value = null;
                try {
                    value = object.getString("login");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (value.equals("False")) {
                    System.out.println(response);
                    Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        if (object.getString("usertype").equals("DE")) {
                            System.out.println(response);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("dealername", uname.getText().toString());
                            editor.putString("dealerpassword", pass.getText().toString());
                            editor.apply();
                            Intent intent = new Intent(MainActivity.this, DashboardActivity.class);

                            startActivity(intent);
                            finish();
                        } else if (object.getString("usertype").equals("SD")) {
                            System.out.println(response);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("dealername", uname.getText().toString());
                            editor.putString("dealerpassword", pass.getText().toString());
                            editor.apply();
                            Intent intent = new Intent(MainActivity.this, Dashboard2Activity.class);
                            startActivity(intent);
                            finish();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                no_of_networkcall=no_of_networkcall+1;
                if(no_of_networkcall==10)
                {

                    Log.d("jishnu", "error for server");
                    dialog.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                    serverdialogcancel_button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            blur.setVisibility(View.GONE);
                            dialog.setVisibility(View.GONE);

                        }
                    });

                }
                getresult();

            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("dealername", uname.getText().toString());
                MyData.put("dealerpassword", pass.getText().toString());

                return MyData;
            }
        };
        MyRequestQueue.add(MyStringRequest);


    }

    public void hideKeybpard (View view){
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

        private InstallStateUpdatedListener  installStateUpdatedListener = new InstallStateUpdatedListener()
        {
            @Override
            public  void onStateUpdate(InstallState state){
                if(state.installStatus() == InstallStatus.DOWNLOADED){
                    showCompleteUpdate();
                }

            }
        };

    @Override
    protected void onStop() {
        if(mappUpdateManager!=null)mappUpdateManager.unregisterListener(installStateUpdatedListener);
        super.onStop();
    }

    private void showCompleteUpdate() {
        Snackbar snackbar = Snackbar.make(findViewById(R.id.content), "New app is ready!", Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("Install", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 mappUpdateManager.completeUpdate();
            }
        });
        snackbar.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

       if(requestCode == RC_APP_UPDATE && resultCode != RESULT_OK){

       }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
