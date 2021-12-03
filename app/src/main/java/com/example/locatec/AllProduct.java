package com.example.locatec;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AllProduct extends Fragment {

  private View view;
  RequestListAdapter adapter;
  ListView listView;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.all_product, container, false);

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
        listView = view.findViewById(R.id.productList);
        adapter = new RequestListAdapter();

        adapter.setClickListener(new ClickListener() {
          @Override
          public void refresh() {
            adapter.notifyDataSetChanged();
          }
        });


        for (int i = 0; i < response.body().response.size(); i++) {
          RequestItem requestItem = response.body().response.get(i);
          if (requestItem.isRegister) adapter.addItem(requestItem);
        }

        listView.setAdapter(adapter);

      }

      @Override
      public void onFailure(Call<AllRequestItemsJson> call, Throwable t) {
        Log.d("전체 요청 리스트 조회", "실패");
        t.printStackTrace();
      }
    });
    return view;
  }
}