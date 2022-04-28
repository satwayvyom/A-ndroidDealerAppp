package com.Satway.gpstracker.ui;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.Satway.gpstracker.R;

import java.util.HashMap;
import java.util.Map;

public class test_api extends AppCompatActivity {
    Button button;
    final String url ="http://65.0.135.153/";
    String js="{\n" +
            "  \"t\" : \"jh\",\"v\" : \"12\",\"AD\" : \"hjb\" ,\"l\" : \"jj\"\n" +
            "  ,\"g\" : \"kjh\", \"s\" : \"i\", \"i\" :\"f\",\"p\":\"o\",\"o\":\"m\",\"MV\" : \"fvf\",\"IV\":\"jn\"\n" +
            "  ,\"VM\" :\"frv\" ,\"FV\" :\"kj\",\"SV\":\"fg\",\"HD\":\"PD\",\"PD\":\"kj\",\"SS\":\"kn\",\"MC\":\"kl\",\n" +
            "  \"MN\":\"lklkj\"\n" +
            "  ,\"LC\":\"kjkj\",\"CD\":\"kjhj\",\"GD\":\"lkhg\",\"NO\":\"lk\",\"NI\":\"nj\",\"PS\":\"kj\",\"CG\":\"hj\"\n" +
            "  ,\"VN\":\"kj\",\"AT\":\"jb\",\"FN\":\"lm\",\"HLM\":\"jh\",\"OTA\":\"kjh\",\"VD\":\"lk\"\n" +
            "}";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_api);
        Toolbar toolbar = findViewById(R.id.toolbar);
        button=findViewById(R.id.button);

        setSupportActionBar(toolbar);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    RequestQueue MyRequestQueue = Volley.newRequestQueue(test_api.this);

                    final StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
                        @Override

                        public void onResponse(String response) {
                            Toast.makeText(test_api.this, response, Toast.LENGTH_SHORT).show();
                        }


                    }, new com.android.volley.Response.ErrorListener() { //Create an error listener to handle errors appropriately.
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //This code is executed if there is an error.
                        }
                    }) {
                        protected Map<String, String> getParams() {
                            Map<String, String> MyData = new HashMap<String, String>();
                            MyData.put("username", "bhv" );
                            return MyData;
                        }
                    };


                    MyRequestQueue.add(MyStringRequest);
                }

        });










    }
}