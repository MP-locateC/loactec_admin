package com.example.locatec;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
  @GET("/api/admin/find/all")
  Call<AllRequestItemsJson> findAll();
}
