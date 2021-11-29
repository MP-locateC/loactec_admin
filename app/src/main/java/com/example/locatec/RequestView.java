package com.example.locatec;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


public class RequestView extends LinearLayout {

  private TextView latitude, longitude, type;
  private Button accept, reject;


  public RequestView(Context context, Test test) {
    super(context);
    initView(test);
  }

//  public RequestView(Context context, Test test, @Nullable AttributeSet attrs) {
//    super(context, attrs);
//
//    initView();
//  }

  private void initView(Test test) {
    String infService = Context.LAYOUT_INFLATER_SERVICE;
    LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(infService);
    View view = layoutInflater.inflate(R.layout.request, this, false);
    latitude = view.findViewById(R.id.latitude);
    latitude.setText(test.testA);

    addView(view);
  }
}


class Test {
  String testA = "testA";
  String testB = "testB";
}