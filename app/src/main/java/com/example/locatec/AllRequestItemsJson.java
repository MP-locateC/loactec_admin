package com.example.locatec;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class AllRequestItemsJson {
  int status;
  List<ProductItem> response;
}

