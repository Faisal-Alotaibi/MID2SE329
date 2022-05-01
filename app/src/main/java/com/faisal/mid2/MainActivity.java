package com.faisal.mid2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {
    TextView tempr , humid;
    Spinner city;
    String weatherWebserviceURL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bttn = (Button) findViewById(R.id.dateBtn);
        Calendar c = Calendar.getInstance();
        DateFormat fmtDate = DateFormat.getDateInstance();
        TextView reservation = (TextView) findViewById(R.id.textView);

        tempr = (TextView) findViewById(R.id.tempTXT);
        humid = (TextView) findViewById(R.id.humidTXT);

        city = (Spinner) findViewById(R.id.spinner);

        Button nextBtn = (Button) findViewById(R.id.nextBTN);





        city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                weatherWebserviceURL = "https://api.openweathermap.org/data/2.5/weather?q="+city.getSelectedItem().toString()+"&appid=9daa7e7592c4079e1c32d6c9bbb8a64d&units=metric";
                weather(weatherWebserviceURL);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear, int dayOfMonth) {
                c.set(Calendar.YEAR, year);
                c.set(Calendar.MONTH, monthOfYear);
                c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                reservation.setText("Date is "+
                        fmtDate.format(c.getTime()));
            }
        };

        bttn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                new DatePickerDialog(MainActivity.this, d,
                        c.get(Calendar.YEAR),
                        c.get(Calendar.MONTH),
                        c.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    nextBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(MainActivity.this , Master.class));

        }
    });


    }

    public void weather(String url){

        JsonObjectRequest jsonObj =
                new JsonObjectRequest(Request.Method.GET,
                        url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Faisal", "Response received");
                        Log.d("Faisal" , response.toString());

                        try {
                            String town = response.getString("name");
                            Log.d("Faisal-town", town);


                            JSONObject jsonMain = response.getJSONObject("main");
                            JSONObject jsonSys = response.getJSONObject("sys");

                            double temp = jsonMain.getDouble("temp");
                            Log.d("Faisal-temp", String.valueOf(temp));
                            tempr.setText("temp: "+String.valueOf(temp));



                            double humidNum = jsonMain.getDouble("humidity");
                            Log.d("Faisal-Humidity",String.valueOf(humidNum));
                            humid.setText("Humidity: "+String.valueOf(humidNum));



                            long sunrise = jsonSys.getLong("sunrise");
                            long sunset = jsonSys.getLong("sunset");

                            Log.d("Faisal-sunrise",String.valueOf(sunrise));
                            Log.d("Faisal-sunset",String.valueOf(sunset));

                            /*Date dateSN = new Date();
                            dateSN.setTime(1649040116);
                            DateFormat simple = new SimpleDateFormat("HH:mm");*/


                            String sunriseString = new SimpleDateFormat("HH:mm a", Locale.ENGLISH).format(new Date(sunrise * 1000));

                            String sunsetString = new SimpleDateFormat("HH:mm a", Locale.ENGLISH).format(new Date(sunset * 1000));

                            Log.d("Faisal-sunriseFormat", sunriseString);
                            Log.d("Faisal-sunsetFormat", sunsetString);



                            JSONArray jArray = response.getJSONArray("weather");
                            for (int i=0; i < jArray.length(); i++){
                                try {
                                    Log.d("Faisal-array",jArray.getString(i));
                                    JSONObject oneObject = jArray.getJSONObject(i);
                                    String wheater = oneObject.getString("main");
                                    Log.d("Faisal-w",wheater);

                                    if (wheater.equals("Clouds")){
                                        //Glide.with(MainActivity.this).load("https://images.unsplash.com/photo-1504253163759-c23fccaebb55?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxleHBsb3JlLWZlZWR8MXx8fGVufDB8fHx8&auto=format&fit=crop&w=500&q=60").into(weatherBackground);

                                    }else if (wheater.equals("Clear")){
                                       // Glide.with(MainActivity.this).load("https://media.istockphoto.com/photos/sky-and-grass-backround-picture-id182493016?b=1&k=20&m=182493016&s=170667a&w=0&h=zjP60YhWtNE1iDB1BsvTnPka35zH3ry4v6I3FYfyFuo=").into(weatherBackground);

                                    }else if (wheater.equals("Rainy")){
                                        //Glide.with(MainActivity.this).load("https://media.istockphoto.com/photos/rain-is-falling-in-a-puddle-in-the-garden-picture-id1292942434?b=1&k=20&m=1292942434&s=170667a&w=0&h=dAypvtzYHLZ2QSUNSWASEWvUwDNg9Sso3yb9QfByDks=").into(weatherBackground);

                                    }

                                } catch (JSONException e) {
                                    // Oops
                                }
                            }


                        }catch (JSONException e){
                            e.printStackTrace();
                            Log.e("Receive Error", e.toString());
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Faisal", "ERROR RETRIEVING URL");
                    }
                });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonObj);
    }
}

