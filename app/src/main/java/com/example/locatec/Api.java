package com.example.locatec;

import lombok.Getter;
import lombok.Setter;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {
  @GET("/api/admin/find/NotRegistered")
  Call<AllRequestItemsJson> findNotRegistered();

  @GET("/api/admin/find/all")
  Call<AllRequestItemsJson> findAll();

  @POST("/api/admin/register/permit")
  Call<OneRequestItemJson> permitRequest(@Body ItemId itemId);

  @POST("/api/admin/register/reject")
  Call<OneRequestItemJson> rejectRequest(@Body ItemId itemId);

  @POST("/api/admin/delete")
  Call<DeleteResponseJson> deleteProduct(@Body ItemId itemId);
}


