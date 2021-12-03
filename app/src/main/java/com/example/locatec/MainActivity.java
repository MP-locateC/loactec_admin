package com.example.locatec;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

  private BottomNavigationView bottomNavigationView;
  private FragmentManager fm;
  private FragmentTransaction ft;


  private AllRegisterRequest allRegisterRequest;
  private AllProduct allProduct;


  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main_view);


    bottomNavigationView = findViewById(R.id.bottom);
    bottomNavigationView.setSelectedItemId(R.id.register);

    allRegisterRequest = new AllRegisterRequest();
    allProduct = new AllProduct();
    setFrag(0);

    bottomNavigationView.setOnNavigationItemSelectedListener(
            new BottomNavigationView.OnNavigationItemSelectedListener() {
              @Override
              public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                  case R.id.register:
                    setFrag(0);
                    break;
                  case R.id.all:
                    setFrag(1);
                    break;
                }
                return true;
              }
            });
  }

  private void setFrag(int n) {
    fm = getSupportFragmentManager();
    ft = fm.beginTransaction();
    switch (n) {
      case 0:
        ft.replace(R.id.main_frame, allRegisterRequest);
        ft.commit();
        break;
      case 1:
        ft.replace(R.id.main_frame, allProduct);
        ft.commit();
        break;
    }
  }

}

