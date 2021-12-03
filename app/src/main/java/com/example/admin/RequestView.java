package com.example.admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


public class RequestView extends LinearLayout {

  private TextView latitude, longitude, type;
  private Button accept, reject;


  public RequestView(Context context, requestInfo requestInfo) {
    super(context);
    initView(requestInfo);
  }

//  public RequestView(Context context, Test test, @Nullable AttributeSet attrs) {
//    super(context, attrs);
//
//    initView();
//  }

  private void initView(requestInfo requestInfo) {
    String infService = Context.LAYOUT_INFLATER_SERVICE;
    LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(infService);
    View view = layoutInflater.inflate(R.layout.request, this, false);
    latitude = view.findViewById(R.id.latitude);
    for (int i = 0; i < 10; i++) {
      System.out.println();
    }
    System.out.println(requestInfo.latitude);
    System.out.println(requestInfo.longitude);

    latitude.setText(requestInfo.latitude);

    longitude = view.findViewById(R.id.longitude);
    longitude.setText(requestInfo.longitude);

    addView(view);
  }
}


