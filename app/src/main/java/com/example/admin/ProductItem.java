package com.example.admin;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class ProductItem {
  Long id;
  String createdAt;
  String updatedAt;
  String latitude;
  String longitude;
  String type;
  boolean isRegister;
  String imageUrl;
}
