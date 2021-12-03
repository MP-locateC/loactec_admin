package com.example.admin;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ProductListAdapter extends BaseAdapter {
  List<ProductItem> items = new ArrayList<>();
  ClickListener clickListener;
  Context context;

  public void setClickListener(ClickListener clickListener) {
    this.clickListener = clickListener;
  }

  @Override
  public int getCount() {
    return items.size();
  }

  @Override
  public Object getItem(int position) {
    return items.get(position);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    context = parent.getContext();
    ProductItem item = items.get(position);

    if (convertView == null) {
      LayoutInflater inflater =
              (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      convertView = inflater.inflate(R.layout.registered, parent, false);
    }

    TextView id = convertView.findViewById(R.id.id);
    TextView latitude = convertView.findViewById(R.id.latitude);
    TextView longitude = convertView.findViewById(R.id.longitude);
    TextView type = convertView.findViewById(R.id.type);
    Button delete = convertView.findViewById(R.id.delete);

    id.setText(item.id.toString());
    latitude.setText("위도 : " + item.latitude);
    longitude.setText("경도 : " + item.longitude);

    typeNaming(item, type);


    Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://www.stmap.kro.kr")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    Api api = retrofit.create(Api.class);

    delete.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        api.deleteProduct(new ItemId(item.id)).enqueue(new Callback<DeleteResponseJson>() {
          @Override
          public void onResponse(Call<DeleteResponseJson> call, Response<DeleteResponseJson> response) {
            Log.d("요청 처리", "삭제 성공");
            removeItem(item);
            clickListener.refresh();
            Toast.makeText(context, "삭제 성공", Toast.LENGTH_SHORT).show();
          }

          @Override
          public void onFailure(Call<DeleteResponseJson> call, Throwable t) {
            Log.d("요청 처리", "삭제 실패");
            t.printStackTrace();
          }
        });
      }
    });

    convertView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        View dialog = (View) View.inflate(context, R.layout.image_view, null);
        AlertDialog.Builder dlg = new AlertDialog.Builder(context);
        dlg.setView(dialog);

        ImageView image = dialog.findViewById(R.id.image);

        Picasso.get()
                .load(item.imageUrl)
                .into(image);

        dlg.setPositiveButton("확인", null);
        dlg.show();
      }
    });

    return convertView;

  }

  private void typeNaming(ProductItem item, TextView type) {
    if (item.type.equals("smoking"))
      type.setText("흡연 부스");
    if (item.type.equals("trash"))
      type.setText("쓰레기통");
    type.setText(item.type);
  }

  public void addItem(ProductItem test) {
    items.add(test);
  }

  public void removeItem(ProductItem test) {
    items.remove(test);
  }
}
