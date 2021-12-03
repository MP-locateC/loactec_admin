package com.example.locatec;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends BaseAdapter {
  List<Test2> items = new ArrayList<>();
  Context context;

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
    Test2 item = items.get(position);

    if (convertView == null) {
      LayoutInflater inflater =
              (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      convertView = inflater.inflate(R.layout.request, parent, false);
    }

    TextView latitude = convertView.findViewById(R.id.latitude);
    TextView longitude = convertView.findViewById(R.id.longitude);
    TextView type = convertView.findViewById(R.id.type);


    // todo :: type 설정


    // todo :: 이미지 띄우기
    convertView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Toast.makeText(context, item.latitude + "확인", Toast.LENGTH_SHORT).show();
      }
    });


    latitude.setText("위도 : " + item.latitude);
    longitude.setText("경도 : " + item.longitude);
    type.setText(item.type);

    return convertView;

  }

  public void addItem(Test2 test) {
    items.add(test);
  }
}
