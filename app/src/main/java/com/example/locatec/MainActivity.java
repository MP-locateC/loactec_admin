package com.example.locatec;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.util.List;

import lombok.Getter;
import lombok.Setter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

  RequestView requestView;
  requestInfo requestInfo;

  ListAdapter adapter;
  ListView listView;
//  Context context;


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
    Log.d("TEST", "성공1");
//    context = this;

    api.findAll().enqueue(new Callback<AllRequestItemsJson>() {
      @Override
      public void onResponse(Call<AllRequestItemsJson> call, Response<AllRequestItemsJson> response) {
//        requestInfo = new requestInfo("hello", "test");
        Log.d("TEST", "성공2");
        listView = findViewById(R.id.requestLists);
        adapter = new ListAdapter();
//        Log.d("TEST", response.body().response.toString());

        for (int i = 0; i < response.body().response.size(); i++) {
          RequestItem requestItem = response.body().response.get(i);

          Log.d("TEST", "성공3" + requestItem.latitude);
          Log.d("TEST", "성공3" + requestItem.longitude);

          adapter.addItem(requestItem);
//          requestInfo = new requestInfo(test2.latitude, test2.longitude);
//          requestView = new RequestView(getApplicationContext(), requestInfo);
//          ArrayAdapter<RequestView> adapter = new ArrayAdapter<RequestView>(this,
//                 android.R.layout.simple_list_item_1, requestView);


//          addContentView(requestView,
//                  new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                          ViewGroup.LayoutParams.MATCH_PARENT));
        }

        listView.setAdapter(adapter);

      }

      @Override
      public void onFailure(Call<AllRequestItemsJson> call, Throwable t) {
        Log.d("TEST", "실패");
        t.printStackTrace();
      }
    });

//    requestInfo = new requestInfo("hello1", "test1");
//    requestView = new RequestView(this, requestInfo);
//    addContentView(requestView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//            ViewGroup.LayoutParams.WRAP_CONTENT));
  }
}

