package com.example.locatec;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

  ListAdapter adapter;
  ListView listView;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://www.stmap.kro.kr")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    Api api = retrofit.create(Api.class);

    api.findAll().enqueue(new Callback<AllRequestItemsJson>() {
      @Override
      public void onResponse(Call<AllRequestItemsJson> call, Response<AllRequestItemsJson> response) {
        Log.d("전체 요청 리스트 조회", "성공");
        listView = findViewById(R.id.requestLists);
        adapter = new ListAdapter();

        adapter.setClickListener(new ClickListener() {
          @Override
          public void refresh() {
            adapter.notifyDataSetChanged();
          }
        });

        for (int i = 0; i < response.body().response.size(); i++) {
          RequestItem requestItem = response.body().response.get(i);
          adapter.addItem(requestItem);
        }

        listView.setAdapter(adapter);

      }

      @Override
      public void onFailure(Call<AllRequestItemsJson> call, Throwable t) {
        Log.d("전체 요청 리스트 조회", "실패");
        t.printStackTrace();
      }
    });


  }
}

