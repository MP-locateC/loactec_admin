package com.example.locatec;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends BaseAdapter {
  List<RequestItem> items = new ArrayList<>();
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
    RequestItem item = items.get(position);

    if (convertView == null) {
      LayoutInflater inflater =
              (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      convertView = inflater.inflate(R.layout.request, parent, false);
    }

    TextView latitude = convertView.findViewById(R.id.latitude);
    TextView longitude = convertView.findViewById(R.id.longitude);
    TextView type = convertView.findViewById(R.id.type);

    // todo :: 이미지 띄우기
    convertView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Toast.makeText(context, item.latitude + "확인", Toast.LENGTH_SHORT).show();
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


    latitude.setText("위도 : " + item.latitude);
    longitude.setText("경도 : " + item.longitude);

    typeNaming(item, type);


    return convertView;

  }

  private void typeNaming(RequestItem item, TextView type) {
    if (item.type.equals("smoking"))
      type.setText("흡연 부스");
    if (item.type.equals("trash"))
      type.setText("쓰레기통");
    type.setText(item.type);
  }

  public void addItem(RequestItem test) {
    items.add(test);
  }
}
