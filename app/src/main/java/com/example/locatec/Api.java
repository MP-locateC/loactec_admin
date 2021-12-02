package com.example.locatec;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
  @GET("/api/admin/find/all")
  Call<Test> findAll();
}
