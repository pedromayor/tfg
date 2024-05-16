package com.example.myaplitfg.activity;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.myaplitfg.Entity.service.Condicionesmeteorologicas;
import com.example.myaplitfg.R;
import com.example.myaplitfg.adapter.CondicionesmeteorologicasAdapter;
import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ElTiempoActivity extends AppCompatActivity {

   private RelativeLayout homeRL;
   private ProgressBar LoadingPB;
   private TextView cityNameTV, temperatureTV, conditionTV;
   private RecyclerView weatherRV;
   private TextInputEditText cityEdt;
   private ImageView backIV, iconIV, searchIV;
   private ArrayList<Condicionesmeteorologicas> CondicionesmeteorologicasArrayList;
   private CondicionesmeteorologicasAdapter condicionesmeteorologicasAdapter;
   private LocationManager locationManager;
   private int PERMISSION_CODE = 1;
   private String cityName;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);

      getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
      setContentView(R.layout.activity_tiempo);
      homeRL = findViewById(R.id.idRLHome);
      LoadingPB = findViewById(R.id.idPBLoading);
      cityNameTV = findViewById(R.id.idTVCityName);
      temperatureTV = findViewById(R.id.idTVTemperature);
      conditionTV = findViewById(R.id.idTVCondition);
      weatherRV = findViewById(R.id.idRvWeather);
      cityEdt = findViewById(R.id.idEdtCity);
      backIV = findViewById(R.id.idIVBack);
      iconIV = findViewById(R.id.idIVIcon);
      searchIV = findViewById(R.id.idIVSearch);
      CondicionesmeteorologicasArrayList = new ArrayList<>();
      condicionesmeteorologicasAdapter = new CondicionesmeteorologicasAdapter(this, CondicionesmeteorologicasArrayList);
      weatherRV.setAdapter(condicionesmeteorologicasAdapter);

      locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
      if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
              != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
              != PackageManager.PERMISSION_GRANTED) {
      ActivityCompat.requestPermissions(ElTiempoActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSION_CODE);
   }
   Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
      cityName = getCityName(location.getLongitude(),location.getLatitude());
      getCondicionesInfo(cityName);

      searchIV.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            String city = cityEdt.getText().toString();
            if(city.isEmpty()){
               Toast.makeText(ElTiempoActivity.this, "Por favor introduce una ciudad", Toast.LENGTH_SHORT).show();
            }else {
               cityNameTV.setText(cityName);
               getCondicionesInfo(city);
            }
         }
      });
}

   @Override
   public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
      super.onRequestPermissionsResult(requestCode, permissions, grantResults);
      if(requestCode==PERMISSION_CODE){
         if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "Permiso admitido..", Toast.LENGTH_SHORT).show();
         }else {
            Toast.makeText(this,"Por favor, introduce los permisos", Toast.LENGTH_SHORT).show();
            finish();
         }
      }
   }

   private String getCityName(double longitude, double latitude) {
     String cityName = "not found";
     Geocoder gcd = new Geocoder(getBaseContext(), Locale.getDefault());
     try {
        List<Address> addresses = gcd.getFromLocation(latitude,longitude,10);

        for (Address adr : addresses){
           if(adr!=null){
              String city = adr.getLocality();
              if(city!=null && !city.equals("")){
                 cityName = city;
              }else {
                 Log.d("TAG","Ciudad no encontrada");
                 Toast.makeText(this,"Ciudad del usuario no encontrada..",Toast.LENGTH_SHORT).show();
              }
           }
        }
     }catch (IOException e){
        e.printStackTrace();
     }
   return cityName;
  }

   private void getCondicionesInfo(String cityName){
      String url = "http://api.weatherapi.com/v1/forecast.json?key=0236e49db3df489c9f3161108241505&q=" + cityName + "&days=1&aqi=yes&alerts=yes\n";
      cityNameTV.setText(cityName);
      RequestQueue requestQueue = Volley.newRequestQueue(ElTiempoActivity.this);

      JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
         @Override
         public void onResponse(JSONObject response) {
            LoadingPB.setVisibility(View.GONE);
            homeRL.setVisibility(View.VISIBLE);
            CondicionesmeteorologicasArrayList.clear();

            try {
               String temperature = response.getJSONObject("current").getString("temp_c");
               temperatureTV.setText(temperature+"ºc");
               int isDay = response.getJSONObject("current").getInt("is_day");
               String condition = response.getJSONObject("current").getJSONObject("condition").getString("text");
               String conditionIcon = response.getJSONObject("current").getJSONObject("condition").getString("icon");
               Picasso.get().load("http:".concat(conditionIcon)).into(iconIV);
               conditionTV.setText(condition);
               if(isDay==1){
                  Picasso.get().load("https://unsplash.com/es/fotos/vista-aerea-de-la-montana-durante-la-hora-dorada-4r_tHA3gsUY").into(backIV);
               }else {
                  Picasso.get().load("https://unsplash.com/es/fotos/foto-de-angulo-bajo-de-la-noche-estrellada-sbcIAn4Mn14").into(backIV);
               }

               JSONObject forecastObj = response.getJSONObject("forecast");
               JSONObject forcast0 = forecastObj.getJSONArray("forecastday").getJSONObject(0);
               JSONArray hourArray = forcast0.getJSONArray("hour");

               for(int i=0; i<hourArray.length();i++){
                  JSONObject hourObj =hourArray.getJSONObject(i);
                  String time = hourObj.getString("time");
                  String tempert = hourObj.getString("temp_c");
                  String img = hourObj.getJSONObject("condition").getString("icon");
                  String wind = hourObj.getString("wind_kph");
                  CondicionesmeteorologicasArrayList.add(new Condicionesmeteorologicas(time,tempert,img,wind));

               }
               condicionesmeteorologicasAdapter.notifyDataSetChanged();

            } catch (JSONException e) {
               throw new RuntimeException(e);
            }
         }
      }, new Response.ErrorListener() {
         @Override
         public void onErrorResponse(VolleyError error) {
            Toast.makeText(ElTiempoActivity.this, "Introduce una ciudad validad, por favor..", Toast.LENGTH_SHORT).show();
         }
      });
      requestQueue.add(jsonObjectRequest);
   }
}