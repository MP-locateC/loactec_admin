package com.example.locatec;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

  RequestView requestView;
  requestInfo requestInfo;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://14.53.253.76:8080")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    Api api = retrofit.create(Api.class);
    Log.d("TEST", "성공1");
    api.findAll().enqueue(new Callback<String>() {
      @Override
      public void onResponse(Call<String> call, Response<String> response) {
        requestInfo = new requestInfo("hello", "test");
          Log.d("TEST", "성공2");
          Log.d("TEST", response.body());

      }

      @Override
      public void onFailure(Call<String> call, Throwable t) {
        Log.d("TEST", "실패");
        t.printStackTrace();
      }
    });

    requestInfo = new requestInfo("hello1", "test1");
    requestView = new RequestView(this, requestInfo);
    addContentView(requestView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT));
  }
}