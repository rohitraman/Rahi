package com.example.roshan.rahiapp;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

public class GetTrainsOnActivity extends AppCompatActivity {
    EditText etFrom, etTo, etDate;
    Button button;
    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_trains_on);
        etFrom = (EditText) findViewById(R.id.et_from_2);
        etTo = (EditText) findViewById(R.id.et_to_2);
        etDate = (EditText) findViewById(R.id.et_date);

        button = (Button) findViewById(R.id.button2);
        etDate.setShowSoftInputOnFocus(false);
        Calendar calendar = Calendar.getInstance();
        final int dd = calendar.get(Calendar.DAY_OF_MONTH);
        final int mm = calendar.get(Calendar.MONTH);
        final int yy = calendar.get(Calendar.YEAR);

        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(GetTrainsOnActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        date = String.valueOf(i2) + "-" + String.valueOf(i1+1) + "-" + String.valueOf(i);
                        etDate.setText(date);
                    }
                }, yy, mm, dd);
                dialog.show();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] dates = date.split("-");
                String dd = dates[0];
                String mm = dates[1];
                String yy = dates[2];
                JSONObject object = new JSONObject();
                try {
                    object.put("from",etFrom.getText().toString().toUpperCase());
                    object.put("to",etTo.getText().toString().toUpperCase());
                    object.put("dd",Integer.parseInt(dd));
                    object.put("mm",Integer.parseInt(mm));
                    object.put("yyyy",Integer.parseInt(yy));
                    final JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, "http://192.168.0.105:8080/getTrainsOn", object, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.i("Response",response.toString());
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.i("Eror",error.getLocalizedMessage());
                            NetworkResponse response = error.networkResponse;
                            if(error instanceof ServerError && response!=null)
                            {
                                try {
                                    String string = new String(response.data, HttpHeaderParser.parseCharset(response.headers,"utf-8"));
                                    Log.i("Logging",string);
                                } catch (UnsupportedEncodingException e) {
                                    e.printStackTrace();
                                }
                            }
;                        }
                    });

                    RequestQueue queue = Volley.newRequestQueue(GetTrainsOnActivity.this);
                    queue.add(request);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });

    }
}
